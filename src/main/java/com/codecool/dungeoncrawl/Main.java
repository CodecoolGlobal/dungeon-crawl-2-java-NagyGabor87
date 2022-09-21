package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;

public class Main extends Application {
    GameMap map = MapLoader.loadMap(LEVEL.START.getMapLevel());
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
        makeMusic(Sound.MUSIC.getFilePath());
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
                Cell currentTile = map.getPlayer().getCell();
                map.getPlayer().addInventory(currentTile.getItem());
                currentTile.setType(CellType.FLOOR);
                currentTile.setItem(null);
                refresh();
                break;
            case I:
                ui.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 1);
                ui.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 2);
                String inventory = map.getPlayer().inventoryToString();
                ui.add(new Label("Inventory:"),0,1);
                ui.add(new Label(inventory),0,2);
                refresh();
                break;
            case ESCAPE:
                ui.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 1);
                ui.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 2);
                refresh();
                break;

        }
    }

    private void refresh() {
        moveMonsters();
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
        if (!map.getPlayer().isAlive()) {
            alertUser("You've lost","Sorry but you killed by a monster.", Alert.AlertType.WARNING).showAndWait();
            map = MapLoader.loadMap(LEVEL.END.getMapLevel());
        }
        if(map.getPlayer().getCell().getType() == CellType.QUIT) System.exit(1);
        else if (map.getPlayer().getCell().getType() == CellType.REPEAT) {
            map = MapLoader.loadMap(LEVEL.START.getMapLevel());
            refresh();
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

    private void makeMusic(String filepath) {
        try{
            final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event)
                {
                    if (event.getType() == LineEvent.Type.STOP)
                        clip.start();
                }
            });

            clip.open(AudioSystem.getAudioInputStream(new File(filepath)));
            clip.start();
        }
        catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public Alert alertUser(String title, String message, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert;
    }
}
