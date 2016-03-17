package com.fatalitiii.utilfatal.Node;

import java.awt.Color;

import com.fatalitiii.utilfatal.gui.guiElement;
import com.fatalitiii.utilfatal.utils.ModInfo;
import com.fatalitiii.utilfatal.utils.maths;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class INode {

	int guiXPos, guiYPos;
	String guiIcon;
	float guiIconScale;
	int iconXOffset, iconYOffset;
	int importance;
	String title;
	String description;

	public INode(int guiXPos, int guiYPos, String guiIcon, float guiIconScale, int iconXOffset, int iconYOffset,
			int importance, String title, String description) {
		this.guiXPos = guiXPos;
		this.guiYPos = guiYPos;
		this.guiIcon = guiIcon;
		this.guiIconScale = guiIconScale;
		this.iconXOffset = iconXOffset;
		this.iconYOffset = iconYOffset;
		this.importance = importance;
		this.title = title;
		parsDescription(description);
	}

	private void parsDescription(String description) {
		String parsDescription = "";
		String currentLine = "";
		for (int i = 0; i < description.length(); i++) {

			if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(currentLine) < 120) {
				currentLine += description.charAt(i);
			} else {
				parsDescription += currentLine;
				currentLine = "";
				if (!(description.charAt(i) + "").equalsIgnoreCase(" ")) {
					currentLine = "" + description.charAt(i);
				}
			}
			if (i == description.length() - 1) {
				parsDescription += currentLine;
			}
		}
		this.description = parsDescription;
	}

	public int getGUIXPos() {
		return this.guiXPos;
	}

	public int getGUIYPos() {
		return this.guiYPos;
	}

	public Item getGUIIcon() {
		return GameRegistry.findItem("minecraft", this.guiIcon);
	}

	public int getImportance() {
		return this.importance;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public void render(GuiContainer gui, int mouseX, int mouseY) {
		int j = 0;
		int k = 40;
		int l = 22;
		int m = 24;

		if (importance == 1) {
			j = 24;
			k = 38;
			l = 26;
			m = 28;
		}

		guiElement.addElement(guiXPos, guiYPos, j, k, l, l, "", 1,
				new ResourceLocation(ModInfo.MOD_ID + ":textures/gui/gui.png"), gui);

		guiElement.renderModel(guiXPos + iconXOffset, guiYPos + iconYOffset, 0, guiIconScale, getGUIIcon(), 0, 0);
		guiElement.addText(guiElement.getCenter(title, guiXPos, l, 0.75F), guiYPos + m, title, 0.75F, 6310159, false);
		if ((mouseX >= guiXPos && mouseY >= guiYPos) && (mouseX <= guiXPos + l && mouseY <= guiYPos + l)) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, 0, 10.1F);
			float height = ((maths.round(Minecraft.getMinecraft().fontRendererObj.getStringWidth(description), 120)
					/ 120)) * 10F;
			guiElement.drawRectWithUV(mouseX + 7, mouseY + 2, 0, 64, 1, height+2, 1, 1);
			guiElement.drawRectWithUV(mouseX + 7, mouseY + 2, 0, 64, 127, 1, 1, 1);
			guiElement.drawRectWithUV(mouseX + 7 + 127, mouseY + 2, 1, 64, 1, height+2, 1, 1);
			guiElement.drawRectWithUV(mouseX + 7, mouseY + 2 + height+2, 1, 64, 128, 1, 1, 1);
			guiElement.drawRectWithUV(mouseX + 7 + 1, mouseY + 2 + 1, 2, 64, 126, height+1, 1, 1);

			String currentLine = "";
			float yOffset = 4;
			for (int i = 0; i < description.length(); i++) {
				if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(currentLine) <= 119) {
					currentLine += description.charAt(i);
				} else {
					guiElement.addText(mouseX + 9, mouseY + yOffset, currentLine, 1, Color.WHITE.getRGB(), false);
					yOffset += 10;
					currentLine = "";
					if (!(description.charAt(i) + "").equalsIgnoreCase(" ")) {
						currentLine = "" + description.charAt(i);
					}
				}
				if (i == description.length() - 1 && !(currentLine.equals(""))) {
					guiElement.addText(mouseX + 9, mouseY + yOffset, currentLine, 1, Color.WHITE.getRGB(), false);
				}
			}
			GlStateManager.popMatrix();
		}
	}

}
