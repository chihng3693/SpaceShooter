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

    bullet bulletsFired;

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
        bulletsFired = new bullet(bulletPos, new Vector2(0,100));

        imgWidth = img.getWidth();
        imgHeight = img.getHeight();

        Gdx.input.setCatchBackKey(true);
    }

    public void update(){

    }

    @Override
    public void render(float delta){
        bulletsFired.update();

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
        } else {
            stillTouching = 0;
        }

        //Add bullet
        if(stillTouching == 1){
           // bulletsFired.add(new bullet(pos.x, pos.y));
        }


        imgPosX = pos.x - imgWidth /2;
        imgPosY = pos.y - imgHeight /2;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(img, imgPosX, imgPosY);

        //Draw bullet
        game.batch.draw(bullet,bulletsFired.bulletLocation.x,bulletsFired.bulletLocation.y);

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
