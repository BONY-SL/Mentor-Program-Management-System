package org.menter.application.mpms.dao;

import org.menter.application.mpms.entity.User;
import org.menter.application.mpms.service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Fetch users based on their role
    public List<User> getUsersByRole(String role) {

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE Role = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, role);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setFirstName(resultSet.getString("FirstName"));
                    user.setLastName(resultSet.getString("LastName"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(resultSet.getString("Role"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users by role: " + e.getMessage());
        }
        return users;
    }
}
