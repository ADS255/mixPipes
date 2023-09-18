package com.twofiftyfivebit.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.twofiftyfivebit.game.data.LevelData;

import java.util.ArrayList;

public class GridRenderer
{
    LevelData levelData;
    Texture[] tileTextures;
    private Sprite[] sprites;

    private int width;
    private int height;

    public GridRenderer(LevelData levelData, Texture[] tileTextures)
    {
        this.levelData = levelData;
        this.tileTextures = tileTextures;

        this.width = levelData.getWidth();
        this.height = levelData.getHeight();

        sprites = new Sprite[width*height];

        Vector2 offsetVec = new Vector2(width/2,height/2);
        int index =0;

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                float xPos = x-offsetVec.x-0.5f;
                float yPos = y-offsetVec.y-0.5f;
                float rotation = levelData.getOrientation(index)*90f;

                sprites[index] = new Sprite(tileTextures[0],0,0,tileTextures[0].getWidth(),tileTextures[0].getHeight());
                Sprite sprite = sprites[index];

                sprite.setPosition(xPos,yPos);
                sprite.setSize(1f,1f);
                sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
                sprite.rotate(rotation);
                System.out.println(sprite.getRotation());
                sprite.setColor(Color.WHITE);

                index++;
            }
        }
    }

    public void render(SpriteBatch batch)
    {
        for (int i = 0; i < levelData.getTileCount(); i++)
        {
            sprites[i].draw(batch);
        }
    }
}
