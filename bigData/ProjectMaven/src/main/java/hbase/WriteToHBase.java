package hbase;


import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import java.io.IOException;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.HColumnDescriptor;

public class WriteToHBase {

    public WriteToHBase() {
    }

    private  String TABLE_NAME = "yelamoud:Sensor_analyzer";

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public  class WriteReducer extends TableReducer<LongWritable, Text, Text> {

        @Override
        public void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text val : values) {
                if (Float.parseFloat(val.toString())!=0){
                Put put = new Put(key.toString().getBytes());
                put.addColumn("speed".getBytes(), "speedAverage".getBytes(), val.getBytes());
                context.write(new Text(key.toString()), put);}
            }
        }
    }

    public  void createTable(Connection connect) {
        try {
            final Admin admin = connect.getAdmin();
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(TABLE_NAME));
            ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder("speed".getBytes()).build();
            tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
            TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
            if (!admin.tableExists(TableName.valueOf(TABLE_NAME))) {
                admin.createTable(tableDescriptor);
            }
            admin.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
