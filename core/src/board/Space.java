package board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
    private MyInputHandler              myInputHandler;
    public boolean                      isMine;                 //Is space a mine?
    public boolean                      exposed;                //Is space exposed?

    private enum SpaceState{
        Hidden,
        Exposed,
        Disarmed
    }
    private SpaceState spaceState;

    public Space(BoardHandler boardHandler, MyInputHandler myInputhandler, int i, int j)
    {
        //boardHandler = boardHandler passed from PlayState
        this.boardHandler = boardHandler;
        this.myInputHandler = myInputhandler;
        this.i = i;
        this.j = j;
        exposed = false;
        disarmedTexture = new Texture("flag.png");
        //If board Cell == 'M" space isMine
        if (boardHandler.getBoard()[i][j].equals("M"))
        {
            isMine = true;
        }
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
                if (isMine) {
                    //If space isMine set it to hitmine.png
                    exposedTexture = new Texture("hitmine.png");
                    switchTexture(exposedTexture);
                    PlayState.gameOver = true;
                } else {
                    //If space is not mine set it to its exposed Texture
                    setExposedTexture();
                    switchTexture(exposedTexture);
                }
                MyInputHandler.leftMouseDown = false;
                spaceState = SpaceState.Exposed;
            }
            if (MyInputHandler.rightMouseDown) {
                switch (spaceState) {
                    case Exposed:
                        break;
                    case Hidden:
                        switchTexture(disarmedTexture);
                        PlayState.disarmFlags = PlayState.disarmFlags - 1;
                        spaceState = SpaceState.Disarmed;
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
        hiddenTexture = new Texture(name);
        sprite = new Sprite(hiddenTexture);
        sprite.setSize(width(), height());
    }
    //Check each space against boardHandler's board and find what the space represents then set its exposed texture
    public void setExposedTexture()
    {
        if (boardHandler.getBoard()[i][j].equals("M"))
        {
            exposedTexture = new Texture("mine.png");
        }
        else if (boardHandler.getBoard()[i][j].equals("0"))
        {
            exposedTexture = new Texture("exposed.png");
        }
        else if (boardHandler.getBoard()[i][j].equals("1"))
        {
            exposedTexture = new Texture("number1.png");
        }
        else if (boardHandler.getBoard()[i][j].equals("2"))
        {
            exposedTexture = new Texture("number2.png");
        }
        else if (boardHandler.getBoard()[i][j].equals("3"))
        {
            exposedTexture = new Texture("number3.png");
        }
        else if (boardHandler.getBoard()[i][j].equals("4"))
        {
            exposedTexture = new Texture("number4.png");
        }
        else if (boardHandler.getBoard()[i][j].equals("5"))
        {
            exposedTexture = new Texture("number5.png");
        }
        else if (boardHandler.getBoard()[i][j].equals("6"))
        {
            exposedTexture = new Texture("number6.png");
        }
        else if (boardHandler.getBoard()[i][j].equals("7"))
        {
            exposedTexture = new Texture("number7.png");
        }
        else if (boardHandler.getBoard()[i][j].equals("8"))
        {
            exposedTexture = new Texture("number8.png");
        }
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
        return Gdx.graphics.getWidth() / boardHandler.getBoardSize();
    }
    //return height (dynamically)
    private float height()
    {
        return Gdx.graphics.getHeight() / boardHandler.getBoardSize();
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