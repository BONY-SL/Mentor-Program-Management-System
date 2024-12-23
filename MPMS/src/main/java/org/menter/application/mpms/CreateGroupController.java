package org.menter.application.mpms;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.menter.application.mpms.dao.GroupDAO;
import org.menter.application.mpms.dao.UserDAO;
import org.menter.application.mpms.entity.MentorGroup;
import org.menter.application.mpms.entity.User;

import java.util.List;
import java.util.Objects;

public class CreateGroupController {

    @FXML
    private TextField InputGroupName;
    @FXML
    private ChoiceBox<Integer> mentorID;
    @FXML
    private ChoiceBox<Integer> menteeID;
    @FXML
    private TextField mentorName;
    @FXML
    private TextField menteeName;

    @FXML
    private Button submit;
    @FXML
    private Button reset;


    private final UserDAO userDAO = new UserDAO();
    private final GroupDAO groupDAO = new GroupDAO();

    public void initialize() {
        // Fetch mentors and mentees from the service
        List<User> mentors = userDAO.getUsersByRole("MENTOR");
        List<User> mentees = userDAO.getUsersByRole("MENTEE");

        // Set mentor IDs to the mentorID ChoiceBox
        mentorID.getItems().clear();
        for (User mentor : mentors) {
            mentorID.getItems().add(mentor.getId());  // Add mentor IDs to the ChoiceBox
        }

        mentorID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                User selectedMentor = mentors.stream().filter(mentor -> mentor.getId().equals(newValue)).findFirst().orElse(null);
                if (selectedMentor != null) {
                    mentorName.setText(selectedMentor.getFirstName() + " " + selectedMentor.getLastName());
                }
            }
        });

        // Set mentee IDs to the menteeID ChoiceBox
        menteeID.getItems().clear();
        for (User mentee : mentees) {
            menteeID.getItems().add(mentee.getId());  // Add mentee IDs to the ChoiceBox
        }

        menteeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                User selectedMentee = mentees.stream().filter(mentee -> mentee.getId().equals(newValue)).findFirst().orElse(null);
                if (selectedMentee != null) {
                    menteeName.setText(selectedMentee.getFirstName() + " " + selectedMentee.getLastName());
                }
            }
        });
    }


    // Submit Method: Validates inputs and creates a new group
    @FXML
    protected void submit() {
        // Check if all fields are filled
        if (InputGroupName.getText().isEmpty() || mentorID.getSelectionModel().getSelectedItem() == null || menteeID.getSelectionModel().getSelectedItem() == null) {
            showAlert("Error", "Please fill in all the fields.", Alert.AlertType.ERROR);
            return;
        }

        // Get the selected mentor and mentee IDs
        Integer selectedMentorID = mentorID.getSelectionModel().getSelectedItem();
        Integer selectedMenteeID = menteeID.getSelectionModel().getSelectedItem();

        // Create a new Group object
        MentorGroup newGroup = new MentorGroup();
        newGroup.setGroupName(InputGroupName.getText());
        newGroup.setMentorID(selectedMentorID);
        newGroup.setMenteeID(selectedMenteeID);
        newGroup.setMentorName(mentorName.getText());
        newGroup.setMenteeName(menteeName.getText());

        // Save the new group to the database
        boolean isSaved = groupDAO.createGroup(newGroup);

        if (isSaved) {
            showAlert("Success", "Group created successfully!", Alert.AlertType.INFORMATION);
            reset();  // Optionally reset the fields after successful submission
            closeWindow();
        } else {
            showAlert("Error", "Failed to create group. Please try again.", Alert.AlertType.ERROR);
        }
    }

    // Reset Method: Clears the form fields
    @FXML
    protected void reset() {
        InputGroupName.clear();
        mentorID.getSelectionModel().clearSelection();
        menteeID.getSelectionModel().clearSelection();
        mentorName.clear();
        menteeName.clear();
    }

    // Helper method to display alert messages
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("MPMS");
        alert.setContentText(message);

        // Set the icon for the alert dialog
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("appicon.png"))));
        alert.showAndWait();
    }

    // Helper method to close the current window
    private void closeWindow() {
        // Get the current stage and close it
        Stage currentStage = (Stage) submit.getScene().getWindow();
        currentStage.close();
    }

}
