package encodingAndDecoding;

import org.testng.annotations.Test;
import encodingAndDecoding.credentialsData;
import encodingAndDecoding.base64EncodingAndDecoding;

public class studentApiTest {

    @Test
    public void loginTest(){

        credentialsData loginCred = base64EncodingAndDecoding.base64Encode("test", "pass123");
        System.out.println(loginCred);
    }
}
