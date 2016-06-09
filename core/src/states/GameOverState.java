package states;

import board.ResetSpace;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grimsatisfactions.minesweeper.MineSweeperDemo;

/**
 * Created by Caleb on 5/17/2016.
 */
public class GameOverState extends State {

    private PlayState           playState;      //PlayState Reference
    private ResetSpace          resetSpace;     //ResetSpace Reference (Only update called in this GameState)
    private BitmapFont          font;           //Reference to BitMapFont;
    private String              gameState;      //String - playstate.gameWin/playstate.gameOver

    public GameOverState(GameStateManager gsm, PlayState playState, ResetSpace resetSpace)
    {
        super(gsm);
        this.playState = playState;
        this.resetSpace = resetSpace;

        font = new BitmapFont();

        if (PlayState.gameWin)
        {
            font.setColor(Color.GREEN);
            gameState = "Win!";
        }
        else
        {
            font.setColor(Color.RED);
            gameState = "Lost!";
        }
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
        sb.begin();
        font.draw(sb, gameState, MineSweeperDemo.WIDTH - font.getSpaceWidth() - 50, (MineSweeperDemo.HEIGHT - (MineSweeperDemo.YBUFFER / 2)));
        sb.end();
    }

    @Override
    public void dispose() {
        playState.dispose();
        font.dispose();
    }
}
