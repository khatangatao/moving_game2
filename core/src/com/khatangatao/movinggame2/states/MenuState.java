package com.khatangatao.movinggame2.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.khatangatao.movinggame2.Moving;


public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        //gamePort = new FitViewport(Moving.VIEWPORTWIDTH / Moving.PPM, Moving.VIEWPORTHEIGHT / Moving.PPM, camera);
        gamePort = new FitViewport(Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT, camera);
        background = new Texture("resources.png");
        playBtn = new Texture("startbutton.png");
        camera.setToOrtho(false, Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT);
        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }


    @Override
    protected void handleInput(float dt) {
        if(Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void update(float dt) {
        handleInput(dt);

        //camera.position.x = Moving.WORLDWIDTH/2;
        //camera.position.y = Moving.WORLDHEIGHT/2;
        //camera.position.x = 7500;
        //camera.position.y = 4000;
        //camera.update();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        // Нарисуй мне часть картинки background, которая поместится в камеру
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(playBtn, Moving.VIEWPORTWIDTH / 2 - playBtn.getWidth()/2, Moving.VIEWPORTHEIGHT / 2);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
