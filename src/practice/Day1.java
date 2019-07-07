package practice;

/**
 * @author yang
 * @date 2019/7/7 9:15
 */
public class Day1 {
    public void quickSort(int[] nums, int l, int r) {
        if (l > r) return;
        int i = l, j = r;
        int tmp = nums[l];
        while (i < j) {
            while (i < j && nums[j] >= tmp) {
                j--;
            }
            while (i < j && nums[i] <= tmp) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        nums[l] = nums[i];
        nums[i] = tmp;
        quickSort(nums, l, i - 1);
        quickSort(nums, i + 1, r);
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 5, 1, 9, 7, 8, 6};
        Day1 day1 = new Day1();
        day1.quickSort(nums, 0, nums.length - 1);
    }
}
