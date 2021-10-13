package tictactoe.bll;

public class CleverAI implements IAiModel
{

    @Override
    public void makeMove(int [][] gameBoard)
    {
        for (int i = 0; i < 3; i++)
        {
            int left    = gameBoard[i][0];
            int middle  = gameBoard[i][1];
            int right   = gameBoard[i][2];

            int top     = gameBoard[0][i];
            int center  = gameBoard[1][i];
            int bottom  = gameBoard[2][i];

            boolean rowFull = (left != -1 && middle != -1 && right != -1);
            boolean rowEqual = rowFull && (left == middle && left == right);

            boolean colFull = (top != -1 && center != -1 && bottom != -1);
            boolean colEqual = colFull && (top == center && top == bottom);

            if (rowEqual||colEqual)
            {

            }


        }

        int topLeft     = gameBoard[0][0];
        int bottomRight = gameBoard[2][2];
        int center      = gameBoard[1][1];

        int topRight = gameBoard[2][0];
        int bottomLeft = gameBoard[0][2];


        boolean rowFull = (topLeft != -1 && bottomRight != -1 && center != -1);
        boolean rowEqual = rowFull && (topLeft == center&& topLeft == bottomRight);

        boolean colFull = (topRight != -1 && center != -1 && bottomLeft != -1);
        boolean colEqual = colFull && (topRight== center && topRight == bottomLeft);

        if (rowEqual||colEqual)
        {

        }
    }
}
