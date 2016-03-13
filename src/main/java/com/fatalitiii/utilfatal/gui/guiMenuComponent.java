package com.fatalitiii.utilfatal.gui;

/**
 * @author Fatalitiii
 * @Name guiMenuComponent.java
 * @Comment Holds the information for the menu components
 */
public class guiMenuComponent {

	private int ID;
	private Object[] data;

	public guiMenuComponent(int ID, Object... obj) {
		this.ID = ID;
		this.data = obj;
	}

	public Object[] getData() {
		return this.data;
	}
	
	public int getID(){
		return this.ID;
	}

	public void setValue(Object... obj) {
		this.data = obj;
	}

	public void updateID(int newID) {
		this.ID = newID;
	}

}
