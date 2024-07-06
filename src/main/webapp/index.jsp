<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sorting Algorithms</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="container">
    <h1>Sort Your Numbers</h1>
    <form id="sortForm">
        <label for="numbers">Enter numbers (comma-separated):</label><br>
        <input type="text" id="numbers" name="numbers" required><br><br>

        <label for="algorithm">Select sorting algorithm:</label><br>
        <select id="algorithm" name="algorithm">
            <option value="merge">Merge Sort</option>
            <option value="quick">Quick Sort</option>
            <option value="radix">Radix Sort</option>
            <option value="heap">Heap Sort</option>
            <option value="bucket">Bucket Sort</option>
        </select><br><br>

        <input type="button" value="Sort" onclick="sortNumbers()">
    </form>

    <div class="result-container">
        <h2>Sorted Result:</h2>
        <pre id="result"></pre>
        <p id="error" style="color:red;"></p>
    </div>
    <div class="links-container">
        <h2>Links:</h2>
        <ul id="links"></ul>
    </div>
</div>

<script src="script.js"></script>

</body>
</html>
