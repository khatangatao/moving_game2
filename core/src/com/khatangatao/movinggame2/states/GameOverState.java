package com.khatangatao.movinggame2.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.khatangatao.movinggame2.Moving;

public class GameOverState extends State {
    private Texture background;
    private Texture playBtn;
    private Music levelMusic;
    private Dialog dialog;
    NinePatch patch;
    TextButton button;

    public GameOverState(GameStateManager gameStateManager) {
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

        // Create text button
        patch = new NinePatch(new Texture(Gdx.files.internal("nine/knob.png")), 12, 12, 12, 12);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(
                new NinePatchDrawable(patch),
                new NinePatchDrawable(patch),
                new NinePatchDrawable(patch),
                new BitmapFont()
        );
        style.fontColor = new Color(0.3f, 0.2f, 0.8f, 1f);
        button = new TextButton("hello world", style);
    }

    @Override
    protected void handleInput(float dt) {
        if (Gdx.input.justTouched()) {
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
        spriteBatch.draw(playBtn, Moving.VIEWPORTWIDTH / 2 - playBtn.getWidth() / 2, Moving.VIEWPORTHEIGHT / 8);


//        dialog.draw(spriteBatch, 1);
        button.draw(spriteBatch, 1);

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
