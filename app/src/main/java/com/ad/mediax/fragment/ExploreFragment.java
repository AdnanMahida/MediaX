package com.ad.mediax.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ad.mediax.Adapters.TabExploreAdapter;
import com.ad.mediax.R;
import com.ad.mediax.TabFragment.TabHollyWood;
import com.ad.mediax.TabFragment.TabMovie;
import com.ad.mediax.TabFragment.TabBollyWood;
import com.ad.mediax.TabFragment.TabSouthIndian;
import com.google.android.material.tabs.TabLayout;

public class ExploreFragment extends Fragment {
    View rootView;
    private TabExploreAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_explore, container, false);
        try {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Explore");
            init();

            adapter = new TabExploreAdapter(getFragmentManager());
            adapter.addFragment(new TabMovie(), "ALL");
            adapter.addFragment(new TabBollyWood(), "Bollywood");
            adapter.addFragment(new TabHollyWood(), "Hollywood");
            adapter.addFragment(new TabSouthIndian(), "South Indian");
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }

    private void init() {
        viewPager = (ViewPager) rootView.findViewById(R.id.E_viewPager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.E_tabLayuot);
    }
}
