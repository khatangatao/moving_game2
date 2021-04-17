package com.khatangatao.movinggame2.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khatangatao.movinggame2.Moving;
import com.khatangatao.movinggame2.sprites.Player;


public class PlayState extends State{
    private Player player;
    private Texture background;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        player = new Player(50, 100);
        camera.setToOrtho(false, Moving.WIDTH/2, Moving.HEIGHT/2);
        background = new Texture("bg.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.getPosition().y += 200 * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.getPosition().y -= 200 * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.getPosition().x -= 200 * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.getPosition().x += 200 * Gdx.graphics.getDeltaTime();
        }

        // make sure the player stays within the screen bounds
        if (player.getPosition().x < 0) {
            player.getPosition().x = 0;
        }

        if (player.getPosition().x > camera.viewportWidth - 30) {
            player.getPosition().x = camera.viewportWidth - 30;
        }

        if (player.getPosition().y < 0) {
            player.getPosition().y = 0;
        }

        if (player.getPosition().y > camera.viewportHeight - 30) {
            player.getPosition().y = camera.viewportHeight - 30;
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        //spriteBatch.draw(background, camera.position.x - (camera.viewportWidth/2), camera.position.y - (camera.viewportHeight/2));
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth/2),0);
        //spriteBatch.draw(background, 0,0);
        spriteBatch.draw(player.getTexture(), player.getPosition().x,player.getPosition().y);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
