package com.twofiftyfivebit.game.data;

import com.sun.org.apache.bcel.internal.generic.INEG;
import com.twofiftyfivebit.game.utilities.IInputListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GameDataModel extends LevelData implements IInputListener
{
    private GridTraverser[] traversers;

    private boolean traversalDataChange;
    private int[] traversalData;

    public GameDataModel()
    {
    }

    public GameDataModel(LevelData levelData)
    {
        super(levelData);

        traversers = new GridTraverser[sourcesInfo.length];
        for (int i = 0; i < traversers.length; i++)
        {
            int sourceIndex = sourcesInfo[i].index;
            int id = sourcesInfo[i].id;

            GridTraverser traverser = new GridTraverser(levelData, sourceIndex,id);
            traversers[i] = traverser;
            traverser.traverse();
        }

        traversalDataChange = true;
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

        traversalDataChange = true;

        int[] visited = getVisited();

        for (int i =0; i < goalsInfo.length; i++){
            TileInfo goalInfo = goalsInfo[i];

            if(visited[goalInfo.index] == goalInfo.id){
                System.out.println("Level complete !");
            }
        }
    }

    public int[] getVisited()
    {
        if (traversalDataChange)
        {

            traversalData = new int[width * height];

            for (int i = 0; i < traversers.length; i++)
            {
                HashSet<Integer> traverserVisited = traversers[i].getVisited();

                for (Integer tileIndex : traverserVisited)
                {
                    if (traversalData[tileIndex] == 0)
                    {
                        traversalData[tileIndex] = traversers[i].getId();
                    } else
                    {
                        traversalData[tileIndex] = 3;
                    }
                }

            }

            for(int i = 0; i < goalsInfo.length; i++){
                traversalData[goalsInfo[i].index] = goalsInfo[i].id;
            }

            traversalDataChange = false;
        }

        return traversalData;
    }
}
