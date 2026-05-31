public static int sparseSearch(String[] strs, String target) {

    int left = 0;
    int right = strs.length - 1;

    while (left <= right) {

        int mid = left + (right - left) / 2;

        // Find closest non-empty string
        if (strs[mid].isEmpty()) {

            int l = mid - 1;
            int r = mid + 1;

            while (true) {                          //There is no condition being checked like: while (left <= right) Instead, the loop is intentionally infinite until break happens or return

                if (l < left && r > right) {
                    return -1;
                }

                if (r <= right && !strs[r].isEmpty()) {
                    mid = r;
                    break;
                }

                if (l >= left && !strs[l].isEmpty()) {
                    mid = l;
                    break;
                }

                r++;
                l--;
            }
        }

        // Normal binary search comparison
        if (strs[mid].equals(target)) {
            return mid;
        }

        if (strs[mid].compareTo(target) < 0) {  //"apple".compareTo("ball") is negative value because "apple" comes before "ball" hence search to the right i.e left = mid + 1
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1;
}


// Average TC: O(log n)
// Worst TC case (many empty strings): O(n)
// Space: O(1)


//USING PURE COMPARE TO
public class SparseSearch {

    public static int sparseSearch(String[] strs, String target) {

        if (strs == null || strs.length == 0) return -1;
        if (target == null || target.isEmpty()) return -1;

        int left = 0;
        int right = strs.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            // If mid is empty, find nearest non-empty string
            if (strs[mid].isEmpty()) {

                int l = mid - 1;
                int r = mid + 1;

                while (true) {

                    if (l < left && r > right) {
                        return -1;
                    }

                    if (r <= right && !strs[r].isEmpty()) {
                        mid = r;
                        break;
                    }

                    if (l >= left && !strs[l].isEmpty()) {
                        mid = l;
                        break;
                    }

                    r++;
                    l--;
                }
            }

            // Standard binary search comparison using compareTo
            int cmp = strs[mid].compareTo(target);

            if (cmp == 0) return mid;
            else if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {

        String[] strs = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", ""};

        System.out.println(sparseSearch(strs, "ball")); // 4
        System.out.println(sparseSearch(strs, "car"));  // 7
        System.out.println(sparseSearch(strs, "dad"));  // 10
        System.out.println(sparseSearch(strs, "cat"));  // -1
    }
}