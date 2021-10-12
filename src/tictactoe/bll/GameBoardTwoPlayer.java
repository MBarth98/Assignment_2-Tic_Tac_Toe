package tictactoe.bll;


/**
 * The GameBoardTwoPlayer class is the mandatory implementation for the TicTacToe assignment.
 * It is used for games where there are two human players.
 */
public class GameBoardTwoPlayer implements IGameModel {

    int[][] GameBoardMatrix = new int[3][3];

    public int currentWinner = -1;
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
    public boolean isGameOver() {

        for (int i =0; i <3;i++){
            boolean xy= GameBoardMatrix[i][0]==GameBoardMatrix[i][1];
            boolean xz= GameBoardMatrix[i][0]==GameBoardMatrix[i][2];
            boolean yz= GameBoardMatrix[i][1]==GameBoardMatrix[i][2];

           if (xy && xz && yz)
               return true;
        }
        for (int i =0; i <3;i++) {
            boolean xy = GameBoardMatrix[0][i] == GameBoardMatrix[1][i];
            boolean xz = GameBoardMatrix[0][i] == GameBoardMatrix[2][i];
            boolean yz = GameBoardMatrix[1][i] == GameBoardMatrix[2][i];

            if (xy && xz && yz)
                return true;

        }

            boolean xy= GameBoardMatrix[0][0]==GameBoardMatrix[1][1];
            boolean xz= GameBoardMatrix[0][0]==GameBoardMatrix[2][2];
            boolean yz= GameBoardMatrix[1][1]==GameBoardMatrix[2][2];

            if (xy && xz && yz)
                return true;

         xy= GameBoardMatrix[2][0]==GameBoardMatrix[1][1];
         xz= GameBoardMatrix[2][0]==GameBoardMatrix[0][2];
         yz= GameBoardMatrix[1][1]==GameBoardMatrix[0][2];

        if (xy && xz && yz)
            return true;

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