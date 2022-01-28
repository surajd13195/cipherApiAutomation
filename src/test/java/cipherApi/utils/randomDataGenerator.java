package cipherApi.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class randomDataGenerator {

    public static String getName(){
        String generatedString = RandomStringUtils.randomAlphabetic(2);
        return ("Test"+generatedString);
    }

    public static String getJob() {
        String generatedString = RandomStringUtils.randomAlphabetic(2);
        return ("Leader" + generatedString);
    }

    public static String getEmail() {
        String generatedString = RandomStringUtils.randomAlphabetic(2);
        return ("Test" + generatedString + "@gmail.com");
    }

    public static String getEmpSalary() {
        String generatedString = RandomStringUtils.randomNumeric(5);
        return ("Test" + generatedString);
    }

}