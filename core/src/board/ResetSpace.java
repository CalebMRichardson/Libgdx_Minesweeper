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

    private Vector3             mousePos;
    private Vector2             pos;
    private BoardHandler        boardHandler;
    private GameStateManager    gsm;
    private Texture             defaultTex;
    private Texture             exposedTex;
    private Sprite              sprite;


    //TODO Add comments

    public ResetSpace(BoardHandler boardHandler, GameStateManager gsm)
    {
        this.boardHandler = boardHandler;
        this.gsm = gsm;

        defaultTex = new Texture(FileManager.getTexture(FileManager.BLANK));
        exposedTex = new Texture(FileManager.getTexture(FileManager.EXPOSED));

        pos = new Vector2((MineSweeperDemo.WIDTH / 2) - (width() / 2), (MineSweeperDemo.HEIGHT - ((MineSweeperDemo.YBUFFER / 2) + (height() / 2))));

        sprite = new Sprite(defaultTex);
        sprite.setSize(width(), height());
        sprite.setPosition(pos.x, pos.y);
    }

    public void handleInput(Vector3 mousePos)
    {
        this.mousePos = mousePos;
    }

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

    public void render(SpriteBatch sb)
    {
        sb.draw(sprite, pos.x, pos.y, width(), height());
    }

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

    private void reset()
    {
        dispose();
        gsm.dispose();
        gsm.set(new PlayState(gsm));
    }

    public void dispose()
    {
        defaultTex.dispose();
        exposedTex.dispose();
    }
}
