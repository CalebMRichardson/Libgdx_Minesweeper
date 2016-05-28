package com.grimsatisfactions.minesweeper.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.grimsatisfactions.minesweeper.MineSweeperDemo;

//Game Entry point
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MineSweeperDemo.WIDTH;
		config.height = MineSweeperDemo.HEIGHT;
		config.title = MineSweeperDemo.TITLE;
		config.resizable = false;
		new LwjglApplication(new MineSweeperDemo(), config);
	}
}
