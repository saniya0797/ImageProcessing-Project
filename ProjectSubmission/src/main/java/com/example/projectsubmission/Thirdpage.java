package com.example.projectsubmission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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

//this class is Controller class for "thirdPage.fxml" extending SelectingImage class and implementing "Initializable"
public class Thirdpage extends SelectingImage implements Initializable {
    @FXML
    public ChoiceBox<String> choicebox =new ChoiceBox<>(); //declaring ChoiceBox as a String of arrayList
    public String[] items = {"jpeg","png","gif"};

    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicebox.getItems().addAll(items);
        choicebox.setOnAction(this::getItems);
    }
    private void getItems(ActionEvent event) {
        String items = choicebox.getValue();

    }
    public void chooseDesktop(ActionEvent event) throws IOException {
        List<File> files=fileSelect();
        if (files != null) {
            selectedFiles.addAll(files);
            listview.getItems().addAll(files);

        }
    }
    public List<File> fileSelect(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image(s)");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        return files;
    }
    public void otherFormat(ActionEvent event){ // Method to convert image to the chosen format
        if (selectedFiles.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an image to convert.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        // Show a directory chooser dialog to select a destination directory
        DirectoryChooser dc = new DirectoryChooser();
        File selectedDir = dc.showDialog(null);
        if (selectedDir != null) {
            selectedDir.getAbsolutePath();
        }
        // Get the chosen format from the choicebox and show a file chooser dialog to select a destination file
        String format = choicebox.getValue();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Converted Image(s)");
        fileChooser.setInitialDirectory(selectedDir);
        // Convert  file to the chosen format
        List<File> convertedFiles = new ArrayList<>();
        for (File file : selectedFiles) {
            try {
                // Convert image to selected format
                BufferedImage bufferedImage = ImageIO.read(file);
                File convertedFile = new File(selectedDir, file.getName().replaceAll("\\.[^.]+$", "." + format.toLowerCase()));
                boolean temp = ImageIO.write(bufferedImage, format, convertedFile);
                convertedFiles.add(convertedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Display a success message and clear the selected files list and image view
        if (!convertedFiles.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Conversion completed!", ButtonType.OK);
            alert.showAndWait();
            selectedFiles.clear();
            listview.getItems().clear();
        }
    }
    public void back(ActionEvent event) throws IOException {//method is called when the user clicks "back" button,it loads "secondPage.fxml" file and sets the scene for the stage
        root = FXMLLoader.load(getClass().getResource("secondPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }
    public void mainmenu(ActionEvent event) throws IOException { //method is called when the user clicks a "MainMenu" button ,it loads the "hello-view.FXML" file and sets the scene for the stage
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }
}
