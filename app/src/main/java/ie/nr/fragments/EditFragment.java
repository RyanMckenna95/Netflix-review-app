package ie.nr.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ie.nr.R;
import ie.nr.activities.BaseActivity;
import ie.nr.main.NetflixReviewApp;
import ie.nr.models.Review;

public class EditFragment extends BaseFragment {

    public boolean isFavourite;
    public int position;
    public Review review;
    public ImageView editFavourite;
    private EditText name, reviewET, caption;
    private NumberPicker ratingNum;
    public NetflixReviewApp app;
    public FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    public Button editBtn;

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
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();


        if(getArguments() != null) {
            position = getArguments().getInt("reviewId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit, container, false);
        name = v.findViewById(R.id.editTitleET);
        caption = v.findViewById(R.id.editCaptionET);
        reviewET = v.findViewById(R.id.editReviewET);
        ratingNum = v.findViewById(R.id.editRatingNum);
        editFavourite = v.findViewById(R.id.editFavourite);
        editBtn = v.findViewById(R.id.editReviewBtn);

        name.setText(BaseActivity.reviewArrayList.get(position).name);
        caption.setText(BaseActivity.reviewArrayList.get(position).caption);
        reviewET.setText(BaseActivity.reviewArrayList.get(position).review);
        ratingNum.setValue((int) BaseActivity.reviewArrayList.get(position).rating);

        if (BaseActivity.reviewArrayList.get(position).favourite == true) {
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
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateReview();
            }
        });

    }
    NumberPicker.OnValueChangeListener onValueChangeListener=
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    Toast.makeText(getContext(),
                            "selected number "+ picker.getValue(), Toast.LENGTH_SHORT);
                }
            };


    public void updateReview() {
        if (mListener != null) {
            String reviewTitle = name.getText().toString();
            String reviewCaption = caption.getText().toString();
            String reviewStr = reviewET.getText().toString();
            double ratingValue = ratingNum.getValue();


            if ((!TextUtils.isEmpty(reviewTitle)) || (!TextUtils.isEmpty(reviewCaption)) || (!TextUtils.isEmpty(reviewStr))) {
                Review newReview = new Review(BaseActivity.reviewArrayList.get(position).reviewId, reviewTitle, reviewCaption,ratingValue, reviewStr, isFavourite);
                mDatabase.getReference("reviews").child(BaseActivity.reviewArrayList.get(position).reviewId).setValue(newReview);


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
            isFavourite = false;
            editFavourite.setImageResource(R.drawable.favourites_72);
        } else {
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
        void updateReview(View v);
    }
}
