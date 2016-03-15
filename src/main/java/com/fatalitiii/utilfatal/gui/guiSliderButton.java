package com.fatalitiii.utilfatal.gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.fatalitiii.utilfatal.utils.ModInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

/**
 * @author Fatalitiii
 * @Name guiSliderButton.java
 * @Comment #NowWithFreeSliders
 */
public class guiSliderButton extends GuiButton {

	protected ResourceLocation texture;
	protected int xOffset, yOffset;
	protected int width, height;
	private int xPosition, yPosition;
	private int textureX, textureY;
	private float sliderValue = 0F, maxValue;

	@SuppressWarnings("unused")
	private int id;
	@SuppressWarnings("unused")
	private boolean wasClicking, isScrolling;

	/**
	 * @param ID
	 *            Button ID
	 * @param x
	 *            X Position
	 * @param y
	 *            Y Position
	 * @param width
	 *            width of the texture
	 * @param height
	 *            height of the texture
	 * @param maxValue
	 *            the max value you want the slider to go to. Normally the hight
	 *            of your menu.
	 *
	 */
	public guiSliderButton(int ID, int x, int y, float maxValue) {
		super(ID, x, y, 6, 9, "");
		this.id = ID;
		this.xPosition = x;
		this.yPosition = y;
		this.width = 6;
		this.height = 9;
		this.texture = new ResourceLocation(ModInfo.MOD_ID + ":textures/gui/gui.png");
		this.textureX = 39;
		this.textureY = 0;
		this.maxValue = maxValue - height;
	}
	
	/**
	 * @param ID
	 *            Button ID
	 * @param x
	 *            X Position
	 * @param y
	 *            Y Position
	 * @param width
	 *            width of the texture
	 * @param height
	 *            height of the texture
	 * @param maxValue
	 *            the max value you want the slider to go to. Normally the hight
	 *            of your menu.
	 * @param resourceLocation
	 *            Resource location file
	 * @param textureX
	 *            texture x position
	 * @param textureY
	 *            texture y position       
	 */
	public guiSliderButton(int ID, int x, int y, int width, int height, float maxValue, ResourceLocation resourceLocation, int textureX, int textureY) {
		super(ID, x, y, width, height, "");
		this.id = ID;
		this.xPosition = x;
		this.yPosition = y;
		this.width = width;
		this.height = height;
		this.texture = resourceLocation;
		this.textureX = textureX;
		this.textureY = textureY;
		this.maxValue = maxValue - height;
	}

	protected int getHoverState(boolean flag) {
		return 0;
	}

	@Override
	protected void mouseDragged(Minecraft minecraft, int i, int j) {
		if (!this.visible) {
			return;
		}

		if (isScrolling) {
			sliderValue = (float) (j - (yPosition + 4)) / (float) (this.height - 8);

			if (sliderValue < 0.0F) {
				sliderValue = 0.0F;
			}

			if (sliderValue > maxValue) {
				sliderValue = maxValue;
			}
		}
	}

	public void drawButton(Minecraft mc, int i, int j) {

		if (this.visible) {

			boolean flag = Mouse.isButtonDown(0);
			int k1 = this.xPosition;
			int k2 = k1 + this.width;

			int l1 = this.yPosition;
			int l2 = l1 + (int) maxValue;
			if (flag && i >= k1 && j >= l1 && i < k2 && j < l2) {
				this.isScrolling = true;
			}

			if (!flag) {
				this.isScrolling = false;
			}

			int scrollING = 0;
			if (isScrolling) {
				scrollING = this.width;
			}

			this.wasClicking = flag;

			mc.getTextureManager().bindTexture(texture);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.drawTexturedModalRect(this.xPosition, this.yPosition + (int) sliderValue, textureX + scrollING, textureY,
					this.width, this.height);
			this.mouseDragged(mc, i, j);
			System.out.println(texture+"     "+textureX+"     "+textureY+"     "+sliderValue+this.xPosition+"     "+this.yPosition);
		}
	}

	/**
	 * @return the current sliders value
	 */
	public float getSlider() {
		return sliderValue;
	}

	/**
	 * @return the maxValue that was setup in the initialisation
	 */
	public float getMaxValue() {
		return maxValue;
	}
}