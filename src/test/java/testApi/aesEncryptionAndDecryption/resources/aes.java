package testApi.aesEncryptionAndDecryption.resources;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Possible KEY_SIZE values are 128, 192 and 256
 * Possible T_LEN values are 128, 120, 112, 104 and 96
 */

public class aes {

    private SecretKey key;
    private int KEY_SIZE = 128;
    private int T_LEN = 128;
    Cipher encryptionCipher;

    public void init() throws Exception{

        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        key = generator.generateKey();
    }

    public String encrypt(String message) throws Exception {

        byte[] messageInBytes = message.getBytes();
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
        return encode(encryptedBytes);
    }

    public String decrypt(String encryptedMessage) throws Exception{

        byte[] messageInBytes = decode(encryptedMessage);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN,encryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE,key,spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
        return new String(decryptedBytes);
    }

    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

    public static void main(String[] args) throws Exception {

        pojo pojo1 = new pojo();
        pojo1.setType("Level1Token");
        pojo1.setBankId("bank01");
        pojo1.setMobileNumber("9823242526");
        List<String> crnNo = new ArrayList<String>();
        crnNo.add("crn01");
        crnNo.add("crn02");
        pojo1.setCrnNumber(crnNo);

        String message = pojo1.toString();
        //System.out.println(message);

        try{
            aes gen = new aes();
            gen.init();
            String encryptedMessage = gen.encrypt(message);
            System.out.println("Encrypted message: " +encryptedMessage);
            String decryptedMessage = gen.decrypt(encryptedMessage);
            System.out.println("Decrypted message: " +decryptedMessage);
        }catch (Exception ignored){}

    }
}