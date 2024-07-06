package com.management.websorting.controllers;



import com.management.websorting.utilityClass.AbstractSortServlet;
import com.management.websorting.sortingService.SortingAlgorithms;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "QuickSortServlet", value = "/sort/quick")
public class QuickSortServlet extends AbstractSortServlet {

    @Override
    protected int[] sort(int[] numbers) {
        return SortingAlgorithms.quickSort(numbers, 0, numbers.length - 1);
    }
}

