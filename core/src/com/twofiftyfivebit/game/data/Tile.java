package com.twofiftyfivebit.game.data;

import java.util.concurrent.TimeoutException;

public class Tile
{
    public enum Type
    {
        straight,
        bend,
        junction,
        start,
        end
    }

    public Type type;
    public int orientation;
    public boolean[] connections;

    public static Tile straight = new Tile(Type.straight,0,new boolean[]{true,false,true,false});

    public Tile()
    {
    }

    public Tile(Tile tile)
    {
        this.type = tile.type;
        this.orientation = tile.orientation;
        this.connections = tile.connections;
    }

    public Tile(Type type, int orientation, boolean[] connections)
    {
        this.type = type;
        this.orientation = orientation;
        this.connections = connections;
    }
}
