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
    var numberPattern = /^(-?\d+\s*,\s*)*-?\d+$/;
    if (!numberPattern.test(numbers)) {
        document.getElementById('error').textContent = 'Error: Please enter numbers separated by commas without multiple consecutive commas.';
        return;
    }

    // Get the context path dynamically
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

            // Display all the links
            data.links.forEach(link => {
                var li = document.createElement('li');
                var a = document.createElement('a');
                a.href = 'javascript:window.history.back()'; // Redirect to previous page
                a.textContent = link.rel;
                li.appendChild(a);
                document.getElementById('links').appendChild(li);
            });
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            document.getElementById('error').textContent = 'Error: ' + error.message;
        });
}
