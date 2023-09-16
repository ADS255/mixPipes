package com.twofiftyfivebit.game.utilities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputHandler extends InputAdapter
{
    private  OrthographicCamera camera;

    public InputHandler (OrthographicCamera camera)
    {
        this.camera = camera;

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        System.out.println(camera.unproject(new Vector3(screenX,screenY,0)));
        return true;
    }
}
