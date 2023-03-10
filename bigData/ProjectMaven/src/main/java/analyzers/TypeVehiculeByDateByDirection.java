package analyzers;


import models.CommonData;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;
import java.util.Locale;

public class TypeVehiculeByDateByDirection {
    public static  class TypeVehiculeByDateByDirectionMapper  extends Mapper<Text, Text, Text, Text> {

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

                type.set(commonData.getDate()+","+commonData.getVehicleType().toUpperCase(Locale.ROOT)+","
                        +commonData.getDirection());
                    context.write(type, new Text(commonData.toString()));
        }

    }
    public static class TypeVehiculeByDateByDirectionCombiner extends Reducer<Text, Text, Text, Text> {
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text val : values) {
                context.write(new  Text(key), new Text(val));
            }
        }
    }
    public static class TypeVehiculeByDateByDirectionReducer extends Reducer<Text, Text, Text, Text> {
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
}
