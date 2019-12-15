package com.spaceshooter.game.com.spaceshooter.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.spaceshooter.game.FirstGdxGame;
import com.spaceshooter.game.com.spaceshooter.game.background.backgroundMenu;

public class menuScene implements Screen {

    private FirstGdxGame game;

    private static Texture playButton;
    private static int playWidth = 600;
    private static int playHeight = 250;
    private static float playPosX = Gdx.graphics.getWidth()/2 - playWidth/2;
    private static float playPosY = Gdx.graphics.getHeight()/2 - playHeight/2-200;

    private static Texture exitButton;
    private static int exitWidth = 600;
    private static int exitHeight = 250;
    private static float exitPosX = Gdx.graphics.getWidth()/2 - exitWidth/2;
    private static float exitPosY = playPosY - 300 ;

    private static Texture stageButton;
    private static int stageWidth = 600;
    private static int stageHeight = 250;
    private static float stagePosX = Gdx.graphics.getWidth()/2 - stageWidth/2;
    private static float stagePosY = playPosY + 300;

    private Sound pressbutton;
    backgroundMenu bg;

    public menuScene(FirstGdxGame game){
        this.game = game;
        playButton = new Texture("startgame.png");
        exitButton = new Texture("exitgame.png");
        stageButton = new Texture("stage.png");
    }

    @Override
    public void show(){
        pressbutton = Gdx.audio.newSound(Gdx.files.internal("phaseJump3.mp3"));
        bg= new backgroundMenu();
        Gdx.input.setCatchBackKey(false);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        bg.render(game.batch);
        if(Gdx.input.isTouched()) {
            pressbutton.play();
            //Drawing buttons and Input React
            if (Gdx.input.getX() < playPosX + playWidth && Gdx.input.getX() > playPosX && Gdx.graphics.getHeight() - Gdx.input.getY() < playPosY + playHeight && Gdx.graphics.getHeight() - Gdx.input.getY() > playPosY) {

                    this.dispose();
                    game.batch.end();
                    game.setScreen(new SceneStage2(game));
                    return;

            }

            if (Gdx.input.getX() < stagePosX + stageWidth && Gdx.input.getX() > stagePosX && Gdx.graphics.getHeight() - Gdx.input.getY() < stagePosY + stageHeight && Gdx.graphics.getHeight() - Gdx.input.getY() > stagePosY) {

                    this.dispose();
                    game.batch.end();
                    game.setScreen(new StageMenu(game));
                    return;


            }

            if (Gdx.input.getX() < exitPosX + exitWidth && Gdx.input.getX() > exitPosX && Gdx.graphics.getHeight() - Gdx.input.getY() < exitPosY + exitHeight && Gdx.graphics.getHeight() - Gdx.input.getY() > exitPosY) {


                    this.dispose();
                    Gdx.app.exit();

            }
        }

        game.batch.draw(playButton, playPosX, playPosY, playWidth, playHeight);
        game.batch.draw(exitButton, exitPosX, exitPosY, exitWidth, exitHeight);
        game.batch.draw(stageButton, stagePosX, stagePosY, stageWidth, stageHeight);
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
