package com.example.chapter3.exercise3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.chapter3.R;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter
{
	private static final ArrayList<String> titles = new ArrayList<String>(){
		{
			add("发现");
			add("好友");
			add("我");
		}
	};

	public ViewPagerAdapter(FragmentManager manager)
	{
		super(manager);
	}

	@Override
	public Fragment getItem(int i)
	{
		// set arguments
		Bundle arg = new Bundle();
		Log.d("index", i + "");

		int layoutID = 0;
		int loadingID = 0;
		int viewID = 0;
		if(i == 0)
		{
			layoutID = R.layout.fragment_discover;
			loadingID = R.id.loading_discovery;
			viewID = R.id.view_discovery;
		}
		else if(i == 1)
		{
			layoutID = R.layout.fragment_friends;
			loadingID = R.id.loading_friends;
			viewID = R.id.view_friends;
		}
		else if(i == 2)
		{
			layoutID = R.layout.fragment_mine;
			loadingID = R.id.loading_mine;
			viewID = R.id.view_mine;
		}
		arg.putInt("layout", layoutID);
		arg.putInt("loading", loadingID);
		arg.putInt("view", viewID);

		// create fragment
		MyFragment fragment = new MyFragment();
		fragment.setArguments(arg);
		return fragment;
	}

	@Override
	public int getCount()
	{
		return titles.size();
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position)
	{
		return titles.get(position);
	}
}
