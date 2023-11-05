package com.twofiftyfivebit.game.tweening;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import jdk.dynalink.beans.StaticClass;

public class TweenManager
{
    private static ArrayList<Itween> tweens;

    public TweenManager()
    {
        tweens = new ArrayList<>(10);
    }


    public void update()
    {
        float deltaTime = Gdx.graphics.getDeltaTime();
        runTweens(deltaTime);
    }

    private void runTweens(float delta)
    {
        for (int i = 0; i < tweens.size(); i++)
        {
            Itween tween = tweens.get(i);
            tween.run(delta);

            //if (tween.isComplete())
            //{
                //tweens.remove(i);
            //}
        }
    }

    public static void addTween(Itween tween)
    {
        tweens.add(tween);
    }

}
