package ie.cm.main;

import ie.cm.db.DBManager;
import android.app.Application;
import android.util.Log;

public class NetflixReviewApp extends Application{
    public DBManager dbManager;


    public void onCreate(){
        super.onCreate();
        Log.v("netflixReview", "Netflixreview App Started");
        dbManager = new DBManager(this);
        dbManager.open();
        Log.v("netflixReview", "Realm Database Created & Opened");
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        Log.v("netflixReview", "Realm Database Closed");
        dbManager.close();
    }

}
