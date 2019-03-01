package ie.cm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import ie.cm.R;
import ie.cm.models.Review;

public class ReviewItem {
    public View view;

    public ReviewItem(Context context, ViewGroup parent,
                      View.OnClickListener deleteListener, Review review)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.reviewcard, parent, false);
        view.setTag(review.reviewId);

        updateControls(review);

        ImageView imgDelete = view.findViewById(R.id.rowDeleteImg);
        imgDelete.setTag(review);
        imgDelete.setOnClickListener(deleteListener);
    }

    private void updateControls(Review review) {
        ((TextView) view.findViewById(R.id.rowTitleName)).setText(review.name);

        ((TextView) view.findViewById(R.id.rowCaption)).setText(review.caption);
        ((TextView) view.findViewById(R.id.rowRating)).setText(review.rating + " *");
        ImageView imgIcon = view.findViewById(R.id.rowFavouriteImg);

        if (review.favourite == true)
            imgIcon.setImageResource(R.drawable.favourites_72_on);
        else
            imgIcon.setImageResource(R.drawable.favourites_72);


    }
}
