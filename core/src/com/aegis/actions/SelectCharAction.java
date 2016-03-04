package com.aegis.actions;

import java.util.ArrayList;
import java.util.List;

import com.aegis.game.Board;
import com.aegis.game.BoardObject;
import com.aegis.game.Tile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SelectCharAction extends Action {
	Board board;
	int iterator;
	boolean created = false;
	boolean menuOpen = false;
	int menuIterator;
	Texture actionItem;
	Texture selectedItem;
	List<BoardObject> playersList = new ArrayList<BoardObject>();
	public SelectCharAction(Board board) {
		this.board = board;
	}
	
	@Override
	public void create() {
		iterator = 0;
		playersList = board.getPlayers();
		created = true;
		actionItem = new Texture("baseAction.png");
		selectedItem = new Texture("selectedAction.png");
	}

	@Override
	public void update() {
		if (created) {
			//Say selected one is not selected anymore
			playersList.get(iterator).parentTile.isSelected = false;
			playersList.get(iterator).parentTile.updateTexture();	
			
			//Take Input
			if (!menuOpen) {
				if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
					if (iterator > 0) iterator--;
				}
				
				if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
					if (iterator < playersList.size()-1) iterator++;
				}
				
				if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
					//Open the menu if it's not open
					menuOpen = true;
				}
			} else {
				//Menu is open, navagate the menu
				if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
					if (menuIterator > 0) menuIterator--;
				}
				
				if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
					if (menuIterator < 4) menuIterator++;
				}
				
				if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
					//Open the menu if it's not open
					menuOpen = false;
					
				}
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
	
	@Override
	public void render(SpriteBatch sb) {
		if (menuOpen) {
			for (int i = 0; i < 5; i++) {
				if (i == menuIterator) {
					sb.draw(selectedItem, 32 * i, 0);
				} else {
					sb.draw(actionItem, 32 * i, 0);
				}				
			}
		}
	}

}
