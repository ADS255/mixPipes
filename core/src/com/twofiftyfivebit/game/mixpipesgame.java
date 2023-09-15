package com.twofiftyfivebit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class mixpipesgame extends Game
{
	GameScreen gameScreen;

	@Override
	public void create()
	{
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void render()
	{
		super.render();
	}

	@Override
	public void dispose()
	{
		super.dispose();
		gameScreen.dispose();
	}

}
