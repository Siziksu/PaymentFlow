package com.siziksu.payment.ui.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.siziksu.payment.App;
import com.siziksu.payment.R;
import com.siziksu.payment.common.Constants;
import com.siziksu.payment.presenter.main.MainPresenterContract;
import com.siziksu.payment.presenter.main.MainViewContract;
import com.siziksu.payment.ui.common.manager.DialogFragmentHelper;
import com.siziksu.payment.ui.common.model.Contact;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public final class MainActivity extends AppCompatActivity implements MainViewContract {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    MainPresenterContract<MainViewContract> presenter;

    private ItemsAdapterContract adapter;
    private MultiSelectionHelper multiSelectionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.get().getApplicationComponent().inject(this);
        presenter.onCreate(this);
        initializeViews();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionResult(requestCode, permissions, grantResults);
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

    @Override
    public void onPermissionsChecked() {
        presenter.start();
    }

    @Override
    public void showProgress() {
        DialogFragmentHelper.showLoadingDialog(this);
    }

    @Override
    public void hideProgress() {
        DialogFragmentHelper.hideLoadingDialog(this);
    }

    @Override
    public void onContacts(List<Contact> contacts) {
        adapter.showItems(contacts);
    }

    private void initializeViews() {
        setSupportActionBar(toolbar);
        setTitle(Constants.ACTIVITY_CONTACTS_TITLE);
        multiSelectionHelper = new MultiSelectionHelper(
                () -> adapter.clearItemsSelected(),
                id -> {
                    switch (id) {
                        case R.id.action_forward:
                            presenter.onForwardClick(adapter.getSelectedItems());
                            break;
                        default:
                            break;
                    }
                }
        );
        adapter = new ItemsAdapter(new WeakReference<>(this), new ItemsManager());
        adapter.init(numberOfItemsSelected -> multiSelectionHelper.selected(this, numberOfItemsSelected));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter.getAdapter());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(adapter.getLayoutManager());
    }
}
