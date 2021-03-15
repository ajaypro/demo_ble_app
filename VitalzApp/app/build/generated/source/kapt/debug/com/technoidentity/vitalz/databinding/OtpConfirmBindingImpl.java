package com.technoidentity.vitalz.databinding;
import com.technoidentity.vitalz.R;
import com.technoidentity.vitalz.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class OtpConfirmBindingImpl extends OtpConfirmBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageView_vitalz, 9);
        sViewsWithIds.put(R.id.tx_waiting_otp, 10);
        sViewsWithIds.put(R.id.tx_please_wait, 11);
        sViewsWithIds.put(R.id.relative_layout_mobile, 12);
        sViewsWithIds.put(R.id.tx_send_to, 13);
        sViewsWithIds.put(R.id.relative_layout_otp, 14);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mViewModelOnConfirmOtpAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener etOtp1androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.otp1.getValue()
            //         is viewModel.otp1.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etOtp1);
            // localize variables for thread safety
            // viewModel.otp1
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp1 = null;
            // viewModel.otp1 != null
            boolean viewModelOtp1JavaLangObjectNull = false;
            // viewModel.otp1.getValue()
            java.lang.String viewModelOtp1GetValue = null;
            // viewModel
            com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelOtp1 = viewModel.getOtp1();

                viewModelOtp1JavaLangObjectNull = (viewModelOtp1) != (null);
                if (viewModelOtp1JavaLangObjectNull) {




                    viewModelOtp1.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener etOtp2androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.otp2.getValue()
            //         is viewModel.otp2.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etOtp2);
            // localize variables for thread safety
            // viewModel.otp2 != null
            boolean viewModelOtp2JavaLangObjectNull = false;
            // viewModel
            com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel viewModel = mViewModel;
            // viewModel.otp2.getValue()
            java.lang.String viewModelOtp2GetValue = null;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.otp2
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp2 = null;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelOtp2 = viewModel.getOtp2();

                viewModelOtp2JavaLangObjectNull = (viewModelOtp2) != (null);
                if (viewModelOtp2JavaLangObjectNull) {




                    viewModelOtp2.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener etOtp3androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.otp3.getValue()
            //         is viewModel.otp3.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etOtp3);
            // localize variables for thread safety
            // viewModel
            com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.otp3 != null
            boolean viewModelOtp3JavaLangObjectNull = false;
            // viewModel.otp3.getValue()
            java.lang.String viewModelOtp3GetValue = null;
            // viewModel.otp3
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp3 = null;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelOtp3 = viewModel.getOtp3();

                viewModelOtp3JavaLangObjectNull = (viewModelOtp3) != (null);
                if (viewModelOtp3JavaLangObjectNull) {




                    viewModelOtp3.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener etOtp4androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.otp4.getValue()
            //         is viewModel.otp4.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etOtp4);
            // localize variables for thread safety
            // viewModel.otp4.getValue()
            java.lang.String viewModelOtp4GetValue = null;
            // viewModel.otp4 != null
            boolean viewModelOtp4JavaLangObjectNull = false;
            // viewModel
            com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel viewModel = mViewModel;
            // viewModel.otp4
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp4 = null;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelOtp4 = viewModel.getOtp4();

                viewModelOtp4JavaLangObjectNull = (viewModelOtp4) != (null);
                if (viewModelOtp4JavaLangObjectNull) {




                    viewModelOtp4.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener etOtp5androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.otp5.getValue()
            //         is viewModel.otp5.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etOtp5);
            // localize variables for thread safety
            // viewModel.otp5.getValue()
            java.lang.String viewModelOtp5GetValue = null;
            // viewModel.otp5 != null
            boolean viewModelOtp5JavaLangObjectNull = false;
            // viewModel
            com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.otp5
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp5 = null;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelOtp5 = viewModel.getOtp5();

                viewModelOtp5JavaLangObjectNull = (viewModelOtp5) != (null);
                if (viewModelOtp5JavaLangObjectNull) {




                    viewModelOtp5.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener etOtp6androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.otp6.getValue()
            //         is viewModel.otp6.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etOtp6);
            // localize variables for thread safety
            // viewModel.otp6.getValue()
            java.lang.String viewModelOtp6GetValue = null;
            // viewModel.otp6 != null
            boolean viewModelOtp6JavaLangObjectNull = false;
            // viewModel.otp6
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp6 = null;
            // viewModel
            com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelOtp6 = viewModel.getOtp6();

                viewModelOtp6JavaLangObjectNull = (viewModelOtp6) != (null);
                if (viewModelOtp6JavaLangObjectNull) {




                    viewModelOtp6.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener txMobileNumberandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.mobileNumber.getValue()
            //         is viewModel.mobileNumber.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(txMobileNumber);
            // localize variables for thread safety
            // viewModel.mobileNumber != null
            boolean viewModelMobileNumberJavaLangObjectNull = false;
            // viewModel.mobileNumber.getValue()
            java.lang.String viewModelMobileNumberGetValue = null;
            // viewModel.mobileNumber
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelMobileNumber = null;
            // viewModel
            com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel viewModel = mViewModel;
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

    public OtpConfirmBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private OtpConfirmBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7
            , (android.widget.Button) bindings[8]
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[5]
            , (android.widget.EditText) bindings[6]
            , (android.widget.EditText) bindings[7]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.RelativeLayout) bindings[12]
            , (android.widget.RelativeLayout) bindings[14]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[10]
            );
        this.btnConfirmOtp.setTag(null);
        this.etOtp1.setTag(null);
        this.etOtp2.setTag(null);
        this.etOtp3.setTag(null);
        this.etOtp4.setTag(null);
        this.etOtp5.setTag(null);
        this.etOtp6.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txMobileNumber.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x100L;
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
            setViewModel((com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x80L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelOtp1((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelMobileNumber((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelOtp6((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeViewModelOtp4((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewModelOtp5((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangeViewModelOtp2((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeViewModelOtp3((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelOtp1(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelOtp1, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelMobileNumber(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelMobileNumber, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelOtp6(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelOtp6, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelOtp4(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelOtp4, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelOtp5(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelOtp5, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelOtp2(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelOtp2, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelOtp3(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelOtp3, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
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
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp1 = null;
        java.lang.String viewModelOtp6GetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelMobileNumber = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp6 = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp4 = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp5 = null;
        java.lang.String viewModelOtp3GetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp2 = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelOtp3 = null;
        java.lang.String viewModelMobileNumberGetValue = null;
        java.lang.String viewModelOtp4GetValue = null;
        java.lang.String viewModelOtp2GetValue = null;
        java.lang.String viewModelOtp5GetValue = null;
        android.view.View.OnClickListener viewModelOnConfirmOtpAndroidViewViewOnClickListener = null;
        java.lang.String viewModelOtp1GetValue = null;
        com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x1ffL) != 0) {


            if ((dirtyFlags & 0x181L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.otp1
                        viewModelOtp1 = viewModel.getOtp1();
                    }
                    updateLiveDataRegistration(0, viewModelOtp1);


                    if (viewModelOtp1 != null) {
                        // read viewModel.otp1.getValue()
                        viewModelOtp1GetValue = viewModelOtp1.getValue();
                    }
            }
            if ((dirtyFlags & 0x182L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.mobileNumber
                        viewModelMobileNumber = viewModel.getMobileNumber();
                    }
                    updateLiveDataRegistration(1, viewModelMobileNumber);


                    if (viewModelMobileNumber != null) {
                        // read viewModel.mobileNumber.getValue()
                        viewModelMobileNumberGetValue = viewModelMobileNumber.getValue();
                    }
            }
            if ((dirtyFlags & 0x184L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.otp6
                        viewModelOtp6 = viewModel.getOtp6();
                    }
                    updateLiveDataRegistration(2, viewModelOtp6);


                    if (viewModelOtp6 != null) {
                        // read viewModel.otp6.getValue()
                        viewModelOtp6GetValue = viewModelOtp6.getValue();
                    }
            }
            if ((dirtyFlags & 0x188L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.otp4
                        viewModelOtp4 = viewModel.getOtp4();
                    }
                    updateLiveDataRegistration(3, viewModelOtp4);


                    if (viewModelOtp4 != null) {
                        // read viewModel.otp4.getValue()
                        viewModelOtp4GetValue = viewModelOtp4.getValue();
                    }
            }
            if ((dirtyFlags & 0x190L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.otp5
                        viewModelOtp5 = viewModel.getOtp5();
                    }
                    updateLiveDataRegistration(4, viewModelOtp5);


                    if (viewModelOtp5 != null) {
                        // read viewModel.otp5.getValue()
                        viewModelOtp5GetValue = viewModelOtp5.getValue();
                    }
            }
            if ((dirtyFlags & 0x1a0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.otp2
                        viewModelOtp2 = viewModel.getOtp2();
                    }
                    updateLiveDataRegistration(5, viewModelOtp2);


                    if (viewModelOtp2 != null) {
                        // read viewModel.otp2.getValue()
                        viewModelOtp2GetValue = viewModelOtp2.getValue();
                    }
            }
            if ((dirtyFlags & 0x1c0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.otp3
                        viewModelOtp3 = viewModel.getOtp3();
                    }
                    updateLiveDataRegistration(6, viewModelOtp3);


                    if (viewModelOtp3 != null) {
                        // read viewModel.otp3.getValue()
                        viewModelOtp3GetValue = viewModelOtp3.getValue();
                    }
            }
            if ((dirtyFlags & 0x180L) != 0) {

                    if (viewModel != null) {
                        // read viewModel::onConfirmOtp
                        viewModelOnConfirmOtpAndroidViewViewOnClickListener = (((mViewModelOnConfirmOtpAndroidViewViewOnClickListener == null) ? (mViewModelOnConfirmOtpAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mViewModelOnConfirmOtpAndroidViewViewOnClickListener).setValue(viewModel));
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x180L) != 0) {
            // api target 1

            this.btnConfirmOtp.setOnClickListener(viewModelOnConfirmOtpAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 0x181L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etOtp1, viewModelOtp1GetValue);
        }
        if ((dirtyFlags & 0x100L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etOtp1, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etOtp1androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etOtp2, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etOtp2androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etOtp3, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etOtp3androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etOtp4, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etOtp4androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etOtp5, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etOtp5androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etOtp6, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etOtp6androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.txMobileNumber, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, txMobileNumberandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x1a0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etOtp2, viewModelOtp2GetValue);
        }
        if ((dirtyFlags & 0x1c0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etOtp3, viewModelOtp3GetValue);
        }
        if ((dirtyFlags & 0x188L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etOtp4, viewModelOtp4GetValue);
        }
        if ((dirtyFlags & 0x190L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etOtp5, viewModelOtp5GetValue);
        }
        if ((dirtyFlags & 0x184L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etOtp6, viewModelOtp6GetValue);
        }
        if ((dirtyFlags & 0x182L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txMobileNumber, viewModelMobileNumberGetValue);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel value;
        public OnClickListenerImpl setValue(com.technoidentity.vitalz.otp_confirm.OtpConfirmViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onConfirmOtp(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.otp1
        flag 1 (0x2L): viewModel.mobileNumber
        flag 2 (0x3L): viewModel.otp6
        flag 3 (0x4L): viewModel.otp4
        flag 4 (0x5L): viewModel.otp5
        flag 5 (0x6L): viewModel.otp2
        flag 6 (0x7L): viewModel.otp3
        flag 7 (0x8L): viewModel
        flag 8 (0x9L): null
    flag mapping end*/
    //end
}