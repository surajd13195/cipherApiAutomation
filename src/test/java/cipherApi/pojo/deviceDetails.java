package cipherApi.pojo;

public class deviceDetails {

    public String bankAppVersion;
    public String deviceId;
    public String deviceIpAddress;
    public String deviceOs;
    public String deviceOsVersion;

    public deviceDetails(String bankAppVersion, String deviceId, String deviceIpAddress, String deviceOs, String deviceOsVersion){
        this.bankAppVersion=bankAppVersion;
        this.deviceId=deviceId;
        this.deviceIpAddress=deviceIpAddress;
        this.deviceOs=deviceOs;
        this.deviceOsVersion=deviceOsVersion;
    }

    public String getBankAppVersion() {
        return bankAppVersion;
    }

    public void setBankAppVersion(String bankAppVersion) {
        this.bankAppVersion = bankAppVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIpAddress() {
        return deviceIpAddress;
    }

    public void setDeviceIpAddress(String deviceIpAddress) {
        this.deviceIpAddress = deviceIpAddress;
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
}