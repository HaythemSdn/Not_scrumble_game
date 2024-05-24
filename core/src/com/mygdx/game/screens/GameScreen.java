package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.entities.Boss;
import com.mygdx.game.entities.SpaceShip;
import com.mygdx.game.logic.GameLogic;
import com.mygdx.game.rendering.Map;
import com.mygdx.game.rendering.Renderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input;
import com.mygdx.game.Scramble;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.Texture;


public class GameScreen extends ScreenTemplate {

    private final Map map;
    private final GameLogic gameLogic;
    private final Renderer renderer;
    private final SpaceShip spaceShip;
    private final Boss boss;
    private boolean isPaused;
    public static int score =0;
    public static int highScore=0;
    public static int niveau =1;
    public static int kills =0;
    public float LevelTimeWait = 0;
    public static boolean isPausedForLevel=false;
    private Texture backgroundTexture;
    final static Texture KeepGoingTexture = new Texture(Gdx.files.internal("imgs/keep-going.png"));
    final static Texture ScoreTexture = new Texture(Gdx.files.internal("imgs/score-2.png"));



    public GameScreen(Scramble game) {
        super(game);
        map = new Map(camera);
        spaceShip = new SpaceShip(new Rectangle(200, 200, 50, 30), 800, 400);
        boss = new Boss();
        gameLogic = new GameLogic(spaceShip,map,boss);
        renderer = new Renderer(game, spaceShip, map,boss);
        backgroundTexture = new Texture(Gdx.files.internal("imgs/starWarsBg.jpg"));

        music = Gdx.audio.newMusic(Gdx.files.internal("music/idk_what_love_is.mp3"));
        if (music != null) {
            music.play();
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        checkInputs();

        if (spaceShip.isDead()) {
           
            dispose();
            return; // Ensure to return after disposing
        }
        if (score/niveau >= 1000.0 && niveau <4){
            niveau++;
            isPausedForLevel = true;
        }
        toggleMusic();
       if (isPausedForLevel){
            renderTransitionScreen();
            return; // Ensure not to render the game when paused
        }

        if (isPaused) {
            renderPauseScreen();
            return; // Ensure not to render the game when paused
        }

        if(boss.Alive==false){
            game.setScreen(new WinScreen(game));            
            music.pause();
            return;      
          }

        gameLogic.update(delta);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        renderer.render(game.batch, gameLogic);
        addContent();
        game.batch.end();


    }




    //pour la pause
    private void togglePause() {
        isPaused = !isPaused;
    }

    private void toggleMusic() {
        if (isPaused || isPausedForLevel) {
                music.pause();
            } else {
                music.play();
            }
        }
    //pour l'ecran de pause
    private void renderPauseScreen() {
    
        game.batch.begin();
        game.batch.draw(backgroundTexture, -400, -200);
        
        game.font.getData().setScale(2);
        game.font.setColor(Color.YELLOW);
        game.font.draw(game.batch, "Game Paused", 305, 340);
        game.font.getData().setScale(1);
        game.font.draw(game.batch, "-Press 'P' to Resume-", 320, 200);
        game.font.draw(game.batch, "--Press 'R' to Restart--", 320, 160);
        game.font.draw(game.batch, "Your Score :" +  score , 350, 400);

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            game.setScreen(new GameScreen(game));
            dispose();
            GameScreen.score = 0;
            GameScreen.niveau = 1;
            GameScreen.kills = 0;
        }
        game.batch.end();
    }
    public  String pad(int number) {
        return String.format("%05d", number);
    }
   
    //pour les niveaux
    private void renderTransitionScreen() {
        LevelTimeWait +=3f*niveau;
        game.batch.begin();
        game.batch.draw(backgroundTexture, -400, -200);
        Texture levelUpTexture = new Texture(Gdx.files.internal("imgs/level"+niveau+".png"));
        game.batch.draw(levelUpTexture, 320, 200, 100, 80);
        game.batch.draw(KeepGoingTexture, 260, 80, 220, 100);
        game.batch.draw(ScoreTexture, 300, 350, 150, 40);
        game.font.setColor(Color.WHITE);
        game.font.getData().setScale(2);
        game.font.draw(game.batch, ""+pad(score) , 335, 350);
        map.changeMap(niveau);
        spaceShip.returnToIntialPosition();
        Timer.schedule(new Timer.Task() {   
            @Override
            public void run() {
                isPausedForLevel = false;
            }
        }, LevelTimeWait);
        game.batch.end();
    }
    //pour les inputs
    public void checkInputs(){
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector3 clickPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(clickPos);
            spaceShip.addBullet(clickPos);
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            Vector3 clickPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(clickPos);
            spaceShip.addBomb(clickPos);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            togglePause();
        }
    }
    //pour le contenu
    public void addContent() {
        game.font.setColor(Color.WHITE);
        if(niveau != 4){
            score++;
        }
        game.font.draw(game.batch, "Your Score :" +  score, 350, 400);
        game.font.draw(game.batch, "Niveau :" +  niveau , 80, 400);
        game.font.draw(game.batch, "Kills :" +  kills , 750, 400);
        game.font.getData().setScale(2);
        // Draw "Press P to pause" message in the bottom right corner
        game.font.setColor(Color.WHITE);
        game.font.getData().setScale(1);
        game.font.draw(game.batch, "Press P to pause", camera.viewportWidth - 150, 20);

    }
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void dispose() {
        if (music != null) {
            music.dispose();
        }
    }
}
