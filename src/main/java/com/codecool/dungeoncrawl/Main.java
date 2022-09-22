package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.util.Optional;

public class Main extends Application {
    LEVEL level = LEVEL.START;
    GameMap map = MapLoader.loadMap(level.getMapLevel());
    GridPane ui = new GridPane();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();

    public static void  main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        makeBackgroundMusic(Sound.MUSIC.getFilePath());
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case S:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case A:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case D:
                map.getPlayer().move(1,0);
                refresh();
                break;
            case SPACE:
                map.addInventory();
                refresh();
                break;
            case I:
                clearInventoryText();
                String inventory = map.getPlayer().inventoryToString();
                ui.add(new Label("Inventory:"), 0, 1);
                ui.add(new Label(inventory), 1, 1);
                refresh();
                break;
            case ESCAPE:
                clearInventoryText();
                refresh();
                break;

        }
    }

    private void removeDisappearingWall() {
        if (map.getSkeletonCount() <= 0) {
            for (Cell[] mapRow: map.getCells()){
                for (Cell cell: mapRow) {
                    if (cell.getType().equals(CellType.DISAPPEARING_WALL)){
                        cell.setType(CellType.FLOOR);
                    }
                }
            }
        }
    }

    private void clearInventoryText() {
        ui.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 1);
        ui.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 1);
    }

    private void refresh() {
        moveMonsters();
        removeDisappearingWall();
        drawMap();
        stepNextLevel();
    }

    private void drawMap() {
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
    }

    private void stepNextLevel() {
        if (!map.getPlayer().isAlive()) {
            ButtonType button = alertUser("You've lost","Sorry but you were killed by a monster.", Alert.AlertType.WARNING).orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK){
                map = MapLoader.loadMap(LEVEL.END.getMapLevel());
                refresh();
            }
        }
        if(map.getPlayer().getCell().getType() == CellType.QUIT) System.exit(1);
        else if (map.getPlayer().getCell().getType() == CellType.REPEAT) {
            level = LEVEL.START;
            map = MapLoader.loadMap(level.getMapLevel());
            refresh();
        } else if (map.getPlayer().getCell().getType() == CellType.OPEN_DOOR) {
            map = MapLoader.loadMap(level.nextLevel().getMapLevel());
            level = level.nextLevel();
        }
    }

    private void moveMonsters() {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() instanceof Monster) {
                    if (cell.getActor() instanceof Monster) {
                    int newX = ((Monster) cell.getActor()).generateNextStepX();
                    int newY = ((Monster) cell.getActor()).generateNextStepY();
                    cell.getActor().move(newX, newY);
                    }
                }
            }
        }
    }

    private void makeBackgroundMusic(String filepath) {
        try{
            final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP)
                    makeBackgroundMusic(filepath);
            });
            clip.open(AudioSystem.getAudioInputStream(new File(filepath)));
            // Reduce volume by 3 decibels.
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-3.0f);
            clip.start();
        }
        catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public Optional<ButtonType> alertUser(String title, String message, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
