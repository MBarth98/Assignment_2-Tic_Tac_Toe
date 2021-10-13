package tictactoe.bll;


public abstract class AbstractGameBoard implements IGameModel
{
    private int currentPlayer = EMPTY_PLAYER_ID;

    protected int[][] GameBoardMatrix = new int[GAMEBOARD_LENGHT][GAMEBOARD_HEIGHT];

    protected IAiModel currentAI;

    public int getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer()
    {
        if (currentPlayer == PLAYER_ONE_ID)
        {
            return PLAYER_TWO_ID;
        }
        else
        {
            return PLAYER_ONE_ID;
        }
    }

    /**
     * Attempts to let the current player play at the given coordinates. If the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param column column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    @Override
    public boolean play(int column, int row)
    {
        return false;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    @Override
    public boolean isGameOver()
    {
        boolean winByPartialBoard = getWinner() != EMPTY_PLAYER_ID;
        return winByPartialBoard || isGameBoardFull();
    }

    private boolean isRowFullAndEqual(int index)
    {
        int left    = GameBoardMatrix[index][0];
        int middle  = GameBoardMatrix[index][1];
        int right   = GameBoardMatrix[index][2];

        boolean rowFull = (left != EMPTY_PLAYER_ID && middle != EMPTY_PLAYER_ID && right != EMPTY_PLAYER_ID);
        return rowFull && (left == middle && left == right);
    }

    private boolean isColumnFullAndEqual(int index)
    {
        int top     = GameBoardMatrix[0][index];
        int center  = GameBoardMatrix[1][index];
        int bottom  = GameBoardMatrix[2][index];

        boolean colFull = (top != EMPTY_PLAYER_ID && center != EMPTY_PLAYER_ID && bottom != EMPTY_PLAYER_ID);
        return colFull && (top == center && top == bottom);
    }

    private boolean isDiagonalFullAndEqual()
    {
        int topLeft     = GameBoardMatrix[0][0];
        int bottomRight = GameBoardMatrix[2][2];
        int center      = GameBoardMatrix[1][1];
        int topRight    = GameBoardMatrix[2][0];
        int bottomLeft  = GameBoardMatrix[0][2];

        boolean topToDown = (topLeft != EMPTY_PLAYER_ID && bottomRight != EMPTY_PLAYER_ID && center != EMPTY_PLAYER_ID);
        boolean isTopToDownEqual = topToDown && (topLeft == center && topLeft == bottomRight);

        boolean downToTop = (topRight != EMPTY_PLAYER_ID && center != EMPTY_PLAYER_ID && bottomLeft != EMPTY_PLAYER_ID);
        boolean isDownToTopEqual = downToTop && (topRight== center && topRight == bottomLeft);

        return isDownToTopEqual || isTopToDownEqual;
    }

    /**
     * Gets the id of the winner, -1 if its a draw or if the game is still running.
     *
     * @return int id of winner, or -1 if draw or if gameOver() == false.
     */
    @Override
    public int getWinner()
    {
        if (isDiagonalFullAndEqual())
        {
            return currentPlayer;
        }

        for (int i = 0; i < 3; i++)
        {
            if (isRowFullAndEqual(i) || isColumnFullAndEqual(i))
            {
                return currentPlayer;
            }
        }

        return EMPTY_PLAYER_ID;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame()
    {
        currentPlayer = EMPTY_PLAYER_ID;
        currentAI = AiFactory.createAI();

        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                GameBoardMatrix[i][k] = EMPTY_PLAYER_ID;
            }
        }
    }

    /**
     * Returns the value representing which players has played the given field.
     *
     * @param col The column to look at.
     * @param row The row to look at.
     * @return Will return 0 if player 0 has played the field, 1 for player one, and -1 if no player has played the field.
     */
    @Override
    public int getPlayerAt(int col, int row)
    {
        return GameBoardMatrix[col][row];
    }

    /**
     * Check if the the boards is full, AI uses it to stop itself from trying to fing a empty spot.
     *
     * @return it returns true if the boards is full it counts steady op to 8 (it counts 0 as 1) if tempCount is not 8 it returns false so the AI can still place.
     */
    public boolean isGameBoardFull()
    {
        for (int column = 0; column < GAMEBOARD_LENGHT; column++)
        {
            for (int row = 0; row < GAMEBOARD_HEIGHT; row++)
            {
                if (GameBoardMatrix[column][row] == EMPTY_PLAYER_ID)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
