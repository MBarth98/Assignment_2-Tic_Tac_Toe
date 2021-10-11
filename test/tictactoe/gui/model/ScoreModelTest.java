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
// Save player scores
	this.scores = {
		X: 0,
		O: 0
	};

	this.marks = {
		X: "X",  // Player 1 mark
		O: "O",  // Player 2 mark
		count: 0 // Number of moves made by player
	};
    function onResult(result, scores) {
	if(result == 'draw') {
		alert("It's a draw !");
	} else {
		alert(result + " has won");
		updateScores(scores.X, scores.O);
	}
     	tictactoe.empty();
}

function updateScores(X, O) {
	document.querySelector("#scoreboard #player1").innerHTML = X;
	document.querySelector("#scoreboard #player2").innerHTML = O;	
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
