package models;

import java.util.Locale;

public class CommonData {
    private String sensorType;
    private String date; // dd/mm/yy
    private String time; // hh:mm:ss:cc
    private String vehicleType; // VL PL 2R
    private String speed;
    private String direction;
    private String post;


    public CommonData(String sensor,String date, String time, String vehicleType, String speed,String direction){
        this.date = date;
        this.time = time;
        this.vehicleType = vehicleType.toUpperCase(Locale.ROOT);
        this.speed = speed;
        this.direction = direction;
        this.sensorType = sensor;
    }
    public CommonData(String[] data){
        this.sensorType = data[0];
        this.date = data[1];
        this.time = data[2];
        this.vehicleType = data[3].toUpperCase(Locale.ROOT);
        this.speed = data[4];
        this.direction = data[5];
        this.post= data[6];
    }


    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append(sensorType);
        tmp.append(",");
        tmp.append(date);
        tmp.append(",");
        tmp.append(time);
        tmp.append(",");
        tmp.append(vehicleType.toUpperCase(Locale.ROOT));
        tmp.append(",");
        tmp.append(speed);
        tmp.append(",");
        tmp.append(direction);
        tmp.append(",");
        tmp.append(post);

        return tmp.toString();
    }

    public String print() {
        StringBuilder tmp = new StringBuilder();
        tmp.append(sensorType);
        tmp.append(",");
        tmp.append(date);
        tmp.append(",");
        tmp.append(time);
        tmp.append(",");
        tmp.append(vehicleType.toUpperCase(Locale.ROOT));
        tmp.append(",");
        tmp.append(speed);
        tmp.append(",");
        tmp.append(direction);
        return  tmp.toString();
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

    public String getPost() {
        return post;
    }
}
