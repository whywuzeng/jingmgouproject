package android.support.v4.media.session;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes.Builder;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.media.session.MediaSession.Callback;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;

class MediaSessionCompatApi21
{
  public static Object createCallback(Callback paramCallback)
  {
    return new CallbackProxy(paramCallback);
  }

  public static Object createSession(Context paramContext, String paramString)
  {
    return new MediaSession(paramContext, paramString);
  }

  public static Parcelable getSessionToken(Object paramObject)
  {
    return ((MediaSession)paramObject).getSessionToken();
  }

  public static boolean isActive(Object paramObject)
  {
    return ((MediaSession)paramObject).isActive();
  }

  public static void release(Object paramObject)
  {
    ((MediaSession)paramObject).release();
  }

  public static void sendSessionEvent(Object paramObject, String paramString, Bundle paramBundle)
  {
    ((MediaSession)paramObject).sendSessionEvent(paramString, paramBundle);
  }

  public static void setActive(Object paramObject, boolean paramBoolean)
  {
    ((MediaSession)paramObject).setActive(paramBoolean);
  }

  public static void setCallback(Object paramObject1, Object paramObject2, Handler paramHandler)
  {
    ((MediaSession)paramObject1).setCallback((MediaSession.Callback)paramObject2, paramHandler);
  }

  public static void setFlags(Object paramObject, int paramInt)
  {
    ((MediaSession)paramObject).setFlags(paramInt);
  }

  public static void setMetadata(Object paramObject1, Object paramObject2)
  {
    ((MediaSession)paramObject1).setMetadata((MediaMetadata)paramObject2);
  }

  public static void setPlaybackState(Object paramObject1, Object paramObject2)
  {
    ((MediaSession)paramObject1).setPlaybackState((PlaybackState)paramObject2);
  }

  public static void setPlaybackToLocal(Object paramObject, int paramInt)
  {
    AudioAttributes.Builder localBuilder = new AudioAttributes.Builder();
    localBuilder.setLegacyStreamType(paramInt);
    ((MediaSession)paramObject).setPlaybackToLocal(localBuilder.build());
  }

  public static void setPlaybackToRemote(Object paramObject1, Object paramObject2)
  {
    ((MediaSession)paramObject1).setPlaybackToRemote((VolumeProvider)paramObject2);
  }

  public static Object verifySession(Object paramObject)
  {
    if ((paramObject instanceof MediaSession))
      return paramObject;
    throw new IllegalArgumentException("mediaSession is not a valid MediaSession object");
  }

  public static abstract interface Callback
  {
    public abstract void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver);

    public abstract void onFastForward();

    public abstract boolean onMediaButtonEvent(Intent paramIntent);

    public abstract void onPause();

    public abstract void onPlay();

    public abstract void onRewind();

    public abstract void onSeekTo(long paramLong);

    public abstract void onSetRating(Object paramObject);

    public abstract void onSkipToNext();

    public abstract void onSkipToPrevious();

    public abstract void onStop();
  }

  static class CallbackProxy<T extends MediaSessionCompatApi21.Callback> extends MediaSession.Callback
  {
    protected final T mCallback;

    public CallbackProxy(T paramT)
    {
      this.mCallback = paramT;
    }

    public void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
      this.mCallback.onCommand(paramString, paramBundle, paramResultReceiver);
    }

    public void onFastForward()
    {
      this.mCallback.onFastForward();
    }

    public boolean onMediaButtonEvent(Intent paramIntent)
    {
      return this.mCallback.onMediaButtonEvent(paramIntent);
    }

    public void onPause()
    {
      this.mCallback.onPause();
    }

    public void onPlay()
    {
      this.mCallback.onPlay();
    }

    public void onRewind()
    {
      this.mCallback.onRewind();
    }

    public void onSeekTo(long paramLong)
    {
      this.mCallback.onSeekTo(paramLong);
    }

    public void onSetRating(Rating paramRating)
    {
      this.mCallback.onSetRating(paramRating);
    }

    public void onSkipToNext()
    {
      this.mCallback.onSkipToNext();
    }

    public void onSkipToPrevious()
    {
      this.mCallback.onSkipToPrevious();
    }

    public void onStop()
    {
      this.mCallback.onStop();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompatApi21
 * JD-Core Version:    0.6.2
 */