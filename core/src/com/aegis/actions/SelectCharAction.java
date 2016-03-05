package com.aegis.actions;

import java.util.List;

import com.aegis.game.Board;
import com.aegis.game.BoardObject;
import com.aegis.menu.MenuList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SelectCharAction extends Action {
	Board board;
	int playerIterator = 0;
	MenuList menu;
	List<BoardObject> players;
	public SelectCharAction(Board board) {
		this.board = board;
	}
	
	@Override
	public void create() {
		players = board.getPlayers();
		players.get(0).parentTile.isSelected = true;
		players.get(0).parentTile.updateTexture();

	}

	@Override
	public void update() {
		if (menu == null) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) playerLeft();
			if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) playerRight();
			if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) menuMake();
		} else {
			if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) menu.moveUp();
			if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) menu.moveDown();
			if (Gdx.input.isKeyJustPressed(Input.Keys.Z) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				if (menu.selectedOptionIsMenu()) {
					if (menu.canGoRight()) {
						menu = menu.goRight();
					}
				} else {
					menu.doThing();
				}
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.X) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				if (menu.canGoLeft()) {
					menu = menu.goLeft();
				} else {
					menu = null;
				}
			}
		}
	}
	
	void playerLeft() {
		if (playerIterator > 0) {
			players.get(playerIterator).parentTile.isSelected = false;
			players.get(playerIterator).parentTile.updateTexture();
			playerIterator--;
			players.get(playerIterator).parentTile.isSelected = true;
			players.get(playerIterator).parentTile.updateTexture();
		}
	}
	
	void playerRight() {
		if (playerIterator < players.size()-1) {
			players.get(playerIterator).parentTile.isSelected = false;
			players.get(playerIterator).parentTile.updateTexture();
			playerIterator++;
			players.get(playerIterator).parentTile.isSelected = true;
			players.get(playerIterator).parentTile.updateTexture();
		}
	}
	
	void menuMake() {
		menu = new MenuList();
	}

	@Override
	public void unload() {
		Board.selectedPlayerX = players.get(playerIterator).getParentTile().x;
		Board.selectedPlayerY = players.get(playerIterator).getParentTile().y;
	}
	
	@Override
	public void render(SpriteBatch sb) {
		if (menu!=null) {
			menu.draw(sb);
		}
	}

}
