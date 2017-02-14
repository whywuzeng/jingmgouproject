package org.android.agoo.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface ElectionReceiverService extends IInterface
{
  public abstract boolean sendElectionResult(Intent paramIntent)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ElectionReceiverService
  {
    private static final String DESCRIPTOR = "org.android.agoo.service.ElectionReceiverService";
    static final int TRANSACTION_sendElectionResult = 1;

    public Stub()
    {
      attachInterface(this, "org.android.agoo.service.ElectionReceiverService");
    }

    public static ElectionReceiverService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("org.android.agoo.service.ElectionReceiverService");
      if ((localIInterface != null) && ((localIInterface instanceof ElectionReceiverService)))
        return (ElectionReceiverService)localIInterface;
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
        paramParcel2.writeString("org.android.agoo.service.ElectionReceiverService");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("org.android.agoo.service.ElectionReceiverService");
      if (paramParcel1.readInt() != 0)
      {
        paramParcel1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        boolean bool = sendElectionResult(paramParcel1);
        paramParcel2.writeNoException();
        if (!bool)
          break label102;
      }
      label102: for (paramInt1 = 1; ; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        return true;
        paramParcel1 = null;
        break;
      }
    }

    private static class a
      implements ElectionReceiverService
    {
      private IBinder a;

      a(IBinder paramIBinder)
      {
        this.a = paramIBinder;
      }

      public String a()
      {
        return "org.android.agoo.service.ElectionReceiverService";
      }

      public IBinder asBinder()
      {
        return this.a;
      }

      public boolean sendElectionResult(Intent paramIntent)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("org.android.agoo.service.ElectionReceiverService");
            if (paramIntent != null)
            {
              localParcel1.writeInt(1);
              paramIntent.writeToParcel(localParcel1, 0);
              this.a.transact(1, localParcel1, localParcel2, 0);
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
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.service.ElectionReceiverService
 * JD-Core Version:    0.6.2
 */