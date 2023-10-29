package com.twofiftyfivebit.game.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class TextureHandler
{
    private AssetManager assetManager;

    public static TextureHandler instance;

    private String[] tileTextureFiles = {
            "0.png",
            "1.png",
            "2.png",
            "3.png",
            "4.png",
            "5.png",
            "tileIndicator.png",
            "unfilled_0.png",
            "unfilled_1.png",
            "unfilled_2.png"
    };

    private Texture[] tileTextures;

    public TextureHandler(AssetManager assetManager)
    {
        if (instance != null){
            System.out.println("Error: multiple instance of Texture handler exist");
        }
        instance = this;

        this.assetManager = assetManager;

        int tileTextureCount = tileTextureFiles.length;

        this.tileTextures = new Texture[tileTextureCount];

        for (int i = 0; i < tileTextureCount; i++)
        {
            assetManager.load(tileTextureFiles[i], Texture.class);
            assetManager.finishLoading();
            tileTextures[i] = assetManager.get(tileTextureFiles[i], Texture.class);
            tileTextures[i].setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        assetManager.finishLoading();
    }

    public Texture[] getTileTextures()
    {
        return tileTextures;
    }
}
