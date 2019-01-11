package com.siziksu.payment.presenter.amount;

import android.os.Bundle;
import android.widget.EditText;

import com.siziksu.payment.presenter.BasePresenterContract;
import com.siziksu.payment.presenter.BaseViewContract;

public interface AmountPresenterContract<V extends BaseViewContract> extends BasePresenterContract<V> {

    void checkExtras(Bundle extras);

    void amountEditText(EditText editText);

    void onNextClick();
}
