package com.khatangatao.movinggame2.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.khatangatao.movinggame2.Moving;
import com.khatangatao.movinggame2.scenes.Hud;
import com.khatangatao.movinggame2.sprites.Border;
import com.khatangatao.movinggame2.sprites.Coin;
import com.khatangatao.movinggame2.sprites.Monster;
import com.khatangatao.movinggame2.sprites.Player;
import com.khatangatao.movinggame2.sprites.Table;


public class PlayState extends State {
    private Player player;
    private Texture background;
    private Array<Table> tables;
    public BitmapFont font;
    private Array<Border> borders;
    private Array<Coin> coins;
    private Array<Monster> monsters;
    private String table1pic = "table1.png";
    private String table2pic = "table2.png";
    private String table1picVertical = "table1vertical.png";
    private String table2picVertical = "table2vertical.png";
    private String coinPic = "coin.png";
    private String monsterBasic = "monsteranimation.png";
    private String turtlePic  = "turtleanimation.png";

    private Hud hud;

    private Sound coinSound;
    private Music levelMusic;


    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        player = new Player(Moving.WORLDWIDTH / 2, Moving.WORLDHEIGHT / 2);
        gamePort = new FitViewport(Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT, camera);
        //hud = new Hud(new SpriteBatch());
        font = new BitmapFont();
        camera.setToOrtho(false, Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT);
        background = new Texture("mg_level1.png");
        tables = new Array<>();
        hud = new Hud(new SpriteBatch());

        coinSound = Gdx.audio.newSound(Gdx.files.internal("sfx/Coin.wav"));
        levelMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

        // start the playback of the background music immediately
        levelMusic.setLooping(true);
        levelMusic.setVolume(0.5f);
        levelMusic.play();

        // big tables
        tables.add(new Table(913, Moving.WORLDHEIGHT - 887, table1pic));
        tables.add(new Table(1067, Moving.WORLDHEIGHT - 887, table1pic));
        tables.add(new Table(1261, Moving.WORLDHEIGHT - 476, table1pic));
        tables.add(new Table(169, Moving.WORLDHEIGHT - 200, table1picVertical));
        tables.add(new Table(289, Moving.WORLDHEIGHT - 564, table1picVertical));
        tables.add(new Table(680, Moving.WORLDHEIGHT - 210, table1picVertical));


        //small tables
        tables.add(new Table(933, Moving.WORLDHEIGHT - 476, table2pic));
        tables.add(new Table(1063, Moving.WORLDHEIGHT - 476, table2pic));
        tables.add(new Table(1281, Moving.WORLDHEIGHT - 662, table2pic));
        tables.add(new Table(157, Moving.WORLDHEIGHT - 564, table2picVertical));
        tables.add(new Table(157, Moving.WORLDHEIGHT - 434, table2picVertical));
        tables.add(new Table(289, Moving.WORLDHEIGHT - 404, table2picVertical));
        tables.add(new Table(347, Moving.WORLDHEIGHT - 489, table2pic));
        tables.add(new Table(447, Moving.WORLDHEIGHT - 429, table2picVertical));
        tables.add(new Table(333, Moving.WORLDHEIGHT - 190, table2picVertical));
        tables.add(new Table(479, Moving.WORLDHEIGHT - 173, table2picVertical));
        tables.add(new Table(619, Moving.WORLDHEIGHT - 179, table2picVertical));
        tables.add(new Table(620, Moving.WORLDHEIGHT - 564, table2picVertical));
        tables.add(new Table(620, Moving.WORLDHEIGHT - 434, table2picVertical));


        //Level borders
        borders = new Array<>();
        borders.add(new Border(0, 330, 60, 600));
        borders.add(new Border(0, Moving.WORLDHEIGHT - 60, Moving.WORLDWIDTH, 60));
        borders.add(new Border(830, Moving.WORLDHEIGHT - 400, 80, Moving.WORLDHEIGHT));
        borders.add(new Border(830, Moving.WORLDHEIGHT - 350, 600, 50));
        borders.add(new Border(1420, Moving.WORLDHEIGHT - 637, 80, 300));
        borders.add(new Border(0, 330, 900, 60));
        borders.add(new Border(830, 0, 80, 410));
        borders.add(new Border(900, 0, 600, 5));
        borders.add(new Border(1490, 0, 10, Moving.WORLDHEIGHT - 637));


        // Coins
        coins = new Array<>();
        coins.add(new Coin(120, Moving.WORLDHEIGHT - 100, coinPic));
        coins.add(new Coin(269, Moving.WORLDHEIGHT - 100, coinPic));
        coins.add(new Coin(237, Moving.WORLDHEIGHT - 486, coinPic));
        coins.add(new Coin(415, Moving.WORLDHEIGHT - 405, coinPic));
        coins.add(new Coin(768, Moving.WORLDHEIGHT - 115, coinPic));
        coins.add(new Coin(378, Moving.WORLDHEIGHT - 531, coinPic));
        coins.add(new Coin(932, Moving.WORLDHEIGHT - 400, coinPic));
        coins.add(new Coin(933, Moving.WORLDHEIGHT - 779, coinPic));
        coins.add(new Coin(1344, Moving.WORLDHEIGHT - 395, coinPic));
        coins.add(new Coin(1374, Moving.WORLDHEIGHT - 596, coinPic));

//        Monsters
        monsters = new Array<>();
        monsters.add(new Monster(Moving.WORLDWIDTH / 2 + 100, Moving.WORLDHEIGHT / 2, 3, monsterBasic));
        monsters.add(new Monster(450, 700, 4, turtlePic));
        monsters.add(new Monster(500, 475, 3 , monsterBasic));


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

