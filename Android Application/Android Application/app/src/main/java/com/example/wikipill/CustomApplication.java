package com.example.wikipill;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;

import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate;
import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.wikipill.R;

public class CustomApplication extends Application {
    LocalizationApplicationDelegate localizationDelegate = new LocalizationApplicationDelegate();
    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(localizationDelegate.attachBaseContext(base));
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localizationDelegate.onConfigurationChanged(this);
    }
    @Override
    public Context getApplicationContext() {
        return localizationDelegate.getApplicationContext(super.getApplicationContext());
    }
}

