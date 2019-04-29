package ie.nr.models;

import java.io.Serializable;

public class Movie implements Serializable {
    public int movieId;
    public String movieTitle;
    public String releaseDate;
    public String movieImage;
    public String movieOverview;
    public int movieNumber;

    public Movie() {
    }

    public Movie(final int movieId, final String movieTitle, final String releaseDate, final String movieImage, int movieNumber, String movieOverview) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.movieImage = movieImage;
        this.movieNumber = movieNumber;
        this.movieOverview = movieOverview;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(final String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public int getMovieNumber() {
        return movieNumber;
    }

    public void setMovieNumber(final int movieNumber) {
        this.movieNumber = movieNumber;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(final int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(final String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(final String movieImage) {
        this.movieImage = movieImage;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", movieImage='" + movieImage + '\'' +
                '}';
    }
}