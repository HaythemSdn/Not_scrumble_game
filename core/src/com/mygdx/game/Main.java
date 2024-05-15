package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;


public class Main extends ApplicationAdapter {
	 ShapeRenderer shape;
	 SpriteBatch batch;
	 char[][] textArt;
	 int blockSize = 10;
	 int screenHeight;
	 Texture bg;
	 private float horizontalOffset = 0;
	 private OrthographicCamera camera;
	@Override
	public void create() {
		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		bg = new Texture( Gdx.files.internal("bg.jpeg"));
		//load text art
		try {
			String fileContent = Gdx.files.internal("map.txt").readString();
			String[] lines = fileContent.split("\n");
			textArt = new char[lines.length][];
			for (int i = 0; i < lines.length; i++) {
				textArt[i] = lines[i].toCharArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 screenHeight = textArt.length * blockSize;
	 	int screenWidth = 800; 
		camera = new OrthographicCamera();
		camera.setToOrtho(false, screenWidth, screenHeight);
	}


	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 2f, 1);
		batch.begin();
		batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
    // Set the ShapeRenderer's projection matrix to the camera's combined matrix
    shape.setProjectionMatrix(camera.combined);
    shape.begin(ShapeRenderer.ShapeType.Filled);
	shape.setColor(0.859f, 0.439f, 0.576f, 1f);

    for (int row = 0; row < textArt.length; row++) {
        for (int col = 0; col < textArt[0].length + (int) horizontalOffset / blockSize; col++) {
            int adjustedCol = (col + (int) horizontalOffset / blockSize) % textArt[0].length;
            if (textArt[row][adjustedCol] == '█') {
                shape.rect((col * blockSize - horizontalOffset), screenHeight - (row + 1) * blockSize, blockSize, blockSize);
            }
        }
    }
    shape.end();

    // Update the horizontal offset (example: scroll right at a rate of 50 pixels per second)
    horizontalOffset += Gdx.graphics.getDeltaTime() * 50;

    // Check if the horizontal offset has reached the end of the map
    if (horizontalOffset >= textArt[0].length * blockSize) {
        // Reset the horizontal offset to loop the map
        horizontalOffset = 0;
    }
}

	@Override
	public void dispose() {
		bg.dispose();
		batch.dispose();
		shape.dispose();
	}
}
