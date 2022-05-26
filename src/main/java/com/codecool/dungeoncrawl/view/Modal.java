package com.codecool.dungeoncrawl.view;// Java Program to create a text input
// dialog and add it to the stage

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;
import java.util.Optional;

public class Modal extends Application {
    String data = "";

    public String getData() {
        return data;
    }

    // launch the application
    public void start(Stage s) {
        // set title for the stage
        s.setTitle("creating textInput dialog");

        TextInputDialog window = new TextInputDialog("My Awesome Game");
        window.setTitle("Save Game");
        window.setHeaderText("Name Your Save File");
        window.setContentText("Save:");

        Optional<String> result = window.showAndWait();

        result.ifPresent(name -> {
            this.data=name;
        });



        s.show();
    }

    }
