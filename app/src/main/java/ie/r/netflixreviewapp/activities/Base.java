package ie.r.netflixreviewapp.activities;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.URL;

import ie.r.netflixreviewapp.R;

public class Base extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Content_Home);

        String URL="https://api.themoviedb.org/3/movie/550?api_key=" + getString(R.string.pubKey)+"&language=en-US";
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                URL,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );
    }
}
