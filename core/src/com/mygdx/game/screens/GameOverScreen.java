package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Scramble;
import com.badlogic.gdx.graphics.Texture;


import com.badlogic.gdx.Input;



public class GameOverScreen extends ScreenTemplate{

    private Texture GameOverTexture;

    public GameOverScreen (final Scramble game) {
        super(game);
        GameOverTexture = new Texture(Gdx.files.internal("imgs/GameOver.png"));

    }

    @Override
public void render(float delta) {
    ScreenUtils.clear(Color.BLACK);
    game.batch.begin();
    game.batch.draw(GameOverTexture, 200, 0, 400, 400); 
  
    game.font.setColor(Color.WHITE);
/*     game.font.draw(game.batch, "Game Over", 100, 150);
 */    game.font.draw(game.batch, "Your Score was: " + GameScreen.score, 350, 400);
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


    @Override
    public void dispose() {
    }
}
