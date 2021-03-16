// Generated by data binding compiler. Do not edit!
package com.technoidentity.vitalz.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.technoidentity.vitalz.R;
import com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class OtpConfirmBinding extends ViewDataBinding {
  @NonNull
  public final Button btnConfirmOtp;

  @NonNull
  public final EditText etOtp1;

  @NonNull
  public final EditText etOtp2;

  @NonNull
  public final EditText etOtp3;

  @NonNull
  public final EditText etOtp4;

  @NonNull
  public final EditText etOtp5;

  @NonNull
  public final EditText etOtp6;

  @NonNull
  public final ImageView imageViewVitalz;

  @NonNull
  public final RelativeLayout relativeLayoutMobile;

  @NonNull
  public final RelativeLayout relativeLayoutOtp;

  @NonNull
  public final TextView txMobileNumber;

  @NonNull
  public final TextView txPleaseWait;

  @NonNull
  public final TextView txSendTo;

  @NonNull
  public final TextView txWaitingOtp;

  @Bindable
  protected OtpConfirmViewModel mViewModel;

  protected OtpConfirmBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnConfirmOtp, EditText etOtp1, EditText etOtp2, EditText etOtp3, EditText etOtp4,
      EditText etOtp5, EditText etOtp6, ImageView imageViewVitalz,
      RelativeLayout relativeLayoutMobile, RelativeLayout relativeLayoutOtp,
      TextView txMobileNumber, TextView txPleaseWait, TextView txSendTo, TextView txWaitingOtp) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnConfirmOtp = btnConfirmOtp;
    this.etOtp1 = etOtp1;
    this.etOtp2 = etOtp2;
    this.etOtp3 = etOtp3;
    this.etOtp4 = etOtp4;
    this.etOtp5 = etOtp5;
    this.etOtp6 = etOtp6;
    this.imageViewVitalz = imageViewVitalz;
    this.relativeLayoutMobile = relativeLayoutMobile;
    this.relativeLayoutOtp = relativeLayoutOtp;
    this.txMobileNumber = txMobileNumber;
    this.txPleaseWait = txPleaseWait;
    this.txSendTo = txSendTo;
    this.txWaitingOtp = txWaitingOtp;
  }

  public abstract void setViewModel(@Nullable OtpConfirmViewModel viewModel);

  @Nullable
  public OtpConfirmViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static OtpConfirmBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_otp_conform, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static OtpConfirmBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<OtpConfirmBinding>inflateInternal(inflater, R.layout.activity_otp_conform, root, attachToRoot, component);
  }

  @NonNull
  public static OtpConfirmBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_otp_conform, null, false, component)
   */
  @NonNull
  @Deprecated
  public static OtpConfirmBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<OtpConfirmBinding>inflateInternal(inflater, R.layout.activity_otp_conform, null, false, component);
  }

  public static OtpConfirmBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static OtpConfirmBinding bind(@NonNull View view, @Nullable Object component) {
    return (OtpConfirmBinding)bind(component, view, R.layout.activity_otp_conform);
  }
}
