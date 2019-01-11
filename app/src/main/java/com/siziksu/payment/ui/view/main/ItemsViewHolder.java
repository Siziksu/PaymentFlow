package com.siziksu.payment.ui.view.main;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.siziksu.payment.R;
import com.siziksu.payment.common.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

final class ItemsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.checkbox)
    AppCompatCheckBox checkbox;

    private Consumer<Integer> itemClick;

    ItemsViewHolder(View view, Consumer<Integer> itemClick) {
        super(view);
        ButterKnife.bind(this, view);
        this.itemClick = itemClick;
    }

    @OnClick(R.id.checkbox)
    public void onClick() {
        if (itemClick != null) {
            itemClick.accept(getAdapterPosition());
        }
    }
}
