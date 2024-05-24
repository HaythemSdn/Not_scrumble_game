package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.badlogic.gdx.utils.Timer;

public class Scramble extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        // Set the initial screen (e.g., MainMenuScreen)
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render(); // This line ensures that the rendering loop continues
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public void GameOver() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                setScreen(new GameOverScreen(Scramble.this));
            }
        }, 0.5f); 
    }
    

}