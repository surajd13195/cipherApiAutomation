package cipherApi.resources;

import cipherApi.pojo.*;
import com.google.gson.Gson;

public class readPojo {

    public String bankId;
    public String mobileNumber;
    public String crnNo;
    public String bankAppVersion;
    public String deviceId;
    public String deviceIpAddress;
    public String deviceOs;
    public String deviceOsVersion;
    public String activityType;
    public String sessionId;

    public readPojo(String bankId, String mobileNumber, String crnNo, String bankAppVersion, String deviceId, String deviceIpAddress, String deviceOs, String deviceOsVersion, String activityType, String sessionId){
        this.bankId=bankId;
        this.mobileNumber=mobileNumber;
        this.crnNo=crnNo;
        this.bankAppVersion=bankAppVersion;
        this.deviceId=deviceId;
        this.deviceIpAddress=deviceIpAddress;
        this.deviceOs=deviceOs;
        this.deviceOsVersion=deviceOsVersion;
        this.activityType=activityType;
        this.sessionId=sessionId;
    }

    public String readParameters() throws Exception {

        pojo1 pojo1 = new pojo1();

        Level1Token level1TokenPojo = new Level1Token(bankId, mobileNumber);
        pojo1.setLevel1Token(level1TokenPojo);

        crnNumber crnNumberPojo = new crnNumber(crnNo);
        level1TokenPojo.setCrnNumber(crnNumberPojo);

        deviceDetails deviceDetailsPojo = new deviceDetails(bankAppVersion, deviceId, deviceIpAddress, deviceOs, deviceOsVersion);
        level1TokenPojo.setDeviceDetails(deviceDetailsPojo);

        activityType activityTypePojo = new activityType();
        BalanceInquiry balanceInquiry = new BalanceInquiry();
        activityTypePojo.setBalanceInquiry(balanceInquiry);
        level1TokenPojo.setActivityType(activityTypePojo);

        cipherApi.pojo.sessionId sessionIdPojo = new sessionId(sessionId);
        level1TokenPojo.setSessionId(sessionIdPojo);

        String tokenData = new Gson().toJson(pojo1);
        tokenGeneration gen = new tokenGeneration();
        String encryptedMessage = gen.encrypt(tokenData);
        System.out.println(encryptedMessage);
        return encryptedMessage;
    }
}