package xin.mrbear.hdfs;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

public class TestHDFSClients {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        //这里指定使用的是 hdfs 文件系统
        conf.set("fs.defaultFS", "hdfs://bigdata01:9000");
        //通过如下的方式进行客户端身份的设置
        System.setProperty("HADOOP_USER_NAME", "root");
        //通过 FileSystem 的静态方法获取文件系统客户端对象
        FileSystem fs = FileSystem.get(conf);//列出 hdfs 根目录下的所有文件信息
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), false);
        //遍历出我们得到的指定文件路径的迭代器 获取相应的文件信息
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            String name = fileStatus.getPath().getName();
            System.out.println(name);
        }
        //创建一个文件夹
        //fs.create(new Path("/helloByJava"));

        //下载
        fs.copyToLocalFile(new Path("/hello.sh"),new Path("e:/abc/aaa.sss"));

        //关闭我们的文件系统
        fs.close();



    }

}
