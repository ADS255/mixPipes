package com.twofiftyfivebit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class mixpipesgame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img1;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img1 = new Texture(Gdx.files.internal("Square.png"));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img1, 50, 50);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img1.dispose();
	}
}
