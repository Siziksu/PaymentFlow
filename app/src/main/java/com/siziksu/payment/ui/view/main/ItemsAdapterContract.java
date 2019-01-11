package com.siziksu.payment.ui.view.main;

import android.support.v7.widget.RecyclerView;

import com.siziksu.payment.common.function.Consumer;
import com.siziksu.payment.ui.common.model.Contact;

import java.util.List;

public interface ItemsAdapterContract {

    void init(Consumer<Integer> numberOfItemsSelected);

    RecyclerView.LayoutManager getLayoutManager();

    RecyclerView.Adapter getAdapter();

    void notifyDataSetChanged();

    void showItems(List<Contact> list);

    List<Contact> getSelectedItems();

    void clearItemsSelected();
}
