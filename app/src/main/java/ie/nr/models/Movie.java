package ie.nr.models;

public class Movie {
    public int movieId;
    public String movieTitle;
    public String releaseDate;
    public String movieImage;
    public String movieOverview;

    public Movie() {
    }

    public Movie(final int movieId, final String movieTitle, final String releaseDate, final String movieImage) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.movieImage = movieImage;
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