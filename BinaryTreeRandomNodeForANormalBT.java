import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    int size;

    TreeNode(int val) {
        this.val = val;
        this.size = 1;
    }
}

public class BinaryTreeRandomNodeForANormalBT{

    TreeNode root;
    Random rand = new Random();

    // INSERT (level order)

    public void insert(int val) {
        if (root == null) {
            root = new TreeNode(val);
            return;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr.left == null) {
                curr.left = new TreeNode(val);
                break;
            } else {
                q.add(curr.left);
            }

            if (curr.right == null) {
                curr.right = new TreeNode(val);
                break;
            } else {
                q.add(curr.right);
            }
        }

        updateSize(root); // recompute sizes
    }

    // FIND (DFS)
    public TreeNode find(int val) {
        return find(root, val);
    }

    private TreeNode find(TreeNode node, int val) {
        if (node == null) return null;
        if (node.val == val) return node;

        TreeNode left = find(node.left, val);
        if (left != null) return left;

        return find(node.right, val);
    }

    // DELETE (replace with deepest node)
    
    public void delete(int val) {
        if (root == null) return;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        TreeNode target = null;
        TreeNode curr = null;

        // Find target and deepest node
        while (!q.isEmpty()) {
            curr = q.poll();

            if (curr.val == val) {
                target = curr;
            }

            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }

        if (target != null) {
            int deepestValue = curr.val;
            deleteDeepest(root, curr);
            target.val = deepestValue;
        }

        updateSize(root);
    }

    private void deleteDeepest(TreeNode root, TreeNode dNode) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr.left != null) {
                if (curr.left == dNode) {
                    curr.left = null;
                    return;
                } else q.add(curr.left);
            }

            if (curr.right != null) {
                if (curr.right == dNode) {
                    curr.right = null;
                    return;
                } else q.add(curr.right);
            }
        }
    }

    // GET RANDOM NODE
   
    public TreeNode getRandomNode() {
        if (root == null) return null;

        int k = rand.nextInt(root.size) + 1; // 1 to size
        return getKthNode(root, k);
    }

    private TreeNode getKthNode(TreeNode node, int k) {
        int leftSize = (node.left != null) ? node.left.size : 0;

        if (k <= leftSize) {
            return getKthNode(node.left, k);
        } else if (k == leftSize + 1) {
            return node;
        } else {
            return getKthNode(node.right, k - leftSize - 1);
        }
    }


    // UPDATE SIZE
    private int updateSize(TreeNode node) {
        if (node == null) return 0;

        node.size = 1 + updateSize(node.left) + updateSize(node.right);
        return node.size;
    }
}