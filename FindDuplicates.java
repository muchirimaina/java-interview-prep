import java.util.BitSet;

public static void findDuplicates(int[] values){
    BitSet set = new BitSet(32000);

    for(int val: values){

        int index = val - 1;

        if(set.get(index)){
            System.out.println(val);

        }else{
            set.set(index);
        }
    }
}


// Complexity
// Time Complexity
// O(n)

// One pass through the array.

// Space Complexity
// O(32000 bits)

// Which is:

// 32000 / 8 = 4000 bytes = 4 KB

// That satisfies the constraint perfectly.


//IMPLEMENTING BITSET FROM SCRATCH
class BitSet {

    private int[] bitset;

    public BitSet(int size) {

        // each int stores 32 bits
        bitset = new int[(size >> 5) + 1];
    }

    public boolean get(int pos) {

        // divide by 32
        int wordNumber = pos >> 5;

        // mod 32
        int bitNumber = pos & 0x1F;

        return (bitset[wordNumber] & (1 << bitNumber)) != 0;
    }

    public void set(int pos) {

        // divide by 32
        int wordNumber = pos >> 5;

        // mod 32
        int bitNumber = pos & 0x1F;

        bitset[wordNumber] |= (1 << bitNumber);
    }
}

public class FindDuplicates {

    public static void findDuplicates(int[] values) {

        BitSet bs = new BitSet(32000);

        for (int val : values) {

            // numbers start at 1
            // bit positions start at 0
            int index = val - 1;

            if (bs.get(index)) {
                System.out.println(val);
            } else {
                bs.set(index);
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = {1, 5, 1, 10, 12, 10, 7, 5};

        findDuplicates(arr);
    }
}