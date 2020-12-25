package com.spaceshooter.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceshooter.game.com.spaceshooter.game.scenes.gameOverScene;
import com.spaceshooter.game.com.spaceshooter.game.scenes.gameScene;
import com.spaceshooter.game.com.spaceshooter.game.scenes.menuScene;

public class FirstGdxGame extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		// this.setScreen(new menuScene(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
