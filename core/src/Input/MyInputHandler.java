package Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Caleb on 5/18/2016.
 */
public class MyInputHandler implements InputProcessor{

    public static boolean           leftMouseDown = false;
    public static boolean           rightMouseDown = false;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            leftMouseDown = true;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
        {
            rightMouseDown = true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            leftMouseDown = false;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
        {
            rightMouseDown = false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
