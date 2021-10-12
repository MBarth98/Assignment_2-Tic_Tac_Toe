package tictactoe.gui.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoreModelTest {

    /**
     * Assert that if a winner is added to the ScoreModel the list of winners grows proportionally.
     */
    @Test
    void setNextWinner() {
        ScoreModel instance = new ScoreModel();
        int expectedLength = 1;
        String winner = "Peter";

        instance.AddNextWinner(winner);

        int result = instance.getWinners("player 1").size();
        Assertions.assertEquals(expectedLength, result);
    }

    /**
     * Assert that the last winner is always placed at the first position in the list of winners.
     */
    @Test
    void setTwoWinners()
    {
        ScoreModel instance = new ScoreModel();
        String winnerOne = "Peter";
        String winnerTwo = "Jeppe";

        instance.AddNextWinner(winnerOne);
        instance.AddNextWinner(winnerTwo);

        String actualTopWinner = instance.getWinners("player 1").get(0);
        Assertions.assertEquals(winnerTwo, actualTopWinner);
    }

}