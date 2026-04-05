class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int val) {
		this.val = val;
		this.left = null;
		this.right = null;
	}
}


public class CheckValidBST {

	TreeNode root;

	public TreeNode insert(TreeNode root, int val) {
		if(root == null) {
			return new TreeNode(val);
		}

		if(val < root.val) {
			root.left = insert(root.left,val);
		} else if(val > root.val) {
			root.right = insert(root.right, val);
		}

		return root;
	}
	public static boolean isValidBST(TreeNode root) {

		if(root == null) return true;
		return checkValidity(Long.MIN_VALUE,root,Long.MAX_VALUE);
	}


	public static boolean checkValidity(long min,TreeNode node,long max) {

		if(node == null) return true;

		if(!(min< node.val && node.val < max)) {
			return false;
		}


		return checkValidity(min, node.left,node.val) && checkValidity(node.val, node.right, max);
	}

	public void inorder(TreeNode root) {
		if(root == null) return;

		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
	}

	public static void main(String[] args) {

		CheckValidBST myTree = new CheckValidBST();

		myTree.root = myTree.insert(myTree.root,2);
		myTree.root = myTree.insert(myTree.root,10);
		myTree.root = myTree.insert(myTree.root,20);
		myTree.root = myTree.insert(myTree.root,6);
		myTree.root = myTree.insert(myTree.root,4);
		myTree.root = myTree.insert(myTree.root,8);

		System.out.println("Is it a valid BST? "+ isValidBST(myTree.root));

		System.out.println("Inorder Traversal of My BST :");
		myTree.inorder(myTree.root);




	}

}