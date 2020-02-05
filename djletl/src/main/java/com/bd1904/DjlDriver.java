package com.bd1904;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @ClassName : DjlDriver
 * @Description : 驱动类
 * @Author : 宿立涛
 * @Date: 2020-01-08 14:12
 */
public class DjlDriver {

    public static void main(String[] args) throws  Exception{


        //job
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration,"test_job1");
        job.setJarByClass(DjlDriver.class);
        //设置reduce  map
        job.setMapperClass(DjlMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        //设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交任务
        boolean result = job.waitForCompletion(true);
        System.out.println("job1运行结束,result："+result);

        System.exit(result?0:-1);
    }
}
