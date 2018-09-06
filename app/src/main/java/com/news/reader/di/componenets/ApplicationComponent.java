package com.news.reader.di.componenets;

import com.news.reader.activities.NewsSearchActivity;
import com.news.reader.di.modules.ApplicationModule;
import com.news.reader.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class })
public interface ApplicationComponent {

    void inject(NewsSearchActivity newsSearchActivity);
}
