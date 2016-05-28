package board;

import java.util.Random;

/**
 * Created by Caleb on 5/15/2016.
 */
public class BoardHandler {

    private String                  board[][];              //String[][] which makes up the game "board"
    private Random                  rand = new Random();    //Random Reference
    private int                     boardSize = 20;         //Board Size TODO change to width = 30 and height = 15
    private int                     numOfMines = 50;        //Number of mines on the board

    //Set boad[][] to boardSize (width height)
    public BoardHandler()
    {
        board = new String[boardSize][boardSize];
        rand.setSeed(System.currentTimeMillis());
    }

    //Create Board of empty spaces = '0'
    //AddMines
    //CountMines
    public void createBoard()
    {
        for (int i = 0; i < boardSize; i++)
        {
            for (int j = 0; j < boardSize; j++)
            {
                board[i][j] = "0";
            }
        }

        while (numOfMines > 0)
        {
            addMines();
        }
        //loop through board[][] and count the number of mines each cell is touching
        for (int i = 0; i < boardSize; i++)
        {
            for (int j = 0; j < boardSize; j++)
            {
                if (board[i][j] != "M")
                    board[i][j] = countTouchingMines(i, j);
            }
        }
    }

    //Print board for debugging
    private void printBoard()
    {
        for (int i = 0; i < boardSize; i++)
        {
            for (int j = 0; j < boardSize; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    //AddMines at random positions
    private void addMines()
    {
        int x = rand.nextInt(boardSize);
        int y = rand.nextInt(boardSize);

        //If x and y are free space '0' AddMine else Recursively call self
        if (board[x][y] == "0")
        {
            board[x][y] = "M";
            numOfMines = numOfMines - 1;
        }
        else
        {
            addMines();
        }
    }

    //Count each mine by checking each touching space for a touching mine 'M'
    private String countTouchingMines(int i, int j)
    {
        int touchingMines = 0;

        for (int ii = i - 1; ii < i + 2; ii++)
        {
            for (int jj = j - 1; jj < j + 2; jj++)
            {
                if (indexInBounds(ii, jj))
                {
                    if (board[ii][jj] == "M")
                    {
                        touchingMines++;
                    }
                }
            }
        }
        //return number of touching mines.toString
        return Integer.toString(touchingMines);
    }
    //Check make sure index is inBounds
    public boolean indexInBounds(int x, int y)
    {
        return (x >= 0) && (x < boardSize) && (y >= 0) && (y < boardSize);
    }

    //Return List for Reading
    public String[][] getBoard()
    {
        return board;
    }
    //Return boardSize for reading
    public int getBoardSize()
    {
        return boardSize;
    }

    public int getNumOfMines()
    {
        return numOfMines;
    }
}