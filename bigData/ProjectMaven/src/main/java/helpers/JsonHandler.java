package helpers;

import models.CSVFile;
import models.Folder;
import org.json.simple.parser.ParseException;


import java.util.List;

public class JsonHandler {

    private JsonCsvReader jsonCsvReader ;
    private List<Folder> posts ;


    public JsonHandler(String posts) throws ParseException {
        this.jsonCsvReader = new JsonCsvReader();
        this.posts = jsonCsvReader.stringToPosts(posts);
    }


    public String getSensorType(String fileName){
        String sensor = null;
        for(Folder post : posts) {
            for(CSVFile file : post.getFiles()) {
                if ( fileName.contains(file.getName())) {
                    sensor = post.getSensor();
                }
            }
        }
        return sensor;
    }
    public String getFileType(String fileName){
        String fileType = null;
        for(Folder post : posts) {
            for(CSVFile file : post.getFiles()) {
                if ( fileName.contains(file.getName())) {
                    fileType = file.getType();
                }
            }
        }
        return fileType;
    }

    public String getPostName(String fileName){
        String postName = null;
        for(Folder post : posts) {
            for(CSVFile file : post.getFiles()) {
                if ( fileName.contains(file.getName())) {
                    postName = post.getName();
                }
            }
        }
        return postName;
    }


    public int getPostsSize() {
        return posts.size();
    }


}
