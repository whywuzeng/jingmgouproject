package com.umeng.message.protobuffer;

import com.squareup.wire.Message;
import com.squareup.wire.Message.Builder;
import com.squareup.wire.Message.Datatype;
import com.squareup.wire.ProtoEnum;
import com.squareup.wire.ProtoField;
import com.umeng.message.proguard.aE;

public final class PushRequest extends Message
{
  public static final String DEFAULT_CHECKSUM = "";
  public static final entityEncodingFormat DEFAULT_ENCRYPTION = entityEncodingFormat.JSON;
  public static final aE DEFAULT_ENTITY = aE.a;
  public static final String DEFAULT_SALT = "";
  public static final Integer DEFAULT_SERIALNO = Integer.valueOf(0);
  public static final String DEFAULT_SIGNATURE = "";
  public static final Integer DEFAULT_TIMESTAMP = Integer.valueOf(0);
  public static final String DEFAULT_VERSION = "";
  private static final long a = 0L;

  @ProtoField(tag=5, type=Message.Datatype.STRING)
  public final String checksum;

  @ProtoField(tag=7, type=Message.Datatype.ENUM)
  public final entityEncodingFormat encryption;

  @ProtoField(tag=8, type=Message.Datatype.BYTES)
  public final aE entity;

  @ProtoField(tag=6, type=Message.Datatype.STRING)
  public final String salt;

  @ProtoField(tag=3, type=Message.Datatype.INT32)
  public final Integer serialNo;

  @ProtoField(tag=2, type=Message.Datatype.STRING)
  public final String signature;

  @ProtoField(tag=4, type=Message.Datatype.INT32)
  public final Integer timestamp;

  @ProtoField(tag=1, type=Message.Datatype.STRING)
  public final String version;

  private PushRequest(Builder paramBuilder)
  {
    this(paramBuilder.version, paramBuilder.signature, paramBuilder.serialNo, paramBuilder.timestamp, paramBuilder.checksum, paramBuilder.salt, paramBuilder.encryption, paramBuilder.entity);
    a(paramBuilder);
  }

  public PushRequest(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, String paramString3, String paramString4, entityEncodingFormat paramentityEncodingFormat, aE paramaE)
  {
    this.version = paramString1;
    this.signature = paramString2;
    this.serialNo = paramInteger1;
    this.timestamp = paramInteger2;
    this.checksum = paramString3;
    this.salt = paramString4;
    this.encryption = paramentityEncodingFormat;
    this.entity = paramaE;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this);
    do
    {
      return true;
      if (!(paramObject instanceof PushRequest))
        return false;
      paramObject = (PushRequest)paramObject;
    }
    while ((a(this.version, paramObject.version)) && (a(this.signature, paramObject.signature)) && (a(this.serialNo, paramObject.serialNo)) && (a(this.timestamp, paramObject.timestamp)) && (a(this.checksum, paramObject.checksum)) && (a(this.salt, paramObject.salt)) && (a(this.encryption, paramObject.encryption)) && (a(this.entity, paramObject.entity)));
    return false;
  }

  public int hashCode()
  {
    int i3 = 0;
    int j = this.b;
    int i = j;
    label44: int k;
    label59: int m;
    label75: int n;
    label91: int i1;
    if (j == 0)
    {
      if (this.version == null)
        break label188;
      i = this.version.hashCode();
      if (this.signature == null)
        break label193;
      j = this.signature.hashCode();
      if (this.serialNo == null)
        break label198;
      k = this.serialNo.hashCode();
      if (this.timestamp == null)
        break label203;
      m = this.timestamp.hashCode();
      if (this.checksum == null)
        break label209;
      n = this.checksum.hashCode();
      if (this.salt == null)
        break label215;
      i1 = this.salt.hashCode();
      label107: if (this.encryption == null)
        break label221;
    }
    label188: label193: label198: label203: label209: label215: label221: for (int i2 = this.encryption.hashCode(); ; i2 = 0)
    {
      if (this.entity != null)
        i3 = this.entity.hashCode();
      i = (i2 + (i1 + (n + (m + (k + (j + i * 37) * 37) * 37) * 37) * 37) * 37) * 37 + i3;
      this.b = i;
      return i;
      i = 0;
      break;
      j = 0;
      break label44;
      k = 0;
      break label59;
      m = 0;
      break label75;
      n = 0;
      break label91;
      i1 = 0;
      break label107;
    }
  }

  public static final class Builder extends Message.Builder<PushRequest>
  {
    public String checksum;
    public PushRequest.entityEncodingFormat encryption;
    public aE entity;
    public String salt;
    public Integer serialNo;
    public String signature;
    public Integer timestamp;
    public String version;

    public Builder()
    {
    }

    public Builder(PushRequest paramPushRequest)
    {
      super();
      if (paramPushRequest == null)
        return;
      this.version = paramPushRequest.version;
      this.signature = paramPushRequest.signature;
      this.serialNo = paramPushRequest.serialNo;
      this.timestamp = paramPushRequest.timestamp;
      this.checksum = paramPushRequest.checksum;
      this.salt = paramPushRequest.salt;
      this.encryption = paramPushRequest.encryption;
      this.entity = paramPushRequest.entity;
    }

    public PushRequest build()
    {
      return new PushRequest(this, null);
    }

    public Builder checksum(String paramString)
    {
      this.checksum = paramString;
      return this;
    }

    public Builder encryption(PushRequest.entityEncodingFormat paramentityEncodingFormat)
    {
      this.encryption = paramentityEncodingFormat;
      return this;
    }

    public Builder entity(aE paramaE)
    {
      this.entity = paramaE;
      return this;
    }

    public Builder salt(String paramString)
    {
      this.salt = paramString;
      return this;
    }

    public Builder serialNo(Integer paramInteger)
    {
      this.serialNo = paramInteger;
      return this;
    }

    public Builder signature(String paramString)
    {
      this.signature = paramString;
      return this;
    }

    public Builder timestamp(Integer paramInteger)
    {
      this.timestamp = paramInteger;
      return this;
    }

    public Builder version(String paramString)
    {
      this.version = paramString;
      return this;
    }
  }

  public static enum entityEncodingFormat
    implements ProtoEnum
  {
    private final int a;

    private entityEncodingFormat(int paramInt)
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
 * Qualified Name:     com.umeng.message.protobuffer.PushRequest
 * JD-Core Version:    0.6.2
 */