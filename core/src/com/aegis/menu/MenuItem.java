package com.aegis.menu;

import com.aegis.actions.ActionEnum;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

enum menu {
    move,
    fight,
    magic,
    defend,
    wait,
    fire
}

enum menuState {
    menu,
    action
}

public class MenuItem {
    menuState mst;
    Texture icon;
    Texture personalIcon;
    static Texture baseTexture;
    static Texture selectedTexture;
    boolean selected;
    MenuList parent;
    MenuList child;
    ActionEnum action;

    public MenuItem (menu m) {
        fillStatic();
        updateIcon();
        getItem(m);
        mst = menuState.action;
    }

    public boolean isMenu() {
        if (mst == menuState.menu) {
            if (child != null) {
                if (child.menuOptions.size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAction() {
        if (mst == menuState.action) {
            return true;
        }
        return false;
    }

    public void doAction() {
        MenuManager.currentMenu = null;
    }

    public void makeMenu() {
        child = new MenuList(parent);
        mst = menuState.menu;
    }

    public void addMenuItem(MenuItem subT) {
        child.addMenuItem(subT);
    }

    void getItem(menu m) {
        switch (m) {
            default:
            case move:
                personalIcon = new Texture("move-overlay.png");
                action = ActionEnum.move;
                break;
            case fight:
                personalIcon = new Texture("attack-overlay.png");
                break;
            case magic:
                personalIcon = new Texture("magic-overlay.png");
                break;
            case defend:
                personalIcon = new Texture("defend-overlay.png");
                break;
            case wait:
                personalIcon = new Texture("waitAction.png");
                break;
            case fire:
                personalIcon = new Texture("fireAction.png");
                action = ActionEnum.fart;
                break;
        }
    }

    void updateIcon() {
        if (selected) {
            icon = selectedTexture;
        } else {
            icon = baseTexture;
        }
    }

    void fillStatic() {
        if (baseTexture == null) {
            baseTexture = new Texture("base-icon-unselected.png");
            selectedTexture = new Texture("base-icon-selected.png");
        }
    }

    public void render(SpriteBatch sb, int x, int y) {
        sb.draw(icon, x * 32, y * 32);
        sb.draw(personalIcon, x * 32, y * 32);
    }

    public void select() {
        selected = true;
        updateIcon();
    }

    public void unSelect() {
        selected = false;
        updateIcon();
    }
}