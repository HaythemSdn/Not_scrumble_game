package com.mygdx.game.logic;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;

import com.mygdx.game.entities.Boss;
import com.mygdx.game.entities.Drop;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.PowerUps;
import com.mygdx.game.entities.SpaceShip;
import com.mygdx.game.rendering.Map;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.guns.Bullet;
import com.mygdx.game.guns.Bomb;
import com.mygdx.game.entities.Rocket;


public class GameLogic {

    private final ArrayList<Rocket> rocketdrops;
    private final SpaceShip spaceShip;
    private final ArrayList<Drop> raindrops;
    private final ArrayList<Enemy> enemydrops;
    private final ArrayList<PowerUps> powerUpsDrops;
    private final Boss boss;
    private Map gameMap;

    private long lastDropTime;
    private long lastEnemyDropTime;
    private long lastPowerUpTime; // Separate timing for PowerUps
    Music collisionSound = Gdx.audio.newMusic(Gdx.files.internal("music/collision.mp3"));
    Music PowerUpMusic = Gdx.audio.newMusic(Gdx.files.internal("music/powerUp.mp3"));

    public GameLogic(SpaceShip spaceShip, Map gameMap , Boss boss) {
        this.spaceShip = spaceShip;
        this.raindrops = new ArrayList<>();
        this.enemydrops = new ArrayList<>();
        this.powerUpsDrops = new ArrayList<>();
        this.gameMap = gameMap;
        this.boss=boss;
        this.rocketdrops = gameMap.getrocketdrops();


    }

    public void update(float delta) {

        if (GameScreen.niveau != 4){
            spawnPowerUpDrops();
            if (GameScreen.niveau >= 2 && GameScreen.niveau<4 ) {
                spawnRaindrops();
            }
        }
        spawnEnemydrops();
        
        updateEntities(delta);
        checkCollisions();
    }

    private void spawnRaindrops() {
        if (TimeUtils.nanoTime() - lastDropTime > 2000000000) {
            Drop raindrop = Drop.createRandomDrop();
            raindrops.add(raindrop);
            lastDropTime = TimeUtils.nanoTime();
        }
    }

    private void spawnEnemydrops() {
        int x=800;
        int y = MathUtils.random(0, 780 - 64);
        int num =1;
        int timeShoot = 1000000000;
        if(GameScreen.niveau == 4){
            timeShoot= 300000000;
            num = 4;
            x=600;
            y= MathUtils.random(50, 400);
         }
        if (TimeUtils.nanoTime() - lastEnemyDropTime > timeShoot) {
            for (int i = 0; i < num; i++) {
                Enemy enemydrop = Enemy.createEnemy(x, y);
                enemydrops.add(enemydrop);
            }
            lastEnemyDropTime = TimeUtils.nanoTime();
        }
    }

    private void spawnPowerUpDrops() {
        if (TimeUtils.nanoTime() - lastPowerUpTime > 5000000000L) { // Adjusted spawn rate for PowerUps
            PowerUps powerUp = PowerUps.createRandomPowerUps();
            powerUpsDrops.add(powerUp);
            lastPowerUpTime = TimeUtils.nanoTime();

        }
    }

    private void updateEntities(float delta) {
        spaceShip.update(delta);
        for (Drop raindrop : raindrops) {
            raindrop.update(delta);
        }
        for (Enemy enemydrop : enemydrops) {
            enemydrop.update(delta);
        }
        for (PowerUps powerUp : powerUpsDrops) {
            powerUp.update(delta);
        }
    }

    private void checkCollisions() {
        for (Drop raindrop : raindrops) {
            if (Intersector.overlaps(raindrop.getBoundingRectangle(), spaceShip.getBoundingRectangle())) {
                collisionSound.play();
                raindrop.detruit();
                spaceShip.isHunted(1000);

            }
        }

        for(Rocket rocket: rocketdrops){
            if(Intersector.overlaps(rocket.getHitBox(), spaceShip.getBoundingRectangle())){
                collisionSound.play();
            spaceShip.isDead(); 
            spaceShip.isHunted(150);
            SpaceShip.currentTexture = SpaceShip.bomTexture; 

            }
        }

        for (Enemy enemydrop : enemydrops) {
            // collision with spaceship
            if (Intersector.overlaps(enemydrop.getHitBox(), spaceShip.getBoundingRectangle())) {
                if(!enemydrop.isDestroid()){
                    collisionSound.play();
                    enemydrop.detruit();
                    spaceShip.isHunted(20);
                }
            }
            // collision with bullets
            for (Bullet bullet : spaceShip.getShooting().getBullets()) {
                if (Intersector.overlaps(enemydrop.getHitBox(), bullet.getBoundingRectangle())) {
                    GameScreen.score+=50;
                    enemydrop.detruit();
                    bullet.detruit();
                    collisionSound.play();

                }
                if (Intersector.overlaps(boss.getHitBox(), bullet.getBoundingRectangle())) {
                    bullet.detruit();
                    boss.isHunted(20);
                    collisionSound.play();

                }
            }
            // collision with bombs
            for (Bomb bomb : spaceShip.getShooting().getBombs()) {
                if (Intersector.overlaps(enemydrop.getHitBox(), bomb.getBoundingRectangle())) {
                    enemydrop.detruit();
                    bomb.detruit();
                }
                
            }
        }
        for (PowerUps powerUp : powerUpsDrops) {
            if (Intersector.overlaps(powerUp.getBoundingRectangle(), spaceShip.getBoundingRectangle())) {
                powerUp.detruit();
                PowerUpMusic.play();
                spaceShip.collectPowerUp(); // Implement the method to handle power-up collection
            }
        }

        if (gameMap.checkCollision(spaceShip.getBoundingRectangle())) {
            collisionSound.play();
            spaceShip.isHunted(150);
            
        }
    }

    public ArrayList<Drop> getRaindrops() {
        return raindrops;
    }

    public ArrayList<Enemy> getEnemydrops() {
        return enemydrops;
    }

    public ArrayList<PowerUps> getPowerUpsDrops() {
        return powerUpsDrops;
    }
}