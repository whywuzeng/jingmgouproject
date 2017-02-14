package com.ismartgo.app.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import com.ismartgo.app.common.BitmapUtils;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.utils.LogUtils;
import com.umeng.fb.util.Log;
import java.io.File;
import java.io.PrintStream;

public class SelectPicActivity extends Activity
  implements View.OnClickListener
{
  public static final String KEY_PHOTO_PATH = "photo_path";
  public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
  public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
  private static final String TAG = "SelectPicActivity";
  private Animation anim;
  private Button cancelBtn;
  private View dialogLayout;
  private Uri photoUri;
  private String picPath;
  private Button pickPhotoBtn;
  private Button takePhotoBtn;

  private void doPhoto(int paramInt, Intent paramIntent)
  {
    if (paramInt == 2)
    {
      if (paramIntent == null)
      {
        Toast.makeText(this, "选择图片文件出错", 1).show();
        finish();
        return;
      }
      this.photoUri = paramIntent.getData();
      if (this.photoUri == null)
      {
        Toast.makeText(this, "选择图片文件出错", 1).show();
        finish();
        return;
      }
    }
    paramIntent = new String[1];
    paramIntent[0] = "_data";
    LogUtils.i("SelectPicActivity", "后photoUri= " + this.photoUri);
    Object localObject = managedQuery(this.photoUri, paramIntent, null, null, null);
    if (localObject != null)
    {
      paramInt = ((Cursor)localObject).getColumnIndexOrThrow(paramIntent[0]);
      ((Cursor)localObject).moveToFirst();
      this.picPath = ((Cursor)localObject).getString(paramInt);
      ((Cursor)localObject).close();
    }
    System.out.println("picPath: " + this.picPath);
    if ((this.picPath != null) && ((this.picPath.endsWith(".png")) || (this.picPath.endsWith(".PNG")) || (this.picPath.endsWith(".jpg")) || (this.picPath.endsWith(".JPG")) || (this.picPath.endsWith(".jpeg")) || (this.picPath.endsWith(".JPEG"))))
    {
      paramIntent = BitmapUtils.scal(this.picPath);
      localObject = new Intent();
      ((Intent)localObject).putExtra("photo_path", paramIntent.getAbsolutePath());
      setResult(-1, (Intent)localObject);
      finish();
      return;
    }
    if (!CommonMethod.isEmpty(this.photoUri.getPath()))
    {
      paramIntent = BitmapUtils.scal(this.photoUri.getPath());
      localObject = new Intent();
      ((Intent)localObject).putExtra("photo_path", paramIntent.getAbsolutePath());
      setResult(-1, (Intent)localObject);
      finish();
      return;
    }
    Toast.makeText(this, "选择图片文件不正确", 1).show();
    finish();
  }

  private void initAnim()
  {
    this.anim = AnimationUtils.loadAnimation(this, 2130968591);
    this.anim.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        if (SelectPicActivity.this.dialogLayout != null)
          SelectPicActivity.this.finish();
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
  }

  private void initView()
  {
    this.dialogLayout = findViewById(2131231183);
    this.dialogLayout.setOnClickListener(this);
    this.takePhotoBtn = ((Button)findViewById(2131231185));
    this.takePhotoBtn.setOnClickListener(this);
    this.pickPhotoBtn = ((Button)findViewById(2131231186));
    this.pickPhotoBtn.setOnClickListener(this);
    this.cancelBtn = ((Button)findViewById(2131231187));
    this.cancelBtn.setOnClickListener(this);
  }

  private void pickPhoto()
  {
    try
    {
      new Intent();
      if (Build.VERSION.SDK_INT < 19);
      for (Intent localIntent = new Intent("android.intent.action.GET_CONTENT"); ; localIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
      {
        localIntent.setType("image/*");
        startActivityForResult(localIntent, 2);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void takePhoto()
  {
    if (Environment.getExternalStorageState().equals("mounted"))
      try
      {
        Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        ContentValues localContentValues = new ContentValues();
        this.photoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, localContentValues);
        LogUtils.i("SelectPicActivity", "前photoUri= " + this.photoUri);
        localIntent.putExtra("output", this.photoUri);
        startActivityForResult(localIntent, 1);
        return;
      }
      catch (Exception localException)
      {
        Log.e("Exception", "拍照异常 " + localException.getMessage().toString());
        localException.printStackTrace();
        finish();
        return;
      }
    Toast.makeText(this, "内存卡不存在", 1).show();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1)
      doPhoto(paramInt1, paramIntent);
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131231184:
    default:
      return;
    case 2131231183:
    case 2131231187:
      this.dialogLayout.startAnimation(this.anim);
      return;
    case 2131231185:
      takePhoto();
      return;
    case 2131231186:
    }
    pickPhoto();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903149);
    initView();
    initAnim();
    if (paramBundle != null)
      this.photoUri = ((Uri)paramBundle.getParcelable("photoUri"));
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 0))
    {
      this.dialogLayout.startAnimation(this.anim);
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putParcelable("photoUri", this.photoUri);
    super.onSaveInstanceState(paramBundle);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.SelectPicActivity
 * JD-Core Version:    0.6.2
 */