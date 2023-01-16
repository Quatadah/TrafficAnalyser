package helpers;

import models.CSVFile;
import models.Folder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonCsvReader {

    private List<Folder> postes = new ArrayList<>();
    private String jsonFile;
    private String cvsFolderPath = getClass().getResource("/ResultatCSV/").getPath().substring(1) ;

    public JsonCsvReader() {
        this.jsonFile = getClass().getResource("/paths.json").getPath() ;
    }

    public List<Folder> readJson() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(jsonFile)) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            for (Object key : jsonObject.keySet()) {
                String folderName = (String) key;
                JSONObject folderObject = (JSONObject) jsonObject.get(folderName);
                JSONArray files = (JSONArray) folderObject.get("files");
                String sensorType = (String) folderObject.get("sensor");
                Folder poste = new Folder(folderName,sensorType);

                for (Object file : files) {
                    JSONObject fileObject = (JSONObject) file;
                    String fileName = fileObject.get("name")+".csv";
                    String fileType = (String) fileObject.get("type");
                    String path = cvsFolderPath.concat(folderName + "/" + fileName) ;
                    CSVParser parser = CSVParser.parse(new File(path), StandardCharsets.UTF_8, CSVFormat.DEFAULT);
                    parser.forEach(record -> {
                        if (record.getRecordNumber() == 1) {
                            StringBuilder sb = new StringBuilder();
                            record.forEach(col -> sb.append(col + ","));
                            poste.addFile(new CSVFile((String) fileObject.get("name"),sb.toString(),fileType));
                        }
                    });
                    parser.close();
                    postes.add(poste);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return postes;
    }

    public String getCvsFolderPath() {
        return cvsFolderPath;
    }
}
