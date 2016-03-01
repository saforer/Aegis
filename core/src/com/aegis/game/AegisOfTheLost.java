package com.aegis.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class AegisOfTheLost extends ApplicationAdapter {
	SpriteBatch batch;
	Board boardA;
	Board boardB;
		
	@Override
	public void create () {
		batch = new SpriteBatch();
		boardA = new Board(3, 3, new Vector2(.4f, .425f), true);
		boardA.add(new Object("fighter.png", new Vector2(12, 12)), 0, 1);
		boardA.add(new Object("mage.png", new Vector2(12, 12)), 2, 0);
		boardA.add(new Object("healer.png", new Vector2(12, 8)), 2, 2);
		boardB = new Board(3, 3, new Vector2(.6f, .585f), false);
		boardB.add(new Object("malboro.png", new Vector2(-4, 0)), 1, 1);
		boardB.add(new Object("templar.png", new Vector2(12, 12)), 2, 0);
	}

	@Override
	public void render () {
		boardA.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		boardB.draw(batch);
		boardA.draw(batch);
		batch.end();
	}
}
