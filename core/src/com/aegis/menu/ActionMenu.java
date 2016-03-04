package com.aegis.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ActionMenu {
	List<MenuItem> menuOptions = new ArrayList<MenuItem>();
	int menuIterator = 0;
	int menuNest = 0;
	
	public ActionMenu (List<MenuItem> menuItems) {
		menuOptions = menuItems;
		menuOptions.get(0).select();
	}
	
	public ActionMenu (List<MenuItem> menuItems, int i) {
		menuOptions = menuItems;
		menuOptions.get(0).select();
		menuNest = i;
	}
	
	public void updateMenu() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) menuUp();
		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) menuDown();
	}
	
	void menuUp() {
		if (menuIterator > 0) {
			menuOptions.get(menuIterator).deselect();
			menuIterator--;
			menuOptions.get(menuIterator).select();
		}
	}
	
	void menuDown() {
		if (menuIterator < menuOptions.size()-1) {
			menuOptions.get(menuIterator).deselect();
			menuIterator++;
			menuOptions.get(menuIterator).select();
		}
	}
	
	public void drawMenu(SpriteBatch sb) {
		int i = 0;
		for (MenuItem m : menuOptions) {
			m.render(sb, i++, menuNest);
		}
	}
}
