package com.aegis.actions;

import com.aegis.game.Board;
import com.aegis.game.BoardManager;
import com.aegis.game.Tile;
import com.aegis.states.PlayerTurn;
import com.aegis.states.StateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by Forer on 3/17/2016.
 */
public class MoveAction extends Action {
    int selX;
    int selY;
    Board board;
    Tile toMoveFrom;
    boolean readyToAct;
    @Override
    public void startAction() {
        selX = 0;
        selY = 0;
        PlayerTurn pt = (PlayerTurn) StateManager.getCurrentState();
        toMoveFrom = pt.playerList.get(pt.playerIterator).getParent();
        board = BoardManager.getBoard(0, true);
        board.selectTile(selX, selY);
    }

    @Override
    public void targetUpdate() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) targetUp();
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) targetDown();
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) targetLeft();
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) targetRight();
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) targetSelection();
        board.selectTile(toMoveFrom.x, toMoveFrom.y);
    }

    @Override
    public boolean doneTargetting() {
        return readyToAct;
    }

    @Override
    public void doAction() {
        Tile toGoTo = board.getTile(selX, selY);
        if (toGoTo.heldObject == null) {
            toGoTo.heldObject = toMoveFrom.heldObject;
            toMoveFrom.heldObject = null;
            toGoTo.heldObject.setParent(toGoTo);
            toMoveFrom.unSelect();

        } else {
            System.out.println("I CAN'T MOVE THERE THAT HAS SOMETHING THERE");
        }
    }

    void targetUp() {
        if (selY > 0) {
            board.unSelectTile(selX, selY);
            selY--;
            board.selectTile(selX, selY);
        }
    }

    void targetDown() {
        if (selY < board.height -1) {
            board.unSelectTile(selX, selY);
            selY++;
            board.selectTile(selX, selY);
        }
    }

    void targetLeft() {
        if (selX > 0) {
            board.unSelectTile(selX, selY);
            selX--;
            board.selectTile(selX, selY);
        }
    }

    void targetRight() {
        if (selX < board.width -1) {
            board.unSelectTile(selX, selY);
            selX++;
            board.selectTile(selX, selY);
        }
    }

    void targetSelection() {
        readyToAct = true;
    }

    @Override
    public void cleanup() {
        BoardManager.getBoard(0, true).unSelectTile(selX, selY);
    }
}
