package com.baidu.a.a.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface a extends IInterface
{
  public abstract String a(String paramString)
    throws RemoteException;

  public abstract boolean a(String paramString1, String paramString2, Bundle paramBundle)
    throws RemoteException;

  public static abstract class a extends Binder
    implements a
  {
    public static a a(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IComOpenClient");
      if ((localIInterface != null) && ((localIInterface instanceof a)))
        return (a)localIInterface;
      return new a(paramIBinder);
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("com.baidu.mapframework.open.aidl.IComOpenClient");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.baidu.mapframework.open.aidl.IComOpenClient");
        paramParcel1 = a(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 2:
      }
      paramParcel1.enforceInterface("com.baidu.mapframework.open.aidl.IComOpenClient");
      String str1 = paramParcel1.readString();
      String str2 = paramParcel1.readString();
      if (paramParcel1.readInt() != 0)
      {
        paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        boolean bool = a(str1, str2, paramParcel1);
        paramParcel2.writeNoException();
        if (!bool)
          break label152;
      }
      label152: for (paramInt1 = 1; ; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        return true;
        paramParcel1 = null;
        break;
      }
    }

    private static class a
      implements a
    {
      private IBinder a;

      a(IBinder paramIBinder)
      {
        this.a = paramIBinder;
      }

      public String a(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.baidu.mapframework.open.aidl.IComOpenClient");
          localParcel1.writeString(paramString);
          this.a.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.readString();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramString;
      }

      public boolean a(String paramString1, String paramString2, Bundle paramBundle)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.baidu.mapframework.open.aidl.IComOpenClient");
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            if (paramBundle != null)
            {
              localParcel1.writeInt(1);
              paramBundle.writeToParcel(localParcel1, 0);
              this.a.transact(2, localParcel1, localParcel2, 0);
              localParcel2.readException();
              int i = localParcel2.readInt();
              if (i != 0)
                return bool;
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          bool = false;
        }
      }

      public IBinder asBinder()
      {
        return this.a;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.a.a.a.a
 * JD-Core Version:    0.6.2
 */