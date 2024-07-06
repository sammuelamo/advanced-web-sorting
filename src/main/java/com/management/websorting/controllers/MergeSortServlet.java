package com.management.websorting.controllers;



import com.management.websorting.utilityClass.AbstractSortServlet;
import com.management.websorting.sortingService.SortingAlgorithms;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "MergeSortServlet", value = "/sort/merge")
public class MergeSortServlet extends AbstractSortServlet {

    @Override
    protected int[] sort(int[] numbers) {
        return SortingAlgorithms.mergeSort(numbers);
    }
}

