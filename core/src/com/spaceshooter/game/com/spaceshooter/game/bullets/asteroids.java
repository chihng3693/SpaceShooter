package com.spaceshooter.game.com.spaceshooter.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceshooter.game.com.spaceshooter.game.collision.collision;

public class asteroids {
    public static final int speed = 10;
    public static final int bulletSpeed = 50;
    public static Texture asteroidTexture;
    public static int textureWidth = 200;
    public static int textureHeight = 200;

    public int bulletTimer;

    collision rect;
    private int width;
    private int height;

    private float x,y;
    public boolean remove = false;

    public asteroids(float x){
        this.x = x;
        this.y = Gdx.graphics.getHeight();
        this.bulletTimer = 0;

        if( asteroidTexture == null){
            asteroidTexture = new Texture("spaceship.png");
        }

        this.width = asteroidTexture.getWidth();
        this.height = asteroidTexture.getHeight();

        this.rect = new collision(x, y, 200, 200);
    }

    public void update(){
        y -= speed;
        if(y < -textureHeight){
            remove = true;
        }
        rect.move(x, y);
    }

    public collision getcollision(){
        return rect;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void render(SpriteBatch batch){
        batch.draw(asteroidTexture, x, y, 200, 200);
    }

}
