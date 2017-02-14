package com.baidu.a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface b extends IInterface
{
  public abstract void a(c paramc)
    throws RemoteException;

  public static abstract class a extends Binder
    implements b
  {
    public static b a(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
      if ((localIInterface != null) && ((localIInterface instanceof b)))
        return (b)localIInterface;
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
        paramParcel2.writeString("com.baidu.mapframework.open.aidl.IMapOpenService");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
      a(c.a.b(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }

    private static class a
      implements b
    {
      private IBinder a;

      a(IBinder paramIBinder)
      {
        this.a = paramIBinder;
      }

      public void a(c paramc)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.baidu.mapframework.open.aidl.IMapOpenService");
          if (paramc != null);
          for (paramc = paramc.asBinder(); ; paramc = null)
          {
            localParcel1.writeStrongBinder(paramc);
            this.a.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramc;
      }

      public IBinder asBinder()
      {
        return this.a;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.a.a.a.b
 * JD-Core Version:    0.6.2
 */