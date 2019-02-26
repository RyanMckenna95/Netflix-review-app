package ie.cm.db;

import ie.cm.models.Review;
import io.realm.Realm;
import io.realm.RealmConfiguration;

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

    public void update(Review c, String name, String review, double rating)
    {
        realmDatabase.beginTransaction();
        c.name=name;
        c.review=review;
        c.rating=rating;
        realmDatabase.commitTransaction();
    }

    public void setFavs(Review c, boolean value){
        realmDatabase.beginTransaction();
        c.favourite=value;
        realmDatabase.commitTransaction();
    }
}
