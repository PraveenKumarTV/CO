import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class oops7a {
    public static void main(String args[]) {
        String path = "example.txt";
        String[] sno = new String[10];
        String[] brand = new String[10];
        String[] prsr = new String[10];
        String[] ram = new String[10];
        String[] stg = new String[10];
        String[] size = new String[10];
        String[] cost = new String[10];
        String[] yom = new String[10];
        String[] war = new String[10];
        Scanner sc=new Scanner(System.in);
        int j = 0;  // Initialize 'j' before reading lines

        try {
            BufferedReader fp = new BufferedReader(new FileReader(path));
            String line;
            
            // Skip the header line
            fp.readLine();  

            // Read each line from the file
            while ((line = fp.readLine()) != null) {
                // Split the line into individual data parts
                String[] data = line.split(",");
                
                // Assign each part to the respective array
                sno[j] = data[0];
                brand[j] = data[1];
                prsr[j] = data[2];
                ram[j] = data[3];
                stg[j] = data[4];
                size[j] = data[5];
                cost[j] = data[6];
                yom[j] = data[7];
                war[j] = data[8];

                j++;  // Increment 'j' after each line is processed
            }

            fp.close();  // Don't forget to close the BufferedReader!

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Print all the brands to check the values
        System.out.print("enter sno: ");
        String s=sc.next();
        int a=1;
        for (String ch : sno) {
            if(s.equals(ch)){
                System.out.println("System found");
                a=0;break;
            }
        }
        if(a==1){
            System.out.println("System not found");
        }

        System.out.print("enter brand name: ");
        s=sc.next();
        a=1;
        for(int i=0;i<10;i++){
            String name=brand[i];
            if(s.equals(name)){
                System.out.println("sno: "+sno[i]);
                System.out.println("brand: "+brand[i]);
                System.out.println("processor: "+prsr[i]);
                System.out.println("RAM: "+ram[i]);
                System.out.println("storage: "+stg[i]);
                System.out.println("monitor size: "+size[i]);
                System.out.println("cost: "+cost[i]);
                System.out.println("year of manufacture: "+yom[i]);
                System.out.println("Warranty(in years): "+war[i]);
                a=0;
            }
        }
        if(a==1){
            System.out.println("brand not found");
        }
        System.out.print("enter cost: ");
        s=sc.next();
        int price=Integer.parseInt(s);
        System.out.println("Processor and memory details:");
        for(int i=0;i<10;i++){
            int b=Integer.parseInt(cost[i]);
            if(b>price){
            System.out.println("Processor: "+prsr[i]+" Ram: "+ram[i]+" Storage: "+stg[i]);
            }
        }


    }
}
