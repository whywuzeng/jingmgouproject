package com.alibaba.sdk.android.oss.util;

import android.util.Pair;
import com.alibaba.sdk.android.dpa.util.ToolKit;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.model.AccessControlList;
import com.alibaba.sdk.android.oss.model.AuthenticationType;
import com.alibaba.sdk.android.oss.model.ListObjectResult;
import com.alibaba.sdk.android.oss.model.ListObjectResult.ObjectInfo;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.OSSFederationToken;
import com.alibaba.sdk.android.oss.model.OSSResponseInfo;
import com.alibaba.sdk.android.oss.model.ObjectMeta;
import com.alibaba.sdk.android.oss.model.Range;
import com.alibaba.sdk.android.oss.model.ResourceToQuery;
import com.alibaba.sdk.android.oss.model.SharedComponent;
import com.alibaba.sdk.android.oss.model.UploadPartInfo;
import com.alibaba.sdk.android.oss.model.XOSSMeta;
import com.alibaba.sdk.android.oss.storage.BaseObject;
import com.alibaba.sdk.android.oss.storage.OSSBucket;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OSSToolKit extends ToolKit
{
  static
  {
    if (!OSSToolKit.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public static OSSException buildInterruptException(String paramString1, String paramString2)
  {
    return new OSSException(paramString1, paramString2, new InterruptedException());
  }

  public static OSSException buildLocalException(String paramString1, String paramString2, Exception paramException)
  {
    if ((paramException instanceof OSSException))
      return (OSSException)paramException;
    return new OSSException(paramString1, paramString2, paramException);
  }

  public static OSSException buildMd5CheckException(HttpResponse paramHttpResponse, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    OSSResponseInfo localOSSResponseInfo = new OSSResponseInfo();
    localOSSResponseInfo.setCode("InvalidDigest");
    localOSSResponseInfo.setMessage("Local MD5 Checksum Invalid. Local calculating result is: " + paramString3 + ", but the server side is: " + paramString4);
    localOSSResponseInfo.setRequestId(paramHttpResponse.getFirstHeader("x-oss-request-id").getValue());
    return new OSSException(paramString1, paramString2, localOSSResponseInfo);
  }

  public static void buildMutilPartUploadRequest(HttpUriRequest paramHttpUriRequest, BaseObject paramBaseObject, ResourceToQuery paramResourceToQuery)
  {
    String str1 = epoch2GMTFormat(OSSClient.getStandardEpochTimeInSecond());
    String str2 = "MBAAS_OSS_Android_1.4.0_" + getUserAgent();
    if (isNeedToAuthorize(paramHttpUriRequest, paramBaseObject.getLabeledBucket().getBucketACL()))
      if (OSSClient.getAuthenticationType() == AuthenticationType.ORIGIN_AKSK)
        paramHttpUriRequest.setHeader("Authorization", paramBaseObject.getLabeledBucket().generateToken(paramHttpUriRequest.getMethod(), "", "", str1, "", paramResourceToQuery.toCanoResource()));
    while (true)
    {
      paramHttpUriRequest.setHeader("Content-Type", "");
      paramHttpUriRequest.setHeader("Date", str1);
      paramHttpUriRequest.setHeader("User-Agent", str2);
      return;
      OSSFederationToken localOSSFederationToken = OSSClient.getFederationToken();
      String str3 = "x-oss-security-token:" + localOSSFederationToken.getSecurityToken();
      paramBaseObject = paramBaseObject.getLabeledBucket().generateTokenWithFederationToken(localOSSFederationToken, paramHttpUriRequest.getMethod(), "", "", str1, str3 + "\n", paramResourceToQuery.toCanoResource());
      paramHttpUriRequest.setHeader("x-oss-security-token", localOSSFederationToken.getSecurityToken());
      paramHttpUriRequest.setHeader("Authorization", paramBaseObject);
      continue;
      if ((isReadOnlyHttpMethod(paramHttpUriRequest.getMethod())) && (paramBaseObject.getLabeledBucket().getRefer() != null))
        paramHttpUriRequest.setHeader("Referer", paramBaseObject.getLabeledBucket().getRefer());
    }
  }

  public static OSSException buildNetworkException(HttpResponse paramHttpResponse, String paramString1, String paramString2)
    throws Exception
  {
    Object localObject = "";
    if (paramHttpResponse.getEntity() != null)
    {
      localObject = new byte[2048];
      localObject = new String((byte[])localObject, 0, ToolKit.readFullyToBuffer((byte[])localObject, 2048, paramHttpResponse.getEntity().getContent()), Charset.defaultCharset());
    }
    return buildLocalException(paramString1, paramString2, new IOException((String)localObject));
  }

  public static void buildObjectListRequest(HttpUriRequest paramHttpUriRequest, OSSBucket paramOSSBucket, ResourceToQuery paramResourceToQuery)
  {
    String str1 = epoch2GMTFormat(OSSClient.getStandardEpochTimeInSecond());
    String str2 = "MBAAS_OSS_Android_1.4.0_" + getUserAgent();
    if (OSSClient.getAuthenticationType() == AuthenticationType.ORIGIN_AKSK)
      paramHttpUriRequest.setHeader("Authorization", paramOSSBucket.generateToken(paramHttpUriRequest.getMethod(), "", "", str1, "", paramResourceToQuery.toCanoResource()));
    while (true)
    {
      paramHttpUriRequest.setHeader("Content-Type", "");
      paramHttpUriRequest.setHeader("Date", str1);
      paramHttpUriRequest.setHeader("User-Agent", str2);
      paramHttpUriRequest.setHeader("Accept-Encoding", "*");
      return;
      OSSFederationToken localOSSFederationToken = OSSClient.getFederationToken();
      String str3 = "x-oss-security-token:" + localOSSFederationToken.getSecurityToken() + "\n";
      paramOSSBucket = paramOSSBucket.generateTokenWithFederationToken(localOSSFederationToken, paramHttpUriRequest.getMethod(), "", "", str1, str3, paramResourceToQuery.toCanoResource());
      paramHttpUriRequest.setHeader("x-oss-security-token", localOSSFederationToken.getSecurityToken());
      paramHttpUriRequest.setHeader("Authorization", paramOSSBucket);
    }
  }

  public static OSSException buildOssException(HttpResponse paramHttpResponse, String paramString1, String paramString2)
    throws Exception
  {
    OSSResponseInfo localOSSResponseInfo = new OSSResponseInfo();
    localOSSResponseInfo.setStatusCode(paramHttpResponse.getStatusLine().getStatusCode());
    if ((paramHttpResponse.getEntity() != null) && (paramHttpResponse.getEntity().getContent() != null))
      paramHttpResponse = paramHttpResponse.getEntity().getContent();
    try
    {
      parserErrorResponseXML(localOSSResponseInfo, paramHttpResponse);
      label63: return new OSSException(paramString1, paramString2, localOSSResponseInfo);
    }
    catch (Exception paramHttpResponse)
    {
      break label63;
    }
  }

  public static void buildRequest(HttpUriRequest paramHttpUriRequest, BaseObject paramBaseObject)
  {
    ResourceToQuery localResourceToQuery = new ResourceToQuery();
    localResourceToQuery.setBaseResource("/" + paramBaseObject.getBucketName() + "/" + paramBaseObject.getObjectKey());
    buildRequestWithCompleteInfo(paramHttpUriRequest, paramBaseObject, localResourceToQuery);
  }

  public static void buildRequestWithCompleteInfo(HttpUriRequest paramHttpUriRequest, BaseObject paramBaseObject, ResourceToQuery paramResourceToQuery)
  {
    String str2 = epoch2GMTFormat(OSSClient.getStandardEpochTimeInSecond());
    String str3 = "MBAAS_OSS_Android_1.4.0_" + getUserAgent();
    String str1 = "";
    Object localObject;
    if (checkRequestIsPutOrPost(paramHttpUriRequest))
    {
      if (paramBaseObject.getRequestMeta().getContentType() == null)
        str1 = "";
    }
    else
    {
      setMetaHeader(paramHttpUriRequest, paramBaseObject.getRequestMeta());
      if (!isNeedToAuthorize(paramHttpUriRequest, paramBaseObject.getLabeledBucket().getBucketACL()))
        break label276;
      if (OSSClient.getAuthenticationType() != AuthenticationType.ORIGIN_AKSK)
        break label196;
      localObject = generateCanonicalizedHeader(paramBaseObject.getRequestMeta());
      paramHttpUriRequest.setHeader("Authorization", paramBaseObject.getLabeledBucket().generateToken(paramHttpUriRequest.getMethod(), "", str1, str2, (String)localObject, paramResourceToQuery.toCanoResource()));
    }
    while (true)
    {
      paramHttpUriRequest.setHeader("Date", str2);
      paramHttpUriRequest.setHeader("User-Agent", str3);
      if ((paramBaseObject.getRange() != null) && (paramHttpUriRequest.getMethod().equalsIgnoreCase("GET")))
        paramHttpUriRequest.setHeader("Range", paramBaseObject.getRange().toString());
      return;
      str1 = paramBaseObject.getRequestMeta().getContentType();
      break;
      label196: localObject = OSSClient.getFederationToken();
      paramBaseObject.getRequestMeta().addXOSSMetaDirectly("x-oss-security-token", ((OSSFederationToken)localObject).getSecurityToken());
      String str4 = generateCanonicalizedHeader(paramBaseObject.getRequestMeta());
      paramResourceToQuery = paramBaseObject.getLabeledBucket().generateTokenWithFederationToken((OSSFederationToken)localObject, paramHttpUriRequest.getMethod(), "", str1, str2, str4, paramResourceToQuery.toCanoResource());
      paramHttpUriRequest.setHeader("x-oss-security-token", ((OSSFederationToken)localObject).getSecurityToken());
      paramHttpUriRequest.setHeader("Authorization", paramResourceToQuery);
      continue;
      label276: if ((isReadOnlyHttpMethod(paramHttpUriRequest.getMethod())) && (paramBaseObject.getLabeledBucket().getRefer() != null))
        paramHttpUriRequest.setHeader("Referer", paramBaseObject.getLabeledBucket().getRefer());
    }
  }

  public static byte[] calFileMd5sum(String paramString)
    throws NoSuchAlgorithmException, IOException
  {
    MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
    byte[] arrayOfByte = new byte[4096];
    paramString = new FileInputStream(new File(paramString));
    while (true)
    {
      int i = paramString.read(arrayOfByte);
      if (i == -1)
        break;
      localMessageDigest.update(arrayOfByte, 0, i);
    }
    paramString.close();
    return localMessageDigest.digest();
  }

  public static String checkChildNotNullAndGetValue(Node paramNode)
  {
    if (paramNode.getFirstChild() != null)
      return paramNode.getFirstChild().getNodeValue();
    return null;
  }

  public static void checkETagMd5Invalid(String paramString1, String paramString2, MessageDigest paramMessageDigest, HttpResponse paramHttpResponse)
    throws OSSException
  {
    assert ((paramMessageDigest != null) && (paramHttpResponse != null));
    paramMessageDigest = '"' + getMd5StrFromBytes(paramMessageDigest.digest()) + '"';
    if (paramHttpResponse.getFirstHeader("ETag") == null);
    String str;
    do
    {
      return;
      str = paramHttpResponse.getFirstHeader("ETag").getValue();
    }
    while (paramMessageDigest.equalsIgnoreCase(str));
    OSSLog.logE("[checkETagMd5] - local: " + paramMessageDigest + "  remote: " + str);
    throw buildMd5CheckException(paramHttpResponse, paramString1, paramString2, paramMessageDigest, str);
  }

  public static boolean checkRequestIsGetOrHead(HttpUriRequest paramHttpUriRequest)
  {
    paramHttpUriRequest = paramHttpUriRequest.getMethod();
    return (paramHttpUriRequest.equalsIgnoreCase("GET")) || (paramHttpUriRequest.equalsIgnoreCase("HEAD"));
  }

  public static boolean checkRequestIsPutOrPost(HttpUriRequest paramHttpUriRequest)
  {
    paramHttpUriRequest = paramHttpUriRequest.getMethod();
    return (paramHttpUriRequest.equalsIgnoreCase("PUT")) || (paramHttpUriRequest.equalsIgnoreCase("POST"));
  }

  public static void consumeResponseEntity(HttpResponse paramHttpResponse)
  {
    if ((paramHttpResponse != null) && (paramHttpResponse.getEntity() != null));
    try
    {
      paramHttpResponse.getEntity().consumeContent();
      return;
    }
    catch (IOException paramHttpResponse)
    {
      while (!OSSLog.isEnableLog());
      paramHttpResponse.printStackTrace();
    }
  }

  public static void ensureBucketNameValid(String paramString)
  {
    if (!validateBucketName(paramString))
      throw new IllegalArgumentException("BucketName 无效。BucketName命名规范：只能包括小写字母，数字和短横线（-）；必须以小写字母或者数字开头；长度必须在3-255字节之间。");
  }

  public static void ensureObjectKeyValid(String paramString)
  {
    if (!validateObjectKey(paramString))
      throw new IllegalArgumentException("Object Key无效。Object名称在使用UTF-8编码后长度必须在 1-1023字节之间，而且不能包含回车、换行、以及xml1.0不支持的字符，同时也不能以“/”或者“\\”开头。");
  }

  public static HttpResponse executeRequestWithLog(HttpClient paramHttpClient, HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    if (OSSLog.isEnableLog())
      printRequestHeader(paramHttpUriRequest);
    paramHttpClient = paramHttpClient.execute(paramHttpUriRequest);
    if (OSSLog.isEnableLog())
      printResponseHeader(paramHttpClient);
    return paramHttpClient;
  }

  public static String formatContentType(String paramString)
  {
    String str;
    if (paramString == null)
      str = "";
    do
    {
      return str;
      if (paramString.length() == 0)
        return "";
      str = paramString;
    }
    while (paramString.indexOf('/') != -1);
    return "application/" + paramString;
  }

  public static String generateCanonicalizedHeader(XOSSMeta paramXOSSMeta)
  {
    Object localObject = paramXOSSMeta.getXOSSMetaHeaderList();
    Collections.sort((List)localObject, new Comparator()
    {
      public int compare(Pair<String, String> paramAnonymousPair1, Pair<String, String> paramAnonymousPair2)
      {
        return ((String)paramAnonymousPair1.first).compareTo((String)paramAnonymousPair2.first);
      }
    });
    StringBuilder localStringBuilder = new StringBuilder();
    paramXOSSMeta = null;
    Iterator localIterator = ((List)localObject).iterator();
    if (localIterator.hasNext())
    {
      localObject = (Pair)localIterator.next();
      if (paramXOSSMeta == null)
        localStringBuilder.append((String)((Pair)localObject).first + ":" + (String)((Pair)localObject).second);
      while (true)
      {
        paramXOSSMeta = (XOSSMeta)localObject;
        break;
        if (((String)paramXOSSMeta.first).equals(((Pair)localObject).first))
          localStringBuilder.append("," + (String)((Pair)localObject).second);
        else
          localStringBuilder.append("\n" + (String)((Pair)localObject).first + ":" + (String)((Pair)localObject).second);
      }
    }
    localObject = localStringBuilder.toString();
    paramXOSSMeta = (XOSSMeta)localObject;
    if (!isEmptyString((String)localObject))
    {
      paramXOSSMeta = ((String)localObject).trim();
      paramXOSSMeta = paramXOSSMeta + "\n";
    }
    return paramXOSSMeta;
  }

  public static String generateToken(String paramString1, String paramString2, String paramString3)
  {
    String str = null;
    try
    {
      paramString2 = getHmacSha1Signature(paramString3, paramString2);
      str = paramString2;
      paramString2 = paramString2.trim();
      OSSLog.logD("[genAuth] - signature: " + paramString2);
      return "OSS " + paramString1 + ":" + paramString2;
    }
    catch (Exception paramString2)
    {
      while (true)
      {
        OSSLog.logD(paramString2.toString());
        paramString2 = str;
      }
    }
  }

  public static ObjectMeta getObjectMetadataFromResponse(HttpResponse paramHttpResponse)
    throws ParseException
  {
    paramHttpResponse = paramHttpResponse.getAllHeaders();
    ObjectMeta localObjectMeta = new ObjectMeta();
    int j = paramHttpResponse.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramHttpResponse[i];
      if (localObject.getName().equalsIgnoreCase("Content-Length"))
        localObjectMeta.setContentLength(localObject.getValue());
      while (true)
      {
        i += 1;
        break;
        if (localObject.getName().equalsIgnoreCase("Content-Type"))
          localObjectMeta.setContentType(localObject.getValue());
        else if (localObject.getName().equalsIgnoreCase("Content-Encoding"))
          localObjectMeta.setContentEncoding(localObject.getValue());
        else if (localObject.getName().equalsIgnoreCase("Content-Disposition"))
          localObjectMeta.setContentDisposition(localObject.getValue());
        else if (localObject.getName().equalsIgnoreCase("Cache-control"))
          localObjectMeta.setCacheControl(localObject.getValue());
        else if (localObject.getName().equalsIgnoreCase("Expires"))
          localObjectMeta.setExpirationTime(gmtFormat2Date(localObject.getValue()));
        else if (localObject.getName().equalsIgnoreCase("Date"))
          localObjectMeta.setDate(gmtFormat2Date(localObject.getValue()));
        else if (localObject.getName().equalsIgnoreCase("Last-Modified"))
          localObjectMeta.setLastModified(gmtFormat2Date(localObject.getValue()));
        else if (localObject.getName().equalsIgnoreCase("Server"))
          localObjectMeta.setServer(localObject.getValue());
        else if (localObject.getName().equals("ETag"))
          localObjectMeta.seteTag(localObject.getValue());
        else if (localObject.getName().equalsIgnoreCase("Content-Range"))
          localObjectMeta.setContentRange(localObject.getValue());
        else if (localObject.getName().startsWith("x-oss-"))
          localObjectMeta.addXOSSMetaDirectly(localObject.getName(), localObject.getValue());
      }
    }
    return localObjectMeta;
  }

  public static String getUserAgent()
  {
    String str2 = System.getProperty("http.agent");
    String str1 = str2;
    if (isEmptyString(str2))
      str1 = "Java" + System.getProperty("java.version") + "-Apache-HttpClient";
    return str1;
  }

  public static OSSException handleExceptionalResponse(HttpResponse paramHttpResponse, HttpUriRequest paramHttpUriRequest, String paramString1, String paramString2)
  {
    try
    {
      if (isResponseFromOssServer(paramHttpResponse))
      {
        localOSSException = buildOssException(paramHttpResponse, paramString1, paramString2);
        consumeResponseEntity(paramHttpResponse);
        return localOSSException;
      }
      OSSException localOSSException = buildNetworkException(paramHttpResponse, paramString1, paramString2);
      consumeResponseEntity(paramHttpResponse);
      return localOSSException;
    }
    catch (Exception paramHttpResponse)
    {
    }
    try
    {
      paramHttpUriRequest.abort();
      label44: return buildLocalException(paramString1, paramString2, paramHttpResponse);
    }
    catch (Exception paramHttpUriRequest)
    {
      break label44;
    }
  }

  public static boolean isEmptyString(String paramString)
  {
    return (paramString == null) || (paramString.equals(""));
  }

  public static boolean isNeedToAuthorize(HttpUriRequest paramHttpUriRequest, AccessControlList paramAccessControlList)
  {
    paramHttpUriRequest = paramHttpUriRequest.getMethod();
    if (paramAccessControlList == AccessControlList.PRIVATE);
    while ((paramAccessControlList == AccessControlList.PUBLIC_READ) && (!paramHttpUriRequest.equalsIgnoreCase("GET")) && (!paramHttpUriRequest.equalsIgnoreCase("HEAD")))
      return true;
    return false;
  }

  public static boolean isReadOnlyHttpMethod(String paramString)
  {
    return (paramString.equalsIgnoreCase("GET")) || (paramString.equalsIgnoreCase("HEAD"));
  }

  public static boolean isResponseFromOssServer(HttpResponse paramHttpResponse)
  {
    paramHttpResponse = paramHttpResponse.getFirstHeader("Server");
    return (paramHttpResponse != null) && (paramHttpResponse.getValue() != null) && (paramHttpResponse.getValue().equalsIgnoreCase("AliyunOSS"));
  }

  public static String parseCommonPrefix(NodeList paramNodeList)
  {
    int i = 0;
    if (i < paramNodeList.getLength())
    {
      Node localNode = paramNodeList.item(i);
      String str = localNode.getNodeName();
      if (str == null);
      while (!str.equals("Prefix"))
      {
        i += 1;
        break;
      }
      return checkChildNotNullAndGetValue(localNode);
    }
    return null;
  }

  public static ListObjectResult parseObjectListResponse(InputStream paramInputStream)
    throws Exception
  {
    Object localObject1 = SharedComponent.getDomBuilderFact().newDocumentBuilder().parse(paramInputStream).getDocumentElement();
    paramInputStream = new ListObjectResult();
    OSSLog.logD("[parseObjectListResponse] - " + ((Element)localObject1).getNodeName());
    localObject1 = ((Element)localObject1).getChildNodes();
    int i = 0;
    if (i < ((NodeList)localObject1).getLength())
    {
      Object localObject2 = ((NodeList)localObject1).item(i);
      String str = ((Node)localObject2).getNodeName();
      if (str == null);
      while (true)
      {
        i += 1;
        break;
        if (str.equals("Name"))
        {
          paramInputStream.setBucketName(checkChildNotNullAndGetValue((Node)localObject2));
        }
        else if (str.equals("Prefix"))
        {
          paramInputStream.setPrefix(checkChildNotNullAndGetValue((Node)localObject2));
        }
        else if (str.equals("Marker"))
        {
          paramInputStream.setMarker(checkChildNotNullAndGetValue((Node)localObject2));
        }
        else if (str.equals("Delimiter"))
        {
          paramInputStream.setDelimiter(checkChildNotNullAndGetValue((Node)localObject2));
        }
        else if (str.equals("MaxKeys"))
        {
          paramInputStream.setMaxKeys(Integer.valueOf(checkChildNotNullAndGetValue((Node)localObject2)).intValue());
        }
        else if (str.equals("NextMarker"))
        {
          paramInputStream.setNextMarker(checkChildNotNullAndGetValue((Node)localObject2));
        }
        else if (str.equals("IsTruncated"))
        {
          paramInputStream.setTruncated(checkChildNotNullAndGetValue((Node)localObject2).equals("true"));
        }
        else if (str.equals("Contents"))
        {
          if (((Node)localObject2).getChildNodes() != null)
            paramInputStream.getObjectInfoList().add(parseSingleObjectInfo(((Node)localObject2).getChildNodes()));
        }
        else if ((str.equals("CommonPrefixes")) && (((Node)localObject2).getChildNodes() != null))
        {
          localObject2 = parseCommonPrefix(((Node)localObject2).getChildNodes());
          if (localObject2 != null)
            paramInputStream.getCommonPrefixList().add(localObject2);
        }
      }
    }
    return paramInputStream;
  }

  public static List<UploadPartInfo> parsePartListFromResponse(InputStream paramInputStream)
    throws Exception
  {
    Object localObject1 = SharedComponent.getDomBuilderFact().newDocumentBuilder().parse(paramInputStream).getDocumentElement();
    OSSLog.logD("[parseObjectListResponse] - " + ((Element)localObject1).getNodeName());
    paramInputStream = new ArrayList();
    localObject1 = ((Element)localObject1).getChildNodes();
    int i = 0;
    if (i < ((NodeList)localObject1).getLength())
    {
      Object localObject2 = ((NodeList)localObject1).item(i);
      Object localObject3 = ((Node)localObject2).getNodeName();
      if (localObject3 == null);
      while (true)
      {
        i += 1;
        break;
        if (((String)localObject3).equals("Part"))
        {
          localObject2 = ((Node)localObject2).getChildNodes();
          localObject3 = new UploadPartInfo();
          int j = 0;
          if (j < ((NodeList)localObject2).getLength())
          {
            Node localNode = ((NodeList)localObject2).item(j);
            String str = localNode.getNodeName();
            if (str == null);
            while (true)
            {
              j += 1;
              break;
              if (str.equals("PartNumber"))
                ((UploadPartInfo)localObject3).setPartNumber(Integer.valueOf(checkChildNotNullAndGetValue(localNode)).intValue());
              else if (str.equals("LastModified"))
                ((UploadPartInfo)localObject3).setLastModified(checkChildNotNullAndGetValue(localNode));
              else if (str.equals("ETag"))
                ((UploadPartInfo)localObject3).seteTag(checkChildNotNullAndGetValue(localNode));
              else if (str.equals("Size"))
                ((UploadPartInfo)localObject3).setPartSize(Integer.valueOf(checkChildNotNullAndGetValue(localNode)).intValue());
            }
          }
          paramInputStream.add(localObject3);
        }
      }
    }
    return paramInputStream;
  }

  public static ListObjectResult.ObjectInfo parseSingleObjectInfo(NodeList paramNodeList)
  {
    ListObjectResult.ObjectInfo localObjectInfo = new ListObjectResult.ObjectInfo();
    int i = 0;
    if (i < paramNodeList.getLength())
    {
      Node localNode = paramNodeList.item(i);
      String str = localNode.getNodeName();
      if (str == null);
      while (true)
      {
        i += 1;
        break;
        if (str.equals("Key"))
          localObjectInfo.setObjectKey(checkChildNotNullAndGetValue(localNode));
        else if (str.equals("LastModified"))
          localObjectInfo.setLastModified(checkChildNotNullAndGetValue(localNode));
        else if (str.equals("Size"))
          localObjectInfo.setSize(Integer.valueOf(checkChildNotNullAndGetValue(localNode)).intValue());
        else if (str.equals("ETag"))
          localObjectInfo.setEtag(checkChildNotNullAndGetValue(localNode));
        else if (str.equals("Type"))
          localObjectInfo.setType(checkChildNotNullAndGetValue(localNode));
      }
    }
    return localObjectInfo;
  }

  public static String parseUploadIdFromXml(InputStream paramInputStream)
  {
    Object localObject = null;
    try
    {
      paramInputStream = SharedComponent.getDomBuilderFact().newDocumentBuilder().parse(paramInputStream).getDocumentElement();
      OSSLog.logD("[item] - " + paramInputStream.getNodeName());
      NodeList localNodeList = paramInputStream.getChildNodes();
      int i = 0;
      while (true)
      {
        paramInputStream = localObject;
        if (i < localNodeList.getLength())
        {
          paramInputStream = localNodeList.item(i);
          String str = paramInputStream.getNodeName();
          if ((str != null) && (str.equalsIgnoreCase("UploadId")))
            paramInputStream = checkChildNotNullAndGetValue(paramInputStream);
        }
        else
        {
          return paramInputStream;
        }
        i += 1;
      }
    }
    catch (Exception paramInputStream)
    {
    }
    return null;
  }

  public static void parserErrorResponseXML(OSSResponseInfo paramOSSResponseInfo, InputStream paramInputStream)
    throws Exception
  {
    paramInputStream = SharedComponent.getDomBuilderFact().newDocumentBuilder().parse(paramInputStream);
    paramOSSResponseInfo.setResponseInfoDom(paramInputStream);
    paramInputStream = paramInputStream.getDocumentElement();
    OSSLog.logD("[item] - " + paramInputStream.getNodeName());
    paramInputStream = paramInputStream.getChildNodes();
    int i = 0;
    if (i < paramInputStream.getLength())
    {
      Node localNode = paramInputStream.item(i);
      String str = localNode.getNodeName();
      if (str == null);
      while (true)
      {
        i += 1;
        break;
        if (str.equals("Code"))
          paramOSSResponseInfo.setCode(checkChildNotNullAndGetValue(localNode));
        else if (str.equals("Message"))
          paramOSSResponseInfo.setMessage(checkChildNotNullAndGetValue(localNode));
        else if (str.equals("RequestId"))
          paramOSSResponseInfo.setRequestId(checkChildNotNullAndGetValue(localNode));
        else if (str.equals("HostId"))
          paramOSSResponseInfo.setHostId(checkChildNotNullAndGetValue(localNode));
      }
    }
  }

  public static void printRequestHeader(HttpRequest paramHttpRequest)
  {
    OSSLog.logD("[printRequest] - line : " + paramHttpRequest.getRequestLine());
    paramHttpRequest = paramHttpRequest.getAllHeaders();
    int j = paramHttpRequest.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramHttpRequest[i];
      OSSLog.logD("[printRequest] - " + localObject.getName() + ": " + localObject.getValue());
      i += 1;
    }
  }

  public static void printResponseHeader(HttpResponse paramHttpResponse)
  {
    OSSLog.logD("[printResponse] - line : " + paramHttpResponse.getStatusLine());
    paramHttpResponse = paramHttpResponse.getAllHeaders();
    int j = paramHttpResponse.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramHttpResponse[i];
      OSSLog.logD("[printResponse] - " + localObject.getName() + ": " + localObject.getValue());
      i += 1;
    }
  }

  public static void setMetaHeader(HttpUriRequest paramHttpUriRequest, ObjectMeta paramObjectMeta)
  {
    paramObjectMeta = paramObjectMeta.getMetaNameValues().iterator();
    while (paramObjectMeta.hasNext())
    {
      BasicNameValuePair localBasicNameValuePair = (BasicNameValuePair)paramObjectMeta.next();
      if (!isEmptyString(localBasicNameValuePair.getValue()))
        paramHttpUriRequest.setHeader(localBasicNameValuePair.getName(), localBasicNameValuePair.getValue());
    }
  }

  public static void suspend(long paramLong)
  {
    try
    {
      Thread.sleep(paramLong);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
    }
  }

  public static boolean validateBucketName(String paramString)
  {
    if (paramString == null)
      return false;
    return paramString.matches("^[a-z0-9][a-z0-9\\-]{1,61}[a-z0-9]$");
  }

  public static boolean validateObjectKey(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      try
      {
        byte[] arrayOfByte = paramString.getBytes("utf-8");
        int i = paramString.toCharArray()[0];
        if ((i != 47) && (i != 92) && (arrayOfByte.length > 0) && (arrayOfByte.length < 1024))
          return true;
      }
      catch (UnsupportedEncodingException paramString)
      {
      }
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.util.OSSToolKit
 * JD-Core Version:    0.6.2
 */