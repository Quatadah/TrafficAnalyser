import helpers.DataParser;
import helpers.JsonCsvReader;
import models.CommonData;
import models.Folder;

import java.util.List;

public class App {
    public static void main(String[] args) {
        JsonCsvReader jsonCsvReader = new JsonCsvReader();
        List<Folder> posts = jsonCsvReader.readJson();
        DataParser dataParser = new DataParser();
        List<CommonData> commonData = dataParser.getCommonData(posts);
        System.out.println(commonData.size());

    }
}
