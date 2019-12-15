package com.spaceshooter.game.com.spaceshooter.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.spaceshooter.game.FirstGdxGame;

public class gameOverScene implements Screen{
    FirstGdxGame game;
    int score, highScore;

    private static int bannerWidth = 800;
    private static int bannerHeight = 150;

    Texture gameOverBanner;
    BitmapFont scoreFont;

    public gameOverScene (FirstGdxGame game, int score){
        this.game = game;
        this.score = score;

        //Get high score
        Preferences prefs = Gdx.app.getPreferences("spaceshooter");
        this.highScore = prefs.getInteger("highscore", 0);

        //Check score
        if (score > highScore){
            prefs.putInteger("highscore", score);
            prefs.flush();
        }

        //Load texture
        gameOverBanner = new Texture("gameOver.png");
        scoreFont = new BitmapFont(Gdx.files.internal("font.fnt"));
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.batch.draw(gameOverBanner, Gdx.graphics.getWidth()/2 - bannerWidth/2, Gdx.graphics.getHeight() - bannerHeight - 100, bannerWidth, bannerHeight);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score\n" + score, Color.WHITE, 0 , Align.left, false);
        GlyphLayout highScoreLayout = new GlyphLayout(scoreFont, "High Score\n" + highScore, Color.WHITE, 0 , Align.left, false);
        scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth()/2 - scoreLayout.width/2, Gdx.graphics.getHeight() - bannerHeight - 100 * 2);
        scoreFont.draw(game.batch, highScoreLayout, Gdx.graphics.getWidth()/2 - highScoreLayout.width/2, Gdx.graphics.getHeight() - bannerHeight - 100 * 4);

        game.batch.end();
    }

    @Override
    public void resize (int width, int height){

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void dispose(){

    }

    @Override
    public void hide(){

    }

}
