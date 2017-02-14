package com.squareup.wire;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

final class a<B extends Message.Builder>
{
  private static final int a = "$Builder".length();
  private static final Comparator<Field> b = new Comparator()
  {
    public int a(Field paramAnonymousField1, Field paramAnonymousField2)
    {
      return paramAnonymousField1.getName().compareTo(paramAnonymousField2.getName());
    }
  };
  private final List<Field> c;

  // ERROR //
  public a(java.lang.Class<B> paramClass)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 39	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: new 41	java/util/ArrayList
    //   8: dup
    //   9: invokespecial 42	java/util/ArrayList:<init>	()V
    //   12: putfield 44	com/squareup/wire/a:c	Ljava/util/List;
    //   15: aload_1
    //   16: invokevirtual 50	java/lang/Class:getName	()Ljava/lang/String;
    //   19: astore 4
    //   21: aload 4
    //   23: iconst_0
    //   24: aload 4
    //   26: invokevirtual 25	java/lang/String:length	()I
    //   29: getstatic 27	com/squareup/wire/a:a	I
    //   32: isub
    //   33: invokevirtual 54	java/lang/String:substring	(II)Ljava/lang/String;
    //   36: astore 5
    //   38: aload 5
    //   40: invokestatic 58	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   43: astore 5
    //   45: aload 5
    //   47: invokevirtual 62	java/lang/Class:getDeclaredFields	()[Ljava/lang/reflect/Field;
    //   50: astore 5
    //   52: aload 5
    //   54: arraylength
    //   55: istore_3
    //   56: iconst_0
    //   57: istore_2
    //   58: iload_2
    //   59: iload_3
    //   60: if_icmpge +126 -> 186
    //   63: aload 5
    //   65: iload_2
    //   66: aaload
    //   67: astore 4
    //   69: aload 4
    //   71: ldc 64
    //   73: invokevirtual 70	java/lang/reflect/Field:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   76: checkcast 64	com/squareup/wire/ProtoField
    //   79: astore 6
    //   81: aload 6
    //   83: ifnull +35 -> 118
    //   86: aload 6
    //   88: invokeinterface 74 1 0
    //   93: getstatic 80	com/squareup/wire/Message$Label:REQUIRED	Lcom/squareup/wire/Message$Label;
    //   96: if_acmpne +22 -> 118
    //   99: aload_0
    //   100: getfield 44	com/squareup/wire/a:c	Ljava/util/List;
    //   103: aload_1
    //   104: aload 4
    //   106: invokevirtual 81	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   109: invokevirtual 85	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   112: invokeinterface 91 2 0
    //   117: pop
    //   118: iload_2
    //   119: iconst_1
    //   120: iadd
    //   121: istore_2
    //   122: goto -64 -> 58
    //   125: astore_1
    //   126: new 93	java/lang/AssertionError
    //   129: dup
    //   130: new 95	java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   137: ldc 98
    //   139: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload 4
    //   144: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   150: invokespecial 108	java/lang/AssertionError:<init>	(Ljava/lang/Object;)V
    //   153: athrow
    //   154: astore_1
    //   155: new 93	java/lang/AssertionError
    //   158: dup
    //   159: new 95	java/lang/StringBuilder
    //   162: dup
    //   163: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   166: ldc 110
    //   168: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: aload 4
    //   173: invokevirtual 81	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   176: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: invokespecial 108	java/lang/AssertionError:<init>	(Ljava/lang/Object;)V
    //   185: athrow
    //   186: aload_0
    //   187: getfield 44	com/squareup/wire/a:c	Ljava/util/List;
    //   190: getstatic 32	com/squareup/wire/a:b	Ljava/util/Comparator;
    //   193: invokestatic 116	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   196: return
    //
    // Exception table:
    //   from	to	target	type
    //   38	45	125	java/lang/ClassNotFoundException
    //   99	118	154	java/lang/NoSuchFieldException
  }

  public <B extends Message.Builder> void a(B paramB)
  {
    Object localObject2 = null;
    Object localObject1 = "";
    while (true)
    {
      int i;
      Object localObject3;
      Object localObject4;
      try
      {
        int j = this.c.size();
        i = 0;
        if (i < j)
        {
          Field localField = (Field)this.c.get(i);
          localObject3 = localObject1;
          localObject4 = localObject2;
          if (localField.get(paramB) != null)
            break label155;
          if (localObject2 != null)
            break label170;
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("\n  ");
          ((StringBuilder)localObject2).append(localField.getName());
          localObject3 = localObject1;
          localObject4 = localObject2;
          break label155;
        }
        if (localObject2 != null)
          throw new IllegalStateException("Required field" + (String)localObject1 + " not set:" + localObject2);
      }
      catch (IllegalAccessException paramB)
      {
        throw new AssertionError("Unable to access required fields");
      }
      return;
      label155: i += 1;
      localObject2 = localObject4;
      localObject1 = localObject3;
      continue;
      label170: localObject1 = "s";
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.a
 * JD-Core Version:    0.6.2
 */