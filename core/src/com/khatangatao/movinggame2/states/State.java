package com.khatangatao.movinggame2.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;
    protected Viewport gamePort;

    protected State(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        camera = new OrthographicCamera();
        // Если создать камеру по команде ниже, то камера будет создана с разрешением экрана
        //camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mouse = new Vector3();


    }

    protected abstract void handleInput(float dt);
    public abstract void update(float dt);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void resize(int width, int height);
    public abstract void dispose();
}
