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

public class TraficByDateHourTypeAndSensor {
    public static  class TraficByDateHourTypeAndSensorMapper  extends Mapper<Text, Text, Text, Text> {

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
                type.set(commonData.getDate()+","+
                        commonData.getTime().split(":")[0]+","+
                        commonData.getVehicleType().toUpperCase(Locale.ROOT));
                context.write(type, new Text(commonData.getDirection()));
        }

    }

    public static class TraficByDateHourTypeAndSensorReducer extends Reducer<Text, Text, Text,Text> {
        private final IntWritable outputValue = new IntWritable();
        private final Text outputKey = new Text();


        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

            Map<String, Integer> directionCount = new HashMap<>();
            for (Text value : values) {
                String direction = value.toString();
                if(directionCount.containsKey(direction)){
                    directionCount.put(direction, directionCount.get(direction) + 1);
                }else{
                    directionCount.put(direction, 1);
                }
            }
            for(Map.Entry<String, Integer> entry : directionCount.entrySet()){
                outputKey.set(entry.getKey());
                outputValue.set(entry.getValue());
                context.write(new Text(key.toString()+","+outputKey), new Text(String.valueOf(outputValue)));
            }
        }
    }
}
