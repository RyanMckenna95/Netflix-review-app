package ie.nr.activities;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ie.nr.fragments.BaseFragment;
import ie.nr.main.NetflixReviewApp;
import ie.nr.R;
import ie.nr.models.Movie;
import ie.nr.models.Review;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w200/";
    public static final String API_URL = "https://api.themoviedb.org/3/trending/movie/day?api_key=a9d0e77cbdc5499ba36ac78ea253545a";
    public static final String SEARCH_URL = "https://api.themoviedb.org/3/search/movie?api_key=a9d0e77cbdc5499ba36ac78ea253545a&query=";
    public static ArrayList<Review> reviewArrayList;
    public static ArrayList<Movie> movieList;

    public NetflixReviewApp app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewArrayList = new ArrayList<>();
        movieList = new ArrayList<>();

        app = (NetflixReviewApp) getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void menuHome(MenuItem m) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void menuInfo(MenuItem m) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.appAbout))
                .setMessage(getString(R.string.appDesc)
                        + "\n\n"
                        + getString(R.string.appMoreInfo))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // we could put some code here too
                    }
                })
                .show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            BaseFragment.reviewFragment(this);
        } else if (id == R.id.nav_calendar) {
            BaseFragment.calendarFragment(this);
        } else if (id == R.id.nav_add) {
            BaseFragment.addReviewFragment(this);
        }else if (id == R.id.nav_search){
            BaseFragment.searchFragment(this);
        }else if (id == R.id.nav_trending){
            BaseFragment.trendingFragment(this);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}