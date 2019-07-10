package com.example.chapter3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chapter3.exercise1.Ex1Activity;
import com.example.chapter3.exercise2.Ex2Activity;
import com.example.chapter3.exercise3.Ex3Activity;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get buttons
		Button btn1 = findViewById(R.id.ex1);
		Button btn2 = findViewById(R.id.ex2);
		Button btn3 = findViewById(R.id.ex3);

		// set button callback
		btn1.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(
						MainActivity.this, Ex1Activity.class
				));
			}
		});

		btn2.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(
						MainActivity.this, Ex2Activity.class
				));
			}
		});

		btn3.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(
						MainActivity.this, Ex3Activity.class
				));
			}
		});

	}
}
