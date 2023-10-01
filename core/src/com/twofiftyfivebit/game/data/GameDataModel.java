package com.twofiftyfivebit.game.data;

import com.sun.org.apache.bcel.internal.generic.INEG;
import com.twofiftyfivebit.game.utilities.IInputListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GameDataModel extends LevelData implements IInputListener
{
    private GridTraverser[] traversers;

    public GameDataModel()
    {
    }

    public GameDataModel(LevelData levelData)
    {
        super(levelData);

        traversers = new GridTraverser[sourceIndexes.length];
        for (int i = 0; i < sourceIndexes.length; i++)
        {
            int sourceIndex = sourceIndexes[i];
            GridTraverser traverser = new GridTraverser(levelData, sourceIndex);
            traversers[i] = traverser;
            traverser.traverse();
        }
    }

    @Override
    public void onInput(int clickIndex)
    {
        if (clickIndex != -1)
        {
            tiles[clickIndex].rotate();
        }

        for (int i = 0; i < traversers.length; i++)
        {
            traversers[i].traverse();
        }
    }

    public int[] getVisited()
    {
        int[] traversalData = new int[width * height];

        for (int i = 0; i < traversers.length; i++)
        {
            HashSet<Integer> traverserVisited = traversers[i].getVisited();

            for (Integer tileIndex : traverserVisited)
            {
                if (traversalData[tileIndex] == 0)
                {
                    traversalData[tileIndex] = i + 1;
                } else
                {
                    traversalData[tileIndex] = 3;
                }
            }

        }

        return traversalData;
    }
}
