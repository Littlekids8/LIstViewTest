package com.example.littlekids8.listviewtest;

import android.graphics.drawable.Drawable;

public class AppInfo
{
     String appName;
     String packageName;
     Drawable drawable;

    public  AppInfo(String appName,String packageName,Drawable drawable)
    {
        this.appName = appName;
        this.packageName = packageName;
        this.drawable = drawable;
    }

    public String getAppName()
    {
        return appName;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public Drawable getDrawable()
    {
        return drawable;
    }


}
