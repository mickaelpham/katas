import java.util.*;

public class BinarySearchTree {
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }

        void insert(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new Node(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new Node(value);
                } else {
                    right.insert(value);
                }
            }
        }
    }

    private Node root;

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        root.insert(value);
    }


    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node x) {
        if (x == null) return;
        printInOrder(x.left);
        System.out.println(x.value);
        printInOrder(x.right);
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node x) {
        if (x == null) return;
        System.out.println(x.value);
        printPreOrder(x.left);
        printPreOrder(x.right);
    }

    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(Node x) {
        if (x == null) return;
        printPostOrder(x.left);
        printPostOrder(x.right);
        System.out.println(x.value);
    }

    public void printLevelOrder() {
        if (root == null) return;
        Queue<Node> nodesToPrint = new java.util.LinkedList<>();
        nodesToPrint.add(root);

        while (!nodesToPrint.isEmpty()) {
            Node x = nodesToPrint.poll();
            System.out.println(x.value);
            if (x.left != null) {
                nodesToPrint.offer(x.left);
            }
            if (x.right != null) {
                nodesToPrint.offer(x.right);
            }
        }
    }

    public static void main(String[] args) {
        int[] ages = {21, 34, 20, 18, 54};
        BinarySearchTree tree = new BinarySearchTree();
        for (int age : ages) {
            tree.insert(age);
        }
        System.out.println("in order:");
        tree.printInOrder();
        System.out.println("pre order:");
        tree.printPreOrder();
        System.out.println("post order:");
        tree.printPostOrder();
        System.out.println("level order:");
        tree.printLevelOrder();
    }
}
