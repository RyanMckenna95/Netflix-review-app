package ie.cm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;

import ie.cm.R;
import ie.cm.main.NetflixReviewApp;

public class AddReviewFragment extends Fragment {

    private String  mediaName, userReview;
    private double  userRating;
    private EditText name,review;
    private RatingBar ratingBar;
    private NetflixReviewApp app;

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
        name = v.findViewById(R.id.addNameET);
        review =  v.findViewById(R.id.addShopET);
        price =  v.findViewById(R.id.addPriceET);
        ratingBar =  v.findViewById(R.id.addRatingBar);
        saveButton = v.findViewById(R.id.addCoffeeBtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCoffee();
            }
        });

        return v;
    }
}
