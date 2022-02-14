package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class readConfig {

    public Properties loadProperties(){

        try {
            FileInputStream fis = new FileInputStream("src/test/java/cipherApi/resources/config.properties");
            Properties prop = new Properties();
            prop.load(fis);
            return prop;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File not found exception thrown for config.properties file");
            return null;
        }
    }
}