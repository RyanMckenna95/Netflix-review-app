package ie.nr.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import ie.nr.R;

public class BaseFragment  extends Fragment {
    public static void addReviewFragment (FragmentActivity activity) {
        AddReviewFragment addReviewFragment = new AddReviewFragment();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFrame, addReviewFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void calendarFragment(FragmentActivity activity) {
        CalendarFragment calendarFragment = new CalendarFragment();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFrame, calendarFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void editFragment(FragmentActivity activity) {
        EditFragment editFragment = new EditFragment();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFrame, editFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void reviewFragment(FragmentActivity activity) {
        ReviewFragment reviewfragement = new ReviewFragment();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFrame, reviewfragement)
                .addToBackStack(null)
                .commit();
    }

    public static void searchFragment(FragmentActivity activity) {
        SearchFragment searchFragment = new SearchFragment();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFrame, searchFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void trendingFragment(FragmentActivity activity) {
        TrendingFragment trendingFragment = new TrendingFragment();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFrame, trendingFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void favouriteFragment(FragmentActivity activity) {
        FavouriteFragment favouriteFragment = new FavouriteFragment();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFrame, favouriteFragment)
                .addToBackStack(null)
                .commit();
    }


}
