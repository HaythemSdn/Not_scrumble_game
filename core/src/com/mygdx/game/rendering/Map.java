package com.mygdx.game.rendering;

import java.util.ArrayList;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.OrthographicCamera;  // Import for OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.entities.Rocket;
import java.util.Iterator;
import java.util.HashSet;

public class Map {
    private Rectangle shape;
    private char[][] textArt;
    private int blockSize = 10;
    public static int screenHeight;
    private int screenWidth;
    private float horizontalOffset = 0;
    private OrthographicCamera camera; 
    private final int vitesse = 300;
    private float parallaxOffset = 0; 
     private ArrayList<Rocket> rockets;
    private Set<String> rocketPositions;  // Pour suivre les positions où des fusées ont été spawnées

    String MapFile = Gdx.files.internal("maps/Map1.txt").readString("utf-8");
    private Texture rocket = new Texture(Gdx.files.internal("imgs/spideritoReSizeFinal.png"));
    private Texture parallaxBg = new Texture(Gdx.files.internal("imgs/BackGroundRotated.jpg"));
    private Texture borderUp =new Texture(Gdx.files.internal("imgs/imgLevels/1/borderUp.png"));
    private Texture borderDown =new Texture(Gdx.files.internal("imgs/imgLevels/1/borderDown.png"));
    private Texture bg =new Texture(Gdx.files.internal("imgs/imgLevels/1/bg.png"));

    public Map(OrthographicCamera camera) {
        this.camera = camera;
        this.rockets = new ArrayList<>();
        this.rocketPositions = new HashSet<>();
        chargerMap();
    }

    public void chargerMap() {
            String[] lines = MapFile.split("\n");
            textArt = new char[lines.length][];
           
            for (int i = 0; i < lines.length; i++) {
                textArt[i] = lines[i].toCharArray();

            }
      
        screenHeight = textArt.length * blockSize;
        screenWidth = 800; 
        camera.setToOrtho(false, screenWidth, screenHeight);
    }
    public void render(SpriteBatch batch) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        float parallaxSpeed = vitesse * 0.5f;  // La vitesse du fond en parallaxe est plus lente
        parallaxOffset += parallaxSpeed * Gdx.graphics.getDeltaTime();
        if (parallaxOffset >= parallaxBg.getWidth()) {
            parallaxOffset = 0;
        }
        // Dessiner les éléments de la carte
        batch.draw(parallaxBg, -parallaxOffset, 0, parallaxBg.getWidth(), screenHeight);
        batch.draw(parallaxBg, -parallaxOffset + parallaxBg.getWidth(), 0, parallaxBg.getWidth(), screenHeight);
        for (int row = 0; row < textArt.length; row++) {
            for (int col = 0; col < textArt[0].length; col++) {
                int adjustedCol = (col + (int) horizontalOffset / blockSize) % textArt[0].length;
                float xPosition = col * blockSize - horizontalOffset % blockSize;

                if (textArt[row][adjustedCol] == '█') {
                    shape = new Rectangle(xPosition, screenHeight - (row + 1) * blockSize, blockSize, blockSize);
                    batch.draw(bg, shape.x, shape.y, blockSize, blockSize);
                }
                if (textArt[row][adjustedCol] == 'Ζ') {
                    shape = new Rectangle(xPosition, screenHeight - (row + 1) * blockSize, blockSize, blockSize);
                    batch.draw(borderUp, shape.x, shape.y, blockSize, blockSize);
                }
                if (textArt[row][adjustedCol] == 'B') {
                    shape = new Rectangle(xPosition, screenHeight - (row + 1) * blockSize, blockSize, blockSize);
                    batch.draw(borderDown, shape.x, shape.y, blockSize, blockSize);
                }
                if (textArt[row][adjustedCol] == 'A') {
                    shape = new Rectangle(xPosition, screenHeight - (row + 1) * blockSize, blockSize, blockSize);
                    batch.draw(rocket, shape.x, shape.y, blockSize, blockSize);

                    String positionKey = row + "_" + adjustedCol;  // Unique key for the position
                    if (!rocketPositions.contains(positionKey)) {
                        rockets.add(new Rocket(shape.x, shape.y));
                        rocketPositions.add(positionKey);  // Marquer cette position comme ayant une fusée spawnée
                    }
                }
            }
        }

        // Mettre à jour et dessiner les fusées
        for (Iterator<Rocket> iterator = rockets.iterator(); iterator.hasNext(); ) {
            Rocket rocket = iterator.next();
            rocket.update(Gdx.graphics.getDeltaTime());
            rocket.draw(batch);

            // Supprimer la fusée lorsqu'elle sort de l'écran
            if (rocket.getBoundingRectangle().y > screenHeight) {
                rocket.dispose();
                iterator.remove();
            }
        }
        if(GameScreen.niveau!=4){
 
            
            horizontalOffset += vitesse * Gdx.graphics.getDeltaTime();
        if (horizontalOffset >= textArt[0].length * blockSize) {
            horizontalOffset = 0;
            rocketPositions.clear();  // Réinitialiser les positions des fusées lorsque la carte boucle
        }
        }

        camera.setToOrtho(false, screenWidth, screenHeight);
    }
    public void dispose() {
        bg.dispose();
        borderUp.dispose();
        borderDown.dispose();

        parallaxBg.dispose();  // Dispose de la texture du fond en parallaxe
        for (Rocket rocket : rockets) {
            rocket.dispose();
        }
    }

    public void changeMap(int Level) {
        MapFile= Gdx.files.internal("maps/Map"+Level+".txt").readString("utf-8");
        bg= new Texture(Gdx.files.internal("imgs/imgLevels/"+Level+"/bg.png"));
        borderUp= new Texture(Gdx.files.internal("imgs/imgLevels/"+Level+"/borderUp.png"));
        borderDown= new Texture(Gdx.files.internal("imgs/imgLevels/"+Level+"/borderDown.png"));
        horizontalOffset = 0;
        chargerMap();
    }
    public boolean checkCollision(Rectangle spaceshipBounds) {
        for (int row = 0; row < textArt.length; row++) {
            for (int col = 0; col < textArt[0].length; col++) {
                int adjustedCol = (col + (int) horizontalOffset / blockSize) % textArt[0].length;
                if (textArt[row][adjustedCol] == '█' || textArt[row][adjustedCol] == 'Ζ') {
                    float xPosition = col * blockSize - horizontalOffset % blockSize;
                    Rectangle tileBounds = new Rectangle(xPosition, screenHeight - (row + 1) * blockSize, blockSize, blockSize);
                    if (tileBounds.overlaps(spaceshipBounds)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Rocket> getrocketdrops(){
        return rockets;
    }

}

