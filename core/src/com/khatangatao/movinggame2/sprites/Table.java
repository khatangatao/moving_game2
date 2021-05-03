package com.khatangatao.movinggame2.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Table {
    private Vector3 position;
    private Texture texture;
    private Rectangle body;

    public Table(int x, int y) {
        position = new Vector3(x, y, 0);
        texture = new Texture("table.png");
        body = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBody() {
        return body;
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(body);
    }

    public void dispose() {
        texture.dispose();
    }
}
