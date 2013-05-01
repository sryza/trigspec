package com.cloudera.trigspec;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class TrigSpecGen {
  private static final int RECORDS_PER_FILE = 100;
  private static final int NORMAL_TASK_TIME = 2 * 60 * 1000;
  private static final int STRAGGLER_TASK_TIME = 3 * 60 * 1000;
  
  public static void main(String[] args) throws IOException {
    Path inputDir = new Path(args[0]);
    int numInputs = Integer.parseInt(args[1]);
    // means average task time should be (3 + 2 * 4) / 5 = 2.2 minutes
    int numStragglers = Math.max(1, numInputs / 5);
    Configuration conf = new Configuration();
    
    for (int i = 0; i < numInputs; i++) {
      FileSystem fs = FileSystem.get(inputDir.toUri(), conf);
      Path path = new Path(inputDir, "input_" + i);
      FSDataOutputStream os = fs.create(path);
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
      int timePerRecord = ((i < numStragglers) ? STRAGGLER_TASK_TIME :
        NORMAL_TASK_TIME) / RECORDS_PER_FILE;
      for (int j = 0; j < RECORDS_PER_FILE; j++) {
        bw.write(timePerRecord + "\n");
      }
      bw.close();
    }
  }
}
