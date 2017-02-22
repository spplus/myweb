package qc.com.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import qc.com.conf.SystemPathConf;

public class Initialization {

	private static Map<String, String> businessExceptions = new HashMap<String, String>();
	public Initialization() {
		readBusinessExceptions();
	}

	public static void readBusinessExceptions() {
		Properties p = new Properties();
		InputStream in = SystemPathConf.getExceptionConfigPath();
		try {
			p.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<Entry<Object, Object>> set = p.entrySet();
		Iterator<Entry<Object, Object>> iter = set.iterator();
		while (iter.hasNext()) {
			Entry<Object, Object> e = iter.next();
			String val = (String) e.getValue();
			try {
				val = new String(val.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			businessExceptions.put((String) e.getKey(), val);
		}
	}

	public static Map<String, String> getBusinessExceptions() {
		if (businessExceptions.isEmpty()) {
			readBusinessExceptions();
			return businessExceptions;
		} else {
			return businessExceptions;
		}
	}

	public static void setBusinessExceptions(
			Map<String, String> businessExceptions) {
		Initialization.businessExceptions = businessExceptions;
	}
}
