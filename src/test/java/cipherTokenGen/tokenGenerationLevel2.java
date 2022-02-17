/*
package cipherTokenGen;

import pojo.*;
import com.google.gson.Gson;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

*/
/**
 * Possible KEY_SIZE values are 128, 192 and 256
 * Possible T_LEN values are 128, 120, 112, 104 and 96
 *//*


public class tokenGenerationLevel2 {

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

        pojo1 pojo1 = new pojo1();

        Level2Token level2TokenPojo = new Level2Token("HDFC","9876543210");
        pojo1.setLevel2Token(level2TokenPojo);

        crnNumber crnNumberPojo = new crnNumber("somecrn4");
        level2TokenPojo.setCrnNumber(crnNumberPojo);

        deviceDetails deviceDetailsPojo = new deviceDetails("f3ef2be4fe00ec72","android","11","192.168.2.4","1.0");
        level2TokenPojo.setDeviceDetails(deviceDetailsPojo);

        String activityType = "Test";
        activityType activityTypePojo = new activityType();
        if(activityType.equalsIgnoreCase("BalanceInquiry")){
            BalanceInquiry balanceInquiry = new BalanceInquiry();
            activityTypePojo.setBalanceInquiry(balanceInquiry);
        }else if (activityType.equalsIgnoreCase("AddBeneficiary")){
            AddBeneficiary addBeneficiary = new AddBeneficiary();
            activityTypePojo.setAddBeneficiary(addBeneficiary);
        }else if (activityType.equalsIgnoreCase("AmountWithdrawal")){
            AmountWithdrawal amountWithdrawal = new AmountWithdrawal();
            activityTypePojo.setAmountWithdrawal(amountWithdrawal);
        }else {
            activityTypePojo=null;
        }
        level2TokenPojo.setActivityType(activityTypePojo);

        //sessionId sessionIdPojo = null;
        sessionId sessionIdPojo = new sessionId("1234");
        level2TokenPojo.setSessionId(sessionIdPojo);

        String tokenData = new Gson().toJson(pojo1);
        tokenGeneration gen = new tokenGeneration();
        String encryptedMessage = gen.encrypt(tokenData);

        System.out.println("Encrypted message: " + encryptedMessage);
        System.out.println("Decrypted message: " + gen.decrypt(encryptedMessage));
    }
}*/
