package Tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.InputStreamReader;


public class yq {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            //通过out写文件
            File writename = new File("F:\\softwareHomework\\out.txt");
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));


            //读文件
            String pathname = "F:\\softwareHomework\\yq_in.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename),"GBK"); // 此处用了GBK是因为，解决我之前输出乱码
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";//一行数据
            String province="";//省份
            String previous ="";//前一个省份
            String data="";//数据
            int i=0;
            line= br.readLine();
            while (line!= null) {
                line = br.readLine(); // 一次读入一行数据
                province = line;
                if (province==null)break;
                province = province.substring(0, 3);
                data=line.substring(4);
                if (!(province.equals(previous))) {
                    if(i==0)
                    {
                        i=1;
                    }
                    else{
                        out.write("\n");
                        System.out.print("\n");
                    }
                    out.write(province + "\n");
                    System.out.print(province + "\n");
                    previous=province;
                }
                if(!data.equals("待明确地区\t0")) {
                    out.write(data + "\n");
                    System.out.print(data + "\n");
                }
            }


            out.flush();
            out.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}