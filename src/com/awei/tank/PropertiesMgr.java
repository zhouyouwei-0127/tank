package com.awei.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertiesMgr {
    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if (key == null) {
            return null;
        } else {
          return  properties.get(key);
        }
    }
}
