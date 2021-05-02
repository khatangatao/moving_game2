package com.khatangatao.movinggame2.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Rectangle body;

    public Player(int x, int y) {
        position = new Vector3(x, y, 0);
        texture = new Texture("player.png");
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

    public void update(float dt) {
        body.setPosition(position.x, position.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(body);
    }

}
