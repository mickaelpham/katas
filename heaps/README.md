# Heaps

> In computer science, a heap is a specialized tree-based data structure that satisfies the heap property.

## Min heap

If P is a parent node of C, then the key (the value) of node P is **less** than the key of node C.

## Max heap

If P is a parent node of C, then the key (the value) of node P is **greater** than the key of node C.

## Running Median

1. Create a `min` and a `max` heap
2. If the element to add is _greater_ than the _minimum_ of the biggest half elements, add it to the `min` heap. Else add it to the `max` heap.
3. If the size of one heap is bigger than the other by **more than 1** element, balance the heaps.

The median is calculated as:

* if the heaps are of _different_ size, then the median is the **root** of the heap with the biggest size.
* if the heaps are the _same_ size, then the median is equals to the _sum_ of both heap roots divided by 2.
