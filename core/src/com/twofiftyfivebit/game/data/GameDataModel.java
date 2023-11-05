package com.twofiftyfivebit.game.data;

import com.twofiftyfivebit.game.screenmanagement.IlevelStateListener;
import com.twofiftyfivebit.game.utilities.IInputListener;

import java.util.ArrayList;
import java.util.HashSet;

public class GameDataModel extends LevelData implements IInputListener
{
    private GridTraverser[] traversers;

    private boolean traversalDataChange;
    private int[] traversalData;

    private ArrayList<IlevelStateListener> stateListeners;

    public GameDataModel()
    {
    }

    public GameDataModel(LevelData levelData, IlevelStateListener stateListener)
    {
        super(levelData);

        this.stateListeners = new ArrayList<>();
        this.stateListeners.add(stateListener);

        traversers = new GridTraverser[sourcesInfo.length];
        for (int i = 0; i < traversers.length; i++)
        {
            int sourceIndex = sourcesInfo[i].index;
            int id = sourcesInfo[i].id;

            GridTraverser traverser = new GridTraverser(levelData, sourceIndex, id);
            traversers[i] = traverser;
            traverser.traverse();
        }

        traversalDataChange = true;
    }

    public void addStateListener(IlevelStateListener listener)
    {
        stateListeners.add(listener);
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

        for (int i = 0; i < goalsInfo.length; i++)
        {
            TileInfo goalInfo = goalsInfo[i];

            if (visited[goalInfo.index] == goalInfo.id)
            {
                for (int j = 0; j < stateListeners.size(); j++)
                {
                    stateListeners.get(j).onLevelComplete(); //need to count if all goals reached before calling
                }
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

            traversalDataChange = false;
        }

        return traversalData;
    }
}
