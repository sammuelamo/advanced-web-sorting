package com.management.experiment.utilityClass;


import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractSortServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int[] numbers = SortingUtility.extractNumbersFromRequest(request);
            int[] sortedNumbers = sort(numbers);

            JsonObject responseJson = SortingUtility.createResponseJson(sortedNumbers, request);
            response.setContentType("application/json");
            response.getWriter().write(responseJson.toString());
        } catch (NumberFormatException e) {
            SortingUtility.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid numbers format. Please provide comma-separated integers.");
        } catch (Exception e) {
            SortingUtility.writeErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error occurred.");
        }
    }

    protected abstract int[] sort(int[] numbers);
}

