package com.khatangatao.movinggame2.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.khatangatao.movinggame2.Moving;
import com.khatangatao.movinggame2.sprites.Border;
import com.khatangatao.movinggame2.sprites.Player;
import com.khatangatao.movinggame2.sprites.Table;

import java.util.List;


public class PlayState extends State {
    private Player player;
    private Texture background;
    private Array<Table> tables;
    public BitmapFont font;
    private Array<Border> borders;


    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        player = new Player(Moving.WORLDWIDTH / 2, Moving.WORLDHEIGHT / 2);
        gamePort = new FitViewport(Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT, camera);
        font = new BitmapFont();
        camera.setToOrtho(false, Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT);
        background = new Texture("mg_level1.png");
        tables = new Array<>();
        borders = new Array<>();
        //// todo считывать координаты столов из хранилища
        //for (int i = 1; i < 3; i++) {
        //    tables.add(new Table(i * 50, i * 100));
        //    //tables.add(new Table(50, 100));
        //}

        // Tables
        //tables.add(new Table());


        //Level borders
        borders.add(new Border(0, 330, 60, 600));
        borders.add(new Border(0, Moving.WORLDHEIGHT - 60, Moving.WORLDWIDTH, 60));
        borders.add(new Border(830, Moving.WORLDHEIGHT - 400, 80, Moving.WORLDHEIGHT));
        borders.add(new Border(830, Moving.WORLDHEIGHT - 350, 600, 50));
        borders.add(new Border(1420, 330, 80, 300));
        borders.add(new Border(0, 330, 900, 60));
        borders.add(new Border(830, 0, 80, 410));



    }

    @Override
    protected void handleInput(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            //player.getPosition().y += 200 * Gdx.graphics.getDeltaTime();
            player.getPosition().y += 200 * dt;
            player.update(dt);
            for (Table table : tables) {
                if (player.collides(table.getBody())) {
                    player.getPosition().y = table.getBody().getY() - player.getBody().getHeight();
                }
            }

            for (Border border: borders) {
                if (player.collides(border.getBody())) {
                    player.getPosition().y = border.getBody().getY() - player.getBody().getHeight();
                }
            }

        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            //player.getPosition().y -= 200 * Gdx.graphics.getDeltaTime();
            player.getPosition().y -= 200 * dt;
            player.update(dt);
            for (Table table : tables) {
                if (player.collides(table.getBody())) {
                    player.getPosition().y = table.getBody().getY() + table.getBody().getHeight();
                }
            }

            for (Border border: borders) {
                if (player.collides(border.getBody())) {
                    player.getPosition().y = border.getBody().getY() + border.getBody().getHeight();
                }
            }

        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //player.getPosition().x -= 200 * Gdx.graphics.getDeltaTime();
            player.getPosition().x -= 200 * dt;
            player.update(dt);
            for (Table table : tables) {
                if (player.collides(table.getBody())) {
                    player.getPosition().x = table.getBody().getX() + table.getBody().getWidth();
                }
            }

            for (Border border: borders) {
                if (player.collides(border.getBody())) {
                    player.getPosition().x = border.getBody().getX() + border.getBody().getWidth();
                }
            }

        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //player.getPosition().x += 200 * Gdx.graphics.getDeltaTime();
            player.getPosition().x += 200 * dt;
            player.update(dt);
            for (Table table : tables) {
                if (player.collides(table.getBody())) {
                    player.getPosition().x = table.getBody().getX() - player.getBody().getWidth();
                }
            }

            for (Border border: borders) {
                if (player.collides(border.getBody())) {
                    player.getPosition().x = border.getBody().getX() - player.getBody().getWidth();
                }
            }

        }

        // make sure the player stays within the screen bounds
        if (player.getPosition().x < 0) {
            player.getPosition().x = 0;
        }

        //if (player.getPosition().x > camera.viewportWidth - player.getBody().getWidth()) {
        //    player.getPosition().x = camera.viewportWidth - player.getBody().getWidth();
        //}
        if (player.getPosition().x > Moving.WORLDWIDTH - player.getBody().getWidth()) {
            player.getPosition().x = Moving.WORLDWIDTH - player.getBody().getWidth();
        }

        if (player.getPosition().y < 0) {
            player.getPosition().y = 0;
        }

        //if (player.getPosition().y > camera.viewportHeight - player.getBody().getHeight()) {
        //    player.getPosition().y = camera.viewportHeight - player.getBody().getHeight();
        //}
        if (player.getPosition().y > Moving.WORLDHEIGHT - player.getBody().getHeight()) {
            player.getPosition().y = Moving.WORLDHEIGHT - player.getBody().getHeight();
        }

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        player.update(dt);
        camera.position.x = player.getPosition().x;
        camera.position.y = player.getPosition().y;
        camera.update();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        font.draw(
                spriteBatch,
                "Player position: " + player.getPosition().x + " x " + player.getPosition().y,
                camera.position.x - camera.viewportWidth / 2 + 20,
                camera.position.y + camera.viewportHeight / 2 - 20
        );

        for (Table table : tables) {
            spriteBatch.draw(table.getTexture(), table.getPosition().x, table.getPosition().y);
        }
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
        for (Table table : tables) {
            table.dispose();
        }

        for (Border border : borders) {
            border.dispose();
        }

        font.dispose();
        System.out.println("PlayState disposed");
    }
}
