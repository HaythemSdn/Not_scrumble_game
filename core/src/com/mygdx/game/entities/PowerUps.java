package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class PowerUps implements GameObject {
    private static final String TEXTURE_FILE_NAME = "imgs/PowerUp.png";
    private static final Texture texture = new Texture(Gdx.files.internal(TEXTURE_FILE_NAME));

    private final Rectangle boundingRectangle;
    private final float speed;

    public PowerUps(float x, float y, float width, float height, float speed) {
        this.boundingRectangle = new Rectangle(x, y, width, height);
        this.speed = speed;
    }

    public static PowerUps createRandomPowerUps() {
        float x = 800;
        float y = MathUtils.random(0, 780 - 64);
        float width = 35;
        float height = 35;
        float speed = -200f; // Adjust this value to control the enemy speed
        return new PowerUps(x, y, width, height, speed);
    }
    public void detruit(){
        boundingRectangle.setPosition(-10000, -10000);
        boundingRectangle.setSize(0, 0);
    }

    public Rectangle getHitBox(){
        return new Rectangle(boundingRectangle.x, boundingRectangle.y,boundingRectangle.width/2 ,  boundingRectangle.height/2);
    }

    @Override
    public void update(float delta) {
        boundingRectangle.x += speed * delta;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, boundingRectangle.x, boundingRectangle.y, boundingRectangle.width, boundingRectangle.height);
    }

    @Override
    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}