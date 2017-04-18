package com.codebrat.moviefinder.fragment;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.codebrat.moviefinder.R;
import com.codebrat.moviefinder.model.Search;
import com.codebrat.moviefinder.utils.BasicUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shikhar on 18-04-2017.
 */

public class SearchFragment extends Fragment {
	private  TextView mHeading;
	private Spinner mTypeSpinner;
	private TextView mAddMoreSearches;
	private Button mSearch;
	private AutoCompleteTextView movieName;
	private List<String> categories;
	private LinearLayout moreSearchesLayout;
	private int noOfSearches=1;
	private ArrayList<Search> searchArrayList;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		/** Inflating the layout for this fragment **/
		View view = inflater.inflate(R.layout.fragment_search, null);
		initUI(view);
		return view;
	}

	private void initUI(View view){
		movieName = (AutoCompleteTextView) view.findViewById(R.id.movie_name);

		Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
			"fonts/BreeSerif-Regular.ttf");

		mHeading = (TextView) view.findViewById(R.id.search_heading);
		mHeading.setTypeface(face);

		mTypeSpinner = (Spinner) view.findViewById(R.id.spinner);
		categories = new ArrayList<String>();
		categories.add("Movie");
		categories.add("Series");
		categories.add("Episode");
		setupSpinner(mTypeSpinner);

		searchArrayList = new ArrayList<Search>();
		moreSearchesLayout = (LinearLayout) view.findViewById(R.id.more_searches_layout);

		mAddMoreSearches = (TextView) view.findViewById(R.id.add_searches);
		mAddMoreSearches.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				View moreSearches = getActivity().getLayoutInflater().inflate(R.layout.search_field, null);
				AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) moreSearches.findViewById(R.id.movie_name);
				Spinner spinner = (Spinner) moreSearches.findViewById(R.id.spinner);
				setupSpinner(spinner);

				switch (noOfSearches){
					case 1:
						autoCompleteTextView.setId(R.id.movie_name_1);
						spinner.setId(R.id.spinner_1);
						break;

					case 2:
						autoCompleteTextView.setId(R.id.movie_name_2);
						spinner.setId(R.id.spinner_2);
						break;

					case 3:
						autoCompleteTextView.setId(R.id.movie_name_3);
						spinner.setId(R.id.spinner_3);
						break;

					case 4:
						autoCompleteTextView.setId(R.id.movie_name_4);
						spinner.setId(R.id.spinner_4);
						break;

					default:
						BasicUtils.makeToastShort("Max. searches reached");
						break;
				}

				moreSearchesLayout.addView(moreSearches);
				noOfSearches++;
				if(noOfSearches==5)
					mAddMoreSearches.setVisibility(View.GONE);
			}
		});

		mSearch = (Button) view.findViewById(R.id.search_button);
		mSearch.setTypeface(face);
		mSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Search search;
				AutoCompleteTextView autoCompleteTextView;
				Spinner spinner;
				for(int i=1; i<=noOfSearches; i++) {
					switch (i) {
						case 1:
							autoCompleteTextView = (AutoCompleteTextView) getActivity().findViewById(R.id.movie_name);
							spinner = (Spinner) getActivity().findViewById(R.id.spinner);
							if (!autoCompleteTextView.getText().toString().isEmpty()) {
								search = new Search(autoCompleteTextView.getText().toString(),
									spinner.getSelectedItem().toString().toLowerCase());
								searchArrayList.add(search);
							}
							break;

						case 2:
							autoCompleteTextView = (AutoCompleteTextView) getActivity().findViewById(R.id.movie_name_1);
							spinner = (Spinner) getActivity().findViewById(R.id.spinner_1);
							if (!autoCompleteTextView.getText().toString().isEmpty()) {
								search = new Search(autoCompleteTextView.getText().toString(),
									spinner.getSelectedItem().toString().toLowerCase());
								searchArrayList.add(search);
							}
							break;

						case 3:
							autoCompleteTextView = (AutoCompleteTextView) getActivity().findViewById(R.id.movie_name_2);
							spinner = (Spinner) getActivity().findViewById(R.id.spinner_2);
							if (!autoCompleteTextView.getText().toString().isEmpty()) {
								search = new Search(autoCompleteTextView.getText().toString(),
									spinner.getSelectedItem().toString().toLowerCase());
								searchArrayList.add(search);
							}
							break;

						case 4:
							autoCompleteTextView = (AutoCompleteTextView) getActivity().findViewById(R.id.movie_name_3);
							spinner = (Spinner) getActivity().findViewById(R.id.spinner_3);
							if (!autoCompleteTextView.getText().toString().isEmpty()) {
								search = new Search(autoCompleteTextView.getText().toString(),
									spinner.getSelectedItem().toString().toLowerCase());
								searchArrayList.add(search);
							}
							break;

						case 5:
							autoCompleteTextView = (AutoCompleteTextView) getActivity().findViewById(R.id.movie_name_4);
							spinner = (Spinner) getActivity().findViewById(R.id.spinner_4);
							if (!autoCompleteTextView.getText().toString().isEmpty()) {
								search = new Search(autoCompleteTextView.getText().toString(),
									spinner.getSelectedItem().toString().toLowerCase());
								searchArrayList.add(search);
							}
							break;
						}
					}
					if(searchArrayList.isEmpty()){
						BasicUtils.makeToastShort("Add atleast one query");
						return;
					}

					Fragment fragment = new ResultFragment();
					Bundle bundle = new Bundle();
					bundle.putParcelableArrayList("searchList", searchArrayList);
					fragment.setArguments(bundle);
					FragmentChangeListener fc=(FragmentChangeListener)getActivity();
					fc.replaceFragment(fragment);
					noOfSearches=1;
			}
		});


	}

	public void setupSpinner(Spinner spinner){
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
			R.layout.spinner_item, categories);

		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);

	}
	public interface FragmentChangeListener
	{
		public void replaceFragment(Fragment fragment);
	}
}
