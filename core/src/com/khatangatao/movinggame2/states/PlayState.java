package com.khatangatao.movinggame2.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khatangatao.movinggame2.Moving;


public class PlayState extends State{
    private Texture player;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        player = new Texture("player.png");
        camera.setToOrtho(false, Moving.WIDTH/2, Moving.HEIGHT/2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(player, 50,50);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
