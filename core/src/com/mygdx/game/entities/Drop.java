package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.rendering.Map;

public class Drop implements GameObject {
    private static final String TEXTURE_FILE_NAME = "imgs/rocketSized.png";
    private static final Texture texture = new Texture(Gdx.files.internal(TEXTURE_FILE_NAME));

    private final Rectangle boundingRectangle;
    private final float speed;

    public Drop(float x, float y, float width, float height, float speed) {
        this.boundingRectangle = new Rectangle(x, y, width, height);
        this.speed = speed;
    }

    public static Drop createRandomDrop() {
        float x = MathUtils.random(0, 800 - 64);
        float y = Map.screenHeight;
        float width = 10;
        float height = 20;
        float speed = 200f; // Adjust this value to control the drop speed
        return new Drop(x, y, width, height, speed);
    }
    public void detruit(){
        boundingRectangle.width=0;
        boundingRectangle.height=0;
    }

    @Override
    public void update(float delta) {
        boundingRectangle.y -= speed * delta;
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