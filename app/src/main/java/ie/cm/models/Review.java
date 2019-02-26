package ie.cm.models;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Review extends RealmObject {

    @PrimaryKey
    public String reviewId;
    public String name;
    public String review;
    public double rating;
    public boolean favourite;

    public Review(){}

    public Review(String name, String review, double rating, boolean favy)
    {
        this.reviewId= UUID.randomUUID().toString();
        this.name=name;
        this.review=review;
        this.rating=rating;
        this.favourite=favy;
    }

    public String toString() {
        return "Review of [name=" + name
                + ", review =" + review + ", rating=" + rating + ", price="
                + ", favy =" + favourite + "]";
    }
}
