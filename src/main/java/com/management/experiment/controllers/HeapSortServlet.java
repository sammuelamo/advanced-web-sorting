package com.management.experiment.controllers;



import com.management.experiment.utilityClass.AbstractSortServlet;
import com.management.experiment.sortingService.SortingAlgorithms;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "HeapSortServlet", value = "/sort/heap")
public class HeapSortServlet extends AbstractSortServlet {

    @Override
    protected int[] sort(int[] numbers) {
        return SortingAlgorithms.heapSort(numbers);
    }
}

