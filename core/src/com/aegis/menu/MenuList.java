package com.aegis.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuList {
	public String name;
	public MenuList parent;
	int menuIterator = 0;
	List<MenuItem> menuOptions = new ArrayList<MenuItem>();
	
	public MenuList (String name) {
		this.name = name;
		this.parent = null;
	}
	
	public MenuList (String name, MenuList parent) {
		this.name = name;
		this.parent = parent;
	}
	
	public int menuSize() {
		return menuOptions.size();
	}
	
	public void selectStart() {
		menuOptions.get(0).select();
	}
	
	public void addMenuOption(MenuItem mi) {
		menuOptions.add(mi);
	}
	
	public boolean parentExists() {
		if (parent != null) return true;
		return false;
	}
	
	public MenuList getParent() {
		return parent;
	}
	
	public void moveUp() {
		if (menuIterator < menuOptions.size()-1) {
			menuOptions.get(menuIterator).unSelect();
			menuIterator++;
			menuOptions.get(menuIterator).select();
		}
	}
	
	public void moveDown() {
		if (menuIterator > 0) {
			menuOptions.get(menuIterator).unSelect();
			menuIterator--;
			menuOptions.get(menuIterator).select();
		}
	}
	
	public boolean canGoRight() {
		System.out.println("GO RIGHT? Okay!!!");
		if (menuOptions.get(menuIterator).type == ButtonType.menuHolder) {
			
			System.out.println("It's a menu holder!");
			
			if (menuOptions.get(menuIterator).childMenu != null) {
				
				System.out.println("there's a child menu!");
				
				if (menuOptions.get(menuIterator).childMenu.menuSize() > 0) {
					
					System.out.println("Child menu has elements in it!");
					return true;
				}
			}
		}
		return false;
	}
	
	public MenuList goRight() {
		return menuOptions.get(menuIterator).childMenu;
	}
	
	public boolean canGoLeft() {
		if (parent != null) {
			return true;
		}
		return false;
	}
	
	public MenuList goLeft() {
		return parent;
	}
	
	int distFromRoot() {
		if (parent == null) {
			return 0;
		} else {
			return parent.distFromRoot() + 1;
		}
	}
	
	public void draw(SpriteBatch sb) {
		//Draw menu options
		for (int y = 0; y < menuOptions.size(); y++) {
			menuOptions.get(y).draw(sb, distFromRoot(), y);
		}
		
		//Draw parent menu if it exists
		if (parent != null) {
			parent.draw(sb);
		}
	}	
}