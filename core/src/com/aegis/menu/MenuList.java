package com.aegis.menu;

import com.aegis.actions.ActionEnum;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class MenuList {
    public MenuList parent;
    int menuIterator = 0;
    List<MenuItem> menuOptions = new ArrayList<MenuItem>();

    public MenuList() {
        parent = null;

        fillTempList();
        selectRoot();
    }

    public MenuList(MenuList parent) {
        this.parent = parent;
        selectRoot();
    }

    void fillTempList() {
        MenuItem t;
        MenuItem subT;

        t = new MenuItem(menu.move);
        menuOptions.add(t);

        t = new MenuItem(menu.fight);
        t.makeMenu();
        t.child.parent = this;

        subT = new MenuItem(menu.fire);
        t.addMenuItem(subT);

        t.child.selectRoot();

        menuOptions.add(t);

        t = new MenuItem(menu.magic);
        menuOptions.add(t);
        t = new MenuItem(menu.defend);
        menuOptions.add(t);
        t = new MenuItem(menu.wait);
        menuOptions.add(t);
    }


    public void render(SpriteBatch sb) {
        for (int i = 0; i < menuOptions.size(); i++) {
                menuOptions.get(i).render(sb, depth(), i);
        }
        if (depth() > 0) parent.render(sb);
    }

    public void down() {
        if (menuIterator > 0 ) {
            menuOptions.get(menuIterator).unSelect();
            menuIterator--;
            menuOptions.get(menuIterator).select();
        }
    }

    public void up() {
        if (menuIterator < menuOptions.size() -1) {
            menuOptions.get(menuIterator).unSelect();
            menuIterator++;
            menuOptions.get(menuIterator).select();
        }
    }

    public boolean canLeft() {
        if (parent != null) return true;
        return false;
    }

    public MenuList left() {
        return parent;
    }

    public boolean canRight() {
        boolean output = false;
        if (menuOptions.get(menuIterator).isMenu()) output = true;
        if (menuOptions.get(menuIterator).isAction()) output = true;
        return output;
    }

    public boolean onAction() {
        return menuOptions.get(menuIterator).isAction();
    }

    public void doAction(){
        menuOptions.get(menuIterator).doAction();
    }

    public MenuList right() {
        return menuOptions.get(menuIterator).child;
    }

    public ActionEnum getActionEnum() {
        return menuOptions.get(menuIterator).action;
    }

    public int depth() {
        if (parent == null) {
            return 0;
        } else {
            return parent.depth()+1;
        }
    }

    public void selectRoot() {
        if (menuOptions.size() > 0) menuOptions.get(0).select();
    }

    public void addMenuItem(MenuItem m) {
        menuOptions.add(m);
    }
}