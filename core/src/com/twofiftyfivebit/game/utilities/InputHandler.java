package com.twofiftyfivebit.game.utilities;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.twofiftyfivebit.game.data.LevelData;

import java.util.ArrayList;

public class InputHandler extends InputAdapter
{
    private ArrayList<IInputListener> listeners;

    private OrthographicCamera camera;
    private LevelData levelData;

    private int width;
    private int height;

    private float minX;
    private float maxX;
    private float minY;
    private float maxY;

    private Vector2 gridOrigin;

    public InputHandler(OrthographicCamera camera, LevelData levelData)
    {
        listeners = new ArrayList<>();

        this.camera = camera;
        this.levelData = levelData;

        width = levelData.getWidth();
        height = levelData.getHeight();

        maxX = (width / 2 - 0.5f) * width;
        minX = maxX * -1.0f;
        maxY = (height / 2 - 0.5f) * height;
        minY = maxY * -1.0f;

        gridOrigin = new Vector2(width / 2 * (-1.0f), height / 2 * (-1.0f));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Vector3 screenCoordinates = new Vector3(screenX, screenY, 0);
        Vector3 touchPos = camera.unproject(screenCoordinates);

        float x = touchPos.x;
        float y = touchPos.y;

        int clickIndex = -1;

        if (x >= minX && x <= maxX && y >= minY && y <= maxY)
        {
            clickIndex = toGridIndex(x, y);
        }

        onClickEvent(clickIndex);

        return true;
    }

    public void addListener(IInputListener listener)
    {
        listeners.add(listener);
    }

    private void onClickEvent(int clickIndex)
    {
        for (int i = 0; i < listeners.size(); i++)
        {
            listeners.get(i).onInput(clickIndex);
        }
    }

    private int toGridIndex(float xPos, float yPos)
    {
        int centerIndex = (levelData.getTileCount() - 1) / 2;
        int clickIndex = centerIndex;
        int clickX = Math.round(xPos);
        int clickY = Math.round(yPos);

        clickIndex += (height * clickX);
        clickIndex += clickY;

        return clickIndex;
    }

}
