// Generated by data binding compiler. Do not edit!
package com.technoidentity.vitalz.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.technoidentity.vitalz.R;
import com.technoidentity.vitalz.request_otp.RequestOtpViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class RequestOtpBinding extends ViewDataBinding {
  @NonNull
  public final Button btnRequestOtp;

  @NonNull
  public final EditText etMobileNumber;

  @NonNull
  public final ImageView imageViewVitalz;

  @Bindable
  protected RequestOtpViewModel mViewModel;

  protected RequestOtpBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnRequestOtp, EditText etMobileNumber, ImageView imageViewVitalz) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnRequestOtp = btnRequestOtp;
    this.etMobileNumber = etMobileNumber;
    this.imageViewVitalz = imageViewVitalz;
  }

  public abstract void setViewModel(@Nullable RequestOtpViewModel viewModel);

  @Nullable
  public RequestOtpViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static RequestOtpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_request_otp, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static RequestOtpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<RequestOtpBinding>inflateInternal(inflater, R.layout.activity_request_otp, root, attachToRoot, component);
  }

  @NonNull
  public static RequestOtpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_request_otp, null, false, component)
   */
  @NonNull
  @Deprecated
  public static RequestOtpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<RequestOtpBinding>inflateInternal(inflater, R.layout.activity_request_otp, null, false, component);
  }

  public static RequestOtpBinding bind(@NonNull View view) {
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
  public static RequestOtpBinding bind(@NonNull View view, @Nullable Object component) {
    return (RequestOtpBinding)bind(component, view, R.layout.activity_request_otp);
  }
}
