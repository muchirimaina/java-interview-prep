public class NextNumber {

    // ---------------- NEXT LARGEST ----------------
    public static int getNextLarger(int n) {
        int c = n;
        int c0 = 0;
        int c1 = 0;

        // count trailing zeros
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        // count ones after trailing zeros
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // if no bigger number possible
        if (c0 + c1 == 31 || c0 + c1 == 0) return -1;

        int p = c0 + c1; // position of rightmost non-trailing zero

        // Step 1: flip rightmost non-trailing zero
        n |= (1 << p);

        // Step 2: clear all bits to the right of p
        n &= ~((1 << p) - 1);

        // Step 3: insert (c1 - 1) ones on the right
        int mask = (1 << (c1 - 1)) - 1;
        n |= mask;

        return n;
    }

    // ---------------- NEXT SMALLEST ----------------
    public static int getNextSmaller(int n) {
        int temp = n;
        int c0 = 0;
        int c1 = 0;

        // count trailing ones
        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }

        // if all ones like 111... or 0
        if (temp == 0) return -1;

        // count zeros after trailing ones
        while (((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }

        int p = c0 + c1; // position of rightmost non-trailing one

        // Step 1: clear bits from p onwards
        n &= (~0) << (p + 1);

        // Step 2: mask of (c1 + 1) ones
        int mask = (1 << (c1 + 1)) - 1;

        // Step 3: shift mask into correct position
        n |= mask << (c0 - 1);

        return n;
    }

    // ---------------- TEST ----------------
    public static void main(String[] args) {
        int n = 0b11010101;

        System.out.println("Original      : " + Integer.toBinaryString(n));
        System.out.println("Next Largest  : " + Integer.toBinaryString(getNextLarger(n)));
        System.out.println("Next Smallest : " + Integer.toBinaryString(getNextSmaller(n)));
    }
}