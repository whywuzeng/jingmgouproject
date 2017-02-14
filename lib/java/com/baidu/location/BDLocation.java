package com.baidu.location;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.location.b.f;
import java.util.ArrayList;
import java.util.List;

public final class BDLocation
  implements Parcelable, f
{
  public static final String BDLOCATION_BD09LL_TO_GCJ02 = "bd09ll2gcj";
  public static final String BDLOCATION_BD09_TO_GCJ02 = "bd092gcj";
  public static final String BDLOCATION_GCJ02_TO_BD09 = "bd09";
  public static final String BDLOCATION_GCJ02_TO_BD09LL = "bd09ll";
  public static final Parcelable.Creator CREATOR = new a();
  public static final int LOCATION_WHERE_IN_CN = 1;
  public static final int LOCATION_WHERE_OUT_CN = 0;
  public static final int LOCATION_WHERE_UNKNOW = 2;
  public static final int OPERATORS_TYPE_MOBILE = 1;
  public static final int OPERATORS_TYPE_TELECOMU = 3;
  public static final int OPERATORS_TYPE_UNICOM = 2;
  public static final int OPERATORS_TYPE_UNKONW = 0;
  public static final int TypeCacheLocation = 65;
  public static final int TypeCriteriaException = 62;
  public static final int TypeGpsLocation = 61;
  public static final int TypeNetWorkException = 63;
  public static final int TypeNetWorkLocation = 161;
  public static final int TypeNone = 0;
  public static final int TypeOffLineLocation = 66;
  public static final int TypeOffLineLocationFail = 67;
  public static final int TypeOffLineLocationNetworkFail = 68;
  public static final int TypeServerError = 167;
  private float k0 = 0.0F;
  private String k1 = null;
  private String k2 = null;
  private int k3;
  private int kA = -1;
  private double kB = 4.9E-324D;
  private String kC = null;
  private boolean kD = false;
  private boolean kE = false;
  private boolean kF = false;
  private String kG = null;
  private String kH = "";
  private float kI = -1.0F;
  private String kJ = null;
  private double kK = 4.9E-324D;
  private boolean kL = false;
  private Address kM = new Address.Builder().build();
  private boolean kN = false;
  private String kO = null;
  private String kP = null;
  private int kQ = 1;
  private int kR = 0;
  private float kS = 0.0F;
  private boolean kT = false;
  private List kU = null;
  private String kV = null;
  private int kW = 0;
  private double kX = 4.9E-324D;
  private String kY = null;
  private boolean kZ = false;
  private String kz = null;

  public BDLocation()
  {
  }

  private BDLocation(Parcel paramParcel)
  {
    this.kW = paramParcel.readInt();
    this.k1 = paramParcel.readString();
    this.kB = paramParcel.readDouble();
    this.kX = paramParcel.readDouble();
    this.kK = paramParcel.readDouble();
    this.k0 = paramParcel.readFloat();
    this.kS = paramParcel.readFloat();
    this.kA = paramParcel.readInt();
    this.kI = paramParcel.readFloat();
    this.kC = paramParcel.readString();
    this.kR = paramParcel.readInt();
    this.kO = paramParcel.readString();
    this.kV = paramParcel.readString();
    this.kP = paramParcel.readString();
    Object localObject = paramParcel.readString();
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    String str3 = paramParcel.readString();
    String str4 = paramParcel.readString();
    String str5 = paramParcel.readString();
    paramParcel.readString();
    String str6 = paramParcel.readString();
    String str7 = paramParcel.readString();
    this.kM = new Address.Builder().country(str6).countryCode(str7).province((String)localObject).city(str1).cityCode(str5).district(str2).street(str3).streetNumber(str4).build();
    localObject = new boolean[7];
    this.k3 = paramParcel.readInt();
    this.kH = paramParcel.readString();
    this.kG = paramParcel.readString();
    this.kJ = paramParcel.readString();
    this.kY = paramParcel.readString();
    this.kQ = paramParcel.readInt();
    try
    {
      paramParcel.readBooleanArray((boolean[])localObject);
      this.kD = localObject[0];
      this.kE = localObject[1];
      this.kN = localObject[2];
      this.kZ = localObject[3];
      this.kT = localObject[4];
      this.kL = localObject[5];
      this.kF = localObject[6];
      label494: localObject = new ArrayList();
      paramParcel.readList((List)localObject, Poi.class.getClassLoader());
      if (((List)localObject).size() == 0)
      {
        this.kU = null;
        return;
      }
      this.kU = ((List)localObject);
      return;
    }
    catch (Exception localException)
    {
      break label494;
    }
  }

  public BDLocation(BDLocation paramBDLocation)
  {
    this.kW = paramBDLocation.kW;
    this.k1 = paramBDLocation.k1;
    this.kB = paramBDLocation.kB;
    this.kX = paramBDLocation.kX;
    this.kD = paramBDLocation.kD;
    this.kK = paramBDLocation.kK;
    this.kE = paramBDLocation.kE;
    this.k0 = paramBDLocation.k0;
    this.kN = paramBDLocation.kN;
    this.kS = paramBDLocation.kS;
    this.kZ = paramBDLocation.kZ;
    this.kA = paramBDLocation.kA;
    this.kI = paramBDLocation.kI;
    this.k2 = paramBDLocation.k2;
    this.kT = paramBDLocation.kT;
    this.kz = paramBDLocation.kz;
    this.kL = paramBDLocation.kL;
    this.kM = new Address.Builder().country(paramBDLocation.kM.country).countryCode(paramBDLocation.kM.countryCode).province(paramBDLocation.kM.province).city(paramBDLocation.kM.city).cityCode(paramBDLocation.kM.cityCode).district(paramBDLocation.kM.district).street(paramBDLocation.kM.street).streetNumber(paramBDLocation.kM.streetNumber).build();
    this.kC = paramBDLocation.kC;
    this.kO = paramBDLocation.kO;
    this.kV = paramBDLocation.kV;
    this.kQ = paramBDLocation.kQ;
    this.kR = paramBDLocation.kR;
    this.kF = paramBDLocation.kF;
    this.kP = paramBDLocation.kP;
    this.k3 = paramBDLocation.k3;
    this.kH = paramBDLocation.kH;
    this.kG = paramBDLocation.kG;
    this.kJ = paramBDLocation.kJ;
    this.kY = paramBDLocation.kY;
    if (paramBDLocation.kU == null)
    {
      this.kU = null;
      return;
    }
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramBDLocation.kU.size())
    {
      Poi localPoi = (Poi)paramBDLocation.kU.get(i);
      localArrayList.add(new Poi(localPoi.getId(), localPoi.getName(), localPoi.getRank()));
      i += 1;
    }
    this.kU = localArrayList;
  }

  // ERROR //
  public BDLocation(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 10
    //   3: aload_0
    //   4: invokespecial 101	java/lang/Object:<init>	()V
    //   7: aload_0
    //   8: iconst_0
    //   9: putfield 103	com/baidu/location/BDLocation:kW	I
    //   12: aload_0
    //   13: aconst_null
    //   14: putfield 105	com/baidu/location/BDLocation:k1	Ljava/lang/String;
    //   17: aload_0
    //   18: ldc2_w 106
    //   21: putfield 109	com/baidu/location/BDLocation:kB	D
    //   24: aload_0
    //   25: ldc2_w 106
    //   28: putfield 111	com/baidu/location/BDLocation:kX	D
    //   31: aload_0
    //   32: iconst_0
    //   33: putfield 113	com/baidu/location/BDLocation:kD	Z
    //   36: aload_0
    //   37: ldc2_w 106
    //   40: putfield 115	com/baidu/location/BDLocation:kK	D
    //   43: aload_0
    //   44: iconst_0
    //   45: putfield 117	com/baidu/location/BDLocation:kE	Z
    //   48: aload_0
    //   49: fconst_0
    //   50: putfield 119	com/baidu/location/BDLocation:k0	F
    //   53: aload_0
    //   54: iconst_0
    //   55: putfield 121	com/baidu/location/BDLocation:kN	Z
    //   58: aload_0
    //   59: fconst_0
    //   60: putfield 123	com/baidu/location/BDLocation:kS	F
    //   63: aload_0
    //   64: iconst_0
    //   65: putfield 125	com/baidu/location/BDLocation:kZ	Z
    //   68: aload_0
    //   69: iconst_m1
    //   70: putfield 127	com/baidu/location/BDLocation:kA	I
    //   73: aload_0
    //   74: ldc 128
    //   76: putfield 130	com/baidu/location/BDLocation:kI	F
    //   79: aload_0
    //   80: aconst_null
    //   81: putfield 132	com/baidu/location/BDLocation:k2	Ljava/lang/String;
    //   84: aload_0
    //   85: iconst_0
    //   86: putfield 134	com/baidu/location/BDLocation:kT	Z
    //   89: aload_0
    //   90: aconst_null
    //   91: putfield 136	com/baidu/location/BDLocation:kz	Ljava/lang/String;
    //   94: aload_0
    //   95: aconst_null
    //   96: putfield 138	com/baidu/location/BDLocation:kG	Ljava/lang/String;
    //   99: aload_0
    //   100: aconst_null
    //   101: putfield 140	com/baidu/location/BDLocation:kJ	Ljava/lang/String;
    //   104: aload_0
    //   105: aconst_null
    //   106: putfield 142	com/baidu/location/BDLocation:kY	Ljava/lang/String;
    //   109: aload_0
    //   110: iconst_0
    //   111: putfield 144	com/baidu/location/BDLocation:kL	Z
    //   114: aload_0
    //   115: new 146	com/baidu/location/Address$Builder
    //   118: dup
    //   119: invokespecial 147	com/baidu/location/Address$Builder:<init>	()V
    //   122: invokevirtual 151	com/baidu/location/Address$Builder:build	()Lcom/baidu/location/Address;
    //   125: putfield 153	com/baidu/location/BDLocation:kM	Lcom/baidu/location/Address;
    //   128: aload_0
    //   129: aconst_null
    //   130: putfield 155	com/baidu/location/BDLocation:kC	Ljava/lang/String;
    //   133: aload_0
    //   134: aconst_null
    //   135: putfield 157	com/baidu/location/BDLocation:kO	Ljava/lang/String;
    //   138: aload_0
    //   139: aconst_null
    //   140: putfield 159	com/baidu/location/BDLocation:kV	Ljava/lang/String;
    //   143: aload_0
    //   144: iconst_0
    //   145: putfield 161	com/baidu/location/BDLocation:kF	Z
    //   148: aload_0
    //   149: iconst_0
    //   150: putfield 163	com/baidu/location/BDLocation:kR	I
    //   153: aload_0
    //   154: iconst_1
    //   155: putfield 165	com/baidu/location/BDLocation:kQ	I
    //   158: aload_0
    //   159: aconst_null
    //   160: putfield 167	com/baidu/location/BDLocation:kP	Ljava/lang/String;
    //   163: aload_0
    //   164: ldc 169
    //   166: putfield 171	com/baidu/location/BDLocation:kH	Ljava/lang/String;
    //   169: aload_0
    //   170: aconst_null
    //   171: putfield 173	com/baidu/location/BDLocation:kU	Ljava/util/List;
    //   174: aload_1
    //   175: ifnull +12 -> 187
    //   178: aload_1
    //   179: ldc 169
    //   181: invokevirtual 293	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   184: ifeq +4 -> 188
    //   187: return
    //   188: new 295	org/json/JSONObject
    //   191: dup
    //   192: aload_1
    //   193: invokespecial 297	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   196: astore_1
    //   197: aload_1
    //   198: ldc_w 299
    //   201: invokevirtual 303	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   204: astore 4
    //   206: aload 4
    //   208: ldc_w 305
    //   211: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   214: invokestatic 315	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   217: istore_2
    //   218: aload_0
    //   219: iload_2
    //   220: invokevirtual 319	com/baidu/location/BDLocation:setLocType	(I)V
    //   223: aload_0
    //   224: aload 4
    //   226: ldc_w 321
    //   229: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   232: invokevirtual 324	com/baidu/location/BDLocation:setTime	(Ljava/lang/String;)V
    //   235: iload_2
    //   236: bipush 61
    //   238: if_icmpne +204 -> 442
    //   241: aload_1
    //   242: ldc_w 326
    //   245: invokevirtual 303	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   248: astore_1
    //   249: aload_1
    //   250: ldc_w 328
    //   253: invokevirtual 303	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   256: astore 4
    //   258: aload_0
    //   259: aload 4
    //   261: ldc_w 330
    //   264: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   267: invokestatic 336	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   270: invokevirtual 340	com/baidu/location/BDLocation:setLatitude	(D)V
    //   273: aload_0
    //   274: aload 4
    //   276: ldc_w 342
    //   279: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   282: invokestatic 336	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   285: invokevirtual 345	com/baidu/location/BDLocation:setLongitude	(D)V
    //   288: aload_0
    //   289: aload_1
    //   290: ldc_w 347
    //   293: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   296: invokestatic 353	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   299: invokevirtual 357	com/baidu/location/BDLocation:setRadius	(F)V
    //   302: aload_0
    //   303: aload_1
    //   304: ldc_w 359
    //   307: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   310: invokestatic 353	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   313: invokevirtual 362	com/baidu/location/BDLocation:setSpeed	(F)V
    //   316: aload_0
    //   317: aload_1
    //   318: ldc_w 364
    //   321: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   324: invokestatic 353	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   327: invokevirtual 367	com/baidu/location/BDLocation:setDirection	(F)V
    //   330: aload_0
    //   331: aload_1
    //   332: ldc_w 369
    //   335: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   338: invokestatic 315	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   341: invokevirtual 372	com/baidu/location/BDLocation:setSatelliteNumber	(I)V
    //   344: aload_1
    //   345: ldc_w 374
    //   348: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   351: istore_3
    //   352: iload_3
    //   353: ifeq +14 -> 367
    //   356: aload_0
    //   357: aload_1
    //   358: ldc_w 374
    //   361: invokevirtual 381	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   364: invokevirtual 384	com/baidu/location/BDLocation:setAltitude	(D)V
    //   367: aload_1
    //   368: ldc_w 386
    //   371: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   374: ifeq +48 -> 422
    //   377: aload_0
    //   378: aload_1
    //   379: ldc_w 386
    //   382: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   385: invokestatic 315	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   388: invokevirtual 389	com/baidu/location/BDLocation:setLocationWhere	(I)V
    //   391: aload_0
    //   392: getfield 165	com/baidu/location/BDLocation:kQ	I
    //   395: ifne +39 -> 434
    //   398: aload_0
    //   399: ldc_w 391
    //   402: invokevirtual 394	com/baidu/location/BDLocation:setCoorType	(Ljava/lang/String;)V
    //   405: return
    //   406: astore_1
    //   407: aload_1
    //   408: invokevirtual 397	java/lang/Exception:printStackTrace	()V
    //   411: aload_0
    //   412: iconst_0
    //   413: putfield 103	com/baidu/location/BDLocation:kW	I
    //   416: aload_0
    //   417: iconst_0
    //   418: putfield 134	com/baidu/location/BDLocation:kT	Z
    //   421: return
    //   422: aload_0
    //   423: iconst_1
    //   424: invokevirtual 389	com/baidu/location/BDLocation:setLocationWhere	(I)V
    //   427: goto -36 -> 391
    //   430: astore_1
    //   431: goto -40 -> 391
    //   434: aload_0
    //   435: ldc_w 399
    //   438: invokevirtual 394	com/baidu/location/BDLocation:setCoorType	(Ljava/lang/String;)V
    //   441: return
    //   442: iload_2
    //   443: sipush 161
    //   446: if_icmpne +915 -> 1361
    //   449: aload_1
    //   450: ldc_w 326
    //   453: invokevirtual 303	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   456: astore 11
    //   458: aload 11
    //   460: ldc_w 328
    //   463: invokevirtual 303	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   466: astore_1
    //   467: aload_0
    //   468: aload_1
    //   469: ldc_w 330
    //   472: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   475: invokestatic 336	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   478: invokevirtual 340	com/baidu/location/BDLocation:setLatitude	(D)V
    //   481: aload_0
    //   482: aload_1
    //   483: ldc_w 342
    //   486: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   489: invokestatic 336	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   492: invokevirtual 345	com/baidu/location/BDLocation:setLongitude	(D)V
    //   495: aload_0
    //   496: aload 11
    //   498: ldc_w 347
    //   501: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   504: invokestatic 353	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   507: invokevirtual 357	com/baidu/location/BDLocation:setRadius	(F)V
    //   510: aload 11
    //   512: ldc_w 401
    //   515: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   518: ifeq +226 -> 744
    //   521: aload 11
    //   523: ldc_w 401
    //   526: invokevirtual 303	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   529: astore_1
    //   530: aload_1
    //   531: ldc_w 403
    //   534: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   537: ifeq +26 -> 563
    //   540: aload_1
    //   541: ldc_w 403
    //   544: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   547: astore 4
    //   549: aload 4
    //   551: invokestatic 409	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   554: ifne +112 -> 666
    //   557: aload_0
    //   558: aload 4
    //   560: putfield 138	com/baidu/location/BDLocation:kG	Ljava/lang/String;
    //   563: aload_1
    //   564: ldc_w 411
    //   567: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   570: ifeq +111 -> 681
    //   573: aload_1
    //   574: ldc_w 411
    //   577: invokevirtual 303	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   580: ldc_w 413
    //   583: invokevirtual 417	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   586: astore 4
    //   588: new 227	java/util/ArrayList
    //   591: dup
    //   592: invokespecial 228	java/util/ArrayList:<init>	()V
    //   595: astore 5
    //   597: iconst_0
    //   598: istore_2
    //   599: iload_2
    //   600: aload 4
    //   602: invokevirtual 422	org/json/JSONArray:length	()I
    //   605: if_icmpge +70 -> 675
    //   608: aload 4
    //   610: iload_2
    //   611: invokevirtual 425	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   614: astore 6
    //   616: aload 6
    //   618: ldc_w 427
    //   621: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   624: astore 7
    //   626: aload 5
    //   628: new 230	com/baidu/location/Poi
    //   631: dup
    //   632: aload 6
    //   634: ldc_w 429
    //   637: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   640: aload 7
    //   642: aload 6
    //   644: ldc_w 431
    //   647: invokevirtual 381	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   650: invokespecial 283	com/baidu/location/Poi:<init>	(Ljava/lang/String;Ljava/lang/String;D)V
    //   653: invokeinterface 287 2 0
    //   658: pop
    //   659: iload_2
    //   660: iconst_1
    //   661: iadd
    //   662: istore_2
    //   663: goto -64 -> 599
    //   666: aload_0
    //   667: ldc 169
    //   669: putfield 138	com/baidu/location/BDLocation:kG	Ljava/lang/String;
    //   672: goto -109 -> 563
    //   675: aload_0
    //   676: aload 5
    //   678: putfield 173	com/baidu/location/BDLocation:kU	Ljava/util/List;
    //   681: aload_1
    //   682: ldc_w 433
    //   685: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   688: ifeq +26 -> 714
    //   691: aload_1
    //   692: ldc_w 433
    //   695: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   698: astore 4
    //   700: aload 4
    //   702: invokestatic 409	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   705: ifne +9 -> 714
    //   708: aload_0
    //   709: aload 4
    //   711: putfield 140	com/baidu/location/BDLocation:kJ	Ljava/lang/String;
    //   714: aload_1
    //   715: ldc_w 435
    //   718: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   721: ifeq +23 -> 744
    //   724: aload_1
    //   725: ldc_w 435
    //   728: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   731: astore_1
    //   732: aload_1
    //   733: invokestatic 409	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   736: ifne +8 -> 744
    //   739: aload_0
    //   740: aload_1
    //   741: putfield 142	com/baidu/location/BDLocation:kY	Ljava/lang/String;
    //   744: aload 11
    //   746: ldc_w 437
    //   749: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   752: ifeq +318 -> 1070
    //   755: aload 11
    //   757: ldc_w 437
    //   760: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   763: ldc_w 439
    //   766: invokevirtual 443	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   769: astore 12
    //   771: aload 12
    //   773: arraylength
    //   774: istore_2
    //   775: iload_2
    //   776: ifle +499 -> 1275
    //   779: aload 12
    //   781: iconst_0
    //   782: aaload
    //   783: astore_1
    //   784: goto +493 -> 1277
    //   787: aload_0
    //   788: new 146	com/baidu/location/Address$Builder
    //   791: dup
    //   792: invokespecial 147	com/baidu/location/Address$Builder:<init>	()V
    //   795: aload 9
    //   797: invokevirtual 198	com/baidu/location/Address$Builder:country	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   800: aload 10
    //   802: invokevirtual 201	com/baidu/location/Address$Builder:countryCode	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   805: aload_1
    //   806: invokevirtual 204	com/baidu/location/Address$Builder:province	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   809: aload 4
    //   811: invokevirtual 207	com/baidu/location/Address$Builder:city	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   814: aload 8
    //   816: invokevirtual 210	com/baidu/location/Address$Builder:cityCode	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   819: aload 5
    //   821: invokevirtual 213	com/baidu/location/Address$Builder:district	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   824: aload 6
    //   826: invokevirtual 216	com/baidu/location/Address$Builder:street	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   829: aload 7
    //   831: invokevirtual 219	com/baidu/location/Address$Builder:streetNumber	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   834: invokevirtual 151	com/baidu/location/Address$Builder:build	()Lcom/baidu/location/Address;
    //   837: putfield 153	com/baidu/location/BDLocation:kM	Lcom/baidu/location/Address;
    //   840: aload_0
    //   841: iconst_1
    //   842: putfield 134	com/baidu/location/BDLocation:kT	Z
    //   845: aload 11
    //   847: ldc_w 445
    //   850: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   853: ifeq +30 -> 883
    //   856: aload_0
    //   857: aload 11
    //   859: ldc_w 445
    //   862: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   865: putfield 155	com/baidu/location/BDLocation:kC	Ljava/lang/String;
    //   868: aload_0
    //   869: getfield 155	com/baidu/location/BDLocation:kC	Ljava/lang/String;
    //   872: invokestatic 409	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   875: ifeq +8 -> 883
    //   878: aload_0
    //   879: aconst_null
    //   880: putfield 155	com/baidu/location/BDLocation:kC	Ljava/lang/String;
    //   883: aload 11
    //   885: ldc_w 447
    //   888: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   891: ifeq +30 -> 921
    //   894: aload_0
    //   895: aload 11
    //   897: ldc_w 447
    //   900: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   903: putfield 167	com/baidu/location/BDLocation:kP	Ljava/lang/String;
    //   906: aload_0
    //   907: getfield 167	com/baidu/location/BDLocation:kP	Ljava/lang/String;
    //   910: invokestatic 409	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   913: ifeq +8 -> 921
    //   916: aload_0
    //   917: aconst_null
    //   918: putfield 167	com/baidu/location/BDLocation:kP	Ljava/lang/String;
    //   921: aload 11
    //   923: ldc_w 449
    //   926: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   929: ifeq +30 -> 959
    //   932: aload_0
    //   933: aload 11
    //   935: ldc_w 449
    //   938: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   941: putfield 157	com/baidu/location/BDLocation:kO	Ljava/lang/String;
    //   944: aload_0
    //   945: getfield 157	com/baidu/location/BDLocation:kO	Ljava/lang/String;
    //   948: invokestatic 409	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   951: ifeq +8 -> 959
    //   954: aload_0
    //   955: aconst_null
    //   956: putfield 157	com/baidu/location/BDLocation:kO	Ljava/lang/String;
    //   959: aload 11
    //   961: ldc_w 451
    //   964: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   967: ifeq +30 -> 997
    //   970: aload_0
    //   971: aload 11
    //   973: ldc_w 451
    //   976: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   979: putfield 159	com/baidu/location/BDLocation:kV	Ljava/lang/String;
    //   982: aload_0
    //   983: getfield 159	com/baidu/location/BDLocation:kV	Ljava/lang/String;
    //   986: invokestatic 409	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   989: ifeq +8 -> 997
    //   992: aload_0
    //   993: aconst_null
    //   994: putfield 159	com/baidu/location/BDLocation:kV	Ljava/lang/String;
    //   997: aload 11
    //   999: ldc_w 453
    //   1002: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1005: ifeq +24 -> 1029
    //   1008: aload 11
    //   1010: ldc_w 453
    //   1013: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1016: astore_1
    //   1017: aload_1
    //   1018: invokestatic 409	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1021: ifeq +62 -> 1083
    //   1024: aload_0
    //   1025: iconst_0
    //   1026: putfield 163	com/baidu/location/BDLocation:kR	I
    //   1029: aload 11
    //   1031: ldc_w 386
    //   1034: invokevirtual 378	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1037: ifeq +78 -> 1115
    //   1040: aload_0
    //   1041: aload 11
    //   1043: ldc_w 386
    //   1046: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1049: invokestatic 315	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1052: invokevirtual 389	com/baidu/location/BDLocation:setLocationWhere	(I)V
    //   1055: aload_0
    //   1056: getfield 165	com/baidu/location/BDLocation:kQ	I
    //   1059: ifne +68 -> 1127
    //   1062: aload_0
    //   1063: ldc_w 391
    //   1066: invokevirtual 394	com/baidu/location/BDLocation:setCoorType	(Ljava/lang/String;)V
    //   1069: return
    //   1070: aload_0
    //   1071: iconst_0
    //   1072: putfield 134	com/baidu/location/BDLocation:kT	Z
    //   1075: aload_0
    //   1076: aconst_null
    //   1077: invokevirtual 456	com/baidu/location/BDLocation:setAddrStr	(Ljava/lang/String;)V
    //   1080: goto -235 -> 845
    //   1083: aload_1
    //   1084: ldc_w 458
    //   1087: invokevirtual 293	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1090: ifeq +11 -> 1101
    //   1093: aload_0
    //   1094: iconst_0
    //   1095: putfield 163	com/baidu/location/BDLocation:kR	I
    //   1098: goto -69 -> 1029
    //   1101: aload_0
    //   1102: aload_1
    //   1103: invokestatic 462	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   1106: invokevirtual 465	java/lang/Integer:intValue	()I
    //   1109: putfield 163	com/baidu/location/BDLocation:kR	I
    //   1112: goto -83 -> 1029
    //   1115: aload_0
    //   1116: iconst_1
    //   1117: invokevirtual 389	com/baidu/location/BDLocation:setLocationWhere	(I)V
    //   1120: goto -65 -> 1055
    //   1123: astore_1
    //   1124: goto -69 -> 1055
    //   1127: aload_0
    //   1128: ldc_w 399
    //   1131: invokevirtual 394	com/baidu/location/BDLocation:setCoorType	(Ljava/lang/String;)V
    //   1134: return
    //   1135: aload_1
    //   1136: ldc_w 326
    //   1139: invokevirtual 303	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   1142: astore_1
    //   1143: aload_1
    //   1144: ldc_w 328
    //   1147: invokevirtual 303	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   1150: astore 4
    //   1152: aload_0
    //   1153: aload 4
    //   1155: ldc_w 330
    //   1158: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1161: invokestatic 336	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   1164: invokevirtual 340	com/baidu/location/BDLocation:setLatitude	(D)V
    //   1167: aload_0
    //   1168: aload 4
    //   1170: ldc_w 342
    //   1173: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1176: invokestatic 336	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   1179: invokevirtual 345	com/baidu/location/BDLocation:setLongitude	(D)V
    //   1182: aload_0
    //   1183: aload_1
    //   1184: ldc_w 347
    //   1187: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1190: invokestatic 353	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   1193: invokevirtual 357	com/baidu/location/BDLocation:setRadius	(F)V
    //   1196: aload_0
    //   1197: aload_1
    //   1198: ldc_w 467
    //   1201: invokevirtual 309	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1204: invokestatic 472	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   1207: invokestatic 475	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1210: invokespecial 479	com/baidu/location/BDLocation:if	(Ljava/lang/Boolean;)V
    //   1213: aload_0
    //   1214: ldc_w 399
    //   1217: invokevirtual 394	com/baidu/location/BDLocation:setCoorType	(Ljava/lang/String;)V
    //   1220: return
    //   1221: iload_2
    //   1222: sipush 167
    //   1225: if_icmpne -1038 -> 187
    //   1228: aload_0
    //   1229: iconst_2
    //   1230: invokevirtual 389	com/baidu/location/BDLocation:setLocationWhere	(I)V
    //   1233: return
    //   1234: astore 4
    //   1236: goto -869 -> 367
    //   1239: aconst_null
    //   1240: astore 9
    //   1242: goto +103 -> 1345
    //   1245: aconst_null
    //   1246: astore 8
    //   1248: goto +84 -> 1332
    //   1251: aconst_null
    //   1252: astore 7
    //   1254: goto +67 -> 1321
    //   1257: aconst_null
    //   1258: astore 6
    //   1260: goto +50 -> 1310
    //   1263: aconst_null
    //   1264: astore 5
    //   1266: goto +33 -> 1299
    //   1269: aconst_null
    //   1270: astore 4
    //   1272: goto +16 -> 1288
    //   1275: aconst_null
    //   1276: astore_1
    //   1277: iload_2
    //   1278: iconst_1
    //   1279: if_icmple -10 -> 1269
    //   1282: aload 12
    //   1284: iconst_1
    //   1285: aaload
    //   1286: astore 4
    //   1288: iload_2
    //   1289: iconst_2
    //   1290: if_icmple -27 -> 1263
    //   1293: aload 12
    //   1295: iconst_2
    //   1296: aaload
    //   1297: astore 5
    //   1299: iload_2
    //   1300: iconst_3
    //   1301: if_icmple -44 -> 1257
    //   1304: aload 12
    //   1306: iconst_3
    //   1307: aaload
    //   1308: astore 6
    //   1310: iload_2
    //   1311: iconst_4
    //   1312: if_icmple -61 -> 1251
    //   1315: aload 12
    //   1317: iconst_4
    //   1318: aaload
    //   1319: astore 7
    //   1321: iload_2
    //   1322: iconst_5
    //   1323: if_icmple -78 -> 1245
    //   1326: aload 12
    //   1328: iconst_5
    //   1329: aaload
    //   1330: astore 8
    //   1332: iload_2
    //   1333: bipush 6
    //   1335: if_icmple -96 -> 1239
    //   1338: aload 12
    //   1340: bipush 6
    //   1342: aaload
    //   1343: astore 9
    //   1345: iload_2
    //   1346: bipush 7
    //   1348: if_icmple -561 -> 787
    //   1351: aload 12
    //   1353: bipush 7
    //   1355: aaload
    //   1356: astore 10
    //   1358: goto -571 -> 787
    //   1361: iload_2
    //   1362: bipush 66
    //   1364: if_icmpeq -229 -> 1135
    //   1367: iload_2
    //   1368: bipush 68
    //   1370: if_icmpne -149 -> 1221
    //   1373: goto -238 -> 1135
    //
    // Exception table:
    //   from	to	target	type
    //   188	235	406	java/lang/Exception
    //   241	352	406	java/lang/Exception
    //   391	405	406	java/lang/Exception
    //   434	441	406	java/lang/Exception
    //   449	563	406	java/lang/Exception
    //   563	597	406	java/lang/Exception
    //   599	659	406	java/lang/Exception
    //   666	672	406	java/lang/Exception
    //   675	681	406	java/lang/Exception
    //   681	714	406	java/lang/Exception
    //   714	744	406	java/lang/Exception
    //   744	775	406	java/lang/Exception
    //   787	845	406	java/lang/Exception
    //   845	883	406	java/lang/Exception
    //   883	921	406	java/lang/Exception
    //   921	959	406	java/lang/Exception
    //   959	997	406	java/lang/Exception
    //   997	1029	406	java/lang/Exception
    //   1055	1069	406	java/lang/Exception
    //   1070	1080	406	java/lang/Exception
    //   1083	1098	406	java/lang/Exception
    //   1101	1112	406	java/lang/Exception
    //   1127	1134	406	java/lang/Exception
    //   1135	1220	406	java/lang/Exception
    //   1228	1233	406	java/lang/Exception
    //   367	391	430	java/lang/Exception
    //   422	427	430	java/lang/Exception
    //   1029	1055	1123	java/lang/Exception
    //   1115	1120	1123	java/lang/Exception
    //   356	367	1234	java/lang/Exception
  }

  private String dE()
  {
    return this.kJ;
  }

  private String dF()
  {
    return this.kH;
  }

  private String dG()
  {
    return this.kY;
  }

  private static String dH()
  {
    return Build.MODEL;
  }

  private void jdMethod_if(Boolean paramBoolean)
  {
    this.kL = paramBoolean.booleanValue();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAdUrl(String paramString)
  {
    double d1 = this.kB;
    double d2 = this.kX;
    String str1 = dF();
    String str2 = dH();
    paramString = Jni.E("ak=" + paramString + "&" + "lat=" + String.valueOf(d1) + "&" + "lng=" + String.valueOf(d2) + "&" + "cu=" + str1 + "&" + "mb=" + str2);
    return "http://lba.baidu.com/" + "?a=" + paramString;
  }

  public String getAddrStr()
  {
    return this.kM.address;
  }

  public Address getAddress()
  {
    return this.kM;
  }

  public double getAltitude()
  {
    return this.kK;
  }

  public String getBuildingID()
  {
    return this.kO;
  }

  public String getBuildingName()
  {
    return this.kV;
  }

  public String getCity()
  {
    return this.kM.city;
  }

  public String getCityCode()
  {
    return this.kM.cityCode;
  }

  public String getCoorType()
  {
    return this.k2;
  }

  public String getCountry()
  {
    return this.kM.country;
  }

  public String getCountryCode()
  {
    return this.kM.countryCode;
  }

  public float getDerect()
  {
    return this.kI;
  }

  public float getDirection()
  {
    return this.kI;
  }

  public String getDistrict()
  {
    return this.kM.district;
  }

  public String getFloor()
  {
    return this.kC;
  }

  public double getLatitude()
  {
    return this.kB;
  }

  public int getLocType()
  {
    return this.kW;
  }

  public String getLocationDescribe()
  {
    return this.kG;
  }

  public int getLocationWhere()
  {
    return this.kQ;
  }

  public double getLongitude()
  {
    return this.kX;
  }

  public String getNetworkLocationType()
  {
    return this.kP;
  }

  public int getOperators()
  {
    return this.k3;
  }

  public List getPoiList()
  {
    return this.kU;
  }

  public String getProvince()
  {
    return this.kM.province;
  }

  public float getRadius()
  {
    return this.kS;
  }

  public int getSatelliteNumber()
  {
    this.kZ = true;
    return this.kA;
  }

  public String getSemaAptag()
  {
    return this.kG;
  }

  public float getSpeed()
  {
    return this.k0;
  }

  public String getStreet()
  {
    return this.kM.street;
  }

  public String getStreetNumber()
  {
    return this.kM.streetNumber;
  }

  public String getTime()
  {
    return this.k1;
  }

  public boolean hasAddr()
  {
    return this.kT;
  }

  public boolean hasAltitude()
  {
    return this.kD;
  }

  public boolean hasRadius()
  {
    return this.kN;
  }

  public boolean hasSateNumber()
  {
    return this.kZ;
  }

  public boolean hasSpeed()
  {
    return this.kE;
  }

  public void internalSet(int paramInt, String paramString)
  {
    if (paramString == null);
    while (paramInt != 0)
      return;
    this.kH = paramString;
  }

  public boolean isCellChangeFlag()
  {
    return this.kL;
  }

  public boolean isIndoorLocMode()
  {
    return this.kF;
  }

  public int isParkAvailable()
  {
    return this.kR;
  }

  public void setAddr(Address paramAddress)
  {
    if (paramAddress != null)
    {
      this.kM = paramAddress;
      this.kT = true;
    }
  }

  public void setAddrStr(String paramString)
  {
    this.kz = paramString;
    if (paramString == null)
    {
      this.kT = false;
      return;
    }
    this.kT = true;
  }

  public void setAltitude(double paramDouble)
  {
    this.kK = paramDouble;
    this.kD = true;
  }

  public void setBuildingID(String paramString)
  {
    this.kO = paramString;
  }

  public void setBuildingName(String paramString)
  {
    this.kV = paramString;
  }

  public void setCoorType(String paramString)
  {
    this.k2 = paramString;
  }

  public void setDirection(float paramFloat)
  {
    this.kI = paramFloat;
  }

  public void setFloor(String paramString)
  {
    this.kC = paramString;
  }

  public void setIndoorLocMode(boolean paramBoolean)
  {
    this.kF = paramBoolean;
  }

  public void setLatitude(double paramDouble)
  {
    this.kB = paramDouble;
  }

  public void setLocType(int paramInt)
  {
    this.kW = paramInt;
  }

  public void setLocationDescribe(String paramString)
  {
    this.kG = paramString;
  }

  public void setLocationWhere(int paramInt)
  {
    this.kQ = paramInt;
  }

  public void setLongitude(double paramDouble)
  {
    this.kX = paramDouble;
  }

  public void setNetworkLocationType(String paramString)
  {
    this.kP = paramString;
  }

  public void setOperators(int paramInt)
  {
    this.k3 = paramInt;
  }

  public void setParkAvailable(int paramInt)
  {
    this.kR = paramInt;
  }

  public void setPoiList(List paramList)
  {
    this.kU = paramList;
  }

  public void setRadius(float paramFloat)
  {
    this.kS = paramFloat;
    this.kN = true;
  }

  public void setSatelliteNumber(int paramInt)
  {
    this.kA = paramInt;
  }

  public void setSpeed(float paramFloat)
  {
    this.k0 = paramFloat;
    this.kE = true;
  }

  public void setTime(String paramString)
  {
    this.k1 = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.kW);
    paramParcel.writeString(this.k1);
    paramParcel.writeDouble(this.kB);
    paramParcel.writeDouble(this.kX);
    paramParcel.writeDouble(this.kK);
    paramParcel.writeFloat(this.k0);
    paramParcel.writeFloat(this.kS);
    paramParcel.writeInt(this.kA);
    paramParcel.writeFloat(this.kI);
    paramParcel.writeString(this.kC);
    paramParcel.writeInt(this.kR);
    paramParcel.writeString(this.kO);
    paramParcel.writeString(this.kV);
    paramParcel.writeString(this.kP);
    paramParcel.writeString(this.kM.province);
    paramParcel.writeString(this.kM.city);
    paramParcel.writeString(this.kM.district);
    paramParcel.writeString(this.kM.street);
    paramParcel.writeString(this.kM.streetNumber);
    paramParcel.writeString(this.kM.cityCode);
    paramParcel.writeString(this.kM.address);
    paramParcel.writeString(this.kM.country);
    paramParcel.writeString(this.kM.countryCode);
    paramParcel.writeInt(this.k3);
    paramParcel.writeString(this.kH);
    paramParcel.writeString(this.kG);
    paramParcel.writeString(this.kJ);
    paramParcel.writeString(this.kY);
    paramParcel.writeInt(this.kQ);
    paramParcel.writeBooleanArray(new boolean[] { this.kD, this.kE, this.kN, this.kZ, this.kT, this.kL, this.kF });
    paramParcel.writeList(this.kU);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.BDLocation
 * JD-Core Version:    0.6.2
 */