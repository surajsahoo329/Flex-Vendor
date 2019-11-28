package com.example.flexvendor;

public class Vendor {

    private String vendorId;
    private String vendorName;
    private String vendorMail;
    private String vendorPhone;
    private int companyID;
    private int vendorPhotoFlag;

    //Constructor
    //Function Overloading(Look it up)

    //Another constructor to initialize our varibales
    Vendor(String vendorId, String userName, String userMail, String userPhone, int companyID, int userPhotoFlag) {
        this.vendorId=vendorId;
        this.vendorName= userName;
        this.vendorMail= userMail;
        this.vendorPhone= userPhone;
        this.companyID = companyID;
        this.vendorPhotoFlag= userPhotoFlag;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getVendorMail() {
        return vendorMail;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public int getCompanyID() {return companyID; }

    public int getVendorPhotoFlag() { return vendorPhotoFlag; }
}
