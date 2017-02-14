package com.yolanda.nohttp;

import android.os.Build.VERSION;
import android.text.TextUtils;
import com.yolanda.nohttp.security.SecureVerifier;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;

public abstract class BasicConnection
{
  protected final String BOUNDARY = createBoundry();
  protected final String END_BOUNDARY = "--" + this.BOUNDARY + "--";
  protected final String START_BOUNDARY = "--" + this.BOUNDARY;

  public static void checkRequestMethod(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 7))
      throw new RuntimeException("Invalid HTTP method: " + paramInt);
  }

  public static boolean hasResponseBody(int paramInt)
  {
    return ((100 > paramInt) || (paramInt >= 200)) && (paramInt != 204) && (paramInt != 205) && (paramInt != 304);
  }

  public static boolean hasResponseBody(int paramInt1, int paramInt2)
  {
    return (paramInt1 != 4) && (hasResponseBody(paramInt2));
  }

  private void writeFormFile(OutputStream paramOutputStream, String paramString, Binary paramBinary)
    throws IOException
  {
    Logger.i(paramString + " is File");
    paramOutputStream.write(createFormFileField(paramString, paramBinary, paramBinary.getCharset()).getBytes());
    paramOutputStream.write(paramBinary.getByteArray());
    paramOutputStream.write("\r\n".getBytes());
  }

  private void writeFormString(OutputStream paramOutputStream, String paramString1, String paramString2, String paramString3)
    throws UnsupportedEncodingException, IOException
  {
    Logger.i(paramString1 + " = " + paramString2);
    paramOutputStream.write(createFormStringField(paramString1, paramString2, paramString3).getBytes());
    paramOutputStream.write("\r\n".getBytes());
  }

  protected String createBoundry()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 1;
    if (i >= 12)
      return localStringBuffer.toString();
    long l = System.currentTimeMillis() + i;
    if (l % 3L == 0L)
      localStringBuffer.append((char)(int)l % '\t');
    while (true)
    {
      i += 1;
      break;
      if (l % 3L == 1L)
        localStringBuffer.append((char)(int)(65L + l % 26L));
      else
        localStringBuffer.append((char)(int)(97L + l % 26L));
    }
  }

  protected String createFormFileField(String paramString1, Binary paramBinary, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.START_BOUNDARY).append("\r\n");
    localStringBuilder.append("Content-Disposition: form-data; name=\"").append(paramString1).append("\";");
    if (!TextUtils.isEmpty(paramBinary.getFileName()))
      localStringBuilder.append(" filename=\"").append(paramBinary.getFileName()).append("\"");
    localStringBuilder.append("\r\n");
    localStringBuilder.append("Content-Type: ").append(paramBinary.getMimeType()).append("; charset:").append(paramString2).append("\r\n");
    localStringBuilder.append("Content-Transfer-Encoding: binary\r\n\r\n");
    return localStringBuilder.toString();
  }

  protected String createFormStringField(String paramString1, String paramString2, String paramString3)
    throws UnsupportedEncodingException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.START_BOUNDARY).append("\r\n");
    localStringBuilder.append("Content-Disposition: form-data; name=\"").append(URLEncoder.encode(paramString1, paramString3)).append("\"\r\n");
    localStringBuilder.append("Content-Type: text/plain; charset=").append(paramString3).append("\r\n\r\n");
    localStringBuilder.append(URLEncoder.encode(paramString2, paramString3));
    return localStringBuilder.toString();
  }

  protected String getExcetionMessage(Throwable paramThrowable)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramThrowable != null)
    {
      localStringBuilder.append(paramThrowable.getClass().getName());
      localStringBuilder.append(": ");
      localStringBuilder.append(paramThrowable.getMessage());
    }
    return localStringBuilder.toString();
  }

  protected HttpURLConnection getHttpConnection(BasicAnalyzeRequest paramBasicAnalyzeRequest)
    throws IOException, URISyntaxException
  {
    Object localObject1 = paramBasicAnalyzeRequest.url();
    Logger.d("Reuqest adress:" + (String)localObject1);
    if (Build.VERSION.SDK_INT < 9)
      System.setProperty("http.keepAlive", "false");
    localObject1 = new URL((String)localObject1);
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)((URL)localObject1).openConnection();
    if ("https".equals(((URL)localObject1).getProtocol()))
      SecureVerifier.getInstance().doVerifier((HttpsURLConnection)localHttpURLConnection, paramBasicAnalyzeRequest);
    int i = paramBasicAnalyzeRequest.getRequestMethod();
    checkRequestMethod(i);
    localObject1 = RequestMethod.METHOD[i];
    Logger.d("Request method:" + (String)localObject1);
    localHttpURLConnection.setRequestMethod((String)localObject1);
    localHttpURLConnection.setDoInput(true);
    localHttpURLConnection.setDoOutput(paramBasicAnalyzeRequest.isOutPut());
    localHttpURLConnection.setConnectTimeout(paramBasicAnalyzeRequest.getConnectTimeout());
    localHttpURLConnection.setReadTimeout(paramBasicAnalyzeRequest.getReadTimeout());
    Object localObject2 = paramBasicAnalyzeRequest.getHeaders();
    localObject1 = localObject2;
    if (localObject2 == null)
      localObject1 = new Headers();
    ((Headers)localObject1).set("Accept-Encoding", "gzip");
    if (((Headers)localObject1).get("Accept") == null)
      ((Headers)localObject1).set("Accept", "*/*");
    if (((Headers)localObject1).get("Cache-Control") == null)
      ((Headers)localObject1).set("Cache-Control", "no-cache");
    if (((Headers)localObject1).get("Connection") == null)
      ((Headers)localObject1).set("Connection", "Keep-Alive");
    if (((Headers)localObject1).get("User-Agent") == null)
      ((Headers)localObject1).set("User-Agent", getUserAgent());
    localObject2 = NoHttp.getDefaultCookieManager();
    if (localObject2 != null)
      Headers.addCookiesToHeaders((Headers)localObject1, ((CookieManager)localObject2).get(new URI(paramBasicAnalyzeRequest.url()), Headers.toMultimap((Headers)localObject1)));
    localObject2 = Headers.parseRequestCookie((Headers)localObject1);
    ((Headers)localObject1).removeAll("Cookie");
    ((Headers)localObject1).removeAll("Cookie2");
    localObject2 = ((Map)localObject2).entrySet().iterator();
    if (!((Iterator)localObject2).hasNext())
    {
      Logger.i("-------Request Headers Start-------");
      i = 0;
    }
    while (true)
    {
      if (i >= ((Headers)localObject1).size())
      {
        Logger.i("-------Request Headers End-------");
        if ((!paramBasicAnalyzeRequest.isOutPut()) || (!paramBasicAnalyzeRequest.hasBinary()))
          break label553;
        localHttpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.BOUNDARY);
        return localHttpURLConnection;
        Object localObject3 = (Map.Entry)((Iterator)localObject2).next();
        str = (String)((Map.Entry)localObject3).getKey();
        localObject3 = (String)((Map.Entry)localObject3).getValue();
        if ((TextUtils.isEmpty(str)) || (TextUtils.isEmpty((CharSequence)localObject3)))
          break;
        ((Headers)localObject1).add(str, (String)localObject3);
        break;
      }
      localObject2 = ((Headers)localObject1).name(i);
      String str = ((Headers)localObject1).value(i);
      Logger.i(localObject2 + ": " + str);
      localHttpURLConnection.addRequestProperty((String)localObject2, str);
      i += 1;
    }
    label553: localHttpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + paramBasicAnalyzeRequest.getParamsEncoding());
    return localHttpURLConnection;
  }

  protected abstract String getUserAgent();

  protected byte[] readResponseBody(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1024];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1)
      {
        localByteArrayOutputStream.flush();
        localByteArrayOutputStream.close();
        paramInputStream.close();
        return localByteArrayOutputStream.toByteArray();
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
  }

  protected void sendRequestParam(HttpURLConnection paramHttpURLConnection, AnalyzeRequest paramAnalyzeRequest)
    throws UnsupportedEncodingException, IOException
  {
    if (paramAnalyzeRequest.isOutPut())
    {
      if (!paramAnalyzeRequest.hasBinary())
        break label28;
      writeFormStreamData(paramHttpURLConnection.getOutputStream(), paramAnalyzeRequest);
    }
    label28: 
    do
    {
      do
        return;
      while (!paramAnalyzeRequest.isOutPut());
      paramAnalyzeRequest = paramAnalyzeRequest.getRequestBody();
    }
    while (paramAnalyzeRequest == null);
    paramHttpURLConnection.getOutputStream().write(paramAnalyzeRequest);
  }

  protected void writeFormStreamData(OutputStream paramOutputStream, AnalyzeRequest paramAnalyzeRequest)
    throws UnsupportedEncodingException, IOException
  {
    paramOutputStream = new DataOutputStream(paramOutputStream);
    String str1 = paramAnalyzeRequest.getParamsEncoding();
    Iterator localIterator = paramAnalyzeRequest.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        paramOutputStream.write(("\r\n" + this.END_BOUNDARY + "\r\n").getBytes());
        paramOutputStream.flush();
        paramOutputStream.close();
        return;
      }
      String str2 = (String)localIterator.next();
      Object localObject = paramAnalyzeRequest.value(str2);
      if ((localObject != null) && ((localObject instanceof String)))
        writeFormString(paramOutputStream, str2, localObject.toString(), str1);
      if ((localObject != null) && ((localObject instanceof Binary)))
        writeFormFile(paramOutputStream, str2, (Binary)localObject);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.BasicConnection
 * JD-Core Version:    0.6.2
 */