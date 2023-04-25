package com.example.projectsubmission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
//this class is controller class for first page "hello-view.fxml"
public class Controller {
    public Stage stage;
    public Scene scene;
    public Parent root;

    public void clickhere(ActionEvent event) throws IOException {// method is called when the user clicks a button "GettingStarted" ,it loads the secondPage.FXML file and sets the scene for the stage
        root = FXMLLoader.load(getClass().getResource("secondPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }

}





