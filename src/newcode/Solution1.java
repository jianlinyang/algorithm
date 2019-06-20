package newcode;

import java.util.ArrayList;
import java.util.LinkedList;

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


    /**
     * 从头到尾打印链表
     *
     * @param listNode {@link ListNode}
     * @return arr
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        if (listNode == null) {
            return res;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    /**
     * 重建二叉树
     *
     * @param pre 输入前序遍历
     * @param in  输入中序遍历
     * @return {@link TreeNode}
     */

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return root;
    }

    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
                break;
            }
        }
        return root;
    }


    /**
     * 测试
     *
     * @param args args
     */
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[][] arr1 = {{1, 2, 3}, {3, 4, 5}, {5, 6, 7}};
        String s = " a b";
        ListNode listNode = new ListNode(3);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(5);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        solution1.printListFromTailToHead(listNode);

    }
}
