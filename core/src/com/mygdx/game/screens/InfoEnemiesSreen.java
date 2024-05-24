package com.mygdx.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Scramble;
import com.badlogic.gdx.graphics.Texture;


public class InfoEnemiesSreen extends ScreenTemplate {
    private Texture spaceshipTexture;
    private Texture EnemyTexture;
    private Texture DropTexture;
    private Texture SpiderTexture;
    private Texture Boss;
    private Texture backgroundTexture;


    public InfoEnemiesSreen(Scramble game) {
        super(game);
        music = Gdx.audio.newMusic(Gdx.files.internal("music/MainMenuMusic.mp3"));
        backgroundTexture = new Texture(Gdx.files.internal("imgs/starWarsBg.jpg"));

        if (music != null) {
            music.play();
        }
        spaceshipTexture = new Texture(Gdx.files.internal("imgs/rocketfinalnofire.png"));
        EnemyTexture = new Texture(Gdx.files.internal("imgs/enemyT.png"));
        DropTexture = new Texture(Gdx.files.internal("imgs/rocketSized.png"));
        SpiderTexture = new Texture(Gdx.files.internal("imgs/spideritoReSizeFinal.png"));
        Boss = new Texture(Gdx.files.internal("imgs/Boss.png"));

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0.5f, 0, 1); // Orange color
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, -400, -200);

        game.font.getData().setScale(1);
        game.font.setColor(Color.ORANGE);

        game.batch.draw(spaceshipTexture, 325, 350, 100, 100); 
        game.font.draw(game.batch, "Take care of your spaceShip", 295, 335);

        game.batch.draw(EnemyTexture, 325, 215, 100, 100); 
        game.font.draw(game.batch, "Shoot or Dodge the Enemy's SpaceShip\n    Damage caused: -20 health", 260, 220);


        game.batch.draw(DropTexture, 650, 240, 20, 45); 
        game.font.draw(game.batch, "Dodge the Falling Bombs\nDamage caused: Instant Death", 560, 220);
        
        game.batch.draw(SpiderTexture, 70, 240, 45, 20); 
        game.font.draw(game.batch, "Dodge the Spiders or Destroy \nDamage caused: Instant Death", 10, 220);

        game.batch.draw(Boss, 550, 30, 200, 150); 
        game.font.draw(game.batch, "If use see this fight for your life \nDamage caused: Instant Death", 325, 100);

        

        game.font.getData().setScale(1);
        game.font.setColor(Color.WHITE);

        game.font.draw(game.batch, "Press 'R' to return to Main Menu", 10, 100);
        game.font.draw(game.batch, "c BroGamers 2024", 10, 50);

        game.batch.end();

        // Handle input
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            if (music != null) {
                music.pause();
            }
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (spaceshipTexture != null) {
            spaceshipTexture.dispose();
        }
    }
}