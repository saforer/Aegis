package com.aegis.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Board {
	Tile[][] board;
	int width;
	int height;
	Vector2 boardPos;
	
	public Board (int x, int y, Vector2 pos) {
		width = x;
		height = y;
		boardPos = pos;
		board = new Tile[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				board[i][j] = new Tile();
			}
		}
	}
	

	public void add (Object obj, int x, int y) {
		board[x][y].heldObject = obj;
	}
	
	public void draw (SpriteBatch sb) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				board[x][y].draw(sb, boardPos, x, y);
			}
		}
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (board[x][y].heldObject != null) board[x][y].drawObject(sb, boardPos, x, y);
			}
		}
	}
}
