package ie.cm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.RatingBar;

import ie.cm.main.NetflixReviewApp;

public class AddReviewFragmant extends Fragment {

    private String  mediaName, userReview;
    private double  userRating;
    private EditText name,review;
    private RatingBar ratingBar;
    private NetflixReviewApp app;

    public AddReviewFragmant(){

    }

    public static AddReviewFragmant newInstance(){
        AddReviewFragmant fragmant = new AddReviewFragmant();

        return fragmant;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (NetflixReviewApp) getActivity().getApplication();
    }
}
