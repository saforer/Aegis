package com.aegis.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TempItem extends MenuItem {
	Texture layeredIcon;
	public TempItem(MenuList parent, String str) {
		super("Move", parent);
		type = ButtonType.actionHolder;
		
		layeredIcon = new Texture(str);
	}
	
	@Override
	public void draw(SpriteBatch sb, int x, int y) {
		super.draw(sb, x, y);
		sb.draw(layeredIcon, x * icon.getWidth(), y * icon.getHeight());
	}
}
