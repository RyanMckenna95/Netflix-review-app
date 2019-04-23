package ie.nr.db;

import ie.nr.models.Review;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import android.content.Context;
import android.database.SQLException;

public class DBManager {

    public Realm realmDatabase;

    public DBManager(Context context){
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("reviews.realm")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(config);
    }

    public void open() throws SQLException {
        realmDatabase = Realm.getDefaultInstance();
    }

    public void close() {realmDatabase.close();}

    public void add(Review c) {
        realmDatabase.beginTransaction();
        realmDatabase.copyToRealm(c);
        realmDatabase.commitTransaction();
    }

    public void update(Review c, String name, String review,String caption, double rating)
    {
        realmDatabase.beginTransaction();
        c.name=name;
        c.review=review;
        c.caption=caption;
        c.rating=rating;
        realmDatabase.commitTransaction();
    }

    public void setFavs(Review c, boolean value){
        realmDatabase.beginTransaction();
        c.favourite=value;
        realmDatabase.commitTransaction();
    }

    public void updateName(String name, String reviewId)
    {

        Review c = realmDatabase.where(Review.class)
                .equalTo("reviewId",reviewId)
                .findFirst();
        realmDatabase.beginTransaction();
        c.name = name;
        realmDatabase.commitTransaction();
    }

    public void delete(String reviewId) {
        realmDatabase.beginTransaction();
        realmDatabase.where(Review.class)
                .equalTo("reviewId",reviewId)
                .findAll()
                .deleteAllFromRealm();
        realmDatabase.commitTransaction();
    }

    public RealmResults<Review> getAll() {
        RealmResults<Review> result = realmDatabase.where(Review.class)
                .findAll();
        return result;
    }

    public RealmResults<Review> getFavourites() {
        return realmDatabase.where(Review.class)
                .equalTo("favourite",true)
                .findAll();
    }

    public Review get(String reviewId) {
        return realmDatabase.where(Review.class)
                .equalTo("reviewId",reviewId)
                .findAll()
                .first();
    }

    public void reset() {
        realmDatabase.beginTransaction();
        realmDatabase.where(Review.class)
                .findAll()
                .deleteAllFromRealm();
        realmDatabase.commitTransaction();
    }
}
