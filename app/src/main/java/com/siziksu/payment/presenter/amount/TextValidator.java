package com.siziksu.payment.presenter.amount;

import android.annotation.SuppressLint;
import android.widget.EditText;

import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

final class TextValidator {

    private static final long CLICK_INTERVAL = 100;
    private static final float MAX_VALUE = 1000;

    private String lastValue;
    private EditText editText;

    @Inject
    TextValidator() {}

    float getValue() {
        return lastValue == null || lastValue.length() == 0 ? 0 : Float.valueOf(lastValue);
    }

    void init() {
        lastValue = "";
    }

    @SuppressLint("CheckResult")
    void validateAmountEditText(EditText editText) {
        this.editText = editText;
        streamFromField(editText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::checkCharSequence,
                        throwable -> {}
                );
    }

    private Observable<CharSequence> streamFromField(EditText editText) {
        return RxTextView
                .textChanges(editText)
                .skipInitialValue()
                .distinct()
                .debounce(CLICK_INTERVAL, TimeUnit.MILLISECONDS);
    }

    private void checkCharSequence(CharSequence charSequence) {
        String temp = charSequence.toString();
        if (temp.length() != 0) {
            if (temp.matches("^\\.(\\d{1,2})?$")) {
                String value = "0" + temp;
                lastValue = value;
                setValue(value);
            } else if (temp.matches("^\\d{1,4}(\\.)?(\\d{1,2})?$")) {
                float value = Float.valueOf(charSequence.toString());
                if (value > MAX_VALUE) {
                    setValue(lastValue);
                } else {
                    lastValue = temp;
                }
            } else {
                setValue(lastValue);
            }
        } else {
            lastValue = "";
        }
    }

    private void setValue(String value) {
        editText.setText(value);
        editText.setSelection(editText.getText().length());
    }
}
