import analyzers.*;
import cleaner.DataCombiner;
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
import org.json.simple.parser.ParseException;


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

        Job job = Job.getInstance(conf, "Data Cleaner");

        Job job1 = setAnalyzerJobs(conf,"Speed Average By Type",new Path(args[1]),new Path(args[2]));
        Job job2 = setAnalyzerJobs(conf,"Type vehicule by direction and date",new Path(args[1]),new Path(args[3]));
        Job job3 = setAnalyzerJobs(conf,"Trafic By Date by Direction By type",new Path(args[1]),new Path(args[4]));
        Job job4 = setAnalyzerJobs(conf,"Number of vehicule in and out by date",new Path(args[1]),new Path(args[5]));
        Job job5 = setAnalyzerJobs(conf,"Number of observations by date",new Path(args[1]),new Path(args[6]));

        job.setJarByClass(App.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
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


                job.setMapperClass(DataMapper.class);
                job.setCombinerClass(DataCombiner.class);
                job.setReducerClass(DataReducer.class);

                job1.setMapperClass(SpeedAverageByType.SpeedAverageByTypeMapper.class);
                job1.setCombinerClass(SpeedAverageByType.SpeedAverageByTypeCombiner.class);
                job1.setReducerClass(SpeedAverageByType.SpeedAverageByTypeReducer.class);

                job2.setMapperClass(TypeVehiculeByDateByDirection.TypeVehiculeByDateByDirectionMapper.class);
                job2.setCombinerClass(TypeVehiculeByDateByDirection.TypeVehiculeByDateByDirectionCombiner.class);
                job2.setReducerClass(TypeVehiculeByDateByDirection.TypeVehiculeByDateByDirectionReducer.class);

                job3.setMapperClass(AverageVehiculeByHourType.AverageVehiculeByHourTypeMapper.class);
                job3.setCombinerClass(AverageVehiculeByHourType.AverageVehiculeByHourTypeCombiner.class);
                job3.setReducerClass(AverageVehiculeByHourType.AverageVehiculeByHourTypeReducer.class);

                job4.setMapperClass(InOutByDay.InOutByDayMapper.class);
                job4.setCombinerClass(InOutByDay.InOutByDayCombiner.class);
                job4.setReducerClass(InOutByDay.InOutByDayReducer.class);

                job5.setMapperClass(ObservationbyPost.ObservationbyPostMapper.class);
                job5.setCombinerClass(ObservationbyPost.ObservationbyPostCombiner.class);
                job5.setReducerClass(ObservationbyPost.ObservationbyPostReducer.class);


                job.waitForCompletion(true);
                job1.waitForCompletion(true);
                job2.waitForCompletion(true);
                job3.waitForCompletion(true);
                job4.waitForCompletion(true);
                job5.waitForCompletion(true);

        WriteToHBase writeToHBase = new WriteToHBase();
        Configuration config =  HBaseConfiguration.create();

        Job jobSpeedAverage = Job.getInstance(config, "Stock speed Average");
        Job jobTypeByDirection = Job.getInstance(config, "type vehicule by date and direction");
        Job jobAvgVByhour = Job.getInstance(config, "Average vehicule by hour and direction");
        Job jobInOut = Job.getInstance(config, "Number of in and out");
        Job jobObservation = Job.getInstance(config, "Number of Observations by post");

        jobSpeedAverage.setJarByClass(WriteToHBase.class);
        jobTypeByDirection.setJarByClass(WriteToHBase.class);
        jobAvgVByhour.setJarByClass(WriteToHBase.class);
        jobInOut.setJarByClass(WriteToHBase.class);
        jobObservation.setJarByClass(WriteToHBase.class);

        Connection connection = ConnectionFactory.createConnection(config);

        writeToHBase.createTable(connection,writeToHBase.getTABLE_SPEED(),"Speed");
        writeToHBase.createTable(connection,writeToHBase.getTABLE_TYPE(),"Data");
        writeToHBase.createTable(connection,writeToHBase.getTABLE_AVGVLBYHOUR(),"Data");
        writeToHBase.createTable(connection,writeToHBase.getTABLE_INOUT(),"Data");
        writeToHBase.createTable(connection,writeToHBase.getTABLE_OBSERVATIONS(),"Data");


        FileInputFormat.addInputPath(jobSpeedAverage, new Path(args[2]));
        FileInputFormat.addInputPath(jobTypeByDirection, new Path(args[3]));
        FileInputFormat.addInputPath(jobAvgVByhour, new Path(args[4]));
        FileInputFormat.addInputPath(jobInOut, new Path(args[5]));
        FileInputFormat.addInputPath(jobObservation, new Path(args[6]));


        jobSpeedAverage.setInputFormatClass(SequenceFileInputFormat.class);
        jobSpeedAverage.setMapOutputKeyClass(Text.class);
        jobSpeedAverage.setMapOutputValueClass(FloatWritable.class);

        jobTypeByDirection.setInputFormatClass(SequenceFileInputFormat.class);
        jobTypeByDirection.setMapOutputKeyClass(Text.class);
        jobTypeByDirection.setMapOutputValueClass(Text.class);

        jobAvgVByhour.setInputFormatClass(SequenceFileInputFormat.class);
        jobAvgVByhour.setMapOutputKeyClass(Text.class);
        jobAvgVByhour.setMapOutputValueClass(Text.class);

        jobInOut.setInputFormatClass(SequenceFileInputFormat.class);
        jobInOut.setMapOutputKeyClass(Text.class);
        jobInOut.setMapOutputValueClass(Text.class);

        jobObservation.setInputFormatClass(SequenceFileInputFormat.class);
        jobObservation.setMapOutputKeyClass(Text.class);
        jobObservation.setMapOutputValueClass(Text.class);

        TableMapReduceUtil.initTableReducerJob(writeToHBase.getTABLE_SPEED(), WriteToHBase.SpeedAverageReducer.class, jobSpeedAverage);
        TableMapReduceUtil.initTableReducerJob(writeToHBase.getTABLE_TYPE(), WriteToHBase.TypeVehiculeByDateByDirectionReducer.class, jobTypeByDirection);
        TableMapReduceUtil.initTableReducerJob(writeToHBase.getTABLE_AVGVLBYHOUR(), WriteToHBase.AverageVehiculeByHourAndTypeReducer.class, jobAvgVByhour);
        TableMapReduceUtil.initTableReducerJob(writeToHBase.getTABLE_INOUT(), WriteToHBase.InOutByDateReducer.class, jobInOut);
        TableMapReduceUtil.initTableReducerJob(writeToHBase.getTABLE_OBSERVATIONS(), WriteToHBase.ObservationByPostReducer.class, jobObservation);

        jobSpeedAverage.waitForCompletion(true);
        jobTypeByDirection.waitForCompletion(true);
        jobAvgVByhour.waitForCompletion(true);
        jobInOut.waitForCompletion(true);
        jobObservation.waitForCompletion(true);

    }

    public static Job setAnalyzerJobs(Configuration config, String name, Path input, Path output) throws IOException {
        Job job = Job.getInstance(config,name);
        job.setJarByClass(App.class);
        job.setOutputKeyClass(Text.class);
        if (name.contains("Speed Average"))
        {job.setOutputValueClass(FloatWritable.class);}
        else{ job.setOutputValueClass(Text.class);}
        job.setInputFormatClass(SequenceFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        FileInputFormat.addInputPath(job, input);
        FileOutputFormat.setOutputPath(job, output);
        return job;
    }
}

