package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.Movement;
import com.mygdx.game.components.Shooting;
import  com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;

public class SpaceShip implements GameObject {
    private static final String TEXTURE_FILE_NAME_1 = "imgs/rocketMgada.png";
    private static final String TEXTURE_FILE_NAME_2 = "imgs/rocketfinalnofire.png";
    private static final Texture texture1 = new Texture(Gdx.files.internal(TEXTURE_FILE_NAME_1));
    private static final Texture texture2 = new Texture(Gdx.files.internal(TEXTURE_FILE_NAME_2));
    public static final Texture bomTexture = new Texture(Gdx.files.internal("imgs/bom.png"));

    private final Rectangle boundingRectangle;
    private final Movement movement;
    private final Shooting shooting;
    public  static Texture currentTexture;
    private boolean useTexture1 = true;
    private int life = 100 ;

    private BitmapFont font;
    // private float healthDecreaseRate = 1f; 

    public SpaceShip(Rectangle boundingRectangle, float viewportWidth, float viewportHeight) {
        this.boundingRectangle = boundingRectangle;
        this.movement = new Movement(boundingRectangle, viewportWidth, viewportHeight);
        this.shooting = new Shooting(boundingRectangle);
        currentTexture = texture1;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    @Override
    public void update(float delta) {
        movement.update(delta);
        shooting.update(delta);
        
        // Ensure health doesn't go below 0
     
        updateTexture();
    }

    private void updateTexture() {

        if (System.currentTimeMillis() % 2000 < 1000) {
            if (!useTexture1) {
                currentTexture = texture1;
                useTexture1 = true;
            }
        } else {
            if (useTexture1) {
                currentTexture = texture2;
                useTexture1 = false;
            }
        }
    }
    
    @Override
    public void draw(SpriteBatch batch) {
        
        batch.draw(currentTexture, boundingRectangle.x, boundingRectangle.y, boundingRectangle.width, boundingRectangle.height);
        shooting.draw(batch);
        font.draw(batch, "HP: " + life, boundingRectangle.x, boundingRectangle.y - 20);

    }
    public void returnToIntialPosition() {
        boundingRectangle.x = 200;
        boundingRectangle.y = 200;
    }
    @Override
    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public void addBullet(Vector3 clickPos) {
        shooting.addBullet(clickPos);
    }

    public void addBomb(Vector3 clickPos) {
        shooting.addBomb(clickPos);
    }

    public Shooting getShooting() {
        return shooting;
    }
    public void isHunted(int point) {
        /* life */
        life -= point;
        if( point <= 0){
            currentTexture = bomTexture;
        }
       
    }
    public boolean isDead(){
        
        return life<=0 ;
    }

    public void collectPowerUp() {
       
        life += 30; 
        if (life > 100) {
            life = 100; 
        }
    }


    
    @Override
    public void dispose() {
        texture1.dispose();
        texture2.dispose();
    }
}