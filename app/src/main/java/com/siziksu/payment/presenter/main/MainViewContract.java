package com.siziksu.payment.presenter.main;

import com.siziksu.payment.presenter.BaseViewContract;
import com.siziksu.payment.ui.common.model.Contact;

import java.util.List;

public interface MainViewContract extends BaseViewContract {

    void onPermissionsChecked();

    void showProgress();

    void hideProgress();

    void onContacts(List<Contact> contacts);
}
