package com.aegis.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Forer on 3/11/2016.
 */
public class MenuManager {
    static MenuManager i;
    static MenuList currentMenu;

    public static MenuManager getInstance() {
        if (i == null) i = new MenuManager();
        return i;
    }

    public static boolean isMenuOpen() {
        if (currentMenu == null) return false;
        return true;
    }

    public static void menuUp() {
        currentMenu.up();
    }

    public static void menuDown() {
        currentMenu.down();
    }

    public static boolean menuCanLeft() {
        return currentMenu.canLeft();
    }

    public static void menuLeft() {
        currentMenu = currentMenu.left();
    }

    public static boolean menuCanRight() {
        return currentMenu.canRight();
    }

    public static void menuRight() {
        if (currentMenu.canRight()) {
            if (currentMenu.onAction()) {
                currentMenu.doAction();
            } else {
                currentMenu = currentMenu.right();
            }
        }

    }

    public static int depth() {
        return currentMenu.depth();
    }

    public static void createMenu() {
        currentMenu = new MenuList();
    }

    public static void render(SpriteBatch sb) {
        if (currentMenu != null) currentMenu.render(sb);
    }
}
