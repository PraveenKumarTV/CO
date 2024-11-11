import java.io.*;
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

    public String toFileString() {
        return studentId + "," + getName() + "," + email + "," + graduationYear + "," + gpa + "," + scholarshipAmount;
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

    public String toFileString() {
        return getName() + "," + donationAmount;
    }
}

class AlumniEvent {
    private String eventName;
    private String date;
    private String location;
    private List<Student> attendees;
    private int maxAttendees;

    public AlumniEvent(String eventName, String date, String location, int maxAttendees) {
        this.eventName = eventName;
        this.date = date;
        this.location = location;
        this.maxAttendees = maxAttendees;
        this.attendees = new ArrayList<>();
    }

    public boolean addAttendee(Student student) {
        if (attendees.size() < maxAttendees) {
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
        for (Student student : attendees) {
            student.displayInfo(); // Polymorphic call
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
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public Donor getDonor(String name) {
        for (Donor donor : donors) {
            if (donor.getName().equals(name)) {
                return donor;
            }
        }
        return null;
    }

    public void writeStudentsToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.write(student.toFileString());
                writer.newLine();
            }
        }
    }

    public void writeDonorsToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Donor donor : donors) {
                writer.write(donor.toFileString());
                writer.newLine();
            }
        }
    }

    public void readStudentsFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String studentId = data[0];
                String name = data[1];
                String email = data[2];
                int graduationYear = Integer.parseInt(data[3]);
                double gpa = Double.parseDouble(data[4]);
                double scholarshipAmount = Double.parseDouble(data[5]);
                try {
                    Student student = new Student(name, email, graduationYear, studentId, gpa, scholarshipAmount);
                    addStudent(student);
                } catch (InvalidScholarshipAmountException | InvalidIdFormatException e) {
                    System.out.println("Error processing student: " + e.getMessage());
                }
            }
        }
    }

    public void readDonorsFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                double donationAmount = Double.parseDouble(data[1]);
                try {
                    Donor donor = new Donor(name, donationAmount);
                    addDonor(donor);
                } catch (InvalidDonationAmountException e) {
                    System.out.println("Error processing donor: " + e.getMessage());
                }
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        AlumniEvent alumniEvent = new AlumniEvent("2024 Alumni Reunion", "Sep 25, 2024", "KK Auditorium", 5);
        ScholarshipManager manager = new ScholarshipManager();

        try {
            // Reading students and donors from file
            manager.readStudentsFromFile("students.txt");
            manager.readDonorsFromFile("donors.txt");

            // Adding students to the event and manager
            Student student1 = new Student("Praveen", "praveen@gmail.com", 2020, "IT001", 9.8, 1000.00);
            Student student2 = new Student("Raju", "raju@gmail.com", 2021, "IT002", 9.9, 1500.00);  // Will throw error
            Student student3 = new Student("Akthar", "akthar@gmail.com", 2022, "IT003", 9.7, 2000.00);
            alumniEvent.addAttendee(student1);
            alumniEvent.addAttendee(student3);

            // Adding donors
            Donor donor1 = new Donor("96Reblend", 5000);
            manager.addDonor(donor1);

            // Displaying event details
            alumniEvent.displayEventDetails();

            // Write students and donors to files after processing
            manager.writeStudentsToFile("students.txt");
            manager.writeDonorsToFile("donors.txt");

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student ID to retrieve: ");
            String id = sc.next();
            Student retrievedStudent = manager.getStudent(id);
            if (retrievedStudent != null) {
                retrievedStudent.displayInfo(); // Polymorphic call
            } else {
                System.out.println("Student not found.");
            }

            System.out.print("Enter donor name to retrieve: ");
            String donorName = sc.next();
            Donor retrievedDonor = manager.getDonor(donorName);
            if (retrievedDonor != null) {
                retrievedDonor.displayInfo(); // Polymorphic call
            } else {
                System.out.println("Donor not found.");
            }
        } catch (IOException | InvalidScholarshipAmountException | InvalidDonationAmountException | InvalidIdFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
