package com.management.experiment.controllers;



import com.management.experiment.utilityClass.AbstractSortServlet;
import com.management.experiment.sortingService.SortingAlgorithms;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "MergeSortServlet", value = "/sort/merge")
public class MergeSortServlet extends AbstractSortServlet {

    @Override
    protected int[] sort(int[] numbers) {
        return SortingAlgorithms.mergeSort(numbers);
    }
}

