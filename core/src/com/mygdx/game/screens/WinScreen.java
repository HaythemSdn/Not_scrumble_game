package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Scramble; 
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Input;



public class WinScreen extends ScreenTemplate {
    private Texture backgroundTexture;
    private Texture youWonTexture;


    public WinScreen(Scramble game) {
        super(game);
        backgroundTexture = new Texture(Gdx.files.internal("imgs/starWarsBg.jpg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music/MainMenuMusic.mp3"));
        youWonTexture = new Texture(Gdx.files.internal("imgs/YouWonFont.png"));

        if (music != null) {
            music.play();
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); // Black color
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        
        game.batch.draw(backgroundTexture, -400, -200);
        
        game.batch.draw(youWonTexture, 150, 200, 400, 60);
        // Render menu elements here
        game.font.getData().setScale(1);
        game.font.setColor(Color.WHITE);

        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "GG !", 100, 150);
        if( GameScreen.score > GameScreen.highScore)GameScreen.highScore=GameScreen.score;
        game.font.draw(game.batch, "Your Score was: " + GameScreen.score, 350, 400);
        game.font.draw(game.batch, "Tap 'R' to restart", 100, 100);
        game.font.draw(game.batch, "Press 'T' for Main Menu", 100, 50);
        game.batch.end();
    
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            game.setScreen(new GameScreen(game));
            dispose();
            GameScreen.score = 0;
            GameScreen.niveau = 1;
            GameScreen.kills = 0;
        }
    
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
        
    }
}
