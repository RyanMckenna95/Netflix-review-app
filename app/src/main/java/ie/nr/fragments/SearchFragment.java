package ie.nr.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ie.nr.R;
import ie.nr.activities.BaseActivity;
import ie.nr.adapters.SearchAdapter;
import ie.nr.models.Movie;

public class SearchFragment extends BaseFragment {

    private String mySearch;
    private int pageNumber;
    private EditText searchString;
    private Button searchBtn;
    private ArrayList<Movie> movieList;
    private RequestQueue mQueue;
    private RecyclerView searchRecyclerView;
    private SearchAdapter mSearchAdapter;
    private Button next;
    private Button prev;


    public SearchFragment()  {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getActivity().setTitle(R.string.searchReviewsLbl);
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        searchString = v.findViewById(R.id.searchView);
        searchRecyclerView = v.findViewById(R.id.searchRecycler);
        searchBtn = v.findViewById(R.id.searchBtn);
        next = v.findViewById(R.id.nextPage);
        prev = v.findViewById(R.id.prevPage);
        mQueue = Volley.newRequestQueue(getContext());
        movieList = new ArrayList<>();
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pageNumber = 1;


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mySearch = searchString.getText().toString();
                Log.i("SEARCH===>>>", mySearch);
                if (!mySearch.isEmpty()) {
                    getSearchedMovies(mySearch, pageNumber);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                pageNumber++;
                mySearch = searchString.getText().toString();
                Log.i("SEARCH===>>>", mySearch);
                if (!mySearch.isEmpty()) {
                    getSearchedMovies(mySearch, pageNumber);
                }

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                pageNumber--;
                mySearch = searchString.getText().toString();
                Log.i("SEARCH===>>>", mySearch);
                if (!mySearch.isEmpty()) {
                    getSearchedMovies(mySearch, pageNumber);
                }
            }
        });


        return v;
    }

    public void getSearchedMovies(String search, int pageNumber) {
        JsonObjectRequest searchRequest = new JsonObjectRequest(Request.Method.GET, BaseActivity.SEARCH_URL + search + "&page=" + pageNumber,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {
                            int maxTitleSize = 17;
                            JSONArray movieInfo = response.getJSONArray("results");
                            movieList.clear(); // Clear out list for new search
                            for (int i = 0; i < movieInfo.length(); i++) {
                                JSONObject movie = movieInfo.getJSONObject(i);
                                Movie searchedMovie = new Movie();
                                searchedMovie.movieId = movie.getInt("id");
                                searchedMovie.movieTitle = movie.optString("title");
                                searchedMovie.releaseDate = movie.optString("release_date");
                                searchedMovie.movieImage = movie.optString("poster_path");
                                if (searchedMovie.movieTitle.length() > maxTitleSize) {
                                    searchedMovie.movieTitle = searchedMovie.movieTitle.substring(0, maxTitleSize);
                                }

                                searchedMovie.setMovieImage(BaseActivity.IMAGE_URL + searchedMovie.movieImage);

                                movieList.add(searchedMovie);
                                mSearchAdapter = new SearchAdapter(getContext(), movieList);
                                searchRecyclerView.setAdapter(mSearchAdapter);

                                mSearchAdapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Toast.makeText(getContext(), "Request could not be gotten, Please try again later", Toast.LENGTH_SHORT).show();
                    }
                });
        mQueue.add(searchRequest);

    }

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}