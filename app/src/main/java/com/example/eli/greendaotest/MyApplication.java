package com.example.eli.greendaotest;

import android.app.Application;
import android.content.Context;

import com.example.eli.greendaotest.db.GreenDaoManager;


/**
 * Created by anye0 on 2016/7/24.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        GreenDaoManager.getInstance();
    }

    public static Context getContext() {
        return mContext;
    }
}
