package com.technoidentity.vitalz.databinding;
import com.technoidentity.vitalz.R;
import com.technoidentity.vitalz.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class RequestOtpBindingImpl extends RequestOtpBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageView_vitalz, 3);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mViewModelOnRequestOtpAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener etMobileNumberandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.mobileNumber.getValue()
            //         is viewModel.mobileNumber.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etMobileNumber);
            // localize variables for thread safety
            // viewModel.mobileNumber != null
            boolean viewModelMobileNumberJavaLangObjectNull = false;
            // viewModel.mobileNumber.getValue()
            java.lang.String viewModelMobileNumberGetValue = null;
            // viewModel.mobileNumber
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelMobileNumber = null;
            // viewModel
            com.technoidentity.vitalz.request_otp.RequestOtpViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelMobileNumber = viewModel.getMobileNumber();

                viewModelMobileNumberJavaLangObjectNull = (viewModelMobileNumber) != (null);
                if (viewModelMobileNumberJavaLangObjectNull) {




                    viewModelMobileNumber.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public RequestOtpBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private RequestOtpBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.Button) bindings[2]
            , (android.widget.EditText) bindings[1]
            , (android.widget.ImageView) bindings[3]
            );
        this.btnRequestOtp.setTag(null);
        this.etMobileNumber.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((com.technoidentity.vitalz.request_otp.RequestOtpViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.technoidentity.vitalz.request_otp.RequestOtpViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelMobileNumber((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelMobileNumber(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelMobileNumber, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String viewModelMobileNumberGetValue = null;
        android.view.View.OnClickListener viewModelOnRequestOtpAndroidViewViewOnClickListener = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelMobileNumber = null;
        com.technoidentity.vitalz.request_otp.RequestOtpViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x7L) != 0) {


            if ((dirtyFlags & 0x6L) != 0) {

                    if (viewModel != null) {
                        // read viewModel::onRequestOtp
                        viewModelOnRequestOtpAndroidViewViewOnClickListener = (((mViewModelOnRequestOtpAndroidViewViewOnClickListener == null) ? (mViewModelOnRequestOtpAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mViewModelOnRequestOtpAndroidViewViewOnClickListener).setValue(viewModel));
                    }
            }

                if (viewModel != null) {
                    // read viewModel.mobileNumber
                    viewModelMobileNumber = viewModel.getMobileNumber();
                }
                updateLiveDataRegistration(0, viewModelMobileNumber);


                if (viewModelMobileNumber != null) {
                    // read viewModel.mobileNumber.getValue()
                    viewModelMobileNumberGetValue = viewModelMobileNumber.getValue();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.btnRequestOtp.setOnClickListener(viewModelOnRequestOtpAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etMobileNumber, viewModelMobileNumberGetValue);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etMobileNumber, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etMobileNumberandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.technoidentity.vitalz.request_otp.RequestOtpViewModel value;
        public OnClickListenerImpl setValue(com.technoidentity.vitalz.request_otp.RequestOtpViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onRequestOtp(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.mobileNumber
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}