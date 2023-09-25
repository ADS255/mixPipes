package com.twofiftyfivebit.game.data;

import java.util.HashMap;
import java.util.Map;
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

    public static HashMap<Type, Integer> textureLookup = new HashMap<>();

    static
    {
        textureLookup.put(Type.straight, 0);
        textureLookup.put(Type.bend, 1);
        textureLookup.put(Type.junction, 2);
        textureLookup.put(Type.start, 3);
        textureLookup.put(Type.end, 3);
    }

    public Type type;
    public int orientation;
    public int[] connections;

    public static Tile straight = new Tile(Type.straight, 0, new int[]{1, 0, 1, 0});

    public Tile()
    {
    }

    public Tile(Tile tile)
    {
        this.type = tile.type;
        this.orientation = tile.orientation;
        this.connections = tile.connections;
    }

    public Tile(Type type, int orientation, int[] connections)
    {
        this.type = type;
        this.orientation = orientation;
        this.connections = connections;
    }

    public static int getTextureIndex(Type tileType)
    {
        return textureLookup.get(tileType);
    }

    public void rotate()
    {
        orientation = (orientation + 1) % 4;
        shiftConnectionsRight();
    }

    private void shiftConnectionsRight()
    {
        int lastElement = connections[connections.length - 1];
        int[] shiftedConnections = new int[connections.length];

        for (int i = 0; i < connections.length - 1; i++)
        {
            shiftedConnections[i + 1] = connections[i];
        }

        shiftedConnections[0] = lastElement;

        connections = shiftedConnections;
    }
}
