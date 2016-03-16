package com.aegis.states;

import com.aegis.game.Board;
import com.aegis.game.BoardManager;
import com.aegis.game.BoardObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Forer on 3/11/2016.
 */
public class IsTurnEnd extends State {
    @Override
    public void load() {
        Board boardToCheck;
        if (StateManager.playerTurn) {
            boardToCheck = BoardManager.getBoard(0, true);
        } else {
            boardToCheck = BoardManager.getBoard(0, false);
        }

        boolean allExhausted = true; //is there at least ONE non-exhausted thing?
        for (BoardObject b : boardToCheck.getObjectOnBoard()) {
            if (b.exhausted == false) {
                allExhausted = false;
            }
        }

        if (allExhausted) {
            //TURN ENDS!!!!
            System.out.println("ALL EXHAUSTED!!!!!!");
            StateManager.playerTurn = false;
            StateManager.loadState(new DeadState());
        } else {
            int x = 0;
            int y = 0;
            for (BoardObject b : boardToCheck.getObjectOnBoard()) {
                if (b.exhausted == false) {
                    x = b.getParent().x;
                    y = b.getParent().y;
                }
            }
            //Someone's not exhausted.
            StateManager.loadState(new PlayerTurn());
        }

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
