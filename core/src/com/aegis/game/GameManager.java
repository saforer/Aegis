package com.aegis.game;

import com.aegis.menu.MenuManager;
import com.aegis.states.StateManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Forer on 3/11/2016.
 */
public class GameManager {
    static GameManager i;
    StateManager sm;
    BoardManager bm;
    MenuManager mm;
    public static GameManager getInstance() {
        if (i==null) i = new GameManager();
        return i;
    }

    public GameManager() {

        bm = BoardManager.getInstance();
        mm = MenuManager.getInstance();

        bm.addBoard(new Board(3,3), true);
        bm.addBoard(new Board(3,3), false);

        bm.addObject(new BoardObject(job.fighter), true, 0, 1, 0);
        bm.addObject(new BoardObject(job.mage), true, 2, 2, 0);
        bm.addObject(new BoardObject(job.redmage), true, 2, 0, 0);
        bm.addObject(new BoardObject(job.defender), false, 2, 0, 0);
        bm.addObject(new BoardObject(job.defender), false, 2, 2, 0);
        bm.addObject(new BoardObject(job.malboro), false, 1, 1, 0);

        sm = StateManager.getInstance();
    }

    public void update (float dt){
        sm.update(dt);
        bm.update(dt);
    }

    public void render (SpriteBatch sb) {
        sm.render(sb);
        bm.drawBoards(sb);
        mm.render(sb);
    }
}
