package com.aegis.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

enum ButtonType {
	menuHolder,
	actionHolder
}

public class MenuItem {
	Texture icon;
	static Texture baseTexture;
	static Texture selectTexture;
	public Runnable runThis;
	boolean active;
	String name;
	MenuList parent;
	MenuList childMenu;
	ButtonType type;
	
	public MenuItem (String name, MenuList parent, MenuList childMenu) {
		//See if statics are blank
		staticFill();
		
		this.name = name;
		this.childMenu = childMenu;
		type = ButtonType.menuHolder;
		this.parent = parent; 
		icon = baseTexture;
	}
	
	public MenuItem (String name, MenuList parent) {
		//See if statics are blank
		staticFill();
		
		this.name = name;
		type = ButtonType.menuHolder;
		icon = baseTexture;
		this.parent = parent;
	}
	
	void staticFill() {
		if (baseTexture == null) {
			baseTexture = new Texture("base-icon-unselected.png");
			selectTexture = new Texture("base-icon-selected.png");
		}
	}
	
	public boolean isMenu() {
		if (type == ButtonType.menuHolder && childMenu != null) {
			if (childMenu.menuSize() > 0) return true;
		}
		return false;
	}
	
	void doSomething() { 
		runThis.run();
	}
	
	public void select() {
		icon = selectTexture;
	}
	
	public void unSelect() {
		icon = baseTexture;
	}
	
	public void draw(SpriteBatch sb, int x, int y) {
		sb.draw(icon, x * icon.getWidth(), y * icon.getHeight());
	}
}
