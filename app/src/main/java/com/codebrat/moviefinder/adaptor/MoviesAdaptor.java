package com.codebrat.moviefinder.adaptor;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codebrat.moviefinder.R;
import com.codebrat.moviefinder.model.Movies;
import com.codebrat.moviefinder.utils.WebUtils;
import com.pkmmte.view.CircularImageView;

import java.util.List;

/**
 * Created by Shikhar on 18-04-2017.
 */

public class MoviesAdaptor extends RecyclerView.Adapter {
	private List<Object> list;
	private Context mContext;

	public MoviesAdaptor(Context context, List<Object> list) {
		this.list = list;
		mContext = context;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView;
		RecyclerView.ViewHolder vh;

		itemView = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.list_item_movie, parent, false);
		vh = new MoviesViewHolder(itemView);
		return vh;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		holder.setIsRecyclable(false);
		Movies movie = (Movies) list.get(position);

		WebUtils.setImage(mContext, movie.getPoster(), ((MoviesViewHolder)holder).poster);
		((MoviesViewHolder)holder).title.setText(movie.getTitle());
		((MoviesViewHolder)holder).genre.setText(movie.getGenre());
		((MoviesViewHolder)holder).released.setText(movie.getReleased());
		((MoviesViewHolder)holder).plot.setText(movie.getPlot());
		((MoviesViewHolder)holder).ratings.setText(movie.getRatings());

		Typeface face = Typeface.createFromAsset(mContext.getAssets(),
			"fonts/Roboto-Black.ttf");
		((MoviesViewHolder)holder).title.setTypeface(face);
		((MoviesViewHolder)holder).genre.setTypeface(face);
		((MoviesViewHolder)holder).released.setTypeface(face);
		((MoviesViewHolder)holder).ratings.setTypeface(face);

		face = Typeface.createFromAsset(mContext.getAssets(),
			"fonts/Pacifico-Regular.ttf");
		((MoviesViewHolder)holder).plot.setTypeface(face);

	}

	@Override
	public int getItemViewType(int position) {
		return -1;
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	class MoviesViewHolder extends RecyclerView.ViewHolder {
		CircularImageView poster;
		TextView title;
		TextView genre;
		TextView released;
		TextView plot;
		TextView ratings;

		public MoviesViewHolder(View v) {
			super(v);
			poster = (CircularImageView) v.findViewById(R.id.poster);
			title = (TextView) v.findViewById(R.id.title);
			genre = (TextView) v.findViewById(R.id.genre);
			released = (TextView) v.findViewById(R.id.released);
			plot = (TextView) v.findViewById(R.id.plot);
			ratings = (TextView) v.findViewById(R.id.ratings);
		}
	}

}
