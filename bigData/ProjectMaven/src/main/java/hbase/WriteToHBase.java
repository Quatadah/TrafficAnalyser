package hbase;


import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


import org.apache.hadoop.hbase.TableName;

public class WriteToHBase {

    public WriteToHBase() {
    }

    private  String TABLE_SPEED = "yelamoud:speed";
    private  String TABLE_TYPE  = "yelamoud:type";
    private  String TABLE_AVGVLBYHOUR = "yelamoud:avgVehiculeByHour";
    private  String TABLE_INOUT  = "yelamoud:inOutCampus";
    private  String TABLE_OBSERVATIONS = "yelamoud:observationsByDate";


    public String getTABLE_SPEED() {
        return TABLE_SPEED;
    }
    public String getTABLE_TYPE() {
        return TABLE_TYPE;
    }
    public String getTABLE_AVGVLBYHOUR() { return TABLE_AVGVLBYHOUR; }
    public String getTABLE_INOUT() { return TABLE_INOUT; }
    public String getTABLE_OBSERVATIONS() { return TABLE_OBSERVATIONS; }

    public static class SpeedAverageReducer extends TableReducer<Text, FloatWritable, Text> {
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

    public static class TypeVehiculeByDateByDirectionReducer extends TableReducer<Text, Text, Text> {

        private AtomicInteger uniqueId = new AtomicInteger();

        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text val : values) {
                if (val.toString() != null){
                    Put put = new Put(String.valueOf(uniqueId.incrementAndGet()).getBytes());

                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("Date"), Bytes.toBytes(key.toString().split(",")[0]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("Direction"), Bytes.toBytes(key.toString().split(",")[2]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("VehiculeType"), Bytes.toBytes(key.toString().split(",")[1]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("NumberOfVehicule"), Bytes.toBytes(String.valueOf(val)));
                    context.write(new Text(key.toString()), put);}
            }
        }
    }

    public static class AverageVehiculeByHourAndTypeReducer extends TableReducer<Text, Text, Text> {

        private AtomicInteger uniqueId = new AtomicInteger();

        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text val : values) {
                if (val.toString() != null){
                    Put put = new Put(String.valueOf(uniqueId.incrementAndGet()).getBytes());

                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("Heure"), Bytes.toBytes(key.toString().split(",")[1]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("Direction"), Bytes.toBytes(key.toString().split(",")[0]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("VehiculeType"), Bytes.toBytes(key.toString().split(",")[2]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("Average"), Bytes.toBytes(String.valueOf(val)));
                    context.write(new Text(key.toString()), put);}
            }
        }
    }

    public static class InOutByDateReducer extends TableReducer<Text, Text, Text> {

        private AtomicInteger uniqueId = new AtomicInteger();

        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text val : values) {
                if (val.toString() != null){
                    Put put = new Put(String.valueOf(uniqueId.incrementAndGet()).getBytes());

                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("Date"), Bytes.toBytes(key.toString().split(",")[0]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("VehiculeType"), Bytes.toBytes(key.toString().split(",")[1]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("Sens"), Bytes.toBytes(key.toString().split(",")[2]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("NumberOfVehicule"), Bytes.toBytes(String.valueOf(val)));
                    context.write(new Text(key.toString()), put);}
            }
        }
    }

    public static class ObservationByPostReducer extends TableReducer<Text, Text, Text> {

        private AtomicInteger uniqueId = new AtomicInteger();

        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text val : values) {
                if (val.toString() != null){
                    Put put = new Put(String.valueOf(uniqueId.incrementAndGet()).getBytes());

                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("Date"), Bytes.toBytes(key.toString().split(",")[0]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("Post"), Bytes.toBytes(key.toString().split(",")[1]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("SensorType"), Bytes.toBytes(key.toString().split(",")[2]));
                    put.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("NumberOfObservation"), Bytes.toBytes(String.valueOf(val)));
                    context.write(new Text(key.toString()), put);}
            }
        }
    }

    public  void createTable(Connection connect,String TABLE,String columnDesc) {
        try {
            final Admin admin = connect.getAdmin();
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(TABLE));
            ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(columnDesc.getBytes()).build();
            tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
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
