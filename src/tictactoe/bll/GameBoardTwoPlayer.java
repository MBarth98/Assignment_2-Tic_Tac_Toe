package tictactoe.bll;


/**
 * The GameBoardTwoPlayer class is the mandatory implementation for the TicTacToe assignment.
 * It is used for games where there are two human players.
 */
public class GameBoardTwoPlayer implements IGameModel {

    int[][] GameBoardMatrix = new int[3][3];

    private int currentWinner = -1;

    private int currentPlayer = 0;
    private int CountRound = 0;
    private int Draw;


    protected GameBoardTwoPlayer() {
        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                GameBoardMatrix[i][k] = -1;
            }
        }
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer() {
        if (CountRound % 2==0)
            currentPlayer = 0;
        else
            currentPlayer = 1;

        return currentPlayer;

    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    @Override
    public boolean play(int col, int row) {


        if (currentPlayer == 0 && getPlayerAt(col,row) == -1){
            GameBoardMatrix[col][row] = 0;
            CountRound++;
            return true;
        }
        else if (currentPlayer == 1 && getPlayerAt(col,row) == -1){
            GameBoardMatrix[col][row] = 1;
            CountRound++;
            return true;
        }

        if (col == 1){
            System.out.println("Placed");

            return true;
        }
        System.out.println("Something went wrong");

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

            boolean rowFull = (left != -1 && middle != -1 && right != -1);
            boolean rowEqual = rowFull && (left == middle && left == right);

            boolean colFull = (top != -1 && center != -1 && bottom != -1);
            boolean colEqual = colFull && (top == center && top == bottom);

            if (rowEqual||colEqual)
            {
                currentWinner = left;
                System.out.println("gameover");

                return true;
            }


        }

        int topLeft     = GameBoardMatrix[0][0];
        int bottomRight = GameBoardMatrix[2][2];
        int center      = GameBoardMatrix[1][1];

        int topRight = GameBoardMatrix[2][0];
        int bottomLeft = GameBoardMatrix[0][2];


        boolean rowFull = (topLeft != -1 && bottomRight != -1 && center != -1);
        boolean rowEqual = rowFull && (topLeft == center&& topLeft == bottomRight);

        boolean colFull = (topRight != -1 && center != -1 && bottomLeft != -1);
        boolean colEqual = colFull && (topRight== center && topRight == bottomLeft);

        if (rowEqual||colEqual)
        {
            currentWinner = center;
            System.out.println("gameover");

            return true;
        }

        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner() {
        return currentWinner;
    }


    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame() {
        currentWinner = -1;
        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                System.out.println(GameBoardMatrix[i][k]);
                GameBoardMatrix[i][k] = -1;
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
    public int getPlayerAt(int col, int row) {
        return GameBoardMatrix[col][row];
    }

}