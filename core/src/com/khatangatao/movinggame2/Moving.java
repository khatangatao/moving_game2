package com.khatangatao.movinggame2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khatangatao.movinggame2.states.GameStateManager;
import com.khatangatao.movinggame2.states.MenuState;

public class Moving extends ApplicationAdapter {
	//Viewport size
	public static final int WIDTH = 400;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Moving game 2";
	private GameStateManager gameStateManager;
	private SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gameStateManager.push(new MenuState(gameStateManager));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//GameStateManager involved to work
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);



	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
