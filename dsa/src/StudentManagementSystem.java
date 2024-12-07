import java.util.ArrayList;
import java.util.Scanner;

// Custom Exception for Invalid Marks
class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

// Student class
class Student {
    private int id;
    private String name;
    private double marks;

    // Constructor
    public Student(int id, String name, double marks) throws InvalidMarksException {
        if (marks < 0 || marks > 10) {
            throw new InvalidMarksException("Marks must be between 0 and 10.");
        }
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) throws InvalidMarksException {
        if (marks < 0 || marks > 10) {
            throw new InvalidMarksException("Marks must be between 0 and 10.");
        }
        this.marks = marks;
    }

    // Ranking method
    public String getRank() {
        if (marks >= 0 && marks < 5) {
            return "Fail";
        } else if (marks >= 5 && marks < 6.5) {
            return "Medium";
        } else if (marks >= 6.5 && marks < 7.5) {
            return "Good";
        } else if (marks >= 7.5 && marks < 9) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }
}

// Student Management System class
public class StudentManagementSystem {
    private static ArrayList<Student> studentList = new ArrayList<>();

    // Method to add a student
    public static void addStudent(int id, String name, double marks) {
        try {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Student name cannot be empty.");
            }
            Student student = new Student(id, name, marks);
            studentList.add(student);
            System.out.println("Student added successfully!");
        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to display student information
    public static void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student student : studentList) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Marks: " + student.getMarks() + ", Rank: " + student.getRank());
        }
    }

    // Method to edit a student's marks
    public static void editStudentMarks(int id, double newMarks) {
        try {
            Student student = findStudentById(id);
            if (student == null) {
                System.out.println("Student not found!");
                return;
            }
            student.setMarks(newMarks);
            System.out.println("Student marks updated successfully!");
        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to find a student by ID
    private static Student findStudentById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Main method to run the system
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Sample data input
        addStudent(1, "John", 7.8);
        addStudent(2, "Jane", 4.5); // Invalid marks
        addStudent(3, "Alice", 8.2);
        
        displayStudents();

        // Edit a student's marks
        System.out.print("Enter student ID to edit marks: ");
        int id = scanner.nextInt();
        System.out.print("Enter new marks: ");
        double newMarks = scanner.nextDouble();
        editStudentMarks(id, newMarks);

        displayStudents();
        scanner.close();
    }
}

