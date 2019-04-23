package ie.nr.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import ie.nr.R;
import ie.nr.models.Review;

public class ReviewListAdapter extends ArrayAdapter<Review> {
        private Context context;
        private View.OnClickListener deleteListener;
        public List<Review> reviewList;

    public ReviewListAdapter(Context context, View.OnClickListener deleteListener, List<Review> reviewList) {
        super(context, R.layout.reviewrow, reviewList);

        this.context = context;
        this.deleteListener = deleteListener;
        this.reviewList = reviewList;
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        ReviewItem item = new ReviewItem(context, parent,
                deleteListener, reviewList.get(position));
        return item.view;
    }

        @Override
        public int getCount() {
        return reviewList.size();
    }

        @Override
        public Review getItem(int position) {
        return reviewList.get(position);
    }
}

