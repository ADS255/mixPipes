package com.twofiftyfivebit.game.data;

import com.twofiftyfivebit.game.graphics.GridRenderer;

import java.util.ArrayList;
import java.util.HashSet;

public class GridTraverser
{
    private LevelData levelData;

    private ArrayList<Integer> visited;

    public GridTraverser(LevelData levelData)
    {
        this.levelData = levelData;
        this.visited = new ArrayList<>();
    }

    public void traverse(int startIndex)
    {
        visited.clear();
        traverseTile(startIndex);

        for (int i = 0; i < visited.size(); i++)
        {
            System.out.println("Visited: "+ visited.get(i));
        }
        System.out.println("----------------------");
    }

    public ArrayList<Integer> getVisited()
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

                int neighbourIndex = getNeighbourIndex(index,i);
                Tile neighbour = levelData.getTile(neighbourIndex);

                if (neighbour == null)
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

        switch (direction)
        {
            case 0:
                neighbourIndex += verticalOffset;
                break;
            case 1:
                neighbourIndex += horizontalOffset;
                break;
            case 2:
                neighbourIndex -= verticalOffset;
                break;
            case 3:
                neighbourIndex -= horizontalOffset;
                break;
        }

        return neighbourIndex;
    }
}
