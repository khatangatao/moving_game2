package com.khatangatao.movinggame2.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Monster {
    private boolean runningRight;

    private Vector3 position;
    private int velocity;
    private Texture texture;
    private Rectangle body;
    private Animation animation;

    public Monster(int x, int y) {
        position = new Vector3(x, y, 0);
        texture = new Texture("monsteranimation.png");
        animation = new Animation(new TextureRegion(texture), 3, 0.5f);
        body = new Rectangle(position.x, position.y, animation.getFrame().getRegionWidth(), animation.getFrame().getRegionHeight());
        runningRight = true;
        velocity = 30;
    }

    public TextureRegion getAnimation() {
        if (runningRight && !animation.getFrame().isFlipX()) {
            return animation.getFrame();
        } else if (runningRight && animation.getFrame().isFlipX()) {
            TextureRegion result = animation.getFrame();
            result.flip(true, false);
            return result;
        } else if (!runningRight && animation.getFrame().isFlipX()){
            return animation.getFrame();
        } else {
            TextureRegion result = animation.getFrame();
            result.flip(true, false);
            return result;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBody() {
        return body;
    }


    public void update(float dt) {
        position.x += velocity * dt;
        body.setPosition(position.x, position.y);
        animation.update(dt);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(body);
    }

    public void invertDirection(){
        velocity *=-1;
        runningRight = !runningRight;
    }

    public void dispose() {
        texture.dispose();

    }

}
