package cn.jpush.android.service;

public class PushProtocol
{
  static
  {
    Object localObject1 = "~}AM1%5\006".toCharArray();
    int j = localObject1.length;
    int m = 0;
    int i = 0;
    Object localObject2 = localObject1;
    int k = j;
    if (j <= 1);
    label121: 
    do
    {
      m = i;
      k = i;
      localObject2 = localObject1;
      int n = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 89;
      case 0:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        localObject2[k] = ((char)(i ^ n));
        m += 1;
        if (j != 0)
          break label121;
        k = j;
        break;
        i = 20;
        continue;
        i = 13;
        continue;
        i = 52;
        continue;
        i = 62;
      }
      k = j;
      localObject2 = localObject1;
      localObject1 = localObject2;
      j = k;
      i = m;
    }
    while (k > m);
    System.loadLibrary(new String(localObject2).intern());
  }

  public static synchronized native int Close(long paramLong);

  public static native int EnChannel(long paramLong1, long paramLong2, String paramString1, String paramString2);

  public static native String GetEsg(long paramLong);

  public static native int GetSdkVersion();

  public static native int HbJPush(long paramLong1, long paramLong2);

  public static native long InitConn();

  public static native int InitPush(long paramLong, String paramString, int paramInt);

  public static native int LogPushWithBack(long paramLong1, byte[] paramArrayOfByte, long paramLong2, String paramString1, String paramString2, long paramLong3);

  public static native int MsgResponse(long paramLong1, int paramInt, long paramLong2, byte paramByte, long paramLong3);

  public static native int PushTime(long paramLong1, long paramLong2, String paramString1, String paramString2);

  public static native int RecvPush(long paramLong, byte[] paramArrayOfByte, int paramInt);

  public static native int RegPush(long paramLong, String paramString1, String paramString2, String paramString3, String paramString4);

  public static native int RepPush(long paramLong1, long paramLong2, byte paramByte, String paramString);

  public static native int Stop(long paramLong);

  public static native int TagAlias(long paramLong1, long paramLong2, String paramString1, String paramString2, int paramInt);

  public static native int UnChnelId(long paramLong1, long paramLong2, String paramString1, String paramString2);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.PushProtocol
 * JD-Core Version:    0.6.2
 */