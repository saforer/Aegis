package com.aegis.actions;

import java.util.ArrayList;
import java.util.List;

import com.aegis.game.Board;
import com.aegis.game.BoardObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class SelectCharAction extends Action {
	Board board;
	int iterator;
	boolean created = false;
	List<BoardObject> playersList = new ArrayList<BoardObject>();
	public SelectCharAction(Board board) {
		this.board = board;
	}
	
	@Override
	public void create() {
		iterator = 0;
		playersList = board.getPlayers();
		created = true;
	}

	@Override
	public void update() {
		if (created) {
			//Say selected one is not selected anymore
			playersList.get(iterator).parentTile.isSelected = false;
			playersList.get(iterator).parentTile.updateTexture();	
			
			//Take Input
			if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				if (iterator > 0) iterator--;
			}
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				if (iterator < playersList.size()-1) iterator++;
			}
			
			//Select current one
			playersList.get(iterator).parentTile.isSelected = true;
			playersList.get(iterator).parentTile.updateTexture();
		}
		
	}

	@Override
	public void unload() {
		//Say selected one is not selected anymore
		playersList.get(iterator).parentTile.isSelected = false;
		playersList.get(iterator).parentTile.updateTexture();
	}

}
