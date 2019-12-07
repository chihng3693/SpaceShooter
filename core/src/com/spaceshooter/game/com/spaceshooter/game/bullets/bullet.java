package com.spaceshooter.game.com.spaceshooter.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class bullet {
    public static final int speed = 20;
    private static Texture bulletTexture;

    float x,y;
    public boolean remove = false;

    public bullet(float x, float y){
        this.x = x;
        this.y = y;

        if(bulletTexture == null){
            bulletTexture = new Texture("bullet.png");
        }
    }

    public void update(){
        y += speed;
        if(y > Gdx.graphics.getHeight()){
            remove = true;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(bulletTexture, x, y);
    }
}
