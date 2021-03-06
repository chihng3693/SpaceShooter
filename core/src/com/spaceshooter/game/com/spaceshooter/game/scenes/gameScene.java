package com.spaceshooter.game.com.spaceshooter.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.spaceshooter.game.FirstGdxGame;
import com.spaceshooter.game.com.spaceshooter.game.background.background;
import com.spaceshooter.game.com.spaceshooter.game.bullets.asteroids;
import com.spaceshooter.game.com.spaceshooter.game.bullets.bullet;
import com.spaceshooter.game.com.spaceshooter.game.bullets.enemyBullet;
import com.spaceshooter.game.com.spaceshooter.game.collision.collision;
import com.spaceshooter.game.com.spaceshooter.game.explosion.explosion;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.soap.Text;

public class gameScene implements Screen {
    private OrthographicCamera cam;
    private Vector3 pos;

    public Texture img;
    private float imgWidth = 0;
    private float imgHeight = 0;
    private float imgPosX = 0;
    private float imgPosY = 0;

    FirstGdxGame game;

    private float shootWait = 800;
    private float shootTimer;
    private float enemyTimer;

    ArrayList<bullet> bulletsFired;
    ArrayList<enemyBullet> enemyFired;

    private int stillTouching = 0;

    //Spawing asteroids
    private float asteroidSpawn;
    private float minSpawn = 1.0f;
    private float maxSpawn = 4.0f;
    Random random;
    ArrayList<asteroids> asteroidsAppeared;

    ArrayList<explosion> explosions;

    private float health = 1;
    Texture healthBar;

    collision playerRect;

    BitmapFont scoreFont;
    int score;

    background bg;

    public Sound playershoot;
    public Sound enemyshoot;
    public Sound minuslife;
    public Sound addscore;
    public Sound explode;
    public Music bgmusic;

    public gameScene(FirstGdxGame game){
        this.game = game;
        bulletsFired = new ArrayList<bullet>();

        random = new Random();
        asteroidSpawn = random.nextFloat () * (maxSpawn - minSpawn) + minSpawn;

        scoreFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        score = 0;
    }

