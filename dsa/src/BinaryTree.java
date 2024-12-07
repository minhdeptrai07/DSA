// Node class to represent each student (node) in the tree
class StudentNode {
    int studentId;
    String studentName;
    double studentMarks;
    StudentNode left, right;

    public StudentNode(int studentId, String studentName, double studentMarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMarks = studentMarks;
        left = right = null;
    }
}

// Binary Tree class to manage the student data
public class BinaryTree {

    private StudentNode root;

    // Constructor
    public BinaryTree() {
        root = null;
    }

    // 1. Add a new student to the tree
    public void insertNode(int studentId, String studentName, double studentMarks) {
        root = insertNodeRecursive(root, studentId, studentName, studentMarks);
    }

    private StudentNode insertNodeRecursive(StudentNode node, int studentId, String studentName, double studentMarks) {
        // If the tree is empty or we've found the position, create a new node
        if (node == null) {
            return new StudentNode(studentId, studentName, studentMarks);
        }

        // Otherwise, recur down the tree
        if (studentId < node.studentId) {
            node.left = insertNodeRecursive(node.left, studentId, studentName, studentMarks);
        } else if (studentId > node.studentId) {
            node.right = insertNodeRecursive(node.right, studentId, studentName, studentMarks);
        }

        return node;
    }

    // 2. Search for a student by studentId
    public StudentNode searchNode(int studentId) {
        return searchNodeRecursive(root, studentId);
    }

    private StudentNode searchNodeRecursive(StudentNode node, int studentId) {
        // Base case: if the node is null or the studentId is found
        if (node == null || node.studentId == studentId) {
            return node;
        }

        // If studentId is smaller, search in the left subtree
        if (studentId < node.studentId) {
            return searchNodeRecursive(node.left, studentId);
        }

        // Otherwise, search in the right subtree
        return searchNodeRecursive(node.right, studentId);
    }

    // 3. Delete a student by studentId
    public void deleteNode(int studentId) {
        root = deleteNodeRecursive(root, studentId);
    }

    private StudentNode deleteNodeRecursive(StudentNode node, int studentId) {
        // Base case: if the tree is empty
        if (node == null) {
            return node;
        }

        // Otherwise, recur down the tree
        if (studentId < node.studentId) {
            node.left = deleteNodeRecursive(node.left, studentId);
        } else if (studentId > node.studentId) {
            node.right = deleteNodeRecursive(node.right, studentId);
        } else {
            // Node to be deleted is found

            // Case 1: Node has no children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: Node has only one child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Case 3: Node has two children
            // Get the inorder successor (smallest in the right subtree)
            node.studentId = findMin(node.right).studentId;
            node.studentName = findMin(node.right).studentName;
            node.studentMarks = findMin(node.right).studentMarks;

            // Delete the inorder successor
            node.right = deleteNodeRecursive(node.right, node.studentId);
        }

        return node;
    }

    private StudentNode findMin(StudentNode node) {
        StudentNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // 4. In-order Traversal to display all students in sorted order
    public void inorderTraversal() {
        inorderTraversalRecursive(root);
    }

    private void inorderTraversalRecursive(StudentNode node) {
        if (node != null) {
            inorderTraversalRecursive(node.left);  // Visit left subtree
            System.out.println("ID: " + node.studentId + ", Name: " + node.studentName + ", Marks: " + node.studentMarks);  // Visit current node
            inorderTraversalRecursive(node.right);  // Visit right subtree
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Inserting students
        tree.insertNode(1, "Alice", 8.2);
        tree.insertNode(3, "Bob", 7.5);
        tree.insertNode(2, "Charlie", 6.8);
        tree.insertNode(4, "David", 9.1);

        // Display all students (in-order traversal)
        System.out.println("Student Records in Sorted Order:");
        tree.inorderTraversal();

        // Search for a student
        System.out.println("\nSearching for student with ID 3:");
        StudentNode student = tree.searchNode(3);
        if (student != null) {
            System.out.println("Found: " + student.studentName + " with Marks: " + student.studentMarks);
        } else {
            System.out.println("Student not found.");
        }

        // Delete a student (ID 2)
        System.out.println("\nDeleting student with ID 2...");
        tree.deleteNode(2);
        
        // Display updated students (in-order traversal)
        System.out.println("\nUpdated Student Records:");
        tree.inorderTraversal();
    }
}
