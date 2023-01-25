package analyzers;


import models.CommonData;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;
import java.util.Locale;

public class SpeedAverageByType {

    public static  class SpeedAverageByTypeMapper  extends Mapper<Text, Text, Text, FloatWritable> {

        private CommonData commonData ;

        @Override
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException {

                String[] data = value.toString().split(",");
                commonData = new CommonData(data);
                Text type = new Text();
                if (commonData != null ){
                    type.set(commonData.getVehicleType().toUpperCase(Locale.ROOT));
                    context.write(type, new FloatWritable(Float.parseFloat(commonData.getSpeed()))); }
        }

    }

    public static class SpeedAverageByTypeReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
        @Override
        public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
            float sum = 0;
            int count = 0;
            for (FloatWritable val : values) {
                if (val.get()!=0){
                sum += val.get();
                count++;}
            }
            if (!key.equals("UT") || !key.equals("EDPM"))
            context.write(key, new FloatWritable(sum/count));
        }
    }
}
