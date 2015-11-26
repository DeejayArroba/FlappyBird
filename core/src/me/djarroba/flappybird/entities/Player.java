package me.djarroba.flappybird.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import me.djarroba.flappybird.screens.GameScreen;
import me.djarroba.flappybird.units.Units;

public class Player extends Sprite {

	private GameScreen screen;
	public Body body;

	public Player(GameScreen screen) {
		super(screen.game.assetManager.get("spritesheet.png", Texture.class), 264, 64, 16, 12);
		this.screen = screen;

		setSize(getWidth()/ Units.PPU, getHeight()/Units.PPU);
		createPhysicsBody();
	}

	public void createPhysicsBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(0, 0);

		body = screen.world.createBody(bodyDef);

		FixtureDef fixtureDef = new FixtureDef();

		CircleShape shape = new CircleShape();
		shape.setRadius(0.8f);

		fixtureDef.shape = shape;


		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;

		body.createFixture(fixtureDef);
		shape.dispose();

	}

	public void update(float delta) {
		setPosition(body.getPosition().x, body.getPosition().y);

		boolean jumped = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);

		if(jumped) {
			body.setLinearVelocity(body.getLinearVelocity().x, 7f);
		}

	}

}
