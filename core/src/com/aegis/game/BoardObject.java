package com.aegis.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BoardObject {
	Texture objImg;
	Texture[] objImages;
	int animationFrame;
	float count;
	Vector2 offset;
	public Tile parentTile;
	
	public BoardObject(String str, Vector2 offset) {
		objImg = new Texture(str);		
		this.offset = offset;
	}
	
	public BoardObject(String[] str, Vector2 offset) {
		animationFrame = 0;
		count = 0f;
		objImages = new Texture[str.length];
		for (int i = 0; i < str.length; i++) {
			objImages[i] = new Texture(str[i]);
		}
		objImg = objImages[0];
				
		this.offset = offset;
	}
	
	public void draw (SpriteBatch sb, float x, float y, float dt) {
		if (objImages != null) {
			if (count >= .5f) {
				count = 0f;
				objImg = frameAdvance();
			} else {
				count += dt;
			}
		}
		
		sb.draw(objImg, x + offset.x, y + offset.y, objImg.getWidth() * 2, objImg.getHeight() * 2);
	}
	
	Texture frameAdvance() {
		animationFrame++;
		if (animationFrame > objImages.length-1) animationFrame = 0;
		return objImages[animationFrame];
	}
	
	public Tile getParentTile() {
		return parentTile;
	}
}
