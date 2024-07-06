package com.management.experiment.controllers;



import com.management.experiment.utilityClass.AbstractSortServlet;
import com.management.experiment.sortingService.SortingAlgorithms;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "QuickSortServlet", value = "/sort/quick")
public class QuickSortServlet extends AbstractSortServlet {

    @Override
    protected int[] sort(int[] numbers) {
        return SortingAlgorithms.quickSort(numbers, 0, numbers.length - 1);
    }
}

