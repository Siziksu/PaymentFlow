package com.siziksu.payment.presenter.main;

import android.app.Activity;
import android.os.Bundle;

import com.siziksu.payment.App;
import com.siziksu.payment.domain.main.MainDomainContract;
import com.siziksu.payment.presenter.mapper.ContactMapper;
import com.siziksu.payment.ui.common.manager.PermissionsManager;
import com.siziksu.payment.ui.common.model.Contact;
import com.siziksu.payment.ui.common.router.RouterContract;

import java.util.List;

import javax.inject.Inject;

public final class MainPresenter implements MainPresenterContract<MainViewContract> {

    @Inject
    RouterContract router;
    @Inject
    MainDomainContract domain;
    @Inject
    PermissionsManager permissionsManager;

    private MainViewContract view;

    public MainPresenter() {
        App.get().getApplicationComponent().inject(this);
    }

    @Override
    public void onCreate(Activity activity) {
        permissionsManager.checkPermissions(activity);
    }

    @Override
    public void onResume(MainViewContract view) {
        this.view = view;
        if (domain != null) {
            domain.register();
        }
    }

    @Override
    public void onDestroy() {
        view = null;
        if (domain != null) {
            domain.unregister();
        }
    }

    @Override
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        if (view != null) {
            if (permissionsManager.areAllPermissionsGranted(requestCode, grantResults)) {
                view.onPermissionsChecked();
            }
        }
    }

    @Override
    public void start() {
        if (view == null || domain == null) { return; }
        view.showProgress();
        domain.getContacts(contacts -> {
                               if (view != null) {
                                   view.hideProgress();
                                   view.onContacts(new ContactMapper().map(contacts));
                               }
                           },
                           () -> {
                               if (view != null) {
                                   view.hideProgress();
                               }
                           });
    }

    @Override
    public void onForwardClick(List<Contact> list) {
        if (view == null) { return; }
        router.goToAmountSelectionActivity(view.getAppCompatActivity(), list);
    }
}
