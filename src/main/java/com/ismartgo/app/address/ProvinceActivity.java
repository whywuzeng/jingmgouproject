// INTERNAL ERROR //

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.address.ProvinceActivity
 * JD-Core Version:    0.6.2
 */
package com.ismartgo.app.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.bean.Address;
import com.ismartgo.app.tools.CityDBManager;
import com.umeng.analytics.MobclickAgent;
import java.io.PrintStream;
import java.util.ArrayList;

public class ProvinceActivity
        extends BaseActivity
{
    public static String areaId = "";
    public static String cityId;
    public static String provinceId;
    public static int regionRequestCode = 10000;
    public static int regionResultCode = 10001;
    private ListRegionAdapter adapter;
    private Context context;
    private ArrayList<Address> list;
    @AbIocView(id=2131362121)
    private ListView listview_region;
    String title;

    static
    {
        provinceId = "";
        cityId = "";
    }

    private void initView()
    {
        initTitleBar();
        CityDBManager localCityDBManager = new CityDBManager(this.context);
        final String str = getIntent().getStringExtra("title");
        boolean bool;
        if (str.equals("select_province"))
        {
            PrintStream localPrintStream = System.out;
            StringBuilder localStringBuilder = new StringBuilder("--->tv_title=");
            if (this.tv_title == null)
            {
                bool = true;
                localPrintStream.println(bool);
                this.tv_title.setText("选择省份");
                this.list = localCityDBManager.getRovinceList();
            }
        }
        for (;;)
        {
            this.tv_left.setVisibility(0);
            this.adapter = new ListRegionAdapter(this, this.list);
            this.listview_region.setAdapter(this.adapter);
            this.listview_region.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
                {
                    paramAnonymousAdapterView = new Intent(ProvinceActivity.this.context, ProvinceActivity.class);
                    if (str.equals("select_province"))
                    {
                        paramAnonymousAdapterView.putExtra("title", "select_city");
                        ProvinceActivity.provinceId = ((Address)ProvinceActivity.this.list.get(paramAnonymousInt)).getId();
                        paramAnonymousAdapterView.putExtra("id", ProvinceActivity.provinceId);
                        ProvinceActivity.this.startActivityForResult(paramAnonymousAdapterView, ProvinceActivity.regionRequestCode);
                    }
                    do
                    {
                        return;
                        if (str.equals("select_city"))
                        {
                            paramAnonymousAdapterView.putExtra("title", "select_area");
                            ProvinceActivity.cityId = ((Address)ProvinceActivity.this.list.get(paramAnonymousInt)).getId();
                            paramAnonymousAdapterView.putExtra("id", ProvinceActivity.cityId);
                            ProvinceActivity.this.startActivityForResult(paramAnonymousAdapterView, ProvinceActivity.regionRequestCode);
                            return;
                        }
                    } while (!str.equals("select_area"));
                    paramAnonymousAdapterView = new Intent();
                    ProvinceActivity.areaId = ((Address)ProvinceActivity.this.list.get(paramAnonymousInt)).getId();
                    paramAnonymousAdapterView.putExtra("id", ProvinceActivity.provinceId + "," + ProvinceActivity.cityId + "," + ProvinceActivity.areaId);
                    ProvinceActivity.this.setResult(ProvinceActivity.regionResultCode, paramAnonymousAdapterView);
                    ProvinceActivity.this.finish();
                }
            });
            return;
            bool = false;
            break;
            if (str.equals("select_city"))
            {
                this.tv_title.setText("选择城市");
                this.list = localCityDBManager.getCityList(getIntent().getStringExtra("id"));
            }
            else if (str.equals("select_area"))
            {
                this.tv_title.setText("选择地区");
                this.list = localCityDBManager.getAreaList(getIntent().getStringExtra("id"));
            }
        }
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if ((paramInt1 == regionRequestCode) && (paramInt2 == regionResultCode))
        {
            setResult(paramInt2, paramIntent);
            finish();
        }
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130903091);
        this.context = this;
        initView();
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
}
