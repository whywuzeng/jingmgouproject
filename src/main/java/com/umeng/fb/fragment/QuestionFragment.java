package com.umeng.fb.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.umeng.fb.res.e;
import com.umeng.fb.res.f;
import com.umeng.fb.util.b;

public class QuestionFragment extends Fragment
{
  private final String a = QuestionFragment.class.getName();
  private final String b = "http://fb.umeng.com/feedback_sdk_webview/questions.html?appkey=";
  private Context c;
  private WebView d;
  private String e = null;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.c = getActivity();
    this.e = ("http://fb.umeng.com/feedback_sdk_webview/questions.html?appkey=" + b.p(this.c));
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(f.n(this.c), paramViewGroup, false);
    this.d = ((WebView)paramLayoutInflater.findViewById(e.D(this.c)));
    this.d.getSettings().setJavaScriptEnabled(true);
    this.d.loadUrl(this.e);
    return paramLayoutInflater;
  }

  public void onDestroy()
  {
    super.onDestroy();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.fragment.QuestionFragment
 * JD-Core Version:    0.6.2
 */