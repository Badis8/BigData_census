package com.bigdata;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
public   class EducationMapper extends Mapper<Object, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",\\s*");
        if (fields.length > 4) {
            String education = fields[3]; // The 4th field is the education level
            if (education.equals("Bachelors") || education.equals("Masters") || education.equals("Doctorate")) {
                word.set("Educated");
            } else {
                word.set("NotEducated");
            }
            context.write(word, one);
        }
    }
}
