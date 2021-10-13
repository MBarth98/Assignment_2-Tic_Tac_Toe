package tictactoe.bll;

public class CheatingAI implements IAiModel
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

            boolean rowHalf = (left != -1 && middle != -1);
            boolean rowShalf = (middle != -1 && right != -1);
            boolean rowEqual = rowHalf && (left == middle);
            boolean rowSEqual = rowShalf && (middle == right);

            boolean colHalf = (top != -1 && center != -1);
            boolean colSHalf = (center != -1 && bottom != -1);
            boolean colEqual = colHalf && (top == center);
            boolean colSEqual = colHalf && (center == bottom);

            if (rowEqual)
            {
                if (gameBoard[i][0] == -1){
                    gameBoard[i][0] = 1;
                }
                else if (gameBoard[i][1] == -1)
                {
                    gameBoard[i][1] = 1;
                }
            }
            else if(rowSEqual)
            {
                if (gameBoard[i][1] == -1){
                    gameBoard[i][1] = 1;
                }
                else if (gameBoard[i][2] == -1)
                {
                    gameBoard[i][2] = 1;
                }
            }
            else if(colEqual)
            {
                if (gameBoard[0][i] == -1){
                    gameBoard[0][i] = 1;
                }
                else if (gameBoard[1][i] == -1)
                {
                    gameBoard[1][i] = 1;
                }
            }
            else if(colSEqual)
            {
                if (gameBoard[1][i] == -1){
                    gameBoard[1][i] = 1;
                }
                else if (gameBoard[2][i] == -1)
                {
                    gameBoard[2][i] = 1;
                }
            }
            else
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
