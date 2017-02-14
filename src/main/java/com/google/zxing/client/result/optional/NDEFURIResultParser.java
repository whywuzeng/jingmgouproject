package com.google.zxing.client.result.optional;

import com.google.zxing.Result;
import com.google.zxing.client.result.URIParsedResult;

final class NDEFURIResultParser extends AbstractNDEFResultParser
{
  private static final String[] URI_PREFIXES = { null, "http://www.", "https://www.", "http://", "https://", "tel:", "mailto:", "ftp://anonymous:anonymous@", "ftp://ftp.", "ftps://", "sftp://", "smb://", "nfs://", "ftp://", "dav://", "news:", "telnet://", "imap:", "rtsp://", "urn:", "pop:", "sip:", "sips:", "tftp:", "btspp://", "btl2cap://", "btgoep://", "tcpobex://", "irdaobex://", "file://", "urn:epc:id:", "urn:epc:tag:", "urn:epc:pat:", "urn:epc:raw:", "urn:epc:", "urn:nfc:" };

  static String decodeURIPayload(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte[0] & 0xFF;
    String str = null;
    if (i < URI_PREFIXES.length)
      str = URI_PREFIXES[i];
    paramArrayOfByte = bytesToString(paramArrayOfByte, 1, paramArrayOfByte.length - 1, "UTF8");
    if (str == null)
      return paramArrayOfByte;
    return str + paramArrayOfByte;
  }

  public static URIParsedResult parse(Result paramResult)
  {
    paramResult = paramResult.getRawBytes();
    if (paramResult == null);
    do
    {
      return null;
      paramResult = NDEFRecord.readRecord(paramResult, 0);
    }
    while ((paramResult == null) || (!paramResult.isMessageBegin()) || (!paramResult.isMessageEnd()) || (!paramResult.getType().equals("U")));
    return new URIParsedResult(decodeURIPayload(paramResult.getPayload()), null);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.optional.NDEFURIResultParser
 * JD-Core Version:    0.6.2
 */