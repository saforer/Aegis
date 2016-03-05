package com.aegis.actions;

import com.aegis.game.Board;
import com.aegis.game.EnumState;
import com.aegis.game.Tile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoveCharAction extends Action {
	Board masterBoard;
	Tile[][] board;
	int selectedTileX;
	int selectedTileY;
	boolean highlighting;
	int highlightedX;
	int highlightedY;
	
	public MoveCharAction(Board board) {
		this.masterBoard = board;
		this.board = masterBoard.board; 
	}

	@Override
	public void create() {
		System.out.println("CREATED");
		selectedTileX = Board.selectedPlayerX;
		selectedTileY = Board.selectedPlayerY;
		board[selectedTileX][selectedTileY].isSelected = true;
		board[selectedTileX][selectedTileY].updateTexture();
		highlighting = true;
		highlightedX = selectedTileX;
		highlightedY = selectedTileY;
		board[selectedTileX][selectedTileY].highlightingTile = true;
		board[selectedTileX][selectedTileY].updateTexture();
	}

	@Override
	public void update() {
		//Left
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && masterBoard.isInArray(selectedTileX-1,selectedTileY)) {
			board[selectedTileX][selectedTileY].isSelected = false;
			board[selectedTileX][selectedTileY].updateTexture();
			selectedTileX--;
			board[selectedTileX][selectedTileY].isSelected = true;
			board[selectedTileX][selectedTileY].updateTexture();
		}
		
		//Up
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && masterBoard.isInArray(selectedTileX,selectedTileY-1)) {
			board[selectedTileX][selectedTileY].isSelected = false;
			board[selectedTileX][selectedTileY].updateTexture();
			selectedTileY--;
			board[selectedTileX][selectedTileY].isSelected = true;
			board[selectedTileX][selectedTileY].updateTexture();
		}
		
		//Right
		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && masterBoard.isInArray(selectedTileX+1,selectedTileY)) {
			board[selectedTileX][selectedTileY].isSelected = false;
			board[selectedTileX][selectedTileY].updateTexture();
			selectedTileX++;
			board[selectedTileX][selectedTileY].isSelected = true;
			board[selectedTileX][selectedTileY].updateTexture();
		}
		
		//Down
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && masterBoard.isInArray(selectedTileX,selectedTileY+1)) {
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
				System.out.println("DID THE THING");
				to.heldObject = from.heldObject;
				from.heldObject = null;
				to.heldObject.parentTile = to;
			}
			
			to.isSelected = false;
			from.isSelected = false;
			to.highlightingTile = false;
			from.highlightingTile = false;
			to.updateTexture();
			from.updateTexture();
			masterBoard.state = EnumState.selectingCharacter;
		}
	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
	}

}
