package com.bigdata;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MaritalStatusReducer extends Reducer<Text, NumPair, Text, DoubleWritable> {
    @Override
    public void reduce(Text key, Iterable<NumPair> values, Context context) throws IOException, InterruptedException {
        int totalHours = 0;
        int count = 0;
        for (NumPair value : values) {
            totalHours += value.getSum();
            count += value.getCount();
        }
        double average = (double) totalHours / count;
        context.write(key, new DoubleWritable(average));
    }
}
