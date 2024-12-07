class Student {
    int studentId;
    String studentName;
    double studentMarks;
    String rank;
    Student left, right;

    public Student(int studentId, String studentName, double studentMarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMarks = studentMarks;
        this.rank = calculateRank(studentMarks);
        this.left = this.right = null;
    }

    private String calculateRank(double marks) {
        if (marks < 5.0) return "Fail";
        if (marks < 6.5) return "Medium";
        if (marks < 7.5) return "Good";
        return "Very Good";
    }

    public void updateMarks(double newMarks) {
        this.studentMarks = newMarks;
        this.rank = calculateRank(newMarks);
    }

    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + studentName + ", Marks: " + studentMarks + ", Rank: " + rank;
    }
}

class StudentBinaryTree {
    private Student root;

    // Add a new student
    public void addStudent(int studentId, String studentName, double studentMarks) {
        root = addRecursive(root, studentId, studentName, studentMarks);
    }

    private Student addRecursive(Student node, int studentId, String studentName, double studentMarks) {
        if (node == null) return new Student(studentId, studentName, studentMarks);

        if (studentId < node.studentId) {
            node.left = addRecursive(node.left, studentId, studentName, studentMarks);
        } else if (studentId > node.studentId) {
            node.right = addRecursive(node.right, studentId, studentName, studentMarks);
        }
        return node;
    }

    // Search for a student by ID
    public Student searchStudentById(int studentId) {
        return searchRecursive(root, studentId);
    }

    private Student searchRecursive(Student node, int studentId) {
        if (node == null || node.studentId == studentId) return node;
        return studentId < node.studentId ? searchRecursive(node.left, studentId) : searchRecursive(node.right, studentId);
    }

    // Edit student details
    public boolean editStudent(int studentId, String newName, double newMarks) {
        Student student = searchStudentById(studentId);
        if (student != null) {
            student.studentName = newName;
            student.updateMarks(newMarks);
            return true;
        }
        return false;
    }

    // Delete a student
    public boolean deleteStudent(int studentId) {
        root = deleteRecursive(root, studentId);
        return root != null;
    }

    private Student deleteRecursive(Student node, int studentId) {
        if (node == null) return null;

        if (studentId < node.studentId) {
            node.left = deleteRecursive(node.left, studentId);
        } else if (studentId > node.studentId) {
            node.right = deleteRecursive(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            node.studentId = findMin(node.right).studentId;
            node.right = deleteRecursive(node.right, node.studentId);
        }
        return node;
    }

    private Student findMin(Student node) {
        return node.left == null ? node : findMin(node.left);
    }

    // In-order traversal to display students
    public void displayStudents() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Student node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.println(node);
            inorderTraversal(node.right);
        }
    }
}

public class StudentManagementApp {
    public static void main(String[] args) {
        StudentBinaryTree tree = new StudentBinaryTree();

        // Add students
        tree.addStudent(1, "Alice", 8.0);
        tree.addStudent(2, "Bob", 6.2);
        tree.addStudent(3, "Charlie", 4.9);
        tree.addStudent(4, "David", 7.0);

        // Display all students
        System.out.println("All Students:");
        tree.displayStudents();

        // Search for a student
        System.out.println("\nSearching for Student with ID 2:");
        System.out.println(tree.searchStudentById(2));

        // Edit a student
        System.out.println("\nEditing Student with ID 3:");
        if (tree.editStudent(3, "Charlie Brown", 5.5)) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
        tree.displayStudents();

        // Delete a student
        System.out.println("\nDeleting Student with ID 4:");
        if (tree.deleteStudent(4)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
        tree.displayStudents();
    }
}
