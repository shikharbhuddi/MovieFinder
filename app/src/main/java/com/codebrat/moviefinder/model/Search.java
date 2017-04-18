package com.codebrat.moviefinder.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shikhar on 18-04-2017.
 */

public class Search implements Parcelable{
	private String movieName;
	private String movieType;

	public Search(){

	}

	public Search(String movieName, String movieType){
		this.movieName = movieName;
		this.movieType = movieType;
	}

	public String getMovieName(){
		return movieName;
	}

	public void setMovieName(String movieName){
		this.movieName = movieName;
	}

	public String getMovieType(){
		return movieType;
	}

	public void setMovieType(String movieType){
		this.movieType = movieType;
	}

	public Search(Parcel in) {
		super();
		readFromParcel(in);
	}

	public static final Parcelable.Creator<Search> CREATOR = new Parcelable.Creator<Search>() {
		public Search createFromParcel(Parcel in) {
			return new Search(in);
		}

		public Search[] newArray(int size) {

			return new Search[size];
		}

	};

	public void readFromParcel(Parcel in) {
		movieName = in.readString();
		movieType = in.readString();
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(movieName);
		dest.writeString(movieType);
	}
}
