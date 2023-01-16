
import java.text.SimpleDateFormat;
import java.util.Date;




public class DataCleaner {
    public static class CommonData {
        private String date; // dd/mm/yy
        private String time; // hh:mm:ss:cc
        private String vehicleType; // VL PL 2R
        private String speed;


        public CommonData(String date, String time, String vehicleType, String speed){
            this.date = date;
            this.time = time;
            this.vehicleType = vehicleType;
            this.speed = speed;
        }
    }

    public static String parseDate(String inputDate, String pattern){
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

    public static CommonData parseRadar(String[] row) {
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
        return new CommonData(date, time, vehicleType, speed);
    }

    public static CommonData parseTube(String[] row) {
        String voie = row[0];
        String date = parseDate(row[1], "dd/M/yy");
        String heureMinute = row[2];
        String seconde = row[3];
        String centieme = row[4];
        String speed = row[5];
        String type = row[8];
        String time = heureMinute + ":" + seconde + ":" + centieme;
        String vehicleType = type;
        return new CommonData(date, time, vehicleType, speed);
    }

    public static CommonData parseCamera(String[] row) {
        // Extraire les valeurs de la ligne de données et les transformer en format commun
        String vehicleType = row[1];
        String date = parseDate(row[2], "dd/mm/yy");
        String time = row[3] + ":00";
        String versJardinPublic = row[4];
        String versGare = row[5];
        String remarquesCyclistes = row[6];
        String direction = "";
        if (!versJardinPublic.isEmpty()) {
            direction = "nord_sud";
        } else if (!versGare.isEmpty()) {
            direction = "sud-nord";
        }
        String speed = "0"; // La vitesse n'est pas disponible dans ces données
        return new CommonData(date, time, vehicleType, speed);
    }

}
