package code;


import java.util.Arrays;

/**
 * @author yang
 * @date 2019/7/2 23:42
 */
public class DynamicPrograming {

    /**
     * 01背包
     */
    public static int knapsack(int w, int[] weights, int[] values) {
        int[][] dp = new int[weights.length + 1][w + 1];
        for (int i = 1; i <= weights.length; i++) {
            int wi = weights[i - 1];
            int vi = values[i - 1];
            for (int j = 1; j <= w; j++) {
                if (wi <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wi] + vi);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[weights.length][w];
    }

    /**
     * 改变一组数的正负号使得它们的和为一给定数
     *
     * @param nums
     * @param S
     * @return
     */
    public static int findTargetSumWays(int[] nums, int S) {
        int sum = computeArraySum(nums);
        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        int W = (sum + S) / 2;
        int[] dp = new int[W + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = W; i >= num; i--) {
                dp[i] = dp[i] + dp[i - num];
            }
        }
        return dp[W];
    }

    private static int computeArraySum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    /**
     * 最长公共子列
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int lengthOfLCS(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;

        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }

    private static int minUp(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        int max = 0;
        for (int i : dp) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    /**
     * 找零钱
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0 || coins == null || coins.length == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == amount ? -1 : dp[amount];
    }

    /**
     * 找零钱总数
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        if (amount == 0 || coins == null || coins.length == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 5};
        int[] v = {6, 10, 12};
        int[] arr = {6, 10, 1, 2, 3, 12};
        DynamicPrograming dynamicPrograming = new DynamicPrograming();
        System.out.println(dynamicPrograming.coinChange(w, 16));
    }

}
