package com.siziksu.payment.ui.common.router;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import com.siziksu.payment.R;
import com.siziksu.payment.common.Constants;
import com.siziksu.payment.ui.common.model.Contact;
import com.siziksu.payment.ui.view.amount.AmountSelectionActivity;
import com.siziksu.payment.ui.view.confirmation.ConfirmationActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class Router implements RouterContract {

    @Inject
    RouterHelper routerHelper;

    public Router(RouterHelper routerHelper) {
        this.routerHelper = routerHelper;
    }

    @Override
    public void goToAmountSelectionActivity(AppCompatActivity activity, List<Contact> list) {
        routerHelper.putParcelableArrayList(Constants.EXTRAS_CONTACTS_KEY, (ArrayList<? extends Parcelable>) list)
                .animateTransition(R.anim.slide_enter_from_right, R.anim.slide_exit_to_left)
                .route(activity, AmountSelectionActivity.class);
    }

    @Override
    public void goToConfirmationActivity(AppCompatActivity activity, List<Contact> list, float value) {
        routerHelper.putParcelableArrayList(Constants.EXTRAS_CONTACTS_KEY, (ArrayList<? extends Parcelable>) list)
                .animateTransition(R.anim.slide_enter_from_right, R.anim.slide_exit_to_left)
                .putFloat(Constants.EXTRAS_AMOUNT_KEY, value)
                .route(activity, ConfirmationActivity.class);
    }
}
