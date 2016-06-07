package board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.io.File;
import java.lang.String;
import java.util.ArrayList;

import com.grimsatisfactions.minesweeper.FileManager;
import com.grimsatisfactions.minesweeper.MineSweeperDemo;
import states.PlayState;
import Input.MyInputHandler;

/**
 * Created by Caleb on 5/15/2016.
 */
public class Space {

    private Texture                     hiddenTexture;          //Hidden Texture reference (Default Texture)
    private Texture                     exposedTexture;         //Exposed Texture reference (Blank, Mine, Number)
    private Texture                     disarmedTexture;        //Disarmed Mine Texture reference
    private Sprite                      sprite;                 //Sprite Reference
    private Vector2                     pos;                    //Sprite Position in ScreenSpace
    private Vector3                     mousePos;               //Mouse Position set to ScreenSpace
    private int                         i;                      //Space i value
    private int                         j;                      //Space j value
    private BoardHandler                boardHandler;           //Reference to boardHandler
    private MyInputHandler              myInputHandler;         //Reference to MyInputHandler
    private PlayState                   playState;              //Reference to PlayState
    private String                      spaceValue;             //What is this space
    public boolean                      isMine;                 //Is space a mine?

    public enum SpaceState{
        Hidden,
        Exposed,
        Disarmed
    }
    public SpaceState spaceState;

    public Space(BoardHandler boardHandler, MyInputHandler myInputhandler, PlayState playstate, int i, int j)
    {
        //boardHandler = boardHandler passed from PlayState
        this.boardHandler = boardHandler;
        this.myInputHandler = myInputhandler;
        this.playState = playstate;
        this.i = i;
        this.j = j;
        disarmedTexture = new Texture(FileManager.getTexture(FileManager.FLAG));
        spaceState = SpaceState.Hidden;
    }

    //Handle mouseMove (already converted to screen space)
    public void handleInput(Vector3 mousePos)
    {
        this.mousePos = mousePos;
    }

    //Update Function
    //Checks for mouseOver (space)
    //setExposedTexture if mouseOver and buttonPress
    public void update(float dt)
    {
        if (mouseOver()) {
            if (MyInputHandler.leftMouseDown) {
                if (spaceState == SpaceState.Hidden) {
                    if (isMine) {
                        //If space isMine set it to hitmine.png
                        exposedTexture = new Texture(FileManager.getTexture(FileManager.HITMINE));
                        switchTexture(exposedTexture);
                        PlayState.gameOver = true;
                    } else if (spaceValue.equals("0")) {
                        playState.checkAdjacentSpaces(this);
                    } else {
                        //If space is not mine set it to its exposed Texture
                        setExposedTexture();
                        switchTexture(exposedTexture);
                    }
                    spaceState = SpaceState.Exposed;
                }
                MyInputHandler.leftMouseDown = false;
            }
            if (MyInputHandler.rightMouseDown) {
                switch (spaceState) {
                    case Exposed:
                        break;
                    case Hidden:
                        //If the we still have disarmFlags left
                        if (PlayState.disarmFlags > 0) {
                            switchTexture(disarmedTexture);
                            PlayState.disarmFlags = PlayState.disarmFlags - 1;
                            spaceState = SpaceState.Disarmed;
                            playState.checkForWin();
                        }
                        break;
                    case Disarmed:
                        switchTexture(hiddenTexture);
                        PlayState.disarmFlags = PlayState.disarmFlags + 1;
                        spaceState = SpaceState.Disarmed.Hidden;
                        break;
                    default:
                        break;
                }
                MyInputHandler.rightMouseDown = false;
            }
        }
    }
    //Draw Space
    public void render(SpriteBatch sb)
    {
        sb.draw(sprite, pos.x, pos.y, width(), height());
    }
    //IsMouseOver?
    private boolean mouseOver()
    {
        return sprite.getBoundingRectangle().contains(mousePos.x, mousePos.y);
    }
    //Create the sprite
    //Set sprite texture to hiddenTexture
    //Set SpriteSize (dynamically)
    public void createSprite(String name)
    {
        hiddenTexture = new Texture(FileManager.getTexture(name));
        sprite = new Sprite(hiddenTexture);
        sprite.setSize(width(), height());
    }
    //Check each space against boardHandler's board and find what the space represents then set its exposed texture
    public void setExposedTexture()
    {
        if (spaceValue.equals("M"))
        {
            exposedTexture = new Texture(FileManager.getTexture(FileManager.MINE));
        }
        else if (spaceValue.equals("0"))
        {
            exposedTexture = new Texture(FileManager.getTexture(FileManager.EXPOSED));
        }
        else if (spaceValue.equals("1"))
        {
            exposedTexture = new Texture(FileManager.getTexture(FileManager.NUMBER1));
        }
        else if (spaceValue.equals("2"))
        {
            exposedTexture = new Texture(FileManager.getTexture(FileManager.NUMBER2));
        }
        else if (spaceValue.equals("3"))
        {
            exposedTexture = new Texture(FileManager.getTexture(FileManager.NUMBER3));
        }
        else if (spaceValue.equals("4"))
        {
            exposedTexture = new Texture(FileManager.getTexture(FileManager.NUMBER4));
        }
        else if (spaceValue.equals("5"))
        {
            exposedTexture = new Texture(FileManager.getTexture(FileManager.NUMBER5));
        }
        else if (spaceValue.equals("6"))
        {
            exposedTexture = new Texture(FileManager.getTexture(FileManager.NUMBER6));
        }
        else if (spaceValue.equals("7"))
        {
            exposedTexture = new Texture(FileManager.getTexture(FileManager.NUMBER7));
        }
        else if (spaceValue.equals("8"))
        {
            exposedTexture = new Texture(FileManager.getTexture(FileManager.NUMBER8));
        }
    }
    //Set Texture
    public void setTexture(String tex)
    {
        if (exposedTexture == null)
            exposedTexture = new Texture(tex);
        else
        {
            exposedTexture.dispose();
            exposedTexture = new Texture(tex);
        }
        switchTexture(exposedTexture);
    }
    //switch sprite to texture
    //set sprite size (dynamically)
    public void switchTexture(Texture texture)
    {
        sprite = new Sprite(texture);
        sprite.setSize(width(), height());
        sprite.setPosition(pos.x, pos.y);
    }
    //set position that sprite will call
    public void setPosition()
    {
        pos = new Vector2((width() * i), (height() * j));
        sprite.setPosition(pos.x, pos.y);
    }

    public Texture getExposedTexture()
    {
        return exposedTexture;
    }

    //return width (dynamically)
    private float width()
    {
        return MineSweeperDemo.WIDTH / boardHandler.getBoardSize();
    }
    //return height (dynamically)
    private float height()
    {
        return (MineSweeperDemo.HEIGHT - MineSweeperDemo.YBUFFER) / boardHandler.getBoardSize();
    }

    public int getI()
    {
        return i;
    }

    public int getJ()
    {
        return j;
    }

    public void setSpaceValue(String spaceValue)
    {
        this.spaceValue = spaceValue;

        if (spaceValue.equals("M"))
            isMine = true;
    }

    public String getSpaceValue()
    {
        return spaceValue;
    }

    //Dispose of texture
    public void dispose()
    {
        if (hiddenTexture != null)
            hiddenTexture.dispose();
        if (exposedTexture != null)
            exposedTexture.dispose();
    }
}