            for (Border border : borders) {
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

            for (Border border : borders) {
                if (player.collides(border.getBody())) {
                    player.getPosition().y = border.getBody().getY() + border.getBody().getHeight();
                }
            }


        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //player.getPosition().x -= 200 * Gdx.graphics.getDeltaTime();
            player.setRunningRight(false);
            player.getPosition().x -= 200 * dt;
            player.update(dt);
            for (Table table : tables) {
                if (player.collides(table.getBody())) {
                    player.getPosition().x = table.getBody().getX() + table.getBody().getWidth();
                }
            }

            for (Border border : borders) {
                if (player.collides(border.getBody())) {
                    player.getPosition().x = border.getBody().getX() + border.getBody().getWidth();
                }
            }

        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //player.getPosition().x += 200 * Gdx.graphics.getDeltaTime();
            player.setRunningRight(true);
            player.getPosition().x += 200 * dt;
            player.update(dt);
            for (Table table : tables) {
                if (player.collides(table.getBody())) {
                    player.getPosition().x = table.getBody().getX() - player.getBody().getWidth();
                }
            }

            for (Border border : borders) {
                if (player.collides(border.getBody())) {
                    player.getPosition().x = border.getBody().getX() - player.getBody().getWidth();
                }
            }

        }

        for (Coin coin : coins) {
            if (player.collides(coin.getBody()) && !coin.isHidden()) {
                coin.setHidden(true);
                coinSound.play();
                hud.incrementCounter();
            }
        }

        for (Monster monster : monsters) {
            if (player.collides(monster.getBody())) {
                gameStateManager.set(new GameOverState(gameStateManager));
            }
        }

        // make sure the player stays within the screen bounds
//        if (player.getPosition().x < 0) {
//            player.getPosition().x = 0;
//        }
//
//        if (player.getPosition().x > Moving.WORLDWIDTH - player.getBody().getWidth()) {
//            player.getPosition().x = Moving.WORLDWIDTH - player.getBody().getWidth();
//        }
//
//        if (player.getPosition().y < 0) {
//            player.getPosition().y = 0;
//        }
//
//        if (player.getPosition().y > Moving.WORLDHEIGHT - player.getBody().getHeight()) {
//            player.getPosition().y = Moving.WORLDHEIGHT - player.getBody().getHeight();
//        }

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        player.update(dt);

        // Monster collisions
        for(Monster monster: monsters) {

            for (Table table : tables) {
                if (monster.collides(table.getBody())) {
                        monster.invertDirection();
                }
            }

            for (Border border : borders) {
                if (monster.collides(border.getBody())) {
                    monster.invertDirection();
                }
            }

            monster.update(dt);

        }

        camera.position.x = player.getPosition().x;
        camera.position.y = player.getPosition().y;
        hud.update(dt);
        hud.setPosition_X(player.getPosition().x);
        hud.setPosition_Y(player.getPosition().y);
        camera.update();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0);
//        spriteBatch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);


        if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            spriteBatch.draw(
                    player.getAnimation(),
                    player.getPosition().x,
                    player.getPosition().y,
                    player.getBody().getWidth() / 2,
                    player.getBody().getHeight() / 2,
                    player.getBody().getWidth(),
                    player.getBody().getHeight(),
                    1,
                    1,
                    -45f
            );
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            spriteBatch.draw(
                    player.getAnimation(),
                    player.getPosition().x,
                    player.getPosition().y,
                    player.getBody().getWidth() / 2,
                    player.getBody().getHeight() / 2,
                    player.getBody().getWidth(),
                    player.getBody().getHeight(),
                    1,
                    1,
                    45f
            );

        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            spriteBatch.draw(
                    player.getAnimation(),
                    player.getPosition().x,
                    player.getPosition().y,
                    player.getBody().getWidth() / 2,
                    player.getBody().getHeight() / 2,
                    player.getBody().getWidth(),
                    player.getBody().getHeight(),
                    1,
                    1,
                    45f
            );

        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            spriteBatch.draw(
                    player.getAnimation(),
                    player.getPosition().x,
                    player.getPosition().y,
                    player.getBody().getWidth() / 2,
                    player.getBody().getHeight() / 2,
                    player.getBody().getWidth(),
                    player.getBody().getHeight(),
                    1,
                    1,
                    315f
            );

        } else {
            spriteBatch.draw(player.getAnimation(), player.getPosition().x, player.getPosition().y);

        }

        for (Table table : tables) {
            spriteBatch.draw(table.getTexture(), table.getPosition().x, table.getPosition().y);
        }

        for (Coin coin : coins) {
            if (!coin.isHidden()) {
                spriteBatch.draw(coin.getTexture(), coin.getPosition().x, coin.getPosition().y
                );
            }
        }

        for (Monster monster : monsters) {
            spriteBatch.draw(monster.getAnimation(), monster.getPosition().x, monster.getPosition().y);
        }

        spriteBatch.end();

        spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

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
        hud.dispose();
        levelMusic.dispose();
        coinSound.dispose();
        System.out.println("PlayState disposed");
    }
}
