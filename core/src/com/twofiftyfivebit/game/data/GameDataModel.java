package com.twofiftyfivebit.game.data;

import com.twofiftyfivebit.game.utilities.IInputListener;

public class GameDataModel extends LevelData implements IInputListener
{
    private GridTraverser traverser;
    public GameDataModel(LevelData levelData)
    {
        super(levelData);
        traverser = new GridTraverser(levelData);
    }

    @Override
    public void onInput(int clickIndex)
    {
        if (clickIndex != -1)
        {
            tiles[clickIndex].rotate();
        }

        traverser.traverse(0);
    }
}
