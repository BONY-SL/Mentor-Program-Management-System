package org.menter.application.mpms.dao;

import org.menter.application.mpms.entity.MentorGroup;
import org.menter.application.mpms.service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
