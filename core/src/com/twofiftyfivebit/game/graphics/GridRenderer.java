package com.twofiftyfivebit.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.twofiftyfivebit.game.data.GameDataModel;
import com.twofiftyfivebit.game.data.LevelData;
import com.twofiftyfivebit.game.data.Tile;
import com.twofiftyfivebit.game.data.TileInfo;
import com.twofiftyfivebit.game.screenmanagement.IlevelStateListener;
import com.twofiftyfivebit.game.tweening.TweenManager;
import com.twofiftyfivebit.game.tweening.tweens.FadeTween;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GridRenderer implements IlevelStateListener
{
    GameDataModel dataModel;
    Texture[] tileTextures;
    private Sprite[] sprites;

    private Sprite[] background;

    private int width;
    private int height;

    private Color[] colors;

    public GridRenderer(GameDataModel dataModel)
    {
        this.dataModel = dataModel;
        this.tileTextures = TextureHandler.instance.getTileTextures();

        this.width = dataModel.getWidth();
        this.height = dataModel.getHeight();

        sprites = new Sprite[width * height];
        background = new Sprite[width * height];

        colors = new Color[]{
                new Color(1.0f,1.0f,0.03f,1.0f),//yellow
                new Color(0.0f, 0.6f, 0.9f, 1.0f),//blue
                new Color(0.0f, 0.8f, 0.4f, 1.0f),//green
        };

        Vector2 offsetVec = new Vector2(width / 2, height / 2);
        int index = 0;

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                Tile currentTile = dataModel.getTile(index);
                float xPos = x - offsetVec.x - 0.5f;
                float yPos = y - offsetVec.y - 0.5f;
                float rotation = currentTile.orientation * 90f;

                int textureIndex = Tile.getTextureIndex(currentTile.type);
                Texture spriteTexture = tileTextures[textureIndex];

                background[index] = new Sprite(tileTextures[6], 0, 0, tileTextures[0].getWidth(), tileTextures[0].getHeight());
                background[index].setSize(0.95f, 0.95f);
                background[index].setPosition(xPos,yPos);

                sprites[index] = new Sprite(spriteTexture, 0, 0, tileTextures[0].getWidth(), tileTextures[0].getHeight());
                Sprite sprite = sprites[index];

                sprite.setPosition(xPos, yPos);
                sprite.setSize(0.95f, 0.95f);
                sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
                sprite.rotate(rotation);

                sprite.setColor(Color.WHITE);

                index++;
            }
        }

        FadeTween tween = new FadeTween(sprites,5f);
        TweenManager.addTween(tween);
    }

    public void render(SpriteBatch batch)
    {
        int[] traversalData = dataModel.getVisited();

        for (int i = 0; i < traversalData.length; i++)
        {
            Tile tile = dataModel.getTile(i);

            Sprite sprite = sprites[i];
            float tileOrientation = tile.orientation * (-90);
            Color tileColor = getSpriteStateColor(traversalData[i]);

            int textureIndex = Tile.getTextureIndex(tile.type);
            if (traversalData[i] == 0 && textureIndex < 3){
                sprite.setTexture(tileTextures[textureIndex +7]);
            }
            else{
                sprite.setTexture(tileTextures[textureIndex]);
            }

            sprite.setRotation(tileOrientation);
            sprite.setColor(tileColor.r,tileColor.g,tileColor.b,sprite.getColor().a);
        }

        TileInfo[] goalInfo = dataModel.getGoalsInfo();

        for (int i = 0; i < goalInfo.length; i++)
        {
            sprites[goalInfo[i].index].setColor(colors[goalInfo[i].id-1]);
        }

        for (int i = 0; i < sprites.length; i++)
        {
            sprites[i].draw(batch);
            background[i].draw(batch);
        }
    }

    private Color getSpriteStateColor(int tileState){

        switch (tileState)
        {
            case 0:
                return Color.WHITE;
            case 1:
                return colors[0];
            case 2:
                return colors[1];
            case 3:
                return colors[2];
            default:
                return Color.VIOLET;
        }
    }

    @Override
    public void onLevelEnter()
    {

    }

    @Override
    public void onLevelComplete()
    {

    }

    @Override
    public void onLevelExit(int currentLevelId)
    {

    }
}
