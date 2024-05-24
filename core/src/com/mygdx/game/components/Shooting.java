package com.mygdx.game.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import com.mygdx.game.guns.Bomb;
import com.mygdx.game.guns.Bullet;

public class Shooting {

    private  Rectangle boundingRectangle;
    private  ArrayList<Bullet> bullets;
    private  ArrayList<Bomb> bombs;

    public Shooting(Rectangle boundingRectangle) {
        this.boundingRectangle = boundingRectangle;
        this.bullets = new ArrayList<>();
        this.bombs = new ArrayList<>();
    }

    public void update(float delta) {
        // Update bullets and bombs
        for (Bullet bullet : bullets) {
            bullet.update(delta);
        }

        for (Bomb bomb : bombs) {
            bomb.update(delta);
        }
    }

    public void draw(SpriteBatch batch) {
        // Draw bullets and bombs
        for (Bullet bullet : bullets) {
            bullet.draw(batch);
        } 

        for (Bomb bomb : bombs) {
            bomb.draw(batch);
        }

    }
    public  ArrayList<Bullet>  getBullets(){
        return  bullets;
    }
    public  ArrayList<Bomb>  getBombs(){
        return  bombs;
    }

    public void addBullet(Vector3 clickPos) {
        float bulletX = boundingRectangle.x + boundingRectangle.width / 2;
        float bulletY = boundingRectangle.y + boundingRectangle.height / 2;
        bullets.add(new Bullet(bulletX, bulletY, clickPos.x, clickPos.y));
    }

    public void addBomb(Vector3 clickPos) {
        float bombX = boundingRectangle.x + boundingRectangle.width / 2;
        float bombY = boundingRectangle.y + boundingRectangle.height / 2;
        bombs.add(new Bomb(bombX, bombY, bombX, 0));
    }

 

    
}