package com.codecool.dungeoncrawl.logic.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class PopupFeedback {
    public static String getPlayerName() {
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
    public static void feedBackSuccessfulLoad(String playerName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game loaded");
        alert.setHeaderText("Your game is loaded");
        alert.setContentText(playerName + " your game loaded successfully");
        alert.showAndWait();
    }

    public static void feedBackFailedLoad(String playerName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game can't load");
        alert.setHeaderText("Your game can't load");
        alert.setContentText(playerName + " you don't have any loaded games");
        alert.showAndWait();
    }

    public static void feedbackSave(String playerName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game is saved");
        alert.setHeaderText("Your game is saved");
        alert.setContentText(playerName + " your game is saved successfully!");
        alert.showAndWait();
    }

    public static boolean isOverrideSavedGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Override saved game?");
        alert.setHeaderText("Override saved game?");
        alert.setContentText("You already have a saved game. Do you want to override it?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
