package com.aegis.actions;

/**
 * Created by Forer on 3/15/2016.
 */
public class ActionFactory {
    static ActionFactory i;

    public static ActionFactory getI() {
        if (i == null) { i = new ActionFactory(); }
        return i;
    }

    public static Action getActionFromEnum(ActionEnum a) {
        switch (a) {
            default:
            case fart:
            return new Fart();
            case move:
                return new MoveAction();
        }
    }
}


