package analyzers;

import models.CommonData;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class InOutByDay {


    public static  class InOutByDayMapper extends Mapper<Text, Text, Text, Text> {

        private CommonData commonData ;

        @Override
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException {

            String[] data = value.toString().split(",");
            Text type = new Text();
            commonData = new CommonData(data);

            if (commonData.getDirection().contains("Crous") || commonData.getDirection().contains("BEC")
                    ||  commonData.getDirection().contains("bibliothéque") || commonData.getDirection().contains("Entrée Fac")
                    || commonData.getDirection().equals("Léon Duguit") || commonData.getDirection().contains("carrefour")
                    || commonData.getDirection().contains("cafeteria") || commonData.getDirection().contains("professeurs"))
            {commonData.setDirection("Entrée");}
            else {
                commonData.setDirection("Sortie");
            }

            type.set(commonData.getDate()+","+commonData.getVehicleType()+","+ commonData.getDirection());
            context.write(type, new Text(commonData.getVehicleType()));
        }

    }

    public static class InOutByDayReducer extends Reducer<Text, Text, Text,Text> {

        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

            int count = 0;
            for (Text val : values) {
                count++;
            }
            context.write(key, new Text(String.valueOf(count)));

    }
}



}
