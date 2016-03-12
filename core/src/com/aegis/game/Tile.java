package com.aegis.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
    public Texture img;
    boolean selected;
    boolean highlighted;
    public int x;
    public int y;
    public BoardObject heldObject;

    static Texture selectedTexture;
    static Texture highlightedTexture;
    static Texture emptyTexture;

    public Tile (int x, int y) {
        if (selectedTexture == null) {
            fillStatic();
        }

        img = emptyTexture;
        this.x = x;
        this.y = y;
    }

    void fillStatic() {
        emptyTexture = new Texture("good-tile.png");
        selectedTexture = new Texture("selected.png");
        highlightedTexture = new Texture("selected2.png");
    }

    void updateTexture() {
        if (selected) {
            img = selectedTexture;
            return;
        }
        if (highlighted) {
            img = highlightedTexture;
            return;
        }
        img = emptyTexture;
    }

    public void select() {
        selected = true;
        updateTexture();
    }

    public void unSelect() {
        selected = false;
        updateTexture();
    }

    public void highlight() {
        highlighted = true;
        updateTexture();
    }

    public void unHighlight() {
        highlighted = false;
        updateTexture();
    }

    public void draw(SpriteBatch sb, boolean friendly) {
        float flX, flY;
        //Start out in the center
        flX = Gdx.graphics.getWidth()/2;
        //Make it go right in a row
        flX += 14 * x;
        //Make each new row start a little more to the left
        flX -= 12 * y;
        //Start out in the center
        flY = Gdx.graphics.getHeight()/2;
        //Make it go down in a row
        flY -= 7 * x;
        //Make each new row a little more down
        flY -= 6 * y;

        if (friendly) {
            flX -= 39;
            flY -= 20;
        }
        sb.draw(img,  flX,  flY);
    }

    public void drawObject(SpriteBatch sb, boolean friendly) {
        if (heldObject == null) {
            return;
        }
        float flX, flY;
        //Start out in the center
        flX = Gdx.graphics.getWidth()/2;
        //Make it go right in a row
        flX += 14 * x;
        //Make each new row start a little more to the left
        flX -= 12 * y;
        //Start out in the center
        flY = Gdx.graphics.getHeight()/2;
        //Make it go down in a row
        flY -= 7 * x;
        //Make each new row a little more down
        flY -= 6 * y;

        if (friendly) {
            flX -= 39;
            flY -= 20;
        }
        sb.draw(heldObject.img, flX + heldObject.offsetX,  flY + heldObject.offsetY);
    }
}