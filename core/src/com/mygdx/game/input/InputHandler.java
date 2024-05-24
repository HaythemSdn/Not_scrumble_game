package com.mygdx.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.entities.SpaceShip;

public class InputHandler implements InputProcessor {
    private final SpaceShip spaceShip;

    public InputHandler(SpaceShip spaceShip) {
        this.spaceShip = spaceShip;
    }

    @Override
    public boolean keyDown(int keycode) {
        // Handle key down events here
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // Handle key up events here
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // Handle key typed events here
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            Vector3 clickPos = new Vector3(screenX, screenY, 0);
            spaceShip.addBullet(clickPos);
        } else if (button == Input.Buttons.RIGHT) {
            Vector3 clickPos = new Vector3(screenX, screenY, 0);
            spaceShip.addBomb(clickPos);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Handle touch up events here
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Handle touch dragged events here
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // Handle mouse moved events here
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // Handle scroll events here
        return false;
    }
}