package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.audio.Music;
public class Boss implements GameObject {
    private   final String TEXTURE_FILE_NAME = "imgs/Boss.png";
    private   final Texture texture = new Texture(Gdx.files.internal(TEXTURE_FILE_NAME));
   private   final Texture BomTexture = new Texture(Gdx.files.internal("imgs/bossBoom.png"));
     private   final Music BomMusic = Gdx.audio.newMusic(Gdx.files.internal("music/enemyBom.mp3"));
    private  Texture currentTexture ;
    private BitmapFont font;
    int life = 1000;
    private  Rectangle boundingRectangle;
    public boolean Alive =true;
    // private  float speed;

    public Boss() {
        float x = 600;
        float y = 50;
        float width = 200;
        float height = 320;
        this.boundingRectangle = new Rectangle(x, y, width, height);
        // this.speed = speed;
        currentTexture= texture;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }


    public void detruit(){
    currentTexture = BomTexture;
    Timer.schedule(new Timer.Task() {
        @Override
        public void run() {
            Alive = false;
            boundingRectangle.setPosition(-1000,-1000);
            boundingRectangle.width=0;
            boundingRectangle.height=0;
        }
    }, 1f);   
    BomMusic.play();
    }
    public void isHunted(int point){
        life-=point;
        if(life <=0){
            detruit();
        }
    }
    @Override
    public void update(float delta) {

    }
    public Rectangle getHitBox(){
        return new Rectangle(boundingRectangle.x, boundingRectangle.y,boundingRectangle.width,  boundingRectangle.height);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(currentTexture, boundingRectangle.x, boundingRectangle.y, boundingRectangle.width, boundingRectangle.height);
        font.draw(batch, "HP: " + life, boundingRectangle.x, boundingRectangle.y + 100);
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