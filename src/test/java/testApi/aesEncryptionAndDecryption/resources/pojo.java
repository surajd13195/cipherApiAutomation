package testApi.aesEncryptionAndDecryption.resources;

import java.util.List;

public class pojo {

    private String type;
    private String bankId;
    private String mobileNumber;
    private List<String> crnNumber;

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

    public List<String> getCrnNumber() {
        return crnNumber;
    }

    public void setCrnNumber(List<String> crnNumber) {
        this.crnNumber = crnNumber;
    }

    @Override
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
    }
}