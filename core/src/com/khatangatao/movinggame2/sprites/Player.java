package com.khatangatao.movinggame2.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {
    public enum State {STANDING, RUNNING};
    public State currentState;
    public State previousState;
    private float stateTimer;
    private boolean runningRight;

    private Vector3 position;
    private Vector3 velocity;
//    private Texture texture;
    private Rectangle body;
    private Animation animation;

    private Animation playerRun;
    private Animation playerStand;

    public Player(int x, int y) {
        position = new Vector3(x, y, 0);
//        texture = new Texture("player.png");
        Texture texture = new Texture("playeranimation.png");
        animation = new Animation(new TextureRegion(texture), 3, 0.5f);
//        body = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        body = new Rectangle(position.x, position.y, animation.getFrame().getRegionWidth(), animation.getFrame().getRegionHeight());

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
    }

    public TextureRegion getAnimation(){
        return animation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBody() {
        return body;
    }

//    public Texture getTexture() {
//        return texture;
//    }

    public void update(float dt) {
        body.setPosition(position.x, position.y);
        animation.update(dt);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(body);
    }

    public void dispose() {
//        texture.dispose();

    }

}
