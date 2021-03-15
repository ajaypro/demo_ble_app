package com.technoidentity.vitalz;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.technoidentity.vitalz.databinding.AddDeviceBindingImpl;
import com.technoidentity.vitalz.databinding.OtpConfirmBindingImpl;
import com.technoidentity.vitalz.databinding.RequestOtpBindingImpl;
import com.technoidentity.vitalz.databinding.SplashScreenBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYADDDEVICE = 1;

  private static final int LAYOUT_ACTIVITYOTPCONFORM = 2;

  private static final int LAYOUT_ACTIVITYREQUESTOTP = 3;

  private static final int LAYOUT_ACTIVITYSPLASHSCREEN = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.technoidentity.vitalz.R.layout.activity_add_device, LAYOUT_ACTIVITYADDDEVICE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.technoidentity.vitalz.R.layout.activity_otp_conform, LAYOUT_ACTIVITYOTPCONFORM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.technoidentity.vitalz.R.layout.activity_request_otp, LAYOUT_ACTIVITYREQUESTOTP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.technoidentity.vitalz.R.layout.activity_splash_screen, LAYOUT_ACTIVITYSPLASHSCREEN);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYADDDEVICE: {
          if ("layout/activity_add_device_0".equals(tag)) {
            return new AddDeviceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_add_device is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYOTPCONFORM: {
          if ("layout/activity_otp_conform_0".equals(tag)) {
            return new OtpConfirmBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_otp_conform is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYREQUESTOTP: {
          if ("layout/activity_request_otp_0".equals(tag)) {
            return new RequestOtpBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_request_otp is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSPLASHSCREEN: {
          if ("layout/activity_splash_screen_0".equals(tag)) {
            return new SplashScreenBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_splash_screen is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/activity_add_device_0", com.technoidentity.vitalz.R.layout.activity_add_device);
      sKeys.put("layout/activity_otp_conform_0", com.technoidentity.vitalz.R.layout.activity_otp_conform);
      sKeys.put("layout/activity_request_otp_0", com.technoidentity.vitalz.R.layout.activity_request_otp);
      sKeys.put("layout/activity_splash_screen_0", com.technoidentity.vitalz.R.layout.activity_splash_screen);
    }
  }
}
