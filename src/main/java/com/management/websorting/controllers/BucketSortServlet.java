package com.management.websorting.controllers;

import com.management.websorting.utilityClass.AbstractSortServlet;
import com.management.websorting.sortingService.SortingAlgorithms;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "BucketSortServlet", value = "/sort/bucket")
public class BucketSortServlet extends AbstractSortServlet {

    @Override
    protected int[] sort(int[] numbers) {
        int bucketSize = numbers.length;
        return SortingAlgorithms.bucketSort(numbers, bucketSize);
    }
}

