package stub2021;

import code.ListNode;

import java.util.Arrays;

/**
 * @author YangJianlin
 * @date 2021/3/22 9:41
 */
public class Link {
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) {
//        testReverseLinkList();
//        testPalindrome();
//        testPrintArray(6);
//        testMergeLinkList();
//        testChangeArr();
        testMaxSumOfArr();
    }

    private static void testMaxSumOfArr() {
        int[] arr = {1, 3, -4, 2, 5, 7, -8, 9};
        int res = arr[0];
        int max = res;
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max + arr[i]);
            res = Math.max(max, res);
        }
        System.out.println(res);
    }

    private static void testChangeArr() {
        int[] arr = {1, 3, 4, 2, 5, 7, 8, 9};
        changeArr(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void changeArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (isEven(arr[i])) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (isOdd(arr[j])) {
                        int tmp = arr[j];
                        for (int k = j; k > i; k--) {
                            swap(arr, k, k - 1);
                        }
                        arr[i] = tmp;
                        break;
                    }
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static boolean isEven(int i) {
        return i % 2 == 0;
    }

    private static boolean isOdd(int i) {
        return !isEven(i);
    }

    private static void testMergeLinkList() {
        ListNode listNode1 = new ListNode(10);
        ListNode listNode2 = new ListNode(20);
        ListNode listNode3 = new ListNode(30);
        ListNode listNode4 = new ListNode(40);
        ListNode listNode5 = new ListNode(11);
        ListNode listNode6 = new ListNode(22);
        ListNode listNode7 = new ListNode(30);
        ListNode listNode8 = new ListNode(31);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
//        ListNode listNode = mergeLinkList(listNode1, listNode5);
        System.out.println(countListNode(listNode1, 4));
    }

    private static int countListNode(ListNode listNode, int k) {
        int count = 1;
        while (listNode != null) {
            if (count++ == k) {
                return listNode.val;
            }
            listNode = listNode.next;
        }
        return -1;
    }

    private static ListNode mergeLinkList(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode res = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                res.next = list1;
                list1 = list1.next;
                res = res.next;
            } else {
                res.next = list2;
                list2 = list2.next;
                res = res.next;
            }
        }
        if (list1 == null) {
            res.next = list2;
        }
        if (list2 == null) {
            res.next = list1;
        }
        return head.next;
    }

    private static void testPrintArray(int size) {
        int[][] arr = new int[size][size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = count++;
            }
        }
        printArray(arr, 0, arr[0].length, 0, arr.length);
    }

    private static void printArray(int[][] arr, int l, int r, int t, int b) {
        while (r > l || b > t) {
            for (int i = l; i < r; i++) {
                System.out.println(arr[t][i]);
            }
            for (int i = t + 1; i < b; i++) {
                System.out.println(arr[i][r - 1]);
            }
            if (l != r - 1 && r - 2 > 0) {
                for (int i = r - 2; i >= l; i--) {
                    System.out.println(arr[b - 1][i]);
                }
            }
            if (t != b - 1 && b - 2 > 0) {
                for (int i = b - 2; i >= t + 1; i--) {
                    System.out.println(arr[i][l]);
                }
            }
            r--;
            l++;
            b--;
            t++;
        }
    }

    private static void testPalindrome() {
        String s = "abba";
        char[] chars = s.toCharArray();
        palindrome(chars);
    }

    private static void palindrome(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            palindrome(chars, i, i);
            palindrome(chars, i, i + 1);
        }
        System.out.println(res.toString());
    }

    private static void palindrome(char[] chars, int i, int j) {
        while (i >= 0 && j < chars.length) {
            if (chars[i] == chars[j]) {
                if (j - i > res.length()) {
                    res = new StringBuilder();
                    for (int k = i; k <= j; k++) {
                        res.append(chars[k]);
                    }
                }
                i--;
                j++;
            } else {
                return;
            }
        }
    }

    private static void testReverseLinkList() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        reverseListNode(listNode1);
        while (listNode4 != null) {
            System.out.println(listNode4.val);
            listNode4 = listNode4.next;
        }
    }

    private static void reverseListNode(ListNode root) {
        if (root == null) {
            return;
        }
        ListNode cur = root;
        ListNode pre = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
    }
}
