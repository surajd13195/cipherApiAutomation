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

    public readPojo(String bankId, String mobileNumber, String crnNo, String deviceId, String deviceOs, String deviceOsVersion, String deviceIpAddress, String bankAppVersion, String activityType, String sessionId){
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
    }

    public String readParameters() throws Exception {

        pojo1 pojo1 = new pojo1();

        Level1Token level1TokenPojo = new Level1Token(bankId, mobileNumber);
        pojo1.setLevel1Token(level1TokenPojo);

        crnNumber crnNumberPojo = new crnNumber(crnNo);
        level1TokenPojo.setCrnNumber(crnNumberPojo);

        deviceDetails deviceDetailsPojo = new deviceDetails(deviceId, deviceOs, deviceOsVersion, deviceIpAddress, bankAppVersion);
        level1TokenPojo.setDeviceDetails(deviceDetailsPojo);

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
        level1TokenPojo.setActivityType(activityTypePojo);

        pojo.sessionId sessionIdPojo = new sessionId(sessionId);
        level1TokenPojo.setSessionId(sessionIdPojo);

        String tokenData = new Gson().toJson(pojo1);
        tokenGeneration gen = new tokenGeneration();
        String encryptedMessage = gen.encrypt(tokenData);
        System.out.println(encryptedMessage);
        System.out.println("Decrypted message: " + gen.decrypt(encryptedMessage));
        return encryptedMessage;
    }
}