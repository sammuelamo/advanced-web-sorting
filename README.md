# Sorting Algorithms Web Application

This Java web application demonstrates various sorting algorithms implemented as servlets. Each servlet sorts a list of numbers and returns the sorted result in JSON format, adhering to HATEOAS principles by providing navigational links.

## Features

- **QuickSortServlet**: Sorts numbers using the Quick Sort algorithm.
- **MergeSortServlet**: Sorts numbers using the Merge Sort algorithm.
- **RadixSortServlet**: Sorts numbers using the Radix Sort algorithm.
- **HeapSortServlet**: Sorts numbers using the Heap Sort algorithm.
- **BucketSortServlet**: Sorts numbers using the Bucket Sort algorithm.
- **AllSortsServlet**: Provides links to all sorting servlets, facilitating easy navigation and discovery of sorting functionalities.

## Usage

1. **Deployment**: Deploy the application on a servlet container (e.g., Apache Tomcat).

2. **Accessing Endpoints**:
    - Each sorting algorithm is accessible via its respective endpoint:
        - Quick Sort: `/sort/quick`
        - Merge Sort: `/sort/merge`
        - Radix Sort: `/sort/radix`
        - Heap Sort: `/sort/heap`
        - Bucket Sort: `/sort/bucket`
    - The main endpoint `/sort` provides links to all sorting algorithms.

3. **Request Format**:
    - Send a POST request to any sorting endpoint (`/sort/quick`, `/sort/merge`, etc.) with a JSON body containing an array of numbers to be sorted.
    - Example JSON body:

      ```json
      {
        "numbers": "5,2,8,1,7,3"
      }
      ```

4. **Response Format**:
    - Each sorting servlet responds with a JSON object containing:
        - `sortedNumbers`: An array of numbers sorted in ascending order.
        - `links`: HATEOAS links for navigation to related endpoints.

5. **Dependencies**:
    - Java Servlet API
    - Gson library for JSON serialization

6. **Build and Run**:
    - Clone the repository:

      ```bash
      git clone <repository-url>
      ```

    - Build the project using Maven or your preferred build tool.

    - Deploy the WAR file to your servlet container.

    - Access the application using the base URL of your servlet container (e.g., `http://localhost:8080/your-app-context`).





