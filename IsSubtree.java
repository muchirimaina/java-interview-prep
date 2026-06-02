// Recursion solution

public class CheckSubtree {

    public static boolean isSubtree(TreeNode root1, TreeNode root2) {
        // Base cases
        if (root2 == null) return true;
        if (root1 == null) return false;

        // If current nodes match, check subtree
        if (root1.val == root2.val && matchTree(root1, root2)) {
            return true;
        }

        // Otherwise search left or right
        return isSubtree(root1.left, root2) || isSubtree(root1.right, root2);
    }

    private static boolean matchTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;

        return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// StringBuilder indexOf solution - Note SB takes a string that why we have string2.toString() but we don't need string1.toString() since string1 is the StringBuilder
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        preOrder(root,string1);
        preOrder(subRoot, string2);

        return string1.indexOf(string2.toString()) != -1;
        
    }

    public void preOrder(TreeNode node, StringBuilder sb ){
        if(node == null){
            sb.append("X,");
            return;
        }

        sb.append("$").append(node.val).append(",");
        preOrder(node.left,sb);
        preOrder(node.right,sb);

    }
}