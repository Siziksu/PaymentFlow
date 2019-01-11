package com.siziksu.payment.ui.view.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siziksu.payment.R;
import com.siziksu.payment.common.function.Consumer;
import com.siziksu.payment.ui.common.model.Contact;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

final class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemsAdapterContract {

    private WeakReference<Context> context;
    private ItemsManagerContract manager;
    private Consumer<Integer> numberOfItemsSelected;
    private LinearLayoutManager layoutManager;

    ItemsAdapter(WeakReference<Context> context, ItemsManagerContract manager) {
        this.context = context;
        this.manager = manager;
    }

    public void init(Consumer<Integer> numberOfItemsSelected) {
        this.numberOfItemsSelected = numberOfItemsSelected;
        layoutManager = new LinearLayoutManager(context.get(), LinearLayoutManager.VERTICAL, false);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.get()).inflate(R.layout.item_contact, parent, false);
        return new ItemsViewHolder(
                view,
                item -> {
                    manager.setSelected(item);
                    if (numberOfItemsSelected != null) {
                        numberOfItemsSelected.accept(manager.getSelectedItems().size());
                    }
                }
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemsViewHolder) {
            ItemsViewHolder localHolder = (ItemsViewHolder) holder;
            Contact contact = manager.getItem(position);
            localHolder.name.setText(contact.name);
            localHolder.phone.setText(contact.phone == null ? "N/A" : contact.phone);
            if (!"".equals(contact.getAvatar())) {
                Picasso.get()
                        .load(contact.getAvatar())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(localHolder.avatar);
            } else {
                Picasso.get()
                        .load(R.mipmap.ic_launcher)
                        .into(localHolder.avatar);
            }
            localHolder.checkbox.setChecked(manager.isSelected(contact));
        }
    }

    @Override
    public int getItemCount() {
        return manager.getCount();
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return this;
    }

    @Override
    public void showItems(List<Contact> list) {
        manager.showItems(this, list);
    }

    @Override
    public List<Contact> getSelectedItems() {
        return manager.getSelectedItems();
    }

    @Override
    public void clearItemsSelected() {
        manager.clearItemsSelected();
        notifyDataSetChanged();
    }
}
