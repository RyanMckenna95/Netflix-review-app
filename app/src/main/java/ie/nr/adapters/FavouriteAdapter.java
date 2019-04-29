package ie.nr.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ie.nr.R;
import ie.nr.models.Movie;
import ie.nr.models.Review;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private Context context;
    public ArrayList<Review> reviewList;

    public FavouriteAdapter(Context context, ArrayList<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favrow, parent, false);
        return new FavouriteAdapter.ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder viewHolder, int i) {
        Review movie = reviewList.get(i);
        viewHolder.rowTitle.setText(movie.name);
        viewHolder.rowCaption.setText(movie.caption);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView rowImage;
        public ImageView rowDelete;
        public TextView rowTitle;
        public TextView rowCaption;
        public TextView rowRating;


        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            rowImage = itemView.findViewById(R.id.rowFavouriteImg);
            rowDelete = itemView.findViewById(R.id.rowDeleteImg);
            rowTitle = itemView.findViewById(R.id.rowTitleName);
            rowCaption = itemView.findViewById(R.id.rowCaption);
            rowRating = itemView.findViewById(R.id.rowRating);
        }
    }
}

