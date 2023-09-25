package com.twofiftyfivebit.game.data;

import com.twofiftyfivebit.game.utilities.IInputListener;

public class GameDataModel extends LevelData implements IInputListener
{
    public GameDataModel(LevelData levelData)
    {
        super(levelData);
    }

    @Override
    public void onInput(int clickIndex)
    {
        if (clickIndex != -1)
        {
            tiles[clickIndex].rotate();
        }
    }
}
