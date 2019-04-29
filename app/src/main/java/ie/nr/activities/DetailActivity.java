package ie.nr.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ie.nr.R;
import ie.nr.models.Movie;

public class DetailActivity extends AppCompatActivity {
    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieDesc;
    private Button addToWatch;
    private Movie movie;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmant_details);
        moviePoster = findViewById(R.id.movieCover);
        movieTitle = findViewById(R.id.movieTitleDesc);
        movieDesc = findViewById(R.id.movieDesc);
        addToWatch = findViewById(R.id.addToWatch);
        movie = (Movie)getIntent().getSerializableExtra("Movie");
        movieTitle.setText(movie.movieTitle);
        movieDesc.setText(movie.movieOverview);
        Glide.with(this)
                .load(movie.movieImage)
                .into(moviePoster);


    }
}
