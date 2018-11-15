package com.jaren.likeview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity  {

    private ViewPager mViewPager;
    private TabLayout mTablayout;
    private  String[] mPageTitles={"Attributes","Create","NetRequest"};
    private Fragment[] mFragment=new Fragment[mPageTitles.length];
    private int mPageSize;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTablayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);
        initView();
    }


    private void initView() {
        mPageSize=mPageTitles.length;
        for (int i = 0; i <mPageSize; i++) {
            if (i==0){
                mFragment[i]= new AttributesFragment();
            }else if (i==1){
                mFragment[i]= new CreatedFragment();
            }else if (i==2){
                mFragment[i]= new NetRequestFragment();
            }else {
            }
        }
        mViewPager.setAdapter(new MianFragmentPagerAdapter(getSupportFragmentManager()));
        mTablayout.setupWithViewPager(mViewPager);

    }
    class MianFragmentPagerAdapter extends FragmentPagerAdapter {
        public MianFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {
            return mFragment[i];
        }
        @Override
        public int getCount() {
            return mPageSize;
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mPageTitles[position];
        }
    }



}
