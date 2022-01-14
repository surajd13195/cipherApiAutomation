package cipherApi.resources;

import cipherApi.pojo.crnNumber;
import cipherApi.pojo.deviceDetails;
import cipherApi.pojo.level1Pojo;
import com.google.gson.Gson;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Possible KEY_SIZE values are 128, 192 and 256
 * Possible T_LEN values are 128, 120, 112, 104 and 96
 */

public class tokenGeneration {

    Cipher cipher;
    String mySecretkey;
    SecretKeySpec secretBytes;

    public String encrypt(String data) throws Exception {

        cipher = Cipher.getInstance("AES");
        mySecretkey = "aPdSgVkXabcdefgh";
        secretBytes = new SecretKeySpec(mySecretkey.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretBytes, new SecureRandom());
        byte[] cipherText = cipher.doFinal(data.getBytes());

        String encodedMessage = Base64.getEncoder().encodeToString(cipherText);
        return encodedMessage;
    }

    public String decrypt(String encryptedMessage) throws Exception {

        cipher = Cipher.getInstance("AES");
        byte[] decodedMessage = Base64.getDecoder().decode(encryptedMessage);
        mySecretkey = "aPdSgVkXabcdefgh";
        secretBytes = new SecretKeySpec(mySecretkey.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretBytes);
        byte[] decryptedMessage = cipher.doFinal(decodedMessage);
        return new String(decryptedMessage);
    }

    public static void main(String[] args) throws Exception {

        /*level1Pojo pojo1 = new level1Pojo();
        pojo1.setType("Level1Token");
        pojo1.setBankId("bank01");
        pojo1.setMobileNumber("9823242526");

        crnNumber crnNumberPojo = new crnNumber();
        crnNumberPojo.setCrnNo("11111111111111");
        pojo1.setCrnNumber(crnNumberPojo);

        deviceDetails deviceDetailsPojo = new deviceDetails();
        deviceDetailsPojo.setBankAppVersion("1.0");
        deviceDetailsPojo.setDeviceId("f3ef2be4fe00ec72");
        deviceDetailsPojo.setDeviceIpAddress("192.168.2.4");
        deviceDetailsPojo.setDeviceOs("android");
        deviceDetailsPojo.setDeviceOsVersion("11");
        pojo1.setDeviceDetails(deviceDetailsPojo);

        String tokenData = new Gson().toJson(pojo1);
        tokenGeneration gen = new tokenGeneration();
        String encryptedMessage = gen.encrypt(tokenData);*/

        level1Pojo pojo1 = new level1Pojo("Level1Token","bank01","9823242526");

        crnNumber crnNumberPojo = new crnNumber("11111111111111");
        pojo1.setCrnNumber(crnNumberPojo);

        deviceDetails deviceDetailsPojo = new deviceDetails("1.0","f3ef2be4fe00ec72","192.168.2.4","android","11");
        pojo1.setDeviceDetails(deviceDetailsPojo);

        String tokenData = new Gson().toJson(pojo1);
        tokenGeneration gen = new tokenGeneration();
        String encryptedMessage = gen.encrypt(tokenData);

        System.out.println("Encrypted message: " + encryptedMessage);
        System.out.println("Decrypted message: " + gen.decrypt(encryptedMessage));
    }
}