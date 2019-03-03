package ie.cm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import ie.cm.R;
import ie.cm.activities.Base;
import ie.cm.adapters.TrendingAdapter;
import ie.cm.models.Movie;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrendingFragment extends Fragment {
    public RequestQueue mQueue;
    public RecyclerView mRecyclerView;
    public TrendingAdapter mTrendingAdapter;
    public LayoutManager mLayoutManager;
    public List<Movie> movieList;

    public TrendingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
    }

    public static TrendingFragment newInstance() {
        TrendingFragment fragment = new TrendingFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getActivity().setTitle(R.string.searchReviewsLbl);
        View v = inflater.inflate(R.layout.trending_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mQueue = Volley.newRequestQueue(getContext());

        movieList = new ArrayList<Movie>();
        getTrendingMovies();

    }

    public void getTrendingMovies(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Base.API_URL,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try{
                            int maxTitleSize = 17;
                            JSONArray movieInfo = response.getJSONArray("results");
                            for(int i = 0; i < movieInfo.length(); i++){
                                JSONObject movie = movieInfo.getJSONObject(i);
                                Movie trendingMovies = new Movie();
                                trendingMovies.movieId = movie.getInt("id");
                                trendingMovies.movieTitle = movie.optString("title");
                                trendingMovies.releaseDate = movie.optString("release_date");
                                trendingMovies.movieImage = movie.optString("poster_path");
                                if(trendingMovies.movieTitle.length()  > maxTitleSize){
                                    trendingMovies.movieTitle = trendingMovies.movieTitle.substring(0, maxTitleSize);
                                }

                                trendingMovies.setMovieImage(Base.IMAGE_URL + trendingMovies.movieImage);

                                movieList.add(trendingMovies);

                                mTrendingAdapter = new TrendingAdapter(getContext(), movieList);
                                mRecyclerView.setAdapter(mTrendingAdapter);

                                mTrendingAdapter.notifyDataSetChanged();

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Toast.makeText(getContext(), "Request could not be gotten, Please try again later", Toast.LENGTH_SHORT).show();
                    }
                });
        mQueue.add(jsonObjectRequest);

    }
}