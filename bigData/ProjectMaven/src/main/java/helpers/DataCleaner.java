package helpers;

import models.CommonData;

import java.text.SimpleDateFormat;
import java.util.Date;




public class DataCleaner {

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
            String time = row[2].split(" ")[1] + ":00";
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
            return new CommonData(date, time, vehicleType, speed, direction);
        }else {
            return null;
        }
    }

    public CommonData parseRadar(String[] row) {
        // Extraire les valeurs de la ligne de données et les transformer en format commun
        String sens = row[0];
        String jour = row[1];
        int heureParMinute = Integer.parseInt(row[2]);
        int secondeParCentieme = Integer.parseInt(row[3]);
        String unparsedSpeed = row[4];
        String ser = row[5];
        String vehicleType = row[6];
        String date = jour + "/00/00";
        int heure = heureParMinute / 60;
        int minute = heureParMinute % 60;
        int seconde = secondeParCentieme / 100;
        int centieme = secondeParCentieme % 100;
        String time = heure + ":" + minute + ":" + seconde + ":" + centieme;

        String speed = unparsedSpeed.substring(2);
        String direction ="";
        return new CommonData(date, time, vehicleType, speed,direction);
    }

    public CommonData parseTube(String[] row) {
        String voie = row[0];
        String date = parseDate(row[1], "dd/M/yy");
        String heureMinute = row[2];
        String seconde = row[3];
        String centieme = row[4];
        String speed = row[5];
        String type = row[8];
        String time = heureMinute + ":" + seconde + ":" + centieme;
        String vehicleType = type;
        String direction="";
        return new CommonData(date, time, vehicleType, speed,direction);
    }
}
