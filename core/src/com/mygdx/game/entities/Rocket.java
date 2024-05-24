package com.mygdx.game.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Rocket {
    private static final float SPEED = 300f; // Speed of the rocket
    private final Rectangle boundingRectangle;
    private  Texture rocketTexture;
    private final Music BomMusic = Gdx.audio.newMusic(Gdx.files.internal("music/enemyBom.mp3"));
    public  static Texture BomTexture = new Texture(Gdx.files.internal("imgs/enemyBom.png"));

    /*public createRocket(){

    }*/


    public Rocket(float x, float y) {
        rocketTexture = new Texture(Gdx.files.internal("imgs/spideritoReSizeFinal.png"));
        boundingRectangle = new Rectangle(x, y, 50 , 25);
    }

    public void update(float delta) {
        boundingRectangle.y += SPEED * delta; // Move the rocket upwards
    }

    public void draw(SpriteBatch batch) {
        batch.draw(rocketTexture, boundingRectangle.x, boundingRectangle.y);
    }
    public Rectangle getHitBox(){
        return new Rectangle(boundingRectangle.x, boundingRectangle.y,boundingRectangle.width/2 ,  boundingRectangle.height/2);
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public void dispose() {
        rocketTexture.dispose();
    }
    public void detruit() {
        // Change the current texture to indicate destruction (optional)
        rocketTexture = BomTexture;
        // Play the explosion sound
        BomMusic.play();
    
        // Immediately move the bounding rectangle off-screen and set its size to zero
        boundingRectangle.setPosition(-10000, -10000);
        boundingRectangle.setSize(0, 0);
        // Mark the bullet as inactive
        // Dispose of the bullet resources if necessary
  
       
    }
}
