package testApi.encodingAndDecoding;

import org.testng.annotations.Test;

public class studentApiTest {

    @Test
    public void loginTest(){

        credentialsData loginCred = base64EncodingAndDecoding.base64Encode("test", "pass123");
        System.out.println(loginCred);
    }
}
