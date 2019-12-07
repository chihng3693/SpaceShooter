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
import com.spaceshooter.game.com.spaceshooter.game.bullets.asteroids;
import com.spaceshooter.game.com.spaceshooter.game.bullets.bullet;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.soap.Text;

public class gameScene implements Screen {
    private OrthographicCamera cam;
    private Vector3 pos;

    private Texture img;
    private float imgWidth = 0;
    private float imgHeight = 0;
    private float imgPosX = 0;
    private float imgPosY = 0;

    FirstGdxGame game;

    private float shootWait = 500;
    private float shootTimer;

    ArrayList<bullet> bulletsFired;

    private int stillTouching = 0;

    private float asteroidSpawn;
    private float minSpawn = 0.6f;
    private float maxSpawn = 2.0f;
    Random random;
    ArrayList<asteroids> asteroidsAppeared;

    public gameScene(FirstGdxGame game){
        this.game = game;
        bulletsFired = new ArrayList<bullet>();

        random = new Random();
        asteroidSpawn = random.nextFloat () * (maxSpawn - minSpawn) + minSpawn;
    }

    @Override
    public void show(){
        img = new Texture("badlogic.jpg");

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        pos = new Vector3(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);

        bulletsFired  = new ArrayList<bullet>();

        asteroidsAppeared = new ArrayList<asteroids>();

        imgWidth = img.getWidth();
        imgHeight = img.getHeight();

        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta){
        shootTimer += 80;

        //Spawn asteroids
        asteroidSpawn -= Gdx.graphics.getDeltaTime();
        if(asteroidSpawn <= 0){
            asteroidSpawn = random.nextFloat () * (maxSpawn - minSpawn) + minSpawn;
            asteroidsAppeared.add(new asteroids(random.nextInt(Gdx.graphics.getWidth())));
        }

        //Remove asteroids
        ArrayList<asteroids> removeAsteroids = new ArrayList<asteroids>();
        for (asteroids Asteroids : asteroidsAppeared){
            Asteroids.update();
            if(Asteroids.remove){
                removeAsteroids.add(Asteroids);
            }
        }

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
        if(shootTimer >= shootWait) {
            shootTimer = 0;
            bulletsFired.add(new bullet(pos.x, pos.y));
        }


        //Remove bullet
        ArrayList<bullet> removeBullets = new ArrayList<bullet>();
        for (bullet Bullets : bulletsFired){
            Bullets.update();
            if(Bullets.remove){
                removeBullets.add(Bullets);
            }
        }


        imgPosX = pos.x - imgWidth /2;
        imgPosY = pos.y - imgHeight /2;

        //Collision
        for(bullet Bullets : bulletsFired){
            for(asteroids Asteroids : asteroidsAppeared){
                if(Bullets.getcollision().collideReact(Asteroids.getcollision())){
                    removeBullets.add(Bullets);
                    removeAsteroids.add(Asteroids);
                }
            }
        }
        asteroidsAppeared.removeAll(removeAsteroids);
        bulletsFired.removeAll(removeBullets);



        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(img, imgPosX, imgPosY);

        //Draw bullet
        for(bullet Bullets : bulletsFired){
            Bullets.render(game.batch);
        }

        //Draw asteroids
        for(asteroids Asteroids : asteroidsAppeared){
            Asteroids.render(game.batch);
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
