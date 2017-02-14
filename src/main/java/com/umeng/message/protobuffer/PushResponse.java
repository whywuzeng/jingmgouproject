package com.umeng.message.protobuffer;

import com.squareup.wire.Message;
import com.squareup.wire.Message.Builder;
import com.squareup.wire.Message.Datatype;
import com.squareup.wire.ProtoEnum;
import com.squareup.wire.ProtoField;

public final class PushResponse extends Message
{
  public static final responseCode DEFAULT_CODE = responseCode.SUCCESS;
  public static final String DEFAULT_DESCRIPTION = "";
  private static final long a = 0L;

  @ProtoField(tag=1, type=Message.Datatype.ENUM)
  public final responseCode code;

  @ProtoField(tag=2, type=Message.Datatype.STRING)
  public final String description;

  @ProtoField(tag=3)
  public final Info info;

  private PushResponse(Builder paramBuilder)
  {
    this(paramBuilder.code, paramBuilder.description, paramBuilder.info);
    a(paramBuilder);
  }

  public PushResponse(responseCode paramresponseCode, String paramString, Info paramInfo)
  {
    this.code = paramresponseCode;
    this.description = paramString;
    this.info = paramInfo;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this);
    do
    {
      return true;
      if (!(paramObject instanceof PushResponse))
        return false;
      paramObject = (PushResponse)paramObject;
    }
    while ((a(this.code, paramObject.code)) && (a(this.description, paramObject.description)) && (a(this.info, paramObject.info)));
    return false;
  }

  public int hashCode()
  {
    int k = 0;
    int j = this.b;
    int i = j;
    if (j == 0)
    {
      if (this.code == null)
        break label77;
      i = this.code.hashCode();
      if (this.description == null)
        break label82;
    }
    label77: label82: for (j = this.description.hashCode(); ; j = 0)
    {
      if (this.info != null)
        k = this.info.hashCode();
      i = (j + i * 37) * 37 + k;
      this.b = i;
      return i;
      i = 0;
      break;
    }
  }

  public static final class Builder extends Message.Builder<PushResponse>
  {
    public PushResponse.responseCode code;
    public String description;
    public PushResponse.Info info;

    public Builder()
    {
    }

    public Builder(PushResponse paramPushResponse)
    {
      super();
      if (paramPushResponse == null)
        return;
      this.code = paramPushResponse.code;
      this.description = paramPushResponse.description;
      this.info = paramPushResponse.info;
    }

    public PushResponse build()
    {
      return new PushResponse(this, null);
    }

    public Builder code(PushResponse.responseCode paramresponseCode)
    {
      this.code = paramresponseCode;
      return this;
    }

    public Builder description(String paramString)
    {
      this.description = paramString;
      return this;
    }

    public Builder info(PushResponse.Info paramInfo)
    {
      this.info = paramInfo;
      return this;
    }
  }

  public static final class Info extends Message
  {
    public static final String DEFAULT_DEVICETOKENS = "";
    public static final Integer DEFAULT_LAUNCHPOLICY = Integer.valueOf(0);
    public static final Integer DEFAULT_TAGPOLICY = Integer.valueOf(0);
    public static final Integer DEFAULT_TAGREMAINCOUNT = Integer.valueOf(0);
    public static final String DEFAULT_TAGS = "";
    private static final long a = 0L;

    @ProtoField(tag=3, type=Message.Datatype.STRING)
    public final String deviceTokens;

    @ProtoField(tag=1, type=Message.Datatype.INT32)
    public final Integer launchPolicy;

    @ProtoField(tag=2, type=Message.Datatype.INT32)
    public final Integer tagPolicy;

    @ProtoField(tag=4, type=Message.Datatype.INT32)
    public final Integer tagRemainCount;

    @ProtoField(tag=5, type=Message.Datatype.STRING)
    public final String tags;

    private Info(Builder paramBuilder)
    {
      this(paramBuilder.launchPolicy, paramBuilder.tagPolicy, paramBuilder.deviceTokens, paramBuilder.tagRemainCount, paramBuilder.tags);
      a(paramBuilder);
    }

    public Info(Integer paramInteger1, Integer paramInteger2, String paramString1, Integer paramInteger3, String paramString2)
    {
      this.launchPolicy = paramInteger1;
      this.tagPolicy = paramInteger2;
      this.deviceTokens = paramString1;
      this.tagRemainCount = paramInteger3;
      this.tags = paramString2;
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this);
      do
      {
        return true;
        if (!(paramObject instanceof Info))
          return false;
        paramObject = (Info)paramObject;
      }
      while ((a(this.launchPolicy, paramObject.launchPolicy)) && (a(this.tagPolicy, paramObject.tagPolicy)) && (a(this.deviceTokens, paramObject.deviceTokens)) && (a(this.tagRemainCount, paramObject.tagRemainCount)) && (a(this.tags, paramObject.tags)));
      return false;
    }

    public int hashCode()
    {
      int n = 0;
      int j = this.b;
      int i = j;
      label44: int k;
      if (j == 0)
      {
        if (this.launchPolicy == null)
          break label122;
        i = this.launchPolicy.hashCode();
        if (this.tagPolicy == null)
          break label127;
        j = this.tagPolicy.hashCode();
        if (this.deviceTokens == null)
          break label132;
        k = this.deviceTokens.hashCode();
        label59: if (this.tagRemainCount == null)
          break label137;
      }
      label132: label137: for (int m = this.tagRemainCount.hashCode(); ; m = 0)
      {
        if (this.tags != null)
          n = this.tags.hashCode();
        i = (m + (k + (j + i * 37) * 37) * 37) * 37 + n;
        this.b = i;
        return i;
        label122: i = 0;
        break;
        label127: j = 0;
        break label44;
        k = 0;
        break label59;
      }
    }

    public static final class Builder extends Message.Builder<PushResponse.Info>
    {
      public String deviceTokens;
      public Integer launchPolicy;
      public Integer tagPolicy;
      public Integer tagRemainCount;
      public String tags;

      public Builder()
      {
      }

      public Builder(PushResponse.Info paramInfo)
      {
        super();
        if (paramInfo == null)
          return;
        this.launchPolicy = paramInfo.launchPolicy;
        this.tagPolicy = paramInfo.tagPolicy;
        this.deviceTokens = paramInfo.deviceTokens;
        this.tagRemainCount = paramInfo.tagRemainCount;
        this.tags = paramInfo.tags;
      }

      public PushResponse.Info build()
      {
        return new PushResponse.Info(this, null);
      }

      public Builder deviceTokens(String paramString)
      {
        this.deviceTokens = paramString;
        return this;
      }

      public Builder launchPolicy(Integer paramInteger)
      {
        this.launchPolicy = paramInteger;
        return this;
      }

      public Builder tagPolicy(Integer paramInteger)
      {
        this.tagPolicy = paramInteger;
        return this;
      }

      public Builder tagRemainCount(Integer paramInteger)
      {
        this.tagRemainCount = paramInteger;
        return this;
      }

      public Builder tags(String paramString)
      {
        this.tags = paramString;
        return this;
      }
    }
  }

  public static enum responseCode
    implements ProtoEnum
  {
    private final int a;

    private responseCode(int paramInt)
    {
      this.a = paramInt;
    }

    public int getValue()
    {
      return this.a;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.protobuffer.PushResponse
 * JD-Core Version:    0.6.2
 */