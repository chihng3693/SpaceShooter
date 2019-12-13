package com.spaceshooter.game.com.spaceshooter.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceshooter.game.com.spaceshooter.game.collision.collision;

public class enemyBullet {
    public static final int speed = 20;
    public static Texture bulletTexture;
    public int enemyTimer;


    private float x,y;
    private int width = 100;
    private int height = 300;
    public boolean remove = false;

    collision rect;

    public enemyBullet(float x, float y){
        this.x = x;
        this.y = y;
        this.enemyTimer = 0;

        if(bulletTexture == null){
            bulletTexture = new Texture("bullet.png");
        }

        this.width = bulletTexture.getWidth();
        this.height = bulletTexture.getHeight();

        this.rect = new collision(x, y, width, height);
    }

    public void update(){
        y -= speed;
        if(y > Gdx.graphics.getHeight()){
            remove = true;
        }
        rect.move(x, y);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public collision getcollision(){
        return rect;
    }

    public void render(SpriteBatch batch){
        batch.draw(bulletTexture, x, y);
    }
}
