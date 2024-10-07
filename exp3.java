import java.util.Date;
import java.util.Scanner;

interface displayable{
        public void disp();
}

class MotorBill {
    private String[] services = {"waterwash", "engine repair", "general service"};
    private int[] serviceCosts = {1000, 2000, 3000};
    private String[] models = {"model 1", "model 2", "model 3"};
    private int[] modelCosts = {50000, 60000, 70000};
    private int a;
    Date date = new Date();
    
    void serCalculate(Service s) {
        System.out.println("===============");
        System.out.println("Bill Details:");
        System.out.println("Name: " + s.name);
        System.out.println("Contact no: " + s.phno);
        System.out.println("Address: " + s.addr);
        System.out.println("Date: " + date.toString());

        boolean validService = false;
        for (int i = 0; i < services.length; i++) {
            if (services[i].equals(s.task)) {
                a = serviceCosts[i];
                System.out.println(s.task + " = " + serviceCosts[i]);
                validService = true;
                break;
            }
        }

        if (!validService) {
            System.out.println("Invalid service");
            return;
        }

        System.out.println("GST(18%) = " + (a * 0.18));
        System.out.println("Discount(10%) = " + (a * 0.10));
        System.out.println("Additional charges: 150");
        System.out.println("Total amount = " + (a + a * 0.18 - a * 0.10 + 150));
        System.out.println("===============");
    }

    void salCalculate(Sales s) {
        System.out.println("===============");
        System.out.println("Bill Details:");
        System.out.println("Name: " + s.name);
        System.out.println("Contact no: " + s.phno);
        System.out.println("Address: " + s.addr);
        System.out.println("Date: " + date.toString());
        System.out.println("Model name: " + s.task);
        System.out.println("Selected color: " + s.color);

        boolean validModel = false;
        for (int i = 0; i < models.length; i++) {
            if (models[i].equals(s.task)) {
                a = modelCosts[i];
                System.out.println(s.task + " = " + modelCosts[i]);
                validModel = true;
                break;
            }
        }

        if (!validModel) {
            System.out.println("Invalid option");
            return;
        }

        System.out.println("GST(18%) = " + (a * 0.18));
        System.out.println("Discount(10%) = " + (a * 0.10));
        System.out.println("Additional charges: 150");
        System.out.println("Total amount = " + (a + a * 0.18 - a * 0.10 + 150));
        System.out.println("===============");
    }
}

class Service implements displayable {
    String name;
    int phno;
    String addr;
    String task;
    Scanner sc = new Scanner(System.in);

    Service() {
        name = "";
        phno = 0;
        addr = "";
    }

    void inp() {
        System.out.println("Enter name:");
        name = sc.next();
        System.out.println("Enter address:");
        addr = sc.next();
        System.out.println("Enter phone number:");
        phno = sc.nextInt();
    }

    public void disp() {
        System.out.println("Available services:");
        System.out.println("1. waterwash");
        System.out.println("2. engine repair");
        System.out.println("3. general service");
        System.out.println("Select from the options:");
        task = sc.next();
    }
}

class Sales implements displayable {
    String name;
    String task;
    String color;
    int phno;
    String addr;
    Scanner sc = new Scanner(System.in);

    Sales() {
        name = "";
        phno = 0;
        addr = "";
    }

    void inp() {
        System.out.println("Enter name:");
        name = sc.next();
        System.out.println("Enter address:");
        addr = sc.next();
        System.out.println("Enter phone number:");
        phno = sc.nextInt();
    }

    public void disp() {
        System.out.println("Available models:");
        System.out.println("1. model 1");
        System.out.println("2. model 2");
        System.out.println("3. model 3");
        System.out.println("Select from the options:");
        task = sc.nextLine();
        System.out.println("Select color: Red, Black, White");
        color = sc.next();
    }
}

class exp3 {
    public static void main(String args[]) {
        MotorBill ob = new MotorBill();
        Service ob1 = new Service();
        Sales ob2 = new Sales();

        //ob1.disp();
        //ob1.inp();
        //ob.serCalculate(ob1);

        ob2.disp();
        ob2.inp();
        ob.salCalculate(ob2);
    }
}
