package ie.cm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import ie.cm.R;
import ie.cm.models.Movie;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public TrendingAdapter(final Context context, final List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }


    @NonNull
    @Override
    public TrendingAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_list, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrendingAdapter.ViewHolder viewHolder, final int i) {
        Movie movie = movieList.get(i);
        String urlImage = movie.getMovieImage();

        viewHolder.movieTitle.setText(movie.movieTitle);
        viewHolder.movieReleaseDate.setText(movie.releaseDate);

        Glide.with(context)
                .load(urlImage)
                .into(viewHolder.movieImage);


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView movieTitle;

        public TextView movieReleaseDate;

        public ImageView movieImage;

        public ViewHolder(@NonNull final View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieReleaseDate = itemView.findViewById(R.id.movieReleaseDate);
            movieImage = itemView.findViewById(R.id.movieCover);

        }
    }
}
