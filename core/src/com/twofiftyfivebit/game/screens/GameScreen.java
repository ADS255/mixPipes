package com.twofiftyfivebit.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.twofiftyfivebit.game.data.GameDataModel;
import com.twofiftyfivebit.game.graphics.GridRenderer;
import com.twofiftyfivebit.game.utilities.InputHandler;

public class GameScreen implements Screen
{
    protected OrthographicCamera camera;
    protected FillViewport viewport;
    final protected float worldWidth = 16f;
    final protected float worldHeight = 9f;
    protected SpriteBatch batch;

    protected FPSLogger fpsLogger;

    protected InputHandler inputHandler;
    protected GridRenderer gridRenderer;

    protected GameDataModel gameDataModel;

    public GameScreen(GameDataModel gameDataModel)
    {
        camera = new OrthographicCamera();
        viewport = new FillViewport(worldWidth, worldHeight, camera);

        batch = new SpriteBatch();

        fpsLogger = new FPSLogger();

        this.gameDataModel = gameDataModel;

        inputHandler = new InputHandler(camera, this.gameDataModel);
        Gdx.input.setInputProcessor(inputHandler);
        inputHandler.addListener(gameDataModel);


        gridRenderer = new GridRenderer(this.gameDataModel);

        this.gameDataModel.addStateListener(gridRenderer);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        fpsLogger.log();

        Gdx.gl.glClearColor(0.18f, 0.18f, 0.18f, 1.0f); // Set a clear color (e.g., black)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        gridRenderer.render(batch);

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
    }
}
