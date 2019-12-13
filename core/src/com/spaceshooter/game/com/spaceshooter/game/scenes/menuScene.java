package com.spaceshooter.game.com.spaceshooter.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.spaceshooter.game.FirstGdxGame;
import com.spaceshooter.game.com.spaceshooter.game.background.background;
import com.spaceshooter.game.com.spaceshooter.game.bullets.asteroids;
import com.spaceshooter.game.com.spaceshooter.game.bullets.bullet;
import com.spaceshooter.game.com.spaceshooter.game.bullets.enemyBullet;
import com.spaceshooter.game.com.spaceshooter.game.explosion.explosion;

public class menuScene implements Screen {

    private FirstGdxGame game;

    private static Texture playButton;
    private static int playWidth = 600;
    private static int playHeight = 250;
    private static float playPosX = Gdx.graphics.getWidth()/2 - playWidth/2;
    private static float playPosY = Gdx.graphics.getHeight()/2 - playHeight/2;

    private static Texture exitButton;
    private static int exitWidth = 600;
    private static int exitHeight = 250;
    private static float exitPosX = Gdx.graphics.getWidth()/2 - exitWidth/2;
    private static float exitPosY = playPosY - 300 - exitHeight/2;

    private Sound pressbutton;

    public menuScene(FirstGdxGame game){
        this.game = game;
        playButton = new Texture("playButton.png");
        exitButton = new Texture("exitButton.png");
    }

    @Override
    public void show(){
        pressbutton = Gdx.audio.newSound(Gdx.files.internal("phaseJump3.mp3"));

        Gdx.input.setCatchBackKey(false);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        //Drawing buttons and Input React
        if(Gdx.input.getX() < playPosX + playWidth && Gdx.input.getX() > playPosX && Gdx.graphics.getHeight() - Gdx.input.getY() < playPosY + playHeight && Gdx.graphics.getHeight() - Gdx.input.getY() > playPosY){
            if(Gdx.input.isTouched()){
                pressbutton.play();
                game.setScreen(new gameScene(game));
            }
        }

        if(Gdx.input.getX() < exitPosX + exitWidth && Gdx.input.getX() > exitPosX && Gdx.graphics.getHeight() - Gdx.input.getY() < exitPosY + exitHeight && Gdx.graphics.getHeight() - Gdx.input.getY() > exitPosY){
            if(Gdx.input.isTouched()){
                pressbutton.play();
                this.dispose();
                Gdx.app.exit();
            }
        }

        game.batch.draw(playButton, playPosX, playPosY, playWidth, playHeight);
        game.batch.draw(exitButton, exitPosX, exitPosY, exitWidth, exitHeight);
        game.batch.end();

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
    public void hide() {

    }

    @Override
    public void dispose(){

    }

}
