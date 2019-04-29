package ie.nr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ie.nr.R;
import ie.nr.fragments.AddReviewFragment;
import ie.nr.fragments.BaseFragment;
import ie.nr.models.Movie;

public class DetailActivity extends AppCompatActivity {
    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieDesc;
    private Button toReview;
    private Movie movie;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmant_details);
        moviePoster = findViewById(R.id.movieCover);
        movieTitle = findViewById(R.id.movieTitleDesc);
        movieDesc = findViewById(R.id.movieDesc);
        toReview = findViewById(R.id.addToReviewBtn);
        movie = (Movie)getIntent().getSerializableExtra("Movie");
        movieTitle.setText(movie.movieTitle);
        movieDesc.setText(movie.movieOverview);
        Glide.with(this)
                .load(movie.movieImage)
                .into(moviePoster);


        toReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });
    }

}
