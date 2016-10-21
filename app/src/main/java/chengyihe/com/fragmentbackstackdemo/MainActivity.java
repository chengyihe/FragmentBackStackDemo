package chengyihe.com.fragmentbackstackdemo;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{
    private final String TAG = getClass().getSimpleName();
    private Fragment mFirstFragment = new FirstFragment();
    private Fragment mSecondFragment = new SecondFragment();

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate()++");

        FragmentTransaction transaction;

        transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_element, mFirstFragment);
        transaction.addToBackStack("state in which the first fragment is showing");
        transaction.commit();

        transaction = getFragmentManager().beginTransaction();
        transaction.detach(mFirstFragment);
        transaction.add(R.id.fragment_element, mSecondFragment);
        transaction.addToBackStack("state in which the second fragment is showing");
        transaction.commit();

        getFragmentManager().addOnBackStackChangedListener(this);

        Log.d(TAG, "onCreate()--");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    public void onBackStackChanged() {
        int count = getFragmentManager().getBackStackEntryCount();

        Log.d(TAG, "onBackStackChanged(): backstack count is " + count);

        if (count > 0)
            Log.d(TAG, "onBackStackChanged(): backstack top is " + getFragmentManager().getBackStackEntryAt(count - 1).getName());
    }
}
