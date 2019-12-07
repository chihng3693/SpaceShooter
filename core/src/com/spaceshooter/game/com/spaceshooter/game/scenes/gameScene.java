package com.spaceshooter.game.com.spaceshooter.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.spaceshooter.game.FirstGdxGame;
import com.spaceshooter.game.com.spaceshooter.game.bullets.bullet;

import java.util.ArrayList;

import javax.xml.soap.Text;

public class gameScene implements Screen {
    private OrthographicCamera cam;
    private Vector3 pos;

    private Texture bullet;
    private Vector2 bulletPos;

    private Texture img;
    private float imgWidth = 0;
    private float imgHeight = 0;
    private float imgPosX = 0;
    private float imgPosY = 0;

    FirstGdxGame game;

    private float shootWait = 250;
    private float shootTimer;

    private boolean bulletTrigger = false;

    ArrayList<bullet> bulletFired;

    private int stillTouching = 0;

    public gameScene(FirstGdxGame game){
        this.game = game;

    }

    @Override
    public void show(){
        img = new Texture("badlogic.jpg");
        bullet = new Texture("bullet.png");

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        pos = new Vector3(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);

        bulletPos = new Vector2(pos.x,pos.y);
        bulletFired  = new ArrayList<bullet>();

        imgWidth = img.getWidth();
        imgHeight = img.getHeight();

        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta){
        shootTimer += 10;

        //Touching
        if(Gdx.input.isTouched()){
            if(Gdx.input.getX() < imgPosX + imgWidth && Gdx.input.getX() > imgPosX && Gdx.graphics.getHeight() - Gdx.input.getY() < imgPosY + imgHeight && Gdx.graphics.getHeight() - Gdx.input.getY() > imgPosY){
                pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                cam.unproject(pos);
                stillTouching = 1;
            } else if (stillTouching == 1){
                pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                cam.unproject(pos);

            }
        } else if (!Gdx.input.isTouched()){
            stillTouching = 0;
            bulletTrigger = false;
        }

        //Add bullet
        if(shootTimer >= shootWait){
            shootTimer = 0;
            bulletPos.x = pos.x;
            bulletPos.y = pos.y;
            bullet bulletShot = new bullet(bulletPos, new Vector2(0,30));
            bulletFired.add(bulletShot);
        }

        imgPosX = pos.x - imgWidth /2;
        imgPosY = pos.y - imgHeight /2;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(img, imgPosX, imgPosY);

        //Draw bullet
        int bulletCount = 0;
        while (bulletCount < bulletFired.size()){
            bullet currentBullet = bulletFired.get(bulletCount);
            currentBullet.update();

            //Remove bullet
            if(currentBullet.bulletLocation.x > 0 && currentBullet.bulletLocation.x < Gdx.graphics.getWidth() && currentBullet.bulletLocation.y > 0 && currentBullet.bulletLocation.y < Gdx.graphics.getHeight()){
                game.batch.draw(bullet, currentBullet.bulletLocation.x, currentBullet.bulletLocation.y);
            } else {
                bulletFired.remove(bulletCount);
                /*if(bulletFired.size() > 0){
                    bulletCount--;
                }*/
            }
            bulletCount++;
        }

        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            // change screen to MainMenu
            game.setScreen(new menuScene(game));
        }



    }

    @Override
    public void resize(int width, int height){

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void hide(){

    }

    @Override
    public void dispose(){

    }
}
