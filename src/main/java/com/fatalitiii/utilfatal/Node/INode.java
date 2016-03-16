package com.fatalitiii.utilfatal.Node;

import java.awt.Color;

import com.fatalitiii.utilfatal.gui.guiElement;
import com.fatalitiii.utilfatal.utils.ModInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
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
		this.description = description;
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

	public void render(GuiContainer gui) {
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

		guiElement.renderModel(guiXPos + iconXOffset, guiYPos + iconYOffset, 1, guiIconScale, getGUIIcon(), 0, 0);
		guiElement.addText(guiElement.getCenter(title, guiXPos, l, 0.75F), guiYPos + m, title, 0.75F, 6310159, false);

	}

}
