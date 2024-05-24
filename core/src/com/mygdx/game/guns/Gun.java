package com.mygdx.game.guns;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


abstract public class Gun {
    protected  Rectangle boundingRectangle;
    protected  Vector3 direction;
    protected final float speed;

    public Gun(float x, float y, float targetX, float targetY) {
        direction = new Vector3(targetX - x, targetY - y, 0).nor();
        speed = 500f; // Adjust this value to control the bullet speed
    }

    public void update(float delta) {
        boundingRectangle.x += direction.x * speed * delta;
        boundingRectangle.y += direction.y * speed * delta;
    }

    public abstract void draw(SpriteBatch batch) ;
    public void detruit(){
        boundingRectangle.setPosition(-1000,-1000);
        boundingRectangle.width=0;
        boundingRectangle.height=0;
    }
    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }
}



