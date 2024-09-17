import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
class motorbill{
   private Map<String,Integer> ser=new HashMap<>();
    private Map<String,Integer> sal=new HashMap<>();int a;
    Date date=new Date();
    
    void sercalculate(service s){
     
    ser.put("waterwash",1000);
    ser.put("engine repair",2000);
    ser.put("general service",3000);
    System.out.println("===============");
        System.out.println("Bill Details:");
        System.out.println("Name: "+s.name);
        System.out.println("Contact no: "+s.phno);
        System.out.println("Address: "+s.addr);
        System.out.println("Date: "+date.toString());
        
        boolean f=true;
        for(String i: ser.keySet()){
            if(i.equals(s.task)){
                a=ser.get(i);
                System.out.println(i+" = "+ser.get(i));
            f=false;
            }
    }
        
    if(f){
        System.out.println("invalid service");
        return;
    }
    
        
    System.out.println("GST(18%) = "+(a*0.01*18));
        System.out.println("Discount(10%) = "+(a*0.01*10));
        System.out.println("Additional charges: "+150);
        System.out.println("Total amount = "+(a+a*0.01*18-a*0.01*10+150));
        System.out.println("===============");
    }

    void salcalculate(sales s){
        sal.put("model 1",50000);
        sal.put("model 2",60000);
        sal.put("model 3",70000);

        System.out.println("===============");
        System.out.println("Bill Details:");
        System.out.println("Name: "+s.name);
        System.out.println("Contact no: "+s.phno);
        System.out.println("Address: "+s.addr);
        System.out.println("Date: "+date.toString());
        System.out.println("model name: "+s.task);
        System.out.println("Selected color: "+s.color);
        boolean f=true;
        for(String i:sal.keySet()){
            if(i.equals(s.task)){
                f=false;
                a=sal.get(i);
                System.out.println(i+" = "+sal.get(i));

            }
        }

        if(f){
        System.out.println("invalid option");
        return;
    }

        System.out.println("GST(18%) = "+(a*0.01*18));
        System.out.println("Discount(10%) = "+(a*0.01*10));
        System.out.println("Additional charges: "+150);
        System.out.println("Total amount = "+(a+a*0.01*18-a*0.01*10+150));
        System.out.println("===============");


    }


    
}

class service extends motorbill{
    String name;
    int phno;String addr;
    
    String task;

    Scanner sc=new Scanner(System.in);
     
    service(String name,int phno,String addr){
        this.name=name;
        this.phno=phno;
        this.addr=addr;
    }

    void disp(){
        System.out.println("waterwash");
        System.out.println("engine repair");
        System.out.println("general service");
        System.out.println("select from the options:");
        task=sc.nextLine();
    }
    


}

class sales extends motorbill{
    String name;
    String task;String color;int phno;String addr;
    Scanner sc=new Scanner(System.in);

    sales(String name, int phno, String addr){
        this.name=name;
        this.phno=phno;
        this.addr=addr;
    }

    void disp(){
        System.out.println("model 1");
        System.out.println("model 2");
        System.out.println("model 3");
        System.out.println("select from the options:");
        task=sc.nextLine();
        System.out.println("Select color: Red, Black, White");
        color=sc.nextLine();

    }

}

class exp3{
    public static void main(String args[]){
        motorbill ob=new motorbill();
        service ob1=new service("praveen",12345,"madurai");
        sales ob2=new sales("praveen",12345,"madurai");
        ob1.disp();
        ob.sercalculate(ob1);
        ob2.disp();
        ob.salcalculate(ob2);
        
        }
}