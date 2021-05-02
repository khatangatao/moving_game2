package com.khatangatao.movinggame2.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.khatangatao.movinggame2.Moving;
import com.khatangatao.movinggame2.sprites.Player;
import com.khatangatao.movinggame2.sprites.Table;


public class PlayState extends State {
    private Player player;
    //private Table table1, table2;
    private Texture background;
    private Array<Table> tables;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        player = new Player(50, 300);
        //table1 = new Table(100, 200);
        //table2 = new Table(30, 50);
        camera.setToOrtho(false, Moving.WIDTH / 2, Moving.HEIGHT / 2);
        background = new Texture("bg.png");
        tables = new Array<>();
        // todo считывать координаты столов из хранилища
        for (int i = 1; i < 3; i++) {
            tables.add(new Table(i * 50, i * 100));
            //tables.add(new Table(50, 100));
        }

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.getPosition().y += 200 * Gdx.graphics.getDeltaTime();
            //if (player.collides(table1.getBody())) {
            //    player.getPosition().y = table1.getBody().getY() - player.getBody().getHeight() - 2;
            //
            //} else if (player.collides(table2.getBody())) {
            //    player.getPosition().y = table2.getBody().getY() - player.getBody().getHeight() - 2;
            //}
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.getPosition().y -= 200 * Gdx.graphics.getDeltaTime();
            //if (player.collides(table1.getBody())) {
            //    player.getPosition().y = table1.getBody().getY() + table1.getBody().getHeight() + 2;
            //
            //} else if (player.collides(table2.getBody())) {
            //    player.getPosition().y = table2.getBody().getY() + table2.getBody().getHeight() + 2;
            //}
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.getPosition().x -= 200 * Gdx.graphics.getDeltaTime();
            //if (player.collides(table1.getBody())) {
            //    player.getPosition().x = table1.getBody().getX() + table1.getBody().getWidth() + 2;
            //
            //} else if (player.collides(table2.getBody())) {
            //    player.getPosition().x = table2.getBody().getX() + table2.getBody().getWidth() + 2;
            //}
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.getPosition().x += 200 * Gdx.graphics.getDeltaTime();
            //if (player.collides(table1.getBody())) {
            //    player.getPosition().x = table1.getBody().getX() - player.getBody().getWidth() - 2;
            //
            //} else if (player.collides(table2.getBody())) {
            //    player.getPosition().x = table2.getBody().getX() - player.getBody().getWidth() - 2;
            //}
        }

        // make sure the player stays within the screen bounds
        if (player.getPosition().x < 0) {
            player.getPosition().x = 0;
        }

        if (player.getPosition().x > camera.viewportWidth - player.getBody().getWidth()) {
            player.getPosition().x = camera.viewportWidth - player.getBody().getWidth();
        }

        if (player.getPosition().y < 0) {
            player.getPosition().y = 0;
        }

        if (player.getPosition().y > camera.viewportHeight - player.getBody().getHeight()) {
            player.getPosition().y = camera.viewportHeight - player.getBody().getHeight();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);
        for(Table table: tables) {
            if (table.collides(player.getBody())) {
            //    Как определить, какими сторонами соприкоснулись игрок и стол?

            }
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        //spriteBatch.draw(background, camera.position.x - (camera.viewportWidth/2), camera.position.y - (camera.viewportHeight/2));
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        //spriteBatch.draw(background, 0,0);
        spriteBatch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        for(Table table: tables) {
            spriteBatch.draw(table.getTexture(), table.getPosition().x, table.getPosition().y);
        }
        //spriteBatch.draw(table1.getTexture(), table1.getPosition().x, table1.getPosition().y);
        //spriteBatch.draw(table2.getTexture(), table2.getPosition().x, table2.getPosition().y);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
