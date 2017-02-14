package com.baidu.a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface c extends IInterface
{
  public abstract void a(IBinder paramIBinder)
    throws RemoteException;

  public static abstract class a extends Binder
    implements c
  {
    public a()
    {
      attachInterface(this, "com.baidu.mapframework.open.aidl.IOpenClientCallback");
    }

    public static c b(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IOpenClientCallback");
      if ((localIInterface != null) && ((localIInterface instanceof c)))
        return (c)localIInterface;
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
        paramParcel2.writeString("com.baidu.mapframework.open.aidl.IOpenClientCallback");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("com.baidu.mapframework.open.aidl.IOpenClientCallback");
      a(paramParcel1.readStrongBinder());
      paramParcel2.writeNoException();
      return true;
    }

    private static class a
      implements c
    {
      private IBinder a;

      a(IBinder paramIBinder)
      {
        this.a = paramIBinder;
      }

      public void a(IBinder paramIBinder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.baidu.mapframework.open.aidl.IOpenClientCallback");
          localParcel1.writeStrongBinder(paramIBinder);
          this.a.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.a;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.a.a.a.c
 * JD-Core Version:    0.6.2
 */