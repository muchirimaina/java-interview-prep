public class Coins {

    public static int numberOfWays(int n) {
        int[] coins = {25, 10, 5, 1};
        return countWays(n, coins, 0);
    }

    private static int countWays(int amount, int[] coins, int index) {

        // Last coin is penny -> only 1 way left
        if (index == coins.length - 1) {
            return 1;
        }

        int ways = 0;
        int coinValue = coins[index];

        for (int i = 0; i * coinValue <= amount; i++) {
            int remaining = amount - (i * coinValue);
            ways += countWays(remaining, coins, index + 1);
        }

        return ways;
    }

    public static void main(String[] args) {
        System.out.println(numberOfWays(10)); // 4
    }
}


// Time complexity: O(n * number_of_coins)
// For this problem: O(n * 4) → basically O(n)
// Space complexity: O(n * 4) for dp + recursion stack O(n)

// Before memoization:
// Exponential-like recursion tree (repeated work)
// After memoization:
// Each state computed once