package me.djarroba.flappybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2D;
import me.djarroba.flappybird.screens.GameScreen;

public class FlappyBird extends Game {

	Screen screen;
	public AssetManager assetManager;

	@Override
	public void create() {
		assetManager = new AssetManager();

		assetManager.load("spritesheet.png", Texture.class);
		assetManager.load("background.png", Texture.class);

		assetManager.finishLoading();

		Box2D.init();

		screen = new GameScreen(this);
		setScreen(screen);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
		screen.dispose();
	}
}
