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
        String winner = winner;

        instance.setNextWinner(winner);

        int result = instance.getWinners().size();
        Assertions.assertEquals(expectedLength, result);
    }
int xWinCount = 0;
int oWinCount = 0;

private void incrementWinner( String winner )
{
    if( "X".equals( winner ) )
    {
        xWinCount += 1;
    }
    else
    {
        oWinCount += 1;
    }
      
}
    /**
     * Assert that the last winner is always placed at the first position in the list of winners.
     */
    @Test
    void setTwoWinners()
    {
        ScoreModel instance = new ScoreModel();
        String winnerOne = x;
        String winnerTwo = o;

        instance.setNextWinner(winnerOne);
        instance.setNextWinner(winnerTwo);

        String actualTopWinner = instance.getWinners().get(0);
        Assertions.assertEquals(winnerTwo, actualTopWinner);
    }

}
