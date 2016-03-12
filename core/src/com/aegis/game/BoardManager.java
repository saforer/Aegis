package com.aegis.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class BoardManager {
    public static BoardManager i;
    static List<Board> playerBoard;
    static List<Board> enemyBoard;

    BoardManager() {
        playerBoard = new ArrayList<Board>();
        enemyBoard = new ArrayList<Board>();
    }

    public static BoardManager getInstance() {
        if (i == null) {
            i = new BoardManager();
        }
        return i;
    }

    public static void addBoard(Board inBoard, boolean friendly) {
        if (friendly) {
            playerBoard.add(inBoard);
        } else {
            enemyBoard.add(inBoard);
        }
    }

    public static Board getBoard(int boardNum, boolean friendly) {
        if (friendly) {
            return playerBoard.get(boardNum);
        } else {
            return enemyBoard.get(boardNum);
        }
    }

    public void drawBoards(SpriteBatch sb) {
        for (Board b : enemyBoard) {
            b.drawBoard(sb,false);
        }

        for (Board b : playerBoard) {
            b.drawBoard(sb,true);
        }
    }

    public void addObject(BoardObject bo, boolean friendly, int y, int x, int z) {
        List<Board> touse;
        if (friendly) {
            touse = playerBoard;
        } else {
            touse = enemyBoard;
        }
        touse.get(z).tileGrid[x][y].heldObject = bo;
        bo.setParent(touse.get(z).tileGrid[x][y]);
    }

    public void update(float dt) {
        for (Board b : playerBoard) {
            b.update(dt);
        }
        for (Board b : enemyBoard) {
            b.update(dt);
        }
    }
}
