package states;

import board.ResetSpace;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Caleb on 5/17/2016.
 */
public class GameOverState extends State {

    private PlayState           playState;      //PlayState Reference
    private ResetSpace          resetSpace;     //ResetSpace Reference (Only update called in this GameState)

    public GameOverState(GameStateManager gsm, PlayState playState, ResetSpace resetSpace)
    {
        super(gsm);
        this.playState = playState;
        this.resetSpace = resetSpace;
    }

    @Override
    public void handleInput() {
        playState.handleInput();
    }

    @Override
    public void update(float dt) {
        resetSpace.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        playState.render(sb);
    }

    @Override
    public void dispose() {
        playState.dispose();
    }
}
