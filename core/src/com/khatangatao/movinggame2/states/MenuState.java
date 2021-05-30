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
    private TextureRegion background2;
    private Sprite background3;
    private Texture background4;
    private Texture playBtn;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        //gamePort = new FitViewport(Moving.VIEWPORTWIDTH / Moving.PPM, Moving.VIEWPORTHEIGHT / Moving.PPM, camera);
        gamePort = new FitViewport(Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT, camera);

        //background2 = new TextureRegion(background, 7500, 5000, Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT);
        //background3 = new Sprite(background);
        //background3.setPosition(0,0);
        //background3.setSize(Moving.VIEWPORTHEIGHT, Moving.VIEWPORTWIDTH);

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
        // Нарисуй мне всю картинку background в прямоугольнике с заданным размером
        //spriteBatch.draw(background, 0,0, Moving.WIDTH, Moving.HEIGHT);

        // Возьми background и нарисуй ее в точке x,y. После обработки матрицей проецирования
        // на экран будет выведено то, что поместится в окно камеры.
        // Если камеру передвинуть, то станет видна другая часть background
        //spriteBatch.draw(background, 0,0);
        //spriteBatch.draw(background2, 0,0);

        // Нарисуй мне часть картинки background, которая поместится в камеру
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        //spriteBatch.draw(background2, camera.position.x - (camera.viewportWidth / 2), 0);
        //spriteBatch.draw(background4, camera.position.x - (camera.viewportWidth / 2), 0);
        //spriteBatch.draw(background, 0, 0);
        //background3.draw(spriteBatch);
        spriteBatch.draw(playBtn, Moving.VIEWPORTWIDTH / 2 - playBtn.getWidth()/2, Moving.VIEWPORTHEIGHT / 2);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        //background2.getTexture().dispose();
        //background3.getTexture().dispose();
        //background4.dispose();

        playBtn.dispose();
        System.out.println("MenuState disposed");
    }
}
