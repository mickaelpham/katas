import java.util.Random;

public class RunningMedian {
    // Keep track of the maximum of the lowest half elements
    private MaxHeap maxHeap = new MaxHeap();

    // Keep track of the minimum of the highest half elements
    private MinHeap minHeap = new MinHeap();

    public int size() {
        return maxHeap.size() + minHeap.size();
    }

    public void add(int element) {
        if (size() == 0) {
            maxHeap.add(element);
        } else if (size() == 1) {
            minHeap.add(element);
        } else if (element > minHeap.peek()) {
            minHeap.add(element);
        } else {
            maxHeap.add(element);
        }

        // balance the heaps if needed
        if (Math.abs(minHeap.size() - maxHeap.size()) > 1) {
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.remove());
            } else {
                minHeap.add(maxHeap.remove());
            }
        }
    }

    // Get the running median
    public double get() {
        double median = 0;

        if (size() == 0) {
            median = 0;
        } else if (size() == 1) {
            median = maxHeap.peek();
        } else if (size() % 2 == 0) {
            median = (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else {
            if (minHeap.size() > maxHeap.size()) {
                median = minHeap.peek();
            } else {
                median = maxHeap.peek();
            }
        }

        return median;
    }

    // MAIN loop
    // private static final int MAX_ELEMENT = 1000000;
    // private static final int MAX_RANDOM = 1000;

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        RunningMedian rm = new RunningMedian();
        System.out.println("median = " + rm.get());
        for (int i : input) {
            rm.add(i);
            System.out.println("median = " + rm.get());
        }
        //
        // Random rand = new Random();
        // RunningMedian runningMedian = new RunningMedian();
        // for (int i = 0; i < MAX_ELEMENT; i++) {
        //     int element = rand.nextInt(MAX_RANDOM);
        //     // System.out.println("--- adding: " + element);
        //     runningMedian.add(element);
        //     System.out.println(runningMedian.get());
        // }
    }
}
