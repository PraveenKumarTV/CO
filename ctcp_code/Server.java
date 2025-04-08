import java.net.*;
import java.util.*;
import java.io.*;
class Server{
    public static void main(String[] args) {
        try{
            int serverPort=Integer.parseInt(args[0]);
            ServerSocket server=new ServerSocket(serverPort);
            while(true){
                Socket client=server.accept();
                Op th=new Op(client);
                th.start();
            }
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}

class Op extends Thread{
    Socket client;
    Op(Socket client){
        this.client=client;
    }
    public void run(){
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
            int cnt=Integer.parseInt(br.readLine());
            int sum=0;
            for(int i=1;i<=cnt;i++){
                sum+=i;
                Thread.sleep(200);
            }
            PrintWriter out=new PrintWriter(client.getOutputStream());
            out.println(sum);
            out.flush();
            client.close();
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
    }
}