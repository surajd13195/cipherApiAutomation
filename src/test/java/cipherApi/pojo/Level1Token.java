package cipherApi.pojo;

public class Level1Token {

    public String bankId;
    public String mobileNumber;
    crnNumber crnNumber;
    deviceDetails deviceDetails;
    activityType activityType;
    sessionId sessionId;

    public Level1Token(String bankId, String mobileNumber){
        this.bankId=bankId;
        this.mobileNumber=mobileNumber;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public cipherApi.pojo.crnNumber getCrnNumber() {
        return crnNumber;
    }

    public void setCrnNumber(cipherApi.pojo.crnNumber crnNumber) {
        this.crnNumber = crnNumber;
    }

    public cipherApi.pojo.deviceDetails getDeviceDetails() {
        return deviceDetails;
    }

    public void setDeviceDetails(cipherApi.pojo.deviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public cipherApi.pojo.activityType getActivityType() {
        return activityType;
    }

    public void setActivityType(cipherApi.pojo.activityType activityType) {
        this.activityType = activityType;
    }

    public cipherApi.pojo.sessionId getSessionId() {
        return sessionId;
    }

    public void setSessionId(cipherApi.pojo.sessionId sessionId) {
        this.sessionId = sessionId;
    }

    /**@Override
    public String toString()
    {
        return "{type="
                + type
                + ", bankId="
                + bankId
                + ", mobileNumber="
                + mobileNumber
                + ", crnNumber="
                + crnNumber+ "}";
    }*/
}