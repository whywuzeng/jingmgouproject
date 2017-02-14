package com.google.zxing.client.result;

public final class GeoParsedResult extends ParsedResult
{
  private final double altitude;
  private final double latitude;
  private final double longitude;
  private final String query;

  GeoParsedResult(double paramDouble1, double paramDouble2, double paramDouble3, String paramString)
  {
    super(ParsedResultType.GEO);
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
    this.altitude = paramDouble3;
    this.query = paramString;
  }

  public double getAltitude()
  {
    return this.altitude;
  }

  public String getDisplayResult()
  {
    StringBuffer localStringBuffer = new StringBuffer(20);
    localStringBuffer.append(this.latitude);
    localStringBuffer.append(", ");
    localStringBuffer.append(this.longitude);
    if (this.altitude > 0.0D)
    {
      localStringBuffer.append(", ");
      localStringBuffer.append(this.altitude);
      localStringBuffer.append('m');
    }
    if (this.query != null)
    {
      localStringBuffer.append(" (");
      localStringBuffer.append(this.query);
      localStringBuffer.append(')');
    }
    return localStringBuffer.toString();
  }

  public String getGeoURI()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("geo:");
    localStringBuffer.append(this.latitude);
    localStringBuffer.append(',');
    localStringBuffer.append(this.longitude);
    if (this.altitude > 0.0D)
    {
      localStringBuffer.append(',');
      localStringBuffer.append(this.altitude);
    }
    if (this.query != null)
    {
      localStringBuffer.append('?');
      localStringBuffer.append(this.query);
    }
    return localStringBuffer.toString();
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public double getLongitude()
  {
    return this.longitude;
  }

  public String getQuery()
  {
    return this.query;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.GeoParsedResult
 * JD-Core Version:    0.6.2
 */