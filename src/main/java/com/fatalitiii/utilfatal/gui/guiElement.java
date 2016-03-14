package com.fatalitiii.utilfatal.gui;

import static org.lwjgl.opengl.GL11.*;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author Fatalitiii
 * @Name guiElement.java
 * @Comment Gui Element
 */
public class guiElement {

	/**
	 * @param xPosition
	 *            X Position
	 * @param yPosition
	 *            Y Position
	 * @param xTexturePosition
	 *            Texture X Position
	 * @param yTexturePosition
	 *            Texture Y Position
	 * @param xSize
	 *            Width
	 * @param ySize
	 *            Height
	 * @param text
	 *            Any text that you want to add to the element
	 * @param scale
	 *            text scale value
	 * @param texture
	 *            Resource Location
	 * @param colour
	 *            Colour overlay that alters the colour of the element
	 * @param gui
	 *            the GuiContainer you are calling the method from. E.g this
	 */
	public static void addElement(int xPosition, int yPosition, int xTexturePosition, int yTexturePosition, int xSize,
			int ySize, String text, float scale, ResourceLocation texture, int colour, GuiContainer gui) {

		int c = colour;
		java.awt.Color.BLACK.getRGB();

		if ((c & -67108864) == 0) {
			c |= -16777216;
		}

		float red = (float) (c >> 16 & 255) / 255.0F;
		float green = (float) (c >> 8 & 255) / 255.0F;
		float blue = (float) (c & 255) / 255.0F;
		float alpha = (float) (c >> 24 & 255) / 255.0F;

		glColor4f(red, green, blue, alpha);
		renderElement(xPosition, yPosition, xTexturePosition, yTexturePosition, xSize, ySize, text, scale, texture,
				gui);
	}

	/**
	 * @param xPosition
	 *            X Position
	 * @param yPosition
	 *            Y Position
	 * @param xTexturePosition
	 *            Texture X Position
	 * @param yTexturePosition
	 *            Texture Y Position
	 * @param xSize
	 *            Width
	 * @param ySize
	 *            Height
	 * @param text
	 *            Any text that you want to add to the element
	 * @param scale
	 *            text scale value
	 * @param texture
	 *            Resource Location
	 * @param gui
	 *            the GuiContainer you are calling the method from. E.g this
	 */
	public static void addElement(int xPosition, int yPosition, int xTexturePosition, int yTexturePosition, int xSize,
			int ySize, String text, float scale, ResourceLocation texture, GuiContainer gui) {
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		renderElement(xPosition, yPosition, xTexturePosition, yTexturePosition, xSize, ySize, text, scale, texture,
				gui);
	}

