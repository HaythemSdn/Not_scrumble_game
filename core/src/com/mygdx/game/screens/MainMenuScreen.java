package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Scramble; 
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Input;


public class MainMenuScreen extends ScreenTemplate {
    private Texture backgroundTexture;
    private Texture HowFarTexture;
    private Texture HighScoreTexture;

    private enum MenuOption {
        PLAY, SETTINGS, INFO, EXIT
    }

    private MenuOption selectedOption;

    public MainMenuScreen(Scramble game) {
        super(game);
        backgroundTexture = new Texture(Gdx.files.internal("imgs/starWarsBg.jpg"));
        HowFarTexture = new Texture(Gdx.files.internal("imgs/HowFar.png"));
        HighScoreTexture = new Texture(Gdx.files.internal("imgs/HighScore.png"));

        music = Gdx.audio.newMusic(Gdx.files.internal("music/MainMenuMusic.mp3"));
        if (music != null) {
            music.play();
        }

        selectedOption = MenuOption.PLAY; // Default selection
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); // Black color
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, -400, -200);

        game.batch.draw(HowFarTexture, 200, 70, 400, 60);
        game.batch.draw(HighScoreTexture, 300, 430, 150, 30);
        

        
        // Render menu elements here
        game.font.getData().setScale(2);
        game.font.setColor(Color.WHITE);
        game.font.getData().setScale(1);
        game.font.draw(game.batch, ""+ GameScreen.score, 370, 420);
        game.font.draw(game.batch, "(you are not) Welcome to Scramble :)) !!!  ", 260, 320);
        game.font.getData().setScale(1);
        game.font.draw(game.batch, " c BroGrammmers 2024", 80, 30);
        // Draw menu options
        drawMenuOption(game, "-> Press 'P' to Play!, (please don't) <-", 270, 260, selectedOption == MenuOption.PLAY);
        drawMenuOption(game, "-> Press 'T' to learn how to play! <-", 270, 220, selectedOption == MenuOption.SETTINGS);
        drawMenuOption(game, "-> Press 'E' to learn infos about enemies <-", 270, 180, selectedOption == MenuOption.INFO);
        drawMenuOption(game, "-> Press 'Q' to Quit! <-", 270, 140, selectedOption == MenuOption.EXIT);

        game.font.getData().setScale(2);
        game.font.getData().setScale(1);
        
        // game.font.draw(game.batch, ";P", 345, 35);
        game.font.setColor(Color.BLACK);

        game.batch.end();

        // Handle input
        handleInput();
    }

    private void drawMenuOption(Scramble game, String text, float x, float y, boolean selected) {
        if (selected) {
            game.font.setColor(Color.ORANGE);
        } else {
            game.font.setColor(Color.WHITE);
        }
        game.font.draw(game.batch, text, x, y);
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            switch (selectedOption) {
                case PLAY:
                    selectedOption = MenuOption.EXIT;
                    break;
                case SETTINGS:
                    selectedOption = MenuOption.PLAY;
                    break;
                case INFO:
                    selectedOption = MenuOption.SETTINGS;
                    break;
                case EXIT:
                    selectedOption = MenuOption.INFO;
                    break;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            switch (selectedOption) {
                case PLAY:
                    selectedOption = MenuOption.SETTINGS;
                    break;
                case SETTINGS:
                    selectedOption = MenuOption.INFO;
                    break;
                case INFO:
                    selectedOption = MenuOption.EXIT;
                    break;
                case EXIT:
                    selectedOption = MenuOption.PLAY;
                    break;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (music != null) {
                music.pause();
            }
            switch (selectedOption) {
                case PLAY:
                    GameScreen.score = 0;
                    GameScreen.niveau = 1;
                    GameScreen.kills = 0;
                    game.setScreen(new GameScreen(game));
                    dispose();
                    break;
                case SETTINGS:
                    game.setScreen(new SettingsScreen(game));
                    dispose();
                    break;
                case INFO:
                    game.setScreen(new InfoEnemiesSreen(game));
                    dispose();
                    break;
                case EXIT:
                    Gdx.app.exit();
                    break;
            }
        }
    

    if(Gdx.input.isKeyPressed(Input.Keys.P)) {
        if (music != null) {
            music.pause();
        }
        GameScreen.score = 0;
        GameScreen.niveau = 1;
        GameScreen.kills = 0;
        game.setScreen(new GameScreen(game));

        dispose();
    } else if (Gdx.input.isKeyPressed(Input.Keys.T)) {
        if (music != null) {
            music.pause();
        }
        game.setScreen(new SettingsScreen(game));
        dispose();
    }else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
        if (music != null) {
            music.pause();
        }
        game.setScreen(new InfoEnemiesSreen(game));
        dispose();
    }else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
        Gdx.app.exit();
    }
}
}