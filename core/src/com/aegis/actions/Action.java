package com.aegis.actions;

public abstract class Action {
	public boolean[][] shapeOfTarget;
	public int focalX, focalY;

	public abstract void targetUpdate();
	public abstract boolean doneTargetting();
	public abstract void doAction();
	public abstract void cleanup();
}
