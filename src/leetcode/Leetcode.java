package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author yang
 * @date 2019/8/15 12:25
 */
public class Leetcode {
    /**
     * 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        int length = s.length();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            int tmp = subLongestPalindrome(s, i, i);
            int tmp2 = subLongestPalindrome(s, i, i + 1);
            int max = Math.max(tmp, tmp2);
            if (max > end - start) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return s.substring(start, end + 1);
    }


    private int subLongestPalindrome(String s, int l, int r) {
        while (true) {
            if (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            } else {
                break;
            }
        }
        return r - l - 1;
    }

    /**
     * 最长有效括号
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        int res = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(i);
            } else {
                if (stack.peek() != -1 && s.charAt(stack.peek()) == map.get(s.charAt(i))) {
                    stack.pop();
                } else {
                    stack.push(i);
                }
                res = Math.max(res, i - stack.peek());
            }
        }
        return res;
    }

    /**
     * 不同路径
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 || j == 0) {
                        if (i == 0 && j == 0) continue;
                        dp[i][j] = i == 0 ? dp[i][j - 1] : dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                    }
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 最小路径和
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0) {
                    if (i == 0 && j == 0) continue;
                    dp[i][j] = i == 0 ? dp[i][j - 1] + grid[i][j] : dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 柱状图中最大面积
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                area = Math.max(area, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            area = Math.max(area, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return area;
    }

    /**
     * 最大矩形
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int max = 0;
        int[] dp = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dp[j] = matrix[i][j] == 1 ? dp[j] + 1 : 0;
            }
            max = Math.max(max, largestRectangleArea(dp));
        }
        return max;
    }

    /**
     * 交错字符串
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * 不同的子序列
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[t.length()][s.length()];
    }

    /**
     * 三角形最小路径和
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int count = 0;
        for (List<Integer> list : triangle) {
            count += list.size();
        }
        int[] dp = new int[count];
        int index = 0;
        count = 0;
        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (i == triangle.size() - 1) {
                    dp[count++] = list.get(j);
                } else {
                    dp[count++] = list.get(j) + Math.min(dp[j + index], dp[j + index + 1]);
                }
            }
            index = count - list.size();
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        Leetcode leetcode = new Leetcode();
        int[][] arr = {{1, 0, 1, 0, 0}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}};
//        int[] arr = {2, 1, 5, 6, 2, 3};
        List<List<Integer>> in = new ArrayList<>();
        List<Integer> tmp1 = new ArrayList<>();
        tmp1.add(2);
        List<Integer> tmp2 = new ArrayList<>();
        tmp2.add(3);
        tmp2.add(4);
        List<Integer> tmp3 = new ArrayList<>();
        tmp3.add(6);
        tmp3.add(5);
        tmp3.add(7);
        in.add(tmp1);
        in.add(tmp2);
        in.add(tmp3);
        leetcode.minimumTotal(in);
        System.out.println(leetcode.maximalRectangle(arr));
    }
}
