package tictactoe.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class ScoreModel {

    private final ObservableList<String> winners;


    public ScoreModel() {
        winners = FXCollections.observableArrayList();
    }

    public ObservableList<String> getWinners(String s) {
        return winners;
    }

    public void AddNextWinner(String winner) {
       if (winner.equals("-1")) {
           winners.add("Draw");
       }
       else if (winner.equals("0")) {
           winners.add("Player 0");
       }
       else if (winner.equals("1")) {
           winners.add("Player 1");
       }
    }

}