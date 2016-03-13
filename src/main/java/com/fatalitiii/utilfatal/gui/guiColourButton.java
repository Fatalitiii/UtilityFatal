package com.fatalitiii.utilfatal.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

/**
 * @author Fatalitiii
 * @Name guiColorButton.java
 * @Comment Gui Color Button
 */
public class guiColourButton extends GuiButton {

	protected ResourceLocation texture;
	protected int xOffset, yOffset;
	protected int width, height;
	/** The x position of this control. */
	public int xPosition, yPosition;
	public String displayString;
	public int id;
	public boolean enabled;
	public boolean visible;
	protected boolean field_146123_n;

	protected boolean icon = false;
	protected int iconX, iconY;
	protected int iconXOffset, iconYOffset;
	protected int iconWidth, iconHeight;

	protected float red = 1.0F, green = 1.0F, blue = 1.0F;

	protected boolean canHover = false;
	protected HoverFacing hoverDirection = HoverFacing.NONE;

	// Default
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.canHover = false;
	}

	// Default | Icon
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, int iconX, int iconY, int iconXLocation,
			int iconYLocation, int iconWidth, int iconHeight) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.icon = true;
		this.iconX = iconX;
		this.iconY = iconY;
		this.iconXOffset = iconXLocation;
		this.iconYOffset = iconYLocation;
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
	}

	// Default | Hover
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, HoverFacing hoverDirection) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.canHover = true;
		this.hoverDirection = hoverDirection;
	}

	// Default | Icon | Hover
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, HoverFacing hoverDirection, int iconX, int iconY,
			int iconXLocation, int iconYLocation, int iconWidth, int iconHeight) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.canHover = true;
		this.hoverDirection = hoverDirection;

		this.icon = true;
		this.iconX = iconX;
		this.iconY = iconY;
		this.iconXOffset = iconXLocation;
		this.iconYOffset = iconYLocation;
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;

	}

	// Dec Colour
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, int colour) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.red = (float) (colour >> 16 & 255) / 255.0F;
		this.green = (float) (colour >> 8 & 255) / 255.0F;
		this.blue = (float) (colour & 255) / 255.0F;
	}

	/**
	 * 
	 * @param ID
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @param text
	 * @param texture
	 * @param xOffset
	 * @param yOffset
	 * @param colour
	 * @param hoverDirection
	 */
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, int colour, HoverFacing hoverDirection) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.red = (float) (colour >> 16 & 255) / 255.0F;
		this.green = (float) (colour >> 8 & 255) / 255.0F;
		this.blue = (float) (colour & 255) / 255.0F;

		this.canHover = true;
		this.hoverDirection = hoverDirection;
	}

	// Dec Colour | Icon
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, int colour, int iconX, int iconY, int iconXLocation,
			int iconYLocation, int iconWidth, int iconHeight) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.red = (float) (colour >> 16 & 255) / 255.0F;
		this.green = (float) (colour >> 8 & 255) / 255.0F;
		this.blue = (float) (colour & 255) / 255.0F;

		this.icon = true;
		this.iconX = iconX;
		this.iconY = iconY;
		this.iconXOffset = iconXLocation;
		this.iconYOffset = iconYLocation;
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
	}

	// Dec Colour | Icon | Hover
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, int colour, HoverFacing hoverDirection, int iconX,
			int iconY, int iconXLocation, int iconYLocation, int iconWidth, int iconHeight) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.red = (float) (colour >> 16 & 255) / 255.0F;
		this.green = (float) (colour >> 8 & 255) / 255.0F;
		this.blue = (float) (colour & 255) / 255.0F;

		this.icon = true;
		this.iconX = iconX;
		this.iconY = iconY;
		this.iconXOffset = iconXLocation;
		this.iconYOffset = iconYLocation;
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
	}

	// Float Colour
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, float redChannel, float greenChannel,
			float blueChannel) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.red = redChannel;
		this.green = blueChannel;
		this.blue = greenChannel;
	}

	// Float Colour | Hover
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, float redChannel, float greenChannel, float blueChannel,
			HoverFacing hoverDirection) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.red = redChannel;
		this.green = blueChannel;
		this.blue = greenChannel;

		this.canHover = true;
		this.hoverDirection = hoverDirection;
	}

	// Float Colour | Icon
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, float redChannel, float greenChannel, float blueChannel,
			int iconX, int iconY, int iconXLocation, int iconYLocation, int iconWidth, int iconHeight) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.red = redChannel;
		this.green = blueChannel;
		this.blue = greenChannel;

		this.icon = true;
		this.iconX = iconX;
		this.iconY = iconY;
		this.iconXOffset = iconXLocation;
		this.iconYOffset = iconYLocation;
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
	}

	// Float Colour | Icon | Hover
	public guiColourButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset, float redChannel, float greenChannel, float blueChannel,
			HoverFacing hoverDirection, int iconX, int iconY, int iconXLocation, int iconYLocation, int iconWidth,
			int iconHeight) {
		super(ID, xPosition, yPosition, width, height, text);
		this.setupButton(ID, xPosition, yPosition, width, height, text, texture, xOffset, yOffset);

		this.red = redChannel;
		this.green = blueChannel;
		this.blue = greenChannel;

		this.icon = true;
		this.iconX = iconX;
		this.iconY = iconY;
		this.iconXOffset = iconXLocation;
		this.iconYOffset = iconYLocation;
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;

		this.canHover = true;
		this.hoverDirection = hoverDirection;

	}

	@Override
	public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
		if (this.visible) {
			FontRenderer fontrenderer = Minecraft.getMinecraft().fontRendererObj;
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			GL11.glColor4f(red, green, blue, 1.0F);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition
					&& p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
			int k = 1;
			int k1 = 1;
			if (canHover == true) {
				if (hoverDirection == HoverFacing.RIGHT) {
					k1 = 1;
					k = this.getHoverState(this.field_146123_n);
				} else {
					k = 1;
					k1 = this.getHoverState(this.field_146123_n);
				}
			}
			this.drawTexturedModalRect(this.xPosition, this.yPosition, xOffset + ((k - 1) * width),
					yOffset + ((k1 - 1) * width), this.width, this.height);
			if (this.icon) {
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				this.drawTexturedModalRect(this.xPosition + this.iconX, this.yPosition + this.iconY, iconXOffset,
						iconYOffset, iconWidth, iconHeight);
			}
			int l = 14737632;

			this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + 1 + this.width / 2,
					this.yPosition + (this.height - 7) / 2, l);
		}
	}

	protected int getHoverState(boolean p_146114_1_) {
		byte b0 = 1;

		if (!this.enabled) {
			b0 = 0;
		} else if (p_146114_1_) {
			b0 = 2;
		}

		return b0;
	}

	private void setupButton(int ID, int xPosition, int yPosition, int width, int height, String text,
			ResourceLocation texture, int xOffset, int yOffset) {
		this.width = width;
		this.height = height;
		this.enabled = true;
		this.visible = true;
		this.id = ID;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.displayString = text;
		this.texture = texture;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public enum HoverFacing {
		NONE, DOWN, RIGHT
	}
}
