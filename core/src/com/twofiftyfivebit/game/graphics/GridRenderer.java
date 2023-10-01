package com.twofiftyfivebit.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.twofiftyfivebit.game.data.GameDataModel;
import com.twofiftyfivebit.game.data.LevelData;
import com.twofiftyfivebit.game.data.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GridRenderer
{
    GameDataModel dataModel;
    Texture[] tileTextures;
    private Sprite[] sprites;

    private int width;
    private int height;

    public GridRenderer(GameDataModel dataModel, Texture[] tileTextures)
    {
        this.dataModel = dataModel;
        this.tileTextures = tileTextures;

        this.width = dataModel.getWidth();
        this.height = dataModel.getHeight();

        sprites = new Sprite[width * height];

        Vector2 offsetVec = new Vector2(width / 2, height / 2);
        int index = 0;

        for (int i = 0; i < tileTextures.length; i++)
        {
            tileTextures[i].setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

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

                sprites[index] = new Sprite(spriteTexture, 0, 0, tileTextures[0].getWidth(), tileTextures[0].getHeight());
                Sprite sprite = sprites[index];

                sprite.setPosition(xPos, yPos);
                sprite.setSize(1f, 1f);
                sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
                sprite.rotate(rotation);
                sprite.setColor(Color.WHITE);

                index++;
            }
        }
    }

    public void render(SpriteBatch batch)
    {
        for (int i = 0; i < dataModel.getTileCount(); i++)
        {
            Tile tile = dataModel.getTile(i);
            sprites[i].setRotation(tile.orientation * (-90f));

            sprites[i].draw(batch);
        }

        int[] traversalData = dataModel.getVisited();

        for (int i = 0; i < traversalData.length; i++)
        {
            int tileState = traversalData[i];

            switch (tileState)
            {
                case 0:
                    sprites[i].setColor(Color.WHITE);
                    break;

                case 1:
                    sprites[i].setColor(Color.BLUE);
                    break;

                case 2:
                    sprites[i].setColor(Color.YELLOW);
                    break;

                case 3:
                    sprites[i].setColor(Color.GREEN);
                    break;
            }

            sprites[i].draw(batch);
        }
    }
}
