package me.djarroba.flappybird.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import me.djarroba.flappybird.FlappyBird;
import me.djarroba.flappybird.entities.PipeSet;
import me.djarroba.flappybird.entities.Player;
import me.djarroba.flappybird.units.Units;

import java.util.ArrayList;

public class GameScreen implements Screen {

	public FlappyBird game;

	FillViewport viewport;
	OrthographicCamera camera;

	public World world;

	SpriteBatch batch;
	Player player;

	ArrayList<PipeSet> pipeSets;

	Texture spritesheet;
	Sprite background;

	public static final float WORLD_WIDTH = 16;
	public static final float WORLD_HEIGHT = 9;

	public GameScreen(FlappyBird game) {
		this.game = game;

		spritesheet = game.assetManager.get("spritesheet.png", Texture.class);

		camera = new OrthographicCamera();

		viewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

		camera.position.set(0, 0, 0);

		viewport.apply();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		world = new World(new Vector2(0, -9.8f), true);

		player = new Player(this);

		pipeSets = new ArrayList<PipeSet>();
		pipeSets.add(new PipeSet(this, 0));

		background = new Sprite(game.assetManager.get("background.png", Texture.class));
		background.setSize(background.getWidth() / Units.PPU, background.getHeight() / Units.PPU);
		background.setPosition(-WORLD_WIDTH/2, -WORLD_HEIGHT/2);
	}


	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.step(1/60f, 6, 2);

		player.update(delta);

		for(PipeSet pipeSet : pipeSets) {
			pipeSet.update(delta);
		}

		camera.update();

		batch.begin();

		background.draw(batch);

		player.draw(batch);

		for(PipeSet pipeSet : pipeSets) {
			pipeSet.drawPipes(batch);
		}

		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(0, 0, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
