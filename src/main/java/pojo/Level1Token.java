package pojo;

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

    public pojo.crnNumber getCrnNumber() {
        return crnNumber;
    }

    public void setCrnNumber(pojo.crnNumber crnNumber) {
        this.crnNumber = crnNumber;
    }

    public pojo.deviceDetails getDeviceDetails() {
        return deviceDetails;
    }

    public void setDeviceDetails(pojo.deviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public pojo.activityType getActivityType() {
        return activityType;
    }

    public void setActivityType(pojo.activityType activityType) {
        this.activityType = activityType;
    }

    public pojo.sessionId getSessionId() {
        return sessionId;
    }

    public void setSessionId(pojo.sessionId sessionId) {
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