module com.example.registration_regexp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.registration_regexp to javafx.fxml;
    exports com.example.registration_regexp;
}