	private static void renderElement(int xPosition, int yPosition, int xTexturePosition, int yTexturePosition,
			int xSize, int ySize, String text, float scale, ResourceLocation texture, GuiContainer gui) {
		glPushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		glEnable(GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		gui.drawTexturedModalRect(xPosition, yPosition, xTexturePosition, yTexturePosition, xSize, ySize);
		if (!text.equals("")) {
			glScalef(scale, scale, scale);
			int l = 14737632;
			float amend = (1 / scale);
			FontRenderer fontrenderer = Minecraft.getMinecraft().fontRendererObj;
			gui.drawCenteredString(fontrenderer, text, (int) ((xPosition + xSize / 2) * amend) + 1,
					(int) ((yPosition + (ySize - (9 / amend)) / 2) * amend) + 1, l);
		}
		glPopMatrix();
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * @param xPosition
	 *            X Position
	 * @param yPosition
	 *            Y Position
	 * @param text
	 *            Any text that you want to add to the element
	 * @param scale
	 *            text scale value
	 * @param colour
	 *            Colour overlay that alters the colour of the element
	 * @param shadow
	 *            True or False if you want a black drop shadow
	 */
	public static void addText(float xPosition, float yPosition, String text, float scale, int colour, boolean shadow) {
		glPushMatrix();
		glTranslatef(0, 0, 5);
		glScalef(scale, scale, 0F);
		glTranslatef(xPosition / scale, yPosition / scale, 5);
		FontRenderer fontrenderer = Minecraft.getMinecraft().fontRendererObj;
		if (shadow) {
			fontrenderer.drawStringWithShadow(text, 0, 0, colour);
		} else {
			fontrenderer.drawString(text, 0, 0, colour);
		}
		glPopMatrix();
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * @param xPosition
	 *            X Position
	 * @param yPosition
	 *            Y Position
	 * @param zIndex
	 *            Z Position
	 * @param scale
	 *            Scale of the item
	 * @param item
	 *            Item E.g Items.apple
	 * @param rotateX
	 *            X Rotation
	 * @param rotateY
	 *            Y Rotation
	 */
	public static void renderModel(float xPosition, float yPosition, int zIndex, float scale, Item item, float rotateX,
			float rotateY) {
		ItemStack stack = new ItemStack(item, 1, 0);
		EntityItem entity = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, stack);
		entity.hoverStart = 0.0F;

		renderModel(entity, xPosition, yPosition, zIndex, -scale * 10F, scale * 10F, scale, rotateX, rotateY);
	}

	/**
	 * @param xPosition
	 *            X Position
	 * @param yPosition
	 *            Y Position
	 * @param zIndex
	 *            Z Position
	 * @param scale
	 *            Scale of the item
	 * @param ItemStack
	 *            ItemStack
	 * @param rotateX
	 *            X Rotation
	 * @param rotateY
	 *            Y Rotation
	 */
	public static void renderModel(float xPosition, float yPosition, int zIndex, float scale, ItemStack itemStack,
			float rotateX, float rotateY) {
		EntityItem entity = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, itemStack);
		entity.hoverStart = 0.0F;

		renderModel(entity, xPosition, yPosition, zIndex, scale * 10F, scale * 10F, scale, rotateX, rotateY);
	}

	/**
	 * @param xPosition
	 *            X Position
	 * @param yPosition
	 *            Y Position
	 * @param zIndex
	 *            Z Position
	 * @param scale
	 *            Scale of the item
	 * @param Block
	 *            Block E.g Blocks.anvil
	 * @param rotateX
	 *            X Rotation
	 * @param rotateY
	 *            Y Rotation
	 */
	public static void renderModel(float xPosition, float yPosition, int zIndex, float scale, Block block,
			float rotateX, float rotateY) {
		ItemStack stack = new ItemStack(Item.getItemFromBlock(block), 1, 0);
		EntityItem entity = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, stack);
		entity.hoverStart = 0.0F;

		renderModel(entity, xPosition, yPosition, zIndex, -scale * 10F, scale * 10F, 1F, rotateX, rotateY);
	}

	/**
	 * @param xPosition
	 *            X Position
	 * @param yPosition
	 *            Y Position
	 * @param zIndex
	 *            Z Position
	 * @param scale
	 *            Scale of the item
	 * @param entity
	 *            Entity class
	 * @param rotateX
	 *            X Rotation
	 * @param rotateY
	 *            Y Rotation
	 */
	public static void renderModel(float xPosition, float yPosition, int zIndex, float scale, EntityLivingBase entity,
			float rotateX, float rotateY) {
		entity.renderYawOffset = rotateY;
		entity.rotationYaw = rotateY;
		entity.rotationYawHead = entity.rotationYaw;
		entity.rotationPitch = -15F;

		renderModel(entity, xPosition, yPosition, zIndex, scale, scale, scale, -rotateX);
	}

