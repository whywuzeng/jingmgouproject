package com.ismartgo.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
import com.ismartgo.app.adapter.ReceiptModeAdapter;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.bean.ReceiptItems;
import com.ismartgo.app.bean.ReceiptMode;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.CircleImageView;
import com.ismartgo.app.grid.impl.KeyBoardCallback;
import com.ismartgo.app.grid.utils.DisplayUtil;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.utils.OSSDialog;
import com.ismartgo.app.grid.utils.ShowPicDialog;
import com.ismartgo.app.grid.view.MyKeyBoardView;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpJsonParse;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.oos.FederationToken;
import com.ismartgo.app.oos.FederationTokenGetter;
import com.ismartgo.app.oos.OSSUtils;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.Url;
import com.yolanda.nohttp.Response;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class ModifyReceiptActivity extends BaseActivity
  implements View.OnClickListener, KeyBoardCallback, AdapterView.OnItemClickListener, OnCompleteListener
{
  private int AnimationDuration = 300;
  private ReceiptModeAdapter adapter;
  private FrameLayout animLayout;
  private OSSDialog dialog;
  private MyDialog dialog2;
  private int flag = 0;
  private GridView gridView;
  private int id;
  private ImageView imgBack;
  private ImageView imgDel;
  private ImageView imgReceipt;
  private String imgUrl;
  private int intentFlag = 0;
  private boolean isBackground;
  private boolean isClean = false;
  private ReceiptItems item;
  private CircleImageView ivShopType;
  private Handler myHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 0:
      case 1:
      }
      try
      {
        ModifyReceiptActivity.this.animLayout.removeAllViews();
        label39: ModifyReceiptActivity.this.isClean = false;
        return;
        ModifyReceiptActivity.this.remainedFailure(0);
        return;
      }
      catch (Exception paramAnonymousMessage)
      {
        break label39;
      }
    }
  };
  private int number = 0;
  private String oosPicUrl;
  private int position;
  private List<ReceiptMode> receiptModeList;
  private TextView tvDate;
  private TextView tvShopType;
  private TextView tvTotal;
  private String uploadImgPath;
  private String uploadMoney;

  private void addReceipt()
  {
    HashMap localHashMap = new HashMap();
    if (BaseActivity.loginUser == null)
    {
      str = "0";
      localHashMap.put("uid", str);
      localHashMap.put("shoptype", this.id);
      if (this.uploadMoney != null)
        break label210;
      str = "0.00";
      label63: localHashMap.put("money", str);
      localHashMap.put("imgurl", this.oosPicUrl.replace("sgreceipt.oss-cn-shenzhen.aliyuncs.com", "img1.ismartgo.cn"));
      if (((AndroidApplication)getApplication()).getCurrentLocation() != null)
        break label218;
      str = "0";
      label109: localHashMap.put("cityid", str);
      if (BaseActivity.log != null)
        break label248;
      str = "0";
      label128: localHashMap.put("gpslon", str);
      if (BaseActivity.lat != null)
        break label255;
    }
    label210: label218: label248: label255: for (String str = "0"; ; str = BaseActivity.lat)
    {
      localHashMap.put("gpslat", str);
      HttpRequest.getInstance().executePostStringRequest(this, SharedPreferenceUtil.getReceiptSvUrl(this) + Url.MY_RECEIPT_ADD, HttpWhat.MY_RECEIPT_ADD, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          ModifyReceiptActivity.this.dialog.dismiss();
          ModifyReceiptActivity.this.remainedFailure(1);
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          Log.d("smartgo", ((String)paramAnonymousResponse.get()).toString());
          if (paramAnonymousResponse != null)
            try
            {
              if (new JSONObject(((String)paramAnonymousResponse.get()).toString()).getInt("status") == 10001)
              {
                ModifyReceiptActivity.this.dialog.dismiss();
                ModifyReceiptActivity.this.backMyReceiptList(false);
                return;
              }
              ModifyReceiptActivity.this.remainedFailure(1);
              return;
            }
            catch (JSONException paramAnonymousResponse)
            {
              paramAnonymousResponse.printStackTrace();
              return;
            }
          ModifyReceiptActivity.this.remainedFailure(1);
        }
      });
      return;
      str = BaseActivity.loginUser.getId();
      break;
      str = this.uploadMoney;
      break label63;
      str = ((AndroidApplication)getApplication()).getCurrentLocation().getCityId();
      break label109;
      str = BaseActivity.log;
      break label128;
    }
  }

  private View addViewToAnimLayout(ViewGroup paramViewGroup, View paramView, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt[0];
    int j = paramArrayOfInt[1];
    paramViewGroup.addView(paramView);
    paramViewGroup = new FrameLayout.LayoutParams(DisplayUtil.dip2px(this, 50.0F), DisplayUtil.dip2px(this, 50.0F));
    paramViewGroup.leftMargin = i;
    paramViewGroup.topMargin = j;
    paramView.setPadding(5, 5, 5, 5);
    paramView.setLayoutParams(paramViewGroup);
    return paramView;
  }

  private void backMyReceiptList(boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, MyReceiptListActivity.class);
    if (paramBoolean)
      setResult(1, localIntent);
    while (true)
    {
      finish();
      return;
      startActivity(localIntent);
    }
  }

  private FrameLayout createAnimLayout()
  {
    ViewGroup localViewGroup = (ViewGroup)getWindow().getDecorView();
    FrameLayout localFrameLayout = new FrameLayout(this);
    localFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    localFrameLayout.setBackgroundResource(17170445);
    localViewGroup.addView(localFrameLayout);
    return localFrameLayout;
  }

  private void doAnim(Drawable paramDrawable, int[] paramArrayOfInt, ReceiptMode paramReceiptMode)
  {
    if (!this.isClean)
    {
      setAnim(paramDrawable, paramArrayOfInt, paramReceiptMode);
      return;
    }
    try
    {
      this.animLayout.removeAllViews();
      this.isClean = false;
      setAnim(paramDrawable, paramArrayOfInt, paramReceiptMode);
      return;
    }
    catch (Exception paramDrawable)
    {
      paramDrawable.printStackTrace();
      return;
    }
    finally
    {
      this.isClean = true;
    }
    throw paramDrawable;
  }

  private void getData(String paramString, boolean paramBoolean)
  {
    this.receiptModeList = HttpJsonParse.jsonParse_receiptMode(paramString);
    if (this.receiptModeList != null)
    {
      if (paramBoolean)
      {
        SharedPreferenceUtil.setReceiptShopType(this, paramString);
        SharedPreferenceUtil.setReceiptShopFlag(this, 1);
      }
      this.adapter.setReceiptModeList(this.receiptModeList);
      if ((this.intentFlag != 1) && (this.receiptModeList.get(0) != null))
      {
        paramString = (ReceiptMode)this.receiptModeList.get(0);
        this.id = paramString.getId();
        ImgLoader.getInstance(this).showPic(paramString.getUrl(), this.ivShopType, false);
        this.tvShopType.setText(paramString.getTitle());
      }
    }
  }

  private void initClickListener()
  {
    this.imgBack.setOnClickListener(this);
    this.imgDel.setOnClickListener(this);
    this.imgReceipt.setOnClickListener(this);
    this.gridView.setOnItemClickListener(this);
  }

  private void initData()
  {
    this.receiptModeList = new ArrayList();
    this.adapter = new ReceiptModeAdapter(this, this.receiptModeList);
    this.gridView.setAdapter(this.adapter);
    if ((SharedPreferenceUtil.getReceiptShopFlag(this) == 0) || (SharedPreferenceUtil.getReceiptShopType(this).equals("")))
    {
      Log.d("smartgo", "不使用缓存");
      HttpRequest.getInstance().executePostStringRequest(this, SharedPreferenceUtil.getReceiptSvUrl(this) + Url.MY_RECEIPT_SHOP_TYPE, HttpWhat.MY_SHOP_SORT, null, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          ModifyReceiptActivity.this.getData(((String)paramAnonymousResponse.get()).toString(), true);
        }
      });
      return;
    }
    Log.d("smartgo", "使用缓存");
    getData(SharedPreferenceUtil.getReceiptShopType(this), false);
  }

  private void initKeyBoard()
  {
    this.ivShopType = ((CircleImageView)findViewById(2131231243));
    this.tvShopType = ((TextView)findViewById(2131231244));
    if (this.intentFlag == 1)
    {
      ImgLoader.getInstance(this).showPic(this.item.getImgurl(), this.imgReceipt, false);
      ImgLoader.getInstance(this).showPic(this.item.getShopTypeUrl(), this.ivShopType, false);
      this.id = this.item.getShoptype();
      Log.d("smartgo", "id" + this.id);
      this.tvShopType.setText(this.item.getShopTypeName());
      localObject = null;
      try
      {
        String str = CommonMethod.parseGivedTime(this.item.getDate());
        localObject = str;
        this.tvDate.setText((CharSequence)localObject);
        this.tvTotal.setText(String.valueOf(this.item.getMoney()));
        new MyKeyBoardView(String.valueOf(this.item.getMoney())).init(this, this);
        return;
      }
      catch (ParseException localParseException)
      {
        while (true)
          localParseException.printStackTrace();
      }
    }
    this.tvDate.setText(CommonMethod.getCurrentDate("yyyy年MM月dd日"));
    Object localObject = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject).outHeight = DisplayUtil.dip2px(this, 45.0F);
    ((BitmapFactory.Options)localObject).outWidth = DisplayUtil.dip2px(this, 45.0F);
    localObject = BitmapFactory.decodeFile(this.uploadImgPath, (BitmapFactory.Options)localObject);
    this.imgReceipt.setImageBitmap((Bitmap)localObject);
    new MyKeyBoardView(null).init(this, this);
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
    this.gridView = ((GridView)findViewById(2131230966));
    this.tvTotal = ((TextView)findViewById(2131231245));
    this.imgBack = ((ImageView)findViewById(2131230965));
    this.imgDel = ((ImageView)findViewById(2131230964));
    this.imgReceipt = ((ImageView)findViewById(2131230963));
    if (this.intentFlag != 1)
      this.imgDel.setVisibility(8);
    this.tvDate = ((TextView)findViewById(2131230923));
    this.dialog = new OSSDialog(this);
    this.animLayout = createAnimLayout();
  }

  private void intentAction(String paramString, boolean paramBoolean)
  {
    this.dialog2.show();
    if (paramBoolean)
    {
      localObject = new HashMap();
      ((Map)localObject).put("receiptid", this.item.getId());
      if (BaseActivity.loginUser == null);
      for (paramString = "0"; ; paramString = BaseActivity.loginUser.getId())
      {
        ((Map)localObject).put("uid", paramString);
        HttpRequest.getInstance().executePostStringRequest(this, SharedPreferenceUtil.getReceiptSvUrl(this) + Url.MY_RECEIPT_DELETE, HttpWhat.MY_RECEIPT_DELETE, (Map)localObject, new HttpCallback()
        {
          public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
          {
            ModifyReceiptActivity.this.dialog2.dismiss();
            ToastDefine.makeText(ModifyReceiptActivity.this, "删除失败", 0).show();
          }

          public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
          {
            ModifyReceiptActivity.this.dialog2.dismiss();
            ModifyReceiptActivity.this.backMyReceiptList(true);
          }
        });
        return;
      }
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("receiptid", this.item.getId());
    if (BaseActivity.loginUser == null);
    for (Object localObject = "0"; ; localObject = BaseActivity.loginUser.getId())
    {
      localHashMap.put("uid", localObject);
      localHashMap.put("shoptype", this.id);
      localObject = paramString;
      if (paramString == null)
        localObject = "0.00";
      localHashMap.put("money", localObject);
      HttpRequest.getInstance().executePostStringRequest(this, SharedPreferenceUtil.getReceiptSvUrl(this) + Url.MY_RECEIPT_MODIFY, HttpWhat.MY_RECEIPT_MODIFY, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          ModifyReceiptActivity.this.dialog2.dismiss();
          ToastDefine.makeText(ModifyReceiptActivity.this, "修改失败", 0).show();
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          ModifyReceiptActivity.this.dialog2.dismiss();
          ModifyReceiptActivity.this.backMyReceiptList(true);
        }
      });
      return;
    }
  }

  private void remainedFailure(final int paramInt)
  {
    final AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.show();
    localAlertDialog.setContentView(2130903159);
    ((TextView)localAlertDialog.findViewById(2131230782)).setText("重试");
    ((TextView)localAlertDialog.findViewById(2131231101)).setText("上传失败！");
    localAlertDialog.findViewById(2131230873).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localAlertDialog.dismiss();
      }
    });
    localAlertDialog.findViewById(2131230782).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ModifyReceiptActivity.this.isBackground = false;
        localAlertDialog.dismiss();
        if (paramInt == 0)
        {
          ModifyReceiptActivity.this.uploadPicture();
          return;
        }
        ModifyReceiptActivity.this.addReceipt();
      }
    });
  }

  private void setAnim(Drawable paramDrawable, int[] paramArrayOfInt, final ReceiptMode paramReceiptMode)
  {
    ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F, 1, 1.0F, 1, 1.0F);
    localScaleAnimation.setDuration(this.AnimationDuration);
    localScaleAnimation.setFillAfter(true);
    Object localObject = new ImageView(this);
    ((ImageView)localObject).setImageDrawable(paramDrawable);
    paramDrawable = addViewToAnimLayout(this.animLayout, (View)localObject, paramArrayOfInt);
    paramDrawable.setAlpha(0.6F);
    localObject = new int[2];
    this.ivShopType.getLocationInWindow((int[])localObject);
    int i = localObject[0];
    int j = paramArrayOfInt[0];
    int k = localObject[1];
    int m = paramArrayOfInt[1];
    paramArrayOfInt = new TranslateAnimation(0.0F, i - j, 0.0F, k - m);
    localObject = new RotateAnimation(0.0F, 180.0F, 1, 0.5F, 1, 0.5F);
    ((Animation)localObject).setDuration(this.AnimationDuration);
    paramArrayOfInt.setDuration(this.AnimationDuration);
    AnimationSet localAnimationSet = new AnimationSet(true);
    localAnimationSet.setFillAfter(true);
    localAnimationSet.addAnimation((Animation)localObject);
    localAnimationSet.addAnimation(localScaleAnimation);
    localAnimationSet.addAnimation(paramArrayOfInt);
    localAnimationSet.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        paramAnonymousAnimation = ModifyReceiptActivity.this;
        paramAnonymousAnimation.number -= 1;
        if (ModifyReceiptActivity.this.number == 0)
        {
          ModifyReceiptActivity.this.isClean = true;
          ModifyReceiptActivity.this.myHandler.sendEmptyMessage(0);
        }
        ImgLoader.getInstance(ModifyReceiptActivity.this).showPic(ModifyReceiptActivity.this.imgUrl, ModifyReceiptActivity.this.ivShopType, false);
        ModifyReceiptActivity.this.tvShopType.setText(paramReceiptMode.getTitle());
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        paramAnonymousAnimation = ModifyReceiptActivity.this;
        paramAnonymousAnimation.number += 1;
      }
    });
    paramDrawable.startAnimation(localAnimationSet);
  }

  private void uploadFileToOSS()
  {
    Log.i("smargo", "开始上传到阿里云");
    try
    {
      final String str = new SimpleDateFormat("yyMMdd").format(new Date());
      str = "usr/" + str + "/" + UUID.randomUUID() + ".jpg";
      final OSSBucket localOSSBucket = OSSUtils.ossService.getOssBucket(OSSUtils.bucketName);
      OSSFile localOSSFile = OSSUtils.ossService.getOssFile(localOSSBucket, str);
      localOSSFile.setUploadFilePath(this.uploadImgPath, "image/*");
      localOSSFile.enableUploadCheckMd5sum();
      localOSSFile.uploadInBackground(new SaveCallback()
      {
        public void onFailure(String paramAnonymousString, OSSException paramAnonymousOSSException)
        {
          ModifyReceiptActivity.this.flag = 2;
          if (!ModifyReceiptActivity.this.isBackground)
          {
            ModifyReceiptActivity.this.dialog.dismiss();
            ModifyReceiptActivity.this.myHandler.sendEmptyMessage(1);
          }
        }

        public void onProgress(String paramAnonymousString, int paramAnonymousInt1, int paramAnonymousInt2)
        {
        }

        public void onSuccess(String paramAnonymousString)
        {
          paramAnonymousString = OSSUtils.ossService.getOssData(localOSSBucket, str);
          try
          {
            ModifyReceiptActivity.this.oosPicUrl = URLDecoder.decode(paramAnonymousString.getResourceURL(), "utf-8");
            Log.d("smartgo", "oosPicUrl: " + ModifyReceiptActivity.this.oosPicUrl);
            if (ModifyReceiptActivity.this.oosPicUrl != null)
            {
              if (ModifyReceiptActivity.this.isBackground)
              {
                ModifyReceiptActivity.this.flag = 1;
                return;
              }
              ModifyReceiptActivity.this.addReceipt();
              return;
            }
          }
          catch (UnsupportedEncodingException paramAnonymousString)
          {
            paramAnonymousString.printStackTrace();
          }
        }
      });
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      Log.e("smartgo", "未找到上传的照片");
    }
  }

  private void uploadPicture()
  {
    if (!this.isBackground)
      this.dialog.show();
    Log.d("smartgo", "开始上传！");
    FederationTokenGetter.getTokenFromServer(this, loginUser.getId(), TelephoneUtils.getIMEI(getApplicationContext()), this);
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968584, 2130968586);
  }

  public void keywordsCallback(String paramString)
  {
    if (!CommonMethod.isEmpty(paramString))
      this.tvTotal.setText(paramString);
  }

  public void onClick(final View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230965:
      finish();
      return;
    case 2131230964:
      paramView = new AlertDialog.Builder(this).create();
      paramView.show();
      paramView.setContentView(2130903159);
      ((TextView)paramView.findViewById(2131231101)).setText("删除该小票记录吗？");
      paramView.findViewById(2131230873).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramView.dismiss();
        }
      });
      paramView.findViewById(2131230782).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ModifyReceiptActivity.this.intentAction("", true);
          paramView.dismiss();
        }
      });
      return;
    case 2131230963:
    }
    boolean bool = false;
    if (this.intentFlag == 1)
    {
      paramView = this.item.getImgurl();
      bool = true;
    }
    while (true)
    {
      new ShowPicDialog(this, paramView, bool).show();
      return;
      paramView = this.uploadImgPath;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    overridePendingTransition(2130968583, 2130968585);
    setContentView(2130903073);
    this.intentFlag = getIntent().getIntExtra("intentFlag", 0);
    if (this.intentFlag == 1)
    {
      this.position = getIntent().getIntExtra("position", 0);
      this.item = ((ReceiptItems)getIntent().getSerializableExtra("ReceiptInfo"));
      this.dialog2 = new MyDialog(this);
    }
    while (true)
    {
      initView();
      initKeyBoard();
      initClickListener();
      initData();
      return;
      initOSS();
      this.uploadImgPath = getIntent().getStringExtra("path");
      if (this.uploadImgPath != null)
      {
        this.isBackground = true;
        this.flag = 0;
        uploadPicture();
      }
    }
  }

  public void onFailure(Throwable paramThrowable, int paramInt, String paramString)
  {
    if (!this.isBackground)
    {
      this.dialog.dismiss();
      remainedFailure(0);
      return;
    }
    this.flag = 2;
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (this.receiptModeList != null)
    {
      paramAdapterView = (ReceiptMode)this.receiptModeList.get(paramInt);
      this.imgUrl = paramAdapterView.getUrl();
      this.id = paramAdapterView.getId();
      paramView = (ImageView)paramView.findViewById(2131231189);
      int[] arrayOfInt = new int[2];
      paramView.getLocationInWindow(arrayOfInt);
      doAnim(paramView.getDrawable(), arrayOfInt, paramAdapterView);
    }
  }

  public void onResult(final Object paramObject, int paramInt)
  {
    Log.d("smartgo", "taken获取成功！");
    OSSUtils.ossService.setGlobalDefaultStsTokenGetter(new StsTokenGetter()
    {
      public OSSFederationToken getFederationToken()
      {
        FederationToken localFederationToken = SharedPreferenceUtil.getOSSToken(ModifyReceiptActivity.this);
        if ((localFederationToken == null) || (TextUtils.isEmpty(localFederationToken.getAccessKeyId())) || (TextUtils.isEmpty(localFederationToken.getAccessKeySecret())) || (TextUtils.isEmpty(localFederationToken.getSecurityToken())) || (-1L == localFederationToken.getExpiration()))
        {
          localFederationToken = (FederationToken)paramObject;
          if (localFederationToken == null)
          {
            if (!ModifyReceiptActivity.this.isBackground)
            {
              ModifyReceiptActivity.this.dialog.dismiss();
              ModifyReceiptActivity.this.remainedFailure(0);
            }
            return null;
          }
          SharedPreferenceUtil.setOSSToken(ModifyReceiptActivity.this, localFederationToken.getAccessKeyId(), localFederationToken.getAccessKeySecret(), localFederationToken.getSecurityToken(), localFederationToken.getExpiration());
        }
        while (true)
        {
          Log.i("smartgo", "获取token成功");
          return new OSSFederationToken(localFederationToken.getAccessKeyId(), localFederationToken.getAccessKeySecret(), localFederationToken.getSecurityToken(), localFederationToken.getExpiration());
          Calendar localCalendar = Calendar.getInstance();
          localCalendar.setTime(new Date(localFederationToken.getExpiration()));
          localCalendar.add(11, 8);
          if (Calendar.getInstance().getTime().getTime() > localCalendar.getTime().getTime())
          {
            localFederationToken = (FederationToken)paramObject;
            SharedPreferenceUtil.setOSSToken(ModifyReceiptActivity.this, localFederationToken.getAccessKeyId(), localFederationToken.getAccessKeySecret(), localFederationToken.getSecurityToken(), localFederationToken.getExpiration());
          }
        }
      }
    });
    Log.d("smartgo", "上传图片！");
    uploadFileToOSS();
  }

  public void uploadCallback(String paramString)
  {
    if (this.intentFlag == 1)
    {
      intentAction(paramString, false);
      return;
    }
    this.uploadMoney = paramString;
    if (this.flag == 1)
    {
      this.dialog.show();
      addReceipt();
      return;
    }
    if (this.flag == 2)
    {
      this.dialog.show();
      this.isBackground = false;
      uploadPicture();
      return;
    }
    this.dialog.show();
    this.isBackground = false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ModifyReceiptActivity
 * JD-Core Version:    0.6.2
 */