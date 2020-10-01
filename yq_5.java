package Tests;

import java.io.*;
import java.util.Scanner;


public class yq_5 {
    public static class objectdata//对象数组用来存读取的数据
    {
        // 成员变量
        public String provincename;
        public String cityname;
        public int citydata;
        public int sumdata;

        // 构造方法
        public objectdata()
        {
            super();
        }

        public objectdata(String provincename, String cityname,int citydata)
        {
            super();
            this.provincename = provincename;
            this.cityname = cityname;
            this.citydata = citydata;
        }

        // 成员方法
        // getXxx()/setXxx()
        public String getName()
        {
            return provincename;
        }

        public void setName(String name)
        {
            this.provincename = name;
        }
        public String getcityName()
        {
            return cityname;
        }

        public void setcityName(String name)
        {
            this.cityname = name;
        }

        public int getdata()
        {
            return citydata;
        }

        public void setAge(int data)
        {
            this.citydata = data;
        }

        @Override
        public String toString()
        {
            return " [proname=" + provincename + ", cname=" + cityname + "cdata"+citydata+"]";
        }
    }
    public static class pro_sumdata//用来绑定省和总人数
    {
        public String name_province;
        public int Sum_data;
        public pro_sumdata(String provincename,int prodata)
        {

        }
    }
    public static void InputMessage(String msg[]){
        System.out.println("请输入：输入文件名 输出文件名 指定的省份（不指定省份时输出全部）");
        Scanner cin= new Scanner(System.in);
        String message=cin.nextLine();
        String[] mess=message.split(" ");

        for (int i = 0; i < mess.length; i++) {
            msg[i] = mess[i];
        }
    }//输入函数

    public static void ReadFile(String in_name,objectdata[] object)  {
        //读文件
        try {
            String in_name_1;
            in_name_1=in_name;
            File filename = new File("F:\\softwareHomework\\"  + in_name_1);
            String pathname = filename.toString();
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename), "GBK"); // 此处用了GBK用来解决我之前输出乱码。
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            String line = "";//一行数据
            int i=0;
            String province="";//省份
            String shiqu="";//市
            String data="";//数据
            int intdata;
            while ((line=br.readLine())!= null) {//一次读入一行数据
                String[] informations=line.split("\t");//分割数据
                province=informations[0];
                shiqu=informations[1];
                data=informations[2];
                intdata=Integer.parseInt(data);
                object[i]=new objectdata(province,shiqu,intdata);
                i++;
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }



    }//读文件函数

    public static void SortProvince(objectdata[] shujuS,pro_sumdata[] shengdata)//按省排序
    {
        String []all_province = {"浙江省","江西省","广东省","江苏省","湖南省","安徽省","陕西省","河南省","贵州省"};
        //计算每个省的总数
        int []provinceSumdata = new int[9];
        for (int k = 0; k < shujuS.length-1; k++) {
            for (int j = 0; j < 9; j++) {
                if (shujuS[k].getName()!=null)
                    if(shujuS[k].getName().equals(all_province[j])){
                        provinceSumdata[j] += shujuS[k].citydata;
                    }
            }
        }

        for (int k = 0; k < 9; k++) {
            String pN=all_province[k];
            int pD=provinceSumdata[k];
            shengdata[k]=new pro_sumdata(pN,pD);
            shengdata[k].name_province=pN;
            shengdata[k].Sum_data=pD;
        }

        //给省总数排序
        for(int g=0;g<8;g++)
        {
            for(int j=0;j<9;j++)
            {
                if(shengdata[j].Sum_data<shengdata[j+1].Sum_data)
                {
                    shengdata[9]=shengdata[j];
                    shengdata[j]=shengdata[j+1];
                    shengdata[j+1]=shengdata[9];
                }
            }
        }
    }
    public static void SortCity(objectdata[] shujuS){
        objectdata temp;
        for (int j = 0 ;j<shujuS.length;j++) {//全部按感染排序
            while (shujuS[j] != null) {
                for (int k = 0 ;k<shujuS.length;k++)
                    while (shujuS[k] != null) {
                        if (shujuS[j].citydata > shujuS[k].citydata) {
                            temp = shujuS[k];
                            shujuS[k] =shujuS[j];
                            shujuS[j]= temp;
                        }
                        break;
                    }
                break;
            }
        }
    }//按城市排序

    public static void WirteFlie(String out_name,String order_name,pro_sumdata[] shengdata,objectdata[] shujuS)//写入文件
    {
        try {
            //通过out写
            File writename = new File("F:\\softwareHomework\\"  + out_name);
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            for (int p = 0; p < 9; p++) {
                if(order_name==null) {
                    out.write(  shengdata[p].name_province + " " + shengdata[p].Sum_data + "\n");
                    System.out.print( shengdata[p].name_province + " " + shengdata[p].Sum_data + "\n");
                    for (int k = 0; k < shujuS.length - 1; k++) {

                        if (shengdata[p].name_province.equals(shujuS[k].getName()) && !shujuS[k].getcityName().equals("待明确地区")) {


                            out.write(shujuS[k].getcityName() + "\t" + shujuS[k].getdata() + "\n");
                            System.out.print(shujuS[k].getcityName() + "\t" + shujuS[k].getdata() + "\n");
                        }
                    }
                }
                else if (shengdata[p].name_province.equals(order_name))
                {
                    out.write("\n" + shengdata[p].name_province + " " + shengdata[p].Sum_data + "\n");
                    System.out.print("\n" + shengdata[p].name_province + " " + shengdata[p].Sum_data + "\n");
                    for (int k = 0; k < shujuS.length - 1; k++) {

                        if (shengdata[p].name_province.equals(shujuS[k].getName()) && !shujuS[k].getcityName().equals("待明确地区")) {
                            out.write(shujuS[k].getcityName() + "\t" + shujuS[k].getdata() + "\n");
                            System.out.print(shujuS[k].getcityName() + "\t" + shujuS[k].getdata() + "\n");
                        }
                    }
                }
            }
            out.flush();
            out.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public static void  main(String[] args) {
        // TODO Auto-generated method stub
        //yq_in.txt yq_out_5.txt
        //yq_in.txt yq_out_5.txt 湖南省
            objectdata [] shujuS=new objectdata[129];//使用对象数组来存每一行的数据
            pro_sumdata [] shengdata=new pro_sumdata[10];//使用对象数组来绑定省份和总数
            String messsage[] = new String[3];//用来存储输入信息
            InputMessage(messsage);//分割信息，存储输入文件名，输出文件名，省份名称
            String in_name=messsage[0];
            String out_name=messsage[1];
            String order_name=messsage[2];

            ReadFile(in_name,shujuS);//读取文件，存入对象数组种
            SortProvince(shujuS,shengdata);//按省份排序
            SortCity(shujuS);//按城市排序
            WirteFlie(out_name,order_name,shengdata,shujuS);//写入文件

    }


}
