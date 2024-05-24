package com.mygdx.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Scramble;
import com.badlogic.gdx.graphics.Texture;


public class SettingsScreen extends ScreenTemplate {
    private Texture spaceshipTexture;
    private Texture EnemyTexture;
    private Texture DropTexture;
    private Texture SpiderTexture;
    private Texture backgroundTexture;


    public SettingsScreen(Scramble game) {
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
        
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0.5f, 0, 1); // Orange color
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

  /*      
        game.batch.draw(spaceshipTexture, 600, 100, 100, 100); 
        game.font.draw(game.batch, "Take care of your spaceShip", 560, 100);

        game.batch.draw(EnemyTexture, 325, 100, 100, 100); 
        game.font.draw(game.batch, "Shoot or Dodge the Enemy's SpaceShip\n    Damage caused: -20 health", 260, 100);

        game.batch.draw(DropTexture, 100, 120, 20, 45); 
        game.font.draw(game.batch, "Dodge the Falling Bombs\nDamage caused: Instant Death", 10, 100);
        
        game.batch.draw(SpiderTexture, 70, 270, 45, 20); 
        game.font.draw(game.batch, "Dodge the Spiders or Destroy \nDamage caused: Instant Death", 10, 250); */

        
        game.batch.draw(backgroundTexture, -400, -200);

        game.font.getData().setScale(2);
        game.font.setColor(Color.ORANGE);
        game.font.draw(game.batch, "Instructions", 350, 450);
        game.font.getData().setScale(1);

        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "Use W or UP-ARROW to move UP\n", 250, 400);
        game.font.draw(game.batch, "Use S or DOWN-ARROW to move DOWN`\n", 250, 360);
        game.font.draw(game.batch, "Use A or LEFT-ARROW to move LEFT\n", 250, 320);
        game.font.draw(game.batch, "Use D or RIGHT-ARROW to move RIGHT\n", 250, 280);
        game.font.draw(game.batch, "Use MOUSE-RIGHT-CLICK to drop BOMBS\n", 250, 240);
        game.font.draw(game.batch, "Use MOUSE-LEFT-CLICK to shoot bullets to CURSOR LOCATION\n", 250, 200);
        game.font.draw(game.batch, "Press P while in Game To pause the Game\n", 250, 160);

        game.font.draw(game.batch, "Press 'R' to return to Main Menu\n", 250, 100);
        game.font.draw(game.batch, "c BroGamers 2024", 200, 50);

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