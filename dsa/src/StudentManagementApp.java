
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Student Class
class Student {
    String id;
    String name;
    double marks;
    String rank;

    // Constructor
    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.rank = assignRank(marks);
    }

    // Method to assign rank based on marks
    public String assignRank(double marks) {
        if (marks >= 0 && marks < 5.0)
            return "Fail";
        if (marks >= 5.0 && marks < 6.5)
            return "Medium";
        if (marks >= 6.5 && marks < 7.5)
            return "Good";
        if (marks >= 7.5 && marks < 9.0)
            return "Very Good";
        if (marks >= 9.0 && marks <= 10.0)
            return "Excellent";
        return "Invalid Marks";
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + rank;
    }
}

// Student Manager Class
class StudentManager {
    ArrayList<Student> students = new ArrayList<>();

    // Add Student
    public void addStudent(String id, String name, double marks) {
        Student newStudent = new Student(id, name, marks);
        students.add(newStudent);
    }

    // Edit Student
    public void editStudent(String id, String newName, double newMarks) {
        for (Student student : students) {
            if (student.id.equalsIgnoreCase(id)) {
                student.name = newName;
                student.marks = newMarks;
                student.rank = student.assignRank(newMarks); // Reassign rank
                break;
            }
        }
    }

    // Delete Student
    public void deleteStudent(String id) {
        students.removeIf(student -> student.id.equalsIgnoreCase(id));
    }

    // Sort Students using Bubble Sort
    public void bubbleSortStudents() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).marks > students.get(j + 1).marks) {
                    // Swap the students
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    // Search for a student by ID (alphanumeric)
    public Student searchStudent(String studentId) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("students.csv"), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split line into columns
                String[] data = line.split(",");
                try {
                    String id = data[0].trim(); // Get ID as String
                    if (id.equalsIgnoreCase(studentId)) { // Compare ignoring case
                        String name = data[1].trim(); // Get name
                        double marks = Double.parseDouble(data[2].trim()); // Get marks
                        return new Student(id, name, marks); // Create and return Student object
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid data format in file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
        return null; // Return null if student not found
    }

    // Display all students
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Load students from CSV file
    public void loadStudentsFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            String line;
            boolean isFirstLine = true; // Flag to skip BOM if present in the first line
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    // Skip BOM if exists
                    if (line.startsWith("\uFEFF")) {
                        line = line.substring(1); // Remove BOM
                    }
                    isFirstLine = false;
                }

                String[] data = line.split(",");
                try {
                    String id = data[0].trim(); // Process ID
                    String name = data[1].trim(); // Process name
                    double marks = Double.parseDouble(data[2].trim()); // Process marks
                    addStudent(id, name, marks); // Add student to list
                } catch (NumberFormatException e) {
                    System.out.println("Invalid data in CSV: " + line);
                }
            }
            System.out.println("Students loaded from CSV file successfully.");
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}

// Main Application Class
public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        // Load students from CSV file at the start
        manager.loadStudentsFromCSV("students.csv"); // Ensure this file is in the same directory

        while (true) {
            System.out.println("\n----- Student Management System -----");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students by Marks");
            System.out.println("5. Search Student");
            System.out.println("6. Display All Students");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Student
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Marks: ");
                    double marks = scanner.nextDouble();
                    manager.addStudent(id, name, marks);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    // Edit Student
                    System.out.print("Enter Student ID to Edit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Marks: ");
                    double newMarks = scanner.nextDouble();
                    manager.editStudent(editId, newName, newMarks);
                    System.out.println("Student updated successfully!");
                    break;

                case 3:
                    // Delete Student
                    System.out.print("Enter Student ID to Delete: ");
                    String deleteId = scanner.nextLine();
                    manager.deleteStudent(deleteId);
                    System.out.println("Student deleted successfully!");
                    break;

                case 4:
                    // Sort Students
                    manager.bubbleSortStudents();
                    System.out.println("Students sorted by marks.");
                    break;

                case 5:
                    // Search for a Student
                    System.out.print("Enter Student ID to Search: ");
                    String searchId = scanner.nextLine();
                    Student student = manager.searchStudent(searchId);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    // Display All Students
                    manager.displayStudents();
                    break;

                case 7:
                    // Exit
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}