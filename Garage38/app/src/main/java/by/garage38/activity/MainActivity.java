package by.garage38.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import by.garage38.R;
import by.garage38.fragment.FragmentMain;

public class MainActivity extends ActionBarActivity {

    private static final String TAG_MAIN_FRAGMENT = "main";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportFragmentManager().findFragmentByTag(TAG_MAIN_FRAGMENT) == null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main, FragmentMain.newInstance(), TAG_MAIN_FRAGMENT)
                    .commit();
        }



    }






}
