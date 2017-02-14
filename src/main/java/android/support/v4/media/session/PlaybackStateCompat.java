package android.support.v4.media.session;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.text.TextUtils;

public final class PlaybackStateCompat
  implements Parcelable
{
  public static final long ACTION_FAST_FORWARD = 64L;
  public static final long ACTION_PAUSE = 2L;
  public static final long ACTION_PLAY = 4L;
  public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024L;
  public static final long ACTION_PLAY_FROM_SEARCH = 2048L;
  public static final long ACTION_PLAY_PAUSE = 512L;
  public static final long ACTION_REWIND = 8L;
  public static final long ACTION_SEEK_TO = 256L;
  public static final long ACTION_SET_RATING = 128L;
  public static final long ACTION_SKIP_TO_NEXT = 32L;
  public static final long ACTION_SKIP_TO_PREVIOUS = 16L;
  public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096L;
  public static final long ACTION_STOP = 1L;
  public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new Parcelable.Creator()
  {
    public PlaybackStateCompat createFromParcel(Parcel paramAnonymousParcel)
    {
      return new PlaybackStateCompat(paramAnonymousParcel, null);
    }

    public PlaybackStateCompat[] newArray(int paramAnonymousInt)
    {
      return new PlaybackStateCompat[paramAnonymousInt];
    }
  };
  public static final long PLAYBACK_POSITION_UNKNOWN = -1L;
  public static final int STATE_BUFFERING = 6;
  public static final int STATE_CONNECTING = 8;
  public static final int STATE_ERROR = 7;
  public static final int STATE_FAST_FORWARDING = 4;
  public static final int STATE_NONE = 0;
  public static final int STATE_PAUSED = 2;
  public static final int STATE_PLAYING = 3;
  public static final int STATE_REWINDING = 5;
  public static final int STATE_SKIPPING_TO_NEXT = 10;
  public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
  public static final int STATE_STOPPED = 1;
  private final long mActions;
  private final long mBufferedPosition;
  private final CharSequence mErrorMessage;
  private final long mPosition;
  private final float mSpeed;
  private final int mState;
  private Object mStateObj;
  private final long mUpdateTime;

  private PlaybackStateCompat(int paramInt, long paramLong1, long paramLong2, float paramFloat, long paramLong3, CharSequence paramCharSequence, long paramLong4)
  {
    this.mState = paramInt;
    this.mPosition = paramLong1;
    this.mBufferedPosition = paramLong2;
    this.mSpeed = paramFloat;
    this.mActions = paramLong3;
    this.mErrorMessage = paramCharSequence;
    this.mUpdateTime = paramLong4;
  }

  private PlaybackStateCompat(Parcel paramParcel)
  {
    this.mState = paramParcel.readInt();
    this.mPosition = paramParcel.readLong();
    this.mSpeed = paramParcel.readFloat();
    this.mUpdateTime = paramParcel.readLong();
    this.mBufferedPosition = paramParcel.readLong();
    this.mActions = paramParcel.readLong();
    this.mErrorMessage = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
  }

  public static PlaybackStateCompat fromPlaybackState(Object paramObject)
  {
    if ((paramObject == null) || (Build.VERSION.SDK_INT < 21))
      return null;
    PlaybackStateCompat localPlaybackStateCompat = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(paramObject), PlaybackStateCompatApi21.getPosition(paramObject), PlaybackStateCompatApi21.getBufferedPosition(paramObject), PlaybackStateCompatApi21.getPlaybackSpeed(paramObject), PlaybackStateCompatApi21.getActions(paramObject), PlaybackStateCompatApi21.getErrorMessage(paramObject), PlaybackStateCompatApi21.getLastPositionUpdateTime(paramObject));
    localPlaybackStateCompat.mStateObj = paramObject;
    return localPlaybackStateCompat;
  }

  public int describeContents()
  {
    return 0;
  }

  public long getActions()
  {
    return this.mActions;
  }

  public long getBufferedPosition()
  {
    return this.mBufferedPosition;
  }

  public CharSequence getErrorMessage()
  {
    return this.mErrorMessage;
  }

  public long getLastPositionUpdateTime()
  {
    return this.mUpdateTime;
  }

  public float getPlaybackSpeed()
  {
    return this.mSpeed;
  }

  public Object getPlaybackState()
  {
    if ((this.mStateObj != null) || (Build.VERSION.SDK_INT < 21))
      return this.mStateObj;
    this.mStateObj = PlaybackStateCompatApi21.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime);
    return this.mStateObj;
  }

  public long getPosition()
  {
    return this.mPosition;
  }

  public int getState()
  {
    return this.mState;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("PlaybackState {");
    localStringBuilder.append("state=").append(this.mState);
    localStringBuilder.append(", position=").append(this.mPosition);
    localStringBuilder.append(", buffered position=").append(this.mBufferedPosition);
    localStringBuilder.append(", speed=").append(this.mSpeed);
    localStringBuilder.append(", updated=").append(this.mUpdateTime);
    localStringBuilder.append(", actions=").append(this.mActions);
    localStringBuilder.append(", error=").append(this.mErrorMessage);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.mState);
    paramParcel.writeLong(this.mPosition);
    paramParcel.writeFloat(this.mSpeed);
    paramParcel.writeLong(this.mUpdateTime);
    paramParcel.writeLong(this.mBufferedPosition);
    paramParcel.writeLong(this.mActions);
    TextUtils.writeToParcel(this.mErrorMessage, paramParcel, paramInt);
  }

  public static final class Builder
  {
    private long mActions;
    private long mBufferedPosition;
    private CharSequence mErrorMessage;
    private long mPosition;
    private float mRate;
    private int mState;
    private long mUpdateTime;

    public Builder()
    {
    }

    public Builder(PlaybackStateCompat paramPlaybackStateCompat)
    {
      this.mState = paramPlaybackStateCompat.mState;
      this.mPosition = paramPlaybackStateCompat.mPosition;
      this.mRate = paramPlaybackStateCompat.mSpeed;
      this.mUpdateTime = paramPlaybackStateCompat.mUpdateTime;
      this.mBufferedPosition = paramPlaybackStateCompat.mBufferedPosition;
      this.mActions = paramPlaybackStateCompat.mActions;
      this.mErrorMessage = paramPlaybackStateCompat.mErrorMessage;
    }

    public PlaybackStateCompat build()
    {
      return new PlaybackStateCompat(this.mState, this.mPosition, this.mBufferedPosition, this.mRate, this.mActions, this.mErrorMessage, this.mUpdateTime, null);
    }

    public void setActions(long paramLong)
    {
      this.mActions = paramLong;
    }

    public void setBufferedPosition(long paramLong)
    {
      this.mBufferedPosition = paramLong;
    }

    public void setErrorMessage(CharSequence paramCharSequence)
    {
      this.mErrorMessage = paramCharSequence;
    }

    public void setState(int paramInt, long paramLong, float paramFloat)
    {
      this.mState = paramInt;
      this.mPosition = paramLong;
      this.mRate = paramFloat;
      this.mUpdateTime = SystemClock.elapsedRealtime();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.media.session.PlaybackStateCompat
 * JD-Core Version:    0.6.2
 */