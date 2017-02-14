package cn.jpush.android.api;

import android.app.NotificationManager;
import android.content.Context;
import cn.jpush.android.data.JPushLocalNotification;
import cn.jpush.android.service.PushService;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.service.g;
import cn.jpush.android.util.a;
import cn.jpush.android.util.ab;
import cn.jpush.android.util.ae;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.x;
import cn.jpush.android.util.z;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Set<Ljava.lang.String;>;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JPushInterface
{
  public static final String ACTION_ACTIVITY_OPENDED;
  public static final String ACTION_CONNECTION_CHANGE;
  public static final String ACTION_MESSAGE_RECEIVED;
  public static final String ACTION_NOTIFICATION_OPENED;
  public static final String ACTION_NOTIFICATION_RECEIVED;
  public static final String ACTION_NOTIFICATION_RECEIVED_PROXY;
  public static final String ACTION_REGISTRATION_ID;
  public static final String ACTION_RESTOREPUSH;
  public static final String ACTION_RICHPUSH_CALLBACK;
  public static final String ACTION_STATUS;
  public static final String ACTION_STOPPUSH;
  public static final String ACTION_UNREGISTER;
  public static final String EXTRA_ACTIVITY_PARAM;
  public static final String EXTRA_ALERT;
  public static final String EXTRA_APP_KEY;
  public static final String EXTRA_CONNECTION_CHANGE;
  public static final String EXTRA_CONTENT_TYPE;
  public static final String EXTRA_EXTRA;
  public static final String EXTRA_MESSAGE;
  public static final String EXTRA_MSG_ID;
  public static final String EXTRA_NOTIFICATION_DEVELOPER_ARG0;
  public static final String EXTRA_NOTIFICATION_ID;
  public static final String EXTRA_NOTIFICATION_TITLE;
  public static final String EXTRA_NOTI_TYPE;
  public static final String EXTRA_PUSH_ID;
  public static final String EXTRA_REGISTRATION_ID;
  public static final String EXTRA_RICHPUSH_FILE_PATH;
  public static final String EXTRA_RICHPUSH_FILE_TYPE;
  public static final String EXTRA_RICHPUSH_HTML_PATH;
  public static final String EXTRA_RICHPUSH_HTML_RES;
  public static final String EXTRA_STATUS;
  public static final String EXTRA_TITLE;
  private static final Integer a;
  private static ConcurrentHashMap<Integer, PushNotificationBuilder> b;
  private static e c;
  private static ConcurrentLinkedQueue<Long> d;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[51];
    int j = 0;
    Object localObject1 = "";
    int i = 50;
    Object localObject2 = arrayOfString;
    Object localObject4 = localObject2;
    localObject1 = ((String)localObject1).toCharArray();
    int k = localObject1.length;
    int i1 = 0;
    int m = 0;
    int i4 = i;
    Object localObject3 = localObject1;
    int i2 = j;
    localObject2 = localObject4;
    int n = k;
    int i3;
    if (k <= 1)
    {
      i3 = i;
      label65: n = m;
      label68: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 65;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label68;
        EXTRA_STATUS = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qL`5\0241Q \024?\r`I\b\"\013wO\0258\020k";
        i = 51;
        j = i2;
        break;
        ACTION_UNREGISTER = (String)localObject1;
        localObject1 = "";
        i = 52;
        j = i2;
        break;
        EXTRA_NOTIFICATION_ID = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qh]\006.\026a";
        i = 53;
        j = i2;
        break;
        EXTRA_MSG_ID = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qd^\021:\032|";
        i = 54;
        j = i2;
        break;
        EXTRA_APP_KEY = (String)localObject1;
        localObject1 = "";
        i = 55;
        j = i2;
        break;
        EXTRA_REGISTRATION_ID = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qdB\004#\013";
        i = 56;
        j = i2;
        break;
        EXTRA_ALERT = (String)localObject1;
        localObject1 = "";
        i = 57;
        j = i2;
        break;
        EXTRA_RICHPUSH_HTML_PATH = (String)localObject1;
        localObject1 = "";
        i = 58;
        j = i2;
        break;
        EXTRA_RICHPUSH_HTML_RES = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qL`5\0241Q \0234\030l]\025#\036qG\016?";
        i = 59;
        j = i2;
        break;
        ACTION_REGISTRATION_ID = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qL`5\0241Q \0234\fqA\0234\017p]\t";
        i = 60;
        j = i2;
        break;
        ACTION_RESTOREPUSH = (String)localObject1;
        localObject1 = "";
        i = 61;
        j = i2;
        break;
        EXTRA_NOTI_TYPE = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qfA\017%\032kZ\036%\006uK";
        i = 62;
        j = i2;
        break;
        EXTRA_CONTENT_TYPE = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qL`5\0241Q \022%\020u^\024\"\027";
        i = 63;
        j = i2;
        break;
        ACTION_STOPPUSH = (String)localObject1;
        localObject1 = "";
        i = 64;
        j = i2;
        break;
        EXTRA_RICHPUSH_FILE_PATH = (String)localObject1;
        localObject1 = "";
        i = 65;
        j = i2;
        break;
        ACTION_NOTIFICATION_OPENED = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qqG\025=\032";
        i = 66;
        j = i2;
        break;
        EXTRA_TITLE = (String)localObject1;
        localObject1 = "";
        i = 67;
        j = i2;
        break;
        ACTION_RICHPUSH_CALLBACK = (String)localObject1;
        localObject1 = "";
        i = 68;
        j = i2;
        break;
        EXTRA_NOTIFICATION_DEVELOPER_ARG0 = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qL`5\0241Q \022%\036q[\022";
        i = 69;
        j = i2;
        break;
        ACTION_STATUS = (String)localObject1;
        localObject1 = "";
        i = 70;
        j = i2;
        break;
        ACTION_MESSAGE_RECEIVED = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025q`V\025#\036";
        i = 71;
        j = i2;
        break;
        EXTRA_EXTRA = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qL`5\0241Q \002>\021kK\002%\026j@";
        i = 72;
        j = i2;
        break;
        ACTION_CONNECTION_CHANGE = (String)localObject1;
        localObject1 = "";
        i = 73;
        j = i2;
        break;
        ACTION_NOTIFICATION_RECEIVED_PROXY = (String)localObject1;
        localObject1 = "";
        i = 74;
        j = i2;
        break;
        EXTRA_ACTIVITY_PARAM = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qfA\017?\032fZ\b>\021zM\t0\021bK";
        i = 75;
        j = i2;
        break;
        EXTRA_CONNECTION_CHANGE = (String)localObject1;
        localObject1 = "";
        i = 76;
        j = i2;
        break;
        EXTRA_PUSH_ID = (String)localObject1;
        localObject1 = "\0221\013d1\004,M  \037;Wa(\025qhK\022\"\036bK";
        i = 77;
        j = i2;
        break;
        EXTRA_MESSAGE = (String)localObject1;
        localObject1 = "";
        i = 78;
        j = i2;
        break;
        EXTRA_NOTIFICATION_TITLE = (String)localObject1;
        localObject1 = "";
        i = 79;
        j = i2;
        break;
        ACTION_NOTIFICATION_RECEIVED = (String)localObject1;
        localObject1 = "";
        i = 80;
        j = i2;
        break;
        ACTION_ACTIVITY_OPENDED = (String)localObject1;
        localObject1 = "";
        i = 81;
        j = i2;
        break;
        EXTRA_RICHPUSH_FILE_TYPE = (String)localObject1;
        localObject1 = ";\017P})81Qk3\027>Fk";
        i = -1;
        j = i2;
        break;
      }
      i1 = n;
      n = k;
      localObject2 = localObject4;
      i2 = j;
      localObject3 = localObject1;
      i4 = i3;
      i3 = i4;
      localObject1 = localObject3;
      j = i2;
      localObject4 = localObject2;
      k = n;
      m = i1;
      if (n > i1)
        break label65;
      localObject1 = new String(localObject3).intern();
      switch (i4)
      {
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
      case 65:
      case 66:
      case 67:
      case 68:
      case 69:
      case 70:
      case 71:
      case 72:
      case 73:
      case 74:
      case 75:
      case 76:
      case 77:
      case 78:
      case 79:
      case 80:
      case 81:
      default:
        localObject2[i2] = localObject1;
        j = 1;
        localObject1 = "81So-\030;\005z \026\037.";
        i = 0;
        localObject2 = arrayOfString;
        break;
      case 0:
        localObject2[i2] = localObject1;
        j = 2;
        localObject1 = "%7@.-\0241Bf5Q0C.5\0208V.,\020&Gka\0340Wka\0057D`a@o\025 ";
        i = 1;
        localObject2 = arrayOfString;
        break;
      case 1:
        localObject2[i2] = localObject1;
        j = 3;
        localObject1 = "%7@.,\0028lja\030,\005`.\005So-\030;\005#a";
        i = 2;
        localObject2 = arrayOfString;
        break;
      case 2:
        localObject2[i2] = localObject1;
        j = 4;
        localObject1 = "\034>]@4\034Vf.\0043A.Qo\t.\006\030)@.4\001Dm5\0300K o";
        i = 3;
        localObject2 = arrayOfString;
        break;
      case 3:
        localObject2[i2] = localObject1;
        j = 5;
        localObject1 = "\020<Qg.\037eVk5=>Qk2\005\021Jz(\0276Fo5\0300K@4\034=@|aK";
        i = 4;
        localObject2 = arrayOfString;
        break;
      case 4:
        localObject2[i2] = localObject1;
        j = 6;
        localObject1 = "81So-\030;\005z \026e\005";
        i = 5;
        localObject2 = arrayOfString;
        break;
      case 5:
        localObject2[i2] = localObject1;
        j = 7;
        localObject1 = "\020<Qg.\037eVz.\001\017P})";
        i = 6;
        localObject2 = arrayOfString;
        break;
      case 6:
        localObject2[i2] = localObject1;
        j = 8;
        localObject1 = "?\niBa\0220Kz$\t+";
        i = 7;
        localObject2 = arrayOfString;
        break;
      case 7:
        localObject2[i2] = localObject1;
        j = 9;
        localObject1 = "梱洔刕彝刌泐杖罴绒ぃ欕勷佹專坩杸缎绹旸膫務纸终扩蠍び";
        i = 8;
        localObject2 = arrayOfString;
        break;
      case 8:
        localObject2[i2] = localObject1;
        j = 10;
        localObject1 = ";\017P})QqVaa\0276Ika\0250\005`.\005Ho5\0227\005D\021\004,M.o\033>W.'\0303@.(\037Qf$Q/Wa+\024<Q\"a\0057@.1\0030Fk2\002Rg-\035@v(\005Dza\0361Fko";
        i = 9;
        localObject2 = arrayOfString;
        break;
      case 9:
        localObject2[i2] = localObject1;
        j = 11;
        localObject1 = "\020<Qg.\037eL`(\005s\005Z)\024Vj*Q)@|2\0300K.(\002";
        i = 10;
        localObject2 = arrayOfString;
        break;
      case 10:
        localObject2[i2] = localObject1;
        j = 12;
        localObject1 = "";
        i = 11;
        localObject2 = arrayOfString;
        break;
      case 11:
        localObject2[i2] = localObject1;
        j = 13;
        localObject1 = "2*Vz.\0346_k%Q=Pg-\025:W.'\0030H.2\020)@ja\001-@h$\003:Km$Qr\005";
        i = 12;
        localObject2 = arrayOfString;
        break;
      case 12:
        localObject2[i2] = localObject1;
        j = 14;
        localObject1 = "\020<Qg.\037eBk5!*Vf\017\036+Lh(\022>Qg.\037\035Pg-\025:W.{Q";
        i = 13;
        localObject2 = arrayOfString;
        break;
      case 13:
        localObject2[i2] = localObject1;
        j = 15;
        localObject1 = "Xv";
        i = 14;
        localObject2 = arrayOfString;
        break;
      case 14:
        localObject2[i2] = localObject1;
        j = 16;
        localObject1 = "]U{2\031\013Lc$K";
        i = 15;
        localObject2 = arrayOfString;
        break;
      case 15:
        localObject2[i2] = localObject1;
        j = 17;
        localObject1 = "\\v\016&";
        i = 16;
        localObject2 = arrayOfString;
        break;
      case 16:
        localObject2[i2] = localObject1;
        j = 18;
        localObject1 = "\020<Qg.\037eVk5!*Vf\025\0302@.lQ<Ia2\024;";
        i = 17;
        localObject2 = arrayOfString;
        break;
      case 17:
        localObject2[i2] = localObject1;
        j = 19;
        localObject1 = "Y\004\025#w,$\025\"v\fvz&i";
        i = 18;
        localObject2 = arrayOfString;
        break;
      case 18:
        localObject2[i2] = localObject1;
        j = 20;
        localObject1 = "81So-\030;\005z(\034:\005h.\0032Dza\\";
        i = 19;
        localObject2 = arrayOfString;
        break;
      case 19:
        localObject2[i2] = localObject1;
        j = 21;
        localObject1 = "\020<Qg.\037eVk5!*Vf\025\0302@.lQ:Ko#\035:A4";
        i = 20;
        localObject2 = arrayOfString;
        break;
      case 20:
        localObject2[i2] = localObject1;
        j = 22;
        localObject1 = "Y\004\025#x,#\024Uq\\fxrs*o\b=\034X\003{&\032Ar\034S=@\004\025#x,#\027Uq\\lx'";
        i = 21;
        localObject2 = arrayOfString;
        break;
      case 21:
        localObject2[i2] = localObject1;
        j = 23;
        localObject1 = "X#\r";
        i = 22;
        localObject2 = arrayOfString;
        break;
      case 22:
        localObject2[i2] = localObject1;
        j = 24;
        localObject1 = "\002:Wx(\022:zm.\0371@m$\005";
        i = 23;
        localObject2 = arrayOfString;
        break;
      case 23:
        localObject2[i2] = localObject1;
        j = 25;
        localObject1 = "?\niBa\0370Qg'\030<Dz(\0361";
        i = 24;
        localObject2 = arrayOfString;
        break;
      case 24:
        localObject2[i2] = localObject1;
        j = 26;
        localObject1 = "%7@.\017\036+Lh(\022>Qg.\037g{(\035;@|a\030;\005o-\003:Dj8Q:]}(\005,\005#a";
        i = 25;
        localObject2 = arrayOfString;
        break;
      case 25:
        localObject2[i2] = localObject1;
        j = 27;
        localObject1 = "?\niBa\001*Vf\017\036+Lh(\022>Qg.\037\035Pg-\025:W";
        i = 26;
        localObject2 = arrayOfString;
        break;
      case 26:
        localObject2[i2] = localObject1;
        j = 28;
        localObject1 = "\030;\005})\036*Ija\023:\005b \0038@|a\0057D`aA";
        i = 27;
        localObject2 = arrayOfString;
        break;
      case 27:
        localObject2[i2] = localObject1;
        j = 29;
        localObject1 = "\020<Qg.\037eVk5!*Vf\017\036+Lh(\022>Qg.\037\035Pg-\025:W.lQ6A4";
        i = 28;
        localObject2 = arrayOfString;
        break;
      case 28:
        localObject2[i2] = localObject1;
        j = 30;
        localObject1 = "\020<Qg.\037eWk2\0042@^4\0027";
        i = 29;
        localObject2 = arrayOfString;
        break;
      case 29:
        localObject2[i2] = localObject1;
        j = 31;
        localObject1 = "\":Q.\022\0303@`\"\024u{2\031\013Lc$Qr\005";
        i = 30;
        localObject2 = arrayOfString;
        break;
      case 30:
        localObject2[i2] = localObject1;
        j = 32;
        localObject1 = "#:Ha7\024Qf$Q,Lb$\037<@.5\0302@/";
        i = 31;
        localObject2 = arrayOfString;
        break;
      case 31:
        localObject2[i2] = localObject1;
        j = 33;
        localObject1 = "\":Q.\022\0303@`\"\024u{2\031\013Lc$Q\031Dg-\024;";
        i = 32;
        localObject2 = arrayOfString;
        break;
      case 32:
        localObject2[i2] = localObject1;
        j = 34;
        localObject1 = "Qr\b.";
        i = 33;
        localObject2 = arrayOfString;
        break;
      case 33:
        localObject2[i2] = localObject1;
        j = 35;
        localObject1 = "Qe\005";
        i = 34;
        localObject2 = arrayOfString;
        break;
      case 34:
        localObject2[i2] = localObject1;
        j = 36;
        localObject1 = "81So-\030;\005~ \003>Hk5\024-\005h.\0032DzmQ,Qo3\005\027J{3Q>Kja\0241AF.\004-\005})\036*Ija\023:Qy$\0241\005>a\017\027=mQ,Qo3\005\022L`2Q>Kja\0241AC(\037,\005})\036*Ija\023:Qy$\0241\005>a\017\0207oQ";
        i = 35;
        localObject2 = arrayOfString;
        break;
      case 35:
        localObject2[i2] = localObject1;
        j = 37;
        localObject1 = "81So-\030;\005j \bCa3\034>Q.lQ";
        i = 36;
        localObject2 = arrayOfString;
        break;
      case 36:
        localObject2[i2] = localObject1;
        j = 38;
        localObject1 = "81So-\030;\005z(\034:\005h.\0032Dza\\Vz \003+ma4\003Vf.\0043A.-\024,V.5\031>K.$\037;ma4\003";
        i = 37;
        localObject2 = arrayOfString;
        break;
      case 37:
        localObject2[i2] = localObject1;
        j = 39;
        localObject1 = "An\027=uDiz>\037Cl";
        i = 38;
        localObject2 = arrayOfString;
        break;
      case 38:
        localObject2[i2] = localObject1;
        j = 40;
        localObject1 = "]Qo&\002e";
        i = 39;
        localObject2 = arrayOfString;
        break;
      case 39:
        localObject2[i2] = localObject1;
        j = 41;
        localObject1 = "%7@.-\0241Bz)Q0C.5\0208V.2\0310Pb%Q=@.-\024,V.5\031>K.pAo\025.#\b+@}o";
        i = 40;
        localObject2 = arrayOfString;
        break;
      case 40:
        localObject2[i2] = localObject1;
        j = 42;
        localObject1 = "81So-\030;\005z \026,\t.6\0303I./\036+\005}$\005Qo&\002Qf(\002Qg,\024q";
        i = 41;
        localObject2 = arrayOfString;
        break;
      case 41:
        localObject2[i2] = localObject1;
        j = 43;
        localObject1 = "]Rg-\035Ka5Q,@za\0203Lo2Q+Mg2Q+Lc$_";
        i = 42;
        localObject2 = arrayOfString;
        break;
      case 42:
        localObject2[i2] = localObject1;
        j = 44;
        localObject1 = "?\niBa\0203Lo2Q>Kja\005>B}oQ\030Lx$Q*U. \022+La/_";
        i = 43;
        localObject2 = arrayOfString;
        break;
      case 43:
        localObject2[i2] = localObject1;
        j = 45;
        localObject1 = "81So-\030;\005o-\030>V4a";
        i = 44;
        localObject2 = arrayOfString;
        break;
      case 44:
        localObject2[i2] = localObject1;
        j = 46;
        localObject1 = "\020<Qg.\037eVk503Lo201AZ \026,\005#a\0203Lo2K";
        i = 45;
        localObject2 = arrayOfString;
        break;
      case 45:
        localObject2[i2] = localObject1;
        j = 47;
        localObject1 = "\0370Qg'\030<Dz(\0361";
        i = 46;
        localObject2 = arrayOfString;
        break;
      case 46:
        localObject2[i2] = localObject1;
        j = 48;
        localObject1 = "81So-\030;\005`.\0056Cg\"\020+La/8;\t.\006\030)@.4\001Dm5\0300K o";
        i = 47;
        localObject2 = arrayOfString;
        break;
      case 47:
        localObject2[i2] = localObject1;
        j = 49;
        localObject1 = "\002:V}$\0300K.5\0302@a4\005Ik2\002Qf \037\024>2";
        i = 48;
        localObject2 = arrayOfString;
        break;
      case 48:
        localObject2[i2] = localObject1;
        j = 50;
        localObject1 = "\002:V}$\0300K.5\0302@a4\005Io3\026:W.5\031>K.p\025>\\";
        i = 49;
        localObject2 = arrayOfString;
        break;
      case 49:
        localObject2[i2] = localObject1;
        z = arrayOfString;
        a = Integer.valueOf(0);
        b = new ConcurrentHashMap();
        c = e.b();
        d = new ConcurrentLinkedQueue();
        return;
        i = 113;
        continue;
        i = 95;
        continue;
        i = 37;
        continue;
        i = 14;
      }
    }
  }

  private static String a(Set<String> paramSet)
  {
    Object localObject = null;
    Iterator localIterator = null;
    if (paramSet == null)
      localObject = localIterator;
    int i;
    label77: 
    do
    {
      do
      {
        return localObject;
        if (paramSet.isEmpty())
          return "";
        localIterator = paramSet.iterator();
        i = 0;
        paramSet = (Set<String>)localObject;
        localObject = paramSet;
      }
      while (!localIterator.hasNext());
      localObject = (String)localIterator.next();
      if ((ai.a((String)localObject)) || (!z.a((String)localObject)))
        break label120;
      if (paramSet != null)
        break;
      paramSet = (Set<String>)localObject;
      i += 1;
      localObject = paramSet;
    }
    while (i >= 100);
    while (true)
    {
      break;
      paramSet = paramSet + "," + (String)localObject;
      break label77;
      label120: x.e(z[0], z[6] + (String)localObject);
    }
  }

  private static void a(Context paramContext, String paramString, Set<String> paramSet, TagAliasCallback paramTagAliasCallback, boolean paramBoolean)
  {
    if (paramContext == null)
      throw new IllegalArgumentException(z[8]);
    if ((PushService.b) && (!a.b(paramContext)))
      x.b(z[0], z[9]);
    if (paramString != null)
    {
      i = z.b(paramString);
      if (i != 0)
      {
        x.b(z[0], z[45] + paramString + z[43]);
        if (paramTagAliasCallback != null)
          paramTagAliasCallback.gotResult(i, paramString, paramSet);
      }
    }
    Object localObject;
    do
    {
      do
      {
        return;
        localObject = paramSet;
        if (paramTagAliasCallback == null)
        {
          localObject = paramSet;
          if (!paramBoolean)
            localObject = filterValidTags(paramSet);
        }
        if (localObject == null)
          break;
        i = z.a((Set)localObject);
        if (i == 0)
          break;
        x.b(z[0], z[42]);
      }
      while (paramTagAliasCallback == null);
      paramTagAliasCallback.gotResult(i, paramString, (Set)localObject);
      return;
      paramSet = a((Set)localObject);
      if ((paramString != null) || (paramSet != null))
        break;
      x.e(z[0], z[44]);
    }
    while (paramTagAliasCallback == null);
    paramTagAliasCallback.gotResult(d.a, paramString, (Set)localObject);
    return;
    String str;
    if (paramSet != null)
    {
      str = paramSet.replaceAll(",", "");
      if (ai.a(str))
        break label371;
    }
    label371: for (int i = str.getBytes().length + 0; ; i = 0)
    {
      if (i <= 1000);
      for (i = 1; i == 0; i = 0)
      {
        if (paramTagAliasCallback != null)
          paramTagAliasCallback.gotResult(d.h, paramString, (Set)localObject);
        x.e(z[0], z[41]);
        return;
      }
      x.b(z[0], z[46] + paramString + z[40] + paramSet);
      ServiceInterface.a(paramContext, paramString, paramSet, new b(paramString, (Set)localObject, paramTagAliasCallback));
      return;
    }
  }

  private static void a(Context paramContext, boolean paramBoolean, String paramString)
  {
    x.c();
    if (paramContext == null)
      throw new IllegalArgumentException(z[8]);
    if (!paramBoolean)
    {
      ServiceInterface.a(paramContext, paramBoolean, paramString);
      x.b(z[0], z[18]);
      return;
    }
    String str = z[22];
    if (Pattern.compile(z[19] + str + z[23] + str + z[17] + str + z[15]).matcher(paramString).matches())
    {
      ServiceInterface.a(paramContext, paramBoolean, paramString);
      x.b(z[0], z[21] + paramBoolean + z[16] + paramString);
      return;
    }
    x.e(z[0], z[20] + paramString);
  }

  static boolean a(int paramInt)
  {
    if (paramInt <= 0);
    while (b.get(Integer.valueOf(paramInt)) == null)
      return false;
    return true;
  }

  public static void addLocalNotification(Context paramContext, JPushLocalNotification paramJPushLocalNotification)
  {
    g.a(paramContext).a(paramContext, paramJPushLocalNotification);
  }

  static PushNotificationBuilder b(int paramInt)
  {
    x.a(z[0], z[14] + paramInt);
    int i = paramInt;
    if (paramInt <= 0)
      i = a.intValue();
    Object localObject1 = (PushNotificationBuilder)b.get(Integer.valueOf(i));
    Object localObject2 = localObject1;
    if (localObject1 == null)
      x.c();
    try
    {
      localObject2 = ab.a(i);
      boolean bool = ai.a((String)localObject2);
      if (bool);
      for (localObject1 = null; ; localObject1 = localObject2)
      {
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          x.c();
          localObject2 = new DefaultPushNotificationBuilder();
        }
        return localObject2;
        new StringBuilder(z[13]).append((String)localObject2).toString();
        x.b();
        localObject2 = BasicPushNotificationBuilder.a((String)localObject2);
      }
    }
    catch (Exception localException)
    {
      while (true)
        x.h();
    }
  }

  public static void clearAllNotifications(Context paramContext)
  {
    ServiceInterface.g(paramContext);
  }

  public static void clearLocalNotifications(Context paramContext)
  {
    g.a(paramContext);
    g.b(paramContext);
  }

  public static void clearNotificationById(Context paramContext, int paramInt)
  {
    if (paramInt <= 0)
    {
      x.e(z[0], z[48]);
      return;
    }
    ((NotificationManager)paramContext.getSystemService(z[47])).cancel(paramInt);
  }

  public static Set<String> filterValidTags(Set<String> paramSet)
  {
    if (paramSet == null)
      localObject = null;
    do
    {
      return localObject;
      localObject = paramSet;
    }
    while (paramSet.isEmpty());
    Object localObject = new LinkedHashSet();
    paramSet = paramSet.iterator();
    int i = 0;
    while (paramSet.hasNext())
    {
      String str = (String)paramSet.next();
      if ((!ai.a(str)) && (z.a(str)))
      {
        ((Set)localObject).add(str);
        int j = i + 1;
        i = j;
        if (j >= 100)
        {
          x.d(z[0], z[2]);
          return localObject;
        }
      }
      else
      {
        x.e(z[0], z[1] + str);
      }
    }
    return localObject;
  }

  public static boolean getConnectionState(Context paramContext)
  {
    return ae.a(paramContext, z[24], 0) == 1;
  }

  public static String getRegistrationID(Context paramContext)
  {
    return a.t(paramContext);
  }

  public static String getUdid(Context paramContext)
  {
    return a.k(paramContext);
  }

  public static void init(Context paramContext)
  {
    x.b(z[0], z[11] + ServiceInterface.b());
    if (paramContext == null)
      throw new IllegalArgumentException(z[8]);
    if ((PushService.b) && (!a.b(paramContext)))
      x.b(z[0], z[9]);
    ab.a(paramContext.getApplicationContext());
    if (ae.a(paramContext, z[12], -1) == -1)
      setLatestNotificationNumber(paramContext, 5);
    if (!ServiceInterface.a())
    {
      x.e(z[0], z[10]);
      System.exit(-1);
    }
    ServiceInterface.a(paramContext);
  }

  public static void initCrashHandler(Context paramContext)
  {
    c.a().a(paramContext);
  }

  public static boolean isPushStopped(Context paramContext)
  {
    if (paramContext == null)
      throw new IllegalArgumentException(z[8]);
    return ServiceInterface.h(paramContext);
  }

  public static void onFragmentPause(Context paramContext, String paramString)
  {
    c.b(paramContext, paramString);
  }

  public static void onFragmentResume(Context paramContext, String paramString)
  {
    c.a(paramContext, paramString);
  }

  public static void onKillProcess(Context paramContext)
  {
    c.c(paramContext);
  }

  public static void onPause(Context paramContext)
  {
    c.b(paramContext);
  }

  public static void onResume(Context paramContext)
  {
    c.a(paramContext);
  }

  public static void removeLocalNotification(Context paramContext, long paramLong)
  {
    g.a(paramContext);
    g.a(paramContext, paramLong);
  }

  public static void reportNotificationOpened(Context paramContext, String paramString)
  {
    if (ai.a(paramString))
      x.e(z[0], z[3] + paramString);
    ServiceInterface.a(paramString, 1028, paramContext);
  }

  public static void resumePush(Context paramContext)
  {
    x.b(z[0], z[30]);
    if (paramContext == null)
      throw new IllegalArgumentException(z[8]);
    ServiceInterface.b(paramContext, 1);
  }

  public static void setAlias(Context paramContext, String paramString, TagAliasCallback paramTagAliasCallback)
  {
    setAliasAndTags(paramContext, paramString, null, paramTagAliasCallback);
  }

  @Deprecated
  public static void setAliasAndTags(Context paramContext, String paramString, Set<String> paramSet)
  {
    a(paramContext, paramString, paramSet, null, false);
  }

  public static void setAliasAndTags(Context paramContext, String paramString, Set<String> paramSet, TagAliasCallback paramTagAliasCallback)
  {
    long l = System.currentTimeMillis();
    int i;
    if (d.size() < 10)
    {
      d.offer(Long.valueOf(l));
      i = 1;
    }
    while (i == 0)
    {
      x.e();
      if (paramTagAliasCallback != null)
        paramTagAliasCallback.gotResult(d.k, paramString, paramSet);
      return;
      if (l - ((Long)d.element()).longValue() > 10000L)
      {
        while (d.size() >= 10)
          d.poll();
        d.offer(Long.valueOf(l));
        i = 1;
      }
      else
      {
        i = 0;
      }
    }
    a(paramContext, paramString, paramSet, paramTagAliasCallback, true);
  }

  public static void setDebugMode(boolean paramBoolean)
  {
    PushService.b = paramBoolean;
  }

  public static void setDefaultPushNotificationBuilder(BasicPushNotificationBuilder paramBasicPushNotificationBuilder)
  {
    if (paramBasicPushNotificationBuilder == null)
      throw new IllegalArgumentException(z[25]);
    b.put(a, paramBasicPushNotificationBuilder);
    ab.c(a, paramBasicPushNotificationBuilder.toString());
  }

  public static void setLatestNotificationNumber(Context paramContext, int paramInt)
  {
    x.a(z[0], z[5] + paramInt);
    if (paramInt <= 0)
    {
      x.e(z[0], z[4]);
      return;
    }
    ServiceInterface.d(paramContext, paramInt);
  }

  public static void setPushNotificationBuilder(Integer paramInteger, BasicPushNotificationBuilder paramBasicPushNotificationBuilder)
  {
    x.a(z[0], z[29] + paramInteger);
    if (paramBasicPushNotificationBuilder == null)
      throw new IllegalArgumentException(z[27]);
    if (paramInteger.intValue() <= 0)
    {
      x.e(z[0], z[28]);
      return;
    }
    if (b.containsKey(paramInteger))
      x.d(z[0], z[26] + paramInteger);
    ab.c(paramInteger, paramBasicPushNotificationBuilder.toString());
    b.put(paramInteger, paramBasicPushNotificationBuilder);
  }

  public static void setPushTime(Context paramContext, Set<Integer> paramSet, int paramInt1, int paramInt2)
  {
    if ((PushService.b) && (!a.b(paramContext)))
      x.b(z[0], z[9]);
    if (paramSet == null)
    {
      a(paramContext, true, z[39]);
      return;
    }
    if ((paramSet.size() == 0) || (paramSet.isEmpty()))
    {
      a(paramContext, false, z[39]);
      return;
    }
    if (paramInt1 > paramInt2)
    {
      x.e(z[0], z[38]);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      Integer localInteger = (Integer)paramSet.next();
      if ((localInteger.intValue() > 6) || (localInteger.intValue() < 0))
      {
        x.e(z[0], z[37] + localInteger);
        return;
      }
      localStringBuilder.append(localInteger);
    }
    localStringBuilder.append("_");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append("^");
    localStringBuilder.append(paramInt2);
    a(paramContext, true, localStringBuilder.toString());
  }

  public static void setSilenceTime(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt3 < 0) || (paramInt4 < 0) || (paramInt2 > 59) || (paramInt4 > 59) || (paramInt3 > 23) || (paramInt1 > 23))
    {
      x.e(z[0], z[36]);
      return;
    }
    if ((paramInt1 == 0) && (paramInt2 == 0) && (paramInt3 == 0) && (paramInt4 == 0))
    {
      ServiceInterface.a(paramContext, "");
      x.b(z[0], z[32]);
      return;
    }
    if (ServiceInterface.a(paramContext, paramInt1, paramInt2, paramInt3, paramInt4))
    {
      x.b(z[0], z[31] + paramInt1 + z[35] + paramInt2 + z[34] + paramInt3 + z[35] + paramInt4);
      return;
    }
    x.e(z[0], z[33]);
  }

  public static void setStatisticsEnable(boolean paramBoolean)
  {
    c.a(paramBoolean);
  }

  public static void setStatisticsSessionTimeout(long paramLong)
  {
    if (paramLong < 10L)
    {
      x.d(z[0], z[49]);
      return;
    }
    if (paramLong > 86400L)
    {
      x.d(z[0], z[50]);
      return;
    }
    c.a(paramLong);
  }

  public static void setTags(Context paramContext, Set<String> paramSet, TagAliasCallback paramTagAliasCallback)
  {
    setAliasAndTags(paramContext, null, paramSet, paramTagAliasCallback);
  }

  public static void stopPush(Context paramContext)
  {
    x.b(z[0], z[7]);
    if (paramContext == null)
      throw new IllegalArgumentException(z[8]);
    ServiceInterface.a(paramContext, 1);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.JPushInterface
 * JD-Core Version:    0.6.2
 */