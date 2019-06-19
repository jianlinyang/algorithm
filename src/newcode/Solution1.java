package newcode;

/**
 * @author yang
 * @date 2019/6/19 23:27
 * 剑指offer 1-22
 */
public class Solution1 {
    /**
     * 二维数组中的查找
     *
     * @param target 查找对象
     * @param array  被查找数组
     * @return true/false
     */
    public boolean find(int target, int[][] array) {
        int deep = array.length - 1;
        int length = array[0].length - 1;
        int i = 0;
        while (i <= length && deep >= 0) {
            if (target == array[deep][i]) {
                return true;
            }
            if (target > array[deep][i]) {
                i++;
            } else {
                deep--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1, 2, 3}, {3, 4, 5}, {5, 6, 7}};
        Solution1 solution1 = new Solution1();
        boolean b = solution1.find(3, arr1);
        System.out.println(b);
    }
}
