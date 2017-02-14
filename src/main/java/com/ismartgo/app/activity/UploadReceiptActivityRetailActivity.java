package com.ismartgo.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ab.view.ioc.AbIocView;
import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.model.AuthenticationType;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.OSSFederationToken;
import com.alibaba.sdk.android.oss.model.StsTokenGetter;
import com.alibaba.sdk.android.oss.storage.OSSBucket;
import com.alibaba.sdk.android.oss.storage.OSSData;
import com.alibaba.sdk.android.oss.storage.OSSFile;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.oos.FederationToken;
import com.ismartgo.app.oos.FederationTokenGetter;
import com.ismartgo.app.oos.OSSUtils;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import de.greenrobot.event.EventBus;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class UploadReceiptActivityRetailActivity extends BaseActivity
  implements View.OnClickListener, HttpCallback<String>, OnCompleteListener
{
  private static final String TAG = "UploadReceiptActivityRetailActivity";
  public static final int TO_SELECT_PHOTO = 3;
  private Button btnUpload;
  private MyDialog dialog;
  private Handler dialogHandler;
  private String endDate;
  private int failureWhat = 1048578;
  private String fragmentTag;
  private int gameId;
  private OOSHandler handler = new OOSHandler();
  private int id;
  private ImageView imgUploadResult;
  private AlertDialog mAlertDialog;
  private String oosPicUrl;
  private String picPath;
  private String receiptDetailUrl;

  @AbIocView(id=2131230919)
  private WebView receiptDetailWeb;
  private String receiptDetailWhat = String.valueOf(UUID.randomUUID());
  private int successWhat = 1048577;
  private TextView tvUploadResult;
  private String uploadReceiptWhat;
  private int uploadTag;

  private void dismissDialog()
  {
    if (this.dialogHandler == null)
      this.dialogHandler = new Handler();
    this.dialogHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        UploadReceiptActivityRetailActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if ((UploadReceiptActivityRetailActivity.this.mAlertDialog != null) && (UploadReceiptActivityRetailActivity.this.mAlertDialog.isShowing()))
              UploadReceiptActivityRetailActivity.this.mAlertDialog.cancel();
            if (UploadReceiptActivityRetailActivity.this.uploadTag == 1)
            {
              HashMap localHashMap = new HashMap();
              localHashMap.put("tag", UploadReceiptActivityRetailActivity.this.fragmentTag);
              localHashMap.put("gameid", Integer.valueOf(UploadReceiptActivityRetailActivity.this.gameId));
              EventBus.getDefault().post(localHashMap);
              UploadReceiptActivityRetailActivity.this.finish();
            }
          }
        });
      }
    }
    , 1500L);
  }

  private void getToken()
  {
    FederationTokenGetter.getTokenFromServer(this, loginUser.getId(), TelephoneUtils.getIMEI(getApplicationContext()), this);
  }

  private void initData(Bundle paramBundle)
  {
    if (paramBundle == null)
    {
      this.gameId = getIntent().getIntExtra("gameid", 0);
      this.fragmentTag = getIntent().getStringExtra("tag");
      this.endDate = getIntent().getStringExtra("end_date");
      paramBundle = new HashMap();
      paramBundle.put("uid", loginUser.getId());
      paramBundle.put("devcode", TelephoneUtils.getIMEI(getApplicationContext()));
      paramBundle.put("cityname", city);
      paramBundle.put("lat", lat);
      paramBundle.put("lon", log);
      paramBundle.put("gameid", String.valueOf(this.gameId));
      HttpRequest.getInstance().executePostStringRequest(this, Url.RECEIPT_ACTIVITY_DETAIL_URL, 39, paramBundle, this);
    }
    for (this.receiptDetailUrl = getIntent().getStringExtra("receipt_detail"); ; this.receiptDetailUrl = paramBundle.getString("receipt_detail"))
    {
      Log.i("UploadReceiptActivityRetailActivity", "详情地址： " + this.receiptDetailUrl);
      if (!TextUtils.isEmpty(this.receiptDetailUrl))
        this.receiptDetailWeb.loadUrl(addUserIdUrl(this.receiptDetailUrl));
      return;
      loginUser = (User)paramBundle.getSerializable("user");
      this.gameId = paramBundle.getInt("gameid");
      this.fragmentTag = paramBundle.getString("tag");
      HashMap localHashMap = new HashMap();
      localHashMap.put("uid", paramBundle.getString("uid"));
      localHashMap.put("devcode", TelephoneUtils.getIMEI(getApplicationContext()));
      localHashMap.put("cityname", paramBundle.getString("cityname"));
      localHashMap.put("lat", paramBundle.getString("lat"));
      localHashMap.put("lon", paramBundle.getString("lon"));
      localHashMap.put("gameid", String.valueOf(this.gameId));
      HttpRequest.getInstance().executePostStringRequest(this, Url.RECEIPT_ACTIVITY_DETAIL_URL, 39, localHashMap, this);
    }
  }

  private void initOSS()
  {
    try
    {
      ApplicationInfo localApplicationInfo = getApplicationContext().getPackageManager().getApplicationInfo(getPackageName(), 128);
      OSSUtils.serverAddress = localApplicationInfo.metaData.getString("ServerAddress");
      OSSUtils.bucketName = localApplicationInfo.metaData.getString("BucketName");
      OSSUtils.endPoint = localApplicationInfo.metaData.getString("EndPoint");
      OSSUtils.ossService = OSSServiceProvider.getService();
      OSSUtils.ossService.setApplicationContext(getApplicationContext());
      OSSUtils.ossService.setGlobalDefaultHostId(OSSUtils.endPoint);
      OSSUtils.ossService.setAuthenticationType(AuthenticationType.FEDERATION_TOKEN);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        localNameNotFoundException.printStackTrace();
    }
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("活动详情");
    this.btnUpload = ((Button)findViewById(2131230920));
    this.btnUpload.setOnClickListener(this);
    this.dialog = new MyDialog(this);
  }

  private void showFailDialog()
  {
    this.uploadTag = 2;
    this.mAlertDialog = uploadResultDialog();
    this.imgUploadResult.setImageResource(2130837689);
    this.tvUploadResult.setText("上传失败\n请重新上传");
    this.mAlertDialog.show();
    dismissDialog();
  }

  private void showSuccessDialog()
  {
    this.uploadTag = 1;
    this.mAlertDialog = uploadResultDialog();
    this.imgUploadResult.setImageResource(2130837716);
    this.tvUploadResult.setText("上传成功");
    this.mAlertDialog.show();
    dismissDialog();
  }

  private void uploadFile()
  {
    if (this.oosPicUrl != null)
    {
      this.oosPicUrl = this.oosPicUrl.replace("sgreceipt.oss-cn-shenzhen.aliyuncs.com", "img1.ismartgo.cn");
      this.uploadReceiptWhat = String.valueOf(UUID.randomUUID());
      HashMap localHashMap = new HashMap();
      localHashMap.put("uid", loginUser.getId());
      localHashMap.put("devcode", TelephoneUtils.getIMEI(getApplicationContext()));
      localHashMap.put("lat", lat);
      localHashMap.put("lon", log);
      localHashMap.put("gameid", String.valueOf(this.gameId));
      localHashMap.put("receipturl", this.oosPicUrl);
      String str = Helper.MD5Params(new String[] { loginUser.getId(), this.oosPicUrl });
      localHashMap.put("key", str);
      Log.i("UploadReceiptActivityRetailActivity", "加密后字符： " + str);
      Log.i("UploadReceiptActivityRetailActivity", "上传图片地址： " + this.oosPicUrl);
      HttpRequest.getInstance().executePostStringRequest(this, Url.UPLOAD_RECEIPT_URL, 38, localHashMap, this);
      return;
    }
    Log.e("UploadReceiptActivityRetailActivity", "图片地址不正确");
  }

  private void uploadFileToOSS()
  {
    Log.i("UploadReceiptActivityRetailActivity", "开始上传到阿里云");
    try
    {
      final String str = new SimpleDateFormat("yyMMdd").format(new Date());
      str = this.gameId + "/" + str + "/" + UUID.randomUUID() + ".jpg";
      final OSSBucket localOSSBucket = OSSUtils.ossService.getOssBucket(OSSUtils.bucketName);
      OSSFile localOSSFile = OSSUtils.ossService.getOssFile(localOSSBucket, str);
      localOSSFile.setUploadFilePath(this.picPath, "image/*");
      localOSSFile.enableUploadCheckMd5sum();
      localOSSFile.uploadInBackground(new SaveCallback()
      {
        public void onFailure(String paramAnonymousString, OSSException paramAnonymousOSSException)
        {
          UploadReceiptActivityRetailActivity.this.dialog.dismiss();
          UploadReceiptActivityRetailActivity.this.handler.sendEmptyMessage(UploadReceiptActivityRetailActivity.this.failureWhat);
        }

        public void onProgress(String paramAnonymousString, int paramAnonymousInt1, int paramAnonymousInt2)
        {
        }

        public void onSuccess(String paramAnonymousString)
        {
          paramAnonymousString = OSSUtils.ossService.getOssData(localOSSBucket, str);
          try
          {
            UploadReceiptActivityRetailActivity.this.oosPicUrl = URLDecoder.decode(paramAnonymousString.getResourceURL(), "utf-8");
            Log.d("UploadReceiptActivityRetailActivity", "oosPicUrl: " + UploadReceiptActivityRetailActivity.this.oosPicUrl);
            UploadReceiptActivityRetailActivity.this.handler.sendEmptyMessage(1);
            return;
          }
          catch (UnsupportedEncodingException paramAnonymousString)
          {
            while (true)
              paramAnonymousString.printStackTrace();
          }
        }
      });
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      Log.e("UploadReceiptActivityRetailActivity", "未找到上传的照片");
      this.handler.sendEmptyMessage(this.failureWhat);
    }
  }

  private AlertDialog uploadResultDialog()
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.show();
    localAlertDialog.setCancelable(false);
    View localView = LayoutInflater.from(this).inflate(2130903117, null);
    this.imgUploadResult = ((ImageView)localView.findViewById(2131231116));
    this.tvUploadResult = ((TextView)localView.findViewById(2131231117));
    localAlertDialog.setContentView(localView);
    return localAlertDialog;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 == -1) && (paramInt1 == 3))
    {
      this.picPath = paramIntent.getStringExtra("photo_path");
      Log.i("Test", "最终选择的图片=" + this.picPath);
      this.dialog.setCancelable(false);
      this.dialog.show();
      getToken();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230920:
    }
    if (loginUser.getLoginType() == 6)
    {
      startActivity(new Intent(this, LoginActivity.class));
      return;
    }
    Log.i("UploadReceiptActivityRetailActivity", "当前时间： " + CommonMethod.getCurrentTime() + "  有效期： " + this.endDate);
    if ((!TextUtils.isEmpty(this.endDate)) && (CommonMethod.getCurrentTime().compareTo(this.endDate) <= 0))
    {
      startActivityForResult(new Intent(this, SelectPicActivity.class), 3);
      return;
    }
    Toast.makeText(this, "活动已超过有效期", 0).show();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(LayoutInflater.from(this).inflate(2130903064, null));
    initView();
    initData(paramBundle);
    initOSS();
  }

  protected void onDestroy()
  {
    if (this.dialogHandler != null)
      this.dialogHandler = null;
    super.onDestroy();
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (paramInt == 38)
    {
      this.dialog.dismiss();
      Toast.makeText(this, "小票上传失败", 0).show();
      this.handler.sendEmptyMessage(this.failureWhat);
    }
  }

  public void onFailure(Throwable paramThrowable, int paramInt, String paramString)
  {
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  public void onResult(final Object paramObject, int paramInt)
  {
    OSSUtils.ossService.setGlobalDefaultStsTokenGetter(new StsTokenGetter()
    {
      public OSSFederationToken getFederationToken()
      {
        FederationToken localFederationToken = SharedPreferenceUtil.getOSSToken(UploadReceiptActivityRetailActivity.this);
        if ((localFederationToken == null) || (TextUtils.isEmpty(localFederationToken.getAccessKeyId())) || (TextUtils.isEmpty(localFederationToken.getAccessKeySecret())) || (TextUtils.isEmpty(localFederationToken.getSecurityToken())) || (-1L == localFederationToken.getExpiration()))
        {
          localFederationToken = (FederationToken)paramObject;
          if (localFederationToken == null)
          {
            Toast.makeText(UploadReceiptActivityRetailActivity.this, "授权失败", 0).show();
            UploadReceiptActivityRetailActivity.this.dialog.dismiss();
            return null;
          }
          SharedPreferenceUtil.setOSSToken(UploadReceiptActivityRetailActivity.this, localFederationToken.getAccessKeyId(), localFederationToken.getAccessKeySecret(), localFederationToken.getSecurityToken(), localFederationToken.getExpiration());
        }
        while (true)
        {
          Log.i("UploadReceiptActivityRetailActivity", "获取token成功");
          return new OSSFederationToken(localFederationToken.getAccessKeyId(), localFederationToken.getAccessKeySecret(), localFederationToken.getSecurityToken(), localFederationToken.getExpiration());
          Calendar localCalendar = Calendar.getInstance();
          localCalendar.setTime(new Date(localFederationToken.getExpiration()));
          localCalendar.add(11, 8);
          if (Calendar.getInstance().getTime().getTime() > localCalendar.getTime().getTime())
          {
            localFederationToken = (FederationToken)paramObject;
            SharedPreferenceUtil.setOSSToken(UploadReceiptActivityRetailActivity.this, localFederationToken.getAccessKeyId(), localFederationToken.getAccessKeySecret(), localFederationToken.getSecurityToken(), localFederationToken.getExpiration());
          }
        }
      }
    });
    this.handler.sendEmptyMessage(0);
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putInt("gameid", this.gameId);
    paramBundle.putString("tag", this.fragmentTag);
    paramBundle.putString("uid", loginUser.getId());
    paramBundle.putString("cityname", city);
    paramBundle.putString("lat", lat);
    paramBundle.putString("lon", log);
    paramBundle.putString("receipt_detail", this.receiptDetailUrl);
    paramBundle.putSerializable("user", loginUser);
    super.onSaveInstanceState(paramBundle);
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    if (paramInt == 38)
    {
      Log.i("UploadReceiptActivityRetailActivity", "上传小票成功 " + (String)paramResponse.get());
      this.dialog.dismiss();
      try
      {
        paramResponse = new JSONObject((String)paramResponse.get());
        if (paramResponse != null)
        {
          if (paramResponse.getInt("status") == 10001)
          {
            this.handler.sendEmptyMessage(this.successWhat);
            return;
          }
          Toast.makeText(this, paramResponse.getString("msg"), 1).show();
          return;
        }
      }
      catch (JSONException paramResponse)
      {
        paramResponse.printStackTrace();
      }
    }
  }

  class OOSHandler extends Handler
  {
    OOSHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      if (paramMessage.what == 0)
        UploadReceiptActivityRetailActivity.this.uploadFileToOSS();
      do
      {
        return;
        if (paramMessage.what == 1)
        {
          UploadReceiptActivityRetailActivity.this.uploadFile();
          return;
        }
        if (paramMessage.what == UploadReceiptActivityRetailActivity.this.successWhat)
        {
          UploadReceiptActivityRetailActivity.this.showSuccessDialog();
          return;
        }
      }
      while (paramMessage.what != UploadReceiptActivityRetailActivity.this.failureWhat);
      UploadReceiptActivityRetailActivity.this.showFailDialog();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.UploadReceiptActivityRetailActivity
 * JD-Core Version:    0.6.2
 */