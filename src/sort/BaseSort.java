package sort;

/**
 * 基本排序算法
 *
 * @author yang
 * @date 2019/6/26 13:30
 */
public class BaseSort {
    /**
     * 冒泡排序
     *
     * @param nums input
     */
    public void maoPaoSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean flag = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    private void swap(int[] nums, int s, int e) {
        int num = nums[e];
        nums[e] = nums[s];
        nums[s] = num;
    }

    /**
     * 测试
     *
     * @param args args
     */
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 5, 1};
        BaseSort baseSort = new BaseSort();
        baseSort.maoPaoSort(nums);
    }
}
