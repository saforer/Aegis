package com.aegis.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AegisOfTheLost extends ApplicationAdapter {
	SpriteBatch batch;
	GameManager gm;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gm = GameManager.getInstance();
	}

	@Override
	public void render () {
		gm.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gm.render(batch);
		batch.end();
	}
}
