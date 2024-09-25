import java.util.*;
class Person {
    private String name;
public Person(String name) {
        this.name = name;
    }
public String getName() {
        return name;
    }
public void displayInfo() {
        System.out.println("Name: " + name);
    }}


class Student extends Person {
    private String email;
    private int graduationYear;
    private String studentId;
    private double gpa;
    private double scholarshipAmount;
public Student(String name, String email, int graduationYear, String studentId, double gpa, double scholarshipAmount) {
        super(name);
        this.email = email;
        this.graduationYear = graduationYear;
        this.studentId = studentId;
        this.gpa = gpa;
        this.scholarshipAmount = scholarshipAmount;
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Email: " + email);
        System.out.println("Graduation Year: " + graduationYear);
        System.out.println("GPA: " + gpa);
        System.out.println("Scholarship Amount: " + scholarshipAmount);
    }
public String getStudentId() {
        return studentId;
    }
}


class Donor extends Person {
    private double donationAmount;
    private Date donationDate;

    public Donor(String name, double donationAmount) {
        super(name);
        this.donationAmount = donationAmount;
        //this.donationDate = donationDate;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Donation Amount: " + donationAmount);
        System.out.println("Donation Date: " + donationDate);
    }
}


class AlumniEvent {
    private String eventName;
    private String date;
    private String location;
    private List<Student> attendees;

    public AlumniEvent(String eventName, String date, String location, int maxAttendees) {
        this.eventName = eventName;
        this.date = date;
        this.location = location;
        this.attendees = new ArrayList<>(maxAttendees);
    }

    public boolean addAttendee(Student student) {
        if (attendees.size() < attendees.size()) {
            attendees.add(student);
            return true;
        }
        return false; // Event is full
    }

    public void displayEventDetails() {
        System.out.println("Event Name: " + eventName);
        System.out.println("Date: " + date);
        System.out.println("Location: " + location);
        System.out.println("Attendees:");
        for (Student attendee : attendees) {
            attendee.displayInfo(); // Polymorphic call
        }
    }
}


class ScholarshipManager {
    private List<Student> students = new ArrayList<>();
    private List<Donor> donors = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public void addDonor(Donor d) {
        donors.add(d);
    }

    public Student getStudent(String id) {
        for (Student s : students) {
            if (s.getStudentId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public Donor getDonor(String name) {
        for (Donor d : donors) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }
}


class AlumniEventApp {
    public static void main(String[] args) {
        AlumniEvent alumniEvent = new AlumniEvent("2024 Alumni Reunion", "Sep 25, 2024", "KK Auditorium", 5);
        ScholarshipManager manager = new ScholarshipManager();

        
        Student student1 = new Student("praveen", "praveen@gmail.com", 2020, "S001", 9.8, 1000.00);
        Student student2 = new Student("raju", "raju@gmail.com", 2021, "S002", 9.9, 1500.00);
        Student student3 = new Student("akthar", "akthar@gmail.com", 2022, "S003", 9.7, 2000.00);

        
        alumniEvent.addAttendee(student1);
        alumniEvent.addAttendee(student2);
        alumniEvent.addAttendee(student3);

        
        manager.addStudent(student1);
        manager.addStudent(student2);
        manager.addStudent(student3);
        Donor d1=new Donor("96 reblend",5000);
        manager.addDonor(d1);

        
        alumniEvent.displayEventDetails();

        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student ID to retrieve: ");
        String id = sc.next();
        Student retrievedStudent = manager.getStudent(id);
        if (retrievedStudent != null) {
            retrievedStudent.displayInfo(); // Polymorphic call
        } else {
            System.out.println("Student not found.");
        }
        
        // Example of retrieving a donor
        System.out.print("Enter donor name to retrieve: ");
        String donorName = sc.next();
        Donor retrievedDonor = manager.getDonor(donorName);
        if (retrievedDonor != null) {
            retrievedDonor.displayInfo(); // Polymorphic call
        } else {
            System.out.println("Donor not found.");
        }
    }
}
