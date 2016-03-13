package com.fatalitiii.utilfatal.mobs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.world.World;


/**
 * @author Fatalitiii
 * @Name getAllMobs.java
 * @Comment Get all registered mobs within minecraft forge
 */
public class getAllMobs {

	@SuppressWarnings("rawtypes")
	private HashMap mobClass = new HashMap<Integer, Class>();
	@SuppressWarnings("rawtypes")
	private HashMap mobString = new HashMap<Integer, String>();
	@SuppressWarnings("rawtypes")
	private Map stringID = new HashMap();
	private static int maxEntityID = 0;

	public getAllMobs() {
	}

	/**
	 * @Comment Initialises getting all mobs from minecraft registry and is done
	 *          by the Utility so no need to call this method.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public void initMobs() {
		int j = 0;

		Field field = null;
		try {
			field = EntityList.class.getDeclaredField("stringToIDMapping");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		Map<String, Integer> refMap = null;
		try {
			refMap = (HashMap<String, Integer>) field.get(new HashMap<String, Object>());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		String mobsGotten = refMap.toString().replaceAll("[{}]", "").replace(" ", "");
		String[] strArray = mobsGotten.split(",");
		for (String str : strArray) {
			String[] mobArray = str.split("=");
			stringID.put(mobArray[0].replace("Entity", ""), Integer.parseInt(mobArray[1]));
		}

		Map<String, Integer> sortedMobDescID = sortByComparator(stringID, false);

		Class[] nonMobClasses = { EntityCreature.class, EntityMinecart.class, EntityMinecartContainer.class,
				Entity.class, EntityHanging.class, EntityThrowable.class, EntityFireball.class };

		String[] blockClassesByString = { "Mob" };

		for (int i = 0; i <= maxEntityID + 1; i++) {
			if (EntityList.idToClassMapping.get(i) != null) {
				Class cls = EntityList.getClassFromID(i).getSuperclass();

				if ((!Arrays.asList(nonMobClasses).contains(cls))
						&& (!Arrays.asList(blockClassesByString).contains(EntityList.getStringFromID(i)))) {
					mobClass.put(i, EntityList.getClassFromID(i));
					mobString.put(j, EntityList.getStringFromID(i));

					j++;
				}
			}
		}
	}

	/**
	 * @return returns all the mob classes as a hashmap.
	 */
	@SuppressWarnings("rawtypes")
	public HashMap getMobClass() {
		return mobClass;
	}

	/**
	 * @return returns all the mob names as a string.
	 */
	@SuppressWarnings("rawtypes")
	public HashMap getMobString() {
		return mobString;
	}

	/**
	 * @param String
	 *            the mobs name as it is in game.
	 * @return returns the mob class as a object.
	 */
	public Object getClassFromSting(String s) {
		int i = (Integer) stringID.get(s);
		return mobClass.get(Integer.valueOf(i)).toString();
	}

	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {

		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		int currentID = 0;

		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
			if (currentID < entry.getValue())
				currentID = entry.getValue();
		}
		maxEntityID = currentID;

		return sortedMap;
	}

	/**
	 * @param String
	 *            the mobs name as it is in game.
	 * @param World
	 *            the world object that you are calling the method from.
	 * @return Entity
	 */
	public Entity getMobClass(String entityName, World worldObj) {
		String classPath = ((String) getClassFromSting(entityName)).split(" ")[1];
		Class<?> clazz = null;
		try {
			clazz = Class.forName(classPath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Constructor<?> constructor = null;
		try {
			constructor = clazz.getConstructor(World.class);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		Object entity = null;
		try {
			entity = constructor.newInstance(worldObj);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ((Entity) entity);
	}
}