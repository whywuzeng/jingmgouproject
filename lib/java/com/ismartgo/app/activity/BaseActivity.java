// INTERNAL ERROR //

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\新建文件夹\jingminggou.bkill.com\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.BaseActivity
 * JD-Core Version:    0.6.2
 */
package com.ismartgo.app.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.bean.UserUsuallyAddress;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.DataUtil;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BaseActivity
        extends AbActivity
{
    public static String city = "广州";
    public static String lat;
    public static String location;
    public static String locationTime = "";
    public static String log = "113.368667";
    public static User loginUser;
    public static int pushSwitch;
    public static ArrayList<UserUsuallyAddress> usuallyAddressList;
    public static int voiceSwich;
    protected ImageView iv_right;
    private PushAgent mPushAgent;
    protected ToastDefine toast;
    protected ImageView tv_left;
    protected TextView tv_right;
    protected TextView tv_title;

    static
    {
        lat = "23.153316";
        location = "";
        pushSwitch = 1;
        voiceSwich = 0;
    }

    private void hideSoftInput(IBinder paramIBinder)
    {
        if (paramIBinder != null) {
            ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(paramIBinder, 2);
        }
    }

    private boolean isShouldHideInput(View paramView, MotionEvent paramMotionEvent)
    {
        int i;
        int j;
        int k;
        int m;
        if ((paramView != null) && ((paramView instanceof EditText)))
        {
            int[] arrayOfInt = new int[2];
            paramView.getLocationInWindow(arrayOfInt);
            i = arrayOfInt[0];
            j = arrayOfInt[1];
            k = paramView.getHeight();
            m = paramView.getWidth();
        }
        return (paramMotionEvent.getX() <= i) || (paramMotionEvent.getX() >= i + m) || (paramMotionEvent.getY() <= j) || (paramMotionEvent.getY() >= j + k);
    }

    public Gift Json2Gift(JSONObject paramJSONObject)
    {
        Gift localGift = new Gift();
        try
        {
            localGift.setGiftId(paramJSONObject.getLong("giftId"));
            localGift.setGiftLogo(Url.ADS_URL + paramJSONObject.getString("giftLogo"));
            localGift.setGiftName(paramJSONObject.getString("giftName"));
            localGift.setRequiredBean(paramJSONObject.getInt("requiredBean"));
            localGift.setGiftType(paramJSONObject.getString("giftMode"));
            localGift.setH5Url(paramJSONObject.getString("h5Url"));
            return localGift;
        }
        catch (JSONException paramJSONObject)
        {
            paramJSONObject.printStackTrace();
        }
        return localGift;
    }

    public String addUserIdUrl(String paramString)
    {
        AndroidApplication localAndroidApplication = (AndroidApplication)getApplication();
        Object localObject = paramString;
        if (localAndroidApplication.getUser() != null)
        {
            if (!paramString.contains("?")) {
                break label92;
            }
            localObject = new StringBuilder(String.valueOf(paramString)).append("&uid=");
            if (localAndroidApplication.getUser().getLoginType() != 6) {
                break label81;
            }
        }
        label81:
        for (paramString = "0";; paramString = localAndroidApplication.getUser().getId())
        {
            localObject = paramString + "&appversion=" + CommonMethod.getAppVersionCode(localAndroidApplication);
            return (String)localObject;
        }
        label92:
        localObject = new StringBuilder(String.valueOf(paramString)).append("?uid=");
        if (localAndroidApplication.getUser().getLoginType() == 6) {}
        for (paramString = "0";; paramString = localAndroidApplication.getUser().getId()) {
            return paramString + "&appversion=" + CommonMethod.getAppVersionCode(localAndroidApplication);
        }
    }

    public void applyKitKatTranslucency()
    {
        int i = Build.VERSION.SDK_INT;
    }

    public void back(View paramView)
    {
        finish();
    }

    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
    {
        if (paramMotionEvent.getAction() == 0)
        {
            View localView = getCurrentFocus();
            if (isShouldHideInput(localView, paramMotionEvent)) {
                hideSoftInput(localView.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(paramMotionEvent);
    }

    public ArrayList<UserUsuallyAddress> getUserUsuallyAddress()
    {
        return usuallyAddressList;
    }

    public void initTitleBar()
    {
        this.iv_right = ((ImageView)findViewById(2131362244));
        this.tv_left = ((ImageView)findViewById(2131362203));
        this.tv_title = ((TextView)findViewById(2131362115));
        this.tv_right = ((TextView)findViewById(2131362245));
        applyKitKatTranslucency();
    }

    public void initTitleBar(int paramInt)
    {
        this.iv_right = ((ImageView)findViewById(2131362244));
        this.tv_left = ((ImageView)findViewById(2131362203));
        this.tv_title = ((TextView)findViewById(2131362115));
        this.tv_right = ((TextView)findViewById(2131362245));
    }

    public boolean isEmail(String paramString)
    {
        if (TextUtils.isEmpty(paramString)) {
            this.toast.setMessage("您还没输入邮箱");
        }
        for (;;)
        {
            this.toast.show();
            return false;
            if (DataUtil.isEmail(paramString).booleanValue()) {
                break;
            }
            this.toast.setMessage("请输入正确的邮箱");
        }
        return true;
    }

    public boolean isLogin()
    {
        return (loginUser != null) && (loginUser.getLoginType() != 6);
    }

    public boolean isLogined()
    {
        boolean bool = true;
        if (loginUser == null)
        {
            bool = false;
            this.toast.setMessage("您还未登录哦...");
            this.toast.show();
        }
        return bool;
    }

    public boolean isMobileNo(String paramString)
    {
        if (TextUtils.isEmpty(paramString)) {
            this.toast.setMessage("请输入手机号码");
        }
        for (;;)
        {
            this.toast.show();
            return false;
            if (paramString.length() == 11) {
                break;
            }
            this.toast.setMessage("请输入正确格式的手机号码");
        }
        return true;
    }

    protected void onCreate(Bundle paramBundle)
    {
        requestWindowFeature(1);
        super.onCreate(paramBundle);
        MobclickAgent.setDebugMode(true);
        this.mPushAgent = PushAgent.getInstance(this);
        loginUser = AndroidApplication.getInstance().getUser();
        this.toast = new ToastDefine(this);
        pushSwitch = SharedPreferenceUtil.getPushSwitch(this);
        voiceSwich = SharedPreferenceUtil.getVoiceSwitch(this);
        AndroidApplication.getInstance().addActivity(this);
    }

    protected void onPause()
    {
        super.onPause();
    }

    protected void onResume()
    {
        super.onResume();
    }

    protected void onStop()
    {
        super.onStop();
    }

    public void setInvisible(View paramView)
    {
        paramView.setVisibility(4);
    }

    public void setUsuallyAddress(ArrayList<UserUsuallyAddress> paramArrayList)
    {
        usuallyAddressList = paramArrayList;
    }

    public void toGiftDetail(Gift paramGift)
    {
        if (paramGift == null) {
            return;
        }
        Intent localIntent = new Intent(this, WebViewActivity.class);
        localIntent.putExtra("gift", paramGift);
        startActivity(localIntent);
    }
}
