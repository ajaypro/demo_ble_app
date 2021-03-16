package com.technoidentity.vitalz.request_otp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/technoidentity/vitalz/request_otp/RequestOtpViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "mobileNumber", "Landroidx/lifecycle/MutableLiveData;", "", "getMobileNumber", "()Landroidx/lifecycle/MutableLiveData;", "setMobileNumber", "(Landroidx/lifecycle/MutableLiveData;)V", "requestOtpInterface", "Lcom/technoidentity/vitalz/request_otp/RequestOtpInterface;", "onRequestOtp", "", "view", "Landroid/view/View;", "setInterface", "app_debug"})
public final class RequestOtpViewModel extends androidx.lifecycle.ViewModel {
    private com.technoidentity.vitalz.request_otp.RequestOtpInterface requestOtpInterface;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> mobileNumber;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getMobileNumber() {
        return null;
    }
    
    public final void setMobileNumber(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    public final void setInterface(@org.jetbrains.annotations.NotNull()
    com.technoidentity.vitalz.request_otp.RequestOtpInterface requestOtpInterface) {
    }
    
    public final void onRequestOtp(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public RequestOtpViewModel() {
        super();
    }
}