package tictactoe.bll;


/**
 * The GameBoardTwoPlayer class is the mandatory implementation for the TicTacToe assignment.
 * It is used for games where there are two human players.
 */
public class GameBoardTwoPlayer extends AbstractGameBoard
{
    protected GameBoardTwoPlayer()
    {
        newGame();
    }

    @Override
    public boolean play(int col, int row)
    {
        setCurrentPlayer(getNextPlayer());

        if (getPlayerAt(col, row) == EMPTY_PLAYER_ID)
        {
            GameBoardMatrix[col][row] = getCurrentPlayer();
            return true;
        }

        return false;
    }
}