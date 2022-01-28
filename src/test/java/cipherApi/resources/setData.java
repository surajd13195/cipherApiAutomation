package cipherApi.resources;

import cipherApi.pojo.*;
import cipherApi.utils.excelUtils;

public class setData {

    int rowNum;
    String bankId;
    String mobileNumber;
    String crnNo;
    String bankAppVersion;
    String deviceId;
    String deviceIpAddress;
    String deviceOs;
    String deviceOsVersion;
    String activityType;
    String sessionId;

    public String level1ValidToken(String excelPath, String sheetName) throws Exception {

        excelUtils excel = new excelUtils(excelPath, sheetName);

        rowNum = 1;
        bankId = excel.getCellData(rowNum, 0);
        mobileNumber = excel.getCellData(rowNum, 1);
        crnNo = excel.getCellData(rowNum, 2);
        bankAppVersion = excel.getCellData(rowNum, 3);
        deviceId = excel.getCellData(rowNum, 4);
        deviceIpAddress = excel.getCellData(rowNum, 5);
        deviceOs = excel.getCellData(rowNum, 6);
        deviceOsVersion = excel.getCellData(rowNum, 7);
        activityType = excel.getCellData(rowNum, 8);
        sessionId = excel.getCellData(rowNum, 9);

        readPojo readPojo = new readPojo(bankId, mobileNumber, crnNo,  bankAppVersion, deviceId, deviceIpAddress, deviceOs, deviceOsVersion, activityType, sessionId);
        String encryptedMessage = readPojo.readParameters();
        return encryptedMessage;

        /*pojo1 pojo1 = new pojo1();

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
        return encryptedMessage;*/
    }

    public String level1InvalidToken(String excelPath, String sheetName) throws Exception {

        excelUtils excel = new excelUtils(excelPath, sheetName);

        rowNum = 2;
        bankId = excel.getCellData(rowNum, 0);
        mobileNumber = excel.getCellData(rowNum, 1);
        crnNo = excel.getCellData(rowNum, 2);
        bankAppVersion = excel.getCellData(rowNum, 3);
        deviceId = excel.getCellData(rowNum, 4);
        deviceIpAddress = excel.getCellData(rowNum, 5);
        deviceOs = excel.getCellData(rowNum, 6);
        deviceOsVersion = excel.getCellData(rowNum, 7);
        activityType = excel.getCellData(rowNum, 8);
        sessionId = excel.getCellData(rowNum, 9);

        readPojo readPojo = new readPojo(bankId, mobileNumber, crnNo,  bankAppVersion, deviceId, deviceIpAddress, deviceOs, deviceOsVersion, activityType, sessionId);
        String encryptedMessage = readPojo.readParameters();
        return encryptedMessage;
    }
}