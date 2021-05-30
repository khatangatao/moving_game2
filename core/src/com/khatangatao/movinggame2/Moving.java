package com.khatangatao.movinggame2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khatangatao.movinggame2.states.GameStateManager;
import com.khatangatao.movinggame2.states.MenuState;

public class Moving extends ApplicationAdapter {
	public static final int WORLDWIDTH = 1504;
	public static final int WORLDHEIGHT = 960;
	public static final int VIEWPORTWIDTH = 400;
	public static final int VIEWPORTHEIGHT = 800;
	//PPM - pixels per meter. For scaling!
	public static final float PPM = 100;

	public static final String TITLE = "Moving game 2";
	private GameStateManager gameStateManager;
	private SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gameStateManager.push(new MenuState(gameStateManager));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//GameStateManager involved to work
		gameStateManager.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
