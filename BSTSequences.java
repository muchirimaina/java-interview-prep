import java.util.*;

class BSTSEQ {

    public static List<List<Integer>> possibleArrays(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            result.add(new ArrayList<>());
            return result;
        }

        List<List<Integer>> leftSeq = possibleArrays(root.left);
        List<List<Integer>> rightSeq = possibleArrays(root.right);

        for (List<Integer> left : leftSeq) {
            for (List<Integer> right : rightSeq) {

                List<List<Integer>> weaved = new ArrayList<>();
                weaveLists(left, right, new LinkedList<>(), weaved);

                for (List<Integer> w : weaved) {
                    List<Integer> resultList = new ArrayList<>();
                    resultList.add(root.val);
                    resultList.addAll(w);
                    result.add(resultList);
                }
            }
        }

        return result;
    }

    private static void weaveLists(List<Integer> first, List<Integer> second,
                                   LinkedList<Integer> prefix,
                                   List<List<Integer>> results) {

        if (first.isEmpty() || second.isEmpty()) {
            List<Integer> result = new ArrayList<>(prefix);
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        int headFirst = first.remove(0);
        prefix.addLast(headFirst);
        weaveLists(first, second, prefix, results);
        prefix.removeLast();
        first.add(0, headFirst);

        int headSecond = second.remove(0);
        prefix.addLast(headSecond);
        weaveLists(first, second, prefix, results);
        prefix.removeLast();
        second.add(0, headSecond);
    }
}

// The wealists work like a machine, interlinking with the two for loops