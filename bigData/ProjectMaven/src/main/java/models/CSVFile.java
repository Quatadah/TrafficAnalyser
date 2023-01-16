package models;

public class CSVFile {
    private String name;
    private String header;
    private String type;

    public CSVFile(String name, String header, String type) {
        this.name = name;
        this.header = header;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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
                ", header='" + header + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
