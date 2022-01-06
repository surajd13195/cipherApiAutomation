package encodingAndDecoding;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.Base64;
import java.util.Random;

public class base64EncodingAndDecoding {

    public static credentialsData base64Encode(final String username, final String password) {

        String credentials = username + ":" + password;
        String encodedData = null;
        try {
            encodedData = Base64.getEncoder().encodeToString(credentials.getBytes());
            System.out.println(encodedData);
        } catch (Exception e) {
            //TODO: handle exception
        }

        credentialsData loginCred = new credentialsData();
        loginCred.setCredentials(encodedData);
        return loginCred;

    }

    public void base64Decode(final String encodedValue) {

        byte[] decodedData;
        try {
            decodedData = Base64.getDecoder().decode(encodedValue);
            String decodedText = new String(decodedData,"UTF-8");
            System.out.println(decodedText);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    // To generate random numbers
    public static String randomNumber(){
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        String id = Integer.toString(randomNumber);
        return id;
    }

    // To generate random String
    public static String randomString(){
        String randomString = RandomStringUtils.randomAlphabetic(8);
        return randomString;
    }

    public static void main(String[] args){
        base64EncodingAndDecoding encodeDecode = new base64EncodingAndDecoding();
        encodeDecode.base64Encode("test", "pass123");
        encodeDecode.base64Decode("dGVzdDpwYXNzMTIz");
    }
}