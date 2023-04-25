package com.example.projectsubmission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//this class is controller class for displaying image properties and thumbnail(secondPage.fxml) extends Controller class and implement ImageInterface
public class SelectingImage extends Controller implements ImageInterface{

    //Declaring secondPage.fxml components as a variable of type FXML's Components
  @FXML public ListView<File> listview = new ListView<>();
  @FXML public Label filenameLabel ;
  @FXML public Label Dimensions;
  @FXML public Label Location;
  @FXML public Label Camera;
  @FXML public ImageView imageview;
  List<File> selectedFiles = new ArrayList<>();//Declaring an Arraylist of type Files
   public void chooseDesktop(ActionEvent event) throws IOException { //this method is called when user click button "Select image from Desktop"
        listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            displayThumbnail(newValue);
            displayImageProperties(newValue);
        }
    });
    List<File> files=fileSelect();
    if (files != null) {
        selectedFiles.addAll(files);
        listview.getItems().addAll(files);
    }
}
     @Override
     public List<File> fileSelect(){ //interface method called by chooseDesktop() to open the file chooser dialog and return the selected image files
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image(s)");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        return files;
    }
    @Override
    public void displayThumbnail(File file) { //method for displaying the thumbnail of the selected image file in the imageview components
        try {
            byte[] imageBytes = Files.readAllBytes(file.toPath());
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            imageview.setImage(image);
            imageview.setFitWidth(100);
            imageview.setFitHeight(100);
            // Set filename label
            String filename = file.getName();
            filenameLabel.setText("Filename: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
     public void displayImageProperties(File file) { //method for displaying the properties of the selected image file in the corresponding Label components
        try {
           // Get image metadata and set labels
            byte[] imageBytes = Files.readAllBytes(file.toPath());
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            Dimensions.setText("Dimensions: " + (int)image.getWidth() + "x" + (int)image.getHeight());
            Camera.setText("Camera: " +(double)image.getProgress());
            Location.setText("Location: " +file.getAbsolutePath());
       } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void next(ActionEvent event) throws IOException { //method is called when the user clicks "Click Here to change Image Format" button,it loads "thirdPage.fxml" file and sets the scene for the stage
        root = FXMLLoader.load(getClass().getResource("thirdPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void back(ActionEvent event) throws IOException {//method is called when the user clicks a "MainMenu" button ,it loads the "hello-view.FXML" file and sets the scene for the stage
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }
}
