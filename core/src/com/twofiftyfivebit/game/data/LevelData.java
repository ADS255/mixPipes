package com.twofiftyfivebit.game.data;

public class LevelData
{
    public  int width;
    public  int height;
    public  Tile[] tiles;

    public LevelData(){}

    public LevelData(int width, int height, Tile[] tiles)
    {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }
}
