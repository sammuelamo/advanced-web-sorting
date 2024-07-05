package com.management.experiment.allsort;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AllSortsServlet", value = "/sort")
public class AllSortsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the base URL for constructing links
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");

        // Create the response JSON object
        JsonObject responseJson = new JsonObject();

        // Create an array for HATEOAS links
        JsonArray linksArray = new JsonArray();

        // Add self link
        linksArray.add(createLinkObject("self", baseUrl + request.getServletPath()));

        // Add quickSort link
        linksArray.add(createLinkObject("quickSort", baseUrl + "/sort/quick"));

        // Add mergeSort link
        linksArray.add(createLinkObject("mergeSort", baseUrl + "/sort/merge"));

        // Add radixSort link
        linksArray.add(createLinkObject("radixSort", baseUrl + "/sort/radix"));

        // Add heapSort link
        linksArray.add(createLinkObject("heapSort", baseUrl + "/sort/heap"));

        // Add bucketSort link
        linksArray.add(createLinkObject("bucketSort", baseUrl + "/sort/bucket"));

        // Add links array to response object
        responseJson.add("links", linksArray);

        // Set response content type to application/json
        response.setContentType("application/json");

        // Use Gson to serialize and format the JSON response
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonResponse = gson.toJson(responseJson);

        // Write the JSON response
        response.getWriter().write(jsonResponse);
    }

    // Helper method to create a JSON object for a link
    private JsonObject createLinkObject(String rel, String href) {
        JsonObject linkObject = new JsonObject();
        linkObject.addProperty("rel", rel);
        linkObject.addProperty("href", href);
        return linkObject;
    }
}
