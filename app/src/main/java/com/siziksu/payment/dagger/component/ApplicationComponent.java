package com.siziksu.payment.dagger.component;

import com.siziksu.payment.App;
import com.siziksu.payment.dagger.module.ApplicationModule;
import com.siziksu.payment.dagger.module.DataModule;
import com.siziksu.payment.dagger.module.DomainModule;
import com.siziksu.payment.dagger.module.PresenterModule;
import com.siziksu.payment.data.Repository;
import com.siziksu.payment.data.persistence.android.ContactClient;
import com.siziksu.payment.domain.main.MainDomain;
import com.siziksu.payment.presenter.amount.AmountPresenter;
import com.siziksu.payment.presenter.confirmation.ConfirmationPresenter;
import com.siziksu.payment.presenter.main.MainPresenter;
import com.siziksu.payment.ui.view.amount.AmountSelectionActivity;
import com.siziksu.payment.ui.view.confirmation.ConfirmationActivity;
import com.siziksu.payment.ui.view.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class,
                DomainModule.class,
                PresenterModule.class,
        }
)
public interface ApplicationComponent {

    void inject(App target);

    void inject(MainActivity target);

    void inject(MainPresenter target);

    void inject(MainDomain target);

    void inject(Repository target);

    void inject(ContactClient target);

    void inject(AmountSelectionActivity target);

    void inject(AmountPresenter target);

    void inject(ConfirmationPresenter target);

    void inject(ConfirmationActivity target);
}
