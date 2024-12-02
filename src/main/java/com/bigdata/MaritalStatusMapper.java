package com.bigdata;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaritalStatusMapper extends Mapper<Object, Text, Text, NumPair> {
    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",\\s*");
        if (fields.length > 12) {
            String maritalStatus = fields[5].trim();
            int hoursPerWeek = Integer.parseInt(fields[12].trim());
            context.write(new Text(maritalStatus), new NumPair(hoursPerWeek, 1));
        }
    }
}