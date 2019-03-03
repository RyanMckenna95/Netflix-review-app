package ie.cm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import ie.cm.R;
import ie.cm.main.NetflixReviewApp;
import ie.cm.models.Review;

public class EditFragment extends Fragment {

    public boolean isFavourite;
    public Review aReview;
    public ImageView editFavourite;
    private EditText name, review, caption;
    private NumberPicker ratingNum;
    public NetflixReviewApp app;

    private OnFragmentInteractionListener mListener;

    public EditFragment() {

    }

    public static EditFragment newInstance(Bundle reviewBundle) {
        EditFragment fragment = new EditFragment();
        fragment.setArguments(reviewBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        app = (NetflixReviewApp) getActivity().getApplication();

        if(getArguments() != null)
            aReview = app.dbManager.get(getArguments().getString("reviewId"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit, container, false);

        ((TextView)v.findViewById(R.id.disTitleTV)).setText(aReview.name);

        name = v.findViewById(R.id.editTitleET);
        caption = v.findViewById(R.id.editCaptionET);
        review = v.findViewById(R.id.editReviewET);
        ratingNum = v.findViewById(R.id.editRatingNum);
        editFavourite = v.findViewById(R.id.editFavourite);

        name.setText(aReview.name);
        caption.setText(aReview.caption);
        review.setText(aReview.review);
        ratingNum.setValue((int)aReview.rating);

        if (aReview.favourite == true) {
            editFavourite.setImageResource(R.drawable.favourites_72_on);
            isFavourite = true;
        } else {
            editFavourite.setImageResource(R.drawable.favourites_72);
            isFavourite = false;
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



       ratingNum.setMinValue(1);
       ratingNum.setMaxValue(10);


        ratingNum.setOnValueChangedListener(onValueChangeListener);
    }
    NumberPicker.OnValueChangeListener onValueChangeListener=
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    Toast.makeText(getContext(),
                            "selected number "+ picker.getValue(), Toast.LENGTH_SHORT);
                }
            };

    public void saveReview(View v) {
        if (mListener != null) {
            String reviewTitle = name.getText().toString();
            String reviewCaption = caption.getText().toString();
            String reviewStr = review.getText().toString();
            double ratingValue = ratingNum.getValue();


            if ((reviewTitle.length() > 0) && (reviewCaption.length() > 0) && (reviewStr.length() > 0)) {
                app.dbManager.update(aReview,reviewTitle,reviewCaption,reviewStr,ratingValue);

                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                    return;
                }
            }
        } else
            Toast.makeText(getActivity(), "You must Enter Something for Title and Caption", Toast.LENGTH_SHORT).show();
    }

    public void toggle(View v) {

        if (isFavourite) {
            app.dbManager.setFavs(aReview,false);
            Toast.makeText(getActivity(), "Removed From Favourites", Toast.LENGTH_SHORT).show();
            isFavourite = false;
            editFavourite.setImageResource(R.drawable.favourites_72);
        } else {
            app.dbManager.setFavs(aReview,true);
            Toast.makeText(getActivity(), "Added to Favourites", Toast.LENGTH_SHORT).show();
            isFavourite = true;
            editFavourite.setImageResource(R.drawable.favourites_72_on);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void toggle(View v);
        void saveReview(View v);
    }
}
