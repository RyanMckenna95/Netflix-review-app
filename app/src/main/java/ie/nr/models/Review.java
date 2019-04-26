package ie.nr.models;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Review extends RealmObject {

    @PrimaryKey
    public String reviewId;
    public String name;
    public String review;
    public String caption;
    public double rating;
    public boolean favourite;

    public Review(){

    }

    public Review(String reviewId, String name, String review, double rating,String caption, boolean favy)
    {
        this.reviewId= reviewId;
        this.name=name;
        this.review=review;
        this.caption=caption;
        this.rating=rating;
        this.favourite=favy;
    }

    public String toString() {
        return "Review of [name=" + name + ", caption =" + caption
                + ", review =" + review + ", rating=" + rating + ", price="
                + ", favy =" + favourite + "]";
    }
}
