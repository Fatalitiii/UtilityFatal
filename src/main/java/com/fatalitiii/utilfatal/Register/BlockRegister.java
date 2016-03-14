package com.fatalitiii.utilfatal.Register;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Fatalitiii
 * @Name BlockRegister.java
 * @Comment Register blocks quick and easy
 */
public class BlockRegister {

	@SuppressWarnings("rawtypes")
	static HashMap<String, ArrayList> blockRegistry = new HashMap<String, ArrayList>();

	/**
	 * Initialise custom blocks quick and easy
	 * 
	 * @param block
	 *            block
	 * @param blockClass
	 *            Block Class
	 * @param name
	 *            Name of block
	 * @param tab
	 *            Creative tab you want to display the block in
	 * @param MOD_ID
	 *            Mod ID associated with the mod
	 */
	@SuppressWarnings("unchecked")
	public static void init(Block block, Class<?> blockClass, String name, CreativeTabs tab, String MOD_ID) {
		try {
			block = ((Block) blockClass.newInstance()).setUnlocalizedName("block_" + name).setCreativeTab(tab);
			GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));

			ArrayList<Block> blocks;
			if (blockRegistry.containsKey(MOD_ID)) {
				blocks = blockRegistry.get(MOD_ID);
			} else {
				blocks = new ArrayList<>();
			}
			blocks.add(block);
			blockRegistry.put(MOD_ID, blocks);

		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Register block asset with item class
	 * 
	 * @param block
	 * @param MOD_ID
	 */
	@SuppressWarnings("unchecked")
	public static void registerRenders(String MOD_ID) {
		if (!blockRegistry.isEmpty()) {
			ArrayList<Block> modBlocks = blockRegistry.get(MOD_ID);
			for (Block block : modBlocks) {
				Item item = Item.getItemFromBlock(block);
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
						new ModelResourceLocation(MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
			}
		}
	}
}
