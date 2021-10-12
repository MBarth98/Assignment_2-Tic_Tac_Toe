package tictactoe.bll;

public class DumDumAI implements IAiModel
{
    /**
     * Ai thats takes a random spot on the map.
     *
     *  it use 2 random number generator one for row other for colum and checks if that spot is 1 or 0 then it is taken if it is -1 it will place it there.
     */
    @Override
    public void makeMove(int [][] gameBoard)
    {
        int max = 2;
        int min = 0;
        int range = max - min +1;
        int row = (int) (Math.random() * range) +min;
        int colum = (int) (Math.random() * range) + min;
        while (gameBoard[row][colum] != -1)
        {
            row = (int) (Math.random() * range) +min;
            colum = (int) (Math.random() * range) + min;
        }
        if (gameBoard[row][colum] == -1)
        {
            gameBoard[row][colum] = 1;
        }
    }
}
