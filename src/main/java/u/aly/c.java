package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.umeng.analytics.b;

public class c
{
  private final byte[] a = { 0, 0, 0, 0, 0, 0, 0, 0 };
  private final int b = 1;
  private final int c = 0;
  private String d = "1.0";
  private String e = null;
  private byte[] f = null;
  private byte[] g = null;
  private byte[] h = null;
  private int i = 0;
  private int j = 0;
  private int k = 0;
  private byte[] l = null;
  private byte[] m = null;
  private boolean n = false;

  private c(byte[] paramArrayOfByte1, String paramString, byte[] paramArrayOfByte2)
    throws Exception
  {
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length == 0))
      throw new Exception("entity is null or empty");
    this.e = paramString;
    this.k = paramArrayOfByte1.length;
    this.l = cc.a(paramArrayOfByte1);
    this.j = ((int)(System.currentTimeMillis() / 1000L));
    this.m = paramArrayOfByte2;
  }

  public static String a(Context paramContext)
  {
    paramContext = x.a(paramContext);
    if (paramContext == null)
      return null;
    return paramContext.getString("signature", null);
  }

  public static c a(Context paramContext, String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      String str1 = bq.p(paramContext);
      String str2 = bq.f(paramContext);
      paramContext = x.a(paramContext);
      String str3 = paramContext.getString("signature", null);
      int i1 = paramContext.getInt("serial", 1);
      paramString = new c(paramArrayOfByte, paramString, (str2 + str1).getBytes());
      paramString.a(str3);
      paramString.a(i1);
      paramString.b();
      paramContext.edit().putInt("serial", i1 + 1).putString("signature", paramString.a()).commit();
      return paramString;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  private byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    int i6 = 0;
    byte[] arrayOfByte1 = b.b(this.m);
    byte[] arrayOfByte2 = b.b(this.l);
    int i7 = arrayOfByte1.length;
    byte[] arrayOfByte3 = new byte[i7 * 2];
    int i5 = 0;
    while (i5 < i7)
    {
      arrayOfByte3[(i5 * 2)] = arrayOfByte2[i5];
      arrayOfByte3[(i5 * 2 + 1)] = arrayOfByte1[i5];
      i5 += 1;
    }
    i5 = 0;
    while (i5 < 2)
    {
      arrayOfByte3[i5] = paramArrayOfByte[i5];
      arrayOfByte3[(arrayOfByte3.length - i5 - 1)] = paramArrayOfByte[(paramArrayOfByte.length - i5 - 1)];
      i5 += 1;
    }
    int i1 = (byte)(paramInt & 0xFF);
    int i2 = (byte)(paramInt >> 8 & 0xFF);
    int i3 = (byte)(paramInt >> 16 & 0xFF);
    int i4 = (byte)(paramInt >>> 24);
    paramInt = i6;
    while (paramInt < arrayOfByte3.length)
    {
      arrayOfByte3[paramInt] = ((byte)(arrayOfByte3[paramInt] ^ new byte[] { i1, i2, i3, i4 }[(paramInt % 4)]));
      paramInt += 1;
    }
    return arrayOfByte3;
  }

  public static c b(Context paramContext, String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      String str1 = bq.p(paramContext);
      String str2 = bq.f(paramContext);
      paramContext = x.a(paramContext);
      String str3 = paramContext.getString("signature", null);
      int i1 = paramContext.getInt("serial", 1);
      paramString = new c(paramArrayOfByte, paramString, (str2 + str1).getBytes());
      paramString.a(true);
      paramString.a(str3);
      paramString.a(i1);
      paramString.b();
      paramContext.edit().putInt("serial", i1 + 1).putString("signature", paramString.a()).commit();
      return paramString;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  private byte[] d()
  {
    return a(this.a, (int)(System.currentTimeMillis() / 1000L));
  }

  private byte[] e()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(b.a(this.f));
    localStringBuilder.append(this.i);
    localStringBuilder.append(this.j);
    localStringBuilder.append(this.k);
    localStringBuilder.append(b.a(this.g));
    return b.b(localStringBuilder.toString().getBytes());
  }

  public String a()
  {
    return b.a(this.f);
  }

  public void a(int paramInt)
  {
    this.i = paramInt;
  }

  public void a(String paramString)
  {
    this.f = b.a(paramString);
  }

  public void a(boolean paramBoolean)
  {
    this.n = paramBoolean;
  }

  public void b()
  {
    if (this.f == null)
      this.f = d();
    byte[] arrayOfByte;
    if (this.n)
      arrayOfByte = new byte[16];
    try
    {
      System.arraycopy(this.f, 1, arrayOfByte, 0, 16);
      this.l = b.a(this.l, arrayOfByte);
      label51: this.g = a(this.f, this.j);
      this.h = e();
      return;
    }
    catch (Exception localException)
    {
      break label51;
    }
  }

  public byte[] c()
  {
    Object localObject = new bp();
    ((bp)localObject).a(this.d);
    ((bp)localObject).b(this.e);
    ((bp)localObject).c(b.a(this.f));
    ((bp)localObject).a(this.i);
    ((bp)localObject).c(this.j);
    ((bp)localObject).d(this.k);
    ((bp)localObject).a(this.l);
    int i1;
    if (this.n)
      i1 = 1;
    while (true)
    {
      ((bp)localObject).e(i1);
      ((bp)localObject).d(b.a(this.g));
      ((bp)localObject).e(b.a(this.h));
      try
      {
        localObject = new cq().a((ch)localObject);
        return localObject;
        i1 = 0;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return null;
  }

  public String toString()
  {
    int i1 = 1;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(String.format("version : %s\n", new Object[] { this.d }));
    localStringBuilder.append(String.format("address : %s\n", new Object[] { this.e }));
    localStringBuilder.append(String.format("signature : %s\n", new Object[] { b.a(this.f) }));
    localStringBuilder.append(String.format("serial : %s\n", new Object[] { Integer.valueOf(this.i) }));
    localStringBuilder.append(String.format("timestamp : %d\n", new Object[] { Integer.valueOf(this.j) }));
    localStringBuilder.append(String.format("length : %d\n", new Object[] { Integer.valueOf(this.k) }));
    localStringBuilder.append(String.format("guid : %s\n", new Object[] { b.a(this.g) }));
    localStringBuilder.append(String.format("checksum : %s ", new Object[] { b.a(this.h) }));
    if (this.n);
    while (true)
    {
      localStringBuilder.append(String.format("codex : %d", new Object[] { Integer.valueOf(i1) }));
      return localStringBuilder.toString();
      i1 = 0;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.c
 * JD-Core Version:    0.6.2
 */