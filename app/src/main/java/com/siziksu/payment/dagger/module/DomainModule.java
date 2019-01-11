package com.siziksu.payment.dagger.module;

import com.siziksu.payment.domain.main.MainDomain;
import com.siziksu.payment.domain.main.MainDomainContract;

import dagger.Module;
import dagger.Provides;

@Module
public final class DomainModule {

    @Provides
    MainDomainContract providesMainDomain() {
        return new MainDomain();
    }
}
