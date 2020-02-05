package com.bd1904;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @ClassName : DjlMapper
 * @Description : map端清洗的类
 * @Author : 宿立涛
 * @Date: 2020-01-08 14:06
 */
public class DjlMapper  extends Mapper<LongWritable,Text,Text,NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //火狐-win7-山东省- http://www.daqi.com-http://www.tiantiandy.net/wind/nn_petpknpc_nr.asp-1366*768-2-121.77.108.142-2018 8 19  24:17:22
        String[]  messages = value.toString().split("-");
        if(messages.length == 9){
            //保留
            StringBuffer  stringBuffer = new StringBuffer();
            for(int i = 0;i<messages.length - 1;i++){
                stringBuffer.append(messages[i]).append("\t");
            }
            String  times[] = messages[8].split("  ")[0].split(" ");
            stringBuffer.append(times[0]).append("\t").append(times[1]).append("\t").append(times[2]);
            context.write(new Text(stringBuffer.toString()),NullWritable.get());
        }
    }
}
