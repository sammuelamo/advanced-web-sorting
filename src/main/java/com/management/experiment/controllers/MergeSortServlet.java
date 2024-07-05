package com.management.experiment.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.management.experiment.sortingService.SortingAlgorithms;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "MergeSortServlet", value = "/sort/merge")
public class MergeSortServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Read the request body
            String requestBody = new String(request.getInputStream().readAllBytes());

            // Extract the numbers from the request body
            String numbersStr = requestBody.split(":")[1].replace("\"", "").replace("}", "").trim();

            // Convert the numbers string to an array
            int[] numbers = Arrays.stream(numbersStr.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // Sort the array using Merge Sort
            int[] sortedNumbers = SortingAlgorithms.mergeSort(numbers);

            // Prepare JSON response
            JsonObject responseJson = new JsonObject();
            JsonArray sortedArray = new JsonArray();
            for (int num : sortedNumbers) {
                sortedArray.add(num);
            }
            responseJson.add("sortedNumbers", sortedArray);

            // Get the context path
            String contextPath = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;

            // Add HATEOAS links
            JsonArray linksArray = new JsonArray();
            JsonObject selfLink = new JsonObject();
            selfLink.addProperty("rel", "self");
            selfLink.addProperty("href", request.getRequestURL().toString());
            linksArray.add(selfLink);

            JsonObject allSortsLink = new JsonObject();
            allSortsLink.addProperty("rel", "allSorts");
            allSortsLink.addProperty("href", basePath + "/sort");
            linksArray.add(allSortsLink);

            JsonObject quickSortLink = new JsonObject();
            quickSortLink.addProperty("rel", "quickSort");
            quickSortLink.addProperty("href", basePath + "/sort/quick");
            linksArray.add(quickSortLink);

            JsonObject mergeSortLink = new JsonObject();
            mergeSortLink.addProperty("rel", "mergeSort");
            mergeSortLink.addProperty("href", basePath + "/sort/merge");
            linksArray.add(mergeSortLink);

            // Add more links as needed
            responseJson.add("links", linksArray);

            // Set response content type to application/json and write the response
            response.setContentType("application/json");
            response.getWriter().write(responseJson.toString());

        } catch (NumberFormatException e) {
            // Handle if the numbers in the request are not valid integers
            JsonObject errorJson = new JsonObject();
            errorJson.addProperty("error", "Invalid numbers format. Please provide comma-separated integers.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write(errorJson.toString());

        } catch (Exception e) {
            // Handle other unexpected exceptions
            JsonObject errorJson = new JsonObject();
            errorJson.addProperty("error", "Internal server error occurred.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.getWriter().write(errorJson.toString());
        }
    }
}
