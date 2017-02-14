package com.ismartgo.app.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.DataUtil;
import com.ismartgo.app.tools.NetUtil;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.wyy.twodimcode.camera.CameraManager;
import com.wyy.twodimcode.decoding.CaptureActivityHandler;
import com.wyy.twodimcode.decoding.InactivityTimer;
import com.wyy.twodimcode.view.ViewfinderView;
import com.yolanda.nohttp.Response;
import com.yolanda.nohttp.Response<Ljava.lang.String;>;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import net.tsz.afinal.FinalHttp;
import org.json.JSONObject;

public class CaptureActivity extends BaseActivity
  implements SurfaceHolder.Callback
{
  private static final float BEEP_VOLUME = 0.1F;
  private static final long VIBRATE_DURATION = 200L;
  private String TAG = "ScanBarcodeActivity";
  AndroidApplication appliaction;
  private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
    {
      paramAnonymousMediaPlayer.seekTo(0);
    }
  };
  private String characterSet;
  private Vector<BarcodeFormat> decodeFormats;
  private EditText etCode;
  private CaptureActivityHandler handler;
  private boolean hasSurface;
  private ImageView imgSplashLight;
  private InactivityTimer inactivityTimer;
  private boolean isOpenSplashLight;
  private MyDialog mDialog;
  private MediaPlayer mediaPlayer;
  private boolean playBeep;
  public String qrcode;
  User user;
  private boolean vibrate;
  private ViewfinderView viewfinderView;

  private void initBeepSound()
  {
    AssetFileDescriptor localAssetFileDescriptor;
    if ((this.playBeep) && (this.mediaPlayer == null))
    {
      setVolumeControlStream(3);
      this.mediaPlayer = new MediaPlayer();
      this.mediaPlayer.setAudioStreamType(3);
      this.mediaPlayer.setOnCompletionListener(this.beepListener);
      localAssetFileDescriptor = getResources().openRawResourceFd(2131034114);
    }
    try
    {
      this.mediaPlayer.setDataSource(localAssetFileDescriptor.getFileDescriptor(), localAssetFileDescriptor.getStartOffset(), localAssetFileDescriptor.getLength());
      localAssetFileDescriptor.close();
      this.mediaPlayer.setVolume(0.1F, 0.1F);
      this.mediaPlayer.prepare();
      return;
    }
    catch (IOException localIOException)
    {
      this.mediaPlayer = null;
    }
  }

  private void initCamera(SurfaceHolder paramSurfaceHolder)
  {
    try
    {
      CameraManager.get().openDriver(paramSurfaceHolder);
      if (this.handler == null)
        this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.characterSet);
      return;
    }
    catch (IOException paramSurfaceHolder)
    {
    }
    catch (RuntimeException paramSurfaceHolder)
    {
    }
  }

  private void initView()
  {
    this.appliaction = ((AndroidApplication)getApplication());
    this.user = this.appliaction.getUser();
    this.mDialog = new MyDialog(this);
  }

  private void judgeLocationAndLogin()
  {
    if (this.appliaction.isLogin())
    {
      this.mDialog.show();
      new FinalHttp();
      Object localObject2 = "";
      Object localObject1 = localObject2;
      if (loginUser != null)
      {
        localObject1 = localObject2;
        if (loginUser.getId() != null)
          localObject1 = loginUser.getId();
      }
      localObject2 = AndroidApplication.getInstance().getCurrentLocation();
      if (localObject2 == null)
        return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("shopid", ((CurrentLocationInfo)localObject2).getScanShopId());
      localHashMap.put("uid", localObject1);
      localHashMap.put("barcode", this.qrcode);
      Log.d("扫描码：------", this.qrcode);
      localHashMap.put("key", Helper.MD5Params(new String[] { String.valueOf(((CurrentLocationInfo)localObject2).getScanShopId()), localObject1, this.qrcode }));
      HttpRequest.getInstance().executePostStringRequest(this, Url.SCANGETBEANS_URL, 53, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          CaptureActivity.this.mDialog.dismiss();
          CaptureActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
          CaptureActivity.this.toast.show();
          CaptureActivity.this.handler.sendEmptyMessage(2131230728);
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          Log.i(CaptureActivity.this.TAG, "扫描结果： " + ((String)paramAnonymousResponse.get()).toString());
          CaptureActivity.this.mDialog.dismiss();
          int i;
          while (true)
          {
            Object localObject2;
            String str2;
            try
            {
              paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
              if (paramAnonymousResponse.getInt("status") != ResultCode.RESULT_OK)
                break label402;
              localObject2 = paramAnonymousResponse.getJSONObject("data");
              String str1 = ((JSONObject)localObject2).getString("link2g");
              str2 = ((JSONObject)localObject2).getString("link3g");
              localObject1 = ((JSONObject)localObject2).getString("link4g");
              paramAnonymousResponse = ((JSONObject)localObject2).getString("linkwifi");
              paramAnonymousInt = ((JSONObject)localObject2).getInt("beanNumber");
              i = ((JSONObject)localObject2).getInt("userBeanNum");
              localObject2 = NetUtil.getCurrentNetworkType(CaptureActivity.this);
              try
              {
                HashMap localHashMap = new HashMap();
                localHashMap.put("barcode", CaptureActivity.this.qrcode);
                MobclickAgent.onEventValue(CaptureActivity.this, "scanmsg", localHashMap, paramAnonymousInt);
                if (((String)localObject2).equals("2G"))
                {
                  paramAnonymousResponse = str1;
                  if (!paramAnonymousResponse.equals(""))
                    break;
                  CaptureActivity.this.toast.setMessage("扫描后url地址为空");
                  CaptureActivity.this.toast.show();
                  return;
                }
              }
              catch (Exception localException)
              {
                localException.printStackTrace();
                continue;
              }
            }
            catch (Exception paramAnonymousResponse)
            {
              paramAnonymousResponse.printStackTrace();
              return;
            }
            if (((String)localObject2).equals("3G"))
              paramAnonymousResponse = str2;
            else if (((String)localObject2).equals("4G"))
              paramAnonymousResponse = (Response<String>)localObject1;
          }
          Object localObject1 = paramAnonymousResponse;
          if (paramAnonymousResponse.startsWith("www."))
            localObject1 = "http://" + paramAnonymousResponse;
          if (DataUtil.isUrl((String)localObject1).booleanValue())
          {
            ScanInStoreGoodsActivity.isScan = true;
            paramAnonymousResponse = new Intent(CaptureActivity.this, ScanGoodsDetail.class);
            paramAnonymousResponse.putExtra("userBeanNum", i);
            paramAnonymousResponse.putExtra("beannumber", paramAnonymousInt);
            paramAnonymousResponse.putExtra("url", (String)localObject1);
            CaptureActivity.this.startActivity(paramAnonymousResponse);
            CaptureActivity.this.finish();
            return;
          }
          CaptureActivity.this.toast.setMessage("扫描后url格式错误");
          CaptureActivity.this.toast.show();
          return;
          label402: CaptureActivity.this.handler.sendEmptyMessage(2131230728);
          CaptureActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          CaptureActivity.this.toast.show();
        }
      });
      return;
    }
    this.toast.setMessage("请登录");
    this.toast.show();
  }

  private void playBeepSoundAndVibrate()
  {
    if ((this.playBeep) && (this.mediaPlayer != null) && (SharedPreferenceUtil.getVoiceSwitch(this) == 1))
      this.mediaPlayer.start();
    if (this.vibrate)
      ((Vibrator)getSystemService("vibrator")).vibrate(200L);
  }

  public void drawViewfinder()
  {
    this.viewfinderView.drawViewfinder();
  }

  public Handler getHandler()
  {
    return this.handler;
  }

  public ViewfinderView getViewfinderView()
  {
    return this.viewfinderView;
  }

  public void handleDecode(Result paramResult, Bitmap paramBitmap)
  {
    this.inactivityTimer.onActivity();
    playBeepSoundAndVibrate();
    this.qrcode = paramResult.getText();
    judgeLocationAndLogin();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903226);
    applyKitKatTranslucency();
    initView();
    CameraManager.init(getApplication());
    this.viewfinderView = ((ViewfinderView)findViewById(2131231410));
    this.hasSurface = false;
    this.inactivityTimer = new InactivityTimer(this);
    this.imgSplashLight = ((ImageView)findViewById(2131231412));
    this.imgSplashLight.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!CaptureActivity.this.isOpenSplashLight)
        {
          CameraManager.get().openSplashLight();
          CaptureActivity.this.imgSplashLight.setImageResource(2130837592);
          paramAnonymousView = CaptureActivity.this;
          if (!CaptureActivity.this.isOpenSplashLight)
            break label72;
        }
        label72: for (boolean bool = false; ; bool = true)
        {
          paramAnonymousView.isOpenSplashLight = bool;
          return;
          CameraManager.get().closeSplashLight();
          CaptureActivity.this.imgSplashLight.setImageResource(2130837591);
          break;
        }
      }
    });
    this.etCode = ((EditText)findViewById(2131231411));
    this.etCode.setSelection(0);
    this.etCode.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 2)
        {
          CaptureActivity.this.qrcode = CaptureActivity.this.etCode.getText().toString().trim();
          CaptureActivity.this.judgeLocationAndLogin();
        }
        return false;
      }
    });
  }

  protected void onDestroy()
  {
    this.inactivityTimer.shutdown();
    super.onDestroy();
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
    if (this.handler != null)
    {
      this.handler.quitSynchronously();
      this.handler = null;
    }
    CameraManager.get().closeDriver();
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
    SurfaceHolder localSurfaceHolder = ((SurfaceView)findViewById(2131231409)).getHolder();
    if (this.hasSurface)
      initCamera(localSurfaceHolder);
    while (true)
    {
      this.decodeFormats = null;
      this.characterSet = null;
      this.playBeep = false;
      if (((AudioManager)getSystemService("audio")).getRingerMode() != 2)
        this.playBeep = true;
      initBeepSound();
      this.vibrate = true;
      return;
      localSurfaceHolder.addCallback(this);
      localSurfaceHolder.setType(3);
    }
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    if (!this.hasSurface)
    {
      this.hasSurface = true;
      initCamera(paramSurfaceHolder);
    }
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    this.hasSurface = false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.CaptureActivity
 * JD-Core Version:    0.6.2
 */