package tictactoe.bll;

/**
 * The GameBoardSinglePlayer class is the optional and advanced implementation for the TicTacToe assignment.
 * It is used for games where there are one human player vs. a computer player.
 */
public class GameBoardSinglePlayer extends AbstractGameBoard
{
    public GameBoardSinglePlayer()
    {
        newGame();
    }

    @Override
    public boolean play(int column, int row)
    {
        if (isGameOver())
        {
            return false;
        }

        setCurrentPlayer(getNextPlayer());

        if (getPlayerAt(column, row) == EMPTY_PLAYER_ID)
        {
            if (getCurrentPlayer() == PLAYER_ONE_ID)
            {
                GameBoardMatrix[column][row] = getCurrentPlayer();

                if (getWinner() == EMPTY_PLAYER_ID)
                {
                    setCurrentPlayer(getNextPlayer());
                    currentAI.makeMove(GameBoardMatrix);
                }
                return true;
            }
        }

        return false;
    }

    /**
     * Ai thats takes a random spot on the map.
     *
     *  it use 2 random number generator one for row other for colum and checks if that spot is 1 or 0 then it is taken if it is -1 it will place it there.
     */
    public void SingleAIDumDum()
    {

    }
}
