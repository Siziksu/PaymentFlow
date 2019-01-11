package com.siziksu.payment.dagger.module;

import com.siziksu.payment.presenter.amount.AmountPresenter;
import com.siziksu.payment.presenter.amount.AmountPresenterContract;
import com.siziksu.payment.presenter.amount.AmountViewContract;
import com.siziksu.payment.presenter.confirmation.ConfirmationPresenter;
import com.siziksu.payment.presenter.confirmation.ConfirmationPresenterContract;
import com.siziksu.payment.presenter.confirmation.ConfirmationViewContract;
import com.siziksu.payment.presenter.main.MainPresenter;
import com.siziksu.payment.presenter.main.MainPresenterContract;
import com.siziksu.payment.presenter.main.MainViewContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Singleton
    @Provides
    MainPresenterContract<MainViewContract> providesMainPresenter() {
        return new MainPresenter();
    }

    @Singleton
    @Provides
    AmountPresenterContract<AmountViewContract> providesAmountPresenterr() {
        return new AmountPresenter();
    }

    @Singleton
    @Provides
    ConfirmationPresenterContract<ConfirmationViewContract> providesConfirmationPresenterr() {
        return new ConfirmationPresenter();
    }
}
