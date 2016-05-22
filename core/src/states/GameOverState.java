package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Caleb on 5/17/2016.
 */
public class GameOverState extends State {

    private PlayState playState;

    public GameOverState(GameStateManager gsm, PlayState playState)
    {
        super(gsm);
        this.playState = playState;
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        playState.render(sb);
    }

    @Override
    public void dispose() {
        //TODO Dispose PlayState
    }
}
