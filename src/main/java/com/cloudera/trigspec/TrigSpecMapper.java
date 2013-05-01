package com.cloudera.trigspec;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TrigSpecMapper extends Mapper<LongWritable, Text, Text, Text> {
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws InterruptedException, IOException {
    int sleepTime = Integer.parseInt(value.toString());
    System.out.println("About to sleep for " + sleepTime + " seconds");
    Thread.sleep(sleepTime);
    context.write(new Text("donekey"), new Text("doneval"));
  }
}
