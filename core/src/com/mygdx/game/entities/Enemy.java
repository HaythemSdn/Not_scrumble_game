package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.screens.GameScreen;
import com.badlogic.gdx.utils.Timer;

public class Enemy implements GameObject {
    private   static final String TEXTURE_FILE_NAME = "imgs/enemyT.png";
    private  static final Texture texture = new Texture(Gdx.files.internal(TEXTURE_FILE_NAME));
    private  static final Texture BomTexture = new Texture(Gdx.files.internal("imgs/enemyBom.png"));
    private   static final Music BomMusic = Gdx.audio.newMusic(Gdx.files.internal("music/enemyBom.mp3"));
    private  Texture currentTexture ;

    private  Rectangle boundingRectangle;
    private  float speed;

    public Enemy(float x, float y, float width, float height, float speed) {
        this.boundingRectangle = new Rectangle(x, y, width, height);
        this.speed = speed;
        currentTexture= texture;
    }

    public static Enemy createEnemy(int x,int y) {
        float width = 64;
        float height = 64;
        float speed = -200f; // Adjust this value to control the enemy speed
        return new Enemy(x, y, width, height, speed);
    }
    public void detruit(){
        BomMusic.play();
        GameScreen.kills++; 
 
        currentTexture=BomTexture;
            Timer.schedule(new Timer.Task() {   
                @Override
                public void run() {
                    boundingRectangle.setPosition(-10000, -10000);
                    boundingRectangle.setSize(0, 0);
                }
            }, 1f);
      }

    public boolean isDestroid(){
        return currentTexture==BomTexture;
    }
    public Rectangle getHitBox(){
        return new Rectangle(boundingRectangle.x, boundingRectangle.y,boundingRectangle.width/2 ,  boundingRectangle.height/2);
    }

    @Override
    public void update(float delta) {
        boundingRectangle.x += speed * delta;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(currentTexture, boundingRectangle.x, boundingRectangle.y, boundingRectangle.width, boundingRectangle.height);
    }

    @Override
    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}