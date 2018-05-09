package com.coco.bottomnavigationitemviewdemo;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private BottomNavigationView bottomNavigationView;
    private OneFragment fragment1;
    private TWoFragment fragment2;
    private ThreeFragment fragment3;
    private FourFragment fragment4;
    private Fragment[] fragments;
    private int lastShowFragment = 0;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        initFragments();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_news:
                                if (lastShowFragment != 0) {
                                    switchFrament(lastShowFragment, 0);
                                    lastShowFragment = 0;
                                }
                               return true;
                            case R.id.item_lib:
                                if (lastShowFragment != 1) {
                                    switchFrament(lastShowFragment, 1);
                                    lastShowFragment = 1;
                                }
                                return true;

                            case R.id.item_find:
                                if (lastShowFragment != 2) {
                                    switchFrament(lastShowFragment, 2);
                                    lastShowFragment = 2;
                                }
                                return true;
                            case R.id.item_more:
                                if (lastShowFragment != 3) {
                                    switchFrament(lastShowFragment, 3);
                                    lastShowFragment = 3;
                                }
                                return true;
                        }
                        return false;
                    }
                });

    }
    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fragment_container, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    private void initFragments() {
        fragment1 = new OneFragment();
        fragment2 = new TWoFragment();
        fragment3 = new ThreeFragment();
        fragment4 = new FourFragment();
        fragments = new Fragment[]{fragment1, fragment2, fragment3, fragment4};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .show(fragment1)
                .commit();
    }
}
