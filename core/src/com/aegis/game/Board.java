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



public class Board {
	public Tile[][] board;
	int width;
	int height;
	Vector2 boardPos;
	boolean isControllable;
	Action currentAction;
	public static int selectedPlayerX;
	public static int selectedPlayerY;
	public static EnumState state;
	EnumState prevState;
	
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
			state = EnumState.selectingCharacter;
			prevState = EnumState.selectingCharacter;
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
					state = EnumState.selectingCharacter;
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
				
				prevState = state;
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
							System.out.println(" Found player at " + i + " " + j);
						}
					}
					
				}
			}
		return output;
	}
	
	public static void goToMove(int X, int Y) {
		selectedPlayerX = X;
		selectedPlayerY = Y;
		state = EnumState.movingCharacter;
	}
	
	
	public boolean isInArray(int x, int y) {
		if (x >= width) return false;
		if (y >= height) return false;
		if (x < 0) return false;
		if (y < 0) return false;
		return true;
	}
}
