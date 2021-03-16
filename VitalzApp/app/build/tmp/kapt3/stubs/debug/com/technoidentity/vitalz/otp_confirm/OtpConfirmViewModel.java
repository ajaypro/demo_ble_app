package com.technoidentity.vitalz.otp_confirm;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020#2\u0006\u0010\u001c\u001a\u00020\u001dR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\tR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!\u00a8\u0006\'"}, d2 = {"Lcom/technoidentity/vitalz/otp_confirm/OtpConfirmViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "mobileNumber", "Landroidx/lifecycle/MutableLiveData;", "", "getMobileNumber", "()Landroidx/lifecycle/MutableLiveData;", "setMobileNumber", "(Landroidx/lifecycle/MutableLiveData;)V", "otp1", "getOtp1", "setOtp1", "otp2", "getOtp2", "setOtp2", "otp3", "getOtp3", "setOtp3", "otp4", "getOtp4", "setOtp4", "otp5", "getOtp5", "setOtp5", "otp6", "getOtp6", "setOtp6", "otpConfirmInterface", "Lcom/technoidentity/vitalz/otp_confirm/OtpConfirmInterface;", "getOtpConfirmInterface", "()Lcom/technoidentity/vitalz/otp_confirm/OtpConfirmInterface;", "setOtpConfirmInterface", "(Lcom/technoidentity/vitalz/otp_confirm/OtpConfirmInterface;)V", "onConfirmOtp", "", "view", "Landroid/view/View;", "setInterface", "app_debug"})
public final class OtpConfirmViewModel extends androidx.lifecycle.ViewModel {
    public com.technoidentity.vitalz.otp_confirm.OtpConfirmInterface otpConfirmInterface;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> mobileNumber;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> otp1;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> otp2;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> otp3;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> otp4;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> otp5;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> otp6;
    
    @org.jetbrains.annotations.NotNull()
    public final com.technoidentity.vitalz.otp_confirm.OtpConfirmInterface getOtpConfirmInterface() {
        return null;
    }
    
    public final void setOtpConfirmInterface(@org.jetbrains.annotations.NotNull()
    com.technoidentity.vitalz.otp_confirm.OtpConfirmInterface p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getMobileNumber() {
        return null;
    }
    
    public final void setMobileNumber(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getOtp1() {
        return null;
    }
    
    public final void setOtp1(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getOtp2() {
        return null;
    }
    
    public final void setOtp2(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getOtp3() {
        return null;
    }
    
    public final void setOtp3(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getOtp4() {
        return null;
    }
    
    public final void setOtp4(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getOtp5() {
        return null;
    }
    
    public final void setOtp5(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getOtp6() {
        return null;
    }
    
    public final void setOtp6(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    public final void setInterface(@org.jetbrains.annotations.NotNull()
    com.technoidentity.vitalz.otp_confirm.OtpConfirmInterface otpConfirmInterface) {
    }
    
    public final void onConfirmOtp(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public OtpConfirmViewModel() {
        super();
    }
}