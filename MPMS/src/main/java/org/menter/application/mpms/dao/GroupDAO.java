package org.menter.application.mpms.dao;

import org.menter.application.mpms.entity.MentorGroup;
import org.menter.application.mpms.entity.User;
import org.menter.application.mpms.service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO {

    public boolean createGroup(MentorGroup group) {

        String sql = "INSERT INTO mentorgroup (GroupName, MentorID, MentorName,MenteeID,MenteeName) VALUES (?, ?, ?,?,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, group.getGroupName());
            statement.setInt(2, group.getMentorID());
            statement.setString(3,group.getMentorName());
            statement.setInt(4, group.getMenteeID());
            statement.setString(5,group.getMenteeName());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Get Mentees Details For Each Mentor
    public List<MentorGroup> getMyGroups(Integer id) {

        List<MentorGroup> mentorGroups = new ArrayList<>();
        String query = "SELECT * FROM mentorgroup WHERE MentorID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MentorGroup mentorGroup = new MentorGroup();
                    mentorGroup.setId(resultSet.getInt("Id"));
                    mentorGroup.setGroupName(resultSet.getString("GroupName"));
                    mentorGroup.setMenteeID(resultSet.getInt("MenteeID"));
                    mentorGroup.setMenteeName(resultSet.getString("MenteeName"));
                    mentorGroups.add(mentorGroup);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users by role: " + e.getMessage());
        }
        return mentorGroups;
    }

    // Get Mentor Details For Each Mentee
    public List<MentorGroup> getMyGroups2(Integer id) {

        List<MentorGroup> mentorGroups = new ArrayList<>();
        String query = "SELECT * FROM mentorgroup WHERE MenteeID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MentorGroup mentorGroup = new MentorGroup();
                    mentorGroup.setId(resultSet.getInt("Id"));
                    mentorGroup.setGroupName(resultSet.getString("GroupName"));
                    mentorGroup.setMentorID(resultSet.getInt("MentorID"));
                    mentorGroup.setMentorName(resultSet.getString("MentorName"));
                    mentorGroups.add(mentorGroup);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users by role: " + e.getMessage());
        }
        return mentorGroups;
    }

    public List<MentorGroup> getAllGroups() {
        List<MentorGroup> mentorGroups = new ArrayList<>();
        String query = "SELECT * FROM mentorgroup";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MentorGroup mentorGroup = new MentorGroup();
                    mentorGroup.setId(resultSet.getInt("Id"));
                    mentorGroup.setGroupName(resultSet.getString("GroupName"));
                    mentorGroup.setMentorID(resultSet.getInt("MentorID"));
                    mentorGroup.setMentorName(resultSet.getString("MentorName"));
                    mentorGroup.setMenteeID(resultSet.getInt("MenteeID"));
                    mentorGroup.setMenteeName(resultSet.getString("MenteeName"));
                    mentorGroups.add(mentorGroup);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users by role: " + e.getMessage());
        }
        return mentorGroups;
    }
}
