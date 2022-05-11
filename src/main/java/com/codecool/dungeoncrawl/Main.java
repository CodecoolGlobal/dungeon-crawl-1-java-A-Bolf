package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;

import com.codecool.dungeoncrawl.logic.actors.Ogre;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import com.codecool.dungeoncrawl.logic.actors.Player;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    List<Skeleton> skeletons = MapLoader.getSkeletons();
    List<Ogre> ogres = MapLoader.getOgres();

    Label inventoryLabel=new Label();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        GridPane buttonPane = new GridPane();
        Button btn = new Button("Pick Up Item");
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(btn, 0, 10);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Inventory:"),0,1);
        ui.add(inventoryLabel,1,1);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);

        borderPane.setBottom(ui);
        borderPane.setRight(ui);
        borderPane.setBottom(buttonPane);
        ui.setAlignment(Pos.BOTTOM_RIGHT);


        Scene scene = new Scene(borderPane);
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Clicked");
            borderPane.requestFocus();
        });
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent -> {
            moveAllSkeleton();
            System.out.println("Running thread");
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        /*Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() ->{
                    moveAllSkeleton();
                });
            }
        },500,500);*/


        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        borderPane.requestFocus();
    }

    private void moveAllSkeleton() {
        System.out.println("#####-------------------------");
        System.out.println(skeletons);
        System.out.println("------------------------------");
        System.out.println(ogres);
        System.out.println("------------------------------");
        for (Skeleton skeleton :
                skeletons) {
            if (skeleton.getCell() != null){
                skeleton.moveSkeleton();
            }

        }
        for (Ogre ogre :
                ogres) {
            if (ogre.getCell() != null) {
                System.out.println(map.getPlayer().getCell().getX());
                ogre.chasePlayer(map.getPlayer());
            }
        }
        refresh();
        System.out.println("Running task");


    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
        }
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
//        inventoryLabel.setMaxWidth(200);
        inventoryLabel.setText("<html><p style=\"width:100px \">"+Player.getInventoryContents()+"</p></html>");
    }
}
