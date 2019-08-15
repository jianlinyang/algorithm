package code;


/**
 * @author yang
 * @date 2019/7/2 23:42
 */
public class DynamicPrograming {

    /**
     * 01背包
     *
     * @param W
     * @param N
     * @param weights
     * @param values
     * @return
     */
    public static int knapsack(int W, int N, int[] weights, int[] values) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= N; i++) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = W; j >= 1; j--) {
                if (j >= w) {
                    dp[j] = Math.max(dp[j], dp[j - w] + v);
                }
            }
        }
        return dp[W];
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


    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        int[] arr = {6, 10, 1, 2, 3, 12};
        DynamicPrograming dynamicPrograming = new DynamicPrograming();
    }

}
