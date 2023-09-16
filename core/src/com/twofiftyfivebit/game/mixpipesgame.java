package com.twofiftyfivebit.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.twofiftyfivebit.game.screens.GameScreen;

public class mixpipesgame extends Game
{
	GameScreen gameScreen;

	AssetManager assetManager;

	@Override
	public void create()
	{
		assetManager = new AssetManager();
		assetManager.load("Square.png", Texture.class);
		assetManager.finishLoading();

		gameScreen = new GameScreen(assetManager);
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

		assetManager.dispose();

		gameScreen.dispose();
	}

}
