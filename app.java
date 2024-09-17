import java.util.*;
class ScholarshipManager {
    private List<Student> students = new ArrayList<>();
    private List<Donor> donors = new ArrayList<>();
    
    public void addStudent(Student s) {
        students.add(s);
        }
    
    public void removeStudent(Student s) {
        students.remove(s);
        }
    
    public Student getStudent(String id) {
        for (Student s : students) {
            if (s.getStudentId().equals(id)) {
                return s;
                }
                }
        return null;
        }
    public void addDonor(Donor d) {
        donors.add(d);
        }
    public void removeDonor(Donor d) {
        donors.remove(d);
        }
    public Donor getDonor(String name) {
        for (Donor d : donors) {
            if (d.getName().equals(name)) {
                return d;}
                }
        return null;
        }
    }

class Student extends ScholarshipManager {
    private String name;
    private String studentId;
    private double gpa;
    private double scholarshipAmount;
    public Student(String name, String studentId, double gpa, double scholarshipAmount) {
        this.name = name;
        this.studentId = studentId;
        this.gpa = gpa;
        this.scholarshipAmount = scholarshipAmount;
    }
    public String getName() {
        return name;
        }
    public String getStudentId() {
        return studentId;
        }
    public double getGPA() {
        return gpa;
        }
    public double getScholarshipAmount() {
        return scholarshipAmount;
        }
    public void setScholarshipAmount(double amount) {
        this.scholarshipAmount = amount;
        }
    }

class Donor extends ScholarshipManager {
    private String name;
    private double donationAmount;
    private Date donationDate;
    public Donor(String name, double donationAmount, Date donationDate) {
        this.name = name;
        this.donationAmount = donationAmount;
        this.donationDate = donationDate;
    }
    public String getName() {
        return name;
        }
    public double getDonationAmount() {
        return donationAmount;
        }
    public Date getDonationDate() {
        return donationDate;
}
}


 class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        // Create a ScholarshipManager instance
        ScholarshipManager manager = new ScholarshipManager();
        
        // Create some Student objects
        Student student1 = new Student("Praveen Kumar", "S001", 9.6, 1000.00);
        Student student2 = new Student("Raju", "S002", 9.8, 1500.00);
        
        // Create some Donor objects
        Donor donor1 = new Donor("96 Reblend", 2000.00, new Date(1234567890L)); // Example date
        Donor donor2 = new Donor("TCE Alumni scholars", 1500.00, new Date(1234567891L)); // Example date
        
        // Add students to the manager
        manager.addStudent(student1);
        manager.addStudent(student2);
        
        // Add donors to the manager
        manager.addDonor(donor1);
        manager.addDonor(donor2);
        
        // Retrieve and display student information
        System.out.print("enter student id:");
        String id=sc.next();
        Student retrievedStudent = manager.getStudent(id);
        if (retrievedStudent != null) {
            System.out.println("Student Name: " + retrievedStudent.getName());
            System.out.println("Scholarship Amount(rupees): " + retrievedStudent.getScholarshipAmount());
        } else {
            System.out.println("Student not found.");
        }
        
        // Retrieve and display donor information
        System.out.print("enter donor name:");
        String p=sc.next();
        Donor retrievedDonor = manager.getDonor(p);
        if (retrievedDonor != null) {
            System.out.println("Donor Name: " + retrievedDonor.getName());
            System.out.println("Donation Amount(rupees): " + retrievedDonor.getDonationAmount());
        } else {
            System.out.println("Donor not found.");
        }
    }
}

