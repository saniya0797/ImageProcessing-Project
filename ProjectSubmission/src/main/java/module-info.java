module com.example.projectsubmission {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.projectsubmission to javafx.fxml;
    exports com.example.projectsubmission;
}