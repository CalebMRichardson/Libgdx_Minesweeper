package com.grimsatisfactions.minesweeper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import states.GameStateManager;
import states.PlayState;

public class MineSweeperDemo extends ApplicationAdapter {

	public static final int 					WIDTH = 400;                //Game Frame WIDTH
	public static final int 					HEIGHT = 400;               //Game Frame HEIGHT
	public static final String 					TITLE = "Mine_Sweeper";     //Game Frame TITLE

	private GameStateManager 					gsm;                        //GameStateManager reference
	private SpriteBatch 						batch;                      //Game SpriteBatch Refrence

    //Create SpriteBatch
    //Create GameStateManager
    //Set Game Clear color
    //Push new GameState (playstate)
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0,0,0,1);
		gsm.push(new PlayState(gsm));

	}
    //Clear Screen
    //HandleInput
    //Update
    //Render
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.handleInput();
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

    //Dispose GameState
	@Override
	public void dispose()
	{
		super.dispose();
		gsm.dispose();
		batch.dispose();
	}
}
