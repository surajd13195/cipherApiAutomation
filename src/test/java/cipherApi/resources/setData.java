package cipherApi.resources;

import cipherApi.pojo.crnNumber;
import cipherApi.pojo.deviceDetails;
import cipherApi.pojo.level1Pojo;
import com.google.gson.Gson;

public class setData {

    public String level1ValidToken() throws Exception {

        level1Pojo pojo1 = new level1Pojo("Level1Token","bank01","9823242526");

        crnNumber crnNumberPojo = new crnNumber("11111111111111");
        pojo1.setCrnNumber(crnNumberPojo);

        deviceDetails deviceDetailsPojo = new deviceDetails("1.0","f3ef2be4fe00ec72","192.168.2.4","android","11");
        pojo1.setDeviceDetails(deviceDetailsPojo);

        String tokenData = new Gson().toJson(pojo1);
        tokenGeneration gen = new tokenGeneration();
        String encryptedMessage = gen.encrypt(tokenData);
        System.out.println(encryptedMessage);
        return encryptedMessage;
    }

    public String level1InvalidToken() throws Exception {

        level1Pojo pojo1 = new level1Pojo("Level2Token","bank01","9823242526");

        crnNumber crnNumberPojo = new crnNumber("11111111111111");
        pojo1.setCrnNumber(crnNumberPojo);

        deviceDetails deviceDetailsPojo = new deviceDetails("1.0","f3ef2be4fe00ec72","192.168.2.4","android","11");
        pojo1.setDeviceDetails(deviceDetailsPojo);

        String tokenData = new Gson().toJson(pojo1);
        tokenGeneration gen = new tokenGeneration();
        String encryptedMessage = gen.encrypt(tokenData);
        System.out.println(encryptedMessage);
        return encryptedMessage;
    }
}