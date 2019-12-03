package com.spaceshooter.game.com.spaceshooter.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class bullet {
    public static final int speed = 800;
    private static Texture bullet;

    float x, y;

    public boolean remove = false;

    public bullet(float x, float y){
        this.x = x;
        this.y = y;

        if(bullet == null){
            bullet = new Texture("bullet.png");

        }
    }

    public void update (float deltaTime){
        y += speed * deltaTime;

        if(y > Gdx.graphics.getHeight()){
            remove = true;
        }
    }

    public void render (SpriteBatch batch){
        batch.draw(bullet, x, y);
    }
}
