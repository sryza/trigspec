package com.cloudera.trigspec;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class TrigSpecDriver extends Configured implements Tool {
  public static void main(String[] args) throws Exception {
    ToolRunner.run(new TrigSpecDriver(), args);
  }
  
  @Override
  public int run(String[] args) throws Exception {
    Job job = new Job(getConf());
    job.setJarByClass(TrigSpecDriver.class);
    job.setMapperClass(TrigSpecMapper.class);
    job.setNumReduceTasks(0);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);
    
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path("" + System.currentTimeMillis()));

    job.waitForCompletion(true);
    return 0;
  }
}
