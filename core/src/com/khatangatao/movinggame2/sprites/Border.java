package com.khatangatao.movinggame2.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Border {
    private Vector3 position;
    private Texture texture;
    private Rectangle body;

    public Border(float x, float y, float width, float height) {
        position = new Vector3(x, y, 0);
        texture = new Texture("border.png");
        body = new Rectangle(position.x, position.y, width, height);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getBody() {
        return body;
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(body);
    }

    public void dispose() {
        texture.dispose();
    }
}
