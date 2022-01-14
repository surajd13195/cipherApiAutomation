package cipherApi.pojo;

public class level1Pojo {

    public String type;
    public String bankId;
    public String mobileNumber;
    crnNumber crnNumber;
    deviceDetails deviceDetails;

    public level1Pojo(String type, String bankId, String mobileNumber){
        this.type=type;
        this.bankId=bankId;
        this.mobileNumber=mobileNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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