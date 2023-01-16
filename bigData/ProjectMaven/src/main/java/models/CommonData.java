package models;

public class CommonData {
    private String date; // dd/mm/yy
    private String time; // hh:mm:ss:cc
    private String vehicleType; // VL PL 2R
    private String speed;
    private String direction;


    public CommonData(String date, String time, String vehicleType, String speed,String direction){
        this.date = date;
        this.time = time;
        this.vehicleType = vehicleType;
        this.speed = speed;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "CommonData{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", speed='" + speed + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
