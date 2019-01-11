package com.siziksu.payment;

import android.app.Application;

import com.siziksu.payment.dagger.component.ApplicationComponent;
import com.siziksu.payment.dagger.component.DaggerApplicationComponent;
import com.siziksu.payment.dagger.module.ApplicationModule;
import com.siziksu.payment.dagger.module.DomainModule;
import com.siziksu.payment.dagger.module.PresenterModule;

public final class App extends Application {

    private ApplicationComponent applicationComponent;

    private static App app;

    public static App get() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initDagger();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .domainModule(new DomainModule())
                .presenterModule(new PresenterModule())
                .build();
        applicationComponent.inject(this);
    }
}
