package com.aegis.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuList {
	public MenuList parent;
	int menuIterator = 0;
	List<MenuItem> menuOptions = new ArrayList<MenuItem>();
	
	public MenuList () {
		this.parent = null;
		
		fillList();
	}
	
	public MenuList (MenuList parent) {
		this.parent = parent;
	}
	
	void fillList() {
		TempItem t;
		t = new TempItem(this, "moveAction.png");
		addMenuOption(t);		
		t.select();
		t = new TempItem(this, "magicAction.png");
		addMenuOption(t);
		t.childMenu = new MenuList(this);
		t.type = ButtonType.menuHolder;
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "fireAction.png"));
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "iceAction.png"));
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "lightningAction.png"));
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "poisonAction.png"));
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "airAction.png"));
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "earthAction.png"));
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "atomAction.png"));
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "undeadAction.png"));
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "lightAction.png"));
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "darkAction.png"));
		t.childMenu.menuOptions.get(0).select();
		
		
		t = new TempItem(this, "fightAction.png");
		addMenuOption(t);
		t.childMenu = new MenuList(this);
		t.type = ButtonType.menuHolder;
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "fistAction.png"));
		MenuItem tempItem = t.childMenu.menuOptions.get(0);
		tempItem.select();
		tempItem.type = ButtonType.menuHolder;
		tempItem.childMenu = new MenuList(t.childMenu);
		tempItem.childMenu.addMenuOption(new TempItem(tempItem.childMenu, "punchGut.png"));
		tempItem.childMenu.addMenuOption(new TempItem(tempItem.childMenu, "punchThrow.png"));
		tempItem.childMenu.menuOptions.get(0).select();
				
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "kickAction.png"));
		tempItem = t.childMenu.menuOptions.get(1);
		tempItem.type = ButtonType.menuHolder;
		tempItem.childMenu = new MenuList(t.childMenu);
		tempItem.childMenu.addMenuOption(new TempItem(tempItem.childMenu, "lowKick.png"));
		tempItem.childMenu.addMenuOption(new TempItem(tempItem.childMenu, "kickFace.png"));
		tempItem.childMenu.menuOptions.get(0).select();
		
		t.childMenu.addMenuOption(new TempItem(t.childMenu, "trapAction.png"));
		tempItem = t.childMenu.menuOptions.get(2);
		tempItem.type = ButtonType.menuHolder;
		tempItem.childMenu = new MenuList(t.childMenu);
		tempItem.childMenu.addMenuOption(new TempItem(tempItem.childMenu, "trapFireAction.png"));
		tempItem.childMenu.addMenuOption(new TempItem(tempItem.childMenu, "trapLightningAction.png"));
		tempItem.childMenu.menuOptions.get(0).select();
		
		t = new TempItem(this, "shieldAction.png");
		addMenuOption(t);
		t = new TempItem(this, "waitAction.png");
		addMenuOption(t);
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