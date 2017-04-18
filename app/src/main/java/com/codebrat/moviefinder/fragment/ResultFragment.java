package com.codebrat.moviefinder.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.codebrat.moviefinder.R;
import com.codebrat.moviefinder.adaptor.MoviesAdaptor;
import com.codebrat.moviefinder.data.ApiUrls;
import com.codebrat.moviefinder.model.Movies;
import com.codebrat.moviefinder.model.Search;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ResultFragment extends Fragment {
	private RecyclerView mMoviesRecyclerView;
	private MoviesAdaptor mMoviesAdaptor;
	private ArrayList<Object> mMoviesList = new ArrayList<>();
	private TextView mMoviesMessage;
	private ArrayList<Search> searchArrayList;
	private ProgressBar mProgressBar;
	private static int position;
	public ResultFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = this.getArguments();
		if (bundle != null) {
			searchArrayList = bundle.getParcelableArrayList("searchList");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_result, container, false);
		initUI(view);
		mMoviesAdaptor = new MoviesAdaptor(getActivity(), mMoviesList);
		final LinearLayoutManager ancLayoutManager = new LinearLayoutManager(getActivity());
		mMoviesRecyclerView.setLayoutManager(ancLayoutManager);
		mMoviesRecyclerView.setAdapter(mMoviesAdaptor);

		mProgressBar.setVisibility(View.VISIBLE);
		position = searchArrayList.size()-1;
		loadMovies(position);
		return view;
	}

	private void loadMovies(int position) {
		hideMsg();

		if(position==-1) {
			Collections.reverse(mMoviesList);
			mMoviesAdaptor.notifyDataSetChanged();
			return;
		}
		Search search = searchArrayList.get(position);
		String url = ApiUrls.API_BASE + "t=" + search.getMovieName() + "&type=" + search.getMovieType()
			+ "&plot=short";
		final RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
		JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
			url
			,new Response.Listener<JSONObject>(){
			public void onResponse(JSONObject jsonObject){
					try {
						if (jsonObject.getBoolean("Response")) {
							Movies movie = new Movies();
							movie.setTitle(jsonObject.getString("Title"));
							movie.setGenre(jsonObject.getString("Genre"));
							movie.setPlot(jsonObject.getString("Plot"));
							if(!jsonObject.has("Ratings") || jsonObject.getJSONArray("Ratings").length()==0){
								movie.setRatings("N/A");
							} else
								movie.setRatings(jsonObject.getJSONArray("Ratings").getJSONObject(0).getString("Value"));
							movie.setReleased(jsonObject.getString("Released"));
							movie.setPoster(jsonObject.getString("Poster"));

							mMoviesList.add(movie);
							mProgressBar.setVisibility(View.GONE);
							mMoviesAdaptor.notifyDataSetChanged();
							loadMovies(--ResultFragment.position);
						} else {
							mProgressBar.setVisibility(View.GONE);
							showMsg(jsonObject.getString("Error"));
						}
					} catch (JSONException e){
						mProgressBar.setVisibility(View.GONE);
						showMsg("Error!\n" + e.toString());
					}
				}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				mProgressBar.setVisibility(View.GONE);
				showMsg("Error!\n" + volleyError.toString());
			}
		});
		requestQueue.add(jsonObjectRequest);
	}

	private void initUI(View view){
		mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
		mMoviesRecyclerView = (RecyclerView) view.findViewById(R.id.movies_list);
		mMoviesMessage = (TextView) view.findViewById(R.id.movies_msg);
	}

	private void showMsg(String msg) {
		mMoviesMessage.setText(Html.fromHtml(msg));
		mMoviesMessage.setVisibility(View.VISIBLE);
	}

	private void hideMsg() {
		mMoviesMessage.setVisibility(View.GONE);
	}

}
