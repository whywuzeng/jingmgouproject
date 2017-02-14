package org.android.agoo.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface SendMessage extends IInterface
{
  public abstract int doSend(Intent paramIntent)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements SendMessage
  {
    private static final String DESCRIPTOR = "org.android.agoo.service.SendMessage";
    static final int TRANSACTION_doSend = 1;

    public Stub()
    {
      attachInterface(this, "org.android.agoo.service.SendMessage");
    }

    public static SendMessage asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("org.android.agoo.service.SendMessage");
      if ((localIInterface != null) && ((localIInterface instanceof SendMessage)))
        return (SendMessage)localIInterface;
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
        paramParcel2.writeString("org.android.agoo.service.SendMessage");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("org.android.agoo.service.SendMessage");
      if (paramParcel1.readInt() != 0);
      for (paramParcel1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1); ; paramParcel1 = null)
      {
        paramInt1 = doSend(paramParcel1);
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      }
    }

    private static class a
      implements SendMessage
    {
      private IBinder a;

      a(IBinder paramIBinder)
      {
        this.a = paramIBinder;
      }

      public String a()
      {
        return "org.android.agoo.service.SendMessage";
      }

      public IBinder asBinder()
      {
        return this.a;
      }

      public int doSend(Intent paramIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("org.android.agoo.service.SendMessage");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.a.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
            localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramIntent;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.service.SendMessage
 * JD-Core Version:    0.6.2
 */