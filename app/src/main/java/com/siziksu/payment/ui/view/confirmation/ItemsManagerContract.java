package com.siziksu.payment.ui.view.confirmation;

import com.siziksu.payment.ui.common.model.Contact;

import java.util.List;

public interface ItemsManagerContract {

    void showItems(ItemsAdapterContract adapter, List<Contact> list);

    int getCount();

    Contact getItem(int item);

    void setSelected(int item);
}
