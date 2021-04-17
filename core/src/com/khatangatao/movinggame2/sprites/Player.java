package com.khatangatao.movinggame2.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private Vector3 position;
    private Vector3 velocity;
    private Texture player;

    public Player(int x, int y) {
        position = new Vector3(x, y, 0);
        player = new Texture("player.png");

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return player;
    }

    public void update(float dt) {

    }

}
