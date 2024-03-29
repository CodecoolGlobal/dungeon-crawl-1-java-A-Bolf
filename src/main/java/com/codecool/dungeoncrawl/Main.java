package com.codecool.dungeoncrawl;


import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.view.Modal;
import com.codecool.dungeoncrawl.logic.Sound;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.List;

import static com.codecool.dungeoncrawl.logic.actors.Player.passage;

public class Main extends Application {
    private int refreshVertical = 0;
    private static boolean gameOver = false;
    private int refreshHorizontal = 0;
    private int maxrefreshHorizontal = 0;
    private int maxrefreshVertical = 0;
    private static byte mapNow = 0;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private int windowHeight = (int) primaryScreenBounds.getHeight();
    private int windowWidth = (int) primaryScreenBounds.getWidth();
    private int oneSquare = 32;
    private Stage pStage;
    Sound sound = Sound.MAP1;
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

    List<Skeleton> skeletons = MapLoader.getSkeletons();
    List<Ogre> ogres = MapLoader.getOgres();


    public static void main(String[] args) {
        launch(args);
    }




    private void printMe() {
        System.out.println(pStage.getHeight());
        System.out.println(pStage.getWidth());
        System.out.println(primaryScreenBounds);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        sound.play(true);
        pStage = primaryStage;

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


        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        createNewMonsterThread(Skeleton.class, 1000);
        createNewMonsterThread(Ogre.class, 550);
        createNewMonsterThread(Blup.class, 1200);
        primaryStage.setTitle("(Code) !COOL Game!");
        primaryStage.show();
        borderPane.requestFocus();
        setStarterValues();
        maxHorizontal();
        maxVertical();
        refresh();
    }

    private void maxHorizontal() {
        maxrefreshHorizontal = (map.getWidth() * oneSquare -windowWidth) / oneSquare;

    }


    private void maxVertical() {
        int plusVerticalSpaceBecauseUi = 2;
        maxrefreshVertical = (map.getHeight() * oneSquare - windowHeight) / oneSquare + plusVerticalSpaceBecauseUi;
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
        if(!gameOver){
        for (Monster monster :
                monsters) {
            monster.moveMonsters(map.getPlayer());
        }
        refresh();}
    }


    private void onKeyPressed(KeyEvent keyEvent) {
        if(!gameOver) {
            boolean collised;
            switch (keyEvent.getCode()) {
                case UP:
                    printMe();
                    collised = Player.collised(0, -1);
                    map.getPlayer().move(0, -1);
                    if (passage()) {
                        changeMap();
                        break;
                    }
                    if (refreshVertical > 0 && Player.getVertical() * oneSquare < map.getHeight() * oneSquare - windowHeight / 2) {
                        if (collised) {
                            refreshVertical--;
                        }
                    }
                    refresh();
                    break;
                case DOWN:
                    printMe();
                    collised = Player.collised(0, 1);
                    map.getPlayer().move(0, 1);
                    if (passage()) {
                        changeMap();
                        break;
                    }
                    if (Player.getVertical() * oneSquare > windowHeight / 2 && refreshVertical < maxrefreshVertical) {
                        if (collised) {
                            refreshVertical++;
                        }
                    }
                    refresh();
                    break;
                case LEFT:
                    printMe();
                    collised = Player.collised(-1, 0);
                    map.getPlayer().move(-1, 0);
                    if (passage()) {
                        changeMap();
                        break;
                    }
                    if (refreshHorizontal > 0 && Player.getHorizontal() * oneSquare < map.getWidth() * oneSquare - windowWidth / 2) {
                        if (collised) {
                            refreshHorizontal--;
                        }
                    }
                    refresh();
                    break;
                case RIGHT:
                    printMe();
                    collised = Player.collised(1, 0);
                    map.getPlayer().move(1, 0);
                    if (passage()) {
                        changeMap();
                        break;
                    }
                    if (refreshHorizontal <= maxrefreshHorizontal && Player.getHorizontal() * oneSquare > windowWidth / 2) {
                        if (collised) {
                            refreshHorizontal++;
                        }
                    }
                    refresh();
                    break;
                case S:
                    if (keyEvent.isControlDown()) {
                        Modal modal = new Modal();
                        modal.start(pStage);
                        System.out.println(modal.getData());
                    }
                    break;
                case E:
                    map.getPlayer().pickUpItem(map.getPlayer().getCell());
                    updateLabels();
                    borderPane.requestFocus();
                    break;

            }
        }
    }

    private void refresh() {
        gameOver();
        if(!gameOver) {
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
            sound.stop();
            sound=Sound.MAP2;
            sound.play(true);
        } else {
            map = MapLoader.loadMap("/map2.txt");
            mapNow = 0;
            sound.stop();
            sound=Sound.MAP1;
            sound.play(true);
        }
        maxHorizontal();
        maxVertical();
        setStarterValues();
        maxHorizontal();
        maxVertical();
        refresh();
    }

    public static boolean setGameOver() {
        return gameOver = true;
    }

    public static byte getMapNow() {
        return mapNow;
    }

    private void gameOver(){
        if(gameOver){
           sound.stop();
           context.setFill(Color.BLACK);
           context.fillRect(0,0,map.getWidth() * Tiles.TILE_WIDTH,map.getHeight() * Tiles.TILE_WIDTH);
           context.setStroke(Color.RED);
           context.setFont(Font.font(35));
           context.setTextAlign(TextAlignment.CENTER);
           context.strokeText("Game Over",windowWidth/2,windowHeight/2);
           Sound.GAME_OVER.play(false);

        }
    }

}
