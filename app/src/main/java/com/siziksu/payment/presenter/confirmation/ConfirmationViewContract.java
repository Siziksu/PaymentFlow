package com.siziksu.payment.presenter.confirmation;

import com.siziksu.payment.presenter.BaseViewContract;
import com.siziksu.payment.ui.common.model.Contact;

import java.util.List;

public interface ConfirmationViewContract extends BaseViewContract {

    void onContacts(List<Contact> contacts, float amount);
}
