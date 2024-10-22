package pack1;

public class Student implements Events {
    String contest[] = new String[10];
    String wkshop[] = new String[10];
    int cc = 0, wc = 0;

    
    public void addContest(String ob) {
        if (cc >= 10) {
            System.out.println("Contest is full");
            return;
        } else {
            contest[cc] = ob;
            cc++;
            System.out.println("Contest added successfully");
        }
    }

    
    public void addWorkshop(String ob) {
        if (wc >= 10) {
            System.out.println("Workshop is full");
            return;
        } else {
            wkshop[wc] = ob; // Corrected to add to wkshop
            wc++;
            System.out.println("Workshop added successfully");
        }
    }

    public void dispContest(){
        int i=0;
        while(i<cc){
            System.out.println(contest[i]);
            i++;
        }
    }

    public void dispWorkshop(){
        int i=0;
        while(i<wc){
            System.out.println(wkshop[i]);
            i++;
        }
    }

}
