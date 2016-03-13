package com.fatalitiii.utilfatal.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.tileentity.TileEntity;

/*
 *  WORK IN PROGRESS
 */

public class getMethods {

	static String[] blockFunctions = { "update", "clear", "getField", "getGuiID", "hasCustomName",
			"getStackInSlotOnClosing", "isUseableByPlayer", "isItemValidForSlot", "createContainer", "closeInventory",
			"openInventory", "getInventoryStackLimit", "getFieldCount", "isItemFuel", "decrStackSize" };
	static String[] blockMaster = { "func", "packet", "data", "set", "NBT", "nbt" };

	public static List<String> getMethodFromTileEntity(TileEntity tileEntity) {

		List<String> result = new ArrayList<String>();

		if (tileEntity != null) {

			Class<? extends TileEntity> tileEntityClass = tileEntity.getClass();

			// FIXME ONLY RETURN METHODS
			// FIXME Dont get PARAM METHODS

			for (Method method : tileEntityClass.getDeclaredMethods()) {
				int modifiers = method.getModifiers();
				if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
					if (checkMaster(method.getName()) && !result.contains(method.getName())) {
						result.add(method.getName());
						System.out.println(method.getName() + "     " + method.getParameterCount());
						for(int i = 0; i<method.getParameterCount();i++){
							System.out.println(method.getParameterTypes()[i]);
						}
					}

				}
			}
		}

		return result;
	}

	private static boolean checkMaster(String name) {
		boolean ret = true;

		if (Arrays.asList(blockFunctions).contains(name)) {
			ret = false;
		}
		if (ret) {
			for (String s : blockMaster) {
				if (name.matches("(.*)" + s + "(.*)")) {
					ret = false;
					break;
				}
			}
		}

		return ret;
	}

	public static String action(TileEntity tileEntity, String function) {
		Class<? extends TileEntity> c = tileEntity.getClass();
		Method method = null;
		Object oj = null;
		Class<?> params[] = {};
		Object paramsObj[] = {};
		String ret = "";
		try {
			oj = c.newInstance();
			method = c.getDeclaredMethod(function, params);
			ret = method.invoke(oj, paramsObj).toString();
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
