package hbase;


import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import java.io.IOException;


import org.apache.hadoop.hbase.TableName;

public class WriteToHBase {

    public WriteToHBase() {
    }

    private  String TABLE_SPEED = "yelamoud:speed";
    private  String TABLE_TYPE = "yelamoud:type";


    public String getTABLE_SPEED() {
        return TABLE_SPEED;
    }
    public String getTABLE_TYPE() {
        return TABLE_TYPE;
    }


    public static class speedAverageReducer extends TableReducer<Text, FloatWritable, Text> {
        @Override
        public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
            for (FloatWritable val : values) {
                if (val.get() !=0){
                Put put = new Put(key.toString().getBytes());

                put.addColumn(Bytes.toBytes("speed"), Bytes.toBytes("speed"), Bytes.toBytes(val.get()));
                context.write(new Text(key.toString()), put);}
            }
        }
    }

    public static class typeByDateByDirectionReducer extends TableReducer<Text, Text, Text> {
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text val : values) {
                if (val.toString() != null){
                    Put put = new Put(key.toString().getBytes());

                    put.addColumn(Bytes.toBytes("NombreVehicule"), Bytes.toBytes("NombreVehicule"), Bytes.toBytes(val.toString().split(",")[0]));
                    put.addColumn(Bytes.toBytes("TypeVehicule"), Bytes.toBytes("TypeVehicule"), Bytes.toBytes(val.toString().split(",")[2]));
                    put.addColumn(Bytes.toBytes("Direction"), Bytes.toBytes("Direction"), Bytes.toBytes(val.toString().split(",")[1]));
                    context.write(new Text(key.toString()), put);}
            }
        }
    }

    public  void createTable(Connection connect,String TABLE) {
        try {
            final Admin admin = connect.getAdmin();
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(TABLE));
            if (TABLE.contains("speed")){
                ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder("speed".getBytes()).build();
                tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
            }else if (TABLE.contains("type")){
                ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder("NombreVehicule".getBytes()).build();
                ColumnFamilyDescriptor columnFamilyDescriptor1 = ColumnFamilyDescriptorBuilder.newBuilder("TypeVehicule".getBytes()).build();
                ColumnFamilyDescriptor columnFamilyDescriptor2 = ColumnFamilyDescriptorBuilder.newBuilder("Direction".getBytes()).build();
                tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor).setColumnFamily(columnFamilyDescriptor1).setColumnFamily(columnFamilyDescriptor2);
            }
            TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
            if (!admin.tableExists(TableName.valueOf(TABLE))) {
                admin.createTable(tableDescriptor);
            }
            admin.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
