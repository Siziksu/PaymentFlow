package com.siziksu.payment.presenter.amount;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.siziksu.payment.App;
import com.siziksu.payment.common.Constants;
import com.siziksu.payment.ui.common.manager.PermissionsManager;
import com.siziksu.payment.ui.common.model.Contact;
import com.siziksu.payment.ui.common.router.RouterContract;

import java.util.List;

import javax.inject.Inject;

public final class AmountPresenter implements AmountPresenterContract<AmountViewContract> {

    @Inject
    RouterContract router;
    @Inject
    PermissionsManager permissionsManager;
    @Inject
    TextValidator validator;

    private AmountViewContract view;
    private List<Contact> contacts;

    public AmountPresenter() {
        App.get().getApplicationComponent().inject(this);
    }

    @Override
    public void onCreate(Activity activity) {
        validator.init();
    }

    @Override
    public void onResume(AmountViewContract view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void checkExtras(Bundle extras) {
        if (extras != null && extras.containsKey(Constants.EXTRAS_CONTACTS_KEY)) {
            contacts = extras.getParcelableArrayList(Constants.EXTRAS_CONTACTS_KEY);
        }
    }

    @Override
    public void amountEditText(EditText editText) {
        validator.validateAmountEditText(editText);
    }

    @Override
    public void onNextClick() {
        if (view == null) { return; }
        router.goToConfirmationActivity(view.getAppCompatActivity(), contacts, validator.getValue());
    }
}
