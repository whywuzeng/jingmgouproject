package cn.jpush.android.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.jpush.android.api.m;
import cn.jpush.android.data.d;
import cn.jpush.android.data.g;
import cn.jpush.android.data.s;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.x;

public class PushActivity extends Activity
{
  private static final String[] z;
  private d a;
  private String b;
  private g c;
  private a d = null;
  private boolean e = false;
  private ProgressDialog f;
  private Handler g = new h(this);
  private View.OnClickListener h = new i(this);

  static
  {
    String[] arrayOfString = new String[13];
    int j = 0;
    Object localObject2 = "v^PeGCU^$PG\026B,CJ\026A-R\002Q\\3RL\026[$ZG\026V$YLYAeUG\026S*BLR\024";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label68: n = m;
      label71: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 55;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label71;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "rCF-vAB\\3^VO";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "D_Y \r\r\031";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "V@g,TJF@6_v_A)R";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "KR";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "HF@6_}D\\&_RCF-hCUA,XLTT7hNWL*BV";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "rZP$DG\026T!S\002ZT<XWB\0257RQY@7TG\026_5BQ^j7^A^E0DJiT&CKY['VPiY$NMCAkOOZ\0251X\002DP6\030NWL*BV\026\024";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "@YQ<";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "K[R\027^A^E0DJtA+uCU^";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "NWL*BV";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "uWG+^LQ\024e`PY[\"\027cDR6\026\002uY*DG\026e0DJwV1^T_A<\026";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "KE`5SCBP\023RPE\\*Y";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "kXC$[KR\025(DE\026A<GG\026A*\027Q^Z2\027\017\026";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 34;
        continue;
        i = 54;
        continue;
        i = 53;
        continue;
        i = 69;
      }
    }
  }

  private void a()
  {
    Object localObject = getPackageManager();
    if (!getApplicationContext().getPackageName().isEmpty())
    {
      localObject = ((PackageManager)localObject).getLaunchIntentForPackage(getApplicationContext().getPackageName());
      if (localObject != null);
    }
    else
    {
      x.d(z[1], z[0]);
      return;
    }
    ((Intent)localObject).addFlags(335544320);
    startActivity((Intent)localObject);
    finish();
  }

  private void a(Intent paramIntent)
  {
    boolean bool = paramIntent.getBooleanExtra(z[11], false);
    this.a = ((d)paramIntent.getSerializableExtra(z[7]));
    this.b = this.a.c;
    if (bool)
    {
      if (this.a != null);
      return;
    }
    if (this.a != null);
    switch (this.a.o)
    {
    case 2:
    default:
      new StringBuilder(z[12]).append(this.a.o).toString();
      x.e();
      m.a(this, this.a, 0);
      finish();
      return;
    case 1:
      this.g.removeMessages(2);
      this.g.removeMessages(3);
      this.g.sendEmptyMessageDelayed(2, 500L);
      return;
    case 0:
    }
    this.g.removeMessages(4);
    this.g.removeMessages(5);
    this.g.sendEmptyMessageDelayed(4, 500L);
  }

  public void onBackPressed()
  {
    super.onBackPressed();
    if (this.e)
      ServiceInterface.a(this.b, 1008, this);
    while (true)
    {
      finish();
      return;
      ServiceInterface.a(this.b, 1006, this);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    x.c();
    super.onCreate(paramBundle);
    if (getIntent() != null)
    {
      this.a = ((d)getIntent().getSerializableExtra(z[7]));
      if (this.a == null)
      {
        x.d(z[1], z[10]);
        finish();
        return;
      }
      if ((this.a != null) && (this.a.z == 2))
      {
        this.a.z = 1;
        this.a.p = 3;
        m.a(this, this.a);
      }
      Object localObject;
      if (Build.VERSION.SDK_INT >= 11)
      {
        paramBundle = ((s)this.a).W;
        int i = getResources().getIdentifier(z[5], z[9], getPackageName());
        if (i == 0)
        {
          x.e(z[1], z[6]);
          finish();
          return;
        }
        localObject = getActionBar();
        ((ActionBar)localObject).setCustomView(i);
        ((ActionBar)localObject).setDisplayOptions(16);
        localObject = (TextView)findViewById(getResources().getIdentifier(z[3], z[4], getPackageName()));
        ImageButton localImageButton = (ImageButton)findViewById(getResources().getIdentifier(z[8], z[4], getPackageName()));
        if (!paramBundle.isEmpty())
        {
          ((TextView)localObject).setText(paramBundle);
          localImageButton.setOnClickListener(this.h);
        }
      }
      while (true)
      {
        a(getIntent());
        return;
        ((TextView)localObject).setText("");
        break;
        requestWindowFeature(1);
        if (this.a.q)
          getWindow().setFlags(1024, 1024);
      }
    }
    x.e();
  }

  protected void onDestroy()
  {
    if (this.c != null)
      this.c.a();
    if (this.d != null)
      this.d.e();
    if (this.f != null)
      this.f.dismiss();
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if ((this.d != null) && (this.d.a()))
        this.d.b();
      while (true)
      {
        return true;
        a();
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onNewIntent(Intent paramIntent)
  {
    a(paramIntent);
  }

  protected void onPause()
  {
    super.onPause();
    if (this.d != null)
      this.d.d();
  }

  protected void onResume()
  {
    super.onResume();
    if (this.d != null)
      this.d.c();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.PushActivity
 * JD-Core Version:    0.6.2
 */