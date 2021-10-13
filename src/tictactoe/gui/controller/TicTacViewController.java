/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import tictactoe.bll.AiFactory;
import tictactoe.bll.GameBoardFactory;
import tictactoe.bll.IGameModel;
import tictactoe.gui.model.ScoreModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Stegger
 */
public class TicTacViewController implements Initializable {
    @FXML
    private ChoiceBox<GameBoardFactory.GAME_MODE> choicePlayMode;

    @FXML
    private ChoiceBox<AiFactory.AI_TYPES> choiceAiDiff;

    @FXML
    private ListView<String> lstScores;

    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;

    /**
     * The prefix text that is shown before the actual player who's turn it is.
     */
    private static final String TXT_PLAYER = "Player: ";

    private GameBoardFactory.GAME_MODE currentGameMode;
    private AiFactory.AI_TYPES currentAiDiff;
    private IAiModel ai;
    private IGameModel game;
    private ScoreModel scoreModel;

    /**
     * Initialize method is called at construction time AFTER the constructor is called, and after all GUI controls are created.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scoreModel = new ScoreModel();
        lstScores.setItems(scoreModel.getWinners("player 1"));
        choicePlayMode.getItems().addAll(GameBoardFactory.GAME_MODE.values());
        choicePlayMode.getSelectionModel().selectLast();
        currentGameMode = choicePlayMode.getSelectionModel().getSelectedItem();

        choiceAiDiff.getItems().addAll(AiFactory.AI_TYPES.values());
        choiceAiDiff.getSelectionModel().selectLast();
        currentAiDiff = choiceAiDiff.getSelectionModel().getSelectedItem();

        ai = AiFactory.createAI(currentAiDiff);
        game = GameBoardFactory.getGameModel(currentGameMode);
        setPlayer();
    }

    /**
     * Event handler that is called whenever a Player clicks a button in the game field.
     *
     * @param event The Button click event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            if (game.play(c, r)) {
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                    scoreModel.AddNextWinner(winner + "");
                } else {
                    setPlayer();
                }
                updateGameBoardButtons();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will update the user interface by asking the GameBoard for who have played the individual field.
     * This is necessary when playing the single player game.
     */
    private void updateGameBoardButtons() {
        Integer col;
        Integer row;
        int c;
        int r;
        int player;
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            row = GridPane.getRowIndex(n);
            col = GridPane.getColumnIndex(n);
            r = (row == null) ? 0 : row;
            c = (col == null) ? 0 : col;
            player = game.getPlayerAt(c, r);
            if (player != -1) {
                String xOrO = player == 0 ? "X" : "O";
                btn.setText(xOrO);
            }
        }
    }

    /**
     * Event handler that is called whenever the players want's to start a new game.
     * The method will switch game mode if the player has chosen so.
     *
     * @param event The action event performed by the user.
     */
    @FXML
    private void handleNewGame(ActionEvent event)
    {
        if (choicePlayMode.getSelectionModel().getSelectedItem() == GameBoardFactory.GAME_MODE.SINGLE_PLAYER)
        {
            choiceAiDiff.setDisable(false);
            choiceAiDiff.setOpacity(100);
        }
        else
        {
            choiceAiDiff.setDisable(true);
            choiceAiDiff.setOpacity(0);
        }
        if (currentGameMode == choicePlayMode.getSelectionModel().getSelectedItem()) {
            if (choiceAiDiff.getSelectionModel().getSelectedItem() == AiFactory.AI_TYPES.DUMDUM_AI) {
                AiFactory.setInstance(AiFactory.AI_TYPES.DUMDUM_AI);
                game.newGame();
            }
            else if (choiceAiDiff.getSelectionModel().getSelectedItem() == AiFactory.AI_TYPES.CLEVER_AI){
                AiFactory.setInstance(AiFactory.AI_TYPES.CLEVER_AI);
                game.newGame();
            }
            else if (choiceAiDiff.getSelectionModel().getSelectedItem() == AiFactory.AI_TYPES.CHEATING_AI){
                AiFactory.setInstance(AiFactory.AI_TYPES.CHEATING_AI);
                game.newGame();
            }
        } else {
            currentGameMode = choicePlayMode.getSelectionModel().getSelectedItem();
            game = GameBoardFactory.getGameModel(currentGameMode);

            AiFactory.setInstance(AiFactory.AI_TYPES.CLEVER_AI);
        }

        setPlayer();
        clearBoard();
    }

    /**
     * Updates the label displaying who's turn it is.
     */
    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer());
    }

    /**
     * Updates the label to display the winner of the game.
     *
     * @param winner The integer value of the player who won the game.
     */
    private void displayWinner(int winner) {
        String message;
        if (winner == -1) {
            message = "It's a draw :-(";
        } else {
            message = "Player " + winner + " wins!!!";
        }
        lblPlayer.setText(message);
    }

    /**
     * Clears the board so that all Buttons of the board do NOT display anything.
     */
    private void clearBoard() {
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
        }
    }

}
