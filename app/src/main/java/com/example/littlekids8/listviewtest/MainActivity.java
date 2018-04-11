package com.example.littlekids8.listviewtest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class MainActivity extends AppCompatActivity
{
    static int num = 0;
    static int clickCount=0 ;//菜单点击次数
    static int eggNum;//随机幸运数
    private List<AppInfo> appList = new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);
        eggNum =(int)(Math.random()*10);//同时获取一个随机数
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.egg_item:
                clickCount++;

                showToast(MainActivity.this,"you clicked "+clickCount+" times",700);
                while(eggNum==0){ eggNum =(int)(Math.random()*11);}
                if (clickCount== eggNum)
                {
                    Intent intent = new Intent(MainActivity.this,EggActivity.class);
                    startActivity(intent);
                    eggNum =(int)(Math.random()*11);//重新获取
                    clickCount = 0;
                }
                break;
            case R.id.egg_info:
                Toast.makeText(this,
                        "Find the Egg By Click the MenuItem:)"+eggNum,Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAppList();

        clickCount =0 ;

        TextView textView1 = (TextView)findViewById(R.id.app_num);
        textView1.setText("USER INSTALLED APP:"+num);

        AppInfoAdapter adapter = new AppInfoAdapter(MainActivity.this ,R.layout.app_item,appList);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                AppInfo appInfo = appList.get(position);
                Toast.makeText(MainActivity.this,
                        "open "+appInfo.getAppName(), Toast.LENGTH_SHORT).show();
                startActivity(getPackageManager().getLaunchIntentForPackage(appInfo.getPackageName()));
            }
        });
    }

    public void getAppList()
    {
        num = 0;
        PackageManager pm = getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages)
        {
            // 判断系统|非系统应用
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
            {
                String appPackageName = packageInfo.packageName;
                String appName = packageInfo.applicationInfo.loadLabel(pm).toString();
                Drawable drawable = packageInfo.applicationInfo.loadIcon(pm);
                AppInfo appInfo = new AppInfo(appName,appPackageName,drawable);
                appList.add(appInfo);
            }
        }
        num = appList.size();
    }

    //使用Handler自定义Toast的显示时长
    public static void showToast(final Activity activity, final String word, final long time){
        activity.runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(activity, word, Toast.LENGTH_LONG);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }
}
