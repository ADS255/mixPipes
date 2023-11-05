package com.twofiftyfivebit.game.tweening;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tween implements Itween
{
    protected Sprite[] targets;

    protected float duration;
    protected float currentTime = 0f;
    protected boolean isComplete = false;

    public Tween (Sprite[] targets, float duration){
        this.targets = targets;
        this.duration = duration;
    }

    protected void runTween(float percent)
    {

    }

    @Override
    public void run(float delta)
    {
        if (currentTime < duration)
        {
            runTween(currentTime / duration);
            currentTime += delta;
        } else
        {
            isComplete = true;
        }
    }

    @Override
    public boolean isComplete()
    {
        return isComplete;
    }
}
