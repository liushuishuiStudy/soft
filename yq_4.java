package Tests;

import java.io.*;
import java.util.Scanner;


public class yq_4 {
    public static class objectdata
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
    public static class pro_sumdata
    {
        public String name_province;
        public int Sum_data;
        public pro_sumdata(String provincename,int prodata)
        {

        }
    }

    public static void  main(String[] args) {
        // TODO Auto-generated method stub
        try {
            //yq yq_in.txt out_4.txt
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
            String pathname=filename.toString();
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename),"GBK"); // 此处用了GBK用来解决我之前输出乱码。
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            //获得文件的行数
            int Sumline=getFileLineNum(pathname);
            Sumline=Sumline+1;
            String line = "";//一行数据
            String province="";//省份
            String previous="";//前一个省份
            String shiqu="";//市
            String data="";//数据
            String [][] citydata=new String[9][20];

            int intdata;

            objectdata [] shujuS=new objectdata[129];
            pro_sumdata [] shengdata=new pro_sumdata[10];

            int i=0;
            int flag=0;
            String []all_province = {"浙江省","江西省","广东省","江苏省","湖南省","安徽省","陕西省","河南省","贵州省"};


            while ((line=br.readLine())!= null) {//一次读入一行数据
                String[] informations=line.split("\t");//分割数据
                province=informations[0];
                shiqu=informations[1];
                data=informations[2];
                intdata=Integer.parseInt(data);
                shujuS[i]=new objectdata(province,shiqu,intdata);
                i++;
            }


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
            //for (int )


//yq yq_in.txt out_4.txt
            for (int p=0;p<9;p++) {
                out.write("\n"+shengdata[p].name_province + " " + shengdata[p].Sum_data + "\n");
                System.out.print("\n"+shengdata[p].name_province + " " + shengdata[p].Sum_data + "\n");
                for (int k = 0; k < shujuS.length - 1; k++) {

                        if (shengdata[p].name_province.equals(shujuS[k].getName()) && !shujuS[k].getcityName().equals("待明确地区")) {


                            out.write(shujuS[k].getcityName() + "\t" + shujuS[k].getdata() + "\n");
                            System.out.print(shujuS[k].getcityName() + "\t" + shujuS[k].getdata() + "\n");
                        }
                    }

                }



            out.flush();
            out.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int getFileLineNum(String filePath) {// 获取整个文件的行数
        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath))) {
            lineNumberReader.skip(Long.MAX_VALUE);
            int lineNumber = lineNumberReader.getLineNumber();
            return lineNumber + 1;// 实际上是读取换行符数量 , 所以需要+1
        } catch (IOException e) {
            return -1;
        }
    }
}