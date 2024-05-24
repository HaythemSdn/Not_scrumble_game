package com.mygdx.game.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;

public class Bullet extends Gun{
   
        private static final String BULLET_TEXTURE_FILE_NAME = "imgs/bullet.png";
        private static final Texture bulletTexture = new Texture(Gdx.files.internal(BULLET_TEXTURE_FILE_NAME));
        

        public Bullet(float x, float y, float targetX, float targetY) {
        super(x, y, targetX, targetY);
        super.boundingRectangle = new Rectangle(x, y, bulletTexture.getWidth(), bulletTexture.getHeight());
        }



        public void draw(SpriteBatch batch) {
            batch.draw(bulletTexture, boundingRectangle.x, boundingRectangle.y, boundingRectangle.width, boundingRectangle.height);
        }


}
