package com.spaceshooter.game.com.spaceshooter.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.spaceshooter.game.FirstGdxGame;

public class gameScene implements Screen {
    private OrthographicCamera cam;
    private Vector3 pos;
    Texture img;
    int iw = 0;
    int ih = 0;
    FirstGdxGame game;

    public gameScene(FirstGdxGame game){
        this.game = game;
    }

    @Override
    public void show(){
        img = new Texture("badlogic.jpg");

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        pos = new Vector3(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);

        iw = img.getWidth();
        ih = img.getHeight();
    }

    @Override
    public void render(float delta){
        //Touching
        if(Gdx.input.isTouched()){
            pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(pos);
        }

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(img, pos.x - (iw/2), pos.y - (ih/2));
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
    public void hide(){

    }

    @Override
    public void dispose(){

    }
}
