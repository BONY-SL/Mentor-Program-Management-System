package org.menter.application.mpms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.menter.application.mpms.dao.GroupDAO;
import org.menter.application.mpms.dao.UserDAO;
import org.menter.application.mpms.entity.MentorGroup;
import org.menter.application.mpms.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ShowGroupsController {

    @FXML
    private TableView<MentorGroup> GroupsTable;
    @FXML
    private TableColumn<MentorGroup, Integer> GroupID;
    @FXML
    private TableColumn<MentorGroup, String> GroupName;
    @FXML
    private TableColumn<MentorGroup, Integer> MentorID;
    @FXML
    private TableColumn<MentorGroup, String> MentorName;
    @FXML
    private TableColumn<MentorGroup, Integer> MenteeID;
    @FXML
    private TableColumn<MentorGroup, String> MenteeName;

    private final GroupDAO groupDAO = new GroupDAO();

    public void initialize() {

        // Set up columns
        GroupID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        GroupName.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        MentorID.setCellValueFactory(new PropertyValueFactory<>("MentorID"));
        MentorName.setCellValueFactory(new PropertyValueFactory<>("MentorName"));
        MenteeID.setCellValueFactory(new PropertyValueFactory<>("MenteeID"));
        MenteeName.setCellValueFactory(new PropertyValueFactory<>("MenteeName"));

        getAllGroups();
    }

    public void getAllGroups() {
        // Fetch mentors and mentees from the database
        List<MentorGroup> groups = groupDAO.getAllGroups();

        // Combine the lists
        List<MentorGroup> mentorGroups = new ArrayList<>(groups);

        // Populate the TableView
        ObservableList<MentorGroup> gp = FXCollections.observableArrayList(mentorGroups);
        GroupsTable.setItems(gp);
    }
}
