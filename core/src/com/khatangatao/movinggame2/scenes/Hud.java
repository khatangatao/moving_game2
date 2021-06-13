package com.khatangatao.movinggame2.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.khatangatao.movinggame2.Moving;


public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private float Position_X;
    private float Position_Y;

    Label countdownLabel;
    Label positionXlabel;
    Label positionYlabel;
    Label timeLabel;
    Label positionY;
    Label positionX;

    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        Position_X = 0;

        viewport = new FitViewport(Moving.VIEWPORTWIDTH, Moving.VIEWPORTHEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        // разместить таблицу вверху
        table.top();
        // указать таблице, что она может заполнить всю
        table.setFillParent(true);


        positionX = new Label("POSITION X", new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        positionY = new Label("POSITION Y", new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.CYAN));

        positionXlabel = new Label(String.format("%06f", Position_X), new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        positionYlabel = new Label(String.format("%06f", Position_Y), new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.CYAN));


        table.add(positionX).expandX().padTop(10);
        table.add(positionY).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);

        //Добавляем новую строку. Все, что ниже этой инструкции, будет размещено на этой строке
        table.row();
        table.add(positionXlabel).expandX();
        table.add(positionYlabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);
    }

    public void update(float dt) {
        timeCount += dt;
        if(timeCount >= 1) {
            worldTimer --;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public void setPosition_X(float x) {
        Position_X = x;
        positionXlabel.setText(String.format("%06f", Position_X));
    }

    public void setPosition_Y(float y) {
        Position_Y = y;
        positionYlabel.setText(String.format("%06f", Position_Y));
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
