package com.baidu.location.g;

import android.util.Xml;
import com.baidu.location.b.f;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;

class a
  implements f
{
  public String b = "";
  public String c = "";
  public String d = "";
  public String e = "";
  public String f = "";
  public String g = "";
  public String h = "";
  private double i = 4.9E-324D;
  private double j = 4.9E-324D;
  private float k = 0.0F;
  private boolean l = false;
  private boolean m = true;

  public a()
  {
  }

  public a(String paramString)
  {
    try
    {
      XmlPullParser localXmlPullParser = Xml.newPullParser();
      localXmlPullParser.setInput(new StringReader(paramString));
      localXmlPullParser.next();
      int i2 = localXmlPullParser.getEventType();
      while (true)
      {
        i2 = localXmlPullParser.next();
        int i1;
        n = i1;
        if (n == 0)
        {
          i1 = n;
          if (localXmlPullParser.getName().equals("LocationRS"))
            i1 = 1;
        }
        else
        {
          paramString = localXmlPullParser.getName();
          i1 = n;
          if (paramString != null)
          {
            boolean bool = paramString.equals("latitude");
            if (bool)
            {
              try
              {
                this.i = Double.valueOf(localXmlPullParser.nextText()).doubleValue();
                i1 = n;
              }
              catch (Exception paramString)
              {
                this.m = false;
                i1 = n;
              }
            }
            else
            {
              bool = paramString.equals("longitude");
              if (bool)
              {
                try
                {
                  this.j = Double.valueOf(localXmlPullParser.nextText()).doubleValue();
                  i1 = n;
                }
                catch (Exception paramString)
                {
                  this.m = false;
                  i1 = n;
                }
              }
              else
              {
                bool = paramString.equals("hpe");
                if (bool)
                {
                  try
                  {
                    this.k = Float.valueOf(localXmlPullParser.nextText()).floatValue();
                    i1 = n;
                  }
                  catch (Exception paramString)
                  {
                    this.m = false;
                    i1 = n;
                  }
                }
                else
                {
                  bool = paramString.equals("country");
                  if (bool);
                  try
                  {
                    this.d = localXmlPullParser.getAttributeValue(0);
                    try
                    {
                      label334: this.c = localXmlPullParser.nextText();
                      i1 = n;
                    }
                    catch (Exception paramString)
                    {
                      i1 = n;
                    }
                    continue;
                    bool = paramString.equals("province");
                    if (bool)
                    {
                      try
                      {
                        this.e = localXmlPullParser.nextText();
                        i1 = n;
                      }
                      catch (Exception paramString)
                      {
                        i1 = n;
                      }
                      continue;
                    }
                    bool = paramString.equals("region");
                    if (bool)
                    {
                      try
                      {
                        this.f = localXmlPullParser.nextText();
                        i1 = n;
                      }
                      catch (Exception paramString)
                      {
                        i1 = n;
                      }
                      continue;
                    }
                    bool = paramString.equals("street-number");
                    if (bool)
                    {
                      try
                      {
                        this.h = localXmlPullParser.nextText();
                        i1 = n;
                      }
                      catch (Exception paramString)
                      {
                        i1 = n;
                      }
                      continue;
                    }
                    bool = paramString.equals("city");
                    if (bool)
                    {
                      try
                      {
                        this.b = localXmlPullParser.nextText();
                        i1 = n;
                      }
                      catch (Exception paramString)
                      {
                        i1 = n;
                      }
                      continue;
                    }
                    bool = paramString.equals("address-line");
                    if (bool)
                    {
                      try
                      {
                        this.g = localXmlPullParser.nextText();
                        i1 = n;
                      }
                      catch (Exception paramString)
                      {
                        i1 = n;
                      }
                      continue;
                    }
                    i1 = n;
                    if (!paramString.equals("error"))
                      continue;
                    this.m = false;
                    i1 = n;
                  }
                  catch (Exception paramString)
                  {
                    break label334;
                  }
                  if (i2 == 1)
                    break;
                  i1 = n;
                  switch (i2)
                  {
                  case 0:
                  case 1:
                  case 3:
                  case 2:
                  }
                  i1 = n;
                }
              }
            }
          }
        }
      }
    }
    catch (Exception paramString)
    {
    }
  }

  public boolean a()
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (!this.c.equals("China"))
    {
      bool1 = bool2;
      if (!this.c.equals("Taiwan"))
      {
        bool1 = bool2;
        if (!this.d.equals("HK"))
          bool1 = false;
      }
    }
    return bool1;
  }

  public boolean b()
  {
    return this.m;
  }

  public double c()
  {
    return this.i;
  }

  public double d()
  {
    return this.j;
  }

  public float e()
  {
    return this.k;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.g.a
 * JD-Core Version:    0.6.2
 */