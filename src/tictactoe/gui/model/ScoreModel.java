package tictactoe.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tictactoe.bll.IGameModel;

import java.util.Objects;

public class ScoreModel {

    public final ObservableList<String> winners;


    public ScoreModel() {
        winners = FXCollections.observableArrayList();
    }

    public ObservableList<String> getWinners(String s) {
        return winners;
    }

    public void AddNextWinner(String winner)
    {
       if (winner.equals(IGameModel.EMPTY_PLAYER_ID + "")) {
           winners.add("Draw");
       }
       else if (winner.equals(IGameModel.PLAYER_ONE_ID + "")) {
           winners.add("Player 0");
       }
       else if (winner.equals(IGameModel.PLAYER_TWO_ID + "")) {
           winners.add("Player 1");
       }
    }

}