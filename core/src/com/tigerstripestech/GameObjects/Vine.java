package com.tigerstripestech.GameObjects;


import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by Josh on 7/20/2014.
 */
public class Vine extends Scrollable {
    private Random r;

    private Rectangle vine;

    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;
    private float groundY;
    private boolean isScored = false;

    public Vine(float x, float y, int width, int height, float scrollSpeed, float groundY){
        super(x,y,width,height,scrollSpeed);
        r = new Random();
        vine = new Rectangle();

        this.groundY = groundY;

    }

    @Override
    public void update(float delta) {
        super.update(delta);

        // The set() method allows you to set the top left corner's x, y
        // coordinates,
        // along with the width and height of the rectangle

        vine.set(position.x, position.y, width, height);
    }

    @Override
    public void reset(float newX){
        super.reset(newX);
        height = r.nextInt(90) + 15;
        isScored = false;
    }

    public Rectangle getVine(){
        return vine;
    }

    public boolean collides(Bird bird) {
        if(position.x < bird.getX() + bird.getWidth()) {
            return(Intersector.overlaps(bird.getBoundingCircle(), vine));
        }
        return false;
    }

    public boolean collides(Tarzan tarzan) {
        if(position.x < tarzan.getX() + tarzan.getWidth()) {
            return(Intersector.overlaps(tarzan.getBoundingCircle(), vine));
        }
        return false;
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean score) {
        isScored = score;
    }

    public void onRestart(float x, int scrollSpeed) {
        velocity.x = scrollSpeed;
        reset(x);
    }
}
