package com.fatalitiii.utilfatal.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

/**
 * @author Fatalitiii
 * @Name jsonToClass.java
 * @Comment Convert JSON file to class array
 */
public class jsonToClass {

	public static File modFolder;
	public static File itemsFolder;
	public static File modelsFolder;

	/**
	 * @Comment Download website / server .json file into mods folder
	 * @param modFolder
	 *            Mod folder name 
	 * @param jsonFile
	 *            JSON file name
	 * @param URL
	 *            URL to website / server JSON file
	 * @throws IOException
	 */
	public static void downloadJSON(File modFolder, String jsonFile, String URL) throws IOException {
		URL url = new URL(URL);
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		FileOutputStream fis = new FileOutputStream(modFolder + "/" + jsonFile + ".json");
		byte[] buffer = new byte[1024];
		int count = 0;
		while ((count = bis.read(buffer, 0, 1024)) != -1) {
			fis.write(buffer, 0, count);
		}
		fis.close();
		bis.close();
	}

	/**
	 * @Comment Convert JSON data into class
	 * @param modFolder
	 *            Mod folder name 
	 * @param jsonFile
	 *            JSON file name
	 * @param cls
	 *            Class template with defined variables
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList getJsonData(File modFolder, String jsonFile, Class<?> cls) {
		JsonReader reader = null;
		try {
			reader = new JsonReader(new InputStreamReader(new FileInputStream(modFolder + "/" + jsonFile + ".json")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (reader != null) {
			Gson myGson = new Gson();
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = jsonParser.parse(reader).getAsJsonArray();
			ArrayList jsonNames = new ArrayList<>();

			Object itemClass = null;
			try {
				itemClass = cls.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			if (itemClass != null) {
				for (JsonElement json : jsonArray) {
					Object item = itemClass;
					item = myGson.fromJson(json, itemClass.getClass());
					jsonNames.add(item);
					System.out.println(cls);
					System.out.println(item);
				}

				return jsonNames;
			}
		}
		return null;

	}

}
