package ie.nr.activities;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ie.nr.main.NetflixReviewApp;
import ie.nr.R;
import ie.nr.models.Review;

public class Base extends AppCompatActivity {
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w200/";
    public static final String API_URL = "https://api.themoviedb.org/3/trending/movie/day?api_key=a9d0e77cbdc5499ba36ac78ea253545a";
    public static ArrayList<Review> reviewArrayList;

    public NetflixReviewApp app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewArrayList = new ArrayList<>();

        app = (NetflixReviewApp) getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void menuHome(MenuItem m) {
        startActivity(new Intent(this, Home.class));
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
}