package com.siziksu.payment.ui.view.confirmation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.siziksu.payment.R;

import butterknife.BindView;
import butterknife.ButterKnife;

final class ItemsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.amount)
    TextView amount;

    ItemsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
