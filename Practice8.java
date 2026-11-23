public class Practice8 {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        int[] values = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (int v : values) {
            tree.add(v);
        }

        System.out.println("Початковий стан дерева");
        tree.printAll();
        tree.printCount();
        tree.printMin();
        tree.printMax();

        System.out.println("\n Перевірка пошуку");
        System.out.println("Чи є елемент 6? " + tree.contains(6));
        System.out.println("Чи є елемент 99? " + tree.contains(99));

        System.out.println("\n Видалення елементів");
        System.out.println("Видаляємо 3 (два нащадки):");
        tree.delete(3);
        tree.printAll();

        System.out.println("Видаляємо 14 (один нащадок):");
        tree.delete(14);
        tree.printAll();

        System.out.println("Видаляємо 1 (немає нащадків):");
        tree.delete(1);
        tree.printAll();

        System.out.println("\n=== Стан після видалень ===");
        tree.printCount();
        tree.printMin();
        tree.printMax();
    }
}


class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = deleteRecursive(current.right, value);
        } else {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
        }

        return current;
    }

    private int findSmallestValue(Node node) {
        return node.left == null ? node.value : findSmallestValue(node.left);
    }

    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsRecursive(current.left, value)
                : containsRecursive(current.right, value);
    }

    public void printAll() {
        if (root == null) {
            System.out.println("Дерево порожнє.");
            return;
        }
        System.out.print("Елементи дерева: ");
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.value + " ");
            printInOrder(node.right);
        }
    }

    public void printMax() {
        if (root == null) {
            System.out.println("Дерево порожнє, найбільший елемент відсутній.");
            return;
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        System.out.println("Найбільший елемент: " + current.value);
    }

    public void printMin() {
        if (root == null) {
            System.out.println("Дерево порожнє, найменший елемент відсутній.");
            return;
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        System.out.println("Найменший елемент: " + current.value);
    }

    public void printCount() {
        System.out.println("Кількість елементів у дереві: " + countRecursive(root));
    }

    private int countRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countRecursive(node.left) + countRecursive(node.right);
    }
}

