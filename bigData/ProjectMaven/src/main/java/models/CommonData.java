package models;

public class CommonData {
    private String sensorType;
    private String date; // dd/mm/yy
    private String time; // hh:mm:ss:cc
    private String vehicleType; // VL PL 2R
    private String speed;
    private String direction;


    public CommonData(String sensor,String date, String time, String vehicleType, String speed,String direction){
        this.date = date;
        this.time = time;
        this.vehicleType = vehicleType;
        this.speed = speed;
        this.direction = direction;
        this.sensorType = sensor;
    }

    @Override
    public String toString() {
        return "CommonData{" +
                "type='" + sensorType + '\'' +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", speed='" + speed + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }

    public String print() {
        return  sensorType + "," + date + "," + time + "," + vehicleType + "," + speed + "," + direction ;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
