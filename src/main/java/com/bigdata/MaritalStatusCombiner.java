package com.bigdata;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class MaritalStatusCombiner extends Reducer<Text, NumPair, Text, NumPair> {
    @Override
    public void reduce(Text key, Iterable<NumPair> values, Context context) throws IOException, InterruptedException {
        int totalHours = 0;
        int count = 0;
        for (NumPair value : values) {
            totalHours += value.getSum();
            count += value.getCount();
        }
        context.write(key, new NumPair(totalHours, count));
    }
}
