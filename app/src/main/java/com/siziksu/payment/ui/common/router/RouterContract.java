package com.siziksu.payment.ui.common.router;

import android.support.v7.app.AppCompatActivity;

import com.siziksu.payment.ui.common.model.Contact;

import java.util.List;

public interface RouterContract {

    void goToAmountSelectionActivity(AppCompatActivity activity, List<Contact> list);

    void goToConfirmationActivity(AppCompatActivity activity, List<Contact> list, float value);
}
