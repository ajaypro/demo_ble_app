package com.technoidentity.vitalz.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b(\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NJ\u0010\u0010O\u001a\u00020P2\u0006\u0010M\u001a\u00020NH\u0007J.\u0010Q\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u0006\u0010R\u001a\u00020\u00042\u0006\u0010S\u001a\u00020\u00042\u0006\u0010T\u001a\u00020-2\u0006\u0010U\u001a\u00020-R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u0014\u0010\u001b\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u001a\u0010\u001d\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0006\"\u0004\b\u001f\u0010\bR\u001a\u0010 \u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\bR\u001a\u0010#\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0006\"\u0004\b%\u0010\bR\u001a\u0010&\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u0006\"\u0004\b(\u0010\bR\u001a\u0010)\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0006\"\u0004\b+\u0010\bR\u0014\u0010,\u001a\u00020-X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001a\u00100\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0006\"\u0004\b2\u0010\bR\u001a\u00103\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0006\"\u0004\b5\u0010\bR\u001a\u00106\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0006\"\u0004\b8\u0010\bR\u001a\u00109\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0006\"\u0004\b;\u0010\bR\u001a\u0010<\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0006\"\u0004\b>\u0010\bR\u001a\u0010?\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0006\"\u0004\bA\u0010\bR\u001a\u0010B\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0006\"\u0004\bD\u0010\bR\u001a\u0010E\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0006\"\u0004\bG\u0010\bR\u001a\u0010H\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0006\"\u0004\bJ\u0010\b\u00a8\u0006V"}, d2 = {"Lcom/technoidentity/vitalz/network/Constants;", "", "()V", "BRANCHID", "", "getBRANCHID", "()Ljava/lang/String;", "setBRANCHID", "(Ljava/lang/String;)V", "BRANCHORGID", "getBRANCHORGID", "setBRANCHORGID", "CUSTOMERID", "getCUSTOMERID", "setCUSTOMERID", "DESIGNATION", "getDESIGNATION", "setDESIGNATION", "EMAIL", "getEMAIL", "setEMAIL", "FIRST_NAME", "getFIRST_NAME", "setFIRST_NAME", "ID", "getID", "setID", "IMAGE_STORED_DIRECTORY", "getIMAGE_STORED_DIRECTORY", "MOBILE_NUMBER", "getMOBILE_NUMBER", "setMOBILE_NUMBER", "ORDERTYPE", "getORDERTYPE", "setORDERTYPE", "OUTLETADDRESS", "getOUTLETADDRESS", "setOUTLETADDRESS", "OUTLETNAME", "getOUTLETNAME", "setOUTLETNAME", "OUTLETSITENUMBER", "getOUTLETSITENUMBER", "setOUTLETSITENUMBER", "PERMISSIONS_REQUEST_FOR_READ_EXTERNAL_STORAGE", "", "getPERMISSIONS_REQUEST_FOR_READ_EXTERNAL_STORAGE", "()I", "PREFERENCE_NAME", "getPREFERENCE_NAME", "setPREFERENCE_NAME", "PRICELIST", "getPRICELIST", "setPRICELIST", "PRODUCTID", "getPRODUCTID", "setPRODUCTID", "PRODUCTIDS", "getPRODUCTIDS", "setPRODUCTIDS", "SELECTED_ORDER_TYPE", "getSELECTED_ORDER_TYPE", "setSELECTED_ORDER_TYPE", "SELECTED_OUTLET", "getSELECTED_OUTLET", "setSELECTED_OUTLET", "TOKEN", "getTOKEN", "setTOKEN", "USERNAME", "getUSERNAME", "setUSERNAME", "emailPattern", "getEmailPattern", "setEmailPattern", "InternetSettings", "", "ctx", "Landroid/content/Context;", "haveInternet", "", "showServerConnectionErrorDialog", "email", "password", "start", "end", "app_debug"})
public final class Constants {
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String TOKEN = "token";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String PREFERENCE_NAME = "vitalz";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String SELECTED_OUTLET = "selectedOutlet";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String SELECTED_ORDER_TYPE = "selectedOrderType";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String ID = "userId";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String PRODUCTID = "productId";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String PRICELIST = "priceList";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String OUTLETNAME = "outletName";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String OUTLETADDRESS = "outletAddress";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String OUTLETSITENUMBER = "outletSitenumber";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String BRANCHID = "barcnhId";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String ORDERTYPE = "orderType";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String BRANCHORGID = "branchOrgId";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String CUSTOMERID = "customerId";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String USERNAME = "username";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String FIRST_NAME = "firstName";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String EMAIL = "emailId";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String PRODUCTIDS = "setOfProductId";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String DESIGNATION = "designation";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String MOBILE_NUMBER = "mobileNumber";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String IMAGE_STORED_DIRECTORY = "/Digital_Sign";
    private static final int PERMISSIONS_REQUEST_FOR_READ_EXTERNAL_STORAGE = 123;
    @org.jetbrains.annotations.NotNull()
    public static final com.technoidentity.vitalz.network.Constants INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTOKEN() {
        return null;
    }
    
    public final void setTOKEN(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPREFERENCE_NAME() {
        return null;
    }
    
    public final void setPREFERENCE_NAME(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSELECTED_OUTLET() {
        return null;
    }
    
    public final void setSELECTED_OUTLET(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSELECTED_ORDER_TYPE() {
        return null;
    }
    
    public final void setSELECTED_ORDER_TYPE(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getID() {
        return null;
    }
    
    public final void setID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPRODUCTID() {
        return null;
    }
    
    public final void setPRODUCTID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPRICELIST() {
        return null;
    }
    
    public final void setPRICELIST(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOUTLETNAME() {
        return null;
    }
    
    public final void setOUTLETNAME(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOUTLETADDRESS() {
        return null;
    }
    
    public final void setOUTLETADDRESS(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOUTLETSITENUMBER() {
        return null;
    }
    
    public final void setOUTLETSITENUMBER(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBRANCHID() {
        return null;
    }
    
    public final void setBRANCHID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getORDERTYPE() {
        return null;
    }
    
    public final void setORDERTYPE(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBRANCHORGID() {
        return null;
    }
    
    public final void setBRANCHORGID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCUSTOMERID() {
        return null;
    }
    
    public final void setCUSTOMERID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUSERNAME() {
        return null;
    }
    
    public final void setUSERNAME(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFIRST_NAME() {
        return null;
    }
    
    public final void setFIRST_NAME(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEMAIL() {
        return null;
    }
    
    public final void setEMAIL(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPRODUCTIDS() {
        return null;
    }
    
    public final void setPRODUCTIDS(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDESIGNATION() {
        return null;
    }
    
    public final void setDESIGNATION(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMOBILE_NUMBER() {
        return null;
    }
    
    public final void setMOBILE_NUMBER(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmailPattern() {
        return null;
    }
    
    public final void setEmailPattern(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIMAGE_STORED_DIRECTORY() {
        return null;
    }
    
    public final int getPERMISSIONS_REQUEST_FOR_READ_EXTERNAL_STORAGE() {
        return 0;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.KITKAT)
    public final boolean haveInternet(@org.jetbrains.annotations.NotNull()
    android.content.Context ctx) {
        return false;
    }
    
    public final void InternetSettings(@org.jetbrains.annotations.NotNull()
    android.content.Context ctx) {
    }
    
    public final void showServerConnectionErrorDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context ctx, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, int start, int end) {
    }
    
    private Constants() {
        super();
    }
}