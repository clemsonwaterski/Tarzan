package com.tigerstripestech.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tigerstripestech.GameObjects.Bird;
import com.tigerstripestech.GameObjects.Tarzan;
import com.tigerstripestech.GameObjects.Grass;
import com.tigerstripestech.GameObjects.ScrollHandler;
import com.tigerstripestech.GameObjects.Vine;
import com.tigerstripestech.ZBHelpers.AssetLoader;

/**
 * Created by Josh on 7/10/2014.
 */
public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int gameHeight;
    private int midPointY;

    // Game objects
    private Bird bird;
    private Tarzan tarzan;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Vine vine1, vine2, vine3;

    // Game assets
    private TextureRegion bg, grass, tarzanTex;
    private Animation birdAnimation;
    private TextureRegion birdMid, birdDown,birdUp;
    private TextureRegion vine;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY){
        myWorld = world;

        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 500, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        // initilize instance variables
        initGameObjects();
        initAssets();
    }

    private void initGameObjects(){
        bird = myWorld.getBird();
        tarzan = myWorld.getTarzan();
        scroller = myWorld.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        vine1 = scroller.getVine1();
        vine2 = scroller.getVine2();
        vine3 = scroller.getVine3();
    }

    private void initAssets(){
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        birdAnimation = AssetLoader.birdAnimation;
        birdDown = AssetLoader.birdDown;
        birdMid = AssetLoader.bird;
        birdUp = AssetLoader.birdUp;
        vine = AssetLoader.vine;
        tarzanTex = AssetLoader.tarzan;
    }

    private void drawGrass() {
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawVines() {
        batcher.draw(vine, vine1.getX(), vine1.getY(), vine1.getWidth(), vine1.getHeight());
        //batcher.draw(vine, vine1.getX(), vine1.getY() + vine1.getHeight() + 45, vine1.getWidth(), midPointY + 66 - (vine1.getHeight() + 45));

        batcher.draw(vine, vine2.getX(), vine2.getY(),vine2.getWidth(), vine2.getHeight());
        //batcher.draw(vine, vine2.getX(), vine2.getY() + vine2.getHeight() + 45, vine2.getWidth(), midPointY + 66 - (vine2.getHeight() + 45));

        batcher.draw(vine, vine3.getX(), vine3.getY(),vine3.getWidth(), vine3.getHeight());
        //batcher.draw(vine, vine3.getX(), vine3.getY() + vine3.getHeight() + 45, vine3.getWidth(), midPointY + 66 - (vine3.getHeight() + 45));
    }

    private void drawTarzan(){
        batcher.draw(tarzanTex, tarzan.getX(), tarzan.getY(), tarzan.getWidth() / 2.0f, tarzan.getHeight() / 2.0f, tarzan.getWidth(), tarzan.getHeight(), 1, 1, tarzan.getRotation());
    }

    public void render(float runTime) {

        // Fill game screen background; prevents flickering
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 500, midPointY + 266);

        // Draw grass
        shapeRenderer.setColor(147 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 266, 500, 11);

        // Draw dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 277, 500, 100);

        // TODO: TEMP
        /*
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(tarzan.getBoundingCircle().x, tarzan.getBoundingCircle().y, tarzan.getBoundingCircle().radius);
        */

        shapeRenderer.end();

        batcher.begin();
        // Disable transparency for performance if not necessary
        batcher.disableBlending();
        //batcher.draw(bg, 0, midPointY + 23, 136, 43);

        //drawGrass();
        drawVines();


        // requires transparency for skulls and bird
        batcher.enableBlending();
        drawTarzan();
        /*
        if (bird.shouldntFlap()){
            batcher.draw(birdMid,bird.getX(), bird.getY(), bird.getWidth()/ 2.0f, bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        }
        else{
            batcher.draw(birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth() / 2.0f, bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        }
        */

        if (myWorld.isReady()) {
            // Draw shadow first
            AssetLoader.shadow.draw(batcher, "Touch here", (500 / 2)
                    - (53), (gameHeight / 2));
            // Draw text
            AssetLoader.font.draw(batcher, "Touch here", (500 / 2)
                    - (53 - 1), ((gameHeight / 2) - 1));
        } else {
            if (myWorld.isGameOver()) {
                AssetLoader.shadow.draw(batcher, "Game Over", (500 / 2)
                        - (53), ((gameHeight / 2) - 25));
                AssetLoader.font.draw(batcher, "Game Over", (500 / 2)
                        - (53 - 1), ((gameHeight / 2) - 1 - 25));

                AssetLoader.shadow.draw(batcher, "Try again?", (500 / 2)
                        - (53), (gameHeight / 2));
                AssetLoader.font.draw(batcher, "Try again?", (500 / 2)
                        - (53 - 1), ((gameHeight / 2) - 1));
            }

            // Convert integer into String
            String score = myWorld.getScore() + "";

            // Draw shadow first
            AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2) - (3 * score.length()), 12);
            // Draw text
            AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2) - (3 * score.length() - 1), 11);
        }

        batcher.end();

    }
}
