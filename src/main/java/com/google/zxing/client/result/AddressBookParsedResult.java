package com.google.zxing.client.result;

public final class AddressBookParsedResult extends ParsedResult
{
  private final String[] addresses;
  private final String birthday;
  private final String[] emails;
  private final String[] names;
  private final String note;
  private final String org;
  private final String[] phoneNumbers;
  private final String pronunciation;
  private final String title;
  private final String url;

  public AddressBookParsedResult(String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString2, String[] paramArrayOfString4, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    super(ParsedResultType.ADDRESSBOOK);
    this.names = paramArrayOfString1;
    this.pronunciation = paramString1;
    this.phoneNumbers = paramArrayOfString2;
    this.emails = paramArrayOfString3;
    this.note = paramString2;
    this.addresses = paramArrayOfString4;
    this.org = paramString3;
    this.birthday = paramString4;
    this.title = paramString5;
    this.url = paramString6;
  }

  public String[] getAddresses()
  {
    return this.addresses;
  }

  public String getBirthday()
  {
    return this.birthday;
  }

  public String getDisplayResult()
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    maybeAppend(this.names, localStringBuffer);
    maybeAppend(this.pronunciation, localStringBuffer);
    maybeAppend(this.title, localStringBuffer);
    maybeAppend(this.org, localStringBuffer);
    maybeAppend(this.addresses, localStringBuffer);
    maybeAppend(this.phoneNumbers, localStringBuffer);
    maybeAppend(this.emails, localStringBuffer);
    maybeAppend(this.url, localStringBuffer);
    maybeAppend(this.birthday, localStringBuffer);
    maybeAppend(this.note, localStringBuffer);
    return localStringBuffer.toString();
  }

  public String[] getEmails()
  {
    return this.emails;
  }

  public String[] getNames()
  {
    return this.names;
  }

  public String getNote()
  {
    return this.note;
  }

  public String getOrg()
  {
    return this.org;
  }

  public String[] getPhoneNumbers()
  {
    return this.phoneNumbers;
  }

  public String getPronunciation()
  {
    return this.pronunciation;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getURL()
  {
    return this.url;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.AddressBookParsedResult
 * JD-Core Version:    0.6.2
 */