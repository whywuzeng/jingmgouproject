package com.umeng.message.proguard;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface bQ extends IInterface
{
  public abstract void a(String paramString)
    throws RemoteException;

  public static abstract class a extends Binder
    implements bQ
  {
    static final int a = 1;
    private static final String b = "org.rome.android.ipp.binder.aidl.IppInterface";

    public a()
    {
      attachInterface(this, "org.rome.android.ipp.binder.aidl.IppInterface");
    }

    public static bQ a(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("org.rome.android.ipp.binder.aidl.IppInterface");
      if ((localIInterface != null) && ((localIInterface instanceof bQ)))
        return (bQ)localIInterface;
      return new a(paramIBinder);
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("org.rome.android.ipp.binder.aidl.IppInterface");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("org.rome.android.ipp.binder.aidl.IppInterface");
      a(paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    }

    private static class a
      implements bQ
    {
      private IBinder a;

      a(IBinder paramIBinder)
      {
        this.a = paramIBinder;
      }

      public String a()
      {
        return "org.rome.android.ipp.binder.aidl.IppInterface";
      }

      public void a(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("org.rome.android.ipp.binder.aidl.IppInterface");
          localParcel1.writeString(paramString);
          this.a.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramString;
      }

      public IBinder asBinder()
      {
        return this.a;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bQ
 * JD-Core Version:    0.6.2
 */