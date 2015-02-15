package com.home.grishnak.filckrexplorer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.grishnak.filckrexplorer.R;
import com.home.grishnak.filckrexplorer.model.menu.DividerMenuItem;
import com.home.grishnak.filckrexplorer.model.menu.MenuItem;
import com.home.grishnak.filckrexplorer.utils.AttributesUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.MenuHolder> {

    private static final int ROW = 0;
    private static final int DIVIDER = 1;
    private final LayoutInflater mInflater;
    private final int color;
    private OnMenuItemSelectedListener onMenuItemSelectedListener;
    private Context context;
    private List<MenuItem> objects;

    public MenuItemsAdapter(Context context, List<MenuItem> objects) {
        this.context = context;
        this.objects = objects;
        mInflater = LayoutInflater.from(context);
        color = AttributesUtils.getPrimaryDarkColor(context, R.style.AppTheme_Repos);
    }

    @Override
    public int getItemViewType(int position) {
        MenuItem item = objects.get(position);

        if (item instanceof DividerMenuItem) {
            return DIVIDER;
        } else {
            return ROW;
        }
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup viewGroup, int itemViewType) {
        switch (itemViewType) {
            case ROW:
                return new RowMenuHolder(mInflater.inflate(R.layout.row_menu, viewGroup, false));
            case DIVIDER:
            default:
                return new DividerMenuHolder(mInflater.inflate(R.layout.row_divider, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(MenuHolder menuHolder, int position) {
        switch (getItemViewType(position)) {
            case ROW:
                MenuItem item = objects.get(position);
                RowMenuHolder holder = (RowMenuHolder) menuHolder;
                holder.text.setText(item.text);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return objects != null ? objects.size() : 0;
    }

    public void setOnMenuItemSelectedListener(OnMenuItemSelectedListener onMenuItemSelectedListener) {
        this.onMenuItemSelectedListener = onMenuItemSelectedListener;
    }

    public interface OnMenuItemSelectedListener {
        void onMenuItemSelected(MenuItem item);
    }

    public class RowMenuHolder extends MenuHolder implements View.OnClickListener {

        @InjectView(android.R.id.icon)
        public ImageView image;
        @InjectView(android.R.id.text1)
        public TextView text;

        public RowMenuHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onMenuItemSelectedListener != null) {
                onMenuItemSelectedListener.onMenuItemSelected(objects.get(getPosition()));
            }
        }
    }

    public class DividerMenuHolder extends MenuHolder {

        public DividerMenuHolder(View itemView) {
            super(itemView);
        }
    }

    public class MenuHolder extends RecyclerView.ViewHolder {

        public MenuHolder(View itemView) {
            super(itemView);
        }
    }
}
