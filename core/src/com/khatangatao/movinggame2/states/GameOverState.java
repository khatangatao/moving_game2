package com.khatangatao.movinggame2.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.khatangatao.movinggame2.Moving;

public class GameOverState extends State{
    private Texture background;
    private Texture playBtn;
    private Music levelMusic;

    public GameOverState(GameStateManager gameStateManager){
        super(gameStateManager);

        gamePort = new FitViewport(Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT, camera);
        gamePort = new FitViewport(Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT, camera);
        background = new Texture("gameover.png");
        playBtn = new Texture("tryagain.png");
        camera.setToOrtho(false, Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT);
        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        levelMusic = Gdx.audio.newMusic(Gdx.files.internal("sfx/gameover.wav"));
        levelMusic.setVolume(1);
        levelMusic.play();
    }

    @Override
    protected void handleInput(float dt) {
        if(Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        // Нарисуй мне часть картинки background, которая поместится в камеру
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(playBtn, Moving.VIEWPORTWIDTH / 2 - playBtn.getWidth()/2, Moving.VIEWPORTHEIGHT / 8);

        Skin skin = new Skin(Gdx.files.internal("dialog/uiskin.json"));
        // fire up the dialog and voila :)
        Dialog d = new Dialog("Title", skin);

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }


    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
