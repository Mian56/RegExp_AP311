package com.example.registration_regexp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Controller for the registration form.
 * This class manages the input fields, validates user data,
 * and handles the addition of new users to the list.
 */
public class regController {

    @FXML
    private TextField fname_tf;

    @FXML
    private TextField lname_tf;

    @FXML
    private TextField gmail_tf;

    @FXML
    private TextField DOB_tf;

    @FXML
    private TextField zip_tf;

    @FXML
    private Button add_button;

    @FXML
    private Label fname_error;

    @FXML
    private Label lname_error;

    @FXML
    private Label email_error;

    @FXML
    private Label dob_error;

    @FXML
    private Label zip_error;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        fname_tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateFirstName();
            }
        });
        lname_tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateLastName();
            }
        });
        gmail_tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateEmail();
            }
        });
        DOB_tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateDOB();
            }
        });
        zip_tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateZip();
            }
        });
    }

    private void validateFirstName() {
        String firstName = fname_tf.getText();
        if (!firstName.matches("^[A-Za-z]{2,25}$")) {
            fname_error.setText("Invalid First Name (2-25 letters)");
        } else {
            fname_error.setText("");
        }
        updateAddButtonState();
    }

    private void validateLastName() {
        String lastName = lname_tf.getText();
        if (!lastName.matches("^[A-Za-z]{2,25}$")) {
            lname_error.setText("Invalid Last Name (2-25 letters)");
        } else {
            lname_error.setText("");
        }
        updateAddButtonState();
    }

    private void validateEmail() {
        String email = gmail_tf.getText();
        if (!email.matches("^[a-zA-Z0-9._%+-]+@farmingdale\\.edu$")) {
            email_error.setText("Invalid Email (must be a Farmingdale email)");
        } else {
            email_error.setText("");
        }
        updateAddButtonState();
    }

    private void validateDOB() {
        String dob = DOB_tf.getText();
        if (!dob.matches("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$")) {
            dob_error.setText("Invalid Date (MM/DD/YYYY)");
        } else {
            dob_error.setText("");
        }
        updateAddButtonState();
    }

    private void validateZip() {
        String zip = zip_tf.getText();
        if (!zip.matches("^\\d{5}$")) {
            zip_error.setText("Invalid Zip Code (5 digits)");
        } else {
            zip_error.setText("");
        }
        updateAddButtonState();
    }

    private void updateAddButtonState() {
        boolean isValid = fname_error.getText().isEmpty() &&
                lname_error.getText().isEmpty() &&
                email_error.getText().isEmpty() &&
                dob_error.getText().isEmpty() &&
                zip_error.getText().isEmpty();

        add_button.setDisable(!isValid);
    }

    /**
     * Adds a new person to the list and switches to the TableView.
     *
     * @throws IOException if there is an error loading the FXML file for the TableView
     */
    @FXML
    public void handle_add() {
        if (add_button.isDisable()) return;

        String firstName = fname_tf.getText();
        String lastName = lname_tf.getText();
        String email = gmail_tf.getText();
        String dob = DOB_tf.getText();
        String zip = zip_tf.getText();

        Person newPerson = new Person(firstName, lastName, email, dob, zip);
        personData.add(newPerson); // Add person to the list

        switchToTableView(); // Switch to the TableView
    }

    private void switchToTableView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("info.fxml"));
            AnchorPane tableView = loader.load();

            regInfoController tableController = loader.getController();
            tableController.setPersonList(personData);

            // Set the new scene
            Stage stage = (Stage) add_button.getScene().getWindow(); // Get the current stage
            Scene scene = new Scene(tableView);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
