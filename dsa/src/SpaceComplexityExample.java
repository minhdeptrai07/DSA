public class SpaceComplexityExample {

    public static int sumArray(int[] arr) {
        int sum = 0; // Constant space for storing the sum
        for (int num : arr) {
            sum += num;  // Summing elements
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};

        long startTime = System.nanoTime();  // Start time measurement
        int sum = sumArray(arr);  // Perform sum calculation
        long endTime = System.nanoTime(); // End time measurement

        System.out.println("Sum of array elements: " + sum);

        long duration = endTime - startTime;
        System.out.println("Time taken for sum calculation: " + duration + " nanoseconds.");
    }
}
