package com.twofiftyfivebit.game.data;

public class LevelData
{
    public int width;
    public int height;
    public Tile[] tiles;

    public LevelData()
    {
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

    public int getOrientation(int index)
    {
        return tiles[index].orientation;
    }

    public void onInput(int x, int y)
    {
        int index = x + y;
        System.out.println(index);
        tiles[index].rotate();
    }
}
