<?php
$servername = "localhost";
$username = "root";   // Default XAMPP MySQL username
$password = "";       // Default XAMPP MySQL password is empty
$dbname = "excel_upload";  // The name of the database

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Initialize search variables
$tutor = "";
$student_name = "";
$year_of_study = "";
$results = []; // Initialize results as an empty array to avoid errors

// Handle form submission for searching and updating
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['search'])) {
        // Get search criteria
        $tutor = isset($_POST['tutorSelect']) ? $_POST['tutorSelect'] : '';
        $student_name = isset($_POST['student_name']) ? $_POST['student_name'] : '';
        $year_of_study = isset($_POST['year_of_study']) ? $_POST['year_of_study'] : '';
        
        // Query to get all tables in the database
        $tables = [];
        $result = $conn->query("SHOW TABLES");
        while ($row = $result->fetch_row()) {
            $tables[] = $row[0]; // Add table name to array
        }

        // Build the SQL query dynamically
        foreach ($tables as $table) {
            $sql = "SELECT * FROM `$table` WHERE 1";
            $conditions = [];

            if ($student_name) {
                $conditions[] = "student_name LIKE '%" . $conn->real_escape_string($student_name) . "%'";
            }

            if ($year_of_study) {
                $conditions[] = "year_of_study LIKE '%" . $conn->real_escape_string($year_of_study) . "%'";
            }

            if ($tutor) {
                $conditions[] = "tutor_name LIKE '%" . $conn->real_escape_string($tutor) . "%'";
            }

            if (count($conditions) > 0) {
                $sql .= " AND " . implode(" AND ", $conditions); // Use AND to combine conditions
            }

            $res = $conn->query($sql);
            if ($res && $res->num_rows > 0) {
                while ($row = $res->fetch_assoc()) {
                    $row['table'] = $table; // Add table name to the row
                    $results[] = $row;
                }
            }
        }

    } elseif (isset($_POST['update'])) {
        // Handle update
        $id = $_POST['id'];
        $student_name = $_POST['student_name'];
        $student_id = $_POST['student_id'];
        $year_of_study = $_POST['year_of_study'];
        $phone_number = $_POST['phone_number'];
        $tutor_name = $_POST['tutor_name'];
        $table = $_POST['table'];  // Get the table name from the hidden input
        
        // Check if the table name is set and not empty
        if (!empty($table)) {
            // Prepare the update query for the respective table
            $query = "UPDATE `$table` SET 
                      student_name = ?, 
                      student_id = ?, 
                      year_of_study = ?, 
                      phone_number = ?, 
                      tutor_name = ? 
                      WHERE id = ?";
            
            // Prepare the statement
            $stmt = $conn->prepare($query);
            if ($stmt) {
                // Bind parameters to the query
                $stmt->bind_param("sssssi", $student_name, $student_id, $year_of_study, $phone_number, $tutor_name, $id);

                // Execute the statement
                if ($stmt->execute()) {
                    $message = "Record updated successfully.";
                } else {
                    $message = "Error updating record: " . $stmt->error;
                }

                // Close the statement
                $stmt->close();
            } else {
                $message = "Error preparing query: " . $conn->error;
            }
        } else {
            $message = "Invalid table name.";
        }
    }
} else {
    $results = []; // Initialize results as an empty array on first load
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search and Edit Student Data</title>
    <style>
        body {
    font-family: Arial, sans-serif;
    background-color: #f4f7f6;
    margin: 0;
    padding: 20px;
}

.container {
    max-width: 100%; /* Ensures the container spans the entire screen width */
    width: 100%;
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    overflow: hidden; /* Ensures no content overflows the container */
}

h2 {
    text-align: center;
    color: #2c3e50;
    margin-bottom: 20px; /* Adds spacing below the heading */
}

form {
    display: flex;
    flex-direction: column;
    gap: 15px; /* Increases the gap between input fields */
    margin-bottom: 20px; /* Adds margin below the form */
}

label {
    font-weight: bold;
    color: #34495e;
    margin-bottom: 5px; /* Adds margin below the label */
}

input, select {
    padding: 12px; /* Increases padding inside the input fields */
    font-size: 16px;
    border-radius: 4px;
    border: 1px solid #ccc;
    width: 100%; /* Ensures input fields cover the entire width */
    box-sizing: border-box; /* Includes padding in the width */
}

button {
    padding: 12px;
    background-color: #3498db;
    color: white;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
    font-size: 16px;
    border-radius: 4px;
}

button:hover {
    background-color: #2980b9;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
    table-layout: auto;  /* Ensures columns adjust based on content */
}

table th, table td {
    border: 1px solid #ddd;
    padding: 12px;  /* Adds more padding to table cells */
    text-align: left;
    overflow: hidden;  /* Ensures content does not overflow the cells */
    text-overflow: ellipsis;  /* Adds an ellipsis if content is too long */
}

table th {
    background-color: #f2f2f2;
}

.message {
    text-align: center;
    font-size: 16px;
    color: #2ecc71;
    font-weight: bold;
    margin-top: 20px;
}

.error-message {
    text-align: center;
    font-size: 16px;
    color: #e74c3c;
    font-weight: bold;
    margin-top: 20px;
}

.actions {
    text-align: center;
}

@media screen and (max-width: 768px) {
    .container {
        padding: 10px; /* Reduces padding for smaller screens */
    }
    
    form {
        gap: 10px; /* Reduces gap between form fields */
    }

    input, select {
        padding: 10px; /* Adjusts padding in smaller screens */
    }
    
    button {
        padding: 10px; /* Adjusts button padding in smaller screens */
    }

    table th, table td {
        padding: 8px; /* Reduces padding in smaller screens */
    }
}

    </style>
</head>
<body>

<div class="container">
    <h2>Search and Edit Student Data</h2>
    
    <form method="POST" action="">
        <label for="tutorSelect">Tutor Name:</label>
        <input type="text" id="tutorSelect" name="tutorSelect" value="<?php echo htmlspecialchars($tutor); ?>" />

        <label for="student_name">Student Name:</label>
        <input type="text" id="student_name" name="student_name" value="<?php echo htmlspecialchars($student_name); ?>" />
        
        <label for="year_of_study">Year of Study:</label>
        <input type="text" id="year_of_study" name="year_of_study" value="<?php echo htmlspecialchars($year_of_study); ?>" />

        <button type="submit" name="search">Search</button>
    </form>

    <?php if (isset($message)): ?>
        <div class="message"><?php echo htmlspecialchars($message); ?></div>
    <?php endif; ?>

    <?php if (count($results) > 0): ?>
        <form method="POST" action="">
            <table>
                <thead>
                    <tr>
                        <th>Table</th>
                        <th>ID</th>
                        <th>Student Name</th>
                        <th>Student ID</th>
                        <th>Year of Study</th>
                        <th>Phone Number</th>
                        <th>Tutor Name</th>
                        <th>Created At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($results as $row): ?>
                        <tr>
                            <td><?php echo htmlspecialchars($row['table']); ?></td>
                            <td><input type="hidden" name="id" value="<?php echo $row['id']; ?>"><?php echo $row['id']; ?></td>
                            <td><input type="text" name="student_name" value="<?php echo htmlspecialchars($row['student_name']); ?>" /></td>
                            <td><input type="text" name="student_id" value="<?php echo htmlspecialchars($row['student_id']); ?>" /></td>
                            <td><input type="text" name="year_of_study" value="<?php echo htmlspecialchars($row['year_of_study']); ?>" /></td>
                            <td><input type="text" name="phone_number" value="<?php echo htmlspecialchars($row['phone_number']); ?>" /></td>
                            <td><input type="text" name="tutor_name" value="<?php echo htmlspecialchars($row['tutor_name']); ?>" /></td>
                            <td><?php echo htmlspecialchars($row['created_at']); ?></td>
                            <td class="actions">
                                <input type="hidden" name="table" value="<?php echo htmlspecialchars($row['table']); ?>"> <!-- Hidden field for table name -->
                                <button type="submit" name="update">Update</button>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                </tbody>
            </table>
        </form>
    <?php else: ?>
        <div class="error-message">No records found matching your criteria.</div>
    <?php endif; ?>
</div>

</body>
</html>

<?php
// Close the database connection
$conn->close();
?>
