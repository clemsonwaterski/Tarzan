package com.tigerstripestech.GameObjects;

import com.tigerstripestech.GameWorld.GameWorld;
import com.tigerstripestech.ZBHelpers.AssetLoader;

/**
 * Created by Josh on 7/20/2014.
 */
public class ScrollHandler {
    private GameWorld gameWorld;
    private Grass frontGrass, backGrass;
    //private Pipe vine1, vine2, vine3;
    private Vine vine1, vine2, vine3;

    public static final int SCROLL_SPEED = -59;
    public static final int VINE_GAP = 49;

    public ScrollHandler(GameWorld gameWorld, float yPos){
        this.gameWorld = gameWorld;
        frontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11, SCROLL_SPEED);

        vine1 = new Vine(210, 0, 22, 60, SCROLL_SPEED, yPos);
        vine2 = new Vine(vine1.getTailX() + VINE_GAP, 0, 22, 70, SCROLL_SPEED, yPos);
        vine3 = new Vine(vine2.getTailX() + VINE_GAP, 0, 22, 70, SCROLL_SPEED, yPos);
    }

    public void update(float delta){

        frontGrass.update(delta);
        backGrass.update(delta);
        vine1.update(delta);
        vine2.update(delta);
        vine3.update(delta);

        if(vine1.isScrolledLeft()){
            vine1.reset(vine3.getTailX() + VINE_GAP);
        }
        else if(vine2.isScrolledLeft()){
            vine2.reset(vine1.getTailX() + VINE_GAP);
        }
        else if(vine3.isScrolledLeft()){
            vine3.reset(vine2.getTailX() + VINE_GAP);
        }

        if(frontGrass.isScrolledLeft()){
            frontGrass.reset(backGrass.getTailX());
        }
        else if(backGrass.isScrolledLeft()){
            backGrass.reset(frontGrass.getTailX());
        }

    }

    public Grass getFrontGrass(){
        return frontGrass;
    }

    public Grass getBackGrass(){
        return backGrass;
    }

    public Vine getVine1(){
        return vine1;
    }

    public Vine getVine2(){
        return vine2;
    }

    public Vine getVine3(){
        return vine3;
    }

    public boolean collides(Bird bird) {
        if (!vine1.isScored()
                && vine1.getX() + (vine1.getWidth() / 2) < bird.getX()
                + bird.getWidth()) {
            addScore(1);
            vine1.setScored(true);
            AssetLoader.coin.play();
        } else if (!vine2.isScored()
                && vine2.getX() + (vine2.getWidth() / 2) < bird.getX()
                + bird.getWidth()) {
            addScore(1);
            vine2.setScored(true);
            AssetLoader.coin.play();

        } else if (!vine3.isScored()
                && vine3.getX() + (vine3.getWidth() / 2) < bird.getX()
                + bird.getWidth()) {
            addScore(1);
            vine3.setScored(true);
            AssetLoader.coin.play();

        }

        return (vine1.collides(bird) || vine2.collides(bird) || vine3
                .collides(bird));
    }

    public boolean collides(Tarzan tarzan) {
        if (!vine1.isScored()
                && vine1.getX() + (vine1.getWidth() / 2) < tarzan.getX()
                + tarzan.getWidth()) {
            addScore(1);
            vine1.setScored(true);
            AssetLoader.coin.play();
        } else if (!vine2.isScored()
                && vine2.getX() + (vine2.getWidth() / 2) < tarzan.getX()
                + tarzan.getWidth()) {
            addScore(1);
            vine2.setScored(true);
            AssetLoader.coin.play();

        } else if (!vine3.isScored()
                && vine3.getX() + (vine3.getWidth() / 2) < tarzan.getX()
                + tarzan.getWidth()) {
            addScore(1);
            vine3.setScored(true);
            AssetLoader.coin.play();

        }

        return (vine1.collides(tarzan) || vine2.collides(tarzan) || vine3
                .collides(tarzan));
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

    public void stop() {
        frontGrass.stop();
        backGrass.stop();
        vine1.stop();
        vine2.stop();
        vine3.stop();

    }

    public void onRestart() {
        frontGrass.onRestart(0, SCROLL_SPEED);
        backGrass.onRestart(frontGrass.getTailX(), SCROLL_SPEED);
        vine1.onRestart(210, SCROLL_SPEED);
        vine2.onRestart(vine1.getTailX() + VINE_GAP, SCROLL_SPEED);
        vine3.onRestart(vine2.getTailX() + VINE_GAP, SCROLL_SPEED);
    }
}
