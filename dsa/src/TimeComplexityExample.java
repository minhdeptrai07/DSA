public class TimeComplexityExample {

    // Linear Search Function (O(n) time complexity)
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Found target at index i
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13};
        int target = 7;

        long startTime = System.nanoTime();  // Start time measurement
        int index = linearSearch(arr, target); // Perform search
        long endTime = System.nanoTime(); // End time measurement

        if (index != -1) {
            System.out.println("Target found at index: " + index);
        } else {
            System.out.println("Target not found.");
        }

        // Calculate and print the elapsed time
        long duration = endTime - startTime;
        System.out.println("Time taken for linear search: " + duration + " nanoseconds.");
    }
}
