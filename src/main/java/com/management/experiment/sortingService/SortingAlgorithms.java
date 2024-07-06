package com.management.experiment.sortingService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortingAlgorithms {
    // Merge sort algorithm
    public static int[] mergeSort(int[] numbers) {
        if (numbers.length <= 1) {
            return numbers;
        }
        int midPoint = numbers.length / 2;
        int[] leftHalf = new int[midPoint];
        int[] rightHalf = new int[numbers.length - midPoint];

        for (int i = 0; i < midPoint; i++) {
            leftHalf[i] = numbers[i];
        }

        for (int i = midPoint; i < numbers.length; i++) {
            rightHalf[i - midPoint] = numbers[i];
        }
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        return merge(numbers, leftHalf, rightHalf);
    }

    private static int[] merge(int[] numbers, int[] leftHalf, int[] rightHalf) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i] <= rightHalf[j]) {
                numbers[k] = leftHalf[i];
                i++;
            } else {
                numbers[k] = rightHalf[j];
                j++;
            }
            k++;
        }
        while (i < leftHalf.length) {
            numbers[k] = leftHalf[i];
            k++;
            i++;
        }
        while (j < rightHalf.length) {
            numbers[k] = rightHalf[j];
            k++;
            j++;
        }
        return numbers;
    }

    // Quick sort algorithm
    public static int[] quickSort(int[] numbers, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int pivotIndex = partition(numbers, lowIndex, highIndex);
            quickSort(numbers, lowIndex, pivotIndex - 1);
            quickSort(numbers, pivotIndex + 1, highIndex);
        }
        return numbers;
    }

    private static int partition(int[] numbers, int lowIndex, int highIndex) {
        int pivot = numbers[highIndex];
        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        while (leftPointer <= rightPointer) {
            while (leftPointer <= rightPointer && numbers[leftPointer] <= pivot) {
                leftPointer++;
            }
            while (leftPointer <= rightPointer && numbers[rightPointer] >= pivot) {
                rightPointer--;
            }
            if (leftPointer < rightPointer) {
                int temp = numbers[leftPointer];
                numbers[leftPointer] = numbers[rightPointer];
                numbers[rightPointer] = temp;
            }
        }

        int temp = numbers[leftPointer];
        numbers[leftPointer] = numbers[highIndex];
        numbers[highIndex] = temp;

        return leftPointer;
    }
    public static int[] heapSort(int[] arr) {
        int[] sortedArray = Arrays.copyOf(arr, arr.length);
        int n = sortedArray.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(sortedArray, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = sortedArray[0];
            sortedArray[0] = sortedArray[i];
            sortedArray[i] = temp;

            // call max heapify on the reduced heap
            heapify(sortedArray, i, 0);
        }

        return sortedArray;
    }

    // To heapify a subtree rooted with node i which is an index in arr[]
    static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    static int[] countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;


        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        return output;
    }

    // The main function to that sorts arr[] of
    // size n using Radix Sort
    public static int[] radixsort(int arr[], int n) {
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);

        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10)
            arr = countSort(arr, n, exp);

        return arr;
    }

    // Bucket Sort
    public static int[] bucketSort(int[] array, int bucketSize) {
        if (array.length == 0) {
            return array;
        }

        // Determine minimum and maximum values in the array
        int minValue = array[0];
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        // Calculate number of buckets needed
        int numBuckets = (maxValue - minValue) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        // Assign elements into buckets
        for (int i = 0; i < array.length; i++) {
            int bucketIndex = (array[i] - minValue) / bucketSize;
            buckets.get(bucketIndex).add(array[i]);
        }

        // Sort each bucket and place back into array
        int index = 0;
        for (int i = 0; i < numBuckets; i++) {
            Collections.sort(buckets.get(i));
            for (int j = 0; j < buckets.get(i).size(); j++) {
                array[index++] = buckets.get(i).get(j);
            }
        }

        return array;
    }

}
