package com.twofiftyfivebit.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.twofiftyfivebit.game.data.GameDataModel;
import com.twofiftyfivebit.game.graphics.GridRenderer;
import com.twofiftyfivebit.game.utilities.InputHandler;

public class GameScreen implements Screen
{
    private OrthographicCamera camera;
    private FillViewport viewport;
    final private float worldWidth = 16f;
    final private float worldHeight = 9f;
    private SpriteBatch batch;

    private AssetManager assetManager;
    private InputHandler inputHandler;
    private GridRenderer gridRenderer;

    private GameDataModel gameDataModel;
    private Texture[] tileTextures;

    public GameScreen(AssetManager assetManager, GameDataModel gameDataModel)
    {
        this.assetManager = assetManager;

        camera = new OrthographicCamera();
        viewport = new FillViewport(worldWidth, worldHeight, camera);

        batch = new SpriteBatch();

        this.gameDataModel = gameDataModel;

        inputHandler = new InputHandler(camera,this.gameDataModel);
        Gdx.input.setInputProcessor(inputHandler);
        inputHandler.addListener(gameDataModel);

        tileTextures = new Texture[4];
        tileTextures[0] = assetManager.get("0.png", Texture.class);
        tileTextures[1] = assetManager.get("1.png", Texture.class);
        tileTextures[2] = assetManager.get("2.png", Texture.class);
        tileTextures[3] = assetManager.get("3.png", Texture.class);

        gridRenderer = new GridRenderer(this.gameDataModel,tileTextures);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.21f, 0.27f, 0.30f, 1.0f); // Set a clear color (e.g., black)
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

        for (int i = 0; i < tileTextures.length; i++)
        {
            tileTextures[i].dispose();
        }
    }
}
