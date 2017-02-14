package com.ta.utdid2.core.persistent;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import com.ta.utdid2.android.utils.StringUtils;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PersistentConfiguration
{
  private static final String KEY_TIMESTAMP = "t";
  private static final String KEY_TIMESTAMP2 = "t2";
  private boolean mCanRead;
  private boolean mCanWrite;
  private String mConfigName;
  private Context mContext;
  private SharedPreferences.Editor mEditor;
  private String mFolderName;
  private boolean mIsLessMode;
  private boolean mIsSafety;
  private MySharedPreferences.MyEditor mMyEditor;
  private MySharedPreferences mMySP;
  private SharedPreferences mSp;
  private TransactionXMLFile mTxf;

  // ERROR //
  public PersistentConfiguration(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 37	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: ldc 39
    //   7: putfield 41	com/ta/utdid2/core/persistent/PersistentConfiguration:mConfigName	Ljava/lang/String;
    //   10: aload_0
    //   11: ldc 39
    //   13: putfield 43	com/ta/utdid2/core/persistent/PersistentConfiguration:mFolderName	Ljava/lang/String;
    //   16: aload_0
    //   17: iconst_0
    //   18: putfield 45	com/ta/utdid2/core/persistent/PersistentConfiguration:mIsSafety	Z
    //   21: aload_0
    //   22: iconst_0
    //   23: putfield 47	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanRead	Z
    //   26: aload_0
    //   27: iconst_0
    //   28: putfield 49	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanWrite	Z
    //   31: aload_0
    //   32: aconst_null
    //   33: putfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   36: aload_0
    //   37: aconst_null
    //   38: putfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   41: aload_0
    //   42: aconst_null
    //   43: putfield 55	com/ta/utdid2/core/persistent/PersistentConfiguration:mEditor	Landroid/content/SharedPreferences$Editor;
    //   46: aload_0
    //   47: aconst_null
    //   48: putfield 57	com/ta/utdid2/core/persistent/PersistentConfiguration:mMyEditor	Lcom/ta/utdid2/core/persistent/MySharedPreferences$MyEditor;
    //   51: aload_0
    //   52: aconst_null
    //   53: putfield 59	com/ta/utdid2/core/persistent/PersistentConfiguration:mContext	Landroid/content/Context;
    //   56: aload_0
    //   57: aconst_null
    //   58: putfield 61	com/ta/utdid2/core/persistent/PersistentConfiguration:mTxf	Lcom/ta/utdid2/core/persistent/TransactionXMLFile;
    //   61: aload_0
    //   62: iconst_0
    //   63: putfield 63	com/ta/utdid2/core/persistent/PersistentConfiguration:mIsLessMode	Z
    //   66: aload_0
    //   67: iload 4
    //   69: putfield 45	com/ta/utdid2/core/persistent/PersistentConfiguration:mIsSafety	Z
    //   72: aload_0
    //   73: iload 5
    //   75: putfield 63	com/ta/utdid2/core/persistent/PersistentConfiguration:mIsLessMode	Z
    //   78: aload_0
    //   79: aload_3
    //   80: putfield 41	com/ta/utdid2/core/persistent/PersistentConfiguration:mConfigName	Ljava/lang/String;
    //   83: aload_0
    //   84: aload_2
    //   85: putfield 43	com/ta/utdid2/core/persistent/PersistentConfiguration:mFolderName	Ljava/lang/String;
    //   88: aload_0
    //   89: aload_1
    //   90: putfield 59	com/ta/utdid2/core/persistent/PersistentConfiguration:mContext	Landroid/content/Context;
    //   93: lconst_0
    //   94: lstore 6
    //   96: lconst_0
    //   97: lstore 12
    //   99: aload_1
    //   100: ifnull +27 -> 127
    //   103: aload_0
    //   104: aload_1
    //   105: aload_3
    //   106: iconst_0
    //   107: invokevirtual 69	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   110: putfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   113: aload_0
    //   114: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   117: ldc 8
    //   119: lconst_0
    //   120: invokeinterface 75 4 0
    //   125: lstore 6
    //   127: invokestatic 81	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   130: astore 14
    //   132: aload 14
    //   134: invokestatic 87	com/ta/utdid2/android/utils/StringUtils:isEmpty	(Ljava/lang/String;)Z
    //   137: ifeq +301 -> 438
    //   140: aload_0
    //   141: iconst_0
    //   142: putfield 49	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanWrite	Z
    //   145: aload_0
    //   146: iconst_0
    //   147: putfield 47	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanRead	Z
    //   150: aload_0
    //   151: getfield 47	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanRead	Z
    //   154: ifne +18 -> 172
    //   157: lload 6
    //   159: lstore 8
    //   161: lload 12
    //   163: lstore 10
    //   165: aload_0
    //   166: getfield 49	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanWrite	Z
    //   169: ifeq +143 -> 312
    //   172: lload 6
    //   174: lstore 8
    //   176: lload 12
    //   178: lstore 10
    //   180: aload_1
    //   181: ifnull +131 -> 312
    //   184: lload 6
    //   186: lstore 8
    //   188: lload 12
    //   190: lstore 10
    //   192: aload_2
    //   193: invokestatic 87	com/ta/utdid2/android/utils/StringUtils:isEmpty	(Ljava/lang/String;)Z
    //   196: ifne +116 -> 312
    //   199: aload_0
    //   200: aload_0
    //   201: aload_2
    //   202: invokespecial 91	com/ta/utdid2/core/persistent/PersistentConfiguration:getTransactionXMLFile	(Ljava/lang/String;)Lcom/ta/utdid2/core/persistent/TransactionXMLFile;
    //   205: putfield 61	com/ta/utdid2/core/persistent/PersistentConfiguration:mTxf	Lcom/ta/utdid2/core/persistent/TransactionXMLFile;
    //   208: lload 6
    //   210: lstore 8
    //   212: lload 12
    //   214: lstore 10
    //   216: aload_0
    //   217: getfield 61	com/ta/utdid2/core/persistent/PersistentConfiguration:mTxf	Lcom/ta/utdid2/core/persistent/TransactionXMLFile;
    //   220: ifnull +92 -> 312
    //   223: lload 12
    //   225: lstore 10
    //   227: aload_0
    //   228: aload_0
    //   229: getfield 61	com/ta/utdid2/core/persistent/PersistentConfiguration:mTxf	Lcom/ta/utdid2/core/persistent/TransactionXMLFile;
    //   232: aload_3
    //   233: iconst_0
    //   234: invokevirtual 97	com/ta/utdid2/core/persistent/TransactionXMLFile:getMySharedPreferences	(Ljava/lang/String;I)Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   237: putfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   240: lload 12
    //   242: lstore 10
    //   244: aload_0
    //   245: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   248: ldc 8
    //   250: lconst_0
    //   251: invokeinterface 100 4 0
    //   256: lstore 8
    //   258: iload 5
    //   260: ifne +322 -> 582
    //   263: lload 6
    //   265: lload 8
    //   267: lcmp
    //   268: ifle +229 -> 497
    //   271: lload 8
    //   273: lstore 10
    //   275: aload_0
    //   276: aload_0
    //   277: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   280: aload_0
    //   281: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   284: invokespecial 104	com/ta/utdid2/core/persistent/PersistentConfiguration:copySPToMySP	(Landroid/content/SharedPreferences;Lcom/ta/utdid2/core/persistent/MySharedPreferences;)V
    //   287: lload 8
    //   289: lstore 10
    //   291: aload_0
    //   292: aload_0
    //   293: getfield 61	com/ta/utdid2/core/persistent/PersistentConfiguration:mTxf	Lcom/ta/utdid2/core/persistent/TransactionXMLFile;
    //   296: aload_3
    //   297: iconst_0
    //   298: invokevirtual 97	com/ta/utdid2/core/persistent/TransactionXMLFile:getMySharedPreferences	(Ljava/lang/String;I)Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   301: putfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   304: lload 8
    //   306: lstore 10
    //   308: lload 6
    //   310: lstore 8
    //   312: lload 8
    //   314: lload 10
    //   316: lcmp
    //   317: ifne +17 -> 334
    //   320: lload 8
    //   322: lconst_0
    //   323: lcmp
    //   324: ifne +113 -> 437
    //   327: lload 10
    //   329: lconst_0
    //   330: lcmp
    //   331: ifne +106 -> 437
    //   334: invokestatic 110	java/lang/System:currentTimeMillis	()J
    //   337: lstore 6
    //   339: aload_0
    //   340: getfield 63	com/ta/utdid2/core/persistent/PersistentConfiguration:mIsLessMode	Z
    //   343: ifeq +24 -> 367
    //   346: aload_0
    //   347: getfield 63	com/ta/utdid2/core/persistent/PersistentConfiguration:mIsLessMode	Z
    //   350: ifeq +87 -> 437
    //   353: lload 8
    //   355: lconst_0
    //   356: lcmp
    //   357: ifne +80 -> 437
    //   360: lload 10
    //   362: lconst_0
    //   363: lcmp
    //   364: ifne +73 -> 437
    //   367: aload_0
    //   368: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   371: ifnull +31 -> 402
    //   374: aload_0
    //   375: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   378: invokeinterface 114 1 0
    //   383: astore_1
    //   384: aload_1
    //   385: ldc 11
    //   387: lload 6
    //   389: invokeinterface 120 4 0
    //   394: pop
    //   395: aload_1
    //   396: invokeinterface 124 1 0
    //   401: pop
    //   402: aload_0
    //   403: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   406: ifnull +31 -> 437
    //   409: aload_0
    //   410: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   413: invokeinterface 127 1 0
    //   418: astore_1
    //   419: aload_1
    //   420: ldc 11
    //   422: lload 6
    //   424: invokeinterface 132 4 0
    //   429: pop
    //   430: aload_1
    //   431: invokeinterface 133 1 0
    //   436: pop
    //   437: return
    //   438: aload 14
    //   440: ldc 135
    //   442: invokevirtual 141	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   445: ifeq +16 -> 461
    //   448: aload_0
    //   449: iconst_1
    //   450: putfield 49	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanWrite	Z
    //   453: aload_0
    //   454: iconst_1
    //   455: putfield 47	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanRead	Z
    //   458: goto -308 -> 150
    //   461: aload 14
    //   463: ldc 143
    //   465: invokevirtual 141	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   468: ifeq +16 -> 484
    //   471: aload_0
    //   472: iconst_1
    //   473: putfield 47	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanRead	Z
    //   476: aload_0
    //   477: iconst_0
    //   478: putfield 49	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanWrite	Z
    //   481: goto -331 -> 150
    //   484: aload_0
    //   485: iconst_0
    //   486: putfield 49	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanWrite	Z
    //   489: aload_0
    //   490: iconst_0
    //   491: putfield 47	com/ta/utdid2/core/persistent/PersistentConfiguration:mCanRead	Z
    //   494: goto -344 -> 150
    //   497: lload 6
    //   499: lload 8
    //   501: lcmp
    //   502: ifge +36 -> 538
    //   505: lload 8
    //   507: lstore 10
    //   509: aload_0
    //   510: aload_0
    //   511: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   514: aload_0
    //   515: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   518: invokespecial 147	com/ta/utdid2/core/persistent/PersistentConfiguration:copyMySPToSP	(Lcom/ta/utdid2/core/persistent/MySharedPreferences;Landroid/content/SharedPreferences;)V
    //   521: lload 8
    //   523: lstore 10
    //   525: aload_0
    //   526: aload_1
    //   527: aload_3
    //   528: iconst_0
    //   529: invokevirtual 69	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   532: putfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   535: goto -231 -> 304
    //   538: lload 6
    //   540: lload 8
    //   542: lcmp
    //   543: ifne +305 -> 848
    //   546: lload 8
    //   548: lstore 10
    //   550: aload_0
    //   551: aload_0
    //   552: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   555: aload_0
    //   556: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   559: invokespecial 104	com/ta/utdid2/core/persistent/PersistentConfiguration:copySPToMySP	(Landroid/content/SharedPreferences;Lcom/ta/utdid2/core/persistent/MySharedPreferences;)V
    //   562: lload 8
    //   564: lstore 10
    //   566: aload_0
    //   567: aload_0
    //   568: getfield 61	com/ta/utdid2/core/persistent/PersistentConfiguration:mTxf	Lcom/ta/utdid2/core/persistent/TransactionXMLFile;
    //   571: aload_3
    //   572: iconst_0
    //   573: invokevirtual 97	com/ta/utdid2/core/persistent/TransactionXMLFile:getMySharedPreferences	(Ljava/lang/String;I)Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   576: putfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   579: goto -275 -> 304
    //   582: lload 8
    //   584: lstore 10
    //   586: aload_0
    //   587: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   590: ldc 11
    //   592: lconst_0
    //   593: invokeinterface 75 4 0
    //   598: lstore 12
    //   600: lload 12
    //   602: lstore 6
    //   604: aload_0
    //   605: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   608: ldc 11
    //   610: lconst_0
    //   611: invokeinterface 100 4 0
    //   616: lstore 10
    //   618: lload 10
    //   620: lstore 8
    //   622: lload 6
    //   624: lload 8
    //   626: lcmp
    //   627: ifge +38 -> 665
    //   630: lload 6
    //   632: lconst_0
    //   633: lcmp
    //   634: ifle +31 -> 665
    //   637: aload_0
    //   638: aload_0
    //   639: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   642: aload_0
    //   643: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   646: invokespecial 104	com/ta/utdid2/core/persistent/PersistentConfiguration:copySPToMySP	(Landroid/content/SharedPreferences;Lcom/ta/utdid2/core/persistent/MySharedPreferences;)V
    //   649: aload_0
    //   650: aload_0
    //   651: getfield 61	com/ta/utdid2/core/persistent/PersistentConfiguration:mTxf	Lcom/ta/utdid2/core/persistent/TransactionXMLFile;
    //   654: aload_3
    //   655: iconst_0
    //   656: invokevirtual 97	com/ta/utdid2/core/persistent/TransactionXMLFile:getMySharedPreferences	(Ljava/lang/String;I)Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   659: putfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   662: goto -358 -> 304
    //   665: lload 6
    //   667: lload 8
    //   669: lcmp
    //   670: ifle +35 -> 705
    //   673: lload 8
    //   675: lconst_0
    //   676: lcmp
    //   677: ifle +28 -> 705
    //   680: aload_0
    //   681: aload_0
    //   682: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   685: aload_0
    //   686: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   689: invokespecial 147	com/ta/utdid2/core/persistent/PersistentConfiguration:copyMySPToSP	(Lcom/ta/utdid2/core/persistent/MySharedPreferences;Landroid/content/SharedPreferences;)V
    //   692: aload_0
    //   693: aload_1
    //   694: aload_3
    //   695: iconst_0
    //   696: invokevirtual 69	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   699: putfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   702: goto -398 -> 304
    //   705: lload 6
    //   707: lconst_0
    //   708: lcmp
    //   709: ifne +35 -> 744
    //   712: lload 8
    //   714: lconst_0
    //   715: lcmp
    //   716: ifle +28 -> 744
    //   719: aload_0
    //   720: aload_0
    //   721: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   724: aload_0
    //   725: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   728: invokespecial 147	com/ta/utdid2/core/persistent/PersistentConfiguration:copyMySPToSP	(Lcom/ta/utdid2/core/persistent/MySharedPreferences;Landroid/content/SharedPreferences;)V
    //   731: aload_0
    //   732: aload_1
    //   733: aload_3
    //   734: iconst_0
    //   735: invokevirtual 69	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   738: putfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   741: goto -437 -> 304
    //   744: lload 8
    //   746: lconst_0
    //   747: lcmp
    //   748: ifne +38 -> 786
    //   751: lload 6
    //   753: lconst_0
    //   754: lcmp
    //   755: ifle +31 -> 786
    //   758: aload_0
    //   759: aload_0
    //   760: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   763: aload_0
    //   764: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   767: invokespecial 104	com/ta/utdid2/core/persistent/PersistentConfiguration:copySPToMySP	(Landroid/content/SharedPreferences;Lcom/ta/utdid2/core/persistent/MySharedPreferences;)V
    //   770: aload_0
    //   771: aload_0
    //   772: getfield 61	com/ta/utdid2/core/persistent/PersistentConfiguration:mTxf	Lcom/ta/utdid2/core/persistent/TransactionXMLFile;
    //   775: aload_3
    //   776: iconst_0
    //   777: invokevirtual 97	com/ta/utdid2/core/persistent/TransactionXMLFile:getMySharedPreferences	(Ljava/lang/String;I)Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   780: putfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   783: goto -479 -> 304
    //   786: lload 6
    //   788: lload 8
    //   790: lcmp
    //   791: ifne +28 -> 819
    //   794: aload_0
    //   795: aload_0
    //   796: getfield 51	com/ta/utdid2/core/persistent/PersistentConfiguration:mSp	Landroid/content/SharedPreferences;
    //   799: aload_0
    //   800: getfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   803: invokespecial 104	com/ta/utdid2/core/persistent/PersistentConfiguration:copySPToMySP	(Landroid/content/SharedPreferences;Lcom/ta/utdid2/core/persistent/MySharedPreferences;)V
    //   806: aload_0
    //   807: aload_0
    //   808: getfield 61	com/ta/utdid2/core/persistent/PersistentConfiguration:mTxf	Lcom/ta/utdid2/core/persistent/TransactionXMLFile;
    //   811: aload_3
    //   812: iconst_0
    //   813: invokevirtual 97	com/ta/utdid2/core/persistent/TransactionXMLFile:getMySharedPreferences	(Ljava/lang/String;I)Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   816: putfield 53	com/ta/utdid2/core/persistent/PersistentConfiguration:mMySP	Lcom/ta/utdid2/core/persistent/MySharedPreferences;
    //   819: goto -515 -> 304
    //   822: astore_1
    //   823: lload 10
    //   825: lstore 8
    //   827: lload 8
    //   829: lstore 10
    //   831: lload 6
    //   833: lstore 8
    //   835: goto -523 -> 312
    //   838: astore_1
    //   839: return
    //   840: astore_1
    //   841: goto -14 -> 827
    //   844: astore_1
    //   845: goto -18 -> 827
    //   848: goto -544 -> 304
    //
    // Exception table:
    //   from	to	target	type
    //   227	240	822	java/lang/Exception
    //   244	258	822	java/lang/Exception
    //   275	287	822	java/lang/Exception
    //   291	304	822	java/lang/Exception
    //   509	521	822	java/lang/Exception
    //   525	535	822	java/lang/Exception
    //   550	562	822	java/lang/Exception
    //   566	579	822	java/lang/Exception
    //   586	600	822	java/lang/Exception
    //   402	437	838	java/lang/Exception
    //   604	618	840	java/lang/Exception
    //   637	662	844	java/lang/Exception
    //   680	702	844	java/lang/Exception
    //   719	741	844	java/lang/Exception
    //   758	783	844	java/lang/Exception
    //   794	819	844	java/lang/Exception
  }

  private boolean checkSDCardXMLFile()
  {
    if (this.mMySP != null)
    {
      boolean bool = this.mMySP.checkFile();
      if (!bool)
        commit();
      return bool;
    }
    return false;
  }

  private void copyMySPToSP(MySharedPreferences paramMySharedPreferences, SharedPreferences paramSharedPreferences)
  {
    if ((paramMySharedPreferences != null) && (paramSharedPreferences != null))
    {
      paramSharedPreferences = paramSharedPreferences.edit();
      if (paramSharedPreferences != null)
      {
        paramSharedPreferences.clear();
        paramMySharedPreferences = paramMySharedPreferences.getAll().entrySet().iterator();
        while (paramMySharedPreferences.hasNext())
        {
          Object localObject = (Map.Entry)paramMySharedPreferences.next();
          String str = (String)((Map.Entry)localObject).getKey();
          localObject = ((Map.Entry)localObject).getValue();
          if ((localObject instanceof String))
            paramSharedPreferences.putString(str, (String)localObject);
          else if ((localObject instanceof Integer))
            paramSharedPreferences.putInt(str, ((Integer)localObject).intValue());
          else if ((localObject instanceof Long))
            paramSharedPreferences.putLong(str, ((Long)localObject).longValue());
          else if ((localObject instanceof Float))
            paramSharedPreferences.putFloat(str, ((Float)localObject).floatValue());
          else if ((localObject instanceof Boolean))
            paramSharedPreferences.putBoolean(str, ((Boolean)localObject).booleanValue());
        }
        paramSharedPreferences.commit();
      }
    }
  }

  private void copySPToMySP(SharedPreferences paramSharedPreferences, MySharedPreferences paramMySharedPreferences)
  {
    if ((paramSharedPreferences != null) && (paramMySharedPreferences != null))
    {
      paramMySharedPreferences = paramMySharedPreferences.edit();
      if (paramMySharedPreferences != null)
      {
        paramMySharedPreferences.clear();
        paramSharedPreferences = paramSharedPreferences.getAll().entrySet().iterator();
        while (paramSharedPreferences.hasNext())
        {
          Object localObject = (Map.Entry)paramSharedPreferences.next();
          String str = (String)((Map.Entry)localObject).getKey();
          localObject = ((Map.Entry)localObject).getValue();
          if ((localObject instanceof String))
            paramMySharedPreferences.putString(str, (String)localObject);
          else if ((localObject instanceof Integer))
            paramMySharedPreferences.putInt(str, ((Integer)localObject).intValue());
          else if ((localObject instanceof Long))
            paramMySharedPreferences.putLong(str, ((Long)localObject).longValue());
          else if ((localObject instanceof Float))
            paramMySharedPreferences.putFloat(str, ((Float)localObject).floatValue());
          else if ((localObject instanceof Boolean))
            paramMySharedPreferences.putBoolean(str, ((Boolean)localObject).booleanValue());
        }
        paramMySharedPreferences.commit();
      }
    }
  }

  private File getRootFolder(String paramString)
  {
    File localFile = Environment.getExternalStorageDirectory();
    if (localFile != null)
    {
      paramString = new File(String.format("%s%s%s", new Object[] { localFile.getAbsolutePath(), File.separator, paramString }));
      if ((paramString != null) && (!paramString.exists()))
        paramString.mkdirs();
      return paramString;
    }
    return null;
  }

  private TransactionXMLFile getTransactionXMLFile(String paramString)
  {
    paramString = getRootFolder(paramString);
    if (paramString != null)
    {
      this.mTxf = new TransactionXMLFile(paramString.getAbsolutePath());
      return this.mTxf;
    }
    return null;
  }

  private void initEditor()
  {
    if ((this.mEditor == null) && (this.mSp != null))
      this.mEditor = this.mSp.edit();
    if ((this.mCanWrite) && (this.mMyEditor == null) && (this.mMySP != null))
      this.mMyEditor = this.mMySP.edit();
    checkSDCardXMLFile();
  }

  public void clear()
  {
    initEditor();
    long l = System.currentTimeMillis();
    if (this.mEditor != null)
    {
      this.mEditor.clear();
      this.mEditor.putLong("t", l);
    }
    if (this.mMyEditor != null)
    {
      this.mMyEditor.clear();
      this.mMyEditor.putLong("t", l);
    }
  }

  public boolean commit()
  {
    boolean bool2 = true;
    long l = System.currentTimeMillis();
    boolean bool1 = bool2;
    if (this.mEditor != null)
    {
      if ((!this.mIsLessMode) && (this.mSp != null))
        this.mEditor.putLong("t", l);
      bool1 = bool2;
      if (!this.mEditor.commit())
        bool1 = false;
    }
    if ((this.mSp != null) && (this.mContext != null))
      this.mSp = this.mContext.getSharedPreferences(this.mConfigName, 0);
    String str = Environment.getExternalStorageState();
    boolean bool3 = bool1;
    if (!StringUtils.isEmpty(str))
    {
      bool2 = bool1;
      if (str.equals("mounted"))
      {
        if (this.mMySP != null)
          break label264;
        TransactionXMLFile localTransactionXMLFile = getTransactionXMLFile(this.mFolderName);
        bool2 = bool1;
        if (localTransactionXMLFile != null)
        {
          this.mMySP = localTransactionXMLFile.getMySharedPreferences(this.mConfigName, 0);
          if (this.mIsLessMode)
            break label249;
          copySPToMySP(this.mSp, this.mMySP);
        }
      }
    }
    while (true)
    {
      this.mMyEditor = this.mMySP.edit();
      bool2 = bool1;
      label189: if (!str.equals("mounted"))
      {
        bool3 = bool2;
        if (str.equals("mounted_ro"))
        {
          bool3 = bool2;
          if (this.mMySP == null);
        }
      }
      else
      {
        bool3 = bool2;
      }
      try
      {
        if (this.mTxf != null)
        {
          this.mMySP = this.mTxf.getMySharedPreferences(this.mConfigName, 0);
          bool3 = bool2;
        }
        return bool3;
        label249: copyMySPToSP(this.mMySP, this.mSp);
        continue;
        label264: bool2 = bool1;
        if (this.mMyEditor == null)
          break label189;
        bool2 = bool1;
        if (this.mMyEditor.commit())
          break label189;
        bool2 = false;
      }
      catch (Exception localException)
      {
      }
    }
    return bool2;
  }

  public Map<String, ?> getAll()
  {
    checkSDCardXMLFile();
    if (this.mSp != null)
      return this.mSp.getAll();
    if (this.mMySP != null)
      return this.mMySP.getAll();
    return null;
  }

  public boolean getBoolean(String paramString)
  {
    boolean bool = false;
    checkSDCardXMLFile();
    if (this.mSp != null)
      bool = this.mSp.getBoolean(paramString, false);
    while (this.mMySP == null)
      return bool;
    return this.mMySP.getBoolean(paramString, false);
  }

  public float getFloat(String paramString)
  {
    float f = 0.0F;
    checkSDCardXMLFile();
    if (this.mSp != null)
      f = this.mSp.getFloat(paramString, 0.0F);
    while (this.mMySP == null)
      return f;
    return this.mMySP.getFloat(paramString, 0.0F);
  }

  public int getInt(String paramString)
  {
    int i = 0;
    checkSDCardXMLFile();
    if (this.mSp != null)
      i = this.mSp.getInt(paramString, 0);
    while (this.mMySP == null)
      return i;
    return this.mMySP.getInt(paramString, 0);
  }

  public long getLong(String paramString)
  {
    long l = 0L;
    checkSDCardXMLFile();
    if (this.mSp != null)
      l = this.mSp.getLong(paramString, 0L);
    while (this.mMySP == null)
      return l;
    return this.mMySP.getLong(paramString, 0L);
  }

  public String getString(String paramString)
  {
    checkSDCardXMLFile();
    if (this.mSp != null)
    {
      String str = this.mSp.getString(paramString, "");
      if (!StringUtils.isEmpty(str))
        return str;
    }
    if (this.mMySP != null)
      return this.mMySP.getString(paramString, "");
    return "";
  }

  public void putBoolean(String paramString, boolean paramBoolean)
  {
    if ((!StringUtils.isEmpty(paramString)) && (!paramString.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.putBoolean(paramString, paramBoolean);
      if (this.mMyEditor != null)
        this.mMyEditor.putBoolean(paramString, paramBoolean);
    }
  }

  public void putFloat(String paramString, float paramFloat)
  {
    if ((!StringUtils.isEmpty(paramString)) && (!paramString.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.putFloat(paramString, paramFloat);
      if (this.mMyEditor != null)
        this.mMyEditor.putFloat(paramString, paramFloat);
    }
  }

  public void putInt(String paramString, int paramInt)
  {
    if ((!StringUtils.isEmpty(paramString)) && (!paramString.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.putInt(paramString, paramInt);
      if (this.mMyEditor != null)
        this.mMyEditor.putInt(paramString, paramInt);
    }
  }

  public void putLong(String paramString, long paramLong)
  {
    if ((!StringUtils.isEmpty(paramString)) && (!paramString.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.putLong(paramString, paramLong);
      if (this.mMyEditor != null)
        this.mMyEditor.putLong(paramString, paramLong);
    }
  }

  public void putString(String paramString1, String paramString2)
  {
    if ((!StringUtils.isEmpty(paramString1)) && (!paramString1.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.putString(paramString1, paramString2);
      if (this.mMyEditor != null)
        this.mMyEditor.putString(paramString1, paramString2);
    }
  }

  public void reload()
  {
    if ((this.mSp != null) && (this.mContext != null))
      this.mSp = this.mContext.getSharedPreferences(this.mConfigName, 0);
    String str = Environment.getExternalStorageState();
    if ((!StringUtils.isEmpty(str)) && ((str.equals("mounted")) || ((str.equals("mounted_ro")) && (this.mMySP != null))));
    try
    {
      if (this.mTxf != null)
        this.mMySP = this.mTxf.getMySharedPreferences(this.mConfigName, 0);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void remove(String paramString)
  {
    if ((!StringUtils.isEmpty(paramString)) && (!paramString.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.remove(paramString);
      if (this.mMyEditor != null)
        this.mMyEditor.remove(paramString);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.core.persistent.PersistentConfiguration
 * JD-Core Version:    0.6.2
 */