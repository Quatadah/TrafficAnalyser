package models;

import java.util.ArrayList;
import java.util.List;

public class Folder {

    private String name;
    private String sensor;
    private List<CSVFile> CSVFiles;

    public Folder(String name, String sensor) {
        this.name = name;
        this.sensor = sensor;
        this.CSVFiles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public List<CSVFile> getFiles() {
        return CSVFiles;
    }

    public void setFiles(List<CSVFile> CSVFiles) {
        this.CSVFiles = CSVFiles;
    }

    public  void addFile(CSVFile CSVFile){
        CSVFiles.add(CSVFile);
    }

    @Override
    public String toString() {
        return "Post{" +
                "name='" + name + '\'' +
                ", sensor='" + sensor +
                '}';
    }
}
