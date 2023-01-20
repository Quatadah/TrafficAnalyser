package models;

public class CSVFile {
    private String name;
    private String type;

    public CSVFile(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
