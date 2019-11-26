package com.spaceshooter.game.com.spaceshooter.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.spaceshooter.game.FirstGdxGame;

public class menuScene implements Screen {

    FirstGdxGame game;

    Texture playButton;
    int playWidth = 0;

    public menuScene(FirstGdxGame game){
        this.game = game;
        playButton = new Texture("PixelArt.png");
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(playButton,Gdx.graphics.getWidth()/2 - 400,Gdx.graphics.getHeight()/2 - 250, 800, 500);
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
