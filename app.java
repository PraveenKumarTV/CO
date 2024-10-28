import java.util.*;

// Custom exceptions
class InvalidDonationAmountException extends Exception {
    public InvalidDonationAmountException(String message) {
        super(message);
    }
}

class InvalidScholarshipAmountException extends Exception {
    public InvalidScholarshipAmountException(String message) {
        super(message);
    }
}

class InvalidIdFormatException extends Exception {
    public InvalidIdFormatException(String message) {
        super(message);
    }
}

// Define Displayable interface
interface Displayable {
    void displayInfo();
}

class Person implements Displayable {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + name);
    }
}

class Student extends Person {
    private String email;
    private int graduationYear;
    private String studentId;
    private double gpa;
    private double scholarshipAmount;

    public Student(String name, String email, int graduationYear, String studentId, double gpa, double scholarshipAmount) throws InvalidScholarshipAmountException, InvalidIdFormatException {
        super(name);
        if (scholarshipAmount < 0) {
            throw new InvalidScholarshipAmountException("Scholarship amount cannot be negative.");
        }
        if (!studentId.startsWith("IT")) {
            throw new InvalidIdFormatException("Student ID must start with 'IT'.");
        }
        this.email = email;
        this.graduationYear = graduationYear;
        this.studentId = studentId;
        this.gpa = gpa;
        this.scholarshipAmount = scholarshipAmount;
    }

    @Override
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

    public Donor(String name, double donationAmount) throws InvalidDonationAmountException {
        super(name);
        if (donationAmount < 0) {
            throw new InvalidDonationAmountException("Donation amount cannot be negative.");
        }
        this.donationAmount = donationAmount;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Donation Amount: " + donationAmount);
    }
}

class AlumniEvent {
    private String eventName;
    private String date;
    private String location;
    private Student[] attendees;
    private int maxAttendees;
    private int currentAttendeeCount;

    public AlumniEvent(String eventName, String date, String location, int maxAttendees) {
        this.eventName = eventName;
        this.date = date;
        this.location = location;
        this.maxAttendees = maxAttendees;
        this.attendees = new Student[maxAttendees];
        this.currentAttendeeCount = 0;
    }

    public boolean addAttendee(Student student) {
        if (currentAttendeeCount < maxAttendees) {
            attendees[currentAttendeeCount] = student;
            currentAttendeeCount++;
            return true;
        }
        return false; // Event is full
    }

    public void displayEventDetails() {
        System.out.println("Event Name: " + eventName);
        System.out.println("Date: " + date);
        System.out.println("Location: " + location);
        System.out.println("Attendees:");
        for (int i = 0; i < currentAttendeeCount; i++) {
            attendees[i].displayInfo(); // Polymorphic call
        }
    }
}

class ScholarshipManager {
    private Student[] students = new Student[100]; // Maximum of 100 students
    private Donor[] donors = new Donor[100]; // Maximum of 100 donors
    private int studentCount = 0;
    private int donorCount = 0;

    public void addStudent(Student s) {
        if (studentCount < students.length) {
            students[studentCount] = s;
            studentCount++;
        }
    }

    public void addDonor(Donor d) {
        if (donorCount < donors.length) {
            donors[donorCount] = d;
            donorCount++;
        }
    }

    public Student getStudent(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equals(id)) {
                return students[i];
            }
        }
        return null;
    }

    public Donor getDonor(String name) {
        for (int i = 0; i < donorCount; i++) {
            if (donors[i].getName().equals(name)) {
                return donors[i];
            }
        }
        return null;
    }
}

class app {
    public static void main(String[] args) {
        AlumniEvent alumniEvent = new AlumniEvent("2024 Alumni Reunion", "Sep 25, 2024", "KK Auditorium", 5);
        ScholarshipManager manager = new ScholarshipManager();

        try {
            Student student1 = new Student("Praveen", "praveen@gmail.com", 2020, "IT001", 9.8, 1000.00);
            Student student2 = new Student("Raju", "raju@gmail.com", 2021, "002", 9.9, -1500.00);
            Student student3 = new Student("Akthar", "akthar@gmail.com", 2022, "IT003", 9.7, 2000.00);

            alumniEvent.addAttendee(student1);
            alumniEvent.addAttendee(student2);
            alumniEvent.addAttendee(student3);

            manager.addStudent(student1);
            manager.addStudent(student2);
            manager.addStudent(student3);
            
            Donor d1 = new Donor("96Reblend", -5000);
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
        } catch (InvalidScholarshipAmountException | InvalidDonationAmountException | InvalidIdFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
