package Tests;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;


public class yq_3 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {

            System.out.println("请输入：文件名 输入文件名 输出文件名 指定的省份（不指定省份时输出全部）");
            Scanner cin= new Scanner(System.in);
            String message=cin.nextLine();
            String floder_name,in_name,out_name,order_name="";
            String[] mess=message.split(" ");

            floder_name=mess[0];
            in_name=mess[1];
            out_name=mess[2];
            if(mess.length==4){
                order_name=mess[3];
            }

            //通过out写文件
            File writename = new File("F:\\softwareHomework\\"+floder_name+"\\"+out_name);
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));


            //读文件
            File filename = new File("F:\\softwareHomework\\"+floder_name+"\\"+in_name);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename),"GBK"); // 此处用了GBK用来解决我之前输出乱码。
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";//一行数据
            String province="";//省份
            String previous ="";//前一个省份
            String data="";//数据
            int i=0;
            while ((line= br.readLine())!= null) {// 一次读入一行数据
                province = line;
                if (province==null)break;
                province = province.substring(0, 3);
                data=line.substring(4);
                if(order_name=="")
                {
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
                else if(province.equals(order_name))
                {
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
            }


            out.flush();
            out.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



