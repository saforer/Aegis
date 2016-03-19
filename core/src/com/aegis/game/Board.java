package com.aegis.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Board {
    Tile[][] tileGrid;
    public int width;
    public int height;

    public Board (int width, int height) {
        this.width = width;
        this.height = height;
        tileGrid = new Tile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tileGrid[x][y] = new Tile(x,y);
            }
        }
    }

    public void drawBoard(SpriteBatch sb, boolean friendly) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tileGrid[x][y].draw(sb, friendly);
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tileGrid[x][y].drawObject(sb, friendly);
            }
        }
    }

    public void update(float dt) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tileGrid[x][y].heldObject != null) tileGrid[x][y].heldObject.update(dt);
            }
        }
    }

    public List<Tile> getTilesInBoard() {
        List<Tile> output = new ArrayList<Tile>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                output.add(tileGrid[x][y]);
            }
        }
        return output;
    }

    public Tile[][] getTileGrid() {
        return tileGrid;
    }

    public List<BoardObject> getObjectOnBoard() {
        List<BoardObject> output = new ArrayList<BoardObject>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tileGrid[x][y].heldObject != null) output.add(tileGrid[x][y].heldObject);
            }
        }
        return output;
    }

    public void selectTile(int x, int y) {
        tileGrid[x][y].select();
    }

    public void unSelectTile(int x, int y) {
        tileGrid[x][y].unSelect();
    }

    public Tile getTile(int x, int y) {
        return tileGrid[x][y];
    }
}