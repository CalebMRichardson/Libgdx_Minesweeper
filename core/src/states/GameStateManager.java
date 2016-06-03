package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Caleb on 5/15/2016.
 */
public class GameStateManager {

    private Stack<State>            states;//Reference Stack of states

    public GameStateManager()
    {
        states = new Stack<State>();
    }

    //Push new State
    public void push (State state)
    {
        states.push(state);
    }

    //Pop current game state
    public void pop()
    {
        states.pop();
    }

    //set new game state
    public void set(State state)
    {
        states.pop();
        states.push(state);
    }

    //hanldeInput current game state
    public void handleInput()
    {
        states.peek().handleInput();
    }
    //update current game state
    public void update(float dt)
    {
        states.peek().update((dt));
    }
    //render current game state
    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }
    //dispose current game state
    public void dispose()
    {
        states.peek().dispose();
    }
}
