package com.fatalitiii.utilfatal.gui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

/**
 * @author Fatalitiii
 * @Name guiMenu.java
 * @Comment Gui Menu
 */
public class guiMenu {

	private List<guiMenuComponent> menuComponent = new ArrayList<>();
	private int xPos, yPos;
	private int height, width;
	private int selectColour;

	private int selected = 0;
	private float scroll;
	private guiSliderButton slider;
	private static int slots;
	private int scrollOffset;
	private float scale = 1F;

	/**
	 * @param xPosition
	 *            X Position
	 * @param yPosition
	 *            Y Position
	 * @param width
	 *            Width of the menu
	 * @param height
	 *            Height of the menu. (Must be multiple of 11)
	 * @param scale
	 *            Text scale for the menu
	 */
	@SuppressWarnings("static-access")
	public guiMenu(int xPosition, int yPosition, int width, int height, float scale) {
		this.xPos = xPosition;
		this.yPos = yPosition;
		this.height = height;
		this.width = width;
		this.slots = (Math.round(height / 11));
		this.selectColour = 0026163;
		if (scale <= 1) {
			this.scale = scale;
		}
	}

	/**
	 * @param xPosition
	 *            X Position
	 * @param yPosition
	 *            Y Position
	 * @param width
	 *            Width of the menu
	 * @param height
	 *            Height of the menu. (Must be multiple of 11)
	 * @param scale
	 *            Text scale for the menu
	 * @param selectColour
	 *            Set the select colour based 00000000
	 */
	@SuppressWarnings("static-access")
	public guiMenu(int xPosition, int yPosition, int width, int height, float scale, int selectColour) {
		this.xPos = xPosition;
		this.yPos = yPosition;
		this.height = height;
		this.width = width;
		this.slots = (Math.round(height / 11));
		this.selectColour = selectColour;
		if (scale <= 1) {
			this.scale = scale;
		}
	}

	/**
	 * @Description add new Component to the menu
	 * @param obj
	 *            new Object[]{"Hi %a", 'a', Items.apple}
	 */
	public void addComponent(Object... obj) {
		this.menuComponent.add(new guiMenuComponent(menuComponent.size(), obj));
	}

	/**
	 * @Description remove given Component from the menu
	 * @param ID
	 *            the position of where it is in the menu. E.g 5th one would be
	 *            4.
	 */
	public void removeComponent(int ID) {
		for (int i = ID + 1; i < this.menuComponent.size(); i++) {
			this.menuComponent.get(i).updateID(i - 1);
		}
		this.menuComponent.remove(ID);
	}

	/**
	 * @Description edit the value of a menu component
	 * @param ID
	 *            the position of where it is in the menu. E.g 5th one would be
	 *            4.
	 * @param obj
	 *            new Object[]{"Hi %a", 'a', Items.apple}
	 */
	public void editValue(int ID, Object... obj) {
		guiMenuComponent getComponent = ((guiMenuComponent) this.menuComponent.get(ID));
		getComponent.setValue(obj);
	}

	/**
	 * @param the
	 *            position of where it is in the menu. E.g 5th one would be 4.
	 * @return object array Object[]{"Hi %a", 'a', Items.apple}
	 */
	public Object[] getValue(int ID) {
		guiMenuComponent getComponent = ((guiMenuComponent) this.menuComponent.get(ID));
		return getComponent.getData();
	}

	public int getComponentAsID(int ID) {
		guiMenuComponent getComponent = ((guiMenuComponent) this.menuComponent.get(ID));
		return getComponent.getID();
	}

	/**
	 * @param ID
	 *            Button ID
	 * @return returns a GuiButton that needs to be added to buttonlist in a
	 *         gui.
	 */
	public GuiButton addButton(int ID) {
		return this.slider = new guiSliderButton(ID, xPos + width + 3, yPos, height);
	}

	/**
	 * @Description update the slider and scroll values
	 */
	public void update() {
		if (slider != null) {
			this.scroll = slider.getSlider();
		}
	}

	/**
	 * @Description render the menu
	 * @param gui
	 *            the GuiContainer you are calling the method from. E.g this
	 */
	public void render(GuiContainer gui) {
		guiElement.addElement(xPos + width + 5, yPos, 51, 0, 2, height, "", 1F,
				new ResourceLocation("utilfatal:textures/gui/gui.png"), gui);

		int maxShow = slots;
		if (menuComponent.size() < maxShow) {
			maxShow = menuComponent.size();
			scrollOffset = 0;
		} else {
			scrollOffset = (int) ((menuComponent.size() - maxShow) * ((scroll) / (height - 9)));
		}
		int lastValue = slots + scrollOffset;
		int yOffset = 0;

		for (int k = scrollOffset; k < lastValue; k++) {
			if (k <= menuComponent.size() - 1) {
				Object[] text = this.getValue(k);
				if (k == selected) {
					guiElement.addElement(xPos, yPos + (yOffset * 11), 0, 0, 39, 11, "", scale,
							new ResourceLocation("utilfatal:textures/gui/gui.png"), selectColour, gui);
					guiElement.addTextWithItem(getCenter((String) text[0]), yPos + (yOffset * 11) + 2 * (1 / scale),
							scale, text);
				} else {
					guiElement.addElement(xPos, yPos + (yOffset * 11), 0, 0, 39, 11, "", scale,
							new ResourceLocation("utilfatal:textures/gui/gui.png"), gui);
					guiElement.addTextWithItem(getCenter((String) text[0]), yPos + (yOffset * 11) + 2 * (1 / scale),
							scale, text);
				}

			} else {
				guiElement.addElement(xPos, yPos + (yOffset * 11), 0, 11, 39, 11, "", 0.5F,
						new ResourceLocation("utilfatal:textures/gui/gui.png"), gui);
			}
			yOffset++;
		}
		if (maxShow != slots)
			guiElement.addElement(xPos, yPos + (yOffset * 11) - 1, 0, 22, 39, 1, "", 0.5F,
					new ResourceLocation("utilfatal:textures/gui/gui.png"), gui);
	}

	private float getCenter(String string) {
		int xOffset = 0;
		char[] str = string.toCharArray();
		Character[] charArray = ArrayUtils.toObject(str);

		for (int j = 0; j < string.length(); j++) {
			if (charArray[j].equals('%')) {
				xOffset += 5;
			} else {
				xOffset++;
			}
		}
		return xPos + ((width - xOffset) / 2.5F) - (string.length() / (1 - (scale * 2F) + 3F));
	}

	/**
	 * @Description Call on mouse click to get what menu component has been
	 *              clicked
	 * @param x
	 *            Mouse X Position
	 * @param y
	 *            Mouse Y Position
	 */
	public void mouseClicked(int x, int y) {
		if (x >= xPos && x <= xPos + width - 2 && y >= yPos && y <= yPos + height) {
			int select = ((y - yPos) / 11) + scrollOffset;
			if (menuComponent.size() > select) {
				this.setSelected(select);
			}
		}
	}

	/**
	 * @return object array of the selected component Object[]{"Hi %a", 'a',
	 *         Items.apple}
	 */
	public Object[] getSelected() {
		return this.getValue(selected);
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}
	
	public int getSize(){
		return menuComponent.size();
	}
	
}
