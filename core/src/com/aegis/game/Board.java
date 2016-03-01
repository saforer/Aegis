package com.aegis.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Board {
	Tile[][] board;
	int width;
	int height;
	Vector2 boardPos;
	boolean isControllable;
	int selectedTileX;
	int selectedTileY;
	boolean highlighting;
	int highlightedX;
	int highlightedY;
	
	public Board (int x, int y, Vector2 pos, boolean isControllable) {
		width = x;
		height = y;
		boardPos = pos;
		board = new Tile[x][y];
		this.isControllable = isControllable;
		selectedTileX = 2;
		selectedTileY = 2;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				board[i][j] = new Tile();
			}
		}
		if (isControllable) {
			board[selectedTileX][selectedTileY].isSelected = true;
			board[selectedTileX][selectedTileY].updateTexture();
		}
	}
	

	public void add (Object obj, int y, int x) {
		board[x][y].heldObject = obj;
	}
	
	public void draw (SpriteBatch sb) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				board[x][y].updateTexture();
				board[x][y].draw(sb, boardPos, x, y);
			}
		}
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (board[x][y].heldObject != null) board[x][y].drawObject(sb, boardPos, x, y);
			}
		}
	}
	
	public void update() {
		if (isControllable) {
			//Left
			if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && isInArray(selectedTileX-1,selectedTileY)) {
				board[selectedTileX][selectedTileY].isSelected = false;
				board[selectedTileX][selectedTileY].updateTexture();
				selectedTileX--;
				board[selectedTileX][selectedTileY].isSelected = true;
				board[selectedTileX][selectedTileY].updateTexture();
			}
			
			//Up
			if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && isInArray(selectedTileX,selectedTileY-1)) {
				board[selectedTileX][selectedTileY].isSelected = false;
				board[selectedTileX][selectedTileY].updateTexture();
				selectedTileY--;
				board[selectedTileX][selectedTileY].isSelected = true;
				board[selectedTileX][selectedTileY].updateTexture();
			}
			
			//Right
			if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && isInArray(selectedTileX+1,selectedTileY)) {
				board[selectedTileX][selectedTileY].isSelected = false;
				board[selectedTileX][selectedTileY].updateTexture();
				selectedTileX++;
				board[selectedTileX][selectedTileY].isSelected = true;
				board[selectedTileX][selectedTileY].updateTexture();
			}
			
			//Down
			if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && isInArray(selectedTileX,selectedTileY+1)) {
				board[selectedTileX][selectedTileY].isSelected = false;
				board[selectedTileX][selectedTileY].updateTexture();
				selectedTileY++;
				board[selectedTileX][selectedTileY].isSelected = true;
				board[selectedTileX][selectedTileY].updateTexture();
			}
			
			//X
			if (Gdx.input.isKeyJustPressed(Input.Keys.Z) && !highlighting) {
				//Highlighting
				highlighting = true;
				board[selectedTileX][selectedTileY].highlightingTile = true;
				board[selectedTileX][selectedTileY].updateTexture();
				highlightedX = selectedTileX;
				highlightedY = selectedTileY;
				
							
			} else if (Gdx.input.isKeyJustPressed(Input.Keys.Z) && highlighting) {
				//Unhighlighting (so just moving)
				highlighting = false;
				board[highlightedX][highlightedY].highlightingTile = false;
				board[highlightedX][highlightedY].updateTexture();
				
				Tile from = board[highlightedX][highlightedY];
				Tile to = board[selectedTileX][selectedTileY];
				
				/*				Null	Not Null
				 * Going FROM	No		Yes
				 * Going TO		Yes		No
				 * 
				 */
				
				if (from.heldObject != null && to.heldObject == null) {
					to.heldObject = from.heldObject;
					from.heldObject = null;
				}
			}
		}
	}
	
	boolean isInArray(int x, int y) {
		if (x >= width) return false;
		if (y >= height) return false;
		if (x < 0) return false;
		if (y < 0) return false;
		return true;
	}
}
