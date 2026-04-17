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

public class BSTRandomNode {

    TreeNode root;
    Random rand = new Random();

    
    // INSERT (BST property)
    
    public void insert(int val) {
        root = insert(root, val);
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);

        if (val <= node.val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    
    // FIND
    
    public TreeNode find(int val) {
        return find(root, val);
    }

    private TreeNode find(TreeNode node, int val) {
        if (node == null) return null;
        if (node.val == val) return node;

        if (val < node.val) return find(node.left, val);
        else return find(node.right, val);
    }

    
    // DELETE (BST delete)
    
    public void delete(int val) {
        root = delete(root, val);
    }

    private TreeNode delete(TreeNode node, int val) {
        if (node == null) return null;

        if (val < node.val) {
            node.left = delete(node.left, val);
        } else if (val > node.val) {
            node.right = delete(node.right, val);
        } else {
            // Node found

            // Case 1: no child
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: one child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Case 3: two children

            //successor approach
            TreeNode successor = getMin(node.right);
            node.val = successor.val;
            node.right = delete(node.right, successor.val);

            //predecessor approach
            TreeNode predecessor = getMax(node.left);
            node.val = predecessor.val;
            node.left = delete(node.left, predecessor.val);
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private TreeNode getMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private TreeNode getMax(TreeNode node){
        while(node.right != null) node = node.right;
        return node;
    }

    
    // GET RANDOM NODE
    
    public TreeNode getRandomNode() {
        if (root == null) return null;

        int k = rand.nextInt(root.size) + 1;
        return getKthNode(root, k);
    }

    private TreeNode getKthNode(TreeNode node, int k) {
        int leftSize = size(node.left);

        if (k <= leftSize) {
            return getKthNode(node.left, k);
        } else if (k == leftSize + 1) {
            return node;
        } else {
            return getKthNode(node.right, k - leftSize - 1);
        }
    }

    private int size(TreeNode node) {
        return node == null ? 0 : node.size;
    }
}