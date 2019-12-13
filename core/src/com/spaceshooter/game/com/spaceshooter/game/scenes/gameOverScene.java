package com.spaceshooter.game.com.spaceshooter.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.spaceshooter.game.FirstGdxGame;

public class gameOverScene implements Screen{
    FirstGdxGame game;
    int score, highScore, musicflag=0;

    private static int bannerWidth = 800;
    private static int bannerHeight = 150;
    private boolean flag=false;

    Texture gameOverBanner;
    BitmapFont scoreFont;
    private Music gameover;
    private Sound pressbutton;

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
        gameover = Gdx.audio.newMusic(Gdx.files.internal("gameOver.mp3"));
        pressbutton = Gdx.audio.newSound(Gdx.files.internal("phaseJump3.mp3"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!gameover.isPlaying() && musicflag == 0) {
            gameover.setVolume(0.8f);
            gameover.play();
            musicflag = 1;
        }

        game.batch.begin();
        //Draw game over
        game.batch.draw(gameOverBanner, Gdx.graphics.getWidth() / 2 - bannerWidth / 2, Gdx.graphics.getHeight() - bannerHeight - 300, bannerWidth, bannerHeight);

        //Draw score
        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score\n" + score, Color.WHITE, 0, Align.left, false);
        GlyphLayout highScoreLayout = new GlyphLayout(scoreFont, "High Score\n" + highScore, Color.WHITE, 0, Align.left, false);
        scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth() / 2 - scoreLayout.width / 2, Gdx.graphics.getHeight() - bannerHeight - 500);
        scoreFont.draw(game.batch, highScoreLayout, Gdx.graphics.getWidth() / 2 - highScoreLayout.width / 2, Gdx.graphics.getHeight() - bannerHeight - 700);

        GlyphLayout tryAgainLayout = new GlyphLayout(scoreFont, "Try Again");
        GlyphLayout mainMenuLayout = new GlyphLayout(scoreFont, "Main Menu");

        float tryX = Gdx.graphics.getWidth() / 2 - tryAgainLayout.width / 2;
        float tryY = Gdx.graphics.getHeight() / 2 - tryAgainLayout.width / 2 - 200;
        float menuX = Gdx.graphics.getWidth() / 2 - mainMenuLayout.width / 2;
        float menuY = tryY - 200;

        float touchX = Gdx.input.getX();
        float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

        if (Gdx.input.isTouched()) {
            pressbutton.play();
            //Press Try Again
            if (touchX < tryX + tryAgainLayout.width && touchX > tryX && touchY < tryY && touchY > tryY - tryAgainLayout.height) {
                this.dispose();
                game.batch.end();
                game.setScreen(new gameScene(game));
                return;
            }

            //Press Main Menu
            if (touchX < menuX + mainMenuLayout.width && touchX > menuX && touchY < menuY && touchY > menuY - mainMenuLayout.height) {
                this.dispose();
                game.batch.end();
                game.setScreen(new menuScene(game));
                return;
            }
        }

        //Draw buttons
        scoreFont.draw(game.batch, tryAgainLayout, tryX, tryY);
        scoreFont.draw(game.batch, mainMenuLayout, menuX, menuY);

        game.batch.end();
    }

    @Override
    public void resize(int width,int height){

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
