package practice;

import code.TreeNode;

import java.util.Stack;

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
        TreeProblem.after(treeNode1);
    }
}
