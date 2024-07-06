package com.management.websorting.utilityClass;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

public class SortingUtility {

    public static int[] extractNumbersFromRequest(HttpServletRequest request) throws IOException, NumberFormatException {
        String requestBody = new String(request.getInputStream().readAllBytes());
        String numbersStr = requestBody.split(":")[1].replace("\"", "").replace("}", "").trim();
        return Arrays.stream(numbersStr.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static JsonObject createResponseJson(int[] sortedNumbers, HttpServletRequest request) {
        JsonObject responseJson = new JsonObject();
        JsonArray sortedArray = new JsonArray();
        for (int num : sortedNumbers) {
            sortedArray.add(num);
        }
        responseJson.add("sortedNumbers", sortedArray);
        responseJson.add("links", createHateoasLinks(request));
        return responseJson;
    }

    private static JsonArray createHateoasLinks(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;

        JsonArray linksArray = new JsonArray();
        linksArray.add(createLink("self", request.getRequestURL().toString()));
        linksArray.add(createLink("allSorts", basePath + "/sort"));
        linksArray.add(createLink("quickSort", basePath + "/sort/quick"));
        linksArray.add(createLink("mergeSort", basePath + "/sort/merge"));
        linksArray.add(createLink("radixSort", basePath + "/sort/radix"));
        linksArray.add(createLink("heapSort", basePath + "/sort/heap"));
        linksArray.add(createLink("bucketSort", basePath + "/sort/bucket"));

        return linksArray;
    }

    private static JsonObject createLink(String rel, String href) {
        JsonObject link = new JsonObject();
        link.addProperty("rel", rel);
        link.addProperty("href", href);
        return link;
    }

    public static void writeErrorResponse(HttpServletResponse response, int statusCode, String errorMessage) throws IOException {
        JsonObject errorJson = new JsonObject();
        errorJson.addProperty("error", errorMessage);
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.getWriter().write(errorJson.toString());
    }
}
