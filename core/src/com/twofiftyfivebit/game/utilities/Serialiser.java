package com.twofiftyfivebit.game.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.twofiftyfivebit.game.data.LevelData;

public class Serialiser
{
    private static Json json = new Json();

    public static LevelData loadLevelData(int levelIndex)
    {
        String path = "levels/" + levelIndex + ".json";
        FileHandle fileHandle = Gdx.files.internal(path);

        LevelData levelData;

        if (fileHandle.exists())
        {
            levelData = json.fromJson(LevelData.class, fileHandle);
        } else
        {
            return null;
        }
        return levelData;
    }
}
