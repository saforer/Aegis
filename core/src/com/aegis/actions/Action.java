package com.aegis.actions;

public abstract class Action {
	public abstract void startAction();
	public abstract void targetUpdate();
	public abstract boolean doneTargetting();
	public abstract void doAction();
	public abstract void cleanup();
}
