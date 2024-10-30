package com.example.registration_regexp;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class regInfoController {
    @FXML
    private TableView<Person> viewInfo_TV; // Reference to the TableView

    @FXML
    private TableColumn<Person, String> firstNameColumn; // Reference to the first name column
    @FXML
    private TableColumn<Person, String> lastNameColumn; // Reference to the last name column
    @FXML
    private TableColumn<Person, String> emailColumn; // Reference to the email column
    @FXML
    private TableColumn<Person, String> dobColumn; // Reference to the DOB column
    @FXML
    private TableColumn<Person, String> zipColumn; // Reference to the zip column

    private ObservableList<Person> personList = FXCollections.observableArrayList(); // List to hold person data

    public void initialize() {
        // Set up the columns to display the data
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        dobColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDob()));
        zipColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getZip()));
    }

    public void setPersonList(ObservableList<Person> personData) {
        this.personList = personData; // Store the received list
        viewInfo_TV.setItems(personList); // Bind the list to the TableView
    }
}
