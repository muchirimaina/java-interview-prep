// 1 GB solution

// “Since integers are non-negative and within a known range, I can use a bitmap where each bit represents whether a number exists. 
// This reduces memory from 32 bits per integer to 1 bit. I scan the file once to set bits, then scan the bitmap to find the first unset bit.


import java.util.BitSet;

public class MissingInt1GB {

    public static int findMissing(int[] stream, int maxValue) {
        BitSet bitset = new BitSet(maxValue);

        // Step 1: mark numbers as seen
        for (int num : stream) {
            if (num >= 0 && num < maxValue) {
                bitset.set(num);
            }
        }

        // Step 2: find first missing
        for (int i = 0; i < maxValue; i++) {
            if (!bitset.get(i)) {
                return i;
            }
        }

        return -1; // should not happen
    }
}

// 10 MB solution

// “With only 10 MB, I cannot maintain a global bitmap, so I partition the value range into buckets.
// In the first pass, I count how many numbers fall into each bucket. Since one number is missing, at least one bucket must be underfilled.
//  In the second pass, I only track numbers inside that bucket using a bitmap, which now fits in memory. Finally, I find the missing offset inside that bucket.”

// LESS MEMORY

public class MissingInt10MB {

    static final int RANGE = 1 << 20; // bucket size
    static final long MAX_VALUE = 1_000_000_000L;

    public static int findMissing(int[] stream) {

        int numBuckets = (int)(MAX_VALUE / RANGE) + 1;
        int[] bucketCount = new int[numBuckets];

        // PASS 1: count elements per bucket
        for (int num : stream) {
            int bucketIndex = num / RANGE;
            bucketCount[bucketIndex]++;
        }

        // find bucket with missing space
        int targetBucket = -1;
        for (int i = 0; i < numBuckets; i++) {
            if (bucketCount[i] < RANGE) {
                targetBucket = i;
                break;
            }
        }

        if (targetBucket == -1) return -1;

        // bitset for just this bucket (~1M bits ≈ 125 KB)
        BitSet bitset = new BitSet(RANGE);

        int start = targetBucket * RANGE;
        int end = start + RANGE;

        for (int num : stream) {
            if (num >= start && num < end) {
                bitset.set(num - start);
            }
        }

        for (int i = 0; i < RANGE; i++) {
            if (!bitset.get(i)) {
                return start + i;
            }
        }

        return -1;
    }
}