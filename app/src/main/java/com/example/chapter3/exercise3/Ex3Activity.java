package com.example.chapter3.exercise3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.chapter3.R;

public class Ex3Activity extends AppCompatActivity
{
	private TabLayout mTabLayout;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ex3);

		// get widgets
		mTabLayout = findViewById(R.id.tabLayout);
		mViewPager = findViewById(R.id.viewPager);

		// init
		// TODO: ex3-1. init ViewPager
		mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
		// TODO: ex3-2. init TabLayout
		mTabLayout.setupWithViewPager(mViewPager);
	}
}
