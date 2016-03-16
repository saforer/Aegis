package com.aegis.game;

import com.badlogic.gdx.graphics.Texture;

enum job {
    fighter,
    mage,
    redmage,
    defender,
    malboro
}

public class BoardObject {
    public boolean exhausted = false;
    Texture img;
    Texture[] imgs;
    int offsetX;
    int offsetY;
    int frame;
    float count;
    Tile parent;
    public String name;


    public BoardObject(job anim) {
        getImgs(anim);
        img = imgs[0];
    }

    void getImgs(job anim) {
        String[] whatTexturesToUse;
        switch(anim) {
            default:
            case fighter:
                whatTexturesToUse = new String[]{"fighter1.png", "fighter2.png", "fighter3.png", "fighter4.png"};
                offsetX = 6;
                offsetY = 6;
                name = "fighter";
                break;
            case mage:
                whatTexturesToUse = new String[]{"mage1.png", "mage2.png", "mage3.png", "mage4.png"};
                offsetX = 6;
                offsetY = 6;
                name = "mage";
                break;
            case redmage:
                whatTexturesToUse = new String[]{"red1.png", "red2.png", "red3.png", "red4.png"};
                offsetX = 6;
                offsetY = 6;
                name = "redMage";
                break;
            case defender:
                whatTexturesToUse = new String[]{"defender1.png","defender2.png","defender3.png","defender4.png"};
                offsetX = 6;
                offsetY = 6;
                name = "defender";
                break;
            case malboro:
                whatTexturesToUse = new String[]{"malboro1.png","malboro2.png","malboro3.png","malboro4.png"};
                offsetX = 0;
                offsetY = 0;
                name = "malboro";
                break;
        }
        imgs = new Texture[whatTexturesToUse.length];
        for (int i = 0; i < whatTexturesToUse.length; i++) {
            imgs[i] = new Texture(whatTexturesToUse[i]);
        }
    }

    public void update (float dt) {
        if (count > .12f) {
            frame++;
            if (frame > imgs.length-1) frame = 0;
            img = imgs[frame];
            count = 0;
        } else {
            count+= dt;
        }
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }

    public Tile getParent() {
        return parent;
    }
}