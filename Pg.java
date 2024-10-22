package pack1;
public class Pg extends Student{
    String name,id;int gy;long ph;
    public Pg(String name,String id,int gy,long ph){
        this.name=name;
        this.id=id;
        this.gy=gy;
        this.ph=ph;
    }
    public void disp(){
        System.out.println("Name: "+name);
        System.out.println("Id: "+id);
        System.out.println("Grad year: "+gy);
        System.out.println("Ph num: "+ph);
    }
    public String toString(){
        return name+" "+id+" "+gy+" "+ph;
    }
}