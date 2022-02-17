package pojo;

public class pojo1 {

    public String bankId;
    public String mobileNumber;
    crnNumber crnNumber;
    deviceDetails deviceDetails;
    public String activityType;
    sessionId sessionId;
    public String levelTag;

    public pojo1(String bankId, String mobileNumber, String activityType, String levelTag){
        this.bankId=bankId;
        this.mobileNumber=mobileNumber;
        this.activityType=activityType;
        this.levelTag=levelTag;
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

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public pojo.sessionId getSessionId() {
        return sessionId;
    }

    public void setSessionId(pojo.sessionId sessionId) {
        this.sessionId = sessionId;
    }

    public String getLevelTag() {
        return levelTag;
    }

    public void setLevelTag(String levelTag) {
        this.levelTag = levelTag;
    }
}