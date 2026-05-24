public class RecursiveMulitply {

    public static int recursiveMultiply(int smaller, int bigger) {
        // Base cases
        if (smaller == 0) return 0;
        if (smaller == 1) return bigger;

        // Divide smaller by 2
        int half = smaller >> 1;

        int halfProd = recursiveMultiply(half, bigger);

        // If smaller is even
        if (smaller % 2 == 0) {
            return halfProd + halfProd;
        } 
        // If smaller is odd
        else {
            return halfProd + halfProd + bigger;
        }
    }

    public static int multiply(int a, int b) {
        int smaller = Math.min(a, b);
        int bigger = Math.max(a, b);
        return recursiveMultiply(smaller, bigger);
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 3;
        System.out.println("Answer: " + multiply(n, m));
    }
}