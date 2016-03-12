package com.aegis.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Forer on 3/11/2016.
 */
public class DeadState extends State {
    @Override
    public void load() {
        System.out.println("HELP I'm in a dead state!");
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void deload() {

    }

    @Override
    public void render(SpriteBatch sb) {

    }
}
