package com.example.chapter3.exercise2;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.chapter3.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Ex2Activity extends AppCompatActivity
{
	private LottieAnimationView mAnimeView;
	private SeekBar mSeekBar;
	private CheckBox mCheckBox;
	private Button mButton;

	private boolean mAutoPlaying = false;

	private static final ArrayList<Integer> animeID = new ArrayList<Integer>() {
		{
			add(R.raw.material_wave_loading);
			add(R.raw.muzli);
		}
	};

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ex2);

		// get widgets
		mAnimeView = findViewById(R.id.animeView);
		mSeekBar = findViewById(R.id.animeSeekBar);
		mCheckBox = findViewById(R.id.animeCheckBox);
		mButton = findViewById(R.id.animeSwitchButton);

		// TODO: set callbacks
		// synchronize animation and the seek bar
		mAnimeView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener()
		{
			@Override
			public void onAnimationUpdate(ValueAnimator animation)
			{
				if(isAutoPlaying()) {
					int max = mSeekBar.getMax();
					int now = (int) (max * mAnimeView.getProgress());
					mSeekBar.setProgress(now);
				}
			}
		});

		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				if(!isAutoPlaying()) {
					float now = mSeekBar.getProgress();
					float max = mSeekBar.getMax();
					mAnimeView.setProgress(now / max);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{

			}
		});

		// set auto playing
		mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if(isChecked)
				{
					// play animation automatically
					mAnimeView.setRepeatCount(LottieDrawable.INFINITE);
					mAnimeView.playAnimation();
					// reset seek bar progress
					mSeekBar.setProgress(0);
					// enable auto playing
					mAutoPlaying = true;
				}
				else
				{
					// stop playing animation
					mAnimeView.cancelAnimation();
					// disable auto playing
					mAutoPlaying = false;
				}
				// set seek bar enable
				mSeekBar.setEnabled(!isAutoPlaying());
			}
		});

		mButton.setOnClickListener(new View.OnClickListener()
		{
			private int mIndex = 0;
			@Override
			public void onClick(View v)
			{
				// switch animation
				mIndex = (mIndex+1) % animeID.size();
				mAnimeView.setAnimation(animeID.get(mIndex));
				// reset seek bar
				mSeekBar.setProgress(0);
			}
		});
	}

	private boolean isAutoPlaying()
	{
		return mAutoPlaying;
	}
}
