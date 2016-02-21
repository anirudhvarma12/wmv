package com.github.anirudhvarma12;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class PropertyWriter {

	private static final String VERSION_KEY = "version";
	private static final String DATE_KEY = "date";

	public static final String TYPE_PROPERTIES = "properties";
	public static final String TYPE_JSON = "json";
	public static final String TYPE_XML = "xml";

	private static final char QUOTE = '"';

	public static void write(String version, String type, File path)
			throws IOException {
		if (type.equals(TYPE_PROPERTIES)) {
			writeProperties(version, path);
		} else if (type.equals(TYPE_JSON)) {
			writeFile(getJSONString(version), path);
		} else if (type.equals(TYPE_XML)) {
			writeFile(getXMLString(version), path);
		}
	}

	private static void writeProperties(String version, File file)
			throws IOException {
		Properties props = new Properties();
		props.put(VERSION_KEY, version);
		props.put(DATE_KEY, getCurrentDateString());
		props.store(new FileOutputStream(file), "Auto generated");
	}

	private static String getJSONString(String version) {
		StringBuilder builder = new StringBuilder("{");
		builder.append(QUOTE).append(VERSION_KEY).append(QUOTE).append(":")
				.append(QUOTE).append(version).append(QUOTE).append(",");
		builder.append(QUOTE).append(DATE_KEY).append(QUOTE).append(":")
				.append(QUOTE).append(getCurrentDateString()).append(QUOTE)
				.append("}");
		return builder.toString();
	}

	private static String getXMLString(String version) {
		StringBuilder builder = new StringBuilder("<wmv>");
		builder.append("<").append(VERSION_KEY).append(">").append(version)
				.append("</").append(VERSION_KEY).append(">");
		builder.append("<").append(DATE_KEY).append(">")
				.append(getCurrentDateString()).append("</").append(DATE_KEY)
				.append(">");
		builder.append("</wmv>");
		return builder.toString();
	}

	private static void writeFile(String data, File file) throws IOException {
		FileWriter writer = new FileWriter(file);
		writer.write(data);
		writer.close();
	}

	private static String getCurrentDateString() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		return formatter.format(date);
	}

}
