package pojo;

public class activityType {

    BalanceInquiry BalanceInquiry;
    AddBeneficiary AddBeneficiary;
    AmountWithdrawal AmountWithdrawal;

    /*public activityType(String activityType) {
        this.BalanceInquiry=BalanceInquiry;
        this.AddBeneficiary= AddBeneficiary;
        this.AmountWithdrawal= AmountWithdrawal;

    }*/

    public pojo.BalanceInquiry getBalanceInquiry() {
        return BalanceInquiry;
    }

    public void setBalanceInquiry(pojo.BalanceInquiry balanceInquiry) {
        BalanceInquiry = balanceInquiry;
    }

    public pojo.AddBeneficiary getAddBeneficiary() {
        return AddBeneficiary;
    }

    public void setAddBeneficiary(pojo.AddBeneficiary addBeneficiary) {
        AddBeneficiary = addBeneficiary;
    }

    public pojo.AmountWithdrawal getAmountWithdrawal() {
        return AmountWithdrawal;
    }

    public void setAmountWithdrawal(pojo.AmountWithdrawal amountWithdrawal) {
        AmountWithdrawal = amountWithdrawal;
    }
}