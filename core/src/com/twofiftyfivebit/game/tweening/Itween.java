package com.twofiftyfivebit.game.tweening;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Itween
{
    public void run(float delta);

    public boolean isComplete();
}
