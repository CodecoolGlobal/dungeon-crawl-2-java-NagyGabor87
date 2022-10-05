package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.MovableMonster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.util.PopupFeedback;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.util.List;
import java.util.Optional;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;

public class Main extends Application {
    LEVEL level = LEVEL.MENU;
    GameMap map = MapLoader.loadMap(level.getMapLevel(), null);
    GridPane ui = new GridPane();

    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    GameDatabaseManager dbManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        setupDbManager();

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
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        makeBackgroundMusic(Sound.MUSIC.getFilePath());
        map.getPlayer().setName(getPlayerName());
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
    }
    private void exit () {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
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
            case SPACE:
                map.addInventory();
                break;
            case I:
                clearInventoryText();
                String inventory = map.getPlayer().inventoryToString();
                ui.add(new Label("Inventory:"), 0, 1);
                ui.add(new Label(inventory), 1, 1);
                break;
            case TAB:
                clearInventoryText();
                break;
            case S:
                saveGame();
                break;
            case L:
                loadGame();
                break;

        }
    }

    private void saveGame() {
        // get all players -> check if player has already saved, get the player id
        Integer playerID = dbManager.getPlayerId(map.getPlayer());
        // if not -> save the state
        if (playerID == null || playerID == 0) {
            dbManager.saveState(map);
            PopupFeedback.feedbackSave(map.getPlayer().getName());
            return;
        }

        // if player present -> check if there is a state with player id
        Integer stateId = dbManager.getGameState(playerID);
        // if not, -> save the state
        if (stateId == null || stateId == 0) {
            dbManager.saveState(map);
            PopupFeedback.feedbackSave(map.getPlayer().getName());
            return;
        }

        // if present -> popup window to override?
        boolean override = PopupFeedback.isOverrideSavedGame();
        // override -> update state with player i
        if (override) {
            dbManager.updateState(stateId, map);
            PopupFeedback.feedbackSave(map.getPlayer().getName());
        }
        // no override -> just exit
    }

    private void loadGame() {
        Integer playerId = map.getPlayer().getId();
        if (playerId == null || playerId == 0) {
            PopupFeedback.feedBackFailedLoad(map.getPlayer().getName());
            return;
        }

        // player id exists, fetch raw player data by player id
        PlayerModel playerModel = dbManager.getPlayerByID(playerId);

        // create player object from player model
        Player newPlayer = new Player(playerModel);

        // create game state model from raw data
        GameState gameState = dbManager.getGameStateByPlayerId(playerId);

        // fill up new map with maploader
        GameMap newMap = MapLoader.loadMap(gameState, newPlayer);


        newPlayer.getCell().setGameMap(newMap);
        map = newMap;
        PopupFeedback.feedBackSuccessfulLoad(map.getPlayer().getName());
    }

    private void removeDisappearingWall() {
        if (map.getSkeletonCount() <= 0) {
            for (Cell[] mapRow : map.getCells()) {
                for (Cell cell : mapRow) {
                    if (cell.getType().equals(CellType.DISAPPEARING_WALL)) {
                        cell.setType(CellType.FLOOR);
                    }
                }
            }
        }
    }

    private void clearInventoryText() {
        ui.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 1);
        ui.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 1);
    }

    private void refresh() {
        moveMonsters();
        removeDisappearingWall();
        drawMap();
        animateTorchFlame();
        stepNextLevel();
    }

    private void animateTorchFlame() {
        for (Cell torch: map.getTorches()) {
            switch (torch.getType()) {
                case TORCH_A:
                    torch.setType(CellType.TORCH_B);
                    break;
                case TORCH_B:
                    torch.setType(CellType.TORCH_A);
                    break;
            }
        }
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
            ButtonType button = alertUser("You've lost", "Sorry but you were killed by a monster.", Alert.AlertType.WARNING).orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {
                map.getPlayer().resetAlive();
                map = MapLoader.loadMap(LEVEL.MENU.getMapLevel(), null);
                return;
            }
        }
        if (map.getPlayer().getCell().getType() == CellType.QUIT) System.exit(1);
        else if (map.getPlayer().getCell().getType() == CellType.PLAY) {
            level = LEVEL.MAP_1;
            map = MapLoader.loadMap(level.getMapLevel(), map.getPlayer());
            refresh();
        } else if (map.getPlayer().getCell().getType() == CellType.OPEN_DOOR) {
            if (map.getLevel().equals(LEVEL.MAP_4.getMapLevel())) {
                ButtonType button = alertUser("You've won", "Congratulations! You've won the game!.", Alert.AlertType.INFORMATION).orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    map = MapLoader.loadMap(LEVEL.MENU.getMapLevel(), null);
                    return;
                }
            }
            map = MapLoader.loadMap(level.nextLevel().getMapLevel(), map.getPlayer());
            level = level.nextLevel();
        }
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
        }
    }

    private void moveMonsters() {
        List<MovableMonster> movingMonsters = map.getMovableMonsters();
        for (MovableMonster movingMonster : movingMonsters) {
            int newX = movingMonster.generateNextStepX();
            int newY = movingMonster.generateNextStepY();
            movingMonster.move(newX,newY);
        }
    }

    private void makeBackgroundMusic(String filepath) {
        try {
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
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public Optional<ButtonType> alertUser(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert.showAndWait();
    }


    private String getPlayerName() {
        String playerName = null;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Player name");
        dialog.setHeaderText("Player name");
        dialog.setContentText("Please enter your name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            playerName = result.get();
        }
        if (playerName == null || playerName.length() == 0) {
            playerName = getPlayerName();
        }
        return playerName;
    }
}
