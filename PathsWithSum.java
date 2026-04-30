//Brute Force Application

public class PathsWithSum {

    public int numberOfPaths(TreeNode root, int targetSum) {
        if (root == null) return 0;

        // paths starting from current node
        int pathsFromRoot = countFromNode(root, targetSum);

        // try left and right as new roots
        int leftPaths = numberOfPaths(root.left, targetSum);
        int rightPaths = numberOfPaths(root.right, targetSum);

        return pathsFromRoot + leftPaths + rightPaths;
    }

    private int countFromNode(TreeNode node, int targetSum) {
        if (node == null) return 0;

        int count = 0;

        if (node.val == targetSum) {
            count++;
        }

        count += countFromNode(node.left, targetSum - node.val);
        count += countFromNode(node.right, targetSum - node.val);

        return count;
    }
}





//Optimal Application using HashMap
import java.util.HashMap;

public class PathsWithSum {

    public int numberOfPaths(TreeNode root, int targetSum) {
        HashMap<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1); // base case

        return dfs(root, 0, targetSum, prefixMap);
    }

    private int dfs(TreeNode node, int currSum, int target, HashMap<Integer, Integer> map) {
        if (node == null) return 0;

        currSum += node.val;

        int count = 0;

        // Check if there is a prefix sum we can subtract
        count += map.getOrDefault(currSum - target, 0);

        // Add current sum to map
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        // Explore children
        count += dfs(node.left, currSum, target, map);
        count += dfs(node.right, currSum, target, map);

        // Backtrack (IMPORTANT)
        map.put(currSum, map.get(currSum) - 1);

        // The above Backtrack could also be written as
        // if (map.get(currSum) == 1) {
        //     map.remove(currSum);
        // } else {
        //     map.put(currSum, map.get(currSum) - 1);
        // } 

        return count;
    }
}