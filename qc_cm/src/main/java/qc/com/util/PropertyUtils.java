package qc.com.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertyUtils {

	private static final String CONFIG = "conf";
	private static ResourceBundle CONFIG_RES = ResourceBundle.getBundle(CONFIG);

	private PropertyUtils() {
	}

	public static String getString(String key) {
		try {
			return CONFIG_RES.getString(key);
		} catch (MissingResourceException e) {
			// CONFIG_RES = ResourceBundle.getBundle(CONFIG);
		}
		return "";
	}

	public static int getInt(String key) {
		String value = getString(key);
		if (!StringUtil.isEmpty(value)) {
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static boolean getBoolean(String key) {
		return "true".equals(getString(key));
	}

}