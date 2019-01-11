package com.siziksu.payment.presenter.confirmation;

import android.app.Activity;
import android.os.Bundle;

import com.siziksu.payment.common.Constants;
import com.siziksu.payment.ui.common.model.Contact;

import java.util.List;

public final class ConfirmationPresenter implements ConfirmationPresenterContract<ConfirmationViewContract> {

    private ConfirmationViewContract view;

    public ConfirmationPresenter() {}

    @Override
    public void onCreate(Activity activity) {}

    @Override
    public void onResume(ConfirmationViewContract view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void checkExtras(Bundle extras) {
        if (view == null) { return; }
        if (extras != null && extras.containsKey(Constants.EXTRAS_CONTACTS_KEY) && extras.containsKey(Constants.EXTRAS_AMOUNT_KEY)) {
            List<Contact> contacts = extras.getParcelableArrayList(Constants.EXTRAS_CONTACTS_KEY);
            float amount = extras.getFloat(Constants.EXTRAS_AMOUNT_KEY);
            view.onContacts(contacts, amount);
        }
    }
}
