package com.aegis.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Forer on 3/11/2016.
 */
public class StateManager {
    public static boolean playerTurn = true;
    static StateManager i;
    static State currentState;
    public static StateManager getInstance() {
        if (i == null) i = new StateManager();
        return i;
    }

    public static State getCurrentState() {
        return currentState;
    }

    public StateManager() {
        loadState(new IsTurnEnd());
    }

    public void update(float dt) {
        currentState.update(dt);
    }

    public void render(SpriteBatch sb) {
        currentState.render(sb);
    }

    public static void loadState(State s) {
        if (currentState != null) currentState.deload();
        currentState = s;
        currentState.load();
    }
}
