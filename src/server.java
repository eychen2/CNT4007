import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.Scanner;

public class server {
    static boolean isInt(String check)
    {
        try{
            int test = Integer.parseInt(check);
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
        while(true)
        {
            Socket client = server.accept();
            BufferedReader read = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream write = new DataOutputStream(client.getOutputStream());
            String inputLine="";
            System.out.println("reached");
            while((inputLine = read.readLine())!=null)
            {
                System.out.println("reached");
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
                System.out.println(op);
                System.out.println(op.length());
                if(op.equals("add"))
                {
                    System.out.println("reached");
                    if(cheese.length<3)
                    {
                        write.writeBytes(Integer.toString(-2) + '\n');
                    }
                    else if(cheese.length>5)
                    {
                        write.writeBytes(Integer.toString(-3) + '\n');
                    }
                    else
                    {
                        write.writeBytes("Valid operation"+'\n');
                    }
                }
                else if(op.equals("subtract"))
                {
                    if(cheese.length<3)
                    {
                        write.writeBytes(Integer.toString(-2)+'\n');
                    }
                    else if(cheese.length>5)
                    {
                        write.writeBytes(Integer.toString(-3) + '\n');
                    }
                    else
                    {
                        write.writeBytes("Valid operation"+'\n');
                    }
                }
                else if (op.equals("multiply"))
                {
                    if(cheese.length<3)
                    {
                        write.writeBytes(Integer.toString(-2)+'\n');
                    }
                    else if(cheese.length>5)
                    {
                        write.writeBytes(Integer.toString(-3) + '\n');
                    }
                    else
                    {
                        write.writeBytes("Valid operation"+'\n');
                    }
                }
                else
                {
                    System.out.println("Not valid operation");
                    write.writeBytes(Integer.toString(-1)+'\n');
                }
            }
            break;
        }
        server.close();
    }
}
