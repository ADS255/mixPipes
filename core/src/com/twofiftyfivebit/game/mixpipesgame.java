package com.twofiftyfivebit.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.twofiftyfivebit.game.data.GameDataModel;
import com.twofiftyfivebit.game.data.LevelData;
import com.twofiftyfivebit.game.graphics.TextureHandler;
import com.twofiftyfivebit.game.screens.GameScreen;
import com.twofiftyfivebit.game.utilities.Serialiser;

public class mixpipesgame extends Game
{
	AssetManager assetManager;
	TextureHandler textureHandler;

	GameScreen gameScreen;

	@Override
	public void create()
	{
		assetManager = new AssetManager();

		textureHandler = new TextureHandler(assetManager);

		LevelData levelData = Serialiser.loadLevelData(0);
		GameDataModel dataModel = new GameDataModel(levelData);

		gameScreen = new GameScreen(dataModel);
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

		textureHandler = null;

		assetManager.dispose();
		gameScreen.dispose();
	}

}
