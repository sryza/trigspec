package com.cloudera.trigspec;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TrigSpecMapper extends Mapper<LongWritable, Text, Text, Text> {
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws InterruptedException, IOException {
    Thread.sleep(Integer.parseInt(value.toString()));
    context.write(new Text("donekey"), new Text("doneval"));
  }
}
