package com.spaceshooter.game.com.spaceshooter.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class bullet {
    public static final int speed = 500;
    private static Texture bullet;

    public static Vector2 bulletLocation = new Vector2(0,0);
    public static Vector2 bulletSpeed = new Vector2(0,0);

    public static boolean remove = false;

    public bullet(Vector2 launchLocation, Vector2 launchSpeed){
        bulletLocation = new Vector2(launchLocation.x, launchLocation.y);
        bulletSpeed = new Vector2(launchSpeed.x, launchSpeed.y);
    }

    public static void update(){
        bulletLocation.x += bulletSpeed.x;
        bulletLocation.y += bulletSpeed.y;
    }

    public static void render (SpriteBatch batch){
        batch.draw(bullet, bulletLocation.x, bulletLocation.y);
    }
}
