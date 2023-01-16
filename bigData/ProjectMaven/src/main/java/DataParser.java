import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class JsonCsvReader {

    private String jsonFile;
    private String cvsFolderPath = getClass().getResource("/ResultatCSV/").getPath() ;

    public JsonCsvReader(String jsonFile) {
        this.jsonFile = getClass().getResource("/"+jsonFile).getPath() ;
    }

    public void readJsonAndCsv() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(jsonFile)) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            for (Object key : jsonObject.keySet()) {
                String folderName = (String) key;
                JSONObject folderObject = (JSONObject) jsonObject.get(folderName);
                JSONArray files = (JSONArray) folderObject.get("files");
                String sensorType = (String) folderObject.get("sensor");


                for (Object file : files) {
                    JSONObject fileObject = (JSONObject) file;
                    String fileName = (String) fileObject.get("name")+ ".csv";
                    String fileType = (String) fileObject.get("type");
                        String path = cvsFolderPath + folderName + "/" + fileName ;
                        List<String> lines = Files.readAllLines(Paths.get(path));
                        System.out.println("Type: " + fileType + " Sensor :" +sensorType + "  File: " + fileName +" " +lines.get(1)   );
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        JsonCsvReader jsonCsvReader = new JsonCsvReader("paths.json");
        jsonCsvReader.readJsonAndCsv();
    }
}
