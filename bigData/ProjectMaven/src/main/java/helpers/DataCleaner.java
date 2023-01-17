package helpers;

import models.CommonData;

import java.text.SimpleDateFormat;
import java.util.Date;




public class DataCleaner {

    public String formatTime(String hour, String minute, String seconds, String centimes) {
        int h = Integer.parseInt(hour);
        int m = Integer.parseInt(minute);
        int s = Integer.parseInt(seconds);
        int c = Integer.parseInt(centimes);
        return String.format("%02d:%02d:%02d.%02d", h, m, s, c);
    }

    public  String parseDate(String inputDate, String pattern){
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat(pattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yy");
            Date tmpDate = inputFormat.parse(inputDate);
            String date = outputFormat.format(tmpDate);
            return date;
        }catch (Exception e) {
            System.out.println("Error parsing date");
            return null;
        }
    }
    public CommonData parseCamera(String[] row,String[] header) {
        if( row.length == 5 || row.length == 7) {
            String vehicleType = row[1];
            String date = parseDate(row[2].split(" ")[0], "dd/mm/yy");
            String time = row[2].split(" ")[1] ;
            String direction;
            if (row[3].isEmpty() || row[4].isEmpty()) {
                if (row[3].equals("1") || row[3].equals("x")) {
                    if (header[3].equals("Entrant")) {
                        direction = "vers entrée du site";
                    } else if (header[3].equals("Sortant")) {
                        direction = "vers sortie du site";
                    } else {
                        direction = header[3];
                    }
                } else {
                    direction = header[4];
                }
            } else {
                if (row[4].equals("E")) {
                    direction = "vers entrée du site";
                } else if (row[4].equals("S1")) {
                    direction = "vers parking cafeteria";
                } else if (row[4].equals("S2")) {
                    direction = "vers avenue Léon Duguit";
                } else if (row[4].equals("S3")) {
                    direction = "vers parking des professeurs";
                } else {
                    direction = "Not defined";
                }
            }
            String speed = "0"; // La vitesse n'est pas disponible dans ces données
            return new CommonData("Camera",date, time, vehicleType, speed, direction);
        }else {
            return null;
        }
    }

    public CommonData parseRadar(String[] row,String type,String fileName) {
        String date;
        String time;
        String speed;
        String vehicleType;
        String direction;

        if (type.equals("type1")){
            date = parseDate(row[0].split(" ")[0], "dd/mm/yy");
            time = row[0].split(" ")[1].concat("."+row[1]);
            speed = row[3].concat("."+row[4]);
            vehicleType = row[7];
                if (row[2].contains("Entr")) {
                    direction="vers Entrée Fac";
                }else{
                    direction = "vers " +row [2];
                }
        }else if (type.equals("type2")){
            date = parseDate(row[0], "dd/M/yy");
            time = formatTime(row[1].split(":")[0],row[1].split(":")[1],row[2],row[3]);
            speed = row[4];
            vehicleType = row[5];
            if (fileName.contains("Sortie_Fac")){
                direction = "vers Sortie Fac";
            }else {
                direction="";
            }
        }else {
            date = parseDate(row[1]+"/10/22", "dd/M/yy");
            int heureParMinute = Integer.parseInt(row[2]);
            int secondeParCentieme = Integer.parseInt(row[3]);
            int heure = heureParMinute / 60;
            int minute = heureParMinute % 60;
            int seconde = secondeParCentieme / 100;
            int centieme = secondeParCentieme % 100;
            time = String.format("%02d:%02d:%02d.%02d", heure, minute, seconde, centieme);
            int sp = Integer.parseInt(row[4].split("=")[1]);
            speed = String.format("%d",sp);
            int ser = Integer.parseInt(row[5].split("=")[1]);
            if ( ser > 205 && ser <1140 ){
                vehicleType = "VL";
            }else if(ser>1140) {
                vehicleType = "PL";
            }else if (ser < 205){
                vehicleType="2RM";
            }else {
                vehicleType=null;
            }
            if (row[0].equals("1") || row[0].contains("Sortie")){
                direction = "vers Sortie Fac";
            }else if (row[0].equals("2")|| row[0].contains("Entr")){
                direction = "vers Entrée Fac";
            }else {
                direction=null;
            }
        }
        return new CommonData("Radar",date, time, vehicleType, speed,direction);
    }

    public CommonData parseTube(String[] row,String fileName) {
        String date = parseDate(row[0], "dd/M/yy");
        String heureMinute = row[1];
        String seconde = row[2];
        String centieme = row[3];
        String speed = row[4];
        String vehicleType;
        if (row[5].contains("lectrique")){
            vehicleType = "Vélo électrique";
        }else{
            vehicleType = row[5];
        }
        String time = formatTime(heureMinute.split(":")[0],heureMinute.split(":")[1],seconde,centieme);
        String direction;
        if(fileName.contains("Fac")){
            direction="vers Fac";
        }else if(fileName.contains("Rocade")){
            direction="vers Rocade";
        }else if(fileName.contains("Talence")){
            direction="vers Talence";
        }else if(fileName.contains("COSEC")) {
            direction="vers COSEC";
        }else if(fileName.contains("BEC")) {
            direction="vers BEC";
        }else if(fileName.contains("Sortie")){
            direction="vers Sortie";
        }else {
            direction = "vers Entrée";
        }
        return new CommonData("Tube",date, time, vehicleType, speed,direction);
    }
}
