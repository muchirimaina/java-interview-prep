class RankNode {

    int data;
    int leftSize = 0;

    RankNode left;
    RankNode right;

    public RankNode(int d) {
        data = d;
    }

    // INSERT
    public void insert(int x) {

        if (x <= data) {

            if (left != null) {
                left.insert(x);
            } else {
                left = new RankNode(x);
            }

            leftSize++;

        } else {

            if (right != null) {
                right.insert(x);
            } else {
                right = new RankNode(x);
            }
        }
    }

    // GET RANK
    public int getRank(int x) {

        if (x == data) {

            return leftSize;

        } else if (x < data) {

            if (left == null) {
                return -1;
            }

            return left.getRank(x);

        } else {

            int rightRank = (right == null)
                    ? -1
                    : right.getRank(x);

            if (rightRank == -1) {
                return -1;
            }

            return leftSize + 1 + rightRank;
        }
    }
}







public class RankFromStream {

    RankNode root = null;

    public void track(int x) {

        if (root == null) {
            root = new RankNode(x);
        } else {
            root.insert(x);
        }
    }

    public int getRankOfNumber(int x) {

        if (root == null) {
            return -1;
        }

        return root.getRank(x);
    }

    public static void main(String[] args) {

        RankFromStream ranks = new RankFromStream();

        int[] stream = {5,1,4,4,5,9,7,13,3};

        for (int num : stream) {
            ranks.track(num);
        }

        System.out.println(ranks.getRankOfNumber(1)); // 0
        System.out.println(ranks.getRankOfNumber(3)); // 1
        System.out.println(ranks.getRankOfNumber(4)); // 3
    }
}



// Time Complexity:
// - track(x): O(log n) average, O(n) worst
// - getRank(x): O(log n) average, O(n) worst

// Space Complexity:
// - O(n) for storing all nodes
// - O(h) recursion stack (O(log n) average, O(n) worst)


// Key Insight (what interviewers want you to say)

// 👉 This solution depends on BST balance.

// That’s why follow-ups often ask:

// “How would you improve worst-case performance?”

// Answer:

// Use a balanced BST (AVL / Red-Black Tree)
// or
// Use Fenwick Tree / Segment Tree for O(log n) guaranteed