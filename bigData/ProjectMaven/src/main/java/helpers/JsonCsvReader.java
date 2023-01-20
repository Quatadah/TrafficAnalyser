package helpers;

import models.CSVFile;
import models.Folder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonCsvReader {

    private List<Folder> postes = new ArrayList<>();
    private String jsonFile;

    public JsonCsvReader() {
        this.jsonFile = getClass().getResource("/paths.json").getPath() ;
    }

    public List<Folder> readJson() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(jsonFile)) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            jsonObjectToPosts(jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return postes;
    }

    public String jsontoString(){
        JSONObject jsonObject = null;
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(jsonFile)) {
            Object obj = jsonParser.parse(reader);
            jsonObject = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }


    public List<Folder> stringToPosts(String posts) throws ParseException {
             JSONParser jsonParser = new JSONParser();
             JSONObject jsonObject = (JSONObject) jsonParser.parse(posts);
             jsonObjectToPosts(jsonObject);
        return postes;
    }

    private void jsonObjectToPosts(JSONObject jsonObject) {
        for (Object key : jsonObject.keySet()) {
            String folderName = (String) key;
            JSONObject folderObject = (JSONObject) jsonObject.get(folderName);
            JSONArray files = (JSONArray) folderObject.get("files");
            String sensorType = (String) folderObject.get("sensor");
            Folder poste = new Folder(folderName,sensorType);
            for (Object file : files) {
                JSONObject fileObject = (JSONObject) file;
                String fileType = (String) fileObject.get("type");
                poste.addFile(new CSVFile((String) fileObject.get("name"),fileType));
            }
            postes.add(poste);
        }
    }

}
