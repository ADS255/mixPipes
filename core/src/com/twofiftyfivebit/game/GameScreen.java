package com.twofiftyfivebit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen
{
    private Camera camera;
    private FillViewport viewport;

    private SpriteBatch batch;

    private Texture background;

    final private float worldWidth = 32f;
    final private float worldHeight = 18f;

    GameScreen()
    {
        camera = new OrthographicCamera();
        viewport = new FillViewport(worldWidth,worldHeight,camera);

        batch = new SpriteBatch();

        background = new Texture("Square.png");
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(255, 0, 0, 1); // Set a clear color (e.g., black)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(background,-8f,0f,1f,1f);
        batch.draw(background,1f,0f,1f,1f);

        batch.end();
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        batch.dispose();

        background.dispose();
    }
}
