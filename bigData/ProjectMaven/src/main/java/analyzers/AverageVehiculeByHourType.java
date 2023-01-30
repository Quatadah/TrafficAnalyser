package analyzers;


import models.CommonData;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AverageVehiculeByHourType {
    public static  class AverageVehiculeByHourTypeMapper extends Mapper<Text, Text, Text, Text> {

        private CommonData commonData ;

        @Override
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException {

                String[] data = value.toString().split(",");
                Text type = new Text();
                commonData = new CommonData(data);
                if (commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("EDPM") || commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("MOTO")
                        || commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("TROTTINETTE") || commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("VÉLO")
                        || commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("VÉLO ÉLECTRIQUE"))
                    commonData.setVehicleType("2R");
                if (commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("BUS"))
                    commonData.setVehicleType("PL");
                if (commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("UT"))
                    commonData.setVehicleType("VL");
                type.set(commonData.getDirection()+","+commonData.getTime().split(":")[0]+","+
                        commonData.getVehicleType().toUpperCase(Locale.ROOT));
                context.write(type, new Text(commonData.getDate()));
        }

    }

    public static class AverageVehiculeByHourTypeCombiner extends Reducer<Text, Text, Text, Text> {

         @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            Map<String, String> dateCount = new HashMap<>();
            int count =0 ;
            for (Text value : values) {
                String date = value.toString();
                dateCount.put(date,date);
                count ++;
            }

            context.write(key, new Text(String.valueOf(count/dateCount.size())));
        }
    }

    public static class AverageVehiculeByHourTypeReducer extends Reducer<Text, Text, Text,Text> {
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int avg =0 ;
            for (Text value : values) {
                avg = Integer.parseInt(String.valueOf(value));
            }
            context.write(key, new Text(String.valueOf(avg)));
        }
    }

}
