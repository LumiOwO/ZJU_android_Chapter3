package com.example.chapter3.exercise1;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.chapter3.R;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

public class Ex1Activity extends AppCompatActivity
{
	private AnimatorSet mAnimatorSet;
	private View mTarget;
	private View mStartColorPicker;
	private View mEndColorPicker;
	private Button mDurationButton;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ex1);

		// get widgets
		mTarget = findViewById(R.id.target);
		mStartColorPicker = findViewById(R.id.startColorPicker);
		mEndColorPicker = findViewById(R.id.endColorPicker);
		mDurationButton = findViewById(R.id.durationButton);

		// add callback for widgets
		mStartColorPicker.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ColorPicker picker = new ColorPicker(Ex1Activity.this);
				picker.setColor(getBgColor(mStartColorPicker));
				picker.enableAutoClose();
				picker.setCallback(new ColorPickerCallback()
				{
					@Override
					public void onColorChosen(int color)
					{
						mStartColorPicker.setBackgroundColor(color);
						resetAnimation();
					}
				});

				picker.show();
			}
		});

		mEndColorPicker.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ColorPicker picker = new ColorPicker(Ex1Activity.this);
				picker.setColor(getBgColor(mEndColorPicker));
				picker.enableAutoClose();
				picker.setCallback(new ColorPickerCallback()
				{
					@Override
					public void onColorChosen(int color)
					{
						mEndColorPicker.setBackgroundColor(color);
						resetAnimation();
					}
				});

				picker.show();
			}
		});

		mDurationButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MaterialDialog.Builder builder = new MaterialDialog.Builder(Ex1Activity.this);
				builder.inputType(InputType.TYPE_CLASS_NUMBER);
				builder.input(getString(R.string.duration_hint), mDurationButton.getText(),
					new MaterialDialog.InputCallback()
					{
						@Override
						public void onInput(@NonNull MaterialDialog dialog, CharSequence input)
						{
							// check the input value
							boolean isValid = true;
							try {
								int duration = Integer.parseInt(input.toString());
								if (duration < 100 || duration > 10000) {
									isValid = false;
								}
							} catch (Throwable e) {
								isValid = false;
							}

							if (isValid) {
								mDurationButton.setText(input);
								resetAnimation();
							} else {
								Toast.makeText(
										Ex1Activity.this,
										getString(R.string.invalid_duration),
										Toast.LENGTH_LONG).show();
							}
						}
					});

				builder.show();
			}
		});

		// start animation
		resetAnimation();
	}

	private void resetAnimation()
	{
		// stop current animator
		if(mAnimatorSet != null)
			mAnimatorSet.cancel();

		// create animators

		// TODO: change color
		// to implement transparent, we need to use "ofArgb"
		ObjectAnimator colorChangeAnime = ObjectAnimator.ofArgb(
			mTarget, "backgroundColor",
			getBgColor(mStartColorPicker),
			getBgColor(mEndColorPicker)
		);
		colorChangeAnime.setDuration(Integer.parseInt(mDurationButton.getText().toString()));
		colorChangeAnime.setRepeatCount(ValueAnimator.INFINITE);
		colorChangeAnime.setRepeatMode(ValueAnimator.REVERSE);

		// TODO ex1-1: change size
		ObjectAnimator widthChangeAnime = ObjectAnimator.ofFloat(
			mTarget, "scaleX", 1f, 2f
		);
		widthChangeAnime.setDuration(Integer.parseInt(mDurationButton.getText().toString()));
		widthChangeAnime.setRepeatCount(ValueAnimator.INFINITE);
		widthChangeAnime.setRepeatMode(ValueAnimator.REVERSE);

		ObjectAnimator heightChangeAnime = ObjectAnimator.ofFloat(
				mTarget, "scaleY", 1f, 2f
		);
		heightChangeAnime.setDuration(Integer.parseInt(mDurationButton.getText().toString()));
		heightChangeAnime.setRepeatCount(ValueAnimator.INFINITE);
		heightChangeAnime.setRepeatMode(ValueAnimator.REVERSE);

		// TODO ex1-2: change transparency
		ObjectAnimator alphaChangeAnime = ObjectAnimator.ofFloat(
				mTarget, "alpha", 1f, 0.5f
		);
		alphaChangeAnime.setDuration(Integer.parseInt(mDurationButton.getText().toString()));
		alphaChangeAnime.setRepeatCount(ValueAnimator.INFINITE);
		alphaChangeAnime.setRepeatMode(ValueAnimator.REVERSE);

		// TODO ex1-3: add animators to animator set
		mAnimatorSet = new AnimatorSet();
		mAnimatorSet.playTogether(
			colorChangeAnime, widthChangeAnime, heightChangeAnime, alphaChangeAnime
		);

		// restart animator
		mAnimatorSet.start();
	}

	private int getBgColor(View view)
	{
		ColorDrawable drawable = (ColorDrawable)view.getBackground();
		return drawable.getColor();
	}

}
