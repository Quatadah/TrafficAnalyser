package analyzers;

import helpers.DataCleaner;
import helpers.JsonHandler;
import models.CommonData;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class SpeedAverageByType {

    public static  class SpeedAverageByTypeMapper  extends Mapper<LongWritable, Text, Text, FloatWritable> {
        private String[] header;
        private CommonData commonData ;
        private String fileName;

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            try {
                InputSplit split = context.getInputSplit();
                if (split instanceof FileSplit) {
                    Path filePath = ((FileSplit) split).getPath();
                    fileName = filePath.getName();
                }
                DataCleaner dataCleaner=new DataCleaner();
                String jsonString = context.getConfiguration().get("posts");
                JsonHandler jsonHandler = new JsonHandler(jsonString);
                String sensorType = jsonHandler.getSensorType(fileName);

                String[] data = value.toString().split(",");
                Text type = new Text();

                if (sensorType.equals("tube") && value !=null){
                    if (key.get() == 0 ) return;
                    commonData = dataCleaner.cleanTube(data,fileName);
                }else if (sensorType.equals("radar") && value !=null){
                    if (key.get() == 0 ) return;
                    commonData = dataCleaner.cleanRadar(data,jsonHandler.getFileType(fileName),fileName);
                } else if (sensorType.equals("camera") && value !=null){
                    if (key.get() == 0 ){
                        header = value.toString().split(",");
                    }else{
                        if (data.length == 4){
                            String[] newData = Arrays.copyOf(data, data.length + 1);
                            newData[data.length] = " ";
                            commonData = dataCleaner.cleanCamera(newData,header);
                        }else{
                            commonData = dataCleaner.cleanCamera(data,header);
                        }
                    }
                }
                if (commonData != null ){
                    type.set(commonData.getVehicleType().toUpperCase(Locale.ROOT));
                    context.write(type, new FloatWritable(Float.parseFloat(commonData.getSpeed()))); }
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
            context.write(key, new FloatWritable(sum/count));
        }
    }
}
