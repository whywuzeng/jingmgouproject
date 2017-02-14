package com.ismartgo.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.utils.RequestTask;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends BaseActivity
{
  private SimpleAdapter adapter;

  @AbIocView(id=2131231034)
  private TextView btn_clear_history;
  private MyAjaxCallback callback;

  @AbIocView(id=2131231028)
  private EditText et_search;
  private String[] hisArrays;
  private final int historyNum = 10;

  @AbIocView(id=2131231031)
  private LinearLayout history_tag;

  @AbIocView(id=2131231033)
  private LinearLayout layout_clear_history;

  @AbIocView(id=2131231027)
  private View layout_search;

  @AbIocView(id=2131231030)
  private View layout_search_history;

  @AbIocView(id=2131231032)
  private ListView lv_search_history;
  private AjaxParams mAjaxParams;
  private MyDialog mDialog;
  private RequestTask mRequestTask;
  private PopupWindow popWin;
  private List<Map<String, String>> searchList;

  @AbIocView(id=2131231026)
  private LinearLayout title_layout;
  private TextView tvSearch;
  private int type;
  private LinkedList<String> typeList = new LinkedList();

  private boolean cancelPop()
  {
    if ((this.popWin != null) && (this.popWin.isShowing()))
    {
      this.popWin.dismiss();
      this.popWin = null;
      return true;
    }
    return false;
  }

  private void getSearchHistory()
  {
    this.layout_search_history.setVisibility(0);
    Object localObject1 = getSharedPreferences("search_history", 0).getString("history", "");
    if (!TextUtils.isEmpty((CharSequence)localObject1))
      this.searchList = new ArrayList();
    int i;
    if (((String)localObject1).indexOf(',') != -1)
    {
      this.history_tag.setVisibility(0);
      this.hisArrays = ((String)localObject1).split(",");
      localObject1 = this.hisArrays;
      int j = localObject1.length;
      i = 0;
      if (i >= j)
        this.layout_clear_history.setVisibility(0);
    }
    for (this.adapter = new SimpleAdapter(this, this.searchList, 2130903156, new String[] { "item" }, new int[] { 2131231199 }); ; this.adapter = null)
    {
      this.lv_search_history.setAdapter(this.adapter);
      return;
      Object localObject2 = localObject1[i];
      HashMap localHashMap = new HashMap();
      localHashMap.put("item", localObject2);
      if (this.searchList == null)
        this.searchList = new ArrayList();
      this.searchList.add(localHashMap);
      i += 1;
      break;
      this.history_tag.setVisibility(4);
      this.layout_clear_history.setVisibility(8);
    }
  }

  private void initView()
  {
    initTitleBar();
    this.et_search.setFocusable(true);
    this.et_search.requestFocus();
    this.et_search.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if ((paramAnonymousInt == 3) || ((paramAnonymousKeyEvent != null) && (paramAnonymousKeyEvent.getKeyCode() == 66)))
        {
          SearchActivity.this.requestSearchType(SearchActivity.this.et_search.getText().toString().trim());
          return true;
        }
        return false;
      }
    });
    this.lv_search_history.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        SearchActivity.this.et_search.setText(SearchActivity.this.hisArrays[paramAnonymousInt]);
        SearchActivity.this.requestSearchType(SearchActivity.this.hisArrays[paramAnonymousInt]);
      }
    });
    this.tvSearch = ((TextView)findViewById(2131231029));
    this.tvSearch.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SearchActivity.this.requestSearchType(SearchActivity.this.et_search.getText().toString().trim());
      }
    });
  }

  private void onSelect(TextView paramTextView, String paramString)
  {
    cancelPop();
    paramTextView.setText(paramString);
    if (paramString.equals("商品"))
      this.type = 2;
    while (true)
    {
      getSearchHistory();
      return;
      if (paramString.equals("商店"))
        this.type = 1;
    }
  }

  private void requestSearchType(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return;
    this.mDialog.show();
    HashMap localHashMap = new HashMap();
    localHashMap.put("searchname", paramString);
    HttpRequest.getInstance().executePostStringRequest(this, Url.GET_SEARCH_TYPE, HttpWhat.SEARCH_TYPE, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        SearchActivity.this.mDialog.dismiss();
        Toast.makeText(SearchActivity.this, "亲，网络好像出问题了~", 1).show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        SearchActivity.this.mDialog.dismiss();
        Log.i("Test", "t= " + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(String.valueOf(((String)paramAnonymousResponse.get()).toString()));
          if (paramAnonymousResponse.getInt("status") != 10001)
          {
            Toast.makeText(SearchActivity.this, "搜索失败", 0).show();
            Log.e("Search", paramAnonymousResponse.getString("msg"));
            return;
          }
          paramAnonymousInt = paramAnonymousResponse.getJSONObject("data").getInt("type");
          if (paramAnonymousInt == 1)
            SearchActivity.this.type = 1;
        }
        catch (JSONException paramAnonymousResponse)
        {
          try
          {
            while (true)
            {
              MobclickAgent.onEvent(SearchActivity.this, "search");
              SearchActivity.this.search();
              return;
              if (paramAnonymousInt == 2)
              {
                SearchActivity.this.type = 2;
                continue;
                paramAnonymousResponse = paramAnonymousResponse;
                Log.i("Test", "exception: " + paramAnonymousResponse.getMessage());
                paramAnonymousResponse.printStackTrace();
              }
            }
          }
          catch (Exception paramAnonymousResponse)
          {
            while (true)
              paramAnonymousResponse.printStackTrace();
          }
        }
      }
    });
  }

  private void saveHistory(String paramString)
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("search_history", 0);
    Object localObject1 = localSharedPreferences.getString("history", "");
    if (!((String)localObject1).contains(paramString + ","))
    {
      Object localObject2 = localObject1;
      if (((String)localObject1).split(",").length == 10)
      {
        localObject2 = localObject1;
        if (((String)localObject1).endsWith(","))
          localObject2 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
        localObject2 = ((String)localObject2).substring(0, ((String)localObject2).lastIndexOf(','));
        System.out.println("--->history=" + (String)localObject2);
      }
      localObject1 = new StringBuilder((String)localObject2);
      ((StringBuilder)localObject1).insert(0, paramString + ",");
      localSharedPreferences.edit().putString("history", ((StringBuilder)localObject1).toString()).commit();
    }
  }

  private void search()
  {
    String str = this.et_search.getText().toString().trim();
    if (TextUtils.isEmpty(str))
    {
      this.toast.setMessage("您还未输入搜索关键字哦...");
      this.toast.show();
      return;
    }
    saveHistory(str);
    Intent localIntent;
    if (this.type == 2)
    {
      localIntent = new Intent(this, SearchForPromotionActivity.class);
      localIntent.putExtra("goods", str);
      startActivity(localIntent);
    }
    while (true)
    {
      finish();
      return;
      if (this.type == 1)
      {
        PromotionActivity.isClickInto = false;
        localIntent = new Intent(this, SearchStorePromotionActivity.class);
        localIntent.putExtra("shop", str);
        startActivity(localIntent);
      }
    }
  }

  public void clearHistorySearch(View paramView)
  {
    getSharedPreferences("search_history", 0).edit().putString("history", "").commit();
    this.lv_search_history.setAdapter(null);
    this.history_tag.setVisibility(4);
    paramView.setVisibility(8);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903085);
    this.mDialog = new MyDialog(this);
    initView();
    getSearchHistory();
    ((InputMethodManager)getSystemService("input_method")).toggleSoftInput(0, 2);
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    cancelPop();
    return super.onTouchEvent(paramMotionEvent);
  }

  public void search(View paramView)
  {
    search();
  }

  private class MyAjaxCallback extends AjaxCallBack
  {
    private MyAjaxCallback()
    {
    }

    public void onFailure(Throwable paramThrowable, int paramInt, String paramString)
    {
      super.onFailure(paramThrowable, paramInt, paramString);
    }

    public void onSuccess(Object paramObject)
    {
      super.onSuccess(paramObject);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.SearchActivity
 * JD-Core Version:    0.6.2
 */