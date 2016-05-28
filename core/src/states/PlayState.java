package states;

import Input.MyInputHandler;
import board.BoardHandler;
import board.Space;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caleb on 5/15/2016.
 */
public class PlayState extends State {

    private Space                   space[][];          //2D Array of space Reference's
    private BoardHandler            boardHandler;       //Reference to BoardHandler.java
    private MyInputHandler          myInputHandler;
    public static boolean           gameOver;           //GameOver
    public static int               disarmFlags;        //Number of disarmed Flags
    private ArrayList<Space>        alreadyDone;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);

        gameOver = false;

        boardHandler = new BoardHandler();
        myInputHandler = new MyInputHandler();
        alreadyDone = new ArrayList<Space>();
        Gdx.input.setInputProcessor(myInputHandler);
        boardHandler.createBoard();
        disarmFlags = boardHandler.getNumOfMines();
        //Set Space size to boardHandler.getBoardSize() for X and Y
        space = new Space[boardHandler.getBoardSize()][boardHandler.getBoardSize()];

        //Loop through and create each space
        for (int i = 0; i < boardHandler.getBoardSize(); i++)
        {
            for (int j = 0; j < boardHandler.getBoardSize(); j++)
            {
                createSpace(i,j);
            }
        }
    }

    //Create Space
    //Create Sprite for space
    //Set position of space sprite
    private void createSpace(int i, int j)
    {
        space[i][j] = new Space(boardHandler, myInputHandler, this, i, j);
        space[i][j].createSprite("blank.png");
        space[i][j].setPosition();
        space[i][j].setSpaceValue(boardHandler.getBoard()[i][j]);
    }

    //Handle Mouse
    //Convert mousePos to screen coordinates using cam.unproject
    //Pass mousePos to each space class for testing
    @Override
    public void handleInput() {
        Vector3 mousePos = new Vector3();

        cam.unproject(mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        for (int i = 0; i < boardHandler.getBoardSize(); i++)
        {
            for (int j = 0; j < boardHandler.getBoardSize(); j++)
            {
                space[i][j].handleInput(mousePos);
            }
        }
    }

    //Call Update for each space
    @Override
    public void update(float dt) {
        for(int i = 0; i < boardHandler.getBoardSize(); i++)
        {
            for (int j = 0; j < boardHandler.getBoardSize(); j++)
            {
                space[i][j].update(dt);
            }
        }
        //If gameOver expose each mine (skip clicked or "exposed" mine which needs to not be drawn over)
        //Create new State - GameOverState -
        if (gameOver)
        {
            for (int i = 0; i < boardHandler.getBoardSize(); i++)
            {
                for (int j = 0; j < boardHandler.getBoardSize(); j++)
                {
                    if (space[i][j].isMine == true && space[i][j].spaceState != Space.SpaceState.Exposed)
                    {
                        space[i][j].setExposedTexture();
                        space[i][j].switchTexture(space[i][j].getExposedTexture());
                    }
                }
            }
            gsm.push(new GameOverState(gsm, this));
        } //TODO Write space branch out for blank spaces
    }
    // Call render from each space
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        for (int i = 0; i < boardHandler.getBoardSize(); i++)
        {
            for (int j = 0; j < boardHandler.getBoardSize(); j++)
            {
                space[i][j].render(sb);
            }
        }
        sb.end();
    }

    public void checkAdjacentSpaces(Space singleSpace)
    {   //TODO CheckAdjacentSpaces is working for the most part. fixme its not fully checking all the north and south squares
        if (singleSpace.spaceState != Space.SpaceState.Exposed && singleSpace.getSpaceValue().equals("1") ||
                singleSpace.getSpaceValue().equals("2") || singleSpace.getSpaceValue().equals("3") ||
                singleSpace.getSpaceValue().equals("4") || singleSpace.getSpaceValue().equals("5") ||
                singleSpace.getSpaceValue().equals("6") || singleSpace.getSpaceValue().equals("7") ||
                singleSpace.getSpaceValue().equals("8"))
        {
            exposeSpace(singleSpace);
            return;
        }
        if (!singleSpace.getSpaceValue().equals("M") && singleSpace.spaceState != Space.SpaceState.Exposed)
        {
            int i = singleSpace.getI();
            int j = singleSpace.getJ();

            exposeSpace(singleSpace);

            for (int ii = i -1; ii < i + 2; ii++)
            {
                for (int jj = j - 1; jj < j + 2; jj++)
                {
                    if (boardHandler.indexInBounds(ii, jj)) {
                        if (space[ii][j].spaceState != Space.SpaceState.Exposed)
                        {
                            checkAdjacentSpaces(space[ii][jj]);
                        }
                    }
                }
            }
        }

        else
        {
            return;
        }
    }

    private void exposeSpace(Space space)
    {
        space.setExposedTexture();
        space.switchTexture(space.getExposedTexture());
        space.spaceState = Space.SpaceState.Exposed;
    }

    //Dispose textures from each space
    @Override
    public void dispose() {
        for (int i = 0; i < boardHandler.getBoardSize(); i++)
        {
            for (int j = 0; j < boardHandler.getBoardSize(); j++)
            {
                space[i][j].dispose();
            }
        }
    }
}