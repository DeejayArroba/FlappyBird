package me.djarroba.flappybird.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.djarroba.flappybird.screens.GameScreen;
import me.djarroba.flappybird.units.Units;

public class PipeSet {

	Sprite topPipe;
	Sprite bottomPipe;
	private GameScreen screen;

	public PipeSet(GameScreen screen, float offset) {
		float separation = 1f;

		this.screen = screen;
		Texture spritesheet = screen.game.assetManager.get("spritesheet.png", Texture.class);
		topPipe = new Sprite(new TextureRegion(spritesheet, 302, 0, 26, 135));
		topPipe.setSize(topPipe.getWidth()/Units.PPU, topPipe.getHeight()/Units.PPU);
		topPipe.setPosition(10, separation+offset);
		bottomPipe = new Sprite(new TextureRegion(spritesheet, 330, 0, 26, 120));
		bottomPipe.setSize(bottomPipe.getWidth()/Units.PPU, bottomPipe.getHeight()/Units.PPU);
		bottomPipe.setPosition(4.5f, -bottomPipe.getHeight()-separation+offset);
	}

	public void drawPipes(SpriteBatch batch) {
		topPipe.draw(batch);
		bottomPipe.draw(batch);
	}

	public void update(float delta) {
		topPipe.setPosition(topPipe.getX()-3*delta, topPipe.getY());
		bottomPipe.setPosition(topPipe.getX()-3*delta, bottomPipe.getY());
	}

}
