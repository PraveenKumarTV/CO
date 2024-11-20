import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;
class Patient{
    String name,id,addr,disease,gender,dob;int age;long phno;
    Patient(String a,String b,String c,String d,String e,String f,long g,int h){
        name=a;
        id=b;addr=c;disease=d;gender=e;dob=f;phno=g;age=h;
    }

    String getname(){
        return name;
    }
    String getid(){
        return id;
    }
    int getage(){
        return age;
    }
    String getaddr(){
        return addr;
    }
    String getdis(){
        return disease;
    }
    String getgen(){
        return gender;
    }
    String getdob(){
        return dob;
    }
    long getphno(){
        return phno;
    }
    public void describe(){
        System.out.println("Name: "+name+" ID: "+id+" address: "+addr+" Disease: "+disease+" DOB: "+dob+" phno: "+phno);
    }

    public String toString(){
        return "Name: "+name+" ID: "+id+" address: "+addr+" Disease: "+disease+" DOB: "+dob+" phno: "+phno;
    }
}
public class exp8a {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Patient ob1=new Patient("cdetyu","123","madurai","fever","male","13/11/24",1234567890,25);
        LogManager lm=LogManager.getLogManager();
        Logger lg=Logger.getLogger("example3");
        Stack<Integer> st=new Stack<>();
        st.push(10);

        
        try{
        FileHandler fh=new FileHandler("example3.txt",true);
        lm.addLogger(lg);
        lg.addHandler(fh);
        fh.setFormatter(new SimpleFormatter());
        lg.log(Level.INFO, ob1.toString());
        fh.close();
    }
    catch(Exception e){
        System.out.println("Exception: "+e);
    }
        /*ArrayList<Patient> list=new ArrayList<Patient>();
        list.add(ob1);
        list.add(ob1);
        list.add(ob1);
        System.out.println(list);

        Iterator<Patient> it=list.iterator();
        System.out.print("enter gender: ");
        String a=sc.next();
        while(it.hasNext()){
            Patient p=it.next();
            if(p.getgen().equals(a)){
                p.describe();
            }
        }
        it=list.iterator();
        System.out.print("enter age: ");
        int b=sc.nextInt();
        while(it.hasNext()){
            Patient p=it.next();
            if(p.getage()>b){
                p.describe();
            }
        }

        it=list.iterator();
        System.out.print("enter id: ");
        a=sc.next();
        while(it.hasNext()){
            Patient p=it.next();
            if(p.getid().equals(a)){
                p.describe();
            }
        }*/

    }
    
}