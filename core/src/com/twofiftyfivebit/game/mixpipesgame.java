package com.twofiftyfivebit.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.twofiftyfivebit.game.data.GameDataModel;
import com.twofiftyfivebit.game.data.LevelData;
import com.twofiftyfivebit.game.data.Tile;
import com.twofiftyfivebit.game.screens.GameScreen;
import com.twofiftyfivebit.game.utilities.Serialiser;

import java.io.FileWriter;

public class mixpipesgame extends Game
{
	AssetManager assetManager;

	GameScreen gameScreen;

	@Override
	public void create()
	{
		assetManager = new AssetManager();
		assetManager.load("0.png", Texture.class);
		assetManager.load("1.png", Texture.class);
		assetManager.load("2.png", Texture.class);
		assetManager.load("3.png", Texture.class);
		assetManager.finishLoading();

		LevelData levelData = Serialiser.loadLevelData(0);
		GameDataModel dataModel = new GameDataModel(levelData);

		gameScreen = new GameScreen(assetManager,dataModel);
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
