package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;


public class MyGdxGame extends ApplicationAdapter {
	 ShapeRenderer shape;
	 char[][] textArt;
	 int blockSize = 10;
	 int screenHeight;
	 private float horizontalOffset = 0;
	 private OrthographicCamera camera;
	@Override
	public void create() {
		shape = new ShapeRenderer();
		//load text art
		try {
			String fileContent = Gdx.files.internal("text_art.txt").readString();
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
		ScreenUtils.clear(1, 0, 0, 1);

    // Set the ShapeRenderer's projection matrix to the camera's combined matrix
    shape.setProjectionMatrix(camera.combined);

    shape.begin(ShapeRenderer.ShapeType.Filled);
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
		
	}
}
