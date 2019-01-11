package com.siziksu.payment.ui.view.amount;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.siziksu.payment.App;
import com.siziksu.payment.R;
import com.siziksu.payment.common.Constants;
import com.siziksu.payment.presenter.amount.AmountPresenterContract;
import com.siziksu.payment.presenter.amount.AmountViewContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public final class AmountSelectionActivity extends AppCompatActivity implements AmountViewContract {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.amount)
    EditText amount;

    @Inject
    AmountPresenterContract<AmountViewContract> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_selection);
        ButterKnife.bind(this);
        App.get().getApplicationComponent().inject(this);
        presenter.onCreate(this);
        presenter.checkExtras(getIntent().getExtras());
        initializeViews();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_enter_from_left, R.anim.slide_exit_to_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    @OnClick(R.id.next)
    public void onNextClick() {
        presenter.onNextClick();
    }

    private void initializeViews() {
        setSupportActionBar(toolbar);
        setTitle(Constants.ACTIVITY_AMOUNT_SELECTION_TITLE);
        presenter.amountEditText(amount);
    }
}
