package practice;

import code.TreeNode;

import java.util.*;

/**
 * @author yang
 * @date 2019/7/8 21:11
 */
public class TreeProblem {
    public static void pre(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.println(pop.val);
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
    }

    public static void in(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root.left;
        stack.push(root);
        while (!stack.isEmpty() || cur != null) {
            if (cur == null) {
                TreeNode pop = stack.pop();
                System.out.println(pop.val);
                cur = pop.right;
            } else {
                stack.push(cur);
                cur = cur.left;
            }
        }
    }

    public static void after(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode pop = stack1.pop();
            stack2.push(pop);
            if (pop.left != null) stack1.push(pop.left);
            if (pop.right != null) stack1.push(pop.right);
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val);
        }
    }

    /**
     * 二叉搜索树第K个节点
     *
     * @param pRoot
     * @param k
     * @return
     */
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k == 0) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = pRoot;
        int count = 0;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                count++;
                TreeNode pop = stack.pop();
                if (count == k) return pop;
                cur = pop.right;
            }
        }
        return null;
    }

    //序列化
    String Serialize(TreeNode root) {
        if (root == null) return null;
        StringBuilder stringBuilder = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll != null) {
                stringBuilder.append(poll.val + ",");
                queue.offer(poll.left);
                queue.offer(poll.right);
            } else {
                stringBuilder.append("#,");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) return null;
        String[] split = str.split(",");
        TreeNode[] treeNodes = new TreeNode[split.length];
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals("#")) treeNodes[i] = new TreeNode(Integer.valueOf(split[i]));
        }
        for (int i = 0, j = 1; j < split.length; i++) {
            if (treeNodes[i] != null) {
                treeNodes[i].left = treeNodes[j++];
                treeNodes[i].right = treeNodes[j++];
            }
        }
        return treeNodes[0];
    }

    //层次遍历
    ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            while (size > 0) {
                TreeNode poll = queue.poll();
                size--;
                if (poll != null) {
                    tmp.add(poll.val);
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            if (tmp.size() != 0) res.add(tmp);
        }
        return res;
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> ret = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (t == null) continue;
            ret.add(t.val);
            queue.add(t.left);
            queue.add(t.right);
        }
        return ret;
    }

    //深度
    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode poll = queue.poll();
                size--;
                if (poll != null) {
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            res++;
        }
        return res - 1;
    }

    //之字遍历
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean reverse = false;
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            while (size > 0) {
                TreeNode poll = queue.poll();
                size--;
                if (poll != null) {
                    tmp.add(poll.val);
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            if (tmp.size() != 0) {
                if (reverse) {
                    Collections.reverse(tmp);
                }
                res.add(tmp);
            }
            reverse = !reverse;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
//        Day2_2.pre(treeNode1);
//        Day2_2.in(treeNode1);
//        TreeProblem.after(treeNode1);

    }
}
