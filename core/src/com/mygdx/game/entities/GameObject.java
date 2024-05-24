package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface GameObject {
    void update(float delta);
    void draw(SpriteBatch batch);
    Rectangle getBoundingRectangle();
    void dispose();
}