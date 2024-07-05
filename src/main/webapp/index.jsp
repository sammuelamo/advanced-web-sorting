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

<script>
    function sortNumbers() {
        var numbers = document.getElementById('numbers').value.trim();
        var algorithm = document.getElementById('algorithm').value;

        // Clear previous error message, results, and links
        document.getElementById('error').textContent = '';
        document.getElementById('result').textContent = '';
        document.getElementById('links').innerHTML = '';

        // Validate input
        if (numbers === '') {
            document.getElementById('error').textContent = 'Error: Please enter numbers to sort.';
            return;
        }

        // Check if the input is correctly formatted (comma-separated numbers without multiple commas)
        var numberPattern = /^(\d+\s*,\s*)*\d+$/;
        if (!numberPattern.test(numbers)) {
            document.getElementById('error').textContent = 'Error: Please enter numbers separated by commas without multiple consecutive commas.';
            return;
        }

        // Get the context path dynamically (for local testing)
        var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
        var url = contextPath + '/sort/' + algorithm;

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ numbers: numbers }),
        })
            .then(response => response.json())
            .then(data => {
                // Display the sorted numbers
                var sortedNumbers = data.sortedNumbers.join(', ');
                document.getElementById('result').textContent = 'Sorted Numbers: ' + sortedNumbers;

                // Display only the "All Sorts" link
                var allSortsLink = data.links.find(link => link.rel === 'allSorts');
                if (allSortsLink) {
                    var li = document.createElement('li');
                    var a = document.createElement('a');
                    a.href = allSortsLink.href;
                    a.textContent = allSortsLink.rel;
                    a.target = '_blank'; // Open link in a new tab
                    li.appendChild(a);
                    document.getElementById('links').appendChild(li);
                }
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                document.getElementById('error').textContent = 'Error: ' + error.message;
            });
    }
</script>

</body>
</html>
