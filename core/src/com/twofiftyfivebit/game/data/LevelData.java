package com.twofiftyfivebit.game.data;

public class LevelData
{
    protected int width;
    protected int height;

    protected int[] sourceIndexes;
    protected Tile[] tiles;

    public LevelData()
    {
    }

    public LevelData(LevelData levelData)
    {
        this.width = levelData.width;
        this.height = levelData.height;
        this.sourceIndexes = levelData.sourceIndexes;
        this.tiles = levelData.tiles;
    }

    public LevelData(int width, int height, Tile[] tiles)
    {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    public Tile[] getTiles()
    {
        return tiles;
    }

    public Tile getTile(int index)
    {
        if (0 <= index && index < tiles.length)
        {
            return tiles[index];
        }
        return null;
    }

    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }

    public int[] getSourceIndexes(){return sourceIndexes;}
    public int getTileCount()
    {
        return tiles.length;
    }

}
