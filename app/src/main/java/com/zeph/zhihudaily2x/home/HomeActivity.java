package com.zeph.zhihudaily2x.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.zeph.zhihudaily2x.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<HomeFragment> fragmentList = new ArrayList<HomeFragment>();
    private List<String> titleList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        initParms(bundle);
        initView();
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        linearLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.ll_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    private void initParms(Bundle bundle) {
        fragmentList.add(HomeFragment.getInstance("今日头条"));
        fragmentList.add(HomeFragment.getInstance("互联网安全"));
        fragmentList.add(HomeFragment.getInstance("不准无聊"));
        fragmentList.add(HomeFragment.getInstance("体育日报"));
        titleList.add("今日头条");
        titleList.add("互联网安全");
        titleList.add("不准无聊");
        titleList.add("体育日报");
    }
}
