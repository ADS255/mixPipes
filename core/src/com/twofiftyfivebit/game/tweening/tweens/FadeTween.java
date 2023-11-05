package com.twofiftyfivebit.game.tweening.tweens;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.twofiftyfivebit.game.tweening.Tween;

public class FadeTween extends Tween
{
    public FadeTween(Sprite[] targets, float duration){
        super(targets,duration);
    }

    @Override
    protected void runTween(float percent)
    {
        for (int i = 0; i < targets.length; i++)
        {
            targets[i].setAlpha(1f - percent);
        }
    }
}
