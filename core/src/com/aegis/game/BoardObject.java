package com.aegis.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BoardObject {
	Texture objImg;
	Vector2 offset;
	public Tile parentTile;
	
	public BoardObject(String str, Vector2 offset) {
		objImg = new Texture(str);
		this.offset = offset;
	}
	
	public void draw (SpriteBatch sb, float x, float y) {
		sb.draw(objImg, x + offset.x, y + offset.y, objImg.getWidth() * 2, objImg.getHeight() * 2);
	}
	
	public Tile getParentTile() {
		return parentTile;
	}
}
