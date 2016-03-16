package com.aegis.actions;

import com.aegis.game.Board;
import com.aegis.game.BoardManager;
import com.aegis.game.Tile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by Forer on 3/15/2016.
 */
public class Fart extends Action {
    boolean targetStarted = false;
    boolean targetFinished = false;
    int selectedX, selectedY = 0;
    Tile[][] tilesInBoard;
    Board boardVs;
    @Override
    public void targetUpdate() {
        if (!targetStarted) {
            startTargetting();
        } else {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) targetUp();
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) targetDown();
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) targetLeft();
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) targetRight();
            if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) targetSelection();
        }
    }

    void startTargetting() {
        boardVs = BoardManager.getBoard(0,false);
        tilesInBoard = boardVs.getTileGrid();
        selectTile();
        targetStarted = true;
    }

    @Override
    public boolean doneTargetting() {
        return targetFinished;
    }

    @Override
    public void doAction() {
        if (tilesInBoard[selectedX][selectedY].heldObject != null) {
            System.out.println("I fart in your direction you " + tilesInBoard[selectedX][selectedY].heldObject.name);
        } else {
            System.out.println("I fart at... nothing?");
        }
    }

    void targetDown() {
        if (boardVs.height - 1 > selectedY) {
            deselectTile();
            selectedY++;
            selectTile();
        }
    }

    void targetUp(){
        if (selectedY > 0) {
            deselectTile();
            selectedY--;
            selectTile();
        }
    }

    void targetLeft() {
        if (selectedX > 0) {
            deselectTile();
            selectedX--;
            selectTile();
        }
    }

    void targetRight() {
        if (boardVs.width - 1 > selectedX) {
            deselectTile();
            selectedX++;
            selectTile();
        }
    }

    void targetSelection() {
        targetFinished = true;
    }

    void deselectTile() {
        tilesInBoard[selectedX][selectedY].unSelect();
    }

    void selectTile() {
        tilesInBoard[selectedX][selectedY].select();
    }

    @Override
    public void cleanup() {
        deselectTile();
    }
}
