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
        
        // Build the search query for the stud_details table
        $sql = "SELECT * FROM `stud_details` WHERE 1";
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

        // Execute the query and get the results
        $res = $conn->query($sql);
        if ($res && $res->num_rows > 0) {
            while ($row = $res->fetch_assoc()) {
                $results[] = $row;
            }
        }
    } elseif (isset($_POST['update'])) {
        // Handle update
        $id = $_POST['id'];
        $student_name = $_POST['student_name'];
        $register_number = $_POST['register_number'];
        $year_of_study = $_POST['year_of_study'];
        $father_phnum = $_POST['father_phnum'];
        $student_phnum = $_POST['student_phnum'];
        $tutor_name = $_POST['tutor_name'];
        
        // Update query
        $query = "UPDATE `stud_details` SET 
                  student_name = ?, 
                  register_number = ?, 
                  year_of_study = ?, 
                  father_phnum = ?, 
                  student_phnum = ?, 
                  tutor_name = ? 
                  WHERE id = ?";
        
        // Prepare the statement
        $stmt = $conn->prepare($query);
        if ($stmt) {
            // Bind parameters to the query
            $stmt->bind_param("ssssssi", $student_name, $register_number, $year_of_study, $father_phnum, $student_phnum, $tutor_name, $id);

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
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 20px;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        label {
            font-weight: bold;
            color: #333;
        }

        input[type="text"] {
            padding: 8px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%;
            box-sizing: border-box;
        }

        button {
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: auto;
            align-self: flex-start;
        }

        button:hover {
            background-color: #0056b3;
        }

        .message {
            background-color: #d4edda;
            color: #155724;
            padding: 10px;
            border: 1px solid #c3e6cb;
            border-radius: 4px;
            margin-bottom: 20px;
        }

        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            padding: 10px;
            border: 1px solid #f5c6cb;
            border-radius: 4px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        input[type="text"] {
            width: 100%;
        }

        td input {
            padding: 6px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .error-message,
        .message {
            margin-top: 20px;
        }

        .actions {
            text-align: center;
        }

        .actions button {
            margin-top: 10px;
            padding: 8px 12px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .actions button:hover {
            background-color: #218838;
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
                        <th>ID</th>
                        <th>Student Name</th>
                        <th>Register Number</th>
                        <th>Year of Study</th>
                        <th>Father's Phone</th>
                        <th>Student's Phone</th>
                        <th>Tutor Name</th>
                        <th>Created At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($results as $row): ?>
                        <tr>
                            <td><?php echo htmlspecialchars($row['id']); ?></td>
                            <td><input type="text" name="student_name" value="<?php echo htmlspecialchars($row['student_name']); ?>" /></td>
                            <td><input type="text" name="register_number" value="<?php echo htmlspecialchars($row['register_number']); ?>" /></td>
                            <td><input type="text" name="year_of_study" value="<?php echo htmlspecialchars($row['year_of_study']); ?>" /></td>
                            <td><input type="text" name="father_phnum" value="<?php echo htmlspecialchars($row['father_phnum']); ?>" /></td>
                            <td><input type="text" name="student_phnum" value="<?php echo htmlspecialchars($row['student_phnum']); ?>" /></td>
                            <td><input type="text" name="tutor_name" value="<?php echo htmlspecialchars($row['tutor_name']); ?>" /></td>
                            <td><?php echo htmlspecialchars($row['created_at']); ?></td>
                            <td>
                                <input type="hidden" name="id" value="<?php echo $row['id']; ?>">
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
