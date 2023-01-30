package analyzers;

import models.CommonData;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Locale;

public class ObservationbyPost {

    public static  class ObservationbyPostMapper  extends Mapper<Text, Text, Text, Text> {

        private CommonData commonData ;

        @Override
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            String[] data = value.toString().split(",");
            commonData = new CommonData(data);
            Text type = new Text();
            if (commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("EDPM") || commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("MOTO")
                    || commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("TROTTINETTE") || commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("VÉLO")
                    || commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("VÉLO ÉLECTRIQUE"))
                commonData.setVehicleType("2R");
            if (commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("BUS"))
                commonData.setVehicleType("PL");
            if (commonData.getVehicleType().toUpperCase(Locale.ROOT).equals("UT"))
                commonData.setVehicleType("VL");
            type.set(commonData.getDate()+","+commonData.getPost()+","+commonData.getSensorType());
            context.write(type, new Text(commonData.toString()));
        }

    }

    public static class ObservationbyPostCombiner extends Reducer<Text, Text, Text, Text> {
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for (Text val : values) {
                count++;
            }
            context.write(new  Text(key),
                    new Text(String.valueOf(count)));
        }
    }

    public static class ObservationbyPostReducer extends Reducer<Text, Text, Text, Text> {
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int num = 0;
            for (Text val : values) {
                num = Integer.parseInt(val.toString());
            }
            context.write(key, new Text(String.valueOf(num)));
        }
    }

}
