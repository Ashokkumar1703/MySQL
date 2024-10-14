package com.example.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDatabase {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Employee Data2";
        String username = "root"; 
        String password = "root"; 

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Step 1: Establish a connection to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Step 2: Write the SQL insert statement
            String sql = "INSERT INTO employee (Employee_No, Employee_Name, Employee_Page, Employee_Salary) VALUES (?, ?, ?, ?)";

            // Step 3: Create a PreparedStatement object
            preparedStatement = connection.prepareStatement(sql);

            // Step 4: Insert data into the employee table
            Object[][] employeeData = {
                {101, "Jenny", 25, 10000},
                {102, "Jacky", 30, 20000},
                {103, "Joe", 20, 40000},
                {104, "John", 40, 80000},
                {105, "Shameer", 25, 90000}
            };

            for (Object[] employee : employeeData) {
                preparedStatement.setInt(1, (int) employee[0]);
                preparedStatement.setString(2, (String) employee[1]);
                preparedStatement.setInt(3, (int) employee[2]);
                preparedStatement.setInt(4, (int) employee[3]); // Correctly set salary as an integer

                // Execute the update
                preparedStatement.executeUpdate();
            }

            System.out.println("Data inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 5: Close the resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}