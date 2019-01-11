package com.siziksu.payment.ui.view.confirmation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.siziksu.payment.App;
import com.siziksu.payment.R;
import com.siziksu.payment.common.Constants;
import com.siziksu.payment.presenter.confirmation.ConfirmationPresenterContract;
import com.siziksu.payment.presenter.confirmation.ConfirmationViewContract;
import com.siziksu.payment.ui.common.model.Contact;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public final class ConfirmationActivity extends AppCompatActivity implements ConfirmationViewContract {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.totalAmount)
    TextView totalAmount;

    @Inject
    ConfirmationPresenterContract<ConfirmationViewContract> presenter;

    private ItemsAdapterContract adapter;
    private boolean alreadyStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        ButterKnife.bind(this);
        App.get().getApplicationComponent().inject(this);
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
        if (!alreadyStarted) {
            alreadyStarted = true;
            presenter.checkExtras(getIntent().getExtras());
        }
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

    @Override
    public void onContacts(List<Contact> contacts, float amount) {
        adapter.showItems(contacts, amount);
        totalAmount.setText(String.format(getString(R.string.total_amount), amount));
    }

    private void initializeViews() {
        setSupportActionBar(toolbar);
        setTitle(Constants.ACTIVITY_CONFIRMATION_TITLE);
        adapter = new ItemsAdapter(new WeakReference<>(this), new ItemsManager());
        adapter.init();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter.getAdapter());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(adapter.getLayoutManager());
    }
}
