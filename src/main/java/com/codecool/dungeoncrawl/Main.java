package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.MovableMonster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;
import com.codecool.dungeoncrawl.view.PopupFeedback;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.util.List;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.util.Scanner;

public class Main extends Application {
    Level level = Level.MENU;
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
        drawMap();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        makeBackgroundMusic(Sound.MUSIC.getFilePath());
        map.getPlayer().setName(PopupFeedback.getPlayerName());
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
                map.addInventory(false);
                drawMap();
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
            case F:
                Stage saveStage = new Stage();
                fileSave(saveStage);
                break;
            case L:
                loadGame();
                break;
            case G:
                loadGameFromJson();
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
            Integer potentialId = dbManager.getPlayerIdFromName(map.getPlayer());
            if (potentialId == null || potentialId == 0){
                PopupFeedback.feedBackFailedLoad(map.getPlayer().getName());
                return;
            } else {
                playerId = potentialId;
            }
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
        drawMap();
        PopupFeedback.feedBackSuccessfulLoad(map.getPlayer().getName());
    }

    private void loadGameFromJson()  { // todo combine with loadGame method
        try {
            GameState currentGameState = fileLoad();
            PlayerModel currentPlayerModel = currentGameState.getPlayer();
            Player newPlayer = new Player(currentPlayerModel);
            GameMap newMap = MapLoader.loadMap(currentGameState, newPlayer);
            newPlayer.getCell().setGameMap(newMap);
            map = newMap;
            drawMap();
            PopupFeedback.feedBackSuccessfulLoad(map.getPlayer().getName());
        } catch (NullPointerException e) {
            PopupFeedback.cantLoadFile();
        }
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
        Player player = map.getPlayer();
        if (!player.isAlive()) {
            playerIsDead(player);
        } else if (player.isPlayerOnQuitCell()) {
            exitGame();
        } else if (player.isPlayerOnPlayCell()) {
            startTheFirstLevel(player);
        } else if (player.isPlayerOnOpenDoor()) {
            if (map.isThisTheLastLevel()) {
                playerHasWon(player);
            }
            map = MapLoader.loadMap(level.nextLevel().getMapLevel(), player);
            level = level.nextLevel();
        }
    }

    private void playerIsDead(Player player) {
        ButtonType button = PopupFeedback.playerLost();
        if (button == ButtonType.OK) {
            player.resetPlayer();
            map = MapLoader.loadMap(Level.MENU.getMapLevel(), player);
            refresh();
        }
    }

    private void exitGame() {
        System.exit(1);
    }

    private void startTheFirstLevel(Player player) {
        level = Level.MAP_1;
        map = MapLoader.loadMap(level.getMapLevel(), player);
        refresh();
    }

    private void playerHasWon(Player player) {
        ButtonType button = PopupFeedback.playerWon();
        if (button == ButtonType.OK) {
            player.resetPlayer();
            map = MapLoader.loadMap(Level.MENU.getMapLevel(), player);
            refresh();
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
    
    private String getCurrentGameState() { //TODO getCurrentGameState similar to savestate in GameDBManager
        Player  player = map.getPlayer();
        PlayerModel playerModel = new PlayerModel(player);
        Date date = new java.sql.Date(System.currentTimeMillis());
        String currentMap = map.toString();
        GameState state = new GameState(currentMap, date, playerModel, map.getLevel());
        return new Gson().toJson(state);
    }

    private void fileSave(Stage primaryStage) {
        primaryStage.setTitle("Make your JSON file here!");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("filename.json");
        File file = fileChooser.showSaveDialog(primaryStage); //todo title is not visible
        if (file != null) {
            writeJsonFile(file);
        }
    }

    private GameState fileLoad(){
        Stage loadStage = new Stage();
        loadStage.setTitle("Choose file to load");
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(loadStage);
            try {
                Scanner scanner = new Scanner(file);
                String jsonString = "";
                while (scanner.hasNextLine()) {
                    jsonString = scanner.nextLine();
                }
                scanner.close();
                return new Gson().fromJson(jsonString, GameState.class);
            } catch (Exception e) {
                System.out.println("something went wrong");
            }
        return null;
    }

    private void writeJsonFile(File file) {
        try (FileWriter filewriter = new FileWriter(file)) {
            filewriter.write(getCurrentGameState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
