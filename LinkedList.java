public class LinkedList<T> {
    private int size = 0;

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public void add(T value) {
        size++;
        Node<T> node = new Node<T>(value);

        if (head == null) {
            head = node;
            tail = head;
            return;
        }

        tail.next = node;
        node.previous = tail;
        tail = node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        return tail.value;
    }

    public T pop() {
        if (tail == null) throw new IllegalStateException();
        T element = tail.value;
        size--;

        tail = tail.previous;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        return element;
    }

    public static void main(String[] args) {
        int[] ages = {21, 34, 20, 18, 54};
        LinkedList<Integer> list = new LinkedList<>();
        for (int age : ages) {
            list.add(age);
        }
        while (!list.isEmpty()) {
            System.out.println(list.pop());
        }
    }
}