	private static void renderModel(Entity entity, float xPos, float yPos, int zIndex, float scaleX, float scaleY,
			float scaleZ, float... rotate) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, 0, zIndex + 10F);
		GlStateManager.scale(scaleX, scaleY, scaleZ);

		if (entity instanceof EntityItem) {
			GlStateManager.translate(xPos / scaleX, yPos / scaleY + (float) (2 * 0.23), 0);
			GlStateManager.rotate(rotate[1], 1, 0, 0);
		} else {
			GlStateManager.translate(xPos / scaleX, yPos / scaleY, 0);
		}
		GlStateManager.rotate(rotate[0], 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
		RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		rendermanager.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	/**
	 * @Description Add text with Items, Entitys or blocks by replacing them
	 *              with % followed by any character
	 * @param xPosition
	 *            X Position
	 * @param yPosition
	 *            Y Position
	 * @param scaleText
	 *            Scale of the item
	 * @param obj
	 *            object e.g Object[]{"Hi %a", 'a', Items.apple}
	 */
	public static void addTextWithItem(float xPosition, float yPosition, float scaleText, Object... obj) {
		float scale = 3F;
		if (scaleText <= 3F) {
			scale = scaleText;
		}
		String text = "";
		int i = 0;

		if (obj.length >= 2) {
			while (obj[i] instanceof String) {
				String s2 = (String) obj[i++];
				text = s2;
			}
			char[] str = text.toCharArray();
			Character[] charArray = ArrayUtils.toObject(str);
			for (; i < obj.length; i += 2) {
				Character character = (Character) obj[i];
				ItemStack itemstack1 = null;
				float offSet = 0;
				boolean foundPosition = false;
				for (int j = 0; j < charArray.length; j++) {

					if (charArray[j].equals(character) && charArray[j - 1].equals('%')) {
						foundPosition = true;
						text = text.replace("%" + charArray[j].toString(), "   ");
						offSet -= 2;
						break;
					}
					int letter = Minecraft.getMinecraft().fontRendererObj.getCharWidth(charArray[j]);
					offSet += letter;
				}
				float xRot = 0.0F, yRot = 0.0F;
				if (obj[i + 1] instanceof Entity) {
					if (foundPosition)
						renderModel(xPosition + (offSet * scale), yPosition + scale + (25F / 3F * scale), 30,
								scale * 10F, (EntityLivingBase) obj[i + 1], 190F, yRot);
				} else {
					if (obj[i + 1] instanceof Item) {
						itemstack1 = new ItemStack((Item) obj[i + 1]);
						if (foundPosition)
							renderModel(xPosition + (offSet * scale), yPosition + scale, 30, scale * 2, itemstack1,
									xRot, yRot);
					} else if (obj[i + 1] instanceof Block) {
						itemstack1 = new ItemStack((Block) obj[i + 1], 1, 0);
						xRot = -190F;
						yRot = 10F;
						if (foundPosition)
							renderModel(xPosition + (offSet * scale),
									yPosition + scale + (0 * scale * 10F) + (3F / 3F * scale), 30, scale * 2,
									itemstack1, xRot, yRot);
					}

				}
			}
		} else {
			text = (String) obj[0];
		}
		addText(xPosition, yPosition, text, scale, 14737632, true);
	}

	/**
	 * 
	 * @param xPosition
	 *            X position on screen
	 * @param yPosition
	 *            Y position on screen
	 * @param u
	 *            Texture start X
	 * @param v
	 *            Texture start Y
	 * @param width
	 *            Width on screen
	 * @param height
	 *            Height on screen
	 * @param textureWidth
	 *            Texture width
	 * @param textureHeight
	 *            Texture height
	 */
	public static void drawRectWithUV(int xPosition, int yPosition, float u, float v, int width, int height,
			float textureWidth, float textureHeight) {
		drawRectWithUV(xPosition, yPosition, u, v, width, height, textureWidth, textureHeight, 256, 256);
	}

	/**
	 * 
	 * @param xPosition
	 *            X position on screen
	 * @param yPosition
	 *            Y position on screen
	 * @param u
	 *            Texture start X
	 * @param v
	 *            Texture start Y
	 * @param width
	 *            Width on screen
	 * @param height
	 *            Height on screen
	 * @param textureWidth
	 *            Texture width
	 * @param textureHeight
	 *            Texture height
	 * @param textureSheetWidth
	 *            Texture resource width if not 256
	 * @param textureSheetHeight
	 *            Texture resource height if not 256
	 */
	public static void drawRectWithUV(int xPosition, int yPosition, float u, float v, int width, int height,
			float textureWidth, float textureHeight, int textureSheetWidth, int textureSheetHeight) {
		float scaleX = 1.0F / textureSheetWidth;
		float scaleY = 1.0F / textureSheetHeight;
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.addVertexWithUV((double) xPosition, (double) (yPosition + height), 0.0D, (double) (u * scaleX),
				(double) (v + textureHeight) * scaleY);
		worldrenderer.addVertexWithUV((double) (xPosition + width), (double) (yPosition + height), 0.0D,
				(double) (u + textureWidth) * scaleX, (double) (v + textureHeight) * scaleY);
		worldrenderer.addVertexWithUV((double) (xPosition + width), (double) yPosition, 0.0D,
				(double) ((u + textureWidth) * scaleX), (double) (v * scaleY));
		worldrenderer.addVertexWithUV((double) xPosition, (double) yPosition, 0.0D, (double) (u * scaleX),
				(double) (v * scaleY));
		tessellator.draw();
	}

}
