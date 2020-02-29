package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements WorkoutListFragment.WorkoutListListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WorkoutDetailFragment frag = (WorkoutDetailFragment)getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        assert frag != null;
        frag.setWorkoutId(3);
        WorkoutListFragment fragL = (WorkoutListFragment)getSupportFragmentManager().findFragmentById(R.id.list_frag);



    }

    private FragmentManager getSupportFragmentManager() {
        return null;
    }

    @Override
    public void itemClicked(long id) {

    }
}
