package com.twofiftyfivebit.game.screenmanagement;

public interface IlevelStateListener
{
    void onLevelEnter();

    void onLevelComplete();

    void onLevelExit(int currentLevelId);
}
