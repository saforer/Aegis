package com.aegis.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile {
	//TODO: (Forer, 3/1/2016: Make this damn code ROW COLUMN not column row)
	public Texture tileImg;
	public boolean isSelected;
	public boolean highlightingTile;
	static Texture emptyTile;
	static Texture selected;
	static Texture highlighted;
	public BoardObject heldObject;
	public int x;
	public int y;
	
	public Tile (int x, int y) {
			if (emptyTile == null) {
				emptyTile = new Texture("good-tile.png");
				selected = new Texture("selected.png");
				highlighted = new Texture("selected2.png");
			}
			this.x = x;
			this.y = y;			
			updateTexture();
	}
	
	Texture getTexture() {
		if (isSelected) return selected;
		if (highlightingTile) return highlighted;
		return emptyTile;
	}
	
	public void updateTexture() {
		tileImg = getTexture();
	}
	
	public void draw (SpriteBatch sb, Vector2 boardStart) {
		float flX, flY;
		//Start out in the center
		flX = Gdx.graphics.getWidth() * boardStart.x;
		//Make it go right in a row
		flX += (14 * 2) * x;
		//Make each new row start a little more to the left
		flX -= (12 * 2) * y;
		//Start out in the center
		flY = Gdx.graphics.getHeight() * boardStart.y;
		//Make it go down in a row
		flY -= (7 * 2) * x;
		//Make each new row a little more down
		flY -= (6 * 2) * y;
		sb.draw(tileImg,  flX,  flY, tileImg.getWidth() * 2, tileImg.getHeight() * 2);

	}
	
	public void drawObject (SpriteBatch sb, Vector2 boardStart) {
		float flX, flY;
		//Start out in the center
		flX = Gdx.graphics.getWidth() * boardStart.x;
		//Make it go right in a row
		flX += (14 * 2) * x;
		//Make each new row start a little more to the left
		flX -= (12 * 2) * y;
		//Start out in the center
		flY = Gdx.graphics.getHeight() * boardStart.y;
		//Make it go down in a row
		flY -= (7 * 2) * x;
		//Make each new row a little more down
		flY -= (6 * 2) * y;
		if (heldObject != null) heldObject.draw(sb, flX, flY);
	}
}
