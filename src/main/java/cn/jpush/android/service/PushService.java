package cn.jpush.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.jpush.android.api.TagAliasCallback;
import cn.jpush.android.api.b;
import cn.jpush.android.c;
import cn.jpush.android.data.e;
import cn.jpush.android.util.JLogger;
import cn.jpush.android.util.JRecorder;
import cn.jpush.android.util.aa;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ae;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.aj;
import cn.jpush.android.util.q;
import cn.jpush.android.util.x;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PushService extends Service
{
  public static boolean A;
  public static String B;
  public static boolean C;
  public static boolean D;
  public static int E = 0;
  private static long K;
  private static long L;
  private static boolean M;
  private static boolean N;
  private static String O;
  private static boolean P;
  private static String Q;
  private static int R;
  private static String S;
  private static String T;
  private static int U;
  private static int V;
  private static String W;
  public static long a;
  private static boolean ac;
  private static boolean ad;
  private static int ae;
  private static boolean ag;
  private static List<String> ah;
  private static Queue<e> ar = new LinkedList();
  private static String as = null;
  private static String at = null;
  private static String au = null;
  public static boolean b;
  private static final String[] bb;
  public static boolean c;
  public static int d;
  public static long e;
  public static long f;
  public static long g;
  public static long h;
  public static int i;
  public static long j;
  public static long k;
  public static long l;
  public static long m;
  public static long n;
  public static String o;
  public static int p;
  public static String q;
  public static boolean r;
  public static boolean s;
  protected static long t;
  protected static String u;
  protected static String v;
  String F = "";
  String G = "";
  boolean H = false;
  InetAddress[] I = null;
  InetAddress J = null;
  private o X;
  private boolean Y;
  private boolean Z;
  private boolean aa = false;
  private boolean ab = true;
  private cn.jpush.android.a.d af;
  private int ai;
  private int aj;
  private long ak;
  private long al;
  private int am;
  private int an = -1;
  private int ao = -1;
  private boolean ap = false;
  private volatile boolean aq = false;
  private Handler av = new n(this);
  protected long w = 0L;
  protected byte[] x = new byte[2048];
  Queue<String> y = new LinkedList();
  Queue<String> z = new ConcurrentLinkedQueue();

  static
  {
    Object localObject2 = new String['ð'];
    int i2 = 0;
    Object localObject3 = "Z_";
    int i1 = -1;
    Object localObject1 = localObject2;
    int i3;
    int i6;
    int i4;
    int i9;
    Object localObject4;
    int i5;
    Object localObject5;
    int i7;
    while (true)
    {
      char[] arrayOfChar = ((String)localObject3).toCharArray();
      i3 = arrayOfChar.length;
      i6 = 0;
      i4 = 0;
      int i8 = i1;
      localObject3 = arrayOfChar;
      i9 = i2;
      localObject4 = localObject1;
      i5 = i3;
      if (i3 <= 1)
      {
        localObject5 = localObject1;
        localObject1 = arrayOfChar;
        i7 = i1;
      }
      label142: 
      do
      {
        i5 = i4;
        while (true)
        {
          localObject3 = localObject1;
          i6 = localObject3[i4];
          switch (i5 % 5)
          {
          default:
            i1 = 4;
            localObject3[i4] = ((char)(i1 ^ i6));
            i5 += 1;
            if (i3 != 0)
              break label142;
            i4 = i3;
          case 0:
          case 1:
          case 2:
          case 3:
          }
        }
        i6 = i5;
        i5 = i3;
        localObject4 = localObject5;
        i9 = i2;
        localObject3 = localObject1;
        i8 = i7;
        i7 = i8;
        localObject1 = localObject3;
        i2 = i9;
        localObject5 = localObject4;
        i3 = i5;
        i4 = i6;
      }
      while (i5 > i6);
      localObject1 = new String((char[])localObject3).intern();
      switch (i8)
      {
      default:
        localObject4[i9] = localObject1;
        i2 = 1;
        localObject3 = "\r_\007{p\027\021Q.!\022QQde\021\030Q.!\022QQga\030\031\026fm\022_I6!\005__6q\037\031Q.!\005QQda\004\020\032gw\037\022\035Kh\037\016\0076>S\016\016";
        i1 = 0;
        localObject1 = localObject2;
        break;
      case 0:
        localObject4[i9] = localObject1;
        i2 = 2;
        localObject3 = "Z]\001qpL";
        i1 = 1;
        localObject1 = localObject2;
        break;
      case 1:
        localObject4[i9] = localObject1;
        i2 = 3;
        localObject3 = "";
        i1 = 2;
        localObject1 = localObject2;
        break;
      case 2:
        localObject4[i9] = localObject1;
        i2 = 4;
        localObject3 = "";
        i1 = 3;
        localObject1 = localObject2;
        break;
      case 3:
        localObject4[i9] = localObject1;
        i2 = 5;
        localObject3 = "\031\0237}w\025\022\035za\025\t\026p";
        i1 = 4;
        localObject1 = localObject2;
        break;
      case 4:
        localObject4[i9] = localObject1;
        i2 = 6;
        localObject3 = "\031\0237}w\025\022\035za\025\t\026p$\027\023\0274v\023\t\001m$;.4KO38#KE:4%Q[82>UHV";
        i1 = 5;
        localObject1 = localObject2;
        break;
      case 5:
        localObject4[i9] = localObject1;
        i2 = 7;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:G93=QG\"4<Z";
        i1 = 6;
        localObject1 = localObject2;
        break;
      case 6:
        localObject4[i9] = localObject1;
        i2 = 8;
        localObject3 = "*Y/0";
        i1 = 7;
        localObject1 = localObject2;
        break;
      case 7:
        localObject4[i9] = localObject1;
        i2 = 9;
        localObject3 = "3\017\001{vV\t\022se\032\024\022g";
        i1 = 8;
        localObject1 = localObject2;
        break;
      case 8:
        localObject4[i9] = localObject1;
        i2 = 10;
        localObject3 = "";
        i1 = 9;
        localObject1 = localObject2;
        break;
      case 9:
        localObject4[i9] = localObject1;
        i2 = 11;
        localObject3 = "";
        i1 = 10;
        localObject1 = localObject2;
        break;
      case 10:
        localObject4[i9] = localObject1;
        i2 = 12;
        localObject3 = "\003\024\027)!\022";
        i1 = 11;
        localObject1 = localObject2;
        break;
      case 11:
        localObject4[i9] = localObject1;
        i2 = 13;
        localObject3 = "S\016]da\004\020\032gw\037\022\035:N&( \\[;8 GE18";
        i1 = 12;
        localObject1 = localObject2;
        break;
      case 12:
        localObject4[i9] = localObject1;
        i2 = 14;
        localObject3 = "\005\030\035pa\004]:P>V";
        i1 = 13;
        localObject1 = localObject2;
        break;
      case 13:
        localObject4[i9] = localObject1;
        i2 = 15;
        localObject3 = "\006\034\020e\021\030Sze\033\030I4";
        i1 = 14;
        localObject1 = localObject2;
        break;
      case 14:
        localObject4[i9] = localObject1;
        i2 = 16;
        localObject3 = "";
        i1 = 15;
        localObject1 = localObject2;
        break;
      case 15:
        localObject4[i9] = localObject1;
        i2 = 17;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S2DT=8*";
        i1 = 16;
        localObject1 = localObject2;
        break;
      case 16:
        localObject4[i9] = localObject1;
        i2 = 18;
        localObject3 = "";
        i1 = 17;
        localObject1 = localObject2;
        break;
      case 17:
        localObject4[i9] = localObject1;
        i2 = 19;
        localObject3 = "";
        i1 = 18;
        localObject1 = localObject2;
        break;
      case 18:
        localObject4[i9] = localObject1;
        i2 = 20;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:V3::GP3/";
        i1 = 19;
        localObject1 = localObject2;
        break;
      case 19:
        localObject4[i9] = localObject1;
        i2 = 21;
        localObject3 = "%\030\001bm\025\030Svq\030\031\037q$[]";
        i1 = 20;
        localObject1 = localObject2;
        break;
      case 20:
        localObject4[i9] = localObject1;
        i2 = 22;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:V3.'[V3-&GL";
        i1 = 21;
        localObject1 = localObject2;
        break;
      case 21:
        localObject4[i9] = localObject1;
        i2 = 23;
        localObject3 = "7\021\001qe\022\004SXk\021\032\026p$?\023";
        i1 = 22;
        localObject1 = localObject2;
        break;
      case 22:
        localObject4[i9] = localObject1;
        i2 = 24;
        localObject3 = "RY";
        i1 = 23;
        localObject1 = localObject2;
        break;
      case 23:
        localObject4[i9] = localObject1;
        i2 = 25;
        localObject3 = "\020\034\037ga";
        i1 = 24;
        localObject1 = localObject2;
        break;
      case 24:
        localObject4[i9] = localObject1;
        i2 = 26;
        localObject3 = "\037\016!qw\002\034\001`V\002\036";
        i1 = 25;
        localObject1 = localObject2;
        break;
      case 25:
        localObject4[i9] = localObject1;
        i2 = 27;
        localObject3 = "Z]";
        i1 = 26;
        localObject1 = localObject2;
        break;
      case 26:
        localObject4[i9] = localObject1;
        i2 = 28;
        localObject3 = "*\"";
        i1 = 27;
        localObject1 = localObject2;
        break;
      case 27:
        localObject4[i9] = localObject1;
        i2 = 29;
        localObject3 = "\020\022\001ce\004\031Sug\002\024\034z>V";
        i1 = 28;
        localObject1 = localObject2;
        break;
      case 28:
        localObject4[i9] = localObject1;
        i2 = 30;
        localObject3 = "\002\034\024g";
        i1 = 29;
        localObject1 = localObject2;
        break;
      case 29:
        localObject4[i9] = localObject1;
        i2 = 31;
        localObject3 = "\004\030\003{v\002";
        i1 = 30;
        localObject1 = localObject2;
        break;
      case 30:
        localObject4[i9] = localObject1;
        i2 = 32;
        localObject3 = "%\b\020wa\023\031S`kV\017\026dk\004\tS}j\020\022S9$";
        i1 = 31;
        localObject1 = localObject2;
        break;
      case 31:
        localObject4[i9] = localObject1;
        i2 = 33;
        localObject3 = "";
        i1 = 32;
        localObject1 = localObject2;
        break;
      case 32:
        localObject4[i9] = localObject1;
        i2 = 34;
        localObject3 = "\003\016\026f[\021\017\034aj\022";
        i1 = 33;
        localObject1 = localObject2;
        break;
      case 33:
        localObject4[i9] = localObject1;
        i2 = 35;
        localObject3 = "\004\t\020";
        i1 = 34;
        localObject1 = localObject2;
        break;
      case 34:
        localObject4[i9] = localObject1;
        i2 = 36;
        localObject3 = "\005\030\002Km\022";
        i1 = 35;
        localObject1 = localObject2;
        break;
      case 35:
        localObject4[i9] = localObject1;
        i2 = 37;
        localObject3 = "\027\036\007}k\030G\033uj\022\021\026]j\020\022!qt\031\017\0074)V";
        i1 = 36;
        localObject1 = localObject2;
        break;
      case 36:
        localObject4[i9] = localObject1;
        i2 = 38;
        localObject3 = "";
        i1 = 37;
        localObject1 = localObject2;
        break;
      case 37:
        localObject4[i9] = localObject1;
        i2 = 39;
        localObject3 = "";
        i1 = 38;
        localObject1 = localObject2;
        break;
      case 38:
        localObject4[i9] = localObject1;
        i2 = 40;
        localObject3 = "";
        i1 = 39;
        localObject1 = localObject2;
        break;
      case 39:
        localObject4[i9] = localObject1;
        i2 = 41;
        localObject3 = "\005\t\034d[\002\025\001qe\022";
        i1 = 40;
        localObject1 = localObject2;
        break;
      case 40:
        localObject4[i9] = localObject1;
        i2 = 42;
        localObject3 = "*P";
        i1 = 41;
        localObject1 = localObject2;
        break;
      case 41:
        localObject4[i9] = localObject1;
        i2 = 43;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:W\"2#DQ%5";
        i1 = 42;
        localObject1 = localObject2;
        break;
      case 42:
        localObject4[i9] = localObject1;
        i2 = 44;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:M84'";
        i1 = 43;
        localObject1 = localObject2;
        break;
      case 43:
        localObject4[i9] = localObject1;
        i2 = 45;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:Q%8!KC$2&Z@";
        i1 = 44;
        localObject1 = localObject2;
        break;
      case 44:
        localObject4[i9] = localObject1;
        i2 = 46;
        localObject3 = "\002\017\006q";
        i1 = 45;
        localObject1 = localObject2;
        break;
      case 45:
        localObject4[i9] = localObject1;
        i2 = 47;
        localObject3 = "\027\022\004";
        i1 = 46;
        localObject1 = localObject2;
        break;
      case 46:
        localObject4[i9] = localObject1;
        i2 = 48;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:T#.;KP?06";
        i1 = 47;
        localObject1 = localObject2;
        break;
      case 47:
        localObject4[i9] = localObject1;
        i2 = 49;
        localObject3 = "";
        i1 = 48;
        localObject1 = localObject2;
        break;
      case 48:
        localObject4[i9] = localObject1;
        i2 = 50;
        localObject3 = "";
        i1 = 49;
        localObject1 = localObject2;
        break;
      case 49:
        localObject4[i9] = localObject1;
        i2 = 51;
        localObject3 = "\036\034\035ph\023.\026`T\003\016\033@m\033\030Sgq\025\036\026gwVPS";
        i1 = 50;
        localObject1 = localObject2;
        break;
      case 50:
        localObject4[i9] = localObject1;
        i2 = 52;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:E:42G[\"<4G";
        i1 = 51;
        localObject1 = localObject2;
        break;
      case 51:
        localObject4[i9] = localObject1;
        i2 = 53;
        localObject3 = "\027\r\003";
        i1 = 52;
        localObject1 = localObject2;
        break;
      case 52:
        localObject4[i9] = localObject1;
        i2 = 54;
        localObject3 = "\032\022\020@}\006\030";
        i1 = 53;
        localObject1 = localObject2;
        break;
      case 53:
        localObject4[i9] = localObject1;
        i2 = 55;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:V3-<FP";
        i1 = 54;
        localObject1 = localObject2;
        break;
      case 54:
        localObject4[i9] = localObject1;
        i2 = 56;
        localObject3 = "\027\021\032uw";
        i1 = 55;
        localObject1 = localObject2;
        break;
      case 55:
        localObject4[i9] = localObject1;
        i2 = 57;
        localObject3 = "\004\t\020K`\023\021\022m";
        i1 = 56;
        localObject1 = localObject2;
        break;
      case 56:
        localObject4[i9] = localObject1;
        i2 = 58;
        localObject3 = "7\021\001qe\022\004SGa\002-\006gl\"\024\036q(V\032\032baV\b\0034)V";
        i1 = 57;
        localObject1 = localObject2;
        break;
      case 57:
        localObject4[i9] = localObject1;
        i2 = 59;
        localObject3 = "\033>\034zj\023\036\007}k\030]N4";
        i1 = 58;
        localObject1 = localObject2;
        break;
      case 58:
        localObject4[i9] = localObject1;
        i2 = 60;
        localObject3 = "即呰I4";
        i1 = 59;
        localObject1 = localObject2;
        break;
      case 59:
        localObject4[i9] = localObject1;
        i2 = 61;
        localObject3 = "&\017\026rw0\024\037q";
        i1 = 60;
        localObject1 = localObject2;
        break;
      case 60:
        localObject4[i9] = localObject1;
        i2 = 62;
        localObject3 = "\004\030\024}w\0024749V";
        i1 = 61;
        localObject1 = localObject2;
        break;
      case 61:
        localObject4[i9] = localObject1;
        i2 = 63;
        localObject3 = "\004\030\024}w\002\017\022`m\031\023,}`";
        i1 = 62;
        localObject1 = localObject2;
        break;
      case 62:
        localObject4[i9] = localObject1;
        i2 = 64;
        localObject3 = "\033/\026wr4\b\025ra\004]N4";
        i1 = 63;
        localObject1 = localObject2;
        break;
      case 63:
        localObject4[i9] = localObject1;
        i2 = 65;
        localObject3 = "乻卄鄾";
        i1 = 64;
        localObject1 = localObject2;
        break;
      case 64:
        localObject4[i9] = localObject1;
        i2 = 66;
        localObject3 = "\037\023\005uh?\030\036}$\020\017\034y$\005\030\001ba\004]X4";
        i1 = 65;
        localObject1 = localObject2;
        break;
      case 65:
        localObject4[i9] = localObject1;
        i2 = 67;
        localObject3 = "\004\030\024}w\002\030\0014w\003\036\020qa\022GSam\022@";
        i1 = 66;
        localObject1 = localObject2;
        break;
      case 66:
        localObject4[i9] = localObject1;
        i2 = 68;
        localObject3 = "\021\030\007Q|\0026\026m>V";
        i1 = 67;
        localObject1 = localObject2;
        break;
      case 67:
        localObject4[i9] = localObject1;
        i2 = 69;
        localObject3 = "V乳SUt\0066\026m>";
        i1 = 68;
        localObject1 = localObject2;
        break;
      case 68:
        localObject4[i9] = localObject1;
        i2 = 70;
        localObject3 = "\004\030\024}w\002\030\001K`\023\013\032wa)\020\022w";
        i1 = 69;
        localObject1 = localObject2;
        break;
      case 69:
        localObject4[i9] = localObject1;
        i2 = 71;
        localObject3 = "$\030\024}w\002\030\0014c\023\tSu$\023\017\001{vV\036\034yi\027\023\0274";
        i1 = 70;
        localObject1 = localObject2;
        break;
      case 70:
        localObject4[i9] = localObject1;
        i2 = 72;
        localObject3 = "\004\030\024}w\002\030\001K`\023\013\032wa)\034\035pv\031\024\027Km\022";
        i1 = 71;
        localObject1 = localObject2;
        break;
      case 71:
        localObject4[i9] = localObject1;
        i2 = 73;
        localObject3 = "$\030\024}w\002\030\0014V\023\036\0054b\027\024\037q`VPSfa\002G";
        i1 = 72;
        localObject1 = localObject2;
        break;
      case 72:
        localObject4[i9] = localObject1;
        i2 = 74;
        localObject3 = "\006\025\034za";
        i1 = 73;
        localObject1 = localObject2;
        break;
      case 73:
        localObject4[i9] = localObject1;
        i2 = 75;
        localObject3 = "\0334\035`a\004\013\022x";
        i1 = 74;
        localObject1 = localObject2;
        break;
      case 74:
        localObject4[i9] = localObject1;
        i2 = 76;
        localObject3 = "$\030\024}w\002\030\001.$";
        i1 = 75;
        localObject1 = localObject2;
        break;
      case 75:
        localObject4[i9] = localObject1;
        i2 = 77;
        localObject3 = "\004\030\024}w\002\030\001K`\023\013\032wa)\024\036qm";
        i1 = 76;
        localObject1 = localObject2;
        break;
      case 76:
        localObject4[i9] = localObject1;
        i2 = 78;
        localObject3 = "\023\017\001{vV\031\026gg\004\024\003`m\031\023I\036";
        i1 = 77;
        localObject1 = localObject2;
        break;
      case 77:
        localObject4[i9] = localObject1;
        i2 = 79;
        localObject3 = "<-\006glV\b\003pe\002\030Swk\030\033\032s$\020\034\032x>";
        i1 = 78;
        localObject1 = localObject2;
        break;
      case 78:
        localObject4[i9] = localObject1;
        i2 = 80;
        localObject3 = "GSK:6";
        i1 = 79;
        localObject1 = localObject2;
        break;
      case 79:
        localObject4[i9] = localObject1;
        i2 = 81;
        localObject3 = "\006\026Sba\004\016\032{jL]";
        i1 = 80;
        localObject1 = localObject2;
        break;
      case 80:
        localObject4[i9] = localObject1;
        i2 = 82;
        localObject3 = "";
        i1 = 81;
        localObject1 = localObject2;
        break;
      case 81:
        localObject4[i9] = localObject1;
        i2 = 83;
        localObject3 = "\002\022\007{h:\030\03549V";
        i1 = 82;
        localObject1 = localObject2;
        break;
      case 82:
        localObject4[i9] = localObject1;
        i2 = 84;
        localObject3 = "\027\023\027fk\037\031]da\004\020\032gw\037\022\035:V3<7KT>2=Q[%)2@A";
        i1 = 83;
        localObject1 = localObject2;
        break;
      case 83:
        localObject4[i9] = localObject1;
        i2 = 85;
        localObject3 = "<-&GL)<#DO3$";
        i1 = 84;
        localObject1 = localObject2;
        break;
      case 84:
        localObject4[i9] = localObject1;
        i2 = 86;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:V3::GP$<']K8";
        i1 = 85;
        localObject1 = localObject2;
        break;
      case 85:
        localObject4[i9] = localObject1;
        i2 = 87;
        localObject3 = "\022\030\001bm\0234\02749V";
        i1 = 86;
        localObject1 = localObject2;
        break;
      case 86:
        localObject4[i9] = localObject1;
        i2 = 88;
        localObject3 = "5\021\032qj\002]\032zb\031GS";
        i1 = 87;
        localObject1 = localObject2;
        break;
      case 87:
        localObject4[i9] = localObject1;
        i2 = 89;
        localObject3 = "$\030\024}w\002\030\0014B\027\024\037q`V\n\032`lV\030\001fk\004]\020{`\023GS";
        i1 = 88;
        localObject1 = localObject2;
        break;
      case 88:
        localObject4[i9] = localObject1;
        i2 = 90;
        localObject3 = "V]S4t\031\017\007.";
        i1 = 89;
        localObject1 = localObject2;
        break;
      case 89:
        localObject4[i9] = localObject1;
        i2 = 91;
        localObject3 = "#\016\0264W?.Sdk\004\tI4";
        i1 = 90;
        localObject1 = localObject2;
        break;
      case 90:
        localObject4[i9] = localObject1;
        i2 = 92;
        localObject3 = "1\030\0074w\023\017\005qvV\024\0034a\004\017\034f$\001\024\007|>V";
        i1 = 91;
        localObject1 = localObject2;
        break;
      case 91:
        localObject4[i9] = localObject1;
        i2 = 93;
        localObject3 = "3\005\020qt\002\024\034z$\001\025\026z$\025\021\034gaV\b\027d$\005\022\020a\002]^4";
        i1 = 92;
        localObject1 = localObject2;
        break;
      case 92:
        localObject4[i9] = localObject1;
        i2 = 94;
        localObject3 = "#\016\0264$\006\022\001`>V";
        i1 = 93;
        localObject1 = localObject2;
        break;
      case 93:
        localObject4[i9] = localObject1;
        i2 = 95;
        localObject3 = "";
        i1 = 94;
        localObject1 = localObject2;
        break;
      case 94:
        localObject4[i9] = localObject1;
        i2 = 96;
        localObject3 = "\034\r\006gl)\016\032g[\030\030\007`}\006\030";
        i1 = 95;
        localObject1 = localObject2;
        break;
      case 95:
        localObject4[i9] = localObject1;
        i2 = 97;
        localObject3 = "";
        i1 = 96;
        localObject1 = localObject2;
        break;
      case 96:
        localObject4[i9] = localObject1;
        i2 = 98;
        localObject3 = "#\023\030zk\001\023";
        i1 = 97;
        localObject1 = localObject2;
        break;
      case 97:
        localObject4[i9] = localObject1;
        i2 = 99;
        localObject3 = "1\030\0074p\025\rSpe\002\034Srv\031\020SPFL]";
        i1 = 98;
        localObject1 = localObject2;
        break;
      case 98:
        localObject4[i9] = localObject1;
        i2 = 100;
        localObject3 = "\002\036\003K`\027\t\022";
        i1 = 99;
        localObject1 = localObject2;
        break;
      case 99:
        localObject4[i9] = localObject1;
        i2 = 101;
        localObject3 = "1\030\0074$\002\036\0034`\027\t\0224b\004\022\0364@4QS`l\023]\020{q\030\tS}wL]";
        i1 = 100;
        localObject1 = localObject2;
        break;
      case 100:
        localObject4[i9] = localObject1;
        i2 = 102;
        localObject3 = "\004\030\003Km\022";
        i1 = 101;
        localObject1 = localObject2;
        break;
      case 101:
        localObject4[i9] = localObject1;
        i2 = 103;
        localObject3 = "\027\r\0034t\027\036\030uc\023]\035ui\023]\037qj\021\t\03349V]";
        i1 = 102;
        localObject1 = localObject2;
        break;
      case 102:
        localObject4[i9] = localObject1;
        i2 = 104;
        localObject3 = "\004\030\024}w\002\017\022`m\031\023Sre\037\021\026p>VX\027";
        i1 = 103;
        localObject1 = localObject2;
        break;
      case 103:
        localObject4[i9] = localObject1;
        i2 = 105;
        localObject3 = "\004\030\024}w\002\017\022`m\031\023S]@L]";
        i1 = 104;
        localObject1 = localObject2;
        break;
      case 104:
        localObject4[i9] = localObject1;
        i2 = 106;
        localObject3 = "SMAL";
        i1 = 105;
        localObject1 = localObject2;
        break;
      case 105:
        localObject4[i9] = localObject1;
        i2 = 107;
        localObject3 = "";
        i1 = 106;
        localObject1 = localObject2;
        break;
      case 106:
        localObject4[i9] = localObject1;
        i2 = 108;
        localObject3 = "\006\017\034wa\005\0161uw\037\0366zp\037\t\n4p\017\r\026.";
        i1 = 107;
        localObject1 = localObject2;
        break;
      case 107:
        localObject4[i9] = localObject1;
        i2 = 109;
        localObject3 = "";
        i1 = 108;
        localObject1 = localObject2;
        break;
      case 108:
        localObject4[i9] = localObject1;
        i2 = 110;
        localObject3 = "Z]\036gc5\022\035`a\030\tI";
        i1 = 109;
        localObject1 = localObject2;
        break;
      case 109:
        localObject4[i9] = localObject1;
        i2 = 111;
        localObject3 = "#\023\026lt\023\036\007q`L]\006zo\030\022\004z$\033\016\0244p\017\r\0264)";
        i1 = 110;
        localObject1 = localObject2;
        break;
      case 110:
        localObject4[i9] = localObject1;
        i2 = 112;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S=[P?;:WE\"4<Z[\"$#Q";
        i1 = 111;
        localObject1 = localObject2;
        break;
      case 111:
        localObject4[i9] = localObject1;
        i2 = 113;
        localObject3 = "\f\024\003Rh\027\032S}wVGS";
        i1 = 112;
        localObject1 = localObject2;
        break;
      case 112:
        localObject4[i9] = localObject1;
        i2 = 114;
        localObject3 = "";
        i1 = 113;
        localObject1 = localObject2;
        break;
      case 113:
        localObject4[i9] = localObject1;
        i2 = 115;
        localObject3 = "\"\024\036q$\002\022Sdv\031\036\026gwV\017\026wa\037\013\026p$\033\016\024:";
        i1 = 114;
        localObject1 = localObject2;
        break;
      case 114:
        localObject4[i9] = localObject1;
        i2 = 116;
        localObject3 = "0\034\032xa\022]\007{$\004\030\003{v\002]\001qg\023\024\005q`VPS";
        i1 = 115;
        localObject1 = localObject2;
        break;
      case 115:
        localObject4[i9] = localObject1;
        i2 = 117;
        localObject3 = "";
        i1 = 116;
        localObject1 = localObject2;
        break;
      case 116:
        localObject4[i9] = localObject1;
        i2 = 118;
        localObject3 = "\033\016\024@}\006\030S)$";
        i1 = 117;
        localObject1 = localObject2;
        break;
      case 117:
        localObject4[i9] = localObject1;
        i2 = 119;
        localObject3 = "\033\016\024Km\022";
        i1 = 118;
        localObject1 = localObject2;
        break;
      case 118:
        localObject4[i9] = localObject1;
        i2 = 120;
        localObject3 = "\027\r\003]`";
        i1 = 119;
        localObject1 = localObject2;
        break;
      case 119:
        localObject4[i9] = localObject1;
        i2 = 121;
        localObject3 = "%\b\020wa\023\031S`kV\017\026dk\004\tSfa\025\030\032ba\022]^4";
        i1 = 120;
        localObject1 = localObject2;
        break;
      case 120:
        localObject4[i9] = localObject1;
        i2 = 122;
        localObject3 = "\005\030\035pa\0044\027";
        i1 = 121;
        localObject1 = localObject2;
        break;
      case 121:
        localObject4[i9] = localObject1;
        i2 = 123;
        localObject3 = "Z]\036gc?\031S)$";
        i1 = 122;
        localObject1 = localObject2;
        break;
      case 122:
        localObject4[i9] = localObject1;
        i2 = 124;
        localObject3 = "";
        i1 = 123;
        localObject1 = localObject2;
        break;
      case 123:
        localObject4[i9] = localObject1;
        i2 = 125;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:J9):RM5<']K8\"!QG34%Q@)-![\\/";
        i1 = 124;
        localObject1 = localObject2;
        break;
      case 124:
        localObject4[i9] = localObject1;
        i2 = 126;
        localObject3 = "#\023\030zk\001\023Swi\022]^4";
        i1 = 125;
        localObject1 = localObject2;
        break;
      case 125:
        localObject4[i9] = localObject1;
        i2 = 127;
        localObject3 = "\027\036\007}k\030G\034zV\023\036\005Wk\033\020\022z`L]";
        i1 = 126;
        localObject1 = localObject2;
        break;
      case 126:
        localObject4[i9] = localObject1;
        i2 = 128;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S\032zp\023\023\007:Q8/6SM%)!UP?2=";
        i1 = 127;
        localObject1 = localObject2;
        break;
      case 127:
        localObject4[i9] = localObject1;
        i2 = 129;
        localObject3 = "$\030\027at\032\024\020up\023]\036gcVPS";
        i1 = 128;
        localObject1 = localObject2;
        break;
      case 128:
        localObject4[i9] = localObject1;
        i2 = 130;
        localObject3 = "A\033\026r2\027J\027#2\025JK&fG\033Cq`\027IG\"fD\036Ew0F\034";
        i1 = 129;
        localObject1 = localObject2;
        break;
      case 129:
        localObject4[i9] = localObject1;
        i2 = 131;
        localObject3 = "";
        i1 = 130;
        localObject1 = localObject2;
        break;
      case 130:
        localObject4[i9] = localObject1;
        i2 = 132;
        localObject3 = "";
        i1 = 131;
        localObject1 = localObject2;
        break;
      case 131:
        localObject4[i9] = localObject1;
        i2 = 133;
        localObject3 = "\"\025\0264t\027\036\030uc\023]\037qj\021\025\0074m\005]I4";
        i1 = 132;
        localObject1 = localObject2;
        break;
      case 132:
        localObject4[i9] = localObject1;
        i2 = 134;
        localObject3 = "\005\016\034{";
        i1 = 133;
        localObject1 = localObject2;
        break;
      case 133:
        localObject4[i9] = localObject1;
        i2 = 135;
        localObject3 = "\003\016\003";
        i1 = 134;
        localObject1 = localObject2;
        break;
      case 134:
        localObject4[i9] = localObject1;
        i2 = 136;
        localObject3 = "\033.\007uv\0022\035Aw\023\017#fa\005\030\035`";
        i1 = 135;
        localObject1 = localObject2;
        break;
      case 135:
        localObject4[i9] = localObject1;
        i2 = 137;
        localObject3 = "#\r\027up\023]\001`gV\024\035`a\004\013\022x$\002\022S";
        i1 = 136;
        localObject1 = localObject2;
        break;
      case 136:
        localObject4[i9] = localObject1;
        i2 = 138;
        localObject3 = "%\030\0074W\"<!@[%8!BM58,[J)2#QJV\t\0344";
        i1 = 137;
        localObject1 = localObject2;
        break;
      case 137:
        localObject4[i9] = localObject1;
        i2 = 139;
        localObject3 = "%\030\0074i%\t\022fp9\023=qp\001\022\001G\031\023\035qg\002\030\0274p\031]";
        i1 = 138;
        localObject1 = localObject2;
        break;
      case 138:
        localObject4[i9] = localObject1;
        i2 = 140;
        localObject3 = "\005\023\020";
        i1 = 139;
        localObject1 = localObject2;
        break;
      case 139:
        localObject4[i9] = localObject1;
        i2 = 141;
        localObject3 = "%\030\0074i%\t\022fp9\023&ga\004-\001qw\023\023\0074p\031]";
        i1 = 140;
        localObject1 = localObject2;
        break;
      case 140:
        localObject4[i9] = localObject1;
        i2 = 142;
        localObject3 = "\023\005\032`";
        i1 = 141;
        localObject1 = localObject2;
        break;
      case 141:
        localObject4[i9] = localObject1;
        i2 = 143;
        localObject3 = "#;";
        i1 = 142;
        localObject1 = localObject2;
        break;
      case 142:
        localObject4[i9] = localObject1;
        i2 = 144;
        localObject3 = "[P^9)[";
        i1 = 143;
        localObject1 = localObject2;
        break;
      case 143:
        localObject4[i9] = localObject1;
        i2 = 145;
        localObject3 = "\032\036\007}i\023";
        i1 = 144;
        localObject1 = localObject2;
        break;
      case 144:
        localObject4[i9] = localObject1;
        i2 = 146;
        localObject3 = "";
        i1 = 145;
        localObject1 = localObject2;
        break;
      case 145:
        localObject4[i9] = localObject1;
        i2 = 147;
        localObject3 = "斀閉ｩ4";
        i1 = 146;
        localObject1 = localObject2;
        break;
      case 146:
        localObject4[i9] = localObject1;
        i2 = 148;
        localObject3 = "\022\030\005Km\030\033\034Kv\023\r,`m\033\030";
        i1 = 147;
        localObject1 = localObject2;
        break;
      case 147:
        localObject4[i9] = localObject1;
        i2 = 149;
        localObject3 = "\033)\033fa\027\031S}wV\023\006xhZ]\001qw\002\034\001`$\002\025\0264p\036\017\026u`Z]\032p$K]";
        i1 = 148;
        localObject1 = localObject2;
        break;
      case 148:
        localObject4[i9] = localObject1;
        i2 = 150;
        localObject3 = "";
        i1 = 149;
        localObject1 = localObject2;
        break;
      case 149:
        localObject4[i9] = localObject1;
        i2 = 151;
        localObject3 = "{w";
        i1 = 150;
        localObject1 = localObject2;
        break;
      case 150:
        localObject4[i9] = localObject1;
        i2 = 152;
        localObject3 = "3\023\022vh\023]0|e\030\023\026x$\005\b\020wa\005\016I4";
        i1 = 151;
        localObject1 = localObject2;
        break;
      case 151:
        localObject4[i9] = localObject1;
        i2 = 153;
        localObject3 = "3\023\022vh\023>\033uj\030\030\037Fa\007\b\026gpV\034\003d(V\016\026z`\023\017Sqv\004\022\001.$S\016S1w";
        i1 = 152;
        localObject1 = localObject2;
        break;
      case 152:
        localObject4[i9] = localObject1;
        i2 = 154;
        localObject3 = "\006\021\022`b\031\017\036";
        i1 = 153;
        localObject1 = localObject2;
        break;
      case 153:
        localObject4[i9] = localObject1;
        i2 = 155;
        localObject3 = "";
        i1 = 154;
        localObject1 = localObject2;
        break;
      case 154:
        localObject4[i9] = localObject1;
        i2 = 156;
        localObject3 = "";
        i1 = 155;
        localObject1 = localObject2;
        break;
      case 155:
        localObject4[i9] = localObject1;
        i2 = 157;
        localObject3 = "";
        i1 = 156;
        localObject1 = localObject2;
        break;
      case 156:
        localObject4[i9] = localObject1;
        i2 = 158;
        localObject3 = "\027\r\003}`";
        i1 = 157;
        localObject1 = localObject2;
        break;
      case 157:
        localObject4[i9] = localObject1;
        i2 = 159;
        localObject3 = "?\023\005uh\037\031Sga\002]\003aw\036)\032yaV\036\034yi\027\023\027.$";
        i1 = 158;
        localObject1 = localObject2;
        break;
      case 158:
        localObject4[i9] = localObject1;
        i2 = 160;
        localObject3 = "0\034\032xa\022]\007{$\004\030\003{v\002]\032zb\031]^4v\023\tI";
        i1 = 159;
        localObject1 = localObject2;
        break;
      case 159:
        localObject4[i9] = localObject1;
        i2 = 161;
        localObject3 = "?\023\005uh\037\031Sga\002)\022sw7\023\027Um\027\016Swk\033\020\022z`L]";
        i1 = 160;
        localObject1 = localObject2;
        break;
      case 160:
        localObject4[i9] = localObject1;
        i2 = 162;
        localObject3 = "";
        i1 = 161;
        localObject1 = localObject2;
        break;
      case 161:
        localObject4[i9] = localObject1;
        i2 = 163;
        localObject3 = "\005\t\034dT\003\016\033";
        i1 = 162;
        localObject1 = localObject2;
        break;
      case 162:
        localObject4[i9] = localObject1;
        i2 = 164;
        localObject3 = "?\023\005uh\037\031SPa5\025\022zj\023\021Swk\033\020\022z`L]";
        i1 = 163;
        localObject1 = localObject2;
        break;
      case 163:
        localObject4[i9] = localObject1;
        i2 = 165;
        localObject3 = "\002\034\024gE\030\0312xm\027\016";
        i1 = 164;
        localObject1 = localObject2;
        break;
      case 164:
        localObject4[i9] = localObject1;
        i2 = 166;
        localObject3 = "VGS";
        i1 = 165;
        localObject1 = localObject2;
        break;
      case 165:
        localObject4[i9] = localObject1;
        i2 = 167;
        localObject3 = "";
        i1 = 166;
        localObject1 = localObject2;
        break;
      case 166:
        localObject4[i9] = localObject1;
        i2 = 168;
        localObject3 = "?\023\005uh\037\031SQj\027\037\037qG\036\034\035za\032]\020{i\033\034\035p>V";
        i1 = 167;
        localObject1 = localObject2;
        break;
      case 167:
        localObject4[i9] = localObject1;
        i2 = 169;
        localObject3 = "";
        i1 = 168;
        localObject1 = localObject2;
        break;
      case 168:
        localObject4[i9] = localObject1;
        i2 = 170;
        localObject3 = "\005\030\035pa\004\024\027";
        i1 = 169;
        localObject1 = localObject2;
        break;
      case 169:
        localObject4[i9] = localObject1;
        i2 = 171;
        localObject3 = "";
        i1 = 170;
        localObject1 = localObject2;
        break;
      case 170:
        localObject4[i9] = localObject1;
        i2 = 172;
        localObject3 = "\033;\032fw\0021\034sc\023\031:z";
        i1 = 171;
        localObject1 = localObject2;
        break;
      case 171:
        localObject4[i9] = localObject1;
        i2 = 173;
        localObject3 = ">\034\035ph\037\023\0244v\023\f\006qw\002GS";
        i1 = 172;
        localObject1 = localObject2;
        break;
      case 172:
        localObject4[i9] = localObject1;
        i2 = 174;
        localObject3 = "0\034\032xa\022]\007{$\005\t\034dT\003\016\0334)V\017\026`>V";
        i1 = 173;
        localObject1 = localObject2;
        break;
      case 173:
        localObject4[i9] = localObject1;
        i2 = 175;
        localObject3 = "0\034\032xa\022]\007{$\005\030\0074t\003\016\033@m\033\030S9$\004\030\007.$";
        i1 = 174;
        localObject1 = localObject2;
        break;
      case 174:
        localObject4[i9] = localObject1;
        i2 = 176;
        localObject3 = "0\034\032xa\022]\007{$3\023\022vh\023>\033uj\030\030\0374)V\017\026`>V";
        i1 = 175;
        localObject1 = localObject2;
        break;
      case 175:
        localObject4[i9] = localObject1;
        i2 = 177;
        localObject3 = "\023\023\022vh\023>\033uj\030\030\037";
        i1 = 176;
        localObject1 = localObject2;
        break;
      case 176:
        localObject4[i9] = localObject1;
        i2 = 178;
        localObject3 = "[P^";
        i1 = 177;
        localObject1 = localObject2;
        break;
      case 177:
        localObject4[i9] = localObject1;
        i2 = 179;
        localObject3 = "";
        i1 = 178;
        localObject1 = localObject2;
        break;
      case 178:
        localObject4[i9] = localObject1;
        i2 = 180;
        localObject3 = "%\b\020wa\023\031S`kV8\035uf\032\0300|e\030\023\026x$[]";
        i1 = 179;
        localObject1 = localObject2;
        break;
      case 179:
        localObject4[i9] = localObject1;
        i2 = 181;
        localObject3 = "23 .$[CS";
        i1 = 180;
        localObject1 = localObject2;
        break;
      case 180:
        localObject4[i9] = localObject1;
        i2 = 182;
        localObject3 = "?\023\032`$\001\024\007|$[]\036Pa\020\034\006xp?-S)$";
        i1 = 181;
        localObject1 = localObject2;
        break;
      case 181:
        localObject4[i9] = localObject1;
        i2 = 183;
        localObject3 = "V]S4$V\020;{w\002GS9:V";
        i1 = 182;
        localObject1 = localObject2;
        break;
      case 182:
        localObject4[i9] = localObject1;
        i2 = 184;
        localObject3 = "?\023\032`$\001\024\007|$[]\032dwV\033\032fw\002]I4";
        i1 = 183;
        localObject1 = localObject2;
        break;
      case 183:
        localObject4[i9] = localObject1;
        i2 = 185;
        localObject3 = "\033-\034fp";
        i1 = 184;
        localObject1 = localObject2;
        break;
      case 184:
        localObject4[i9] = localObject1;
        i2 = 186;
        localObject3 = "\034\r\006gl)\036\034zj)\020\032d";
        i1 = 185;
        localObject1 = localObject2;
        break;
      case 185:
        localObject4[i9] = localObject1;
        i2 = 187;
        localObject3 = "V\020#{v\002]N4";
        i1 = 186;
        localObject1 = localObject2;
        break;
      case 186:
        localObject4[i9] = localObject1;
        i2 = 188;
        localObject3 = "\0334#";
        i1 = 187;
        localObject1 = localObject2;
        break;
      case 187:
        localObject4[i9] = localObject1;
        i2 = 189;
        localObject3 = "?\023\032`$\001\024\007|$[]\034d[\025\022\035zwVGS";
        i1 = 188;
        localObject1 = localObject2;
        break;
      case 188:
        localObject4[i9] = localObject1;
        i2 = 190;
        localObject3 = "\034\r\006gl)\036\034zj)\020\003{v\002";
        i1 = 189;
        localObject1 = localObject2;
        break;
      case 189:
        localObject4[i9] = localObject1;
        i2 = 191;
        localObject3 = "V8\013wa\006\t\032{jVGS]j\037\tScm\002\025S9$\0335\034gpV@S";
        i1 = 190;
        localObject1 = localObject2;
        break;
      case 190:
        localObject4[i9] = localObject1;
        i2 = 192;
        localObject3 = "V\0207qb\027\b\037qT\031\017\00749V";
        i1 = 191;
        localObject1 = localObject2;
        break;
      case 191:
        localObject4[i9] = localObject1;
        i2 = 193;
        localObject3 = "";
        i1 = 192;
        localObject1 = localObject2;
        break;
      case 192:
        localObject4[i9] = localObject1;
        i2 = 194;
        localObject3 = "V]S4v\023\r\034fp\"\024\036q$K]";
        i1 = 193;
        localObject1 = localObject2;
        break;
      case 193:
        localObject4[i9] = localObject1;
        i2 = 195;
        localObject3 = "\005\030\001ba\004)\032yaV@S";
        i1 = 194;
        localObject1 = localObject2;
        break;
      case 194:
        localObject4[i9] = localObject1;
        i2 = 196;
        localObject3 = "\032\022\024}j)\016\026fr\023\017,`m\033\030";
        i1 = 195;
        localObject1 = localObject2;
        break;
      case 195:
        localObject4[i9] = localObject1;
        i2 = 197;
        localObject3 = "\031\023!qg7\036\030.$\005\030\002]`V@S";
        i1 = 196;
        localObject1 = localObject2;
        break;
      case 196:
        localObject4[i9] = localObject1;
        i2 = 198;
        localObject3 = "\032\022\024}j)\017\026dk\004\t,`m\033\030";
        i1 = 197;
        localObject1 = localObject2;
        break;
      case 197:
        localObject4[i9] = localObject1;
        i2 = 199;
        localObject3 = "\002\034\024gE\030\0312xm\027\016W0";
        i1 = 198;
        localObject1 = localObject2;
        break;
      case 198:
        localObject4[i9] = localObject1;
        i2 = 200;
        localObject3 = "";
        i1 = 199;
        localObject1 = localObject2;
        break;
      case 199:
        localObject4[i9] = localObject1;
        i2 = 201;
        localObject3 = "%\030\0074`\023\033\022ah\0024#49V";
        i1 = 200;
        localObject1 = localObject2;
        break;
      case 200:
        localObject4[i9] = localObject1;
        i2 = 202;
        localObject3 = "";
        i1 = 201;
        localObject1 = localObject2;
        break;
      case 201:
        localObject4[i9] = localObject1;
        i2 = 203;
        localObject3 = "";
        i1 = 202;
        localObject1 = localObject2;
        break;
      case 202:
        localObject4[i9] = localObject1;
        i2 = 204;
        localObject3 = "%4 4W\003\036\020qw\005";
        i1 = 203;
        localObject1 = localObject2;
        break;
      case 203:
        localObject4[i9] = localObject1;
        i2 = 205;
        localObject3 = "";
        i1 = 204;
        localObject1 = localObject2;
        break;
      case 204:
        localObject4[i9] = localObject1;
        i2 = 206;
        localObject3 = "%\030\0074i2\030\025uq\032\t#{v\002]N4";
        i1 = 205;
        localObject1 = localObject2;
        break;
      case 205:
        localObject4[i9] = localObject1;
        i2 = 207;
        localObject3 = "";
        i1 = 206;
        localObject1 = localObject2;
        break;
      case 206:
        localObject4[i9] = localObject1;
        i2 = 208;
        localObject3 = "";
        i1 = 207;
        localObject1 = localObject2;
        break;
      case 207:
        localObject4[i9] = localObject1;
        i2 = 209;
        localObject3 = "%4 4V\023\036\026}r\023\031SGp\004\024\035s>V";
        i1 = 208;
        localObject1 = localObject2;
        break;
      case 208:
        localObject4[i9] = localObject1;
        i2 = 210;
        localObject3 = "\"\025\0264w\023\017\005}g\023]\004}h\032]\030}h\032]\032`w\023\021\0254m\030]";
        i1 = 209;
        localObject1 = localObject2;
        break;
      case 209:
        localObject4[i9] = localObject1;
        i2 = 211;
        localObject3 = "\023\017\001{v\005";
        i1 = 210;
        localObject1 = localObject2;
        break;
      case 210:
        localObject4[i9] = localObject1;
        i2 = 212;
        localObject3 = "\037\0162zw\001\030\001";
        i1 = 211;
        localObject1 = localObject2;
        break;
      case 211:
        localObject4[i9] = localObject1;
        i2 = 213;
        localObject3 = "\005\030\001ba\004]\020{j\020\024\0244w\003\036\020qw\005";
        i1 = 212;
        localObject1 = localObject2;
        break;
      case 212:
        localObject4[i9] = localObject1;
        i2 = 214;
        localObject3 = "\004\t\0204m\030]";
        i1 = 213;
        localObject1 = localObject2;
        break;
      case 213:
        localObject4[i9] = localObject1;
        i2 = 215;
        localObject3 = "V\021\022`a\004";
        i1 = 214;
        localObject1 = localObject2;
        break;
      case 214:
        localObject4[i9] = localObject1;
        i2 = 216;
        localObject3 = "\033)\033fa\027\031S}wV\034\037}r\023]\032p$K]";
        i1 = 215;
        localObject1 = localObject2;
        break;
      case 215:
        localObject4[i9] = localObject1;
        i2 = 217;
        localObject3 = "\005\030\001bm\025\030,wk\030\023\026wa\002";
        i1 = 216;
        localObject1 = localObject2;
        break;
      case 216:
        localObject4[i9] = localObject1;
        i2 = 218;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S0[J880@M93,WL734Q";
        i1 = 217;
        localObject1 = localObject2;
        break;
      case 217:
        localObject4[i9] = localObject1;
        i2 = 219;
        localObject3 = "";
        i1 = 218;
        localObject1 = localObject2;
        break;
      case 218:
        localObject4[i9] = localObject1;
        i2 = 220;
        localObject3 = "\025\023]~t\003\016\033:e\030\031\001{m\022S!QC?.'FE\"4<Z[?9";
        i1 = 219;
        localObject1 = localObject2;
        break;
      case 219:
        localObject4[i9] = localObject1;
        i2 = 221;
        localObject3 = "\005\t\034dT\003\016\0338";
        i1 = 220;
        localObject1 = localObject2;
        break;
      case 220:
        localObject4[i9] = localObject1;
        i2 = 222;
        localObject3 = "\027\021\022fi";
        i1 = 221;
        localObject1 = localObject2;
        break;
      case 221:
        localObject4[i9] = localObject1;
        i2 = 223;
        localObject3 = "7\021\022fiV\016\007uv\002\030\0274s\037\t\0334m\030\t\026fr\027\021I4!\022\016";
        i1 = 222;
        localObject1 = localObject2;
        break;
      case 222:
        localObject4[i9] = localObject1;
        i2 = 224;
        localObject3 = "\005\t\022fV\002\036Scm\002\025SyM\030\t\026fr\027\021S)$";
        i1 = 223;
        localObject1 = localObject2;
        break;
      case 223:
        localObject4[i9] = localObject1;
        i2 = 225;
        localObject3 = "\023\023\022vh\023>\033uj\030\030\0378";
        i1 = 224;
        localObject1 = localObject2;
        break;
      case 224:
        localObject4[i9] = localObject1;
        i2 = 226;
        localObject3 = "";
        i1 = 225;
        localObject1 = localObject2;
        break;
      case 225:
        localObject4[i9] = localObject1;
        i2 = 227;
        localObject3 = "";
        i1 = 226;
        localObject1 = localObject2;
        break;
      case 226:
        localObject4[i9] = localObject1;
        i2 = 228;
        localObject3 = "\037\016?{g\027\t\032{j3\023\022vh\023\031";
        i1 = 227;
        localObject1 = localObject2;
        break;
      case 227:
        localObject4[i9] = localObject1;
        i2 = 229;
        localObject3 = "\027\021\037";
        i1 = 228;
        localObject1 = localObject2;
        break;
      case 228:
        localObject4[i9] = localObject1;
        i2 = 230;
        localObject3 = "\001\024\025}[\002\022\004qv\005";
        i1 = 229;
        localObject1 = localObject2;
        break;
      case 229:
        localObject4[i9] = localObject1;
        i2 = 231;
        localObject3 = "\025\022\037@}\006\030";
        i1 = 230;
        localObject1 = localObject2;
        break;
      case 230:
        localObject4[i9] = localObject1;
        i2 = 232;
        localObject3 = "\025\030\037x[\002\022\004qv\005";
        i1 = 231;
        localObject1 = localObject2;
        break;
      case 231:
        localObject4[i9] = localObject1;
        i2 = 233;
        localObject3 = "\005\022\020a\002]\021qb\031\017\0264>V";
        i1 = 232;
        localObject1 = localObject2;
        break;
      case 232:
        localObject4[i9] = localObject1;
        i2 = 234;
        localObject3 = "\005\022\020a\002]\026z`V]S4>V";
        i1 = 233;
        localObject1 = localObject2;
        break;
      case 233:
        localObject4[i9] = localObject1;
        i2 = 235;
        localObject3 = "\030\b\037x$\025\034\037xf\027\036\0304$[]";
        i1 = 234;
        localObject1 = localObject2;
        break;
      case 234:
        localObject4[i9] = localObject1;
        i2 = 236;
        localObject3 = "\025\022\027q";
        i1 = 235;
        localObject1 = localObject2;
        break;
      case 235:
        localObject4[i9] = localObject1;
        i2 = 237;
        localObject3 = "%\b\020wa\023\031Scm\002\025Sga\0074\0274$[]";
        i1 = 236;
        localObject1 = localObject2;
        break;
      case 236:
        localObject4[i9] = localObject1;
        i2 = 238;
        localObject3 = "\005\030\002aa\030\036\026";
        i1 = 237;
        localObject1 = localObject2;
        break;
      case 237:
        localObject4[i9] = localObject1;
        i2 = 239;
        localObject3 = "\004\030\003{v\002Q";
        i1 = 238;
        localObject1 = localObject2;
      case 238:
      }
    }
    localObject4[i9] = localObject1;
    bb = (String[])localObject2;
    b = false;
    c = false;
    d = 2;
    e = 7200000L;
    f = 86400000L;
    g = 300000L;
    h = 3600000L;
    i = 300;
    j = 0L;
    k = 0L;
    l = 0L;
    m = 0L;
    n = 0L;
    K = 86400L;
    L = 360L;
    M = false;
    N = true;
    i1 = -1;
    localObject1 = "GL@:7GSB#*GME";
    while (true)
    {
      localObject1 = ((String)localObject1).toCharArray();
      i2 = localObject1.length;
      i4 = 0;
      i7 = 0;
      localObject2 = localObject1;
      i3 = i2;
      i6 = i1;
      if (i2 <= 1)
      {
        i5 = i1;
        i1 = i7;
      }
      label7436: 
      do
      {
        i4 = i1;
        i3 = i1;
        localObject2 = localObject1;
        i6 = localObject2[i3];
        switch (i4 % 5)
        {
        default:
          i1 = 4;
        case 0:
        case 1:
        case 2:
        case 3:
        }
        while (true)
        {
          localObject2[i3] = ((char)(i1 ^ i6));
          i4 += 1;
          if (i2 != 0)
            break label7552;
          i3 = i2;
          break label7436;
          i1 = 118;
          break;
          i1 = 125;
          break;
          i1 = 115;
          break;
          i1 = 20;
          break;
          i1 = 118;
          continue;
          i1 = 125;
          continue;
          i1 = 115;
          continue;
          i1 = 20;
        }
        i6 = i5;
        i3 = i2;
        localObject2 = localObject1;
        localObject1 = localObject2;
        i2 = i3;
        i1 = i4;
        i5 = i6;
      }
      while (i3 > i4);
      label7552: localObject1 = new String((char[])localObject2).intern();
      switch (i6)
      {
      default:
        o = (String)localObject1;
        p = 3000;
        i1 = 0;
        localObject1 = "GL@:7GSB#*GME";
        break;
      case 4:
        W = (String)localObject1;
        t = 0L;
        u = null;
        v = "";
        ac = false;
        ad = false;
        ae = 10;
        A = false;
        ag = true;
        B = bb['å'];
        C = false;
        D = false;
        localObject1 = new ArrayList();
        ah = (List)localObject1;
        ((List)localObject1).add(S);
        localObject1 = ah;
        localObject2 = "";
        for (i3 = -1; ; i3 = 0)
        {
          localObject5 = ((String)localObject2).toCharArray();
          i2 = localObject5.length;
          i5 = 0;
          i1 = 0;
          i6 = i3;
          localObject2 = localObject5;
          localObject3 = localObject1;
          i4 = i2;
          if (i2 <= 1)
          {
            localObject4 = localObject1;
            localObject1 = localObject5;
          }
          do
          {
            i5 = i1;
            i4 = i1;
            localObject2 = localObject1;
            i6 = localObject2[i4];
            switch (i5 % 5)
            {
            default:
              i1 = 4;
            case 0:
            case 1:
            case 2:
            case 3:
            }
            while (true)
            {
              localObject2[i4] = ((char)(i1 ^ i6));
              i5 += 1;
              if (i2 != 0)
                break label7886;
              i4 = i2;
              break;
              i1 = 118;
              continue;
              i1 = 125;
              continue;
              i1 = 115;
              continue;
              i1 = 20;
            }
            i4 = i2;
            localObject3 = localObject4;
            localObject2 = localObject1;
            i6 = i3;
            i3 = i6;
            localObject1 = localObject2;
            localObject4 = localObject3;
            i2 = i4;
            i1 = i5;
          }
          while (i4 > i5);
          localObject1 = new String((char[])localObject2).intern();
          switch (i6)
          {
          default:
            ((List)localObject3).add(localObject1);
            localObject1 = ah;
            localObject2 = "";
          case 0:
          }
        }
      case 0:
        O = (String)localObject1;
        P = false;
        i1 = 1;
        localObject1 = "\037\020E *\034\r\006glX\036\035";
        break;
      case 1:
        Q = (String)localObject1;
        R = 3000;
        q = "";
        i1 = 2;
        localObject1 = "\005S\031dq\005\025]wj";
        break;
      case 2:
        S = (String)localObject1;
        i1 = 3;
        localObject1 = "GL@:7GSB#*GMK";
        break;
      case 3:
        label7886: T = (String)localObject1;
        U = 9000;
        V = 0;
        r = true;
        s = true;
        i1 = 4;
        localObject1 = "\030\b\037x";
      }
    }
    ((List)localObject3).add(localObject1);
    ah.add(T);
  }

  private void A()
  {
    this.av.removeMessages(1007);
  }

  private void B()
  {
    x.c();
    this.af = a(this, B, D, C);
    this.af.f();
  }

  private static int a(long paramLong1, long paramLong2, String paramString1, String paramString2, int paramInt)
  {
    try
    {
      paramString2 = new JSONObject(paramString2);
      paramString2.put(bb[''], "a");
      if ((0L != paramLong1) && (paramLong2 != 0L))
      {
        paramInt = PushProtocol.TagAlias(paramLong1, paramLong2, paramString1, paramString2.toString(), paramInt);
        return paramInt;
      }
    }
    catch (Exception paramString1)
    {
    }
    return -1;
  }

  private static int a(long paramLong, String paramString, int paramInt)
  {
    new StringBuilder(bb['é']).append(new SimpleDateFormat(bb['']).format(new Date(System.currentTimeMillis()))).toString();
    x.c();
    paramInt = PushProtocol.InitPush(paramLong, paramString, paramInt);
    new StringBuilder(bb['ê']).append(new SimpleDateFormat(bb['']).format(new Date(System.currentTimeMillis()))).toString();
    x.c();
    return paramInt;
  }

  public static long a(Context paramContext)
  {
    if (t != 0L)
      return t;
    ac = c(paramContext);
    return t;
  }

  private cn.jpush.android.a.d a(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    return new m(this, paramContext, paramString, paramBoolean1, paramBoolean2);
  }

  public static void a(int paramInt)
  {
    R = paramInt;
  }

  private void a(int paramInt, long paramLong)
  {
    String str = bb['Á'] + paramInt + bb[24] + paramLong;
    if (!this.y.contains(str))
      this.y.offer(str);
  }

  private void a(long paramLong)
  {
    x.c();
    new Thread(new k(this, paramLong)).start();
  }

  private void a(Context paramContext, String paramString)
  {
    x.b();
    String[] arrayOfString = q.a(paramContext);
    if ((arrayOfString == null) || (arrayOfString.length == 0))
    {
      x.f();
      return;
    }
    int i5 = arrayOfString.length;
    int i1 = 0;
    int i4 = 0;
    paramContext = "[";
    int i3 = 0;
    label41: String str;
    label92: int i2;
    if (i4 < i5)
    {
      str = arrayOfString[i4];
      if (i3 != 0)
        break label309;
      paramContext = paramContext + "\"" + str + "\"";
      i4 += 1;
      i3 += 1;
      if ((i3 < 50) && (paramContext.length() <= 1000) && (i4 != i5))
        break label346;
      paramContext = paramContext + "]";
      paramContext = String.format(bb[1], new Object[] { Integer.valueOf(i5), Integer.valueOf(i1), paramString, Long.valueOf(t), paramContext });
      x.c();
      if (ad)
        o();
      if ((this.w != 0L) && (this.X != null) && (this.X.isAlive()))
      {
        i2 = PushProtocol.RepPush(this.w, t, (byte)8, paramContext);
        if (i2 != 0)
        {
          new StringBuilder(bb[3]).append(i1).append(bb[2]).append(i2).toString();
          x.b();
        }
      }
      i2 = i1 + 1;
      paramContext = "[";
    }
    for (i1 = 0; ; i1 = i3)
    {
      i3 = i1;
      i1 = i2;
      break label41;
      break;
      label309: paramContext = paramContext + bb[0] + str + "\"";
      break label92;
      label346: i2 = i1;
    }
  }

  public static void a(String paramString)
  {
    O = paramString;
  }

  private void a(String paramString1, String paramString2)
  {
    y();
    w();
    if ((paramString1 == null) || (paramString2 == null))
    {
      String.format(bb[''], new Object[] { paramString1, paramString2 });
      x.f();
      return;
    }
    if ((this.w == 0L) || (t == 0L))
    {
      x.c();
      d(paramString1, paramString2);
      w();
      return;
    }
    int i1 = PushProtocol.EnChannel(this.w, t, paramString1, paramString2);
    if (i1 < 0)
    {
      d(paramString1, paramString2);
      w();
      return;
    }
    new StringBuilder(bb['']).append(i1).toString();
    x.d();
  }

  private void a(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      Object localObject2 = this.y.iterator();
      String str;
      do
      {
        localObject1 = paramString1;
        if (!((Iterator)localObject2).hasNext())
          break;
        str = (String)((Iterator)localObject2).next();
      }
      while (!str.startsWith(bb['Ç']));
      String[] arrayOfString = str.split(bb[8]);
      Object localObject1 = paramString1;
      if (arrayOfString.length > 3)
      {
        localObject2 = f(arrayOfString[2], paramString1);
        localObject1 = paramString1;
        if (!ai.a((String)localObject2))
          this.y.remove(str);
      }
      try
      {
        ServiceInterface.b(Integer.valueOf(arrayOfString[3]).intValue());
        localObject1 = localObject2;
        paramString1 = bb['Ç'] + paramString2 + bb[24] + (String)localObject1 + bb[24] + paramInt;
        new StringBuilder(bb['È']).append((String)localObject1).toString();
        x.c();
        this.y.offer(paramString1);
        return;
      }
      catch (Exception paramString1)
      {
        while (true)
          localObject1 = localObject2;
      }
    }
    catch (Exception paramString1)
    {
    }
  }

  private void a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (paramString4.equals(bb['']))
      this.ao = 0;
    while (true)
    {
      ae.b(getApplicationContext(), bb['Û'], this.ao);
      paramString4 = new Intent(paramString4);
      if (paramString2 != null)
        paramString4.putExtra(bb[17], paramString2);
      paramString4.putExtra(bb['Ü'], paramString3);
      paramString4.addCategory(paramString1);
      sendBroadcast(paramString4, String.format(bb[13], new Object[] { paramString1 }));
      return;
      if (paramString4.equals(bb[86]))
        this.ao = 1;
    }
  }

  public static void a(boolean paramBoolean)
  {
    M = paramBoolean;
  }

  private void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    x.c();
    if (paramBoolean3)
      x.c();
    Object localObject4;
    try
    {
      ApplicationInfo localApplicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
      if (localApplicationInfo != null)
        W = localApplicationInfo.metaData.getString(bb[85]);
      if (paramBoolean2)
      {
        if (this.w != 0L)
          this.w = PushProtocol.Close(this.w);
        this.w = PushProtocol.InitConn();
        E = 0;
        e(false);
        if (n() != 0)
        {
          x.c();
          if (this.w != 0L)
            this.w = PushProtocol.Close(this.w);
          if (!paramBoolean3)
            g();
          return;
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      do
        while (true)
        {
          new StringBuilder(bb[79]).append(localNameNotFoundException).toString();
          x.f();
        }
      while (this.w == 0L);
      localObject1 = (TelephonyManager)getSystemService(bb[74]);
      localObject4 = cn.jpush.android.util.a.i(getApplicationContext());
      if (!cn.jpush.android.util.a.c(getApplicationContext(), bb[84]));
    }
    Object localObject2;
    for (Object localObject1 = ((TelephonyManager)localObject1).getSubscriberId(); ; localObject2 = null)
    {
      Object localObject3 = localObject4;
      if (localObject4 == null)
        localObject3 = " ";
      localObject4 = localObject1;
      if (localObject1 == null)
        localObject4 = " ";
      localObject1 = getPackageName();
      String str2 = (String)localObject3 + bb[24] + (String)localObject4 + bb[24] + (String)localObject1 + bb[24] + W;
      String str3 = s();
      String str4 = cn.jpush.android.util.a.a(getApplicationContext(), bb[80]);
      localObject3 = cn.jpush.android.util.a.k(getApplicationContext());
      localObject4 = cn.jpush.android.util.a.j(getApplicationContext());
      Object localObject5 = cn.jpush.android.util.a.g(getApplicationContext(), " ");
      String str1 = cn.jpush.android.util.a.i(getApplicationContext(), " ");
      localObject1 = localObject3;
      if (ai.a((String)localObject3))
        localObject1 = " ";
      localObject3 = localObject4;
      if (ai.a((String)localObject4))
        localObject3 = " ";
      localObject4 = localObject5;
      if (ai.a((String)localObject5))
        localObject4 = " ";
      localObject5 = str1;
      if (ai.a(str1))
        localObject5 = " ";
      ae.b(getApplicationContext(), bb[77], (String)localObject5);
      ae.b(getApplicationContext(), bb[72], (String)localObject3);
      ae.b(getApplicationContext(), bb[70], (String)localObject4);
      localObject1 = cn.jpush.android.util.a.a + bb[24] + (String)localObject1 + bb[24] + (String)localObject5 + bb[24] + (String)localObject3 + bb[24] + (String)localObject4;
      new StringBuilder(bb[76]).append(str2).toString();
      x.c();
      new StringBuilder(bb[81]).append(str3).toString();
      x.c();
      new StringBuilder(bb[88]).append(str4).toString();
      x.c();
      new StringBuilder(bb[68]).append((String)localObject1).toString();
      x.c();
      PushProtocol.RegPush(this.w, str2, str3, str4, (String)localObject1);
      int i2 = 0;
      int i1 = PushProtocol.RecvPush(this.w, this.x, 30);
      if (i1 > 0)
      {
        i1 = cn.jpush.android.util.a.b(this.x);
        int i3 = cn.jpush.android.util.a.c(this.x);
        new StringBuilder(bb[83]).append(i3).toString();
        x.d();
        new StringBuilder(bb[64]).append(b(this.x, i3)).toString();
        x.d();
        if (i1 != c.a.ordinal())
        {
          new StringBuilder(bb[71]).append(i1).toString();
          x.f();
          return;
        }
        if (i3 < 20)
        {
          new StringBuilder(bb[82]).append(20).toString();
          x.f();
          return;
        }
        i1 = 0;
        while (i1 < 2)
        {
          i2 = (i2 << 8) + (this.x[(i1 + 20)] & 0xFF);
          i1 += 1;
        }
        if (i2 == 0)
        {
          cn.jpush.android.a.j = true;
          if (i == 86401)
          {
            x.c();
            ae.b(getApplicationContext(), bb[75], 300);
            i = 300;
            y();
          }
          i1 = 0;
          long l2;
          for (long l1 = 0L; i1 < 8; l1 = (l1 << 8) + l2)
          {
            l2 = this.x[(i1 + 22)] & 0xFF;
            i1 += 1;
          }
          i2 = 0;
          int i4;
          for (i1 = 0; i2 < 2; i1 = (i1 << 8) + (i4 & 0xFF))
          {
            i4 = this.x[(i2 + 30)];
            i2 += 1;
          }
          localObject1 = new byte[i1];
          i2 = 0;
          while (i2 < localObject1.length)
          {
            localObject1[i2] = ((byte)(this.x[(i2 + 32)] & 0xFF));
            i2 += 1;
          }
          localObject1 = new String((byte[])localObject1);
          new StringBuilder(bb[67]).append(l1).toString();
          x.d();
          t = l1;
          u = (String)localObject1;
          ac = true;
          try
          {
            localObject1 = openFileOutput(bb[61], 0);
            ((FileOutputStream)localObject1).write(ByteBuffer.allocate(8).putLong(t).array());
            ((FileOutputStream)localObject1).write(u.getBytes());
            ((FileOutputStream)localObject1).close();
            cn.jpush.android.a.l = false;
            i4 = i1 + 32;
            i1 = 0;
            i2 = 0;
            while (i2 < 2)
            {
              i1 = (i1 << 8) + (this.x[(i4 + i2)] & 0xFF);
              i2 += 1;
            }
          }
          catch (FileNotFoundException localFileNotFoundException)
          {
            while (true)
              x.h();
          }
          catch (IOException localIOException)
          {
            while (true)
              x.h();
            i4 += 2;
            localObject2 = new byte[i1];
            i2 = 0;
            while (i2 < localObject2.length)
            {
              localObject2[i2] = ((byte)(this.x[(i4 + i2)] & 0xFF));
              i2 += 1;
            }
            localObject2 = new String((byte[])localObject2);
            new StringBuilder(bb[62]).append((String)localObject2).toString();
            x.c();
            if (!ai.a((String)localObject2))
            {
              ae.b(getApplicationContext(), bb[63], (String)localObject2);
              if (!paramBoolean3)
                a(cn.jpush.android.a.b, cn.jpush.android.a.f, (String)localObject2, bb[86]);
            }
            i4 += i1;
          }
          if (i4 >= i3)
            break;
          i1 = 0;
          i2 = 0;
          while (i2 < 2)
          {
            i1 = (i1 << 8) + (this.x[(i4 + i2)] & 0xFF);
            i2 += 1;
          }
          i2 = i4 + 2;
          localObject2 = new byte[i1];
          if (i3 != i1 + i2)
          {
            x.f();
            return;
          }
          i1 = 0;
          while (i1 < localObject2.length)
          {
            localObject2[i1] = ((byte)(this.x[(i2 + i1)] & 0xFF));
            i1 += 1;
          }
          localObject2 = new String((byte[])localObject2);
          new StringBuilder(bb[87]).append((String)localObject2).toString();
          x.c();
          cn.jpush.android.util.a.j(getApplicationContext(), (String)localObject2);
          return;
        }
        if (i2 == 1007)
        {
          i2 = 0;
          i1 = 0;
          while (i1 < 2)
          {
            i2 = (i2 << 8) + (this.x[(i1 + 22)] & 0xFF);
            i1 += 1;
          }
          localObject2 = new byte[i2];
          i1 = 0;
          while (i1 < localObject2.length)
          {
            localObject2[i1] = ((byte)(this.x[(i1 + 24)] & 0xFF));
            i1 += 1;
          }
          localObject2 = new String((byte[])localObject2);
          new StringBuilder(bb[66]).append((String)localObject2).toString();
          x.f();
          cn.jpush.android.util.a.c((String)localObject2);
          if (paramBoolean1)
            break;
          a(true, false, paramBoolean3);
          return;
        }
        x.e(bb[4], bb[89] + i2);
        localObject2 = r.a(i2);
        if (localObject2 != null)
          x.e(bb[4], bb[78] + (String)localObject2);
        if (1006 == i2)
        {
          r();
          return;
        }
        if (1007 == i2)
        {
          x.d();
          return;
        }
        if (1005 == i2)
        {
          cn.jpush.android.util.a.c(getApplicationContext(), bb[60] + getPackageName() + bb[69] + cn.jpush.android.a.f + bb[65], bb[60] + getPackageName() + bb[69] + cn.jpush.android.a.f + bb[65]);
          r();
          return;
        }
        if (1009 != i2)
          break;
        cn.jpush.android.a.j = false;
        r();
        return;
      }
      x.e(bb[4], bb[73] + i1);
      this.al = 0L;
      return;
    }
  }

  public static boolean a()
  {
    return N;
  }

  private boolean a(String paramString, int paramInt)
  {
    int i1 = 0;
    if (Looper.myLooper() == null)
    {
      x.c();
      Looper.prepare();
    }
    paramString = new p(this, paramString, paramInt);
    paramString.execute(new Void[0]);
    try
    {
      Thread.sleep(200L);
      paramInt = i1;
      while ((!this.ap) && (paramInt < 3000))
      {
        Thread.sleep(2800L);
        paramInt += 3000;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      if (!this.ap)
        paramString.cancel(true);
    }
    return this.ap;
  }

  public static String b()
  {
    return O;
  }

  public static String b(Context paramContext)
  {
    if (!ai.a(u))
      return u;
    ac = c(paramContext);
    return u;
  }

  private static String b(byte[] paramArrayOfByte, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i1 = 0;
    while (i1 < paramInt)
    {
      String str2 = Integer.toHexString(paramArrayOfByte[i1] & 0xFF);
      String str1 = str2;
      if (str2.length() == 1)
        str1 = "0" + str2;
      localStringBuffer.append(str1.toUpperCase() + " ");
      i1 += 1;
    }
    return "[" + localStringBuffer.toString() + "]";
  }

  public static void b(int paramInt)
  {
    U = paramInt;
  }

  public static void b(String paramString)
  {
    Q = paramString;
  }

  private void b(String paramString, int paramInt)
  {
    if (paramInt != 0)
    {
      Object localObject = (b)ServiceInterface.d.get(Integer.valueOf(paramInt));
      if ((localObject != null) && (((b)localObject).c != null))
      {
        localObject = new Message();
        ((Message)localObject).obj = Integer.valueOf(paramInt);
        ((Message)localObject).what = 1006;
        this.av.sendMessageDelayed((Message)localObject, 20000L);
      }
    }
    for (int i1 = 0; ; i1 = 1)
    {
      b(paramString, W, paramInt);
      if ((this.w != 0L) && (t != 0L))
        break;
      x.c();
      w();
      return;
      paramInt = ae.b(getApplicationContext());
    }
    if (a(this.w, t, W, paramString, paramInt) < 0)
    {
      w();
      return;
    }
    g(paramInt);
    if (i1 == 0)
    {
      x.c(bb[4], bb[18] + paramString);
      return;
    }
    new StringBuilder(bb[18]).append(paramString).toString();
    x.d();
  }

  private void b(String paramString1, String paramString2)
  {
    try
    {
      if ((ai.a(paramString1)) || (ai.a(paramString2)))
      {
        String.format(bb[10], new Object[] { paramString1, paramString2 });
        x.f();
        return;
      }
      if (this.w == 0L)
      {
        x.c();
        c(paramString1, paramString2);
        w();
      }
      while (true)
      {
        return;
        i1 = PushProtocol.UnChnelId(this.w, t, paramString1, paramString2);
        if (i1 >= 0)
          break;
        c(paramString1, paramString2);
        w();
      }
    }
    catch (Exception paramString1)
    {
      while (true)
      {
        int i1;
        return;
        new StringBuilder(bb[11]).append(i1).toString();
        x.d();
      }
    }
    finally
    {
      a(0, bb[7]);
      z();
      stopSelf();
    }
    throw paramString1;
  }

  private void b(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramInt).append(bb[24]).append(paramString2).append(bb[24]).append(paramString1);
      localObject = ((StringBuilder)localObject).toString();
      e(paramString1, paramString2);
      this.z.offer(localObject);
      if (this.z.size() >= 200)
        this.z.poll();
      return;
    }
    finally
    {
      paramString1 = finally;
    }
    throw paramString1;
  }

  public static void b(boolean paramBoolean)
  {
    N = paramBoolean;
  }

  public static void c(int paramInt)
  {
    V = paramInt;
  }

  public static void c(String paramString)
  {
    S = paramString;
  }

  private void c(String paramString1, String paramString2)
  {
    paramString1 = bb['Ý'] + paramString1 + "," + paramString2;
    if (this.y.contains(paramString1))
      return;
    this.y.offer(paramString1);
  }

  public static void c(boolean paramBoolean)
  {
    ad = paramBoolean;
  }

  public static boolean c()
  {
    return true;
  }

  protected static boolean c(Context paramContext)
  {
    boolean bool = false;
    while (true)
    {
      try
      {
        x.d();
        try
        {
          localObject = new byte[8];
          FileInputStream localFileInputStream = paramContext.openFileInput(bb[61]);
          localFileInputStream.read((byte[])localObject, 0, 8);
          t = 0L;
          int i1 = 0;
          if (i1 < 8)
          {
            t = (t << 8) + (localObject[i1] & 0xFF);
            i1 += 1;
            continue;
          }
          localObject = new StringBuilder();
          i1 = localFileInputStream.read();
          if (i1 != -1)
          {
            ((StringBuilder)localObject).append((char)i1);
            continue;
          }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          Object localObject;
          x.c();
          t = 0L;
          long l1 = t;
          if (l1 == 0L)
          {
            return bool;
            localFileNotFoundException.close();
            u = ((StringBuilder)localObject).toString();
            continue;
          }
        }
        catch (IOException localIOException)
        {
          x.h();
          continue;
        }
      }
      finally
      {
      }
      if (ai.a(v))
        v = cn.jpush.android.util.a.t(paramContext);
      if (ai.a(v))
        x.c();
      else
        bool = true;
    }
  }

  public static void d(int paramInt)
  {
    ae = paramInt;
  }

  public static void d(String paramString)
  {
    T = paramString;
  }

  private void d(String paramString1, String paramString2)
  {
    paramString1 = bb['á'] + paramString1 + "," + paramString2;
    if (this.y.contains(paramString1))
      return;
    this.y.offer(paramString1);
  }

  public static void d(boolean paramBoolean)
  {
    ag = paramBoolean;
  }

  private void e(String paramString1, String paramString2)
  {
    if ((ai.a(paramString1)) || (ai.a(paramString2)))
      return;
    while (true)
    {
      String str1;
      try
      {
        Iterator localIterator = this.z.iterator();
        if (!localIterator.hasNext())
          break;
        str1 = (String)localIterator.next();
        if (ai.a(str1))
          continue;
        String[] arrayOfString = str1.split(bb[8]);
        if (arrayOfString.length > 2)
        {
          int i1 = Integer.valueOf(arrayOfString[0]).intValue();
          String str2 = arrayOfString[1];
          if ((!paramString1.equals(arrayOfString[2])) || (!paramString2.equals(str2)))
            continue;
          this.z.remove(str1);
          h(i1);
          continue;
        }
      }
      catch (Exception paramString1)
      {
        paramString1.getMessage();
        x.f();
        return;
      }
      new StringBuilder(bb[9]).append(str1).toString();
      x.f();
      this.z.remove(str1);
    }
  }

  private static String f(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new JSONObject(paramString1);
      paramString2 = new JSONObject(paramString2);
      if (paramString2.has(bb[56]))
        paramString1.put(bb[56], paramString2.get(bb[56]));
      if (paramString2.has(bb[30]))
        paramString1.put(bb[30], paramString2.get(bb[30]));
      paramString1 = paramString1.toString();
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.getMessage();
      x.f();
    }
    return null;
  }

  private void f(int paramInt)
  {
    new StringBuilder(bb['Ö']).append(paramInt).append(bb['×']).toString();
    x.c();
    this.av.removeMessages(1005);
    this.av.removeMessages(1004);
    this.av.sendEmptyMessageDelayed(1004, paramInt);
  }

  private void g(int paramInt)
  {
    Message localMessage = new Message();
    localMessage.obj = Integer.valueOf(paramInt);
    localMessage.what = 1008;
    this.av.sendMessageDelayed(localMessage, 10000L);
  }

  private void g(String paramString1, String paramString2)
  {
    this.y.offer(bb['ï'] + paramString1 + "," + paramString2);
  }

  private void h(int paramInt)
  {
    this.av.removeMessages(1008, Integer.valueOf(paramInt));
  }

  private void h(String paramString)
  {
    new StringBuilder(bb['Ñ']).append(paramString).toString();
    x.d();
    P = true;
    if (ai.a(paramString))
      paramString = ae.a(getApplicationContext(), bb['Ê'], "");
    Object localObject;
    for (int i1 = 0; ; i1 = 1)
    {
      paramString = new JSONObject(paramString);
      localObject = paramString.getJSONArray(bb['Ï']);
      if ((localObject != null) && (((JSONArray)localObject).length() != 0))
        break;
      x.e();
      return;
      ae.b(getApplicationContext(), bb['Ê'], paramString);
      this.al = System.currentTimeMillis();
    }
    String str1;
    int i2;
    if (((JSONArray)localObject).length() > 0)
    {
      str1 = ((JSONArray)localObject).optString(0);
      i2 = str1.indexOf(':');
      if (i2 != -1)
        break label415;
      O = str1;
    }
    while (true)
    {
      if (((JSONArray)localObject).length() >= 2)
      {
        str1 = ((JSONArray)localObject).optString(1);
        if (!ai.a(str1))
        {
          i1 = str1.indexOf(':');
          if (i1 > 0)
          {
            String str2 = str1.substring(0, i1);
            if (!o.equals(str2))
            {
              o = str2;
              new StringBuilder(bb['É']).append(str2).toString();
              x.c();
              ae.b(getApplicationContext(), bb['¼'], str2);
            }
            i1 = Integer.parseInt(str1.substring(i1 + 1));
            if (p != i1)
            {
              p = i1;
              new StringBuilder(bb['Î']).append(i1).toString();
              x.c();
              ae.b(getApplicationContext(), bb['¹'], p);
            }
          }
        }
        if (((JSONArray)localObject).length() >= 3)
        {
          localObject = ((JSONArray)localObject).optString(2);
          if ((!ai.a((String)localObject)) && (!ac.a().contains((CharSequence)localObject)))
          {
            ac.a((String)localObject);
            ae.b(getApplicationContext(), bb['Ð'], (String)localObject);
          }
        }
      }
      try
      {
        paramString = paramString.getJSONArray(bb['Ë']);
        if ((paramString != null) && (paramString.length() != 0))
          break;
        x.c();
        return;
      }
      catch (JSONException paramString)
      {
        x.e();
        return;
      }
      label415: if (i1 != 0)
        x.b(bb[4], bb['Ì']);
      O = str1.substring(0, i2);
      R = Integer.parseInt(str1.substring(i2 + 1));
    }
    new StringBuilder(bb['Í']).append(paramString.toString()).toString();
    x.c();
    q = paramString.toString();
  }

  private void i(String paramString)
  {
    x.c();
    int i2 = 0;
    int i3;
    for (int i1 = 0; i2 < 2; i1 = (i1 << 8) + (i3 & 0xFF))
    {
      i3 = this.x[(i2 + 20)];
      i2 += 1;
    }
    String.format(bb[16], new Object[] { Integer.valueOf(i1) });
    x.c();
    long l1 = 0L;
    i2 = 0;
    while (i2 < 4)
    {
      l1 = (l1 << 8) + (this.x[(i2 + 22)] & 0xFF);
      i2 += 1;
    }
    String.format(bb[12], new Object[] { Long.valueOf(l1) });
    x.c();
    Object localObject1 = new byte[100];
    i2 = 0;
    while ((i2 < localObject1.length) && (this.x[(i2 + 26)] != 0))
    {
      localObject1[i2] = ((byte)(this.x[(i2 + 26)] & 0xFF));
      i2 += 1;
    }
    localObject1 = new String((byte[])localObject1, 0, i2);
    new StringBuilder(bb[15]).append((String)localObject1).toString();
    x.c();
    Object localObject2 = new byte[30];
    i2 = 0;
    while ((i2 < localObject2.length) && (this.x[(i2 + 126)] != 0))
    {
      localObject2[i2] = ((byte)(this.x[(i2 + 126)] & 0xFF));
      i2 += 1;
    }
    localObject2 = new String((byte[])localObject2, 0, i2);
    new StringBuilder(bb[14]).append((String)localObject2).toString();
    x.c();
    if (i1 == 0)
    {
      paramString = new Intent(paramString);
      paramString.putExtra(bb[17], (String)localObject2);
      paramString.addCategory((String)localObject1);
      sendBroadcast(paramString, String.format(bb[13], new Object[] { localObject1 }));
    }
  }

  private boolean i(int paramInt)
  {
    try
    {
      Iterator localIterator = this.z.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (String)localIterator.next();
        if (!ai.a((String)localObject))
        {
          localObject = ((String)localObject).split(bb[8]);
          if (localObject.length > 2)
          {
            int i1 = Integer.valueOf(localObject[0]).intValue();
            if (paramInt == i1)
              return true;
          }
        }
      }
    }
    catch (Exception localException)
    {
      localException.getMessage();
      x.f();
    }
    return false;
  }

  private void j(int paramInt)
  {
    try
    {
      Iterator localIterator = this.z.iterator();
      while (true)
      {
        String str;
        if (localIterator.hasNext())
        {
          str = (String)localIterator.next();
          String[] arrayOfString = str.split(bb[8]);
          if (arrayOfString.length >= 2)
          {
            int i1 = Integer.valueOf(arrayOfString[0]).intValue();
            this.z.remove(str);
            h(i1);
            if (i1 != paramInt)
              continue;
          }
        }
        else
        {
          return;
          new StringBuilder(bb[9]).append(str).toString();
          x.f();
          this.z.remove(str);
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.getMessage();
        x.f();
      }
    }
    finally
    {
    }
  }

  private void j(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      int i1 = paramString.optInt(bb['ì'], cn.jpush.android.api.d.i);
      int i2 = paramString.optInt(bb['î']);
      if (i2 != 0)
      {
        paramString = ServiceInterface.a(i2);
        if (paramString != null)
        {
          TagAliasCallback localTagAliasCallback = paramString.c;
          new StringBuilder(bb['í']).append(i2).toString();
          x.c();
          if (localTagAliasCallback != null)
          {
            ServiceInterface.b(i2);
            j(i2);
            this.av.removeMessages(1006, Integer.valueOf(i2));
            localTagAliasCallback.gotResult(i1, paramString.a, paramString.b);
            return;
          }
          new StringBuilder(bb['ë']).append(i2).toString();
          x.f();
          return;
        }
      }
    }
    catch (Exception paramString)
    {
    }
  }

  private void k(String paramString)
  {
    x.c();
    try
    {
      paramString = new JSONObject(paramString);
      ae.a(getApplicationContext(), paramString);
      if (ae.a(this, bb['Ô'], bb['Ó']).toLowerCase().equals(bb[46]))
      {
        x.c();
        if (this.w != 0L)
          PushProtocol.RepPush(this.w, t, (byte)3, bb['Õ']);
      }
      return;
    }
    catch (Exception paramString)
    {
      x.h();
    }
  }

  private void l(String paramString)
  {
    x.c();
    int i1;
    HashMap localHashMap;
    Object localObject;
    while (true)
    {
      try
      {
        paramString = new JSONObject(paramString);
        i1 = paramString.optInt(bb['ç'], -1);
        if (i1 == -1)
          return;
        localHashMap = new HashMap();
        i2 = paramString.optInt(bb['ä'], -1);
        if (i2 != -1)
        {
          if (i2 == 0)
          {
            ag = true;
            localHashMap.put(bb['ä'], Boolean.valueOf(true));
          }
        }
        else
        {
          i2 = paramString.optInt(bb[19], -1);
          new StringBuilder(bb['ã']).append(i2).toString();
          x.c();
          if (i2 != -1)
          {
            if (i2 != 0)
              break label402;
            localHashMap.put(bb[19], Boolean.valueOf(true));
          }
          i2 = paramString.optInt(bb[47], -1);
          if (i2 != -1)
          {
            if (i2 != 0)
              break label421;
            localHashMap.put(bb[47], Boolean.valueOf(true));
          }
          localHashMap.toString();
          x.c();
          if (i1 != 2)
            break;
          i1 = paramString.optInt(bb[54], -1);
          localObject = bb['å'];
          paramString = (String)localObject;
          if (i1 != -1)
          {
            if (i1 != 0)
              break label440;
            paramString = bb['å'];
          }
          localObject = new Intent(this, PushService.class);
          Bundle localBundle = new Bundle();
          localBundle.putString(bb[54], paramString);
          if (localHashMap.get(bb[19]) != null)
            localBundle.putBoolean(bb[19], ((Boolean)localHashMap.get(bb[19])).booleanValue());
          if (localHashMap.get(bb[47]) != null)
            localBundle.putBoolean(bb[47], ((Boolean)localHashMap.get(bb[47])).booleanValue());
          ((Intent)localObject).putExtras(localBundle);
          startService((Intent)localObject);
          return;
        }
        localHashMap.put(bb['ä'], Boolean.valueOf(false));
        continue;
      }
      catch (JSONException paramString)
      {
        x.h();
        return;
      }
      label402: localHashMap.put(bb[19], Boolean.valueOf(false));
      continue;
      label421: localHashMap.put(bb[47], Boolean.valueOf(false));
      continue;
      label440: if (i1 == 1)
      {
        paramString = bb['è'];
      }
      else if (i1 == 2)
      {
        paramString = bb['æ'];
      }
      else
      {
        paramString = (String)localObject;
        if (i1 == 3)
          paramString = bb['â'];
      }
    }
    if (localHashMap.get(bb[47]) != null)
      C = ((Boolean)localHashMap.get(bb[47])).booleanValue();
    if (localHashMap.get(bb[19]) != null)
      D = ((Boolean)localHashMap.get(bb[19])).booleanValue();
    if (localHashMap.get(bb['ä']) != null)
      ag = ((Boolean)localHashMap.get(bb['ä'])).booleanValue();
    int i2 = paramString.optInt(bb[54], -1);
    if (i2 != -1)
    {
      if (i2 != 0)
        break label693;
      B = bb['å'];
    }
    while (i1 == 0)
    {
      paramString = new Intent(this, PushService.class);
      localObject = new Bundle();
      ((Bundle)localObject).putString(bb[35], bb[35]);
      paramString.putExtras((Bundle)localObject);
      startService(paramString);
      return;
      label693: if (i2 == 1)
        B = bb['è'];
      else if (i2 == 2)
        B = bb['æ'];
      else if (i2 == 3)
        B = bb['â'];
    }
  }

  private void m(String paramString)
  {
    int i4 = 0;
    try
    {
      x.c();
      int i2 = 0;
      for (int i1 = 0; i2 < 2; i1 = (i1 << 8) + (i3 & 0xFF))
      {
        i3 = this.x[(i2 + 20)];
        i2 += 1;
      }
      String.format(bb[16], new Object[] { Integer.valueOf(i1) });
      x.c();
      Object localObject1 = new byte[8];
      i2 = 0;
      while (i2 < localObject1.length)
      {
        localObject1[i2] = ((byte)(this.x[(i2 + 22)] & 0xFF));
        i2 += 1;
      }
      Object localObject2 = new StringBuilder(localObject1.length * 2);
      int i3 = localObject1.length;
      i2 = 0;
      while (i2 < i3)
      {
        int i5 = localObject1[i2];
        ((StringBuilder)localObject2).append(String.format(bb[106], new Object[] { Integer.valueOf(i5 & 0xFF) }));
        i2 += 1;
      }
      localObject1 = ((StringBuilder)localObject2).toString();
      new StringBuilder(bb[105]).append((String)localObject1).toString();
      x.c();
      i2 = 0;
      i3 = 0;
      while (i2 < 2)
      {
        i3 = (i3 << 8) + (this.x[(i2 + 30)] & 0xFF);
        i2 += 1;
      }
      new StringBuilder(bb[103]).append(i3).toString();
      x.c();
      localObject2 = new byte[100];
      i2 = i4;
      while ((i2 < localObject2.length) && (this.x[(i2 + 32)] != 0))
      {
        localObject2[i2] = ((byte)(this.x[(i2 + 32)] & 0xFF));
        i2 += 1;
      }
      localObject2 = new String((byte[])localObject2, 0, i2);
      new StringBuilder(bb[15]).append((String)localObject2).toString();
      x.c();
      if (i1 == 0)
      {
        a((String)localObject2, null, (String)localObject1, paramString);
        return;
      }
      String.format(bb[104], new Object[] { Integer.valueOf(i1) });
      x.d();
      return;
    }
    catch (Exception paramString)
    {
      x.j();
    }
  }

  private int n()
  {
    int i7 = 0;
    Object localObject3 = O;
    int i5 = R;
    Object localObject1;
    int i1;
    int i3;
    if (!P)
    {
      localObject1 = ae.a(getApplicationContext(), bb['º'], O);
      i1 = ae.a(getApplicationContext(), bb['¾'], R);
      new StringBuilder(bb['¶']).append((String)localObject1).append(bb['À']).append(i1).toString();
      x.c();
      i3 = a(this.w, (String)localObject1, i1);
      return i3;
    }
    new StringBuilder(bb['¸']).append((String)localObject3).append(bb['»']).append(i5).toString();
    x.c();
    int i4 = a(this.w, (String)localObject3, i5);
    int i6;
    if ((i4 != 0) && (!ai.a(q)))
    {
      i6 = i4;
      i1 = i5;
      localObject1 = localObject3;
    }
    while (true)
    {
      Object localObject4;
      try
      {
        JSONArray localJSONArray = new JSONArray(q);
        i2 = i4;
        i3 = i5;
        Object localObject5 = localObject3;
        if (localJSONArray != null)
        {
          i2 = i4;
          i3 = i5;
          localObject5 = localObject3;
          i6 = i4;
          i1 = i5;
          localObject1 = localObject3;
          if (localJSONArray.length() > 0)
          {
            i2 = i4;
            i3 = i5;
            localObject5 = localObject3;
            i6 = i4;
            i1 = i5;
            localObject1 = localObject3;
            if (i7 < localJSONArray.length())
            {
              i6 = i4;
              i1 = i5;
              localObject1 = localObject3;
              String[] arrayOfString = localJSONArray.optString(i7).split(":");
              i3 = i4;
              i2 = i5;
              localObject5 = localObject3;
              if (arrayOfString != null)
              {
                i3 = i4;
                i2 = i5;
                localObject5 = localObject3;
                i6 = i4;
                i1 = i5;
                localObject1 = localObject3;
                if (arrayOfString.length == 2)
                {
                  localObject3 = arrayOfString[0];
                  i6 = i4;
                  i1 = i5;
                  localObject1 = localObject3;
                  i5 = Integer.parseInt(arrayOfString[1]);
                  i6 = i4;
                  i1 = i5;
                  localObject1 = localObject3;
                  new StringBuilder(bb['½']).append((String)localObject3).append(bb['»']).append(i5).toString();
                  i6 = i4;
                  i1 = i5;
                  localObject1 = localObject3;
                  x.c();
                  i6 = i4;
                  i1 = i5;
                  localObject1 = localObject3;
                  i2 = a(this.w, (String)localObject3, i5);
                  i1 = i2;
                  i2 = i1;
                  i3 = i5;
                  localObject5 = localObject3;
                  if (i1 == 0)
                    continue;
                  localObject5 = localObject3;
                  i2 = i5;
                  i3 = i1;
                }
              }
              i7 += 1;
              i4 = i3;
              i5 = i2;
              localObject3 = localObject5;
              continue;
            }
          }
        }
        i1 = i3;
        localObject1 = localObject5;
        i3 = i1;
        i1 = i2;
        if (i2 != 0)
        {
          localObject1 = ae.a(getApplicationContext(), bb['¼'], o);
          i3 = ae.a(getApplicationContext(), bb['¹'], p);
          new StringBuilder(bb['¶']).append((String)localObject1).append(bb['À']).append(i3).toString();
          x.c();
          i1 = a(this.w, (String)localObject1, i3);
        }
        if ((i1 != 0) && (!ai.a(Q)))
        {
          i2 = i3;
          localObject3 = localObject1;
          try
          {
            if (a(Q, 1))
              continue;
            i2 = i3;
            localObject3 = localObject1;
            throw new Exception();
          }
          catch (Exception localException1)
          {
            new StringBuilder(bb['¿']).append(Q).toString();
            localObject2 = localObject3;
            x.c();
          }
          i3 = i1;
          if (i1 != 0)
            break;
          ae.b(getApplicationContext(), bb['º'], (String)localObject2);
          ae.b(getApplicationContext(), bb['¾'], i2);
          return i1;
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        i2 = i6;
        continue;
        i2 = i3;
        localObject4 = localObject2;
        localObject2 = this.J.getHostAddress();
        i2 = i3;
        localObject4 = localObject2;
        i3 = R;
        i2 = i3;
        localObject4 = localObject2;
        new StringBuilder(bb['µ']).append((String)localObject2).append(bb['À']).append(i3).append(bb['·']).append(Q).toString();
        i2 = i3;
        localObject4 = localObject2;
        x.c();
        i2 = i3;
        localObject4 = localObject2;
        i4 = a(this.w, (String)localObject2, R);
        i2 = i3;
        i1 = i4;
        continue;
        i2 = i3;
        continue;
      }
      i1 = i5;
      Object localObject2 = localObject4;
      int i2 = i4;
    }
  }

  private void n(String paramString)
  {
    int i3 = 0;
    try
    {
      x.c();
      int i2 = 0;
      for (int i1 = 0; i2 < 2; i1 = (i1 << 8) + (i4 & 0xFF))
      {
        i4 = this.x[(i2 + 20)];
        i2 += 1;
      }
      String.format(bb[16], new Object[] { Integer.valueOf(i1) });
      x.c();
      long l1 = 0L;
      i2 = 0;
      while (i2 < 4)
      {
        l1 = (l1 << 8) + (this.x[(i2 + 22)] & 0xFF);
        i2 += 1;
      }
      String.format(bb[12], new Object[] { Long.valueOf(l1) });
      x.c();
      Object localObject1 = new byte[8];
      i2 = 0;
      while (i2 < localObject1.length)
      {
        localObject1[i2] = ((byte)(this.x[(i2 + 26)] & 0xFF));
        i2 += 1;
      }
      Object localObject2 = new StringBuilder(localObject1.length * 2);
      int i4 = localObject1.length;
      i2 = 0;
      while (i2 < i4)
      {
        int i5 = localObject1[i2];
        ((StringBuilder)localObject2).append(String.format(bb[106], new Object[] { Integer.valueOf(i5 & 0xFF) }));
        i2 += 1;
      }
      localObject1 = ((StringBuilder)localObject2).toString();
      new StringBuilder(bb[105]).append((String)localObject1).toString();
      x.c();
      localObject2 = new byte[100];
      i2 = 0;
      while ((i2 < localObject2.length) && (this.x[(i2 + 34)] != 0))
      {
        localObject2[i2] = ((byte)(this.x[(i2 + 34)] & 0xFF));
        i2 += 1;
      }
      localObject2 = new String((byte[])localObject2, 0, i2);
      new StringBuilder(bb[15]).append((String)localObject2).toString();
      x.c();
      Object localObject3 = new byte[30];
      i2 = i3;
      while ((i2 < localObject3.length) && (this.x[(i2 + 134)] != 0))
      {
        localObject3[i2] = ((byte)(this.x[(i2 + 134)] & 0xFF));
        i2 += 1;
      }
      localObject3 = new String((byte[])localObject3, 0, i2);
      new StringBuilder(bb[14]).append((String)localObject3).toString();
      x.c();
      if (i1 == 0)
      {
        a((String)localObject2, (String)localObject3, (String)localObject1, paramString);
        return;
      }
      String.format(bb[104], new Object[] { Integer.valueOf(i1) });
      x.d();
      return;
    }
    catch (Exception paramString)
    {
      x.j();
    }
  }

  private void o()
  {
    new StringBuilder(bb['Ò']).append(ae).append("s").toString();
    x.c();
    this.av.removeMessages(1001);
    this.av.sendEmptyMessageDelayed(1001, ae * 1000);
  }

  private void o(String paramString)
  {
    x.b();
    int i1;
    while (true)
    {
      try
      {
        paramString = new JSONObject(paramString);
        i1 = paramString.optInt(bb[35], -1);
        if (i1 != -1)
        {
          new StringBuilder(bb['']).append(Integer.toString(i1)).toString();
          x.c();
          i = i1 * 60;
          ae.b(this, bb[75], i);
        }
        i1 = paramString.optInt(bb[''], -1);
        if (i1 != -1)
        {
          new StringBuilder(bb['']).append(Integer.toString(i1)).toString();
          x.c();
          if (i1 == 0)
          {
            r = false;
            ae.b(this, bb[''], bb[25]);
          }
        }
        else
        {
          i1 = paramString.optInt(bb[''], -1);
          if (i1 != -1)
          {
            new StringBuilder(bb['']).append(Integer.toString(i1)).toString();
            x.c();
            if (i1 != 0)
              break label347;
            s = false;
            ae.b(this, bb[''], i1);
          }
          i1 = paramString.optInt(bb[''], -1);
          if (i1 != -1)
          {
            new StringBuilder(bb['']).append(Integer.toString(i1)).toString();
            x.c();
            ae.b(this, bb[''], i1);
          }
          i1 = paramString.optInt(bb[''], -1);
          if (i1 == -1)
            return;
          if (i1 != 0)
            break;
          ae.b(this, bb[26], bb[25]);
          x.c();
          stopSelf();
          return;
        }
        r = true;
        ae.b(this, bb[''], bb[46]);
        continue;
      }
      catch (JSONException paramString)
      {
        x.h();
        return;
      }
      label347: s = true;
    }
    if (i1 == 1)
    {
      x.c();
      stopSelf();
      return;
    }
    if (i1 == 2)
      ServiceInterface.d(getApplicationContext());
  }

  private byte[] p()
  {
    int i2 = 0;
    String str = cn.jpush.android.util.a.d(this);
    Object localObject = ((TelephonyManager)getSystemService(bb[74])).getNetworkOperator();
    str = bb[''] + str;
    try
    {
      i1 = Integer.valueOf((String)localObject).intValue();
      localObject = new byte[''];
      byte[] arrayOfByte = new byte[2];
      byte[] tmp70_68 = arrayOfByte;
      tmp70_68[0] = 0;
      byte[] tmp76_70 = tmp70_68;
      tmp76_70[1] = -128;
      tmp76_70;
      System.arraycopy(arrayOfByte, 0, localObject, 0, arrayOfByte.length);
      aa.a((byte[])localObject, str, 2);
      aa.e((byte[])localObject, i1, 34);
      aa.e((byte[])localObject, Integer.parseInt((int)(t & 0x7FFFFFFF)), 38);
      if (W.length() > 50)
        W = W.substring(0, 49);
      aa.a((byte[])localObject, W, 42);
      aa.a((byte[])localObject, bb[80], 92);
      i1 = i2;
      if (c)
        i1 = 1;
      aa.e((byte[])localObject, i1, 102);
      return localObject;
    }
    catch (Exception localException)
    {
      while (true)
        int i1 = 0;
    }
  }

  // ERROR //
  private void q()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 779	cn/jpush/android/util/x:c	()V
    //   5: aload_0
    //   6: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   9: bipush 61
    //   11: aaload
    //   12: invokevirtual 1592	cn/jpush/android/service/PushService:deleteFile	(Ljava/lang/String;)Z
    //   15: pop
    //   16: iconst_0
    //   17: putstatic 654	cn/jpush/android/service/PushService:ac	Z
    //   20: lconst_0
    //   21: putstatic 646	cn/jpush/android/service/PushService:t	J
    //   24: aload_0
    //   25: monitorexit
    //   26: return
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: athrow
    //   32: astore_1
    //   33: goto -9 -> 24
    //
    // Exception table:
    //   from	to	target	type
    //   2	5	27	finally
    //   5	24	27	finally
    //   5	24	32	java/lang/Exception
  }

  private void r()
  {
    x.c();
    ae.b(getApplicationContext(), bb[75], 86401);
    i = 86401;
    stopSelf();
  }

  private String s()
  {
    try
    {
      String str2 = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      String str1 = str2;
      if (str2.length() > 30)
      {
        x.e(bb[4], bb[97]);
        str1 = str2.substring(0, 30);
      }
      return str1;
    }
    catch (Exception localException)
    {
    }
    return bb[98];
  }

  private void t()
  {
    int i2 = 0;
    if (this.x.length < 31)
    {
      x.f();
      return;
    }
    int i3 = cn.jpush.android.util.a.a(this.x[20]);
    int i1 = 0;
    while (i1 < 8)
    {
      byte[] arrayOfByte = this.x;
      i1 += 1;
    }
    if (i3 == 2)
    {
      m = ae.a(getApplicationContext(), bb['Ä'], m);
      n = ae.a(getApplicationContext(), bb['Æ'], n);
      new StringBuilder(bb['Ã']).append(m).append(bb['Â']).append(n).toString();
      x.d();
      x.c();
      A();
      return;
    }
    if (i3 == 10)
    {
      i1 = 0;
      while (i1 < 2)
      {
        i2 = (i2 << 8) + (this.x[(i1 + 4)] & 0xFF);
        i1 += 1;
      }
      new StringBuilder(bb['Å']).append(i2).toString();
      x.c();
      h(i2);
      j(i2);
      return;
    }
    new StringBuilder(bb[126]).append(i3).toString();
    x.e();
  }

  private void u()
  {
    try
    {
      x.c();
      if (0L != this.w)
        PushProtocol.Stop(this.w);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void v()
  {
    try
    {
      this.av.removeMessages(1008);
      while (true)
      {
        String str;
        try
        {
          Iterator localIterator = this.z.iterator();
          if (localIterator.hasNext())
          {
            str = (String)localIterator.next();
            new StringBuilder(bb['']).append(str).toString();
            x.f();
            if (ai.a(str))
              continue;
            Object localObject2 = str.split(bb[8]);
            if (localObject2.length <= 2)
              break label148;
            int i1 = Integer.valueOf(localObject2[0]).intValue();
            str = localObject2[1];
            localObject2 = localObject2[2];
            a(this.w, t, str, (String)localObject2, i1);
            g(i1);
            continue;
          }
        }
        catch (Exception localException)
        {
          localException.getMessage();
          x.f();
          return;
        }
        label148: new StringBuilder(bb[9]).append(str).toString();
        x.f();
        this.z.remove(str);
      }
    }
    finally
    {
    }
  }

  private void w()
  {
    x.c();
    A();
    if (this.X != null)
    {
      if (!this.X.isAlive());
      while (true)
      {
        try
        {
          this.X.join();
          this.X = new o(this);
          this.X.start();
          return;
        }
        catch (Exception localException1)
        {
          x.h();
          return;
        }
        new StringBuilder(bb['Ø']).append(this.X.getId()).toString();
        x.c();
        try
        {
          if (!this.X.a)
          {
            x.c();
            this.X = new o(this);
            this.X.start();
            return;
          }
        }
        catch (Exception localException2)
        {
          x.h();
          return;
        }
      }
    }
    try
    {
      this.X = new o(this);
      this.X.start();
      return;
    }
    catch (Exception localException3)
    {
      x.h();
    }
  }

  private void x()
  {
    x.c();
    while (true)
    {
      String str = (String)this.y.poll();
      if (str == null)
        break;
      cn.jpush.android.data.r.a(getApplicationContext(), str);
    }
  }

  private void y()
  {
    new StringBuilder(bb['à']).append(i).toString();
    x.c();
    int i1 = i;
    boolean bool = N;
    N = bool;
    if (bool)
    {
      x.c();
      PendingIntent localPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(this, AlarmReceiver.class), 0);
      ((AlarmManager)getSystemService(bb['Þ'])).set(0, System.currentTimeMillis() + i1 * 1000, localPendingIntent);
      String.format(bb['ß'], new Object[] { Integer.valueOf(i1) });
      x.d();
    }
  }

  private void z()
  {
    x.c();
    u();
    o();
  }

  protected final void a(int paramInt, String paramString)
  {
    String str1 = cn.jpush.android.a.b;
    String str2 = cn.jpush.android.a.f;
    x.c();
    if (paramInt == this.an)
      return;
    this.an = paramInt;
    ae.b(getApplicationContext(), bb['Ù'], paramInt);
    Intent localIntent = new Intent(paramString);
    if (str2 != null)
      localIntent.putExtra(bb[17], str2);
    paramString = bb['Ú'];
    boolean bool;
    if (paramInt == 1)
    {
      bool = true;
      localIntent.putExtra(paramString, bool);
      if (!ai.a(str1))
        break label143;
    }
    label143: for (paramString = getPackageName(); ; paramString = str1)
    {
      localIntent.addCategory(paramString);
      sendBroadcast(localIntent, String.format(bb[13], new Object[] { paramString }));
      return;
      bool = false;
      break;
    }
  }

  protected final void d()
  {
    a(false, false, false);
  }

  protected final void e()
  {
    a(false, true, false);
  }

  protected final void e(int paramInt)
  {
    if (this.w == 0L);
    int i1;
    label61: int i2;
    long l1;
    Object localObject3;
    Object localObject5;
    Object localObject4;
    label490: 
    do
    {
      return;
      i1 = cn.jpush.android.util.a.b(this.x);
      new StringBuilder(bb[127]).append(i1).toString();
      x.e();
      if ((i1 != 3) && (i1 != 9))
        break;
      Object localObject1;
      int i3;
      int i4;
      if (i1 == 9)
      {
        i1 = 1;
        new StringBuilder(bb['']).append(paramInt).toString();
        x.c();
        i2 = aa.b(this.x, 20, 1);
        l1 = aa.c(this.x, 21, 8);
        new StringBuilder(bb[118]).append(i2).append(bb[123]).append(l1).toString();
        x.c();
        if (i1 == 0)
          break label557;
        i1 = aa.b(this.x, 29, 2);
        localObject1 = aa.d(this.x, 31, i1);
        i3 = i1 + 31;
        i1 = aa.b(this.x, i3, 2);
        i3 += 2;
        localObject3 = aa.d(this.x, i3, i1);
        i3 += i1;
        i1 = aa.b(this.x, i3, 1);
        new StringBuilder(bb[113]).append(i1).toString();
        x.d();
        i4 = i3 + 1;
        i3 = aa.b(this.x, i4, 2);
        i4 += 2;
        if (i1 != 1)
          break label490;
        localObject5 = aa.a(this.x, i4, i3);
        localObject4 = localObject1;
        localObject1 = localObject5;
      }
      while (true)
      {
        new StringBuilder(bb[107]).append((String)localObject4).append(bb[114]).append((String)localObject3).append(bb[110]).append((String)localObject1).toString();
        x.c();
        switch (i2)
        {
        case 1:
        case 7:
        case 8:
        case 10:
        case 11:
        case 12:
        case 13:
        case 16:
        case 17:
        case 18:
        case 19:
        default:
          new StringBuilder(bb[111]).append(i2).toString();
          x.c();
          return;
          i1 = 0;
          break label61;
          if (i1 == 0)
          {
            localObject5 = aa.d(this.x, i4, i3);
            localObject4 = localObject1;
            localObject1 = localObject5;
          }
          else
          {
            new StringBuilder(bb['']).append(i1).toString();
            x.f();
            localObject5 = null;
            localObject4 = localObject1;
            localObject1 = localObject5;
            continue;
            i1 = aa.b(this.x, 29, 2);
            localObject4 = aa.d(this.x, 31, i1);
            localObject3 = new LineNumberReader(new StringReader((String)localObject4));
            try
            {
              localObject1 = ((LineNumberReader)localObject3).readLine();
              if (localObject1 == null)
              {
                x.f();
                return;
              }
            }
            catch (IOException localIOException)
            {
              x.j();
              return;
            }
            localObject3 = ((LineNumberReader)localObject3).readLine();
            if (localObject3 == null)
            {
              x.f();
              return;
            }
            i1 = localIOException.length() + ((String)localObject3).length() + 2;
            Object localObject2;
            if (((String)localObject4).length() > i1 + 1)
            {
              localObject5 = ((String)localObject4).substring(i1);
              localObject4 = localIOException;
              localObject2 = localObject5;
            }
            else
            {
              x.c();
              localObject5 = "";
              localObject4 = localObject2;
              localObject2 = localObject5;
            }
          }
          break;
        case 0:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 9:
        case 14:
        case 15:
        case 20:
        case 21:
        case 22:
        }
      }
    }
    while (paramInt <= 20);
    try
    {
      label557: new StringBuilder(bb[117]).append(l1).toString();
      x.c();
      if (ServiceInterface.j(getApplicationContext()))
      {
        x.d();
        return;
      }
    }
    catch (Exception localException)
    {
      x.j();
      return;
    }
    cn.jpush.android.data.a locala1;
    if (!cn.jpush.android.util.a.n(getApplicationContext()))
    {
      if (this.w != 0L)
        PushProtocol.MsgResponse(this.w, 0, t, (byte)i2, l1);
      locala1 = cn.jpush.android.data.p.a(getApplicationContext(), localException, (String)localObject4, (String)localObject3, l1);
      if (locala1 == null);
      while (true)
      {
        x.d();
        return;
        ServiceInterface.a(locala1.c, 1030, cn.jpush.android.a.d);
      }
    }
    cn.jpush.android.data.a locala2;
    if (this.w == 0L)
    {
      a(i2, l1);
      new StringBuilder(bb[116]).append(l1).toString();
      x.f();
      localObject5 = new aj(bb[4], bb[115]);
      if ((TextUtils.isEmpty((CharSequence)localObject4)) || (TextUtils.isEmpty((CharSequence)localObject3)))
        break label1461;
      if (TextUtils.isEmpty(locala1))
        break label1455;
      new StringBuilder(bb['']).append(l1).toString();
      x.c();
      locala2 = cn.jpush.android.data.p.a(getApplicationContext(), locala1, (String)localObject4, (String)localObject3, l1);
      if (locala2 != null)
        break label1049;
    }
    label987: Object localObject6;
    while (true)
    {
      ((aj)localObject5).a();
      return;
      if (PushProtocol.MsgResponse(this.w, 0, t, (byte)i2, l1) != 0)
      {
        a(i2, l1);
        break;
      }
      new StringBuilder(bb[121]).append(l1).toString();
      x.b();
      break;
      label1049: localObject6 = locala2.h();
      if (!ar.contains(localObject6))
        break label1098;
      new StringBuilder(bb['']).append(localObject6).toString();
      x.f();
    }
    label1098: if (ar.size() >= 200)
      ar.poll();
    ar.offer(localObject6);
    paramInt = 0;
    if (((String)localObject3).equalsIgnoreCase(bb['']))
      cn.jpush.android.data.p.a(getApplicationContext(), locala2);
    while (true)
    {
      localObject6 = l1;
      new StringBuilder(bb[108]).append(paramInt).toString();
      x.c();
      if ((paramInt & 0x1) != 0)
      {
        x.c();
        locala2.h = true;
        Intent localIntent = new Intent(bb[125]);
        localIntent.putExtra(bb[122], (String)localObject3);
        localIntent.putExtra(bb[120], (String)localObject4);
        localIntent.putExtra(bb[124], locala1);
        localIntent.putExtra(bb[119], (String)localObject6);
        localIntent.putExtra(bb[112], locala2.g);
        localIntent.addCategory((String)localObject4);
        sendOrderedBroadcast(localIntent, (String)localObject4 + bb[109]);
      }
      if ((paramInt & 0x2) == 0)
        break label987;
      x.c();
      if ((ai.a(locala2.i)) && (ai.a(locala2.l)))
        break label987;
      locala2.m = ((String)localObject4);
      locala2.n = ((String)localObject3);
      if (!locala2.f())
      {
        cn.jpush.android.util.a.a(getApplicationContext(), locala2);
        break label987;
        if (!locala2.e)
          break label1695;
        paramInt = 1;
        if (locala2.b != 4)
          continue;
        paramInt = 3;
        continue;
      }
      new Thread(new j(this, cn.jpush.android.data.a.a(locala2))).start();
      break label987;
      label1455: x.f();
      break label987;
      label1461: x.e();
      break label987;
      o(locala1);
      return;
      ServiceInterface.e(getApplicationContext());
      return;
      l(locala1);
      return;
      k(locala1);
      return;
      a(getApplicationContext(), W);
      return;
      a((String)localObject4, W);
      return;
      b(locala1, 0);
      return;
      j(locala1);
      return;
      JLogger.parseModalJson(locala1, getApplicationContext());
      return;
      JRecorder.parseRecordCommand(locala1);
      return;
      if (i1 == 6)
      {
        n(bb[86]);
        return;
      }
      if (i1 == 7)
      {
        i(bb[43]);
        return;
      }
      if (i1 == 11)
      {
        i(bb[22]);
        return;
      }
      if (i1 == 14)
      {
        n(bb['']);
        return;
      }
      if (i1 == 16)
      {
        m(bb[86]);
        return;
      }
      if (i1 == 17)
      {
        m(bb['']);
        return;
      }
      if (i1 == 10)
        break;
      if (i1 == 19)
      {
        t();
        return;
      }
      new StringBuilder(bb[126]).append(i1).toString();
      x.c();
      return;
      label1695: paramInt = 2;
    }
  }

  // ERROR //
  public final void e(boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: aload_0
    //   4: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   7: invokestatic 1768	cn/jpush/android/util/a:v	(Landroid/content/Context;)Ljava/lang/String;
    //   10: putfield 750	cn/jpush/android/service/PushService:F	Ljava/lang/String;
    //   13: aload_0
    //   14: aload_0
    //   15: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   18: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   21: bipush 96
    //   23: aaload
    //   24: ldc_w 650
    //   27: invokestatic 1432	cn/jpush/android/util/ae:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   30: putfield 752	cn/jpush/android/service/PushService:G	Ljava/lang/String;
    //   33: aload_0
    //   34: getfield 750	cn/jpush/android/service/PushService:F	Ljava/lang/String;
    //   37: aload_0
    //   38: getfield 752	cn/jpush/android/service/PushService:G	Ljava/lang/String;
    //   41: invokevirtual 1001	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   44: ifeq +48 -> 92
    //   47: aload_0
    //   48: iload_3
    //   49: putfield 754	cn/jpush/android/service/PushService:H	Z
    //   52: iload_1
    //   53: ifne +52 -> 105
    //   56: aload_0
    //   57: getfield 754	cn/jpush/android/service/PushService:H	Z
    //   60: ifne +45 -> 105
    //   63: invokestatic 824	java/lang/System:currentTimeMillis	()J
    //   66: aload_0
    //   67: getfield 853	cn/jpush/android/service/PushService:al	J
    //   70: lsub
    //   71: invokestatic 1325	java/lang/Math:abs	(J)J
    //   74: ldc2_w 1769
    //   77: lcmp
    //   78: ifge +27 -> 105
    //   81: invokestatic 955	cn/jpush/android/util/x:d	()V
    //   84: aload_0
    //   85: ldc_w 650
    //   88: invokespecial 1772	cn/jpush/android/service/PushService:h	(Ljava/lang/String;)V
    //   91: return
    //   92: iconst_1
    //   93: istore_3
    //   94: goto -47 -> 47
    //   97: astore 4
    //   99: aload 4
    //   101: invokevirtual 1559	java/lang/Exception:printStackTrace	()V
    //   104: return
    //   105: aload_0
    //   106: getfield 754	cn/jpush/android/service/PushService:H	Z
    //   109: ifeq +20 -> 129
    //   112: aload_0
    //   113: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   116: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   119: bipush 96
    //   121: aaload
    //   122: aload_0
    //   123: getfield 750	cn/jpush/android/service/PushService:F	Ljava/lang/String;
    //   126: invokestatic 1106	cn/jpush/android/util/ae:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   129: ldc_w 650
    //   132: astore 8
    //   134: new 1774	java/net/DatagramSocket
    //   137: dup
    //   138: invokespecial 1775	java/net/DatagramSocket:<init>	()V
    //   141: astore 6
    //   143: aload 6
    //   145: astore 4
    //   147: sipush 1024
    //   150: newarray byte
    //   152: astore 9
    //   154: aload 6
    //   156: astore 4
    //   158: getstatic 674	cn/jpush/android/service/PushService:ah	Ljava/util/List;
    //   161: getstatic 716	cn/jpush/android/service/PushService:E	I
    //   164: invokeinterface 1778 2 0
    //   169: checkcast 96	java/lang/String
    //   172: astore 7
    //   174: aload 6
    //   176: astore 4
    //   178: new 812	java/lang/StringBuilder
    //   181: dup
    //   182: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   185: bipush 95
    //   187: aaload
    //   188: invokespecial 813	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   191: aload 7
    //   193: invokevirtual 835	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   199: bipush 90
    //   201: aaload
    //   202: invokevirtual 835	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: getstatic 706	cn/jpush/android/service/PushService:U	I
    //   208: invokevirtual 865	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   211: invokevirtual 836	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: pop
    //   215: aload 6
    //   217: astore 4
    //   219: invokestatic 779	cn/jpush/android/util/x:c	()V
    //   222: aload 6
    //   224: astore 4
    //   226: aload 7
    //   228: invokestatic 984	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   231: ifne +626 -> 857
    //   234: aload 6
    //   236: astore 4
    //   238: getstatic 716	cn/jpush/android/service/PushService:E	I
    //   241: iconst_2
    //   242: if_icmpgt +422 -> 664
    //   245: aload 6
    //   247: astore 4
    //   249: aload_0
    //   250: aload 7
    //   252: iconst_0
    //   253: invokespecial 1555	cn/jpush/android/service/PushService:a	(Ljava/lang/String;I)Z
    //   256: ifne +150 -> 406
    //   259: aload 6
    //   261: astore 4
    //   263: new 791	java/lang/Exception
    //   266: dup
    //   267: invokespecial 1556	java/lang/Exception:<init>	()V
    //   270: athrow
    //   271: astore 5
    //   273: aload 6
    //   275: astore 4
    //   277: new 812	java/lang/StringBuilder
    //   280: dup
    //   281: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   284: bipush 92
    //   286: aaload
    //   287: invokespecial 813	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   290: aload 7
    //   292: invokevirtual 835	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: invokevirtual 836	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   298: pop
    //   299: aload 6
    //   301: astore 4
    //   303: invokestatic 955	cn/jpush/android/util/x:d	()V
    //   306: aload 6
    //   308: astore 4
    //   310: aload 5
    //   312: invokevirtual 1779	java/lang/Exception:toString	()Ljava/lang/String;
    //   315: pop
    //   316: aload 6
    //   318: astore 4
    //   320: invokestatic 955	cn/jpush/android/util/x:d	()V
    //   323: aload 6
    //   325: astore 4
    //   327: getstatic 706	cn/jpush/android/service/PushService:U	I
    //   330: bipush 80
    //   332: if_icmpeq +435 -> 767
    //   335: aload 6
    //   337: astore 4
    //   339: bipush 80
    //   341: putstatic 706	cn/jpush/android/service/PushService:U	I
    //   344: aload 6
    //   346: astore 4
    //   348: getstatic 716	cn/jpush/android/service/PushService:E	I
    //   351: iconst_4
    //   352: if_icmpge +12 -> 364
    //   355: aload 6
    //   357: astore 4
    //   359: aload_0
    //   360: iconst_1
    //   361: invokevirtual 1066	cn/jpush/android/service/PushService:e	(Z)V
    //   364: aload 6
    //   366: ifnull -275 -> 91
    //   369: aload 6
    //   371: invokevirtual 1780	java/net/DatagramSocket:close	()V
    //   374: return
    //   375: astore 4
    //   377: new 812	java/lang/StringBuilder
    //   380: dup
    //   381: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   384: bipush 93
    //   386: aaload
    //   387: invokespecial 813	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   390: aload 4
    //   392: invokevirtual 1781	java/lang/AssertionError:toString	()Ljava/lang/String;
    //   395: invokevirtual 835	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   398: invokevirtual 836	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   401: pop
    //   402: invokestatic 898	cn/jpush/android/util/x:f	()V
    //   405: return
    //   406: aload 6
    //   408: astore 4
    //   410: aload_0
    //   411: getfield 756	cn/jpush/android/service/PushService:I	[Ljava/net/InetAddress;
    //   414: iconst_0
    //   415: aaload
    //   416: astore 5
    //   418: aload 6
    //   420: astore 4
    //   422: aload_0
    //   423: invokespecial 1783	cn/jpush/android/service/PushService:p	()[B
    //   426: astore 8
    //   428: aload 6
    //   430: astore 4
    //   432: aload 8
    //   434: arraylength
    //   435: istore_2
    //   436: iload_2
    //   437: sipush 256
    //   440: if_icmple +414 -> 854
    //   443: sipush 256
    //   446: istore_2
    //   447: aload 6
    //   449: astore 4
    //   451: getstatic 706	cn/jpush/android/service/PushService:U	I
    //   454: bipush 80
    //   456: if_icmpne +222 -> 678
    //   459: aload 6
    //   461: astore 4
    //   463: new 812	java/lang/StringBuilder
    //   466: dup
    //   467: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   470: bipush 94
    //   472: aaload
    //   473: invokespecial 813	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   476: getstatic 706	cn/jpush/android/service/PushService:U	I
    //   479: invokevirtual 865	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   482: invokevirtual 836	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   485: pop
    //   486: aload 6
    //   488: astore 4
    //   490: invokestatic 955	cn/jpush/android/util/x:d	()V
    //   493: aload 6
    //   495: astore 4
    //   497: new 1785	java/net/DatagramPacket
    //   500: dup
    //   501: aload 8
    //   503: iload_2
    //   504: aload 5
    //   506: getstatic 706	cn/jpush/android/service/PushService:U	I
    //   509: invokespecial 1788	java/net/DatagramPacket:<init>	([BILjava/net/InetAddress;I)V
    //   512: astore 5
    //   514: aload 6
    //   516: astore 4
    //   518: aload 6
    //   520: sipush 3000
    //   523: invokevirtual 1791	java/net/DatagramSocket:setSoTimeout	(I)V
    //   526: aload 6
    //   528: astore 4
    //   530: aload 6
    //   532: aload 5
    //   534: invokevirtual 1795	java/net/DatagramSocket:send	(Ljava/net/DatagramPacket;)V
    //   537: aload 6
    //   539: astore 4
    //   541: new 1785	java/net/DatagramPacket
    //   544: dup
    //   545: aload 9
    //   547: aload 9
    //   549: arraylength
    //   550: invokespecial 1798	java/net/DatagramPacket:<init>	([BI)V
    //   553: astore 5
    //   555: aload 6
    //   557: astore 4
    //   559: invokestatic 779	cn/jpush/android/util/x:c	()V
    //   562: aload 6
    //   564: astore 4
    //   566: aload 6
    //   568: aload 5
    //   570: invokevirtual 1801	java/net/DatagramSocket:receive	(Ljava/net/DatagramPacket;)V
    //   573: aload 6
    //   575: astore 4
    //   577: aload 5
    //   579: invokevirtual 1804	java/net/DatagramPacket:getLength	()I
    //   582: newarray byte
    //   584: astore 8
    //   586: aload 6
    //   588: astore 4
    //   590: aload 5
    //   592: invokevirtual 1807	java/net/DatagramPacket:getData	()[B
    //   595: iconst_0
    //   596: aload 8
    //   598: iconst_0
    //   599: aload 8
    //   601: arraylength
    //   602: invokestatic 1579	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   605: aload 6
    //   607: astore 4
    //   609: aload_0
    //   610: new 96	java/lang/String
    //   613: dup
    //   614: aload 8
    //   616: invokespecial 1137	java/lang/String:<init>	([B)V
    //   619: invokespecial 1772	cn/jpush/android/service/PushService:h	(Ljava/lang/String;)V
    //   622: aload 6
    //   624: ifnull -533 -> 91
    //   627: aload 6
    //   629: invokevirtual 1780	java/net/DatagramSocket:close	()V
    //   632: return
    //   633: astore 4
    //   635: new 812	java/lang/StringBuilder
    //   638: dup
    //   639: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   642: bipush 93
    //   644: aaload
    //   645: invokespecial 813	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   648: aload 4
    //   650: invokevirtual 1781	java/lang/AssertionError:toString	()Ljava/lang/String;
    //   653: invokevirtual 835	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   656: invokevirtual 836	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   659: pop
    //   660: invokestatic 898	cn/jpush/android/util/x:f	()V
    //   663: return
    //   664: aload 6
    //   666: astore 4
    //   668: aload 7
    //   670: invokestatic 1811	java/net/InetAddress:getByName	(Ljava/lang/String;)Ljava/net/InetAddress;
    //   673: astore 5
    //   675: goto -257 -> 418
    //   678: aload 6
    //   680: astore 4
    //   682: new 812	java/lang/StringBuilder
    //   685: dup
    //   686: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   689: bipush 91
    //   691: aaload
    //   692: invokespecial 813	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   695: getstatic 706	cn/jpush/android/service/PushService:U	I
    //   698: sipush 10000
    //   701: iadd
    //   702: invokevirtual 865	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   705: invokevirtual 836	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   708: pop
    //   709: aload 6
    //   711: astore 4
    //   713: invokestatic 955	cn/jpush/android/util/x:d	()V
    //   716: aload 6
    //   718: astore 4
    //   720: new 1785	java/net/DatagramPacket
    //   723: dup
    //   724: aload 8
    //   726: iload_2
    //   727: aload 5
    //   729: getstatic 706	cn/jpush/android/service/PushService:U	I
    //   732: sipush 10000
    //   735: iadd
    //   736: invokespecial 1788	java/net/DatagramPacket:<init>	([BILjava/net/InetAddress;I)V
    //   739: astore 5
    //   741: goto -227 -> 514
    //   744: astore 6
    //   746: aload 4
    //   748: astore 5
    //   750: aload 6
    //   752: astore 4
    //   754: aload 5
    //   756: ifnull +8 -> 764
    //   759: aload 5
    //   761: invokevirtual 1780	java/net/DatagramSocket:close	()V
    //   764: aload 4
    //   766: athrow
    //   767: aload 6
    //   769: astore 4
    //   771: sipush 9000
    //   774: putstatic 706	cn/jpush/android/service/PushService:U	I
    //   777: aload 6
    //   779: astore 4
    //   781: getstatic 716	cn/jpush/android/service/PushService:E	I
    //   784: iconst_1
    //   785: iadd
    //   786: putstatic 716	cn/jpush/android/service/PushService:E	I
    //   789: goto -445 -> 344
    //   792: astore 5
    //   794: new 812	java/lang/StringBuilder
    //   797: dup
    //   798: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   801: bipush 93
    //   803: aaload
    //   804: invokespecial 813	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   807: aload 5
    //   809: invokevirtual 1781	java/lang/AssertionError:toString	()Ljava/lang/String;
    //   812: invokevirtual 835	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   815: invokevirtual 836	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   818: pop
    //   819: invokestatic 898	cn/jpush/android/util/x:f	()V
    //   822: goto -58 -> 764
    //   825: astore 4
    //   827: aconst_null
    //   828: astore 5
    //   830: goto -76 -> 754
    //   833: astore 5
    //   835: aconst_null
    //   836: astore 6
    //   838: aload 8
    //   840: astore 7
    //   842: goto -569 -> 273
    //   845: astore 5
    //   847: aload 8
    //   849: astore 7
    //   851: goto -578 -> 273
    //   854: goto -407 -> 447
    //   857: aconst_null
    //   858: astore 5
    //   860: goto -442 -> 418
    //
    // Exception table:
    //   from	to	target	type
    //   84	91	97	java/lang/Exception
    //   178	215	271	java/lang/Exception
    //   219	222	271	java/lang/Exception
    //   226	234	271	java/lang/Exception
    //   238	245	271	java/lang/Exception
    //   249	259	271	java/lang/Exception
    //   263	271	271	java/lang/Exception
    //   410	418	271	java/lang/Exception
    //   422	428	271	java/lang/Exception
    //   432	436	271	java/lang/Exception
    //   451	459	271	java/lang/Exception
    //   463	486	271	java/lang/Exception
    //   490	493	271	java/lang/Exception
    //   497	514	271	java/lang/Exception
    //   518	526	271	java/lang/Exception
    //   530	537	271	java/lang/Exception
    //   541	555	271	java/lang/Exception
    //   559	562	271	java/lang/Exception
    //   566	573	271	java/lang/Exception
    //   577	586	271	java/lang/Exception
    //   590	605	271	java/lang/Exception
    //   609	622	271	java/lang/Exception
    //   668	675	271	java/lang/Exception
    //   682	709	271	java/lang/Exception
    //   713	716	271	java/lang/Exception
    //   720	741	271	java/lang/Exception
    //   369	374	375	java/lang/AssertionError
    //   627	632	633	java/lang/AssertionError
    //   147	154	744	finally
    //   158	174	744	finally
    //   178	215	744	finally
    //   219	222	744	finally
    //   226	234	744	finally
    //   238	245	744	finally
    //   249	259	744	finally
    //   263	271	744	finally
    //   277	299	744	finally
    //   303	306	744	finally
    //   310	316	744	finally
    //   320	323	744	finally
    //   327	335	744	finally
    //   339	344	744	finally
    //   348	355	744	finally
    //   359	364	744	finally
    //   410	418	744	finally
    //   422	428	744	finally
    //   432	436	744	finally
    //   451	459	744	finally
    //   463	486	744	finally
    //   490	493	744	finally
    //   497	514	744	finally
    //   518	526	744	finally
    //   530	537	744	finally
    //   541	555	744	finally
    //   559	562	744	finally
    //   566	573	744	finally
    //   577	586	744	finally
    //   590	605	744	finally
    //   609	622	744	finally
    //   668	675	744	finally
    //   682	709	744	finally
    //   713	716	744	finally
    //   720	741	744	finally
    //   771	777	744	finally
    //   781	789	744	finally
    //   759	764	792	java/lang/AssertionError
    //   134	143	825	finally
    //   134	143	833	java/lang/Exception
    //   147	154	845	java/lang/Exception
    //   158	174	845	java/lang/Exception
  }

  protected final void f()
  {
    x.c();
    this.aj = 0;
    while (true)
    {
      String str = (String)this.y.poll();
      if (str == null)
        break;
      new StringBuilder(bb['­']).append(str).toString();
      x.c();
      String[] arrayOfString;
      int i1;
      if (str.startsWith(bb[31]))
      {
        arrayOfString = str.split(",");
        str = str.substring(str.indexOf(",", str.indexOf(",") + 1) + 1);
        if (str != null)
        {
          i1 = PushProtocol.RepPush(this.w, t, Integer.valueOf(arrayOfString[1]).byteValue(), str);
          if (i1 >= 0)
          {
            new StringBuilder(bb[32]).append(str).toString();
            x.b();
          }
          else
          {
            new StringBuilder(bb[' ']).append(i1).toString();
            x.b();
          }
        }
      }
      else if (str.startsWith(bb['¥']))
      {
        arrayOfString = str.split(bb[8]);
        if (arrayOfString.length > 3)
        {
          try
          {
            i1 = Integer.valueOf(arrayOfString[3]).intValue();
            i2 = a(this.w, t, arrayOfString[1], arrayOfString[2], i1);
            if (i2 >= 0)
              x.c(bb[4], bb[18] + arrayOfString[2]);
          }
          catch (Exception localException1)
          {
            int i2;
            while (true)
              i1 = 0;
            a(arrayOfString[2], W, i1);
            x.c(bb[4], bb['¢'] + i2);
          }
        }
        else
        {
          new StringBuilder(bb['¡']).append(localException1).toString();
          x.f();
        }
      }
      else if (localException1.startsWith(bb['±']))
      {
        arrayOfString = localException1.split(",");
        if (arrayOfString.length > 2)
        {
          i1 = PushProtocol.EnChannel(this.w, t, arrayOfString[1], arrayOfString[2]);
          if (i1 >= 0)
          {
            new StringBuilder(bb['´']).append(arrayOfString[1]).append(bb['¦']).append(arrayOfString[2]).toString();
            x.b();
          }
          else
          {
            new StringBuilder(bb['°']).append(i1).toString();
            x.b();
          }
        }
        else
        {
          new StringBuilder(bb['¨']).append(localException1).toString();
          x.f();
        }
      }
      else if (localException1.startsWith(bb['£']))
      {
        arrayOfString = localException1.split(",");
        if (arrayOfString.length > 2)
        {
          i1 = PushProtocol.UnChnelId(this.w, t, arrayOfString[1], arrayOfString[2]);
          if (i1 >= 0)
          {
            new StringBuilder(bb['©']).append(arrayOfString[1]).append(bb['¦']).append(arrayOfString[2]).toString();
            x.b();
          }
          else
          {
            new StringBuilder(bb['®']).append(i1).toString();
            x.b();
          }
        }
        else
        {
          new StringBuilder(bb['¤']).append(localException1).toString();
          x.f();
        }
      }
      else if (localException1.startsWith(bb['«']))
      {
        arrayOfString = localException1.split(bb[8]);
        if (arrayOfString.length > 2)
        {
          i1 = PushProtocol.PushTime(this.w, t, arrayOfString[1], arrayOfString[2]);
          if (i1 >= 0)
          {
            new StringBuilder(bb['³']).append(arrayOfString[1]).append(bb['¦']).append(arrayOfString[2]).toString();
            x.b();
          }
          else
          {
            new StringBuilder(bb['¯']).append(i1).toString();
            x.b();
          }
        }
        else
        {
          new StringBuilder(bb['']).append(localException1).toString();
          x.f();
        }
      }
      else if (localException1.startsWith(bb['']))
      {
        arrayOfString = localException1.split(bb[8]);
        if (arrayOfString.length >= 2)
        {
          long l1;
          try
          {
            i1 = Integer.parseInt(arrayOfString[0]);
            l1 = Long.parseLong(arrayOfString[1]);
            new StringBuilder().append(i1).append(bb['²']).append(l1).toString();
            x.f();
            if (PushProtocol.MsgResponse(this.w, 0, t, (byte)i1, l1) == 0)
              break label982;
            a(i1, l1);
          }
          catch (Exception localException2)
          {
            localException2.getMessage();
            x.f();
          }
          continue;
          label982: new StringBuilder(bb[121]).append(l1).toString();
          x.b();
        }
        else
        {
          new StringBuilder(bb['§']).append(localException2).toString();
          x.f();
        }
      }
    }
    v();
    A();
    JSONObject localJSONObject;
    if (ae.a(getApplicationContext(), bb[26], bb[46]).equals(bb[25]))
      if (this.w != 0L)
      {
        x.d();
        localJSONObject = new JSONObject();
      }
    try
    {
      localJSONObject.put(bb[''], getPackageName());
      localJSONObject.put(bb['ª'], W);
      label1131: if (ServiceInterface.j(getApplicationContext()))
        PushProtocol.RepPush(this.w, t, (byte)4, localJSONObject.toString());
      while (true)
      {
        o();
        if (M)
          ServiceInterface.e(getApplicationContext());
        if (ad)
        {
          x.c();
          this.av.sendEmptyMessageDelayed(1002, 0L);
        }
        if (V <= 0)
        {
          V += 1;
          ae.b(getApplicationContext(), bb['¬'], V);
        }
        return;
        PushProtocol.RepPush(this.w, t, (byte)5, localJSONObject.toString());
      }
    }
    catch (JSONException localJSONException)
    {
      break label1131;
    }
  }

  protected final void g()
  {
    x.b(bb[4], bb[5]);
    a(0, bb[7]);
    if ((!ServiceInterface.j(getApplicationContext())) && (cn.jpush.android.util.a.b(getApplicationContext())))
      if (i != 86401)
        break label227;
    label227: for (int i1 = 1; ; i1 = 0)
    {
      if (i1 == 0)
      {
        int i3 = cn.jpush.android.util.a.g(getApplicationContext());
        int i2 = (int)(Math.pow(2.0D, this.aj) * 3.0D * 1000.0D);
        this.aj += 1;
        i1 = i2;
        if (i2 > i * 500)
          i1 = i * 500;
        if (((this.aj <= 5) || (i3 == 1)) && (!this.av.hasMessages(1005)) && (!this.av.hasMessages(1004)))
        {
          new StringBuilder(bb[6]).append(i1).toString();
          x.c();
          this.av.sendEmptyMessageDelayed(1005, i1);
        }
      }
      this.ai = 0;
      this.am += 1;
      if (ad)
        this.av.removeMessages(1002);
      return;
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  // ERROR //
  public void onCreate()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   4: invokestatic 1653	cn/jpush/android/service/ServiceInterface:j	(Landroid/content/Context;)Z
    //   7: ifeq +4 -> 11
    //   10: return
    //   11: invokestatic 955	cn/jpush/android/util/x:d	()V
    //   14: invokestatic 1855	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   17: invokevirtual 1856	java/lang/Thread:getId	()J
    //   20: putstatic 1858	cn/jpush/android/service/PushService:a	J
    //   23: aload_0
    //   24: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   27: invokestatic 1860	cn/jpush/android/a:a	(Landroid/content/Context;)Z
    //   30: ifeq -20 -> 10
    //   33: aload_0
    //   34: aload_0
    //   35: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   38: invokestatic 1862	cn/jpush/android/util/a:p	(Landroid/content/Context;)Z
    //   41: putfield 744	cn/jpush/android/service/PushService:ab	Z
    //   44: aload_0
    //   45: getfield 744	cn/jpush/android/service/PushService:ab	Z
    //   48: ifeq -38 -> 10
    //   51: getstatic 654	cn/jpush/android/service/PushService:ac	Z
    //   54: ifne +13 -> 67
    //   57: aload_0
    //   58: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   61: invokestatic 850	cn/jpush/android/service/PushService:c	(Landroid/content/Context;)Z
    //   64: putstatic 654	cn/jpush/android/service/PushService:ac	Z
    //   67: getstatic 654	cn/jpush/android/service/PushService:ac	Z
    //   70: ifeq +13 -> 83
    //   73: aload_0
    //   74: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   77: invokestatic 1864	cn/jpush/android/util/a:w	(Landroid/content/Context;)Z
    //   80: putstatic 1168	cn/jpush/android/a:l	Z
    //   83: invokestatic 779	cn/jpush/android/util/x:c	()V
    //   86: getstatic 1174	cn/jpush/android/a:f	Ljava/lang/String;
    //   89: invokestatic 984	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   92: ifne +9 -> 101
    //   95: getstatic 1174	cn/jpush/android/a:f	Ljava/lang/String;
    //   98: putstatic 644	cn/jpush/android/service/PushService:W	Ljava/lang/String;
    //   101: aload_0
    //   102: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   105: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   108: bipush 17
    //   110: aaload
    //   111: ldc_w 650
    //   114: invokestatic 1432	cn/jpush/android/util/ae:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   117: astore_2
    //   118: aload_2
    //   119: invokestatic 984	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   122: ifne +24 -> 146
    //   125: aload_2
    //   126: getstatic 644	cn/jpush/android/service/PushService:W	Ljava/lang/String;
    //   129: invokevirtual 1001	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   132: ifne +14 -> 146
    //   135: aload_0
    //   136: invokespecial 1866	cn/jpush/android/service/PushService:q	()V
    //   139: aload_0
    //   140: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   143: invokestatic 1868	cn/jpush/android/util/ac:b	(Landroid/content/Context;)V
    //   146: aload_0
    //   147: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   150: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   153: bipush 17
    //   155: aaload
    //   156: getstatic 644	cn/jpush/android/service/PushService:W	Ljava/lang/String;
    //   159: invokestatic 1106	cn/jpush/android/util/ae:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   162: aload_0
    //   163: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   166: invokestatic 1869	cn/jpush/android/util/ae:a	(Landroid/content/Context;)V
    //   169: aload_0
    //   170: iconst_0
    //   171: putfield 1871	cn/jpush/android/service/PushService:Y	Z
    //   174: aload_0
    //   175: iconst_0
    //   176: putfield 1873	cn/jpush/android/service/PushService:Z	Z
    //   179: getstatic 1671	cn/jpush/android/a:d	Landroid/content/Context;
    //   182: astore_3
    //   183: invokestatic 779	cn/jpush/android/util/x:c	()V
    //   186: aconst_null
    //   187: astore_2
    //   188: aload_3
    //   189: invokestatic 1876	cn/jpush/android/data/r:b	(Landroid/content/Context;)Landroid/database/Cursor;
    //   192: astore 4
    //   194: aload 4
    //   196: ifnull +317 -> 513
    //   199: aload 4
    //   201: astore_2
    //   202: aload 4
    //   204: astore_3
    //   205: aload 4
    //   207: invokeinterface 1881 1 0
    //   212: ifle +301 -> 513
    //   215: aload 4
    //   217: astore_2
    //   218: aload 4
    //   220: astore_3
    //   221: new 812	java/lang/StringBuilder
    //   224: dup
    //   225: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   228: bipush 101
    //   230: aaload
    //   231: invokespecial 813	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   234: aload 4
    //   236: invokeinterface 1881 1 0
    //   241: invokevirtual 865	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   244: invokevirtual 836	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: pop
    //   248: aload 4
    //   250: astore_2
    //   251: aload 4
    //   253: astore_3
    //   254: invokestatic 955	cn/jpush/android/util/x:d	()V
    //   257: aload 4
    //   259: astore_2
    //   260: aload 4
    //   262: astore_3
    //   263: aload 4
    //   265: invokeinterface 1884 1 0
    //   270: pop
    //   271: aload 4
    //   273: astore_2
    //   274: aload 4
    //   276: astore_3
    //   277: aload 4
    //   279: invokeinterface 1887 1 0
    //   284: ifne +229 -> 513
    //   287: aload 4
    //   289: astore_2
    //   290: aload 4
    //   292: astore_3
    //   293: aload 4
    //   295: aload 4
    //   297: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   300: bipush 102
    //   302: aaload
    //   303: invokeinterface 1890 2 0
    //   308: invokeinterface 1893 2 0
    //   313: istore_1
    //   314: aload 4
    //   316: astore_2
    //   317: aload 4
    //   319: astore_3
    //   320: aload 4
    //   322: aload 4
    //   324: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   327: bipush 100
    //   329: aaload
    //   330: invokeinterface 1890 2 0
    //   335: invokeinterface 1895 2 0
    //   340: astore 5
    //   342: aload 4
    //   344: astore_2
    //   345: aload 4
    //   347: astore_3
    //   348: aload_0
    //   349: getfield 735	cn/jpush/android/service/PushService:y	Ljava/util/Queue;
    //   352: aload 5
    //   354: invokeinterface 876 2 0
    //   359: ifeq +55 -> 414
    //   362: aload 4
    //   364: astore_2
    //   365: aload 4
    //   367: astore_3
    //   368: new 812	java/lang/StringBuilder
    //   371: dup
    //   372: getstatic 590	cn/jpush/android/service/PushService:bb	[Ljava/lang/String;
    //   375: bipush 99
    //   377: aaload
    //   378: invokespecial 813	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   381: aload 5
    //   383: invokevirtual 835	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: invokevirtual 836	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   389: pop
    //   390: aload 4
    //   392: astore_2
    //   393: aload 4
    //   395: astore_3
    //   396: invokestatic 955	cn/jpush/android/util/x:d	()V
    //   399: aload 4
    //   401: astore_2
    //   402: aload 4
    //   404: astore_3
    //   405: aload_0
    //   406: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   409: iload_1
    //   410: invokestatic 1898	cn/jpush/android/data/r:b	(Landroid/content/Context;I)Z
    //   413: pop
    //   414: aload 4
    //   416: astore_2
    //   417: aload 4
    //   419: astore_3
    //   420: aload 4
    //   422: invokeinterface 1901 1 0
    //   427: pop
    //   428: goto -157 -> 271
    //   431: astore_3
    //   432: aload_2
    //   433: astore_3
    //   434: invokestatic 1545	cn/jpush/android/util/x:j	()V
    //   437: aload_2
    //   438: ifnull +9 -> 447
    //   441: aload_2
    //   442: invokeinterface 1902 1 0
    //   447: aload_0
    //   448: getfield 1871	cn/jpush/android/service/PushService:Y	Z
    //   451: ifeq +92 -> 543
    //   454: aload_0
    //   455: iconst_1
    //   456: putfield 742	cn/jpush/android/service/PushService:aa	Z
    //   459: invokestatic 779	cn/jpush/android/util/x:c	()V
    //   462: aload_0
    //   463: iconst_1
    //   464: putfield 742	cn/jpush/android/service/PushService:aa	Z
    //   467: invokestatic 779	cn/jpush/android/util/x:c	()V
    //   470: aload_0
    //   471: getfield 742	cn/jpush/android/service/PushService:aa	Z
    //   474: ifeq +27 -> 501
    //   477: getstatic 656	cn/jpush/android/service/PushService:ad	Z
    //   480: ifeq +13 -> 493
    //   483: aload_0
    //   484: invokevirtual 1005	cn/jpush/android/service/PushService:getApplicationContext	()Landroid/content/Context;
    //   487: invokestatic 1835	cn/jpush/android/util/a:b	(Landroid/content/Context;)Z
    //   490: ifeq +7 -> 497
    //   493: aload_0
    //   494: invokespecial 947	cn/jpush/android/service/PushService:w	()V
    //   497: aload_0
    //   498: invokespecial 945	cn/jpush/android/service/PushService:y	()V
    //   501: getstatic 1381	cn/jpush/android/a:k	Z
    //   504: ifeq -494 -> 10
    //   507: aload_0
    //   508: lconst_0
    //   509: invokespecial 1385	cn/jpush/android/service/PushService:a	(J)V
    //   512: return
    //   513: aload 4
    //   515: ifnull -68 -> 447
    //   518: aload 4
    //   520: invokeinterface 1902 1 0
    //   525: goto -78 -> 447
    //   528: astore_2
    //   529: aconst_null
    //   530: astore_3
    //   531: aload_3
    //   532: ifnull +9 -> 541
    //   535: aload_3
    //   536: invokeinterface 1902 1 0
    //   541: aload_2
    //   542: athrow
    //   543: aload_0
    //   544: getfield 1873	cn/jpush/android/service/PushService:Z	Z
    //   547: ifne -88 -> 459
    //   550: aload_0
    //   551: iconst_1
    //   552: putfield 742	cn/jpush/android/service/PushService:aa	Z
    //   555: goto -96 -> 459
    //   558: astore_2
    //   559: goto -28 -> 531
    //
    // Exception table:
    //   from	to	target	type
    //   188	194	431	java/lang/Exception
    //   205	215	431	java/lang/Exception
    //   221	248	431	java/lang/Exception
    //   254	257	431	java/lang/Exception
    //   263	271	431	java/lang/Exception
    //   277	287	431	java/lang/Exception
    //   293	314	431	java/lang/Exception
    //   320	342	431	java/lang/Exception
    //   348	362	431	java/lang/Exception
    //   368	390	431	java/lang/Exception
    //   396	399	431	java/lang/Exception
    //   405	414	431	java/lang/Exception
    //   420	428	431	java/lang/Exception
    //   188	194	528	finally
    //   205	215	558	finally
    //   221	248	558	finally
    //   254	257	558	finally
    //   263	271	558	finally
    //   277	287	558	finally
    //   293	314	558	finally
    //   320	342	558	finally
    //   348	362	558	finally
    //   368	390	558	finally
    //   396	399	558	finally
    //   405	414	558	finally
    //   420	428	558	finally
    //   434	437	558	finally
  }

  public void onDestroy()
  {
    new StringBuilder(bb['']).append(Process.myPid()).toString();
    x.c();
    x();
    super.onDestroy();
    u();
    this.av.removeCallbacksAndMessages(null);
    boolean bool = ad;
    cn.jpush.android.util.a.q(getApplicationContext());
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    new StringBuilder(bb[39]).append(paramInt2).append(bb[27]).append(paramIntent).toString();
    x.b();
    if (!this.ab)
    {
      this.av.sendEmptyMessageDelayed(1003, 100L);
      return 1;
    }
    if ((ad) && (!cn.jpush.android.util.a.b(getApplicationContext())))
    {
      x.b();
      this.av.sendEmptyMessageDelayed(1003, 100L);
      return 1;
    }
    Object localObject1;
    Object localObject2;
    if (paramIntent != null)
    {
      localObject1 = paramIntent.getAction();
      localObject2 = paramIntent.getExtras();
      paramIntent = (Intent)localObject1;
      localObject1 = localObject2;
      if ((paramIntent != null) && (localObject1 != null))
      {
        if (!bb[44].equals(paramIntent))
          break label550;
        localObject2 = ((Bundle)localObject1).getString(bb[17]);
        ((Bundle)localObject1).getString(bb[53]);
        if (localObject2 != null)
          W = (String)localObject2;
        if ((this.w != 0L) && (this.X != null) && (this.X.isAlive()))
          break label533;
        new StringBuilder(bb[59]).append(this.w).toString();
        x.c();
        w();
      }
    }
    while (true)
      while (true)
      {
        label232: x.b();
        if (localObject1 != null)
        {
          new StringBuilder(bb[21]).append(((Bundle)localObject1).toString()).toString();
          x.b();
          localObject2 = ((Bundle)localObject1).getString(bb[35]);
          paramInt1 = ((Bundle)localObject1).getInt(bb[57], 0);
          if (localObject2 != null)
          {
            this.aj = 0;
            if (paramInt1 != 0)
              break label1591;
            this.av.removeMessages(1005);
            if (!this.av.hasMessages(1004))
              this.av.sendEmptyMessage(1005);
          }
          localObject2 = ((Bundle)localObject1).getString(bb[54]);
          if (localObject2 != null)
          {
            new StringBuilder(bb[40]).append((String)localObject2).toString();
            x.b();
            a(this, (String)localObject2, ((Bundle)localObject1).getBoolean(bb[19], false), ((Bundle)localObject1).getBoolean(bb[47], false)).f();
          }
        }
        boolean bool = ae.a(getApplicationContext(), bb[26], bb[46]).equals(bb[25]);
        if ((ad) || (!this.aa) || (bool))
          o();
        if ((paramIntent == null) || (localObject1 != null) || (!bb[50].equals(paramIntent)))
          break;
        y();
        this.av.removeMessages(1005);
        this.av.removeMessages(1004);
        this.av.sendEmptyMessageDelayed(1004, 500L);
        return 1;
        label533: x.b(bb[4], bb[23]);
        continue;
        label550: if (bb[43].equals(paramIntent))
        {
          if (this.aa)
          {
            b(((Bundle)localObject1).getString(bb[53]), W);
          }
          else if ((ServiceInterface.i(this) == 2) && (ServiceInterface.h(this)))
          {
            x.c();
            b(((Bundle)localObject1).getString(bb[53]), W);
          }
          else
          {
            x.c();
            localObject2 = W;
          }
        }
        else if (bb[55].equals(paramIntent))
        {
          localObject2 = ((Bundle)localObject1).getString(bb[31]);
          if (localObject2 != null)
            if (this.aa)
            {
              new StringBuilder(bb[37]).append((String)localObject2).toString();
              x.d();
              if (this.w == 0L)
              {
                g(Integer.toString(3), (String)localObject2);
              }
              else if (PushProtocol.RepPush(this.w, t, (byte)3, (String)localObject2) < 0)
              {
                g(Integer.toString(3), (String)localObject2);
              }
              else
              {
                new StringBuilder(bb[32]).append((String)localObject2).toString();
                x.b();
              }
            }
            else
            {
              x.c();
            }
        }
        else if (bb[45].equals(paramIntent))
        {
          if (((Bundle)localObject1).getInt(bb[34], -1) != -1)
            x.c();
        }
        else
        {
          String str;
          Object localObject3;
          if (bb[52].equals(paramIntent))
          {
            str = ((Bundle)localObject1).getString(bb[56]);
            localObject3 = ((Bundle)localObject1).getString(bb[30]);
            paramInt1 = ((Bundle)localObject1).getInt(bb[36], 0);
            if ((str != null) || (localObject3 != null))
            {
              localObject2 = new JSONObject();
              if (str == null);
            }
          }
          else
          {
            try
            {
              while (true)
              {
                ((JSONObject)localObject2).put(bb[56], str);
                if (localObject3 != null)
                  ((JSONObject)localObject2).put(bb[30], localObject3);
                str = ((JSONObject)localObject2).toString();
                if (((JSONObject)localObject2).length() <= 0)
                  break label232;
                if (this.aa)
                {
                  b(str, paramInt1);
                  break label232;
                }
                new StringBuilder(bb[29]).append(paramIntent).toString();
                x.c();
                break label232;
                if (bb[22].equals(paramIntent))
                {
                  if (this.aa)
                  {
                    a(((Bundle)localObject1).getString(bb[53]), W);
                    break label232;
                  }
                  new StringBuilder(bb[29]).append(paramIntent).toString();
                  x.c();
                  break label232;
                }
                if (bb[48].equals(paramIntent))
                  if (this.aa)
                  {
                    bool = ((Bundle)localObject1).getBoolean(bb[49], true);
                    str = ((Bundle)localObject1).getString(bb[33]);
                    if (bool);
                    for (localObject2 = "0"; ; localObject2 = "1")
                    {
                      if ((!ae.a(getApplicationContext(), bb[49], "0").equals(localObject2)) || (!str.equals(ae.a(getApplicationContext(), bb[33], ""))))
                        break label1205;
                      x.b(bb[4], bb[58] + str);
                      break;
                    }
                    label1205: localObject3 = new JSONObject();
                  }
                try
                {
                  while (true)
                  {
                    ((JSONObject)localObject3).put(bb[49], localObject2);
                    if (!ai.a(str))
                      ((JSONObject)localObject3).put(bb[33], str.replaceAll(bb[28], bb[42]));
                    localObject2 = ((JSONObject)localObject3).toString();
                    try
                    {
                      ae.b(getApplicationContext(), bb[49], ((JSONObject)localObject3).getString(bb[49]));
                      ae.b(getApplicationContext(), bb[33], str);
                      if ((this.w == 0L) || (t == 0L))
                      {
                        x.c();
                        this.y.offer(bb[38] + W + bb[24] + (String)localObject2);
                        w();
                      }
                    }
                    catch (Exception localException)
                    {
                      while (true)
                        x.j();
                      if (PushProtocol.PushTime(this.w, t, W, (String)localObject2) < 0)
                      {
                        this.y.offer(bb[38] + W + bb[24] + (String)localObject2);
                        w();
                        break;
                      }
                      x.c(bb[4], bb[51] + (String)localObject2);
                    }
                  }
                  break label232;
                  new StringBuilder(bb[29]).append(paramIntent).toString();
                  x.c();
                  break label232;
                  if (bb[41].equals(paramIntent))
                  {
                    u();
                    break label232;
                  }
                  if (bb[20].equals(paramIntent))
                  {
                    x.b();
                    if (this.aq)
                      break;
                    new Thread(new l(this)).start();
                    return 1;
                  }
                  x.b();
                  break label232;
                  label1591: f(paramInt1);
                }
                catch (JSONException localJSONException1)
                {
                }
              }
              continue;
              paramIntent = null;
              localObject1 = null;
            }
            catch (JSONException localJSONException2)
            {
            }
          }
        }
      }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.PushService
 * JD-Core Version:    0.6.2
 */