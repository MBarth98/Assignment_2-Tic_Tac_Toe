package tictactoe.bll;

/**
 * The GameBoardSinglePlayer class is the optional and advanced implementation for the TicTacToe assignment.
 * It is used for games where there are one human player vs. a computer player.
 */
public class GameBoardSinglePlayer implements IGameModel
{

    private int currentPlayer = PLAYER_ONE_ID;
    private int CountRound = 0;
    int[][] GameBoardMatrix = new int[GAMEBOARD_LENGHT][GAMEBOARD_HEIGHT];
    private int currentWinner = EMPTY_PLAYER_ID;
    private IAiModel currentAI;

    protected GameBoardSinglePlayer()
    {
        newGame();
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer()
    {
        if (CountRound % 2 == 0)
            currentPlayer = PLAYER_ONE_ID;
        else
            currentPlayer = PLAYER_TWO_ID;

        return currentPlayer;
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
        if (getPlayerAt(column, row) == EMPTY_PLAYER_ID)
        {
            if (currentPlayer == PLAYER_ONE_ID)
            {
                GameBoardMatrix[column][row] = PLAYER_ONE_ID;
                CountRound++;

                if (!checkBoardIsFull())
                {
                    currentAI.makeMove(GameBoardMatrix);
                    CountRound++;
                }
                return true;
            }
        }

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
        for (int i = 0; i < 3; i++)
        {
            int left    = GameBoardMatrix[i][0];
            int middle  = GameBoardMatrix[i][1];
            int right   = GameBoardMatrix[i][2];

            int top     = GameBoardMatrix[0][i];
            int center  = GameBoardMatrix[1][i];
            int bottom  = GameBoardMatrix[2][i];

            boolean rowFull = (left != EMPTY_PLAYER_ID && middle != EMPTY_PLAYER_ID && right != EMPTY_PLAYER_ID);
            boolean rowEqual = rowFull && (left == middle && left == right);

            boolean colFull = (top != EMPTY_PLAYER_ID && center != EMPTY_PLAYER_ID && bottom != EMPTY_PLAYER_ID);
            boolean colEqual = colFull && (top == center && top == bottom);

            if (rowEqual || colEqual)
            {
                currentWinner = middle == 0 ? PLAYER_ONE_ID : PLAYER_TWO_ID;

                return true;
            }
        }

        int topLeft     = GameBoardMatrix[0][0];
        int bottomRight = GameBoardMatrix[2][2];
        int center      = GameBoardMatrix[1][1];
        int topRight    = GameBoardMatrix[2][0];
        int bottomLeft  = GameBoardMatrix[0][2];

        boolean rowFull = (topLeft != EMPTY_PLAYER_ID && bottomRight != EMPTY_PLAYER_ID && center != EMPTY_PLAYER_ID);
        boolean rowEqual = rowFull && (topLeft == center&& topLeft == bottomRight);

        boolean colFull = (topRight != EMPTY_PLAYER_ID && center != EMPTY_PLAYER_ID && bottomLeft != EMPTY_PLAYER_ID);
        boolean colEqual = colFull && (topRight== center && topRight == bottomLeft);

        if (rowEqual || colEqual)
        {
            currentWinner = center == 0 ? PLAYER_ONE_ID : PLAYER_TWO_ID;
            return true;
        }

        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw or if the game is still running.
     *
     * @return int id of winner, or -1 if draw or if gameOver() == false.
     */
    @Override
    public int getWinner()
    {
        return currentWinner;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame()
    {
        currentAI = AiFactory.createAI();
        currentPlayer = PLAYER_ONE_ID;
        CountRound = 0;

        for (int column = 0; column < GAMEBOARD_LENGHT; column++)
        {
            for (int row = 0; row < GAMEBOARD_HEIGHT; row++)
            {
                GameBoardMatrix[column][row] = EMPTY_PLAYER_ID;
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
     * Ai thats takes a random spot on the map.
     *
     *  it use 2 random number generator one for row other for colum and checks if that spot is 1 or 0 then it is taken if it is -1 it will place it there.
     */
    public void SingleAIDumDum()
    {
        int column;
        int row;

        do
        {
            column     = (int)(Math.random() * GameBoardMatrix.length);
            row  = (int)(Math.random() * GameBoardMatrix.length);
        }
        while (GameBoardMatrix[column][row] != EMPTY_PLAYER_ID);

        if (GameBoardMatrix[column][row] == EMPTY_PLAYER_ID)
        {
            GameBoardMatrix[column][row] = PLAYER_TWO_ID;
        }
    }

    /**
     * Check if the the boards is full, AI uses it to stop itself from trying to fing a empty spot.
     *
     * @return it returns true if the boards is full it counts steady op to 8 (it counts 0 as 1) if tempCount is not 8 it returns false so the AI can still place.
     */
    public boolean checkBoardIsFull()
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
