package utils;

import constants.frameworkConstants;
import enums.configProperties;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class propertyUtils {

    private static Properties prop = new Properties();
    public static Map<String, String> CONFIGMAP = new HashMap<>();

    public propertyUtils(){
    }

    static {
        try {
            FileInputStream fis = new FileInputStream(frameworkConstants.getConfigfilepath());
            prop.load(fis);
            for (Map.Entry<Object, Object> entry: prop.entrySet()){
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File not found exception thrown for config.properties file");
        }
    }

    public static String get(configProperties key) throws Exception{
        if(Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))){
            throw new Exception("Property name " +key+ " is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }
}