package com.aegis.states;

import com.aegis.actions.Action;
import com.aegis.actions.ActionFactory;
import com.aegis.game.Board;
import com.aegis.game.BoardManager;
import com.aegis.game.BoardObject;
import com.aegis.menu.MenuManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

/**
 * Created by Forer on 3/11/2016.
 */
public class PlayerTurn extends State {
    Board boardToCheck;
    public List<BoardObject> playerList;
    public int playerIterator = 0;
    Action currentAction;

    @Override
    public void load() {
        boardToCheck = BoardManager.getBoard(0, true);
        playerList = boardToCheck.getObjectOnBoard();
        playerList.get(playerIterator).getParent().select();
    }

    @Override
    public void update(float dt) {
        controls();
    }

    @Override
    public void deload() {
        playerList.get(playerIterator).getParent().unSelect();
    }

    @Override
    public void render(SpriteBatch sb) {

    }


    void controls() {
        if (currentAction != null) {
            //OPERATE ACTION
            if (!currentAction.doneTargetting()) {
                currentAction.targetUpdate();
            } else {
                currentAction.doAction();
                currentAction.cleanup();
                currentAction = null;
            }
        } else {
            if (!MenuManager.isMenuOpen()) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) moveUp();
                if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
                    moveDown();

                if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) selectCharacter();
            } else {
                if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) MenuManager.menuUp();
                if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) MenuManager.menuDown();
                if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) rightMenu();
                if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && MenuManager.menuCanLeft()) MenuManager.menuLeft();
            }
        }
    }

    void moveUp() {
        if (playerIterator < playerList.size()-1) {
            playerList.get(playerIterator).getParent().unSelect();
            playerIterator++;
            playerList.get(playerIterator).getParent().select();
        } else {
            playerList.get(playerIterator).getParent().unSelect();
            playerIterator = 0;
            playerList.get(playerIterator).getParent().select();
        }
    }

    void moveDown() {
        if (playerIterator > 0) {
            playerList.get(playerIterator).getParent().unSelect();
            playerIterator--;
            playerList.get(playerIterator).getParent().select();
        } else {
            playerList.get(playerIterator).getParent().unSelect();
            playerIterator = playerList.size() -1;
            playerList.get(playerIterator).getParent().select();
        }
    }

    void rightMenu() {
        if (MenuManager.menuCanRight()) {
            if (MenuManager.isOnAction()) {
                currentAction = ActionFactory.getActionFromEnum(MenuManager.getActionEnum());
                MenuManager.closeMenu();
                currentAction.startAction();
            } else {
                //navigate menu
                MenuManager.menuRight();
            }
        }
    }

    void selectCharacter() {
        MenuManager.createMenu();
    }
}
