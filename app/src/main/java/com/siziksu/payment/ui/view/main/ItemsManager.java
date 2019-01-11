package com.siziksu.payment.ui.view.main;

import com.siziksu.payment.ui.common.model.Contact;

import java.util.ArrayList;
import java.util.List;

final class ItemsManager implements ItemsManagerContract {

    private List<Contact> contacts = new ArrayList<>();
    private List<Contact> selected = new ArrayList<>();

    ItemsManager() {}

    @Override
    public void showItems(ItemsAdapterContract adapter, List<Contact> list) {
        contacts.clear();
        contacts.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getItem(int item) {
        return contacts.get(item);
    }

    @Override
    public void setSelected(int item) {
        Contact contact = contacts.get(item);
        if (selected.contains(contact)) {
            selected.remove(contact);
        } else {
            selected.add(contact);
        }
    }

    @Override
    public boolean isSelected(Contact contact) {
        return selected.contains(contact);
    }

    @Override
    public List<Contact> getSelectedItems() {
        return selected;
    }

    @Override
    public void clearItemsSelected() {
        selected.clear();
    }
}
