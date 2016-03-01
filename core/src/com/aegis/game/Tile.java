package com.aegis.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile {
	public Texture tileImg;
	Object heldObject;
	
	public Tile () {
		tileImg = new Texture("good-tile.png");
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
