package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;

import com.codecool.dungeoncrawl.logic.actors.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import com.codecool.dungeoncrawl.logic.actors.Player;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

import static com.codecool.dungeoncrawl.logic.actors.Player.passage;

public class Main extends Application {
    private int refreshVertical = 0;
    private int refreshHorizontal = 0;
    private int maxrefreshHorizontal = 0;
    private int maxrefreshVertical = 0;
    private byte mapNow = 0;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private int windowHeight;
    private int windowWidth;
    private int oneSquare = 32;
    private Stage pStage;

    GameMap map = MapLoader.loadMap("/map2.txt");
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
    List<Skeleton> skeletons = MapLoader.getSkeletons();
    List<Ogre> ogres = MapLoader.getOgres();


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
        if (cell.hasItem()) {
            ui.add(btn, 0, 10);
            btn.setVisible(true);
        } else {
            try {
                btn.setVisible(false);
                ui.getChildren().remove(btn);
            } catch (Exception e) {
                System.out.println(e);
            }
        }


    }

    private void printMe() {
        System.out.println(pStage.getHeight());
        System.out.println(pStage.getWidth());
        System.out.println(primaryScreenBounds);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pStage = primaryStage;
        windowHeight = (int) primaryStage.getHeight();
        windowWidth = (int) primaryStage.getWidth();
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

        ui.setAlignment(Pos.BOTTOM_LEFT);


        Scene scene = new Scene(borderPane);
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Clicked");
            borderPane.requestFocus();
        });
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        createNewMonsterThread(Skeleton.class, 1000);
        createNewMonsterThread(Ogre.class, 550);
        createNewMonsterThread(Blup.class, 1200);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        borderPane.requestFocus();
        setStarterValues();
        maxHorizontal();
        maxVertical();
        refresh();
    }

    private void maxHorizontal() {
        maxrefreshHorizontal = (map.getWidth() * oneSquare) / oneSquare;

    }


    private void maxVertical() {
        int plusVerticalSpaceBecauseUi = 1;
        maxrefreshVertical = (map.getHeight() * oneSquare) / oneSquare + plusVerticalSpaceBecauseUi;

    }


    private void createNewMonsterThread(Class<?> monsterType, int frequency){
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(frequency), actionEvent -> {
            moveAllMonster(monsterType);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void moveAllMonster(Class<?> monsterType) {
        List<Monster> monsters = map.getMonsters(monsterType);
        for (Monster monster :
                monsters) {
            monster.moveMonsters(map.getPlayer());
        }
        refresh();
    }


    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                printMe();
                map.getPlayer().move(0, -1);
                if (passage()) {
                    changeMap();
                    break;
                }
                if (refreshVertical > 0 && Player.getVertical() * oneSquare < map.getHeight() * oneSquare - windowHeight / 2) {
                    if (Player.collised(0, -1)) {
                        refreshVertical--;
                    }
                }
                refresh();
                break;
            case DOWN:
                printMe();
                map.getPlayer().move(0, 1);
                if (passage()) {
                    changeMap();
                    break;
                }
                if (Player.getVertical() * oneSquare > windowHeight / 2 && refreshVertical < maxrefreshVertical) {
                    if (Player.collised(0, 1)) {
                        refreshVertical++;
                    }
                }
                int a = Player.getVertical();
                refresh();
                break;
            case LEFT:
                printMe();
                map.getPlayer().move(-1, 0);
                if (passage()) {
                    changeMap();
                    break;
                }
                if (refreshHorizontal > 0 && Player.getHorizontal() * oneSquare < map.getWidth() * oneSquare - windowWidth / 2) {
                    if (Player.collised(-1, 0)) {
                        refreshHorizontal--;
                    }
                }
                refresh();
                break;
            case RIGHT:
                printMe();
                map.getPlayer().move(1, 0);
                if (passage()) {
                    changeMap();
                    break;
                }
                if (refreshHorizontal <= maxrefreshHorizontal && Player.getHorizontal() * oneSquare > windowWidth / 2) {
                    if (Player.collised(1, 0)) {
                        refreshHorizontal++;
                    }
                }
                refresh();
                break;
        }
    }

    private void refresh() {
//        pStage.sizeToScene();
        windowHeight = (int) pStage.getHeight();
        windowWidth = (int) pStage.getWidth();
        buttonDisplay();
        borderPane.requestFocus();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x + refreshHorizontal < map.getWidth(); x++) {
            for (int y = 0; y + refreshVertical < map.getHeight(); y++) {
                Cell cell = map.getCell(x + refreshHorizontal, y + refreshVertical);
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

    private void setStarterValues() {

        if (Player.getHorizontal() * oneSquare > windowWidth / 2) {
            refreshHorizontal = (Player.getHorizontal() * oneSquare - windowWidth / 2) / oneSquare;
            if (refreshHorizontal > maxrefreshHorizontal) {
                refreshHorizontal = maxrefreshHorizontal;
            }
        } else if (refreshHorizontal != 0) {
            refreshHorizontal = 0;
        }
        if (Player.getVertical() * oneSquare > windowHeight / 2) {
            refreshVertical = (Player.getVertical() * oneSquare - windowHeight / 2) / oneSquare;
            if (refreshVertical > maxrefreshVertical) {
                refreshVertical = maxrefreshVertical;
            }
        } else if (refreshVertical != 0) {
            refreshVertical = 0;
        }
    }


    private void changeMap() {
        if (mapNow == 0) {
            map = MapLoader.loadMap("/map3.txt");
            mapNow = 1;
        } else {
            map = MapLoader.loadMap("/map2.txt");
            mapNow = 0;
        }
        maxHorizontal();
        maxVertical();
        setStarterValues();
        maxHorizontal();
        maxVertical();
        refresh();
    }
}
