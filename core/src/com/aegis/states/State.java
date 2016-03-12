package com.aegis.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Forer on 3/11/2016.
 */
public abstract class State {
    public abstract void load();
    public abstract void update(float dt);
    public abstract void deload();
    public abstract void render(SpriteBatch sb);
}
