package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Caleb on 5/15/2016.
 */
public abstract class State {

    protected OrthographicCamera        cam;            //Game OrthographicCamera passed around to each state
    protected GameStateManager          gsm;            //GameStateManager


    protected State(GameStateManager gsm)
    {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        cam.update();
    }

    //Abstract classes for calling in each state
    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}
