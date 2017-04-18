package com.codebrat.moviefinder.model;

/**
 * Created by Shikhar on 18-04-2017.
 */

public class Movies {
	private String title;
	private String genre;
	private String released;
	private String plot;
	private String ratings;
	private String poster;

	public Movies(){

	}

	public Movies(String title, String genre, String released, String plot, String ratings, String poster){
		this.title = title;
		this.genre = genre;
		this.released = released;
		this.plot = plot;
		this.ratings = ratings;
		this.poster = poster;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getGenre(){
		return genre;
	}

	public void setGenre(String genre){
		this.genre = genre;
	}

	public String getReleased(){
		return released;
	}

	public void setReleased(String released){
		this.released = released;
	}

	public String getPlot(){
		return plot;
	}

	public void setPlot(String plot){
		this.plot = plot;
	}

	public String getRatings(){
		return ratings;
	}

	public void setRatings(String ratings){
		this.ratings = ratings;
	}

	public String getPoster(){
		return poster;
	}

	public void setPoster(String poster){
		this.poster = poster;
	}
}
