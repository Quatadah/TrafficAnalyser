package helpers;

import helpers.DataCleaner;
import models.CSVFile;
import models.CommonData;
import models.Folder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

    private List<CommonData> camerasData = new ArrayList<>();

    public List<CommonData> getCommonData(List<Folder> posts){
        JsonCsvReader jsonCsvReader = new JsonCsvReader();
        DataCleaner dataCleaner=new DataCleaner();
        posts.forEach(post->
        {
            if (post.getSensor().equals("camera")) {
                System.out.println(post + post.getFiles().get(0).toString());
                for (CSVFile CSVFile : post.getFiles()) {
                    String fileName = CSVFile.getName();
                    String path = jsonCsvReader.getCvsFolderPath().concat(post.getName() + "/" + fileName+".csv") ;
                    CSVParser parser;
                    try {
                        parser = CSVParser.parse(new File(path), StandardCharsets.UTF_8, CSVFormat.DEFAULT);
                        List<CSVRecord> records = parser.getRecords();
                        int count = records.size();
                        String[] header = CSVFile.getHeader().split(",");
                        for (CSVRecord r : records ) {
                            if(r.getRecordNumber()< count && r.getRecordNumber()>2){
                                String [] row = new String[r.size()];
                                for (int i = 0; i < r.size(); i++) {
                                    row[i] = r.get(i);
                                }
                                if (dataCleaner.parseCamera(row,header) != null)
                                    camerasData.add(dataCleaner.parseCamera(row,header));
                            }
                            parser.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return camerasData;
    }
}

