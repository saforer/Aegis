package com.aegis.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuItem {
	Texture icon;
	static Texture base;
	static Texture selected;
	boolean selectedBool;
	
	public MenuItem() {
		if (base == null) {
			base = new Texture("baseAction.png");
			selected = new Texture("selectedAction.png");
		}
		
		selectedBool = false;
		icon = base;
	}
		
	public void render(SpriteBatch sb, int x, int y) {
		sb.draw(icon, x * 32, y);
	}
	
	public void select() {
		selectedBool = true;
		icon = selected;
	}
	
	public void deselect() {
		selectedBool = false;
		icon = base;
	}
	
	public void input() {
		
	}
	
	public void action() {
		
	}
}
