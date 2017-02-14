package com.baidu.lbsapi.auth;

import java.util.Hashtable;

class j
  implements Runnable
{
  j(LBSAuthManager paramLBSAuthManager, int paramInt, boolean paramBoolean, String paramString1, String paramString2, Hashtable paramHashtable)
  {
  }

  public void run()
  {
    if (a.a)
      a.a("status = " + this.b + "; forced = " + this.c + "checkAK = " + LBSAuthManager.access$1(this.a, this.d));
    if ((this.b == 601) || (this.c) || (this.b == -1) || (LBSAuthManager.access$1(this.a, this.d)))
    {
      if (a.a)
        a.a("authenticate sendAuthRequest");
      String[] arrayOfString = b.b(LBSAuthManager.access$2());
      if (a.a)
        a.a("authStrings.length:" + arrayOfString.length);
      if ((arrayOfString != null) && (arrayOfString.length > 1))
      {
        if (a.a)
          a.a("more sha1 auth");
        LBSAuthManager.access$3(this.a, this.c, this.e, this.f, arrayOfString, this.d);
        return;
      }
      LBSAuthManager.access$4(this.a, this.c, this.e, this.f, this.d);
      return;
    }
    if (602 == this.b)
    {
      if (a.a)
        a.a("authenticate wait  ");
      LBSAuthManager.access$5().b();
      LBSAuthManager.access$6(this.a, null, this.d);
      return;
    }
    if (a.a)
      a.a("authenticate else  ");
    LBSAuthManager.access$6(this.a, null, this.d);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.lbsapi.auth.j
 * JD-Core Version:    0.6.2
 */