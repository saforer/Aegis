package com.aegis.game;

import java.util.ArrayList;
import java.util.List;

import com.aegis.actions.Action;
import com.aegis.actions.MoveCharAction;
import com.aegis.actions.SelectCharAction;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

enum enumState {
	selectingCharacter,
	movingCharacter
}

public class Board {
	Tile[][] board;
	int width;
	int height;
	Vector2 boardPos;
	boolean isControllable;
	boolean selecting;
	int selectedTileX;
	int selectedTileY;
	boolean highlighting;
	int highlightedX;
	int highlightedY;
	Action currentAction;
	enumState state;
	enumState prevState;
	
	public Board (int x, int y, Vector2 pos, boolean isControllable) {
		width = x;
		height = y;
		boardPos = pos;
		board = new Tile[x][y];
		this.isControllable = isControllable;
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				board[i][j] = new Tile(i,j);
			}
		}

	}
	
	public void create() {
		if (isControllable) {
			state = enumState.selectingCharacter;
			prevState = enumState.selectingCharacter;
			currentAction = new SelectCharAction(this);
			currentAction.create();
		}
	}
	

	public void add (BoardObject obj, int y, int x) {
		board[x][y].heldObject = obj;
		obj.parentTile = board[x][y];
	}
	
	public void draw (SpriteBatch sb) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				board[x][y].updateTexture();
				board[x][y].draw(sb, boardPos);
			}
		}
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (board[x][y].heldObject != null) board[x][y].drawObject(sb, boardPos);
			}
		}
		
		if (currentAction != null) {
			currentAction.render(sb);
		}
		
	}
	
	public void update() {
		if (currentAction != null) {
			if (prevState != state) {
				//Deload old state
				switch (prevState) {
				default:
					currentAction.unload();
					break;			
				}
			
				
				//Load new state
				switch (state) {
				default:
					state = enumState.selectingCharacter;
					break;
				case selectingCharacter:
					currentAction = new SelectCharAction(this);
					currentAction.create();
					break;
				case movingCharacter:
					currentAction = new MoveCharAction(this);
					currentAction.create();
					break;
				}
			}
			
			//Update for the states
			currentAction.update();
		}
	}
	
	public List<BoardObject> getPlayers() {
		List<BoardObject> output = new ArrayList<BoardObject>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
					if (board[i][j] != null) {
						if (board[i][j].heldObject != null) {
							output.add(board[i][j].heldObject);
						}
					}
					
				}
			}
		return output;
	}
	
	
	
	void moveUpdate() {
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
