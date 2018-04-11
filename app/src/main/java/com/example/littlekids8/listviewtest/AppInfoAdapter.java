package com.example.littlekids8.listviewtest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AppInfoAdapter extends ArrayAdapter<AppInfo> {
    private int resourceId;

    public AppInfoAdapter(Context context,int TextViewResourceId,List<AppInfo> objects)
    {
        super(context,TextViewResourceId,objects);
        resourceId = TextViewResourceId;
    }
    @Override
    public View getView(int position, View convertView , ViewGroup parent)
    {
        //getView方法在每个子项被滚到屏幕内时被调用
        AppInfo appInfo = getItem(position);//获取当前项的appInfo实例、

        View view;
        ViewHolder viewHolder;
        if(convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.appIcon = (ImageView) view.findViewById(R.id.app_icon);
            viewHolder.appName = (TextView)view.findViewById(R.id.app_name);
            viewHolder.packageName=(TextView)view.findViewById(R.id.package_name) ;
            view.setTag(viewHolder);//将ViewHolder存储在View中
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();//重新获取viewholder
        }

        viewHolder.appIcon.setImageDrawable(appInfo.getDrawable());
        viewHolder.appName.setText(appInfo.getAppName());
        viewHolder.packageName.setText("\n"+appInfo.getPackageName());
        return view;


    }
    public class ViewHolder
    {
        ImageView appIcon;
        TextView appName;
        TextView packageName;
    }

}
