package com.siziksu.payment.ui.view.main;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.siziksu.payment.R;
import com.siziksu.payment.common.function.Action;
import com.siziksu.payment.common.function.Consumer;

final class MultiSelectionHelper implements ActionMode.Callback {

    private ActionMode actionMode;
    private Action onDestroy;
    private Consumer<Integer> onMenuClick;

    MultiSelectionHelper(Action onDestroy, Consumer<Integer> onMenuClick) {
        this.onDestroy = onDestroy;
        this.onMenuClick = onMenuClick;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (onMenuClick != null) {
            onMenuClick.accept(menuItem.getItemId());
        }
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        this.actionMode = null;
        if (onDestroy != null) {
            onDestroy.execute();
        }
    }

    public void selected(AppCompatActivity activity, Integer numberOfItemsSelected) {
        if (numberOfItemsSelected > 0 && actionMode == null) {
            this.actionMode = activity.startSupportActionMode(this);
        }
        if (numberOfItemsSelected == 0 && actionMode != null) {
            actionMode.finish();
        }
        if (actionMode != null) {
            actionMode.setTitle(String.valueOf(numberOfItemsSelected) + " selected");
        }
    }
}
