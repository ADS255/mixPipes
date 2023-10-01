package com.twofiftyfivebit.game.data;

import com.twofiftyfivebit.game.graphics.GridRenderer;

import java.util.ArrayList;
import java.util.HashSet;

public class GridTraverser
{
    private LevelData levelData;
    private HashSet<Integer> visited;
    private int sourceIndex;

    public GridTraverser(LevelData levelData, int sourceIndex)
    {
        this.levelData = levelData;
        this.visited = new HashSet<>();
        this.sourceIndex = sourceIndex;
    }

    public void traverse()
    {
        visited.clear();
        traverseTile(sourceIndex);
    }

    public HashSet<Integer> getVisited()
    {
        return visited;
    }

    private void traverseTile(int index)
    {
        if (visited.contains(index))
        {
            return;
        }
        visited.add(index);

        Tile tile = levelData.getTile(index);
        int[] connections = tile.connections;

        for (int i = 0; i < connections.length; i++)
        {
            if (connections[i] == 1)
            {
                int OppositeConnection = (i + 2) % 4;

                int neighbourIndex = getNeighbourIndex(index, i);
                Tile neighbour = levelData.getTile(neighbourIndex);

                if (neighbourIndex == -1 || neighbour == null)
                {
                    continue;
                }

                if (neighbour.hasConnection(OppositeConnection) && !visited.contains(neighbourIndex))
                {
                    traverseTile(neighbourIndex);
                }
            }
        }
    }

    private int getNeighbourIndex(int currentIndex, int direction)
    {
        int neighbourIndex = currentIndex;
        int horizontalOffset = levelData.getHeight();
        int verticalOffset = 1;
        int invalidIndex = -1;

        switch (direction)
        {
            case 0:
                neighbourIndex += verticalOffset;
                if (Math.floor(neighbourIndex / horizontalOffset) != Math.floor(currentIndex / horizontalOffset))
                {
                    return invalidIndex;
                }
                break;
            case 1:
                neighbourIndex += horizontalOffset;
                break;
            case 2:
                neighbourIndex -= verticalOffset;
                if (Math.floor(neighbourIndex / horizontalOffset) != Math.floor(currentIndex / horizontalOffset))
                {
                    return invalidIndex;
                }
                break;
            case 3:
                neighbourIndex -= horizontalOffset;
                break;
        }

        return neighbourIndex;
    }
}
