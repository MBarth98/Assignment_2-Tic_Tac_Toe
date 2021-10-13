package tictactoe.bll;

public class DumDumAI implements IAiModel
{
    /**
     * Ai thats takes a random spot on the map.
     *
     *  it use 2 random number generator one for row other for colum and checks if that spot is 1 or 0 then it is taken if it is -1 it will place it there.
     */
    @Override
    public void makeMove(int[][] gameBoard)
    {
        int column;
        int row;

        do
        {
            column  = (int)(Math.random() * gameBoard.length);
            row     = (int)(Math.random() * gameBoard.length);

        } while (gameBoard[column][row] != IGameModel.EMPTY_PLAYER_ID);

        if (gameBoard[column][row] == IGameModel.EMPTY_PLAYER_ID)
        {
            gameBoard[column][row] = IGameModel.PLAYER_TWO_ID;
        }
    }
}
