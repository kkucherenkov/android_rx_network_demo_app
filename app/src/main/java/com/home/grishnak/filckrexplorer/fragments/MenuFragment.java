package com.home.grishnak.filckrexplorer.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.grishnak.filckrexplorer.R;
import com.home.grishnak.filckrexplorer.adapters.MenuItemsAdapter;
import com.home.grishnak.filckrexplorer.model.menu.DividerMenuItem;
import com.home.grishnak.filckrexplorer.model.menu.MenuItem;
import com.home.grishnak.filckrexplorer.utils.AttributesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MenuFragment extends Fragment implements MenuItemsAdapter.OnMenuItemSelectedListener, View.OnClickListener {

    @InjectView(R.id.userName)
    TextView userName;
    @InjectView(R.id.userLogin)
    TextView userLogin;
    @InjectView(R.id.userAvatar)
    ImageView userAvatar;
    @InjectView(R.id.user)
    View userLayout;
    private OnMenuItemSelectedListener onMenuItemSelectedListener;
    private MenuItemsAdapter adapter;
    private MenuItem currentSelectedItem;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_menu, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        userLayout.setOnClickListener(this);
        userAvatar.setOnClickListener(this);
        userLogin.setOnClickListener(this);
        userName.setOnClickListener(this);

        List<MenuItem> objMenuItems = new ArrayList<>();

        objMenuItems.add(new MenuItem(1, 1, R.string.menu_events));

        currentSelectedItem = new MenuItem(0, 2, R.string.navigation_repos);

        objMenuItems.add(currentSelectedItem);
        objMenuItems.add(new MenuItem(1, 2, R.string.navigation_starred_repos));
        objMenuItems.add(new MenuItem(2, 2, R.string.navigation_watched_repos));

        int primarColorPeople = AttributesUtils.getPrimaryColor(getActivity(), R.style.AppTheme_Repos);

        objMenuItems.add(new MenuItem(0, 3, R.string.navigation_people));

        objMenuItems.add(new DividerMenuItem());
        objMenuItems.add(new MenuItem(0, 4, R.string.navigation_settings));

        //objMenuItems.add(new MenuItem(1, 4, R.string.navigation_about, null));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new MenuItemsAdapter(getActivity(), objMenuItems);
        adapter.setOnMenuItemSelectedListener(this);
        recyclerView.setAdapter(adapter);

        if (onMenuItemSelectedListener != null) {
            onMenuItemSelectedListener.onReposSelected();
            onMenuItemSelectedListener.onMenuItemSelected(currentSelectedItem, true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onMenuItemSelected(MenuItem item) {
        if (item != null && onMenuItemSelectedListener != null) {
            boolean changeTitle = true;
            switch (item.parentId) {
                case 1:
                    changeTitle = itemUser(item);
                    break;
                case 2:
                    changeTitle = itemRepositories(item);
                    break;
                case 3:
                    changeTitle = itemPeople(item);
                    break;
                case 4:
                    changeTitle = itemExtras(item);
                    break;
            }
            onMenuItemSelectedListener.onMenuItemSelected(item, changeTitle);
        }
    }

    private boolean itemUser(MenuItem item) {
        boolean change = true;
        switch (item.id) {
            case 1:
                change = onMenuItemSelectedListener.onUserEventsSelected();
                break;
        }
        return change;
    }

    private boolean itemRepositories(MenuItem item) {
        boolean change = true;
        switch (item.id) {
            case 0:
                change = onMenuItemSelectedListener.onReposSelected();
                break;
            case 1:
                change = onMenuItemSelectedListener.onStarredSelected();
                break;
            case 2:
                change = onMenuItemSelectedListener.onWatchedSelected();
                break;
        }
        return change;
    }

    private boolean itemPeople(MenuItem item) {
        boolean change = true;
        switch (item.id) {
            case 0:
                change = onMenuItemSelectedListener.onPeopleSelected();
                break;
        }
        return change;
    }

    private boolean itemExtras(MenuItem item) {
        boolean change = true;
        switch (item.id) {
            case 0:
                change = onMenuItemSelectedListener.onSettingsSelected();
                break;
            case 1:
                change = onMenuItemSelectedListener.onAboutSelected();
                break;
        }
        return change;
    }

    public void setOnMenuItemSelectedListener(OnMenuItemSelectedListener onMenuItemSelectedListener) {
        this.onMenuItemSelectedListener = onMenuItemSelectedListener;
    }

    @Override
    public void onClick(View v) {
        if (onMenuItemSelectedListener != null) {
            onMenuItemSelectedListener.onProfileSelected();
            onMenuItemSelectedListener.closeMenu();
        }
    }

    public interface OnMenuItemSelectedListener {
        boolean onProfileSelected();

        boolean onReposSelected();

        boolean onStarredSelected();

        boolean onWatchedSelected();

        boolean onPeopleSelected();

        void onMenuItemSelected(@NonNull MenuItem item, boolean changeTitle);

        void closeMenu();

        boolean onUserEventsSelected();

        boolean onSettingsSelected();

        boolean onAboutSelected();
    }

}
