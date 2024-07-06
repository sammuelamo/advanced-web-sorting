package com.management.websorting.controllers;



import com.management.websorting.utilityClass.AbstractSortServlet;
import com.management.websorting.sortingService.SortingAlgorithms;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "HeapSortServlet", value = "/sort/heap")
public class HeapSortServlet extends AbstractSortServlet {

    @Override
    protected int[] sort(int[] numbers) {
        return SortingAlgorithms.heapSort(numbers);
    }
}

