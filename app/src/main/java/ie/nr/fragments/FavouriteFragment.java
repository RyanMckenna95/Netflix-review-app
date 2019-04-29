package ie.nr.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ie.nr.R;
import ie.nr.activities.BaseActivity;
import ie.nr.adapters.FavouriteAdapter;
import ie.nr.adapters.ReviewFilter;
import ie.nr.models.Review;

public class FavouriteFragment extends BaseFragment implements AdapterView.OnItemClickListener,View.OnClickListener {
    public BaseActivity activity;
    public static FavouriteAdapter listAdapter;
    public RecyclerView listView;
    public ReviewFilter reviewFilter;
    public boolean favourites = false;
    public FirebaseDatabase mFirebaseDatabase;
    public DatabaseReference mReference;
    public FirebaseAuth mAuth;
    public ArrayList<Review> favouriteList;
    public ArrayList<Review> myReviews;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favourites, container, false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference();
        listView = v.findViewById(R.id.homeList);
        favouriteList = new ArrayList<>();
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        getReviews();
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    private void getReviews() {
        mFirebaseDatabase.getReference().child("reviews").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot review : dataSnapshot.getChildren()) {
                                Review rew = review.getValue(Review.class);
                                if (rew.favourite){
                                    favouriteList.add(rew);
                                }
                                listAdapter = new FavouriteAdapter(getContext(), favouriteList);
                                listView.setAdapter(listAdapter);
                                listAdapter.notifyDataSetChanged();


                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull final DatabaseError databaseError) {

                    }
                });

    }

            @Override
            public void onClick(View v) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }
