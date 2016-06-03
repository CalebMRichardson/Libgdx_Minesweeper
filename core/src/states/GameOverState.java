package states;

import board.ResetSpace;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Caleb on 5/17/2016.
 */
public class GameOverState extends State {

    private PlayState           playState;
    private ResetSpace          resetSpace;

    // Used for both GameOver and GameWin
    //Check PlayState.gameOver and PlayState.gameWin
    //Create new Frame with "youWin/GameOver" would you like to play again?
    //On Yes Push Gsm new PlayState if No Close Game

    //TODO Add Comments

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
