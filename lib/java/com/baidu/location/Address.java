package com.baidu.location;

import com.baidu.location.b.b;

public final class Address
  implements b
{
  public final String address;
  public final String city;
  public final String cityCode;
  public final String country;
  public final String countryCode;
  public final String district;
  public final String province;
  public final String street;
  public final String streetNumber;

  private Address(Builder paramBuilder)
  {
    this.country = Builder.jdMethod_int(paramBuilder);
    this.countryCode = Builder.jdMethod_do(paramBuilder);
    this.province = Builder.jdMethod_case(paramBuilder);
    this.city = Builder.jdMethod_if(paramBuilder);
    this.cityCode = Builder.jdMethod_byte(paramBuilder);
    this.district = Builder.jdMethod_new(paramBuilder);
    this.street = Builder.jdMethod_for(paramBuilder);
    this.streetNumber = Builder.a(paramBuilder);
    this.address = Builder.jdMethod_try(paramBuilder);
  }

  public static class Builder
  {
    private static final String a = "上海";
    private static final String jdField_case = "重庆";
    private static final String jdField_do = "北京";
    private static final String jdField_int = "天津";
    private String jdField_byte = null;
    private String jdField_char = null;
    private String jdField_else = null;
    private String jdField_for = null;
    private String jdField_goto = null;
    private String jdField_if = null;
    private String jdField_long = null;
    private String jdField_new = null;
    private String jdField_try = null;

    public Address build()
    {
      StringBuffer localStringBuffer = new StringBuffer();
      if (this.jdField_new != null)
        localStringBuffer.append(this.jdField_new);
      if (this.jdField_if != null)
        localStringBuffer.append(this.jdField_if);
      if ((this.jdField_if != null) && (this.jdField_byte != null) && ((!this.jdField_if.contains("北京")) || (!this.jdField_byte.contains("北京"))) && ((!this.jdField_if.contains("上海")) || (!this.jdField_byte.contains("上海"))) && ((!this.jdField_if.contains("天津")) || (!this.jdField_byte.contains("天津"))) && ((!this.jdField_if.contains("重庆")) || (!this.jdField_byte.contains("重庆"))))
        localStringBuffer.append(this.jdField_byte);
      if (this.jdField_goto != null)
        localStringBuffer.append(this.jdField_goto);
      if (this.jdField_else != null)
        localStringBuffer.append(this.jdField_else);
      if (this.jdField_for != null)
        localStringBuffer.append(this.jdField_for);
      if (localStringBuffer.length() > 0)
        this.jdField_long = localStringBuffer.toString();
      return new Address(this, null);
    }

    public Builder city(String paramString)
    {
      this.jdField_byte = paramString;
      return this;
    }

    public Builder cityCode(String paramString)
    {
      this.jdField_char = paramString;
      return this;
    }

    public Builder country(String paramString)
    {
      this.jdField_new = paramString;
      return this;
    }

    public Builder countryCode(String paramString)
    {
      this.jdField_try = paramString;
      return this;
    }

    public Builder district(String paramString)
    {
      this.jdField_goto = paramString;
      return this;
    }

    public Builder province(String paramString)
    {
      this.jdField_if = paramString;
      return this;
    }

    public Builder street(String paramString)
    {
      this.jdField_else = paramString;
      return this;
    }

    public Builder streetNumber(String paramString)
    {
      this.jdField_for = paramString;
      return this;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.Address
 * JD-Core Version:    0.6.2
 */