package com.management.experiment.controllers;



import com.management.experiment.utilityClass.AbstractSortServlet;
import com.management.experiment.sortingService.SortingAlgorithms;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "RadixSortServlet", value = "/sort/radix")
public class RadixSortServlet extends AbstractSortServlet {

    @Override
    protected int[] sort(int[] numbers) {
        return SortingAlgorithms.radixsort(numbers, numbers.length);
    }
}

