package cse2010.binary.tree;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public void prettyPrintTree(TreeNode root) {
        if (root == null) {
            return;
        }

        int[] size = new int[1];
        size[0] = 0;
        int height = height(root, size) - 1;
        int total = 2 * (int) Math.pow(2, height) - 1;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        for (int i = 0; i <= height; i++) {
            int front = (int) (Math.pow(2, (height - i))) - 1;
            int num = (int) (Math.pow(2, i));
            int interval = num > 1 ? (total - front * 2 - num) / (num - 1) : 0;

            for (int j = 0; j < num; j++) {
                TreeNode peek = queue.poll();

                if (j == 0) {
                    print(front, size, peek);
                } else {
                    print(interval, size, peek);
                }

                if (peek == null) {
                    queue.offer(null);
                    queue.offer(null);
                } else {
                    queue.offer(peek.left);
                    queue.offer(peek.right);
                }
            }

            System.out.println();
        }
    }

    private int height(TreeNode root, int[] size) {
        if (root == null) {
            return 0;
        }

        size[0] = Math.max(size[0], Integer.toString(root.val).length());
        return Math.max(height(root.left, size), height(root.right, size)) + 1;
    }

    private void print(int count, int[] size, TreeNode root) {
        for (int i = 0; i < count * size[0]; i++) {
            System.out.print(" ");
        }

        if (root != null) {
            for (int j = 0; j < size[0] - Integer.toString(root.val).length(); j++) {
                System.out.print(" ");
            }

            System.out.print(root.val);
        } else {
            for (int j = 0; j < size[0]; j++) {
                System.out.print(" ");
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode a = new TreeNode(30);
        TreeNode b = new TreeNode(20);
        TreeNode c = new TreeNode(40);
        TreeNode d = new TreeNode(10);
        TreeNode e = new TreeNode(25);
        TreeNode f = new TreeNode(35);
        TreeNode g = new TreeNode(50);
        TreeNode h = new TreeNode(5);
        TreeNode i = new TreeNode(15);
        TreeNode j = new TreeNode(28);
        TreeNode k = new TreeNode(41);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        d.left = h;
        d.right = i;
        e.right = j;
        g.left = k;

        s.prettyPrintTree(a);
    }
}
