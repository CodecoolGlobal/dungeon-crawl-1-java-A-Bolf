package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label damageLabel = new Label();
    Label inventoryLabel = new Label();
    private GridPane ui = new GridPane();
    private BorderPane borderPane = new BorderPane();
    Button btn;


    public static void main(String[] args) {
        launch(args);
    }

    private void initPickupButton() {
        btn = new Button("Pick Up Item");
        btn.setFocusTraversable(false);
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            map.getPlayer().pickUpItem(map.getPlayer().getCell());
            updateLabels();
            ui.getChildren().remove(btn);
            borderPane.requestFocus();
        });
    }

    private void buttonDisplay() {
        Cell cell = map.getPlayer().getCell();
        System.out.println("Cell: " + cell);
        if (cell.hasItem()) {
            ui.add(btn, 0, 10);
            btn.setVisible(true);
            System.out.println("Button SHOW");
        } else {
            System.out.println("Button attempt to HIDE");
            try {
                btn.setVisible(false);
                ui.getChildren().remove(btn);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(ui.getChildren());


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initPickupButton();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Inventory:"), 0, 2);
        ui.add(inventoryLabel, 1, 2);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Damage:"), 0, 1);
        ui.add(damageLabel, 1, 1);


        borderPane.setCenter(canvas);

        borderPane.setBottom(ui);
//        borderPane.setRight(ui);
//        borderPane.setBottom(buttonPane);
        ui.setAlignment(Pos.BOTTOM_LEFT);


        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        refresh();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(800), actionEvent -> {

        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(this::onKeyPressed);


        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        borderPane.requestFocus();
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

    private void refresh() {
        buttonDisplay();
        borderPane.requestFocus();
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
        updateLabels();
    }

    private void updateLabels() {
        healthLabel.setText("" + map.getPlayer().getHealth());
        damageLabel.setText(map.getPlayer().getDamage() + "");
        inventoryLabel.setText(Player.getInventoryContents());
    }
}