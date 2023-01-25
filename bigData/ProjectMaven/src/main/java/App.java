import analyzers.SpeedAverageByType;
import analyzers.TraficByDateHourTypeAndSensor;
import analyzers.TypeVehiculeByDateByDirection;
import cleaner.DataMapper;
import cleaner.DataReducer;
import hbase.WriteToHBase;
import helpers.JsonCsvReader;
import models.CSVFile;
import models.Folder;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.json.simple.parser.ParseException;


import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import java.io.IOException;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.util.List;

public class App {


    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, ParseException {

                JsonCsvReader jsonCsvReader = new JsonCsvReader();
                List<Folder> folders = jsonCsvReader.readJson();
                Configuration conf = new Configuration();

                /*Job job = Job.getInstance(conf, "Data Clean");
                Job job1 = Job.getInstance(conf,"Speed Average By Type");
                Job job2 = Job.getInstance(conf,"Type vehicule by direction and date");
                Job job3 = Job.getInstance(conf,"Trafic By Date by Direction By type");

                job.setJarByClass(App.class);
                job1.setJarByClass(App.class);
                job2.setJarByClass(App.class);
                job3.setJarByClass(App.class);


                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(Text.class);
                job.setOutputFormatClass(SequenceFileOutputFormat.class);

                job1.setOutputKeyClass(Text.class);
                job1.setOutputValueClass(FloatWritable.class);
                job1.setInputFormatClass(SequenceFileInputFormat.class);
                job1.setOutputFormatClass(SequenceFileOutputFormat.class);

                job2.setOutputKeyClass(Text.class);
                job2.setOutputValueClass(Text.class);
                job2.setInputFormatClass(SequenceFileInputFormat.class);
                job2.setOutputFormatClass(SequenceFileOutputFormat.class);

                job3.setOutputKeyClass(Text.class);
                job3.setOutputValueClass(Text.class);
                job3.setInputFormatClass(SequenceFileInputFormat.class);
                job3.setOutputFormatClass(SequenceFileOutputFormat.class);


                job.getConfiguration().set("posts",jsonCsvReader.jsontoString());

               try {
                    for(Folder folder : folders) {
                        for(CSVFile csvFile : folder.getFiles()) {
                            Path inputPath = new Path(args[0] + folder.getName() + "/" + csvFile.getName() + ".csv");
                            FileInputFormat.addInputPath(job, inputPath);
                        }
                    }
                    FileOutputFormat.setOutputPath(job, new Path(args[1]));
               }
                catch (Exception e) {
                    System.out.println(" bad arguments, waiting for [inputURI] & [outputURI]");
                    return ;
                }


                FileInputFormat.addInputPath(job1, new Path(args[1]));
                FileInputFormat.addInputPath(job2, new Path(args[1]));
                FileInputFormat.addInputPath(job3, new Path(args[1]));

                FileOutputFormat.setOutputPath(job1, new Path(args[2]));
                FileOutputFormat.setOutputPath(job2, new Path(args[3]));
                FileOutputFormat.setOutputPath(job3, new Path(args[4]));


                job.setMapperClass(DataMapper.class);
                job.setReducerClass(DataReducer.class);
                job1.setMapperClass(SpeedAverageByType.SpeedAverageByTypeMapper.class);
                job1.setReducerClass(SpeedAverageByType.SpeedAverageByTypeReducer.class);
                job2.setMapperClass(TypeVehiculeByDateByDirection.TypeVehiculeByDateByDirectionMapper.class);
                job2.setReducerClass(TypeVehiculeByDateByDirection.TypeVehiculeByDateByDirectionReducer.class);
                job3.setMapperClass(TraficByDateHourTypeAndSensor.TraficByDateHourTypeAndSensorMapper.class);
                job3.setReducerClass(TraficByDateHourTypeAndSensor.TraficByDateHourTypeAndSensorReducer.class);


                job.waitForCompletion(true);
                job1.waitForCompletion(true);
                job2.waitForCompletion(true);
                job3.waitForCompletion(true);*/

               WriteToHBase writeToHBase = new WriteToHBase();
               Configuration config =  HBaseConfiguration.create();

               Job jobSpeedAverage = Job.getInstance(config, "Stock speed Average");
               Job jobTypeByDirection = Job.getInstance(config, "type vehicule by date and direction");

               jobSpeedAverage.setJarByClass(WriteToHBase.class);
               jobTypeByDirection.setJarByClass(WriteToHBase.class);

               Connection connection = ConnectionFactory.createConnection(config);

               writeToHBase.createTable(connection,writeToHBase.getTABLE_SPEED());
               writeToHBase.createTable(connection,writeToHBase.getTABLE_TYPE());


               FileInputFormat.addInputPath(jobSpeedAverage, new Path(args[2]));
               FileInputFormat.addInputPath(jobTypeByDirection, new Path(args[3]));

               jobSpeedAverage.setInputFormatClass(SequenceFileInputFormat.class);
               jobSpeedAverage.setMapOutputKeyClass(Text.class);
               jobSpeedAverage.setMapOutputValueClass(FloatWritable.class);

                jobTypeByDirection.setInputFormatClass(SequenceFileInputFormat.class);
                jobTypeByDirection.setMapOutputKeyClass(Text.class);
                jobTypeByDirection.setMapOutputValueClass(Text.class);

               TableMapReduceUtil.initTableReducerJob(writeToHBase.getTABLE_SPEED(), WriteToHBase.speedAverageReducer.class, jobSpeedAverage);
                TableMapReduceUtil.initTableReducerJob(writeToHBase.getTABLE_TYPE(), WriteToHBase.typeByDateByDirectionReducer.class, jobTypeByDirection);

                jobSpeedAverage.waitForCompletion(true);
                jobTypeByDirection.waitForCompletion(true);

    }
}

