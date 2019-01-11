package com.siziksu.payment.ui.view.confirmation;

import android.support.v7.widget.RecyclerView;

import com.siziksu.payment.ui.common.model.Contact;

import java.util.List;

public interface ItemsAdapterContract {

    void init();

    RecyclerView.LayoutManager getLayoutManager();

    RecyclerView.Adapter getAdapter();

    void showItems(List<Contact> list, float value);

    void notifyDataSetChanged();
}
