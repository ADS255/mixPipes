package com.twofiftyfivebit.game.data;

public class LevelData
{
    public int width;
    public int height;
    public Tile[] tiles;

    public LevelData()
    {
    }

    public LevelData(LevelData levelData)
    {
        this.width = levelData.width;
        this.height = levelData.height;
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
        return tiles[index];
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getTileCount()
    {
        return tiles.length;
    }

}
