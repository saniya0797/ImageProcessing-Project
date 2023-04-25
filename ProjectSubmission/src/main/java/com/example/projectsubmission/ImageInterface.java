package com.example.projectsubmission;

import java.io.File;
import java.util.List;

public interface ImageInterface { //interface
    void displayImageProperties(File file); // method takes File object as a parameter
    List<File> fileSelect(); // method returns list of Files
    void displayThumbnail(File file); // method take file object as a parameter to display thumbnail of an image
}
