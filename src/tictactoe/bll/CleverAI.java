package tictactoe.bll;

public class CleverAI implements IAiModel
{
    boolean startup = true;
    int[][] predictionBoard = {
            {2,1,2},
            {1,0,1},
            {2,1,2}
    };

    @Override
    public void makeMove(int[][] gameBoard)
    {
        int bestMoveX = 0;
        int bestMoveY = 0;
        int bestMoveValue = Integer.MAX_VALUE;


        for (int column = 0; column < 3; column++)
        {
            for (int row = 0; row < 3; row++)
            {
                if (gameBoard[column][row] != -1)
                    continue;

                if (predictionBoard[column][row] < bestMoveValue)
                {
                    bestMoveValue = predictionBoard[column][row];
                    bestMoveX = column;
                    bestMoveY = row;
                }
            }
        }
        gameBoard[bestMoveX][bestMoveY] = 1;
    }
}
