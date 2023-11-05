package com.twofiftyfivebit.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.twofiftyfivebit.game.data.GameDataModel;
import com.twofiftyfivebit.game.data.LevelData;
import com.twofiftyfivebit.game.graphics.TextureHandler;
import com.twofiftyfivebit.game.screenmanagement.IlevelStateListener;
import com.twofiftyfivebit.game.screens.GameScreen;
import com.twofiftyfivebit.game.tweening.TweenManager;
import com.twofiftyfivebit.game.utilities.Serialiser;

public class mixpipesgame extends Game implements IlevelStateListener
{
	AssetManager assetManager;
	TextureHandler textureHandler;
	TweenManager tweenManager;

	GameScreen gameScreen;

	@Override
	public void create()
	{
		assetManager = new AssetManager();

		textureHandler = new TextureHandler(assetManager);

		tweenManager = new TweenManager();

		loadLevel(0);
	}

	private void loadLevel(int index)
	{
		LevelData levelData = Serialiser.loadLevelData(index);
		GameDataModel dataModel = new GameDataModel(levelData,this);

		gameScreen = new GameScreen(dataModel);
		setScreen(gameScreen);
	}

	@Override
	public void render()
	{
		super.render();
		tweenManager.update();
	}

	@Override
	public void dispose()
	{
		super.dispose();

		textureHandler = null;

		assetManager.dispose();
		gameScreen.dispose();
	}


	@Override
	public void onLevelEnter() {}
	@Override
	public void onLevelComplete() {}
	@Override
	public void onLevelExit(int currentLevelId)
	{
		gameScreen.dispose();
		loadLevel(currentLevelId+1);
	}
}
