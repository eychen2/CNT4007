import java.io.*;
import java.net.*;
import java.util.Scanner;
public class client {
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
//        if(args.length<2)
//        {
//            System.out.println("You didn't give a hostname or port number");
//            System.exit(0);
//        }
//        String host = args[0];
//        int port = Integer.parseInt(args[1]);
        int port = Integer.parseInt(scan.nextLine());
        Socket client = new Socket(InetAddress.getLocalHost().getHostName(),port);
        while(true)
        {
            DataOutputStream write = new DataOutputStream(client.getOutputStream());
            BufferedReader read = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String sentence = scan.nextLine();
            write.writeBytes(sentence+'\n');
            String test=read.readLine();
            System.out.println("From Server: "+test);
            if(test.equals("-5"))
                break;
        }
        client.close();
    }

}
