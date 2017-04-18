package com.codebrat.moviefinder.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codebrat.moviefinder.R;
import com.codebrat.moviefinder.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements SearchFragment.FragmentChangeListener {
	private Toolbar mToolbar;
	private TextView mToolbarTitle;
	private LinearLayout mainLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mainLayout = (LinearLayout) findViewById(R.id.search_layout);
		mToolbar =(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		Typeface face = Typeface.createFromAsset(getAssets(),
			"fonts/BreeSerif-Regular.ttf");

		mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
		mToolbarTitle.setTypeface(face);

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.fragment_container, new SearchFragment(), "Search");
		fragmentTransaction.commit();
	}
	@Override
	public void replaceFragment(Fragment fragment) {
		FragmentManager fragmentManager = getFragmentManager();;
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.toString());
		fragmentTransaction.addToBackStack(fragment.toString());
		fragmentTransaction.commit();
	}
}
