package com.siziksu.payment.presenter.confirmation;

import android.os.Bundle;

import com.siziksu.payment.presenter.BasePresenterContract;
import com.siziksu.payment.presenter.BaseViewContract;

public interface ConfirmationPresenterContract<V extends BaseViewContract> extends BasePresenterContract<V> {

    void checkExtras(Bundle extras);
}
