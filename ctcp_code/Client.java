import java.net.*;
import java.util.*;
import java.io.*;

class Client{
    public static void main(String[] args) {
        try{
        InetAddress addr=InetAddress.getByName(args[0]);
        int port=Integer.parseInt(args[1]);
        int cnt=Integer.parseInt(args[2]);
        Socket client=new Socket(addr,port);
        PrintStream ps=new PrintStream(client.getOutputStream());
        ps.println(cnt);
        BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
        int sum=Integer.parseInt(br.readLine());
        System.out.println(sum);
        client.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}