package com.fatalitiii.utilfatal.Register;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Fatalitiii
 * @Name ItemRegister.java
 * @Comment Register items quick and easy
 */
public class ItemRegister {

	@SuppressWarnings("rawtypes")
	static HashMap<String, ArrayList> itemRegistry = new HashMap<String, ArrayList>();

	/**
	 * Initialise custom blocks quick and easy
	 * 
	 * @param item
	 *            item
	 * @param itemClass
	 *            Item Class
	 * @param name
	 *            Name of item
	 * @param tab
	 *            Creative tab you want to display the item in
	 * @param MOD_ID
	 *            Mod ID associated with the mod
	 */
	@SuppressWarnings("unchecked")
	public static void init(Item item, Class<?> itemClass, String name, CreativeTabs tab, String MOD_ID) {
		try {
			item = ((Item) itemClass.newInstance()).setUnlocalizedName("item_" + name).setCreativeTab(tab);
			GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));

			ArrayList<Item> items;
			if (itemRegistry.containsKey(MOD_ID)) {
				items = itemRegistry.get(MOD_ID);
			} else {
				items = new ArrayList<>();
			}
			items.add(item);
			itemRegistry.put(MOD_ID, items);

		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Register item asset with item class
	 * 
	 * @param item
	 * @param MOD_ID
	 */
	@SuppressWarnings("unchecked")
	public static void registerRenders(String MOD_ID) {
		if (!itemRegistry.isEmpty()) {
			ArrayList<Item> modItems = itemRegistry.get(MOD_ID);
			for (Item item : modItems) {
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
						new ModelResourceLocation(MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
			}
		}
	}
}