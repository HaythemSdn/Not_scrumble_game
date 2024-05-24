package com.mygdx.game.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Scramble;
import com.mygdx.game.entities.Drop;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.PowerUps;
import com.mygdx.game.entities.SpaceShip;
import com.mygdx.game.logic.GameLogic;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.entities.Boss;


public class Renderer {
    private  Scramble game;
    private  SpaceShip spaceShip;
    private  Boss boss;
    private  Map map;

    public Renderer(Scramble game, SpaceShip spaceShip, Map map ,Boss boss) {
        this.game = game;
        this.spaceShip = spaceShip;
        this.map = map;
        this.boss = boss;
    }

    public void render(SpriteBatch batch, GameLogic gameLogic) {
        map.render(batch);
        spaceShip.draw(batch);
        spaceShip.getShooting().draw(batch);
        if(GameScreen.niveau == 4){
            boss.draw(batch);
        }
        for (Drop raindrop :gameLogic.getRaindrops() ){
                raindrop.draw(batch);
            }
        
        for (Enemy enemydrop : gameLogic.getEnemydrops() ){
            enemydrop.draw(batch);
        }

        for (PowerUps powerUp : gameLogic.getPowerUpsDrops() ){
            powerUp.draw(batch);
        }

        if(spaceShip.isDead()){
            game.GameOver();
        }
        // Render other game elements here
    }
}