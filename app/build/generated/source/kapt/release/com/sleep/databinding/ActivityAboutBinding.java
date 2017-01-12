package com.sleep.databinding;
import com.sleep.R;
import com.sleep.BR;
import android.view.View;
public class ActivityAboutBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 4);
    }
    // views
    public final android.widget.LinearLayout activityAbout;
    public final android.widget.TextView appDescription;
    public final android.widget.TextView appWarning;
    public final android.support.v7.widget.Toolbar toolbar;
    public final android.widget.TextView versionName;
    // variables
    private java.lang.String mVersion;
    private java.lang.String mDesc;
    private java.lang.String mWarning;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAboutBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.activityAbout = (android.widget.LinearLayout) bindings[0];
        this.activityAbout.setTag(null);
        this.appDescription = (android.widget.TextView) bindings[2];
        this.appDescription.setTag(null);
        this.appWarning = (android.widget.TextView) bindings[1];
        this.appWarning.setTag(null);
        this.toolbar = (android.support.v7.widget.Toolbar) bindings[4];
        this.versionName = (android.widget.TextView) bindings[3];
        this.versionName.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.version :
                setVersion((java.lang.String) variable);
                return true;
            case BR.desc :
                setDesc((java.lang.String) variable);
                return true;
            case BR.warning :
                setWarning((java.lang.String) variable);
                return true;
        }
        return false;
    }

    public void setVersion(java.lang.String version) {
        this.mVersion = version;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.version);
        super.requestRebind();
    }
    public java.lang.String getVersion() {
        return mVersion;
    }
    public void setDesc(java.lang.String desc) {
        this.mDesc = desc;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.desc);
        super.requestRebind();
    }
    public java.lang.String getDesc() {
        return mDesc;
    }
    public void setWarning(java.lang.String warning) {
        this.mWarning = warning;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.warning);
        super.requestRebind();
    }
    public java.lang.String getWarning() {
        return mWarning;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        java.lang.String version = mVersion;
        java.lang.String desc = mDesc;
        java.lang.String warning = mWarning;

        if ((dirtyFlags & 0x9L) != 0) {
        }
        if ((dirtyFlags & 0xaL) != 0) {
        }
        if ((dirtyFlags & 0xcL) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0xaL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.appDescription, desc);
        }
        if ((dirtyFlags & 0xcL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.appWarning, warning);
        }
        if ((dirtyFlags & 0x9L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.versionName, version);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityAboutBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityAboutBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityAboutBinding>inflate(inflater, com.sleep.R.layout.activity_about, root, attachToRoot, bindingComponent);
    }
    public static ActivityAboutBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityAboutBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.sleep.R.layout.activity_about, null, false), bindingComponent);
    }
    public static ActivityAboutBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityAboutBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_about_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityAboutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): version
        flag 1 (0x2L): desc
        flag 2 (0x3L): warning
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}