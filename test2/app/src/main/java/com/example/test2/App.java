package com.example.test2;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context mContext;
    private static boolean changed;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context montext) {
        mContext = montext;
    }

    public static boolean getChange(){return changed;}

    public static void setChange(boolean change){changed = change; }

}