    @Override
    public void show(){
        img = new Texture("shooter.png");

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        pos = new Vector3(Gdx.graphics.getWidth()/2, img.getHeight(), 0);

        bulletsFired = new ArrayList<bullet>();
        enemyFired = new ArrayList<enemyBullet>();

        asteroidsAppeared = new ArrayList<asteroids>();

        explosions = new ArrayList<explosion>();

        healthBar = new Texture("blank.png");

        imgWidth = img.getWidth();
        imgHeight = img.getHeight();

        playerRect = new collision(0, 0, imgWidth, imgHeight);

        bg = new background();

        playershoot = Gdx.audio.newSound(Gdx.files.internal("laser5.mp3"));
        enemyshoot = Gdx.audio.newSound(Gdx.files.internal("highDown.mp3"));
        minuslife = Gdx.audio.newSound(Gdx.files.internal("phaserDown1.mp3"));
        addscore = Gdx.audio.newSound(Gdx.files.internal("powerUp7.mp3"));
        explode = Gdx.audio.newSound(Gdx.files.internal("boom.mp3"));
        bgmusic = Gdx.audio.newMusic(Gdx.files.internal("bgm1.mp3"));

        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta){
        //play background music
        bgmusic.setVolume(0.5f);
        bgmusic.play();
        bgmusic.setLooping(true);

        shootTimer += 60;

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

        //Determine spaceship's position
        imgPosX = pos.x - imgWidth /2;
        imgPosY = pos.y - imgHeight /2;

        //Spawn asteroids
        asteroidSpawn -= Gdx.graphics.getDeltaTime();
        if(asteroidSpawn <= 0){
            asteroidSpawn = random.nextFloat () * (maxSpawn - minSpawn) + minSpawn;
            asteroidsAppeared.add(new asteroids(random.nextInt(Gdx.graphics.getWidth() - asteroids.textureWidth)));
        }

        //Remove asteroids
        ArrayList<asteroids> removeAsteroids = new ArrayList<asteroids>();
        for (asteroids Asteroids : asteroidsAppeared){
            Asteroids.update();
            if(Asteroids.remove){
                removeAsteroids.add(Asteroids);
            }
        }

        //Add bullet
        if(shootTimer >= shootWait) {
            shootTimer = 0;
            bulletsFired.add(new bullet(pos.x, pos.y));
            playershoot.play(0.8f);
        }

        //Add enemy bullet
        for(asteroids Asteroids : asteroidsAppeared){
            Asteroids.bulletTimer += 25;
            if(Asteroids.bulletTimer >= shootWait) {
                Asteroids.bulletTimer = 0;
                enemyFired.add(new enemyBullet(Asteroids.getX()+Asteroids.textureWidth/2, Asteroids.getY()));
                enemyshoot.play();
            }
        }

        //Player movement
        playerRect.move(imgPosX, imgPosY);

        //Remove bullet
        ArrayList<bullet> removeBullets = new ArrayList<bullet>();
        for (bullet Bullets : bulletsFired){
            Bullets.update();
            if(Bullets.remove){
                removeBullets.add(Bullets);
            }
        }

        //Remove enemy bullet
        ArrayList<enemyBullet> removeEnemyBullets = new ArrayList<enemyBullet>();
        for (enemyBullet EnemyBullets : enemyFired){
            EnemyBullets.update();
            if(EnemyBullets.remove){
                removeEnemyBullets.add(EnemyBullets);
            }
        }

        //Refresh explosion
        ArrayList<explosion> removeExplosion = new ArrayList<explosion>();
        for (explosion Explosions : explosions){
            Explosions.update();
            if(Explosions.remove){
                removeExplosion.add(Explosions);
            }
        }
        explosions.removeAll(removeExplosion);

        //Collision
        for(bullet Bullets : bulletsFired){
            for(asteroids Asteroids : asteroidsAppeared){
                if(Bullets.getcollision().collideReact(Asteroids.getcollision())){
                    removeBullets.add(Bullets);
                    removeAsteroids.add(Asteroids);
                    explosions.add(new explosion(Asteroids.getX(), Asteroids.getY()));
                    explode.play(0.3f);
                    score += 100;
                }
            }
        }
        bulletsFired.removeAll(removeBullets);


        //Collide with player
        for (asteroids Asteroids : asteroidsAppeared){
            if (Asteroids.getcollision().collideReact(playerRect)) {
                removeAsteroids.add(Asteroids);
                health -= 0.1;
                minuslife.play();
            }
        }
        asteroidsAppeared.removeAll(removeAsteroids);

        //Bullet hits player
        for (enemyBullet Enemies : enemyFired){
            if(Enemies.getcollision().collideReact(playerRect)){
                removeEnemyBullets.add(Enemies);
                health -= 0.1;
                minuslife.play();
            }
        }
        enemyFired.removeAll(removeEnemyBullets);

        //When player died
        if(health <= 0){
            bgmusic.stop();
            this.dispose();
            game.setScreen(new gameOverScene(game, score));
            return;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Draw stars
        bg.render(game.batch);

        //Draw score
        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "" + score);
        scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth()/2 - scoreLayout.width/2, Gdx.graphics.getHeight() - scoreLayout.height - 10);

        //Draw bullet
        for(bullet Bullets : bulletsFired){
            Bullets.render(game.batch);
        }

        game.batch.draw(img, imgPosX, imgPosY, 270, 210);

        //Draw enemy bullet
        for(enemyBullet Enemies : enemyFired){
            Enemies.render(game.batch);
        }

        //Draw asteroids
        for(asteroids Asteroids : asteroidsAppeared){
            Asteroids.render(game.batch);
        }

        //Draw explosion
        for(explosion Explosions : explosions){
            Explosions.render(game.batch);
        }

        //Draw health
        if(health > 0.6f){
            game.batch.setColor(Color.GREEN);
        } else if (health > 0.2f){
            game.batch.setColor(Color.ORANGE);
        } else {
            game.batch.setColor(Color.RED);
        }
        game.batch.draw(healthBar, 0, 0, Gdx.graphics.getWidth() * health, 50);
        game.batch.setColor((Color.WHITE));

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
        bgmusic.stop();
    }

    @Override
    public void resume(){
        bgmusic.play();
    }

    @Override
    public void hide(){
        bgmusic.stop();
    }

    @Override
    public void dispose(){
        bgmusic.dispose();
    }
}
