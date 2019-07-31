package code;

import java.util.Arrays;

/**
 * 构建堆
 *
 * @author yang
 * @date 2019/7/18 10:19
 */
public class HeapOperator {
    /**
     * 调整
     *
     * @param array
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉
     *
     * @param array
     * @param parentIndex
     * @param length
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex ;
        while (childIndex < length) {
            // 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            // 如果父节点小于任何一个孩子的值，直接跳出
            if (temp >= array[childIndex]) {
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex ;
        }
        array[parentIndex] = temp;
    }

    public static void heapSort(int[] array) {
        //构建堆
        for (int i = array.length / 2; i >= 0; i--) {
            downAdjust(array, i, array.length - 1);
        }
        for (int i = array.length - 1; i > 0; i--) {
            int i1 = array[i];
            array[i] = array[0];
            array[0] = i1;
            //下沉调整
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{7, 3, 5, 4, 6, 9, 1, 8, 2};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
