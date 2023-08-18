package com.example.test2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Calendar extends Fragment {

    RecyclerView rec_view;
    TabLayout tabs;
    TabItem tab1,tab2,tab3,tab4;
    StateAdaptor adaptor;
    ViewPager2 viewpager;

    private String[] titles = new String[]{"STUDY PLAN", "ASSIGNMENT", "EXAM","LECTURE"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View CalendarView = inflater.inflate(R.layout.fragment_calendar,container,false);
        //rec_view = CalendarView.findViewById(R.id.rec_view);
        tabs = CalendarView.findViewById(R.id.tablayout);
        tab1 = CalendarView.findViewById(R.id.item1);
        tab2 = CalendarView.findViewById(R.id.item2);
        tab3 = CalendarView.findViewById(R.id.item3);
        tab4 = CalendarView.findViewById(R.id.item4);
        viewpager = CalendarView.findViewById(R.id.view_pager);
        adaptor = new StateAdaptor(this,tabs.getTabCount());
        viewpager.setAdapter(adaptor);
        new TabLayoutMediator(tabs, viewpager,(tab, position) -> tab.setText(titles[position])).attach();
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return CalendarView;
    }
}
