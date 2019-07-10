package com.example.chapter3.exercise3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;

public class MyFragment extends Fragment
{
	private int mLayout;
	private int mLoadingID;
	private int mViewID;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// get layout ID
		Bundle arg = getArguments();
		mLayout = arg.getInt("layout");
		mLoadingID = arg.getInt("loading");
		mViewID = arg.getInt("view");
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		// TODO ex3-3: add widgets
		return inflater.inflate(mLayout, container, false);
	}

	@Override
	public void onStart()
	{
		super.onStart();

		// get widgets
		final View view = this.getView();
		final LottieAnimationView animeView = view.findViewById(mLoadingID);
		final View showingView = view.findViewById(mViewID);
		// set transparency
		showingView.setAlpha(0);

		animeView.playAnimation();

		view.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				// TODO ex3-4：animation
				ObjectAnimator animator1 = ObjectAnimator.ofFloat(animeView, "alpha", 1, 0);
				ObjectAnimator animator2 = ObjectAnimator.ofFloat(showingView, "alpha", 0, 1);

				animeView.cancelAnimation();

				AnimatorSet set = new AnimatorSet();
				set.playTogether(animator1, animator2);
				set.start();
			}
		}, 5000);
	}
}
