package com.spaceshooter.game.com.spaceshooter.game.explosion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class explosion {

    public static float frameLength = 0.4f;
    public static int offset = 8;
    public static int size = 400;

    public static Animation animate = null;
    private float x, y;
    float stateTime;

    public boolean remove = false;

    public explosion(float x, float y){
        this.x = x - offset;
        this.y = y - offset;
        stateTime = 0;

        if(animate == null){
            animate = new Animation(frameLength, TextureRegion.split(new Texture("explosion.png"), 100, 100)[0]);

        }
    }

    public void update(){
        stateTime += Gdx.graphics.getDeltaTime() * 50;
        if(animate.isAnimationFinished((stateTime))){
            remove = true;
        }
    }

    public void render (SpriteBatch batch){
        batch.draw((TextureRegion) animate.getKeyFrame(stateTime), x, y);
    }


}
