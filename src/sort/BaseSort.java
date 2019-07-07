package sort;

import java.util.*;

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

    /**
     * 选择排序
     *
     * @param nums
     */
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

    /**
     * 插入排序
     *
     * @param nums
     */
    public void insertSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param nums
     */
    public void xiErSort(int[] nums) {
        int len = nums.length;
        int h = 1;
        while (h < len / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && nums[j] < nums[j - h]; j -= h) {
                    swap(nums, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    /**
     * 归并排序
     *
     * @param a
     * @param low
     * @param high
     */
    public void mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            //左右归并
            merge(a, low, mid, high);
        }
    }

    public void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }

    private void swap(int[] nums, int s, int e) {
        int num = nums[e];
        nums[e] = nums[s];
        nums[s] = num;
    }

    /**
     * 快速排序
     *
     * @param arr  target
     * @param low  low
     * @param high high
     */
    public void quickSort(int[] arr, int low, int high) {
        int i, j, tmp;
        if (low > high) return;
        i = low;
        j = high;
        tmp = arr[low]; //基准位
        while (i < j) {
            while (tmp <= arr[j] && i < j) {//右边哨兵往左走
                j--;
            }
            while (tmp >= arr[i] && i < j) {//左边哨兵往右走
                i++;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        //交换基准位
        arr[low] = arr[i];
        arr[i] = tmp;
        //递归二分
        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);
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
        baseSort.mergeSort(nums, 0, nums.length - 1);
//        baseSort.quickSort(nums, 0, nums.length - 1);


    }
}
