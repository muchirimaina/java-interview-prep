public static int pairWiseSwap(int n) {
    int evenBits = (n & 0xAAAAAAAA) >>> 1;
    int oddBits  = (n & 0x55555555) << 1;
    return evenBits | oddBits;
}