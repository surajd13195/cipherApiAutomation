package cipherTokenGen;

import pojo.*;
import com.google.gson.Gson;

public class readPojo {

    public String bankId;
    public String mobileNumber;
    public String crnNo;
    public String deviceId;
    public String deviceOs;
    public String deviceOsVersion;
    public String deviceIpAddress;
    public String bankAppVersion;
    public String activityType;
    public String sessionId;
    public String levelTag;

    public readPojo(String bankId, String mobileNumber, String crnNo, String deviceId, String deviceOs, String deviceOsVersion, String deviceIpAddress, String bankAppVersion, String activityType, String sessionId, String levelTag){
        this.bankId=bankId;
        this.mobileNumber=mobileNumber;
        this.crnNo=crnNo;
        this.deviceId=deviceId;
        this.deviceOs=deviceOs;
        this.deviceOsVersion=deviceOsVersion;
        this.deviceIpAddress=deviceIpAddress;
        this.bankAppVersion=bankAppVersion;
        this.activityType=activityType;
        this.sessionId=sessionId;
        this.levelTag=levelTag;
    }

    public String readParameters() throws Exception {

        pojo1 pojo1;

        if(activityType.isEmpty()){
            activityType = null;
        }else {
            activityType=activityType;
        }

        pojo1 = new pojo1(bankId, mobileNumber, activityType, levelTag);

        crnNumber crnNumberPojo = new crnNumber(crnNo);
        pojo1.setCrnNumber(crnNumberPojo);

        deviceDetails deviceDetailsPojo = new deviceDetails(deviceId, deviceOs, deviceOsVersion, deviceIpAddress, bankAppVersion);
        pojo1.setDeviceDetails(deviceDetailsPojo);

        if(sessionId.isEmpty()){
            pojo.sessionId sessionIdPojo = null;
        }else {
            pojo.sessionId sessionIdPojo = new sessionId(sessionId);
            pojo1.setSessionId(sessionIdPojo);
        }

        String tokenData = new Gson().toJson(pojo1);
        tokenGeneration gen = new tokenGeneration();
        String encryptedMessage = gen.encrypt(tokenData);
        System.out.println(encryptedMessage);
        System.out.println("Decrypted message: " + gen.decrypt(encryptedMessage));
        return encryptedMessage;
    }
}