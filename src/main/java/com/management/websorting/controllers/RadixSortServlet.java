package com.management.websorting.controllers;



import com.management.websorting.utilityClass.AbstractSortServlet;
import com.management.websorting.sortingService.SortingAlgorithms;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "RadixSortServlet", value = "/sort/radix")
public class RadixSortServlet extends AbstractSortServlet {

    @Override
    protected int[] sort(int[] numbers) {
        return SortingAlgorithms.radixsort(numbers, numbers.length);
    }
}

