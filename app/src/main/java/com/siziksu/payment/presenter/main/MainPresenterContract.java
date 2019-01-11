package com.siziksu.payment.presenter.main;

import com.siziksu.payment.presenter.BasePresenterContract;
import com.siziksu.payment.presenter.BaseViewContract;
import com.siziksu.payment.ui.common.model.Contact;

import java.util.List;

public interface MainPresenterContract<V extends BaseViewContract> extends BasePresenterContract<V> {

    void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults);

    void start();

    void onForwardClick(List<Contact> list);
}
