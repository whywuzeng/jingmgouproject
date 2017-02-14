package com.sina.sso;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract interface RemoteSSO extends IInterface
{
  public abstract String getActivityName();

  public abstract String getPackageName();

  public static abstract class Stub extends Binder
    implements RemoteSSO
  {
    private static final String DESCRIPTOR = "com.sina.sso.RemoteSSO";
    static final int TRANSACTION_getActivityName = 2;
    static final int TRANSACTION_getPackageName = 1;

    public Stub()
    {
      attachInterface(this, "com.sina.sso.RemoteSSO");
    }

    public static RemoteSSO asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.sina.sso.RemoteSSO");
      if ((localIInterface != null) && ((localIInterface instanceof RemoteSSO)))
        return (RemoteSSO)localIInterface;
      return new Proxy(paramIBinder);
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    {
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("com.sina.sso.RemoteSSO");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.sina.sso.RemoteSSO");
        paramParcel1 = getPackageName();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 2:
      }
      paramParcel1.enforceInterface("com.sina.sso.RemoteSSO");
      paramParcel1 = getActivityName();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    }

    private static class Proxy
      implements RemoteSSO
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public String getActivityName()
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sina.sso.RemoteSSO");
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public String getInterfaceDescriptor()
      {
        return "com.sina.sso.RemoteSSO";
      }

      public String getPackageName()
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sina.sso.RemoteSSO");
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.sina.sso.RemoteSSO
 * JD-Core Version:    0.6.2
 */