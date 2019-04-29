package ie.nr.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ie.nr.R;
import ie.nr.activities.MainActivity;
import ie.nr.main.NetflixReviewApp;
import ie.nr.models.Review;

public class AddReviewFragment extends BaseFragment {

    private String  mediaName, userReview,addCaption;
    private double  userRating;
    private EditText name,review,caption;
    private NumberPicker ratingNum;
    private NetflixReviewApp app;
    private Button saveButton;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    public AddReviewFragment(){

    }

    public static AddReviewFragment newInstance(){
        AddReviewFragment fragmant = new AddReviewFragment();
        return fragmant;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (NetflixReviewApp) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add, container, false);
        getActivity().setTitle(R.string.addReviewLbl);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("reviews");
        name = v.findViewById(R.id.addNameET);
        review =  v.findViewById(R.id.addReviewET);
        caption =  v.findViewById(R.id.addCaptionET);
        ratingNum =  v.findViewById(R.id.addRatingNum);
        saveButton = v.findViewById(R.id.addReviewBtn);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NumberPicker numberPicker = view.findViewById(R.id.addRatingNum);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReview();
            }
        });


        numberPicker.setOnValueChangedListener(onValueChangeListener);
    }

    NumberPicker.OnValueChangeListener onValueChangeListener=
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    Toast.makeText(getContext(),
                            "selected number "+ picker.getValue(), Toast.LENGTH_SHORT);
                }
            };

    public void addReview() {

        mediaName = name.getText().toString();
        userReview =review.getText().toString();
        addCaption=caption.getText().toString();

        userRating = ratingNum.getValue();

        if ((mediaName.length() > 0) && (userReview.length() > 0)
                && (addCaption.length() > 0)) {
            String reviewId = mFirebaseDatabase.getReference("Reviews").push().getKey();
            Review c = new Review(reviewId, mediaName, userReview,userRating,addCaption,false);
            mDatabaseReference.child(reviewId).setValue(c);


            //app.dbManager.add(c);
            startActivity(new Intent(this.getActivity(), MainActivity.class));
        } else
            Toast.makeText(
                    this.getActivity(),
                    "You must Enter Something for "
                            + "\'Name\', \'Review\' and \'Caption\'",
                    Toast.LENGTH_SHORT).show();
    }


}
