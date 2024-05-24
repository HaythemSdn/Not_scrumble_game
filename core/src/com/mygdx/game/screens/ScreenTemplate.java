package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Scramble;

public class ScreenTemplate implements Screen {
    protected final Scramble game;
    protected OrthographicCamera camera;
    protected Music music;

    public ScreenTemplate(Scramble game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        // Uncomment and set your music file path
        // music = Gdx.audio.newMusic(Gdx.files.internal("MainMenuMusic.mp3"));
        // music.play();
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        if (music != null) {
            music.dispose();
        }
    }
}
