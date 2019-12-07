package com.spaceshooter.game.com.spaceshooter.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceshooter.game.com.spaceshooter.game.collision.collision;

public class asteroids {
    public static final int speed = 5;
    private static Texture asteroidTexture;
    public static int textureWidth = 200;
    public static int textureHeight = 300;

    collision rect;
    private int width;
    private int height;

    private float x,y;
    public boolean remove = false;

    public asteroids(float x){
        this.x = x;
        this.y = Gdx.graphics.getHeight();

        if( asteroidTexture == null){
            asteroidTexture = new Texture("bullet.png");
        }

        this.width = asteroidTexture.getWidth();
        this.height = asteroidTexture.getHeight();

        this.rect = new collision(x, y, 50, 100);
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
        batch.draw(asteroidTexture, x, y, 50, 100);
    }

}
