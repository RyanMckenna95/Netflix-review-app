package ie.cm.adapters;

import android.widget.Filter;

import ie.cm.db.DBManager;
import ie.cm.models.Review;
import io.realm.Case;
import io.realm.OrderedRealmCollection;
import io.realm.RealmResults;

public class ReviewFilter extends Filter {

    private OrderedRealmCollection<Review>  originalReviewList;
    private RealmResults<Review> realmReviewResults;
    private ReviewListAdapter adapter;
    private boolean favourites= false;
    private DBManager dbManager;

    public ReviewFilter(DBManager dbManager, ReviewListAdapter adapter){
        super();
        this.dbManager=dbManager;
        this.originalReviewList=dbManager.getAll();
        this.adapter=adapter;
    }

    public void setFilter(String filterText) {
        favourites = !filterText.equals("all");
    }

    @Override
    protected FilterResults performFiltering(CharSequence prefix) {
        return new FilterResults();
    }

    @Override
    protected void publishResults(CharSequence prefix, FilterResults results) {

        if ((prefix == null || prefix.length() == 0))
            if(!favourites)
                realmReviewResults=dbManager.getAll();
            else
                realmReviewResults=dbManager.getFavourites();
        else {
            realmReviewResults=dbManager.realmDatabase
                    .where(Review.class)
                   // .equalTo("favourite",favourites)
                    .contains("name",prefix.toString(), Case.INSENSITIVE)
                    .findAll();
        }
        adapter.reviewList=realmReviewResults;

        if(adapter.reviewList.size()>0)
            adapter.notifyDataSetChanged();
        else {
            adapter.notifyDataSetInvalidated();
            adapter.reviewList=originalReviewList;
        }

    }
}
