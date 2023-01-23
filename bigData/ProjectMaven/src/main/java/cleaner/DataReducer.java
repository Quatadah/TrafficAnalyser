package cleaner;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class DataReducer extends Reducer<Text, Text, Text, Text> {
    private AtomicInteger uniqueId = new AtomicInteger();
    private String val = null;
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            val = value.toString();
        }
        context.write(new Text(String.valueOf(uniqueId.incrementAndGet())), new Text(val.toString()));
    }
}
