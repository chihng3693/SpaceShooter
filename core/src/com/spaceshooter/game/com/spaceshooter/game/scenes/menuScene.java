package com.spaceshooter.game.com.spaceshooter.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.spaceshooter.game.FirstGdxGame;

public class menuScene implements Screen {

    private FirstGdxGame game;

    private static Texture playButton;
    private static int playWidth = 800;
    private static int playHeight = 500;
    private static int playPosX = Gdx.graphics.getWidth()/2 - playWidth/2;
    private static int playPosY = Gdx.graphics.getHeight()/2 - playHeight/2;

    public menuScene(FirstGdxGame game){
        this.game = game;
        playButton = new Texture("PixelArt.png");
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(playButton,playPosX, playPosY, playWidth, playHeight);
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
