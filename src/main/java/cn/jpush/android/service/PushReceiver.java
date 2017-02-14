package cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.m;
import cn.jpush.android.data.c;
import cn.jpush.android.data.d;
import cn.jpush.android.data.l;
import cn.jpush.android.data.p;
import cn.jpush.android.util.ae;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.k;
import cn.jpush.android.util.x;
import java.io.File;

public class PushReceiver extends BroadcastReceiver
{
  public static d a;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[47];
    int j = 0;
    Object localObject2 = "P*\\\035K1GR\tP";
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
        i = 124;
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
        localObject2 = "\\*LY";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "_5XL\025]$\\I\023Pj^N\030\020$FD\016Q,L\016\f_&CA\033[hIR\037V,^E";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\0205MR\021W6[I\023Pkbp)m\rwm9m\026ig9";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "4盁\"O\022l [U\021[m\001*";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "J*IS\bj PT";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "p*\ba?j\fgn#p\n|i:w\006it5q\013wo,{\013md\\Z NI\022[!\bI\022\036(IN\025X [TP\036*XE\022\0361@E\\Z NA\tR1\bM\035W+\bA\037J,^I\bG";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "}*FN\031]1AO\022\0366\\A\b[eKH\035P\"MD\\J*\b\r\\";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "J<XE";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "\020elO\\P*\\H\025P\"\006";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "4盁\"O\022l [U\021[m\001*\023P\025IU\017[m\001*";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "S [S\035Y ";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "]+\006J\fK6@\016\035P!ZO\025ZkAN\b[+\\\0162q\021af5}\004|i3p\032ze?{\f~e8a\025zo$g";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "_&\\I\nW1Q";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "Q+zE\037[,^E\\\023e";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = "P \\W\023L.aN\032Q";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        j = 20;
        localObject2 = "n0[H.[&MI\n[7";
        i = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i4] = localObject1;
        j = 21;
        localObject2 = "4盁\"O\022n$]S\031\026l\"";
        i = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i4] = localObject1;
        j = 22;
        localObject2 = "Z JU\033a+GT\025X,KA\bW*F";
        i = 21;
        localObject1 = arrayOfString;
        break;
      case 21:
        localObject3[i4] = localObject1;
        j = 23;
        localObject2 = "_+LR\023W!\006I\022J FTR_&\\I\023Pkxa?u\004oe#\001le8";
        i = 22;
        localObject1 = arrayOfString;
        break;
      case 22:
        localObject3[i4] = localObject1;
        j = 24;
        localObject2 = "";
        i = 23;
        localObject1 = arrayOfString;
        break;
      case 23:
        localObject3[i4] = localObject1;
        j = 25;
        localObject2 = "_+LR\023W!\006I\022J FTR_&\\I\023Pk~i9i";
        i = 24;
        localObject1 = arrayOfString;
        break;
      case 24:
        localObject3[i4] = localObject1;
        j = 26;
        localObject2 = "文沐丅沁杵谽畭";
        i = 25;
        localObject1 = arrayOfString;
        break;
      case 25:
        localObject3[i4] = localObject1;
        j = 27;
        localObject2 = "4\017xU\017V\fFT\031L#IC\031\020*Fr\031M0EET\027Obp\tM-aN\b[7NA\037[kGN,_0[ET\027";
        i = 26;
        localObject1 = arrayOfString;
        break;
      case 26:
        localObject3[i4] = localObject1;
        j = 28;
        localObject2 = "4\017xU\017V\fFT\031L#IC\031\020*Fr\031M0EET\027";
        i = 27;
        localObject1 = arrayOfString;
        break;
      case 27:
        localObject3[i4] = localObject1;
        j = 29;
        localObject2 = "]+\006J\fK6@\016\035P!ZO\025Zkfo(w\003ac=j\fgn#j\034xe";
        i = 28;
        localObject1 = arrayOfString;
        break;
      case 28:
        localObject3[i4] = localObject1;
        j = 30;
        localObject2 = "_+LR\023W!\006I\022J FTR_&\\I\023Pkjo3j\032ko1n\tmt9z";
        i = 29;
        localObject1 = arrayOfString;
        break;
      case 29:
        localObject3[i4] = localObject1;
        j = 31;
        localObject2 = "";
        i = 30;
        localObject1 = arrayOfString;
        break;
      case 30:
        localObject3[i4] = localObject1;
        j = 32;
        localObject2 = "";
        i = 31;
        localObject1 = arrayOfString;
        break;
      case 31:
        localObject3[i4] = localObject1;
        j = 33;
        localObject2 = "_5Xi\030";
        i = 32;
        localObject1 = arrayOfString;
        break;
      case 32:
        localObject3[i4] = localObject1;
        j = 34;
        localObject2 = "4\017xU\017V\fFT\031L#IC\031\020*Fp\035K6M\bU";
        i = 33;
        localObject1 = arrayOfString;
        break;
      case 33:
        localObject3[i4] = localObject1;
        j = 35;
        localObject2 = "";
        i = 34;
        localObject1 = arrayOfString;
        break;
      case 34:
        localObject3[i4] = localObject1;
        j = 36;
        localObject2 = "_5X";
        i = 35;
        localObject1 = arrayOfString;
        break;
      case 35:
        localObject3[i4] = localObject1;
        j = 37;
        localObject2 = "N$KK\035Y \022";
        i = 36;
        localObject1 = arrayOfString;
        break;
      case 36:
        localObject3[i4] = localObject1;
        j = 38;
        localObject2 = "t\025]S\024\036庿讆雦扬\036\026lk\\旈勥丢绿话仝硄仍诤佌掖遄敠枼ま\036O\"棠洷刎坭\"";
        i = 37;
        localObject1 = arrayOfString;
        break;
      case 37:
        localObject3[i4] = localObject1;
        j = 39;
        localObject2 = "M FD\031L\fL";
        i = 38;
        localObject1 = arrayOfString;
        break;
      case 38:
        localObject3[i4] = localObject1;
        j = 40;
        localObject2 = "]+\006J\fK6@\016\035P!ZO\025ZkAN\b[+\\\0162q\021af5}\004|i3p\032an/j\004dl#}\tac7{\001";
        i = 39;
        localObject1 = arrayOfString;
        break;
      case 39:
        localObject3[i4] = localObject1;
        j = 41;
        localObject2 = "P*kO\022P KT\025H,\\Y";
        i = 40;
        localObject1 = arrayOfString;
        break;
      case 40:
        localObject3[i4] = localObject1;
        j = 42;
        localObject2 = "4O朄揰祆叔坭弨叱戯医狳怩丫円玎i叹帣戯医么伲凚珌\020";
        i = 41;
        localObject1 = arrayOfString;
        break;
      case 41:
        localObject3[i4] = localObject1;
        j = 43;
        localObject2 = "S6O\025Z";
        i = 42;
        localObject1 = arrayOfString;
        break;
      case 42:
        localObject3[i4] = localObject1;
        j = 44;
        localObject2 = "P*\\I\032W&II\023P\032\\Y\f[";
        i = 43;
        localObject1 = arrayOfString;
        break;
      case 43:
        localObject3[i4] = localObject1;
        j = 45;
        localObject2 = "]+\006J\fK6@\016\035P!ZO\025Zkxu/v\032ad";
        i = 44;
        localObject1 = arrayOfString;
        break;
      case 44:
        localObject3[i4] = localObject1;
        j = 46;
        localObject2 = "m FD\\\\7GA\030]$[T\\J*\bA\fN\b";
        i = 45;
        localObject1 = arrayOfString;
        break;
      case 45:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        a = null;
        return;
        i = 62;
        continue;
        i = 69;
        continue;
        i = 40;
        continue;
        i = 32;
      }
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null);
    Object localObject1;
    while (true)
    {
      return;
      try
      {
        localObject1 = paramIntent.getAction();
        new StringBuilder(z[17]).append((String)localObject1).toString();
        x.c();
        if ((cn.jpush.android.a.a(paramContext.getApplicationContext())) && (localObject1 != null))
        {
          if (!((String)localObject1).equals(z[30]))
            break label96;
          ae.a(paramContext);
          k.d(paramContext);
          if (PushService.a())
          {
            ServiceInterface.c(paramContext, 500);
            return;
          }
        }
      }
      catch (NullPointerException paramContext)
      {
        x.c();
        return;
      }
    }
    x.d();
    return;
    label96: Object localObject3;
    if (((String)localObject1).equals(z[23]))
    {
      localObject3 = paramIntent.getDataString().replace(z[37], "");
      paramIntent = c.a(paramContext, (String)localObject3);
      ServiceInterface.a(paramContext, r.a((String)localObject3));
    }
    while (true)
    {
      Object localObject4;
      Object localObject2;
      try
      {
        if (TextUtils.isEmpty(paramIntent))
          break;
        localObject4 = paramIntent.split(",");
        if (localObject4.length <= 0)
          break;
        localObject2 = localObject4[0];
        ServiceInterface.a((String)localObject2, 1002, paramContext);
        if (localObject4.length < 2)
          break label1607;
        localObject1 = localObject4[1];
        paramIntent = "";
        if (localObject4.length >= 3)
          paramIntent = localObject4[2];
        x.f();
        if ((!((String)localObject1).equals(z[0])) && (!cn.jpush.android.util.a.a(paramContext, (String)localObject3, (String)localObject1)))
        {
          x.c();
          ServiceInterface.a((String)localObject2, 1100, paramContext);
        }
        if (ai.a(paramIntent))
          break label1601;
        m.a(paramContext, paramIntent);
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.getMessage();
        x.f();
        return;
      }
      if (((String)localObject1).equals(z[9]))
      {
        ServiceInterface.a(paramContext, r.b(paramIntent.getDataString().replace(z[37], "")));
        return;
      }
      if (((String)localObject1).equals(z[18]))
      {
        if ((!PushService.r) || (!PushService.a()))
          break;
        ServiceInterface.c(paramContext);
        return;
      }
      if (((String)localObject1).equals(z[40]))
      {
        paramIntent = (d)paramIntent.getSerializableExtra(z[1]);
        if ((paramIntent == null) || (!paramIntent.a()))
          break;
        ServiceInterface.a(paramIntent.c, 1015, paramContext);
        paramIntent = (l)paramIntent;
        localObject1 = new Intent();
        ((Intent)localObject1).addFlags(268435456);
        ((Intent)localObject1).setAction(z[25]);
        ((Intent)localObject1).setDataAndType(Uri.fromFile(new File(paramIntent.ah)), z[2]);
        paramContext.startActivity((Intent)localObject1);
        return;
      }
      if (((String)localObject1).startsWith(z[14]))
      {
        i = paramIntent.getIntExtra(z[44], 0);
        new StringBuilder(z[15]).append(i).toString();
        x.c();
        if (i == 0)
        {
          if (ServiceInterface.j(paramContext))
          {
            x.d();
            return;
          }
          if (!cn.jpush.android.util.a.n(paramContext))
          {
            x.d();
            return;
          }
        }
        localObject1 = paramIntent.getStringExtra(z[13]);
        if (ai.a((String)localObject1))
          break;
        localObject2 = paramIntent.getStringExtra(z[39]);
        paramIntent = p.a(paramContext, (String)localObject1, paramIntent.getStringExtra(z[33]), (String)localObject2, paramIntent.getStringExtra(z[43]));
        if (paramIntent == null)
        {
          x.e();
          return;
        }
        if (paramIntent.e)
        {
          paramIntent.h = true;
          p.a(paramContext, paramIntent);
        }
        abortBroadcast();
        return;
      }
      if (((String)localObject1).startsWith(z[24]))
      {
        if (paramIntent.getBooleanExtra(z[22], false))
        {
          String str2 = paramIntent.getStringExtra(z[16]);
          i = paramIntent.getIntExtra(z[10], -1);
          if (i == -1)
          {
            paramIntent = paramIntent.getStringExtra(z[6]);
            paramContext = Toast.makeText(paramContext, str2, 1);
            localObject1 = paramContext.getView();
            if ((localObject1 instanceof LinearLayout))
            {
              localObject1 = ((LinearLayout)localObject1).getChildAt(0);
              if ((localObject1 instanceof TextView))
              {
                localObject1 = (TextView)localObject1;
                if (!ai.a(paramIntent))
                  ((TextView)localObject1).setText(paramIntent);
                ((TextView)localObject1).setTextSize(13.0F);
              }
            }
            paramContext.show();
            return;
          }
          if (ai.a(str2))
            break;
          String str3 = z[38];
          localObject3 = z[5];
          localObject4 = z[26];
          String str1 = z[28];
          paramIntent = (Intent)localObject4;
          localObject1 = str1;
          localObject2 = localObject3;
          switch (i)
          {
          default:
            localObject2 = localObject3;
            localObject1 = str1;
            paramIntent = (Intent)localObject4;
          case 0:
          case 1:
          case 2:
          }
          while (true)
          {
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(str3).append(str2).append((String)localObject2).append(paramIntent).append((String)localObject1).append(z[42]);
            localObject3 = new SpannableStringBuilder((CharSequence)localObject3);
            i = str3.length();
            int j = str2.length() + i;
            ((SpannableStringBuilder)localObject3).setSpan(new ForegroundColorSpan(-13527041), i, j, 33);
            i = j + 2;
            j = ((String)localObject2).length() + i - 2;
            ((SpannableStringBuilder)localObject3).setSpan(new ForegroundColorSpan(-13527041), i, j, 33);
            i = paramIntent.length() + j;
            j = ((String)localObject1).length();
            ((SpannableStringBuilder)localObject3).setSpan(new ForegroundColorSpan(-13527041), i, j + i, 33);
            paramContext = Toast.makeText(paramContext, str2, 1);
            paramIntent = paramContext.getView();
            if ((paramIntent instanceof LinearLayout))
            {
              paramIntent = ((LinearLayout)paramIntent).getChildAt(0);
              if ((paramIntent instanceof TextView))
              {
                paramIntent = (TextView)paramIntent;
                if (localObject3 != null)
                  paramIntent.setText((CharSequence)localObject3);
                paramIntent.setTextSize(13.0F);
              }
            }
            paramContext.show();
            return;
            localObject2 = z[21];
            paramIntent = z[26];
            localObject1 = z[34];
            continue;
            localObject2 = z[12];
            paramIntent = z[26];
            localObject1 = z[27];
          }
        }
        localObject1 = paramIntent.getStringExtra(z[45]);
        if (!ai.a((String)localObject1))
        {
          localObject2 = paramIntent.getStringExtra(z[29]);
          new StringBuilder(z[31]).append((String)localObject2).toString();
          x.c();
          if ((localObject2 == null) || (!"1".equals(localObject2)))
            break label1596;
        }
      }
      label1596: for (int i = 1; ; i = 0)
      {
        if (1 != i)
          ServiceInterface.a((String)localObject1, 1000, paramContext);
        if (!cn.jpush.android.util.a.a(paramContext, z[35], true))
        {
          x.b(z[20], z[7]);
          cn.jpush.android.util.a.f(paramContext);
          return;
        }
        localObject2 = new Intent(z[35]);
        ((Intent)localObject2).putExtras(paramIntent.getExtras());
        localObject1 = paramIntent.getStringExtra(z[36]);
        paramIntent = (Intent)localObject1;
        if (ai.a((String)localObject1))
          paramIntent = paramContext.getPackageName();
        ((Intent)localObject2).addCategory(paramIntent);
        new StringBuilder(z[46]).append(paramIntent).toString();
        x.d();
        paramContext.sendBroadcast((Intent)localObject2, paramIntent + z[4]);
        return;
        if (!((String)localObject1).equalsIgnoreCase(z[3]))
          break;
        localObject1 = (NetworkInfo)paramIntent.getParcelableExtra(z[19]);
        if (localObject1 == null)
          break;
        new StringBuilder(z[8]).append(((NetworkInfo)localObject1).toString()).toString();
        x.c();
        if ((2 == ((NetworkInfo)localObject1).getType()) || (3 == ((NetworkInfo)localObject1).getType()))
        {
          x.c();
          return;
        }
        if (paramIntent.getBooleanExtra(z[41], false))
        {
          x.b();
          a.b = false;
          ServiceInterface.b(paramContext);
          return;
        }
        if (NetworkInfo.State.CONNECTED == ((NetworkInfo)localObject1).getState())
        {
          ServiceInterface.f(paramContext);
          x.b();
          a.b = true;
          if (DownloadService.a())
            DownloadService.a(paramContext);
          if (PushService.s)
            ServiceInterface.c(paramContext, 3000);
          if (a == null)
            break;
          m.b(paramContext, a);
          return;
        }
        if (NetworkInfo.State.DISCONNECTED == ((NetworkInfo)localObject1).getState())
        {
          x.b();
          a.b = false;
          ServiceInterface.b(paramContext);
          return;
        }
        new StringBuilder(z[32]).append(((NetworkInfo)localObject1).getState()).append(z[11]).toString();
        x.b();
        return;
      }
      label1601: paramIntent = (Intent)localObject2;
      continue;
      label1607: localObject1 = "";
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.PushReceiver
 * JD-Core Version:    0.6.2
 */