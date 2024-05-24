
package com.mygdx.game.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;




public class Bomb  extends Gun{

    private static final String BOMB_TEXTURE_FILE_NAME = "imgs/bomb.png";

        private static final Texture bombTexture = new Texture(Gdx.files.internal(BOMB_TEXTURE_FILE_NAME));

        public Bomb(float x, float y, float targetX, float targetY) {
            super(x, y, targetX, targetY);
            boundingRectangle = new Rectangle(x, y, bombTexture.getWidth(), bombTexture.getHeight());
        }

        public void draw(SpriteBatch batch) {
            batch.draw(bombTexture, boundingRectangle.x, boundingRectangle.y, boundingRectangle.width, boundingRectangle.height);
        }

}
