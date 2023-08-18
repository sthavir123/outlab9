package com.example.test2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class StateAdaptor extends FragmentStateAdapter {
    private int tabsNumber;

    public StateAdaptor(@NonNull Fragment fragment,int tabs) {
        super(fragment);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
               return new tab1();
            case 1:
                return new tab2();
            case 2:
                return new tab3();
            case 3:
                return new tab4();
            default: return null;

        }

    }

    @Override
    public int getItemCount() {
        return tabsNumber;
    }
}
