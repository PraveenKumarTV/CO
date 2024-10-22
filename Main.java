import pack1.Ug;
import pack1.Pg;
import pack1.Student;
import java.util.*;

class Main{
    public static void main(String s[]){
        Scanner sc=new Scanner(System.in);
        Ug ob=new Ug("Praveen","IT111",2027,8246);
        Ug ob1=new Ug("Arjun","IT111",2027,8246);
        String data=ob.toString();
        String data1=ob1.toString();
        ob.addContest(data);
        ob.disp();
        ob.dispContest();
        ob1.addContest(data);
        ob1.disp();
        ob1.dispContest(data1);
    }
}