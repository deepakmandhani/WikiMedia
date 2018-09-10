package com.wiki.reader.di.componenets;

import com.wiki.reader.activities.WikiSearchActivity;
import com.wiki.reader.di.modules.ApplicationModule;
import com.wiki.reader.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class })
public interface ApplicationComponent {

    void inject(WikiSearchActivity wikiSearchActivity);
}
