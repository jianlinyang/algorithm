package practice;

/**
 * @author yang
 * @date 2019/7/9 14:47
 */
public class Day3 {
    private int[] tmp;

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 5, 1, 9, 7, 8, 6};
        Day3 day3 = new Day3();
        day3.mergeSort(nums);
    }

    public void mergeSort(int[] nums) {
        if (nums.length <= 1) return;
        tmp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        sort(nums, l, m);
        sort(nums, m + 1, r);
        merge(nums, l, m, r);
    }

    private void merge(int[] nums, int l, int m, int r) {
        int i = l, j = m + 1, k = l;
        while (i <= m || j <= r) {
            if (i > m) {
                tmp[k] = nums[j++];
            } else if (j > r) {
                tmp[k] = nums[i++];
            } else if (nums[i] <= nums[j]) {
                tmp[k] = nums[i++];
            } else {
                tmp[k] = nums[j++];
            }
            k++;
        }
        for (int n = l; n <= r; n++) {
            nums[n] = tmp[n];
        }
    }
}
