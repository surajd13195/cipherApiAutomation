package pojo;

public class deviceDetails {

    public String deviceId;
    public String deviceOs;
    public String deviceOsVersion;
    public String deviceIpAddress;
    public String bankAppVersion;

    public deviceDetails(String deviceId, String deviceOs, String deviceOsVersion, String deviceIpAddress, String bankAppVersion){

        this.deviceId=deviceId;
        this.deviceOs=deviceOs;
        this.deviceOsVersion=deviceOsVersion;
        this.deviceIpAddress=deviceIpAddress;
        this.bankAppVersion=bankAppVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public String getDeviceOsVersion() {
        return deviceOsVersion;
    }

    public void setDeviceOsVersion(String deviceOsVersion) {
        this.deviceOsVersion = deviceOsVersion;
    }

    public String getDeviceIpAddress() {
        return deviceIpAddress;
    }

    public void setDeviceIpAddress(String deviceIpAddress) {
        this.deviceIpAddress = deviceIpAddress;
    }

    public String getBankAppVersion() {
        return bankAppVersion;
    }

    public void setBankAppVersion(String bankAppVersion) {
        this.bankAppVersion = bankAppVersion;
    }
}