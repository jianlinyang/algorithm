package newcode;

/**
 * @author yang
 * @date 2019/6/19 23:27
 * 剑指offer 1-22
 */
public class Solution1 {
    /**
     * 1二维数组中的查找
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

    /**
     * 2.替换空格
     *
     * @param str 输入字符串
     * @return result
     */
    public String replaceSpace(StringBuffer str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (' ' == str.charAt(i)) {
                str.replace(i, i + 1, "%20");
                length = length + 2;
            }
            i++;
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[][] arr1 = {{1, 2, 3}, {3, 4, 5}, {5, 6, 7}};
        String s = " a b";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s);
        stringBuffer.replace(0,1,"%20");
        System.out.println(stringBuffer);
    }
}
