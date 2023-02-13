import java.io.*;
import java.net.*;
import java.util.Scanner;
// Found this class when looking how to represent numbers that would normally overflow longs from
// java oracle site: https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html
import java.math.BigInteger;

public class server {
    static boolean isInt(String check)
    {
        try{
            BigInteger test = new BigInteger(check);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    public static void main(String[] args) throws Exception{
//        if(args.length<1)
//        {
//            System.out.println("Port number not given");
//            System.exit(0);
//        }
//        int port=Integer.parseInt(args[0]);
        Scanner scan = new Scanner(System.in);
        int port = Integer.parseInt(scan.nextLine());
        ServerSocket server= new ServerSocket(port);
        int answer=0;
        boolean checkInt=true;
        outloop: while(true)
        {
            Socket client = server.accept();
            BufferedReader read = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream write = new DataOutputStream(client.getOutputStream());
            String inputLine="";
            System.out.println("reached");
            while((inputLine = read.readLine())!=null)
            {
                answer=0;
                checkInt=true;
                String[] cheese = inputLine.split(" ");
//                for(String word: cheese)
//                {
//                    if(isInt(word)==true)
//                    {
//                        System.out.println(word+" is an integer");
//                    }
//                    else
//                    {
//                        System.out.println(word+" isn't an integer");
//                    }
//                }
                String op = cheese[0];

                if(op.equals("add"))
                {
                    System.out.println("reached");
                    if(cheese.length<3)
                    {
                        write.writeBytes("-2"+'\n');
                    }
                    else if(cheese.length>5)
                    {
                        write.writeBytes("-3" + '\n');
                    }
                    else
                    {
                        for(int i=1; i<cheese.length; ++i)
                        {
                            if(!isInt(cheese[i]))
                            {
                                checkInt=false;
                            }
                        }
                        if(!checkInt)
                        {
                            write.writeBytes("-4" +"\n");
                        }
                        else
                        {
                            BigInteger sum = new BigInteger(cheese[1]);
                            for(int i=2; i<cheese.length;++i)
                            {
                                sum = sum.add(new BigInteger(cheese[i]));
                            }

                            write.writeBytes(sum.toString()+'\n');
                        }
                    }
                }
                else if(op.equals("subtract"))
                {
                    if(cheese.length<3)
                    {
                        write.writeBytes("-2"+'\n');
                    }
                    else if(cheese.length>5)
                    {
                        write.writeBytes("-3" + '\n');
                    }
                    else
                    {
                        for(int i=1; i<cheese.length; ++i)
                        {
                            if(!isInt(cheese[i]))
                            {
                                checkInt=false;
                            }
                        }
                        if(!checkInt)
                        {
                            write.writeBytes("-4" +"\n");
                        }
                        else
                        {
                            for(int i=1; i<cheese.length;++i)
                            {

                            }
                            write.writeBytes("Valid operation"+'\n');
                        }
                    }
                }
                else if (op.equals("multiply"))
                {
                    if(cheese.length<3)
                    {
                        write.writeBytes("-2"+'\n');
                    }
                    else if(cheese.length>5)
                    {
                        write.writeBytes("-3" + '\n');
                    }
                    else
                    {
                        for(int i=1; i<cheese.length; ++i)
                        {
                            if(!isInt(cheese[i]))
                            {
                                checkInt=false;
                            }
                        }
                        if(!checkInt)
                        {
                            write.writeBytes("-4" +"\n");
                        }
                        else
                        {
                            BigInteger sum = new BigInteger(cheese[1]);
                            for(int i=2; i<cheese.length;++i)
                            {
                                sum = sum.multiply(new BigInteger(cheese[i]));
                            }
                            write.writeBytes(sum.toString()+'\n');
                        }
                    }
                }
                else if(op.equals("terminate")||op.equals("bye"))
                {
                    write.writeBytes(Integer.toString(-5)+'\n');
                    if(op.equals("terminate"))
                        break outloop;
                }
                else
                {
                    System.out.println("Not valid operation");
                    write.writeBytes("-1"+'\n');
                }
            }
        }
        server.close();
    }
}
