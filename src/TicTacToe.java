import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String player = "X"; // declare player as X
        boolean gameFinished = false;
        // outer loop: continue playing until player chooses to stop
        do
        {
            clearBoard(); // clear board
            display(); // display initial board
            while(!gameFinished) // inner loop: continue taking turns
            {
                System.out.println("Player " + player + ": Make your move (row (1-3) & col (1-3))"); // prompt for player to move
                int row = SafeInput.getRangedInt(in, "Enter row (1-3)", 1, 3) - 1; // row input
                int col = SafeInput.getRangedInt(in, "Enter col (1-3)", 1, 3) - 1; // column input
                if(isValidMove(row, col)) // check if valid
                {
                    board[row][col] = player; // update board
                    display(); // display updated board
                    if(isWin(player)) // check if win
                    {
                        System.out.println("Player " + player + " wins!");
                        gameFinished = true;
                    }
                    if(isTie()) // check if tie
                    {
                        System.out.println("It is a tie!");
                        gameFinished = true;
                    }
                    else // if no win/tie, switch player's turns
                    {
                        if (player.equals("X"))
                        {
                            player = "O";
                        } else {
                            player = "X";
                        }

                    }
                }
                else // move invalid = prompt again
                {
                    System.out.println("Wrong move, try again");
                }
            }
            gameFinished = !SafeInput.getYNConfirm(in, "Do you want to play again"); // ask if want to play again
        } while(!gameFinished);
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j]);
                if (j < COLS - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < ROWS - 1) {
                System.out.println("----------");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }
    private static boolean isRowWin(String player)
    {
        for(int row = 0; row < ROWS; row++)
        {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isColWin(String player)
    {
        for(int col = 0; col < COLS; col++)
        {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagonalWin(String player)
    {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
        {
            return true;
        }
        if  (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
        {
            return true;
        }
        return false;
    }
    private static boolean isTie()
    {
        for(int i = 0; i < ROWS; i++)
        {
            for(int j = 0; j < COLS; j++)
            {
                if(board[i][j].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
