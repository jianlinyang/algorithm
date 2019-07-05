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

    public void selectSort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }

    public void insertSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
        }
    }

    public void xiErSort(int[] nums) {
        int len = nums.length;
        int h = 1;
        while (h < len / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && nums[j] < nums[j - h]; j -= h) {
                    swap(nums, j, j - 1);
                }
            }
            h = h / 3;
        }
    }

    //归并排序
    private int[] copy;

    public void mergeSort(int[] nums) {
        copy = new int[nums.length];
        subSort(nums, 0, nums.length - 1);
    }

    private void merge(int[] nums, int l, int m, int r) {
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++) {
            copy[k] = nums[k];//复制的辅助数组
        }
        for (int k = l; k <= r; k++) {
            if (i > m) {
                nums[k] = copy[j++];
            } else if (j > r) {
                nums[k] = copy[i++];
            } else if (copy[i] <= copy[j]) {
                nums[k] = copy[i++];
            } else {
                nums[k] = copy[j++];
            }
        }
    }

    private void subSort(int[] nums, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = l + (r - l) / 2;
        subSort(nums, l, m);
        subSort(nums, m + 1, r);
        merge(nums, l, m, r);
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
        int[] nums = {4, 3, 2, 5, 1, 9, 7, 8, 6};
        BaseSort baseSort = new BaseSort();
//        baseSort.maoPaoSort(nums);
//        baseSort.selectSort(nums);
//        baseSort.insertSort(nums);
//        baseSort.xiErSort(nums);
        baseSort.mergeSort(nums);
    }
}
