package com.spaceshooter.game.com.spaceshooter.game.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public class background {
    public Texture cosmic;
    public Texture star;
    public int starnum = 80;
    public float[] xs;
    public float[] ys;
    public int[] st;
    public float[] brightness;
    public float[] speed;

    public background() {
        star = new Texture("star.png");
        cosmic = new Texture("background.jpg");
        xs = new float[starnum];
        ys = new float[starnum];
        st = new int[starnum];
        brightness = new float[starnum];
        speed = new float[starnum];

        Random random = new Random();

        for(int i=0; i<starnum; i++) {
            xs[i] = random.nextInt(Gdx.graphics.getWidth());
            ys[i] = random.nextInt(Gdx.graphics.getHeight());
            st[i] = random.nextInt(3);
            brightness[i] = random.nextFloat();
            speed[i] = random.nextFloat();
        }
    }

    public void render(SpriteBatch batch) {
        batch.setColor(1.0f,1.0f,1.0f,0.5f);
        batch.draw(cosmic,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        for(int i=0; i<starnum; i++) {
            if (st[i] == 0) {
                batch.draw(star, xs[i], ys[i], 30, 30);
                ys[i] = ys[i] - (speed[i]+0.1f)*2;
            }
            if (st[i] == 1) {
                batch.draw(star, xs[i], ys[i], 20, 20);
                ys[i] = ys[i] - (speed[i] + 0.1f)*3;
            }
            if (st[i] == 2) {
                batch.draw(star, xs[i], ys[i], 10, 10);
                ys[i] = ys[i] - (speed[i] + 0.1f)*4;
            }
            batch.setColor(1.0f,1.0f,1.0f,brightness[i]+0.2f);
            if (ys[i]<0) {
                Random random = new Random();
                ys[i] = Gdx.graphics.getHeight() + 30;
                xs[i] = random.nextInt(Gdx.graphics.getWidth());
                st[i] = random.nextInt(3);
                brightness[i] = random.nextFloat();
            }
        }
        batch.setColor(1.0f,1.0f,1.0f,1.0f);

    }
}
