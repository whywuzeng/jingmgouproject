package cn.sharesdk.framework.utils;

class e
  implements Appendable
{
  int a = -1;
  char[] b = new char[2];

  e(d paramd, Appendable paramAppendable)
  {
  }

  private void a(char[] paramArrayOfChar, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      this.c.append(paramArrayOfChar[i]);
      i += 1;
    }
  }

  public Appendable append(char paramChar)
  {
    if (this.a != -1)
    {
      if (!Character.isLowSurrogate(paramChar))
        throw new IllegalArgumentException("Expected low surrogate character but got '" + paramChar + "' with value " + paramChar);
      arrayOfChar = this.d.a(Character.toCodePoint((char)this.a, paramChar));
      if (arrayOfChar != null)
        a(arrayOfChar, arrayOfChar.length);
      while (true)
      {
        this.a = -1;
        return this;
        this.c.append((char)this.a);
        this.c.append(paramChar);
      }
    }
    if (Character.isHighSurrogate(paramChar))
    {
      this.a = paramChar;
      return this;
    }
    if (Character.isLowSurrogate(paramChar))
      throw new IllegalArgumentException("Unexpected low surrogate character '" + paramChar + "' with value " + paramChar);
    char[] arrayOfChar = this.d.a(paramChar);
    if (arrayOfChar != null)
    {
      a(arrayOfChar, arrayOfChar.length);
      return this;
    }
    this.c.append(paramChar);
    return this;
  }

  public Appendable append(CharSequence paramCharSequence)
  {
    return append(paramCharSequence, 0, paramCharSequence.length());
  }

  public Appendable append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i;
    char[] arrayOfChar;
    if (paramInt1 < paramInt2)
    {
      if (this.a == -1)
        break label257;
      i = paramInt1 + 1;
      char c1 = paramCharSequence.charAt(paramInt1);
      if (!Character.isLowSurrogate(c1))
        throw new IllegalArgumentException("Expected low surrogate character but got " + c1);
      arrayOfChar = this.d.a(Character.toCodePoint((char)this.a, c1));
      if (arrayOfChar == null)
        break label146;
      a(arrayOfChar, arrayOfChar.length);
      paramInt1 += 1;
      this.a = -1;
    }
    while (true)
    {
      i = this.d.a(paramCharSequence, i, paramInt2);
      if (i > paramInt1)
        this.c.append(paramCharSequence, paramInt1, i);
      if (i == paramInt2)
      {
        return this;
        label146: this.c.append((char)this.a);
        break;
      }
      paramInt1 = d.b(paramCharSequence, i, paramInt2);
      if (paramInt1 < 0)
      {
        this.a = (-paramInt1);
        return this;
      }
      arrayOfChar = this.d.a(paramInt1);
      if (arrayOfChar != null)
      {
        a(arrayOfChar, arrayOfChar.length);
        label208: if (!Character.isSupplementaryCodePoint(paramInt1))
          break label252;
      }
      label252: for (paramInt1 = 2; ; paramInt1 = 1)
      {
        paramInt1 = i + paramInt1;
        i = paramInt1;
        break;
        int j = Character.toChars(paramInt1, this.b, 0);
        a(this.b, j);
        break label208;
      }
      label257: i = paramInt1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.utils.e
 * JD-Core Version:    0.6.2
 */