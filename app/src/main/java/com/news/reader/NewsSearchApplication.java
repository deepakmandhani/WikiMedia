package com.news.reader;

import android.app.Application;

import com.news.reader.di.componenets.ApplicationComponent;
import com.news.reader.di.componenets.DaggerApplicationComponent;
import com.news.reader.di.modules.ApplicationModule;
import com.news.reader.di.modules.NetworkModule;

public class NewsSearchApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .networkModule(new NetworkModule())
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
