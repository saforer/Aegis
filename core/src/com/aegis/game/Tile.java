package com.aegis.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile {
	//TODO: (Forer, 3/1/2016: Make this damn code ROW COLUMN not column row)
	public Texture tileImg;
	boolean isSelected;
	public boolean highlightingTile;
	static Texture emptyTile;
	static Texture selected;
	static Texture highlighted;
	public Object heldObject;
	
	
	public Tile () {
			if (emptyTile == null) {
				emptyTile = new Texture("good-tile.png");
				selected = new Texture("selected.png");
				highlighted = new Texture("selected2.png");
			}
			
			updateTexture();
	}
	
	Texture getTexture() {
		if (isSelected) return selected;
		if (highlightingTile) return highlighted;
		return emptyTile;
	}
	
	void updateTexture() {
		tileImg = getTexture();
	}
	
	public void draw (SpriteBatch sb, Vector2 boardStart, int arrayX, int arrayY) {
		float x, y;
		//Start out in the center
		x = Gdx.graphics.getWidth() * boardStart.x;
		//Make it go right in a row
		x += (14 * 2) * arrayX;
		//Make each new row start a little more to the left
		x -= (12 * 2) * arrayY;
		//Start out in the center
		y = Gdx.graphics.getHeight() * boardStart.y;
		//Make it go down in a row
		y -= (7 * 2) * arrayX;
		//Make each new row a little more down
		y -= (6 * 2) * arrayY;
		sb.draw(tileImg,  x,  y, tileImg.getWidth() * 2, tileImg.getHeight() * 2);

	}
	
	public void drawObject (SpriteBatch sb, Vector2 boardStart, int arrayX, int arrayY) {
		float x, y;
		//Start out in the center
		x = Gdx.graphics.getWidth() * boardStart.x;
		//Make it go right in a row
		x += (14 * 2) * arrayX;
		//Make each new row start a little more to the left
		x -= (12 * 2) * arrayY;
		//Start out in the center
		y = Gdx.graphics.getHeight() * boardStart.y;
		//Make it go down in a row
		y -= (7 * 2) * arrayX;
		//Make each new row a little more down
		y -= (6 * 2) * arrayY;
		if (heldObject != null) heldObject.draw(sb, x, y);
	}
}
