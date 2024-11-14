<?php
$servername = "localhost";
$username = "root";   // Default XAMPP MySQL username
$password = "";       // Default XAMPP MySQL password is empty
$dbname = "excel_upload";  // The name of the database

// Create connection
$conn = new mysqli($servername, $username, $password);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Check if the database exists, if not create it
$sql = "CREATE DATABASE IF NOT EXISTS $dbname";
if ($conn->query($sql) === TRUE) {
    // Database created or already exists
} else {
    die("Error creating database: " . $conn->error);
}

// Select the database
$conn->select_db($dbname);

// Create the table for student details if it doesn't exist
$createTableSQL = "
    CREATE TABLE IF NOT EXISTS `stud_details` (
        id INT AUTO_INCREMENT PRIMARY KEY,
        student_name VARCHAR(255),
        register_number VARCHAR(100) UNIQUE,
        father_name VARCHAR(255),
        father_phnum VARCHAR(15),
        student_phnum VARCHAR(15),
        tutor_name VARCHAR(100),
        year_of_study VARCHAR(50),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    )
";

if ($conn->query($createTableSQL) !== TRUE) {
    die("Error creating table: " . $conn->error);
}

// Handle the incoming AJAX request (Ensure it's POST and the data is valid)
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Get the raw POST data (JSON format)
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);

    // Check if rows exist in the decoded data
    if (isset($data['rows']) && is_array($data['rows'])) {
        $rows = $data['rows']; // Excel data rows

        // Prepare the SQL query to insert data
        $stmt = $conn->prepare("INSERT INTO `stud_details` (student_name, register_number, father_name, father_phnum, student_phnum, tutor_name, year_of_study) VALUES (?, ?, ?, ?, ?, ?, ?)");

        // Iterate over each row and insert the data into the database
        foreach ($rows as $row) {
            // Extract student data from each row
            // Ensure that we have all columns available from the incoming data
            if (count($row) >= 7) {
                $student_name = $row[0]; // Student name
                $register_number = $row[1]; // Register number
                $father_name = $row[2]; // Father's name
                $father_phnum = $row[3]; // Father's phone number
                $student_phnum = $row[4]; // Student's phone number
                $tutor_name = $row[5]; // Tutor name
                $year_of_study = $row[6]; // Year of study

                // Bind parameters and execute the statement
                $stmt->bind_param("sssssss", $student_name, $register_number, $father_name, $father_phnum, $student_phnum, $tutor_name, $year_of_study);
                $stmt->execute();
            } else {
                echo 'Error: Missing data in row';
                exit;
            }
        }

        // Close the statement
        $stmt->close();

        // Return success response
        echo 'Data inserted successfully';
    } else {
        echo 'Error: Invalid data format received';
    }
} else {
    echo 'Invalid request method';
}

// Close the database connection
$conn->close();
?>
