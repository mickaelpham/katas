import java.util.*;

public class MinHeap {
    private static final int DEFAULT_ARRAY_SIZE = 16;

    private int[] array = new int[DEFAULT_ARRAY_SIZE];
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int value) {
        ensureSpace();
        array[size++] = value;
        fixUp(); // fix the heap going up, following min heap condition
    }

    private void ensureSpace() {
        if (array.length == size) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException();
        return array[0];
    }

    public int remove() {
        if (size == 0) throw new IllegalStateException();
        int value = array[0];
        array[0] = array[size-1]; // last inserted element
        size--;
        fixDown(); // fix the heap going down, following min heap condition
        return value;
    }

    private void fixUp() {
        int index = size - 1; // last inserted element
        while (hasParent(index) && array[index] < array[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void fixDown() {
        int index = 0;
        while (hasLeft(index)) {
            int minChild = left(index);
            if (hasRight(index) && array[right(index)] < array[left(index)]) {
                minChild = right(index);
            }

            if (array[minChild] > array[index]) {
                break;
            }

            swap(index, minChild);
            index = minChild;
        }
    }

    private void swap(int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    // utilities method
    private int parent(int index) {
        return (index - 1) / 2;
    }

    private boolean hasParent(int index) {
        return parent(index) >= 0;
    }

    private int left(int index) {
        return index * 2 + 1;
    }

    private boolean hasLeft(int index) {
        return left(index) < size;
    }

    private int right(int index) {
        return index * 2 + 2;
    }

    private boolean hasRight(int index) {
        return right(index) < size;
    }

    public static void main(String[] args) {
        int[] input = {5, 4, 7, 3, 9, 2, 1};
        MinHeap minHeap = new MinHeap();
        for (int i = 0; i < input.length; i++) {
            minHeap.add(input[i]);
        }

        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.remove());
        }
    }
}
