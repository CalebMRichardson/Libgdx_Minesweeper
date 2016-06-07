package board;

import Input.MyInputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.grimsatisfactions.minesweeper.FileManager;
import com.grimsatisfactions.minesweeper.MineSweeperDemo;
import states.GameStateManager;
import states.PlayState;

/**
 * Created by Caleb.Richardson on 6/3/2016.
 */
public class ResetSpace {

    private Vector3             mousePos;           //Mouse Position in games space
    private Vector2             pos;                //ResetSpace position
    private BoardHandler        boardHandler;       //Reference to boardHandler
    private GameStateManager    gsm;                //Reference to GameStateManager
    private Texture             defaultTex;         //Default texture "blank.png"
    private Sprite              sprite;             //ResetSpace Sprite Reference

    //Set BoardHandler and GSM
    //Create Sprite for ResetSpace
    public ResetSpace(BoardHandler boardHandler, GameStateManager gsm)
    {
        this.boardHandler = boardHandler;
        this.gsm = gsm;

        defaultTex = new Texture(FileManager.getTexture(FileManager.BLANK));

        pos = new Vector2((MineSweeperDemo.WIDTH / 2) - (width() / 2), (MineSweeperDemo.HEIGHT - ((MineSweeperDemo.YBUFFER / 2) + (height() / 2))));

        sprite = new Sprite(defaultTex);
        sprite.setSize(width(), height());
        sprite.setPosition(pos.x, pos.y);
    }

    //HandleInput (update mouse pos)
    public void handleInput(Vector3 mousePos)
    {
        this.mousePos = mousePos;
    }
    //Game Update Step check for mouseover and leftMouseDown
    public void update(float dt)
    {
        if (mouseOver())
        {
            if (MyInputHandler.leftMouseDown)
            {
                MyInputHandler.leftMouseDown = false;
                reset();
            }
        }
    }
    //Draw to screen
    public void render(SpriteBatch sb)
    {
        sb.draw(sprite, pos.x, pos.y, width(), height());
    }
    //If mouse is over this
    private boolean mouseOver()
    {
        return sprite.getBoundingRectangle().contains(mousePos.x, mousePos.y);
    }

    private float width()
    {
        return (MineSweeperDemo.WIDTH / boardHandler.getBoardSize());
    }

    private float height()
    {
        return MineSweeperDemo.HEIGHT / boardHandler.getBoardSize();
    }
    //On Reset Dispose of textures and set new PlayState
    private void reset()
    {
        dispose();
        gsm.dispose();
        gsm.set(new PlayState(gsm));
    }

    public void dispose()
    {
        if (defaultTex != null)
            defaultTex.dispose();
    }
}
