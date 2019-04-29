package ie.nr.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ie.nr.R;
import ie.nr.models.Movie;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;

    private List<Movie> movieList;

    public SearchAdapter(final Context context, final List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }


    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_list, viewGroup, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchAdapter.ViewHolder viewHolder, final int i) {
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
