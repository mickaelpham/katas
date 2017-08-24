import java.util.*;

public class MaxHeap {
    private static final int DEFAULT_ARRAY_SIZE = 16;

    private int[] array = new int[DEFAULT_ARRAY_SIZE];
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(int element) {
        ensureCapacity();
        array[size++] = element;
        fixUp();
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException();
        return array[0];
    }

    public int remove() {
        if (size == 0) throw new IllegalStateException();
        int element = array[0];
        array[0] = array[size-1];
        size--;
        fixDown();
        return element;
    }

    private void ensureCapacity() {
        if (array.length == size) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    // fix up, according to the min heap condition
    private void fixUp() {
        int index = size - 1;
        while (hasParent(index) && array[index] > array[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    // fix down, according to the min heap condition
    private void fixDown() {
        int index = 0;
        while (hasLeft(index)) {
            int maxChild = left(index);
            if (hasRight(index) && array[right(index)] > array[left(index)]) {
                maxChild = right(index);
            }

            if (array[index] > array[maxChild]) {
                break;
            }

            swap(index, maxChild);
            index = maxChild;
        }
    }

    // swap in the array
    private void swap(int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    // utilities to navigate the array
    public int parent(int index) { return (index - 1) / 2; }
    public int left(int index) { return index * 2 + 1; }
    public int right(int index) { return index * 2 + 2; }

    public boolean hasParent(int index) { return parent(index) >= 0; }
    public boolean hasLeft(int index) { return left(index) < size; }
    public boolean hasRight(int index) { return right(index) < size; }
}
