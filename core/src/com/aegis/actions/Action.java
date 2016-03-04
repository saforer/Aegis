package com.aegis.actions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Action {
	public abstract void create();
	public abstract void update();
	public abstract void unload();
	public abstract void render(SpriteBatch sb);
}
