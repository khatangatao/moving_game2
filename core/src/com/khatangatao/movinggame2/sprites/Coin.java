package com.khatangatao.movinggame2.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Coin {
    private Vector3 position;
    private Texture texture;
    private Rectangle body;
    private boolean hidden;

    public Coin(int x, int y, String imagepath) {
        position = new Vector3(x, y, 0);
        texture = new Texture(imagepath);
        body = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        hidden = false;
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

    public boolean isHidden(){
        return hidden;
    }

    public void setHidden(boolean flag) {
        hidden = flag;
    }
}
