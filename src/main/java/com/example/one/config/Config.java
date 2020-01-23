package com.example.one.config;

import java.util.Properties;

public class Config {
	private String path;
	private Properties properties;
	private Properties config;
	private static Config instance;

	private Config() {
		properties = new Properties();
		config = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
			 
			config.put("hostname", "http://localhost:8083");
//			path = "server.conf";
//			config.load(new FileInputStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String _getProperty(String key) {
		return properties.getProperty(key);
	}

	private String _getConfig(String key) {
		return config.getProperty(key);
	}

	public static String getProperty(String key) {
		if (instance == null)
			instance = new Config();
		System.out.print(key + ": " + instance._getProperty(key));
		return instance._getProperty(key);
	}

	public static String getConfig(String key) {
		if (instance == null)
			instance = new Config();
		System.out.print(key + ": " + instance._getConfig(key));
		return instance._getConfig(key);
	}
}
