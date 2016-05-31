package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Caleb on 5/17/2016.
 */
public class GameOverState extends State {

    private PlayState playState;

    // Used for both GameOver and GameWin
    //Check PlayState.gameOver and PlayState.gameWin
    //Create new Frame with "youWin/GameOver" would you like to play again?
    //On Yes Push Gsm new Playstate if No Close Game

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
       playState.dispose();
    }
}
