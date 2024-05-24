package com.mygdx.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

public class Movement {
    private static final float MOVEMENT_SPEED = 200f; // Adjust this value to control the movement speed

    private final Rectangle boundingRectangle;
    private final float viewportWidth;
    private final float viewportHeight;

    public Movement(Rectangle boundingRectangle, float viewportWidth, float viewportHeight) {
        this.boundingRectangle = boundingRectangle;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
    }

    public void update(float delta) {
        float deltaX = 0;
        float deltaY = 0;

        // Handle keyboard input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            deltaX -= MOVEMENT_SPEED * delta + 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            deltaX += MOVEMENT_SPEED * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            deltaY += MOVEMENT_SPEED * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            deltaY -= MOVEMENT_SPEED * delta;
        }

        // Update the bounding rectangle position
        boundingRectangle.x += deltaX;
        boundingRectangle.y += deltaY;

        // Clamp the bounding rectangle within the viewport
        boundingRectangle.x = Math.max(0, Math.min(boundingRectangle.x, viewportWidth - boundingRectangle.width));
        boundingRectangle.y = Math.max(0, Math.min(boundingRectangle.y, viewportHeight - boundingRectangle.height));
    }
}