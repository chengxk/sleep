
package android.databinding;
import com.sleep.BR;
class DataBinderMapper {
    final static int TARGET_MIN_SDK = 14;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.sleep.R.layout.activity_main:
                    return com.sleep.databinding.ActivityMainBinding.bind(view, bindingComponent);
                case com.sleep.R.layout.fragment_main:
                    return com.sleep.databinding.FragmentMainBinding.bind(view, bindingComponent);
                case com.sleep.R.layout.activity_about:
                    return com.sleep.databinding.ActivityAboutBinding.bind(view, bindingComponent);
                case com.sleep.R.layout.fragment_add:
                    return com.sleep.databinding.FragmentAddBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 423753077: {
                if(tag.equals("layout/activity_main_0")) {
                    return com.sleep.R.layout.activity_main;
                }
                break;
            }
            case -985887980: {
                if(tag.equals("layout/fragment_main_0")) {
                    return com.sleep.R.layout.fragment_main;
                }
                break;
            }
            case -1774265581: {
                if(tag.equals("layout/activity_about_0")) {
                    return com.sleep.R.layout.activity_about;
                }
                break;
            }
            case -458443032: {
                if(tag.equals("layout/fragment_add_0")) {
                    return com.sleep.R.layout.fragment_add;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"desc"
            ,"progress"
            ,"version"
            ,"warning"};
    }
}