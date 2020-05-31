package com.example.dotinternshiptest.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import com.example.dotinternshiptest.R;
import com.example.dotinternshiptest.ui.main.detail.single.SingleDetailFragment;
import com.example.dotinternshiptest.ui.main.gallery.GalleryFragment;
import com.example.dotinternshiptest.ui.main.list.ListFragment;
import com.example.dotinternshiptest.ui.main.profile.ProfileFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String NAVBAR_STATE = "NAVBAR_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar materialToolbar = findViewById(R.id.mt_activity);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);

        if (savedInstanceState == null) {
            materialToolbar.setTitle("List");
            setFragment(new ListFragment());
        }
        Bundle bundle = new Bundle();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.list_bottomnavbar_menu: {
                    fragment = new com.example.dotinternshiptest.ui.main.list.ListFragment();
                    materialToolbar.setTitle("List");
                    materialToolbar.setNavigationIcon(null);
                    bundle.putInt(NAVBAR_STATE, 0);
                    break;
                }
                case R.id.gallery_bottomnavbar_menu: {
                    fragment = new GalleryFragment();
                    materialToolbar.setTitle("Gallery");
                    materialToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
                    bundle.putInt(NAVBAR_STATE, 1);
                    break;
                }
                case R.id.profile_bottomnavbar_menu: {
                    fragment = new ProfileFragment();
                    materialToolbar.setTitle("Profile");
                    materialToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
                    bundle.putInt(NAVBAR_STATE, 2);
                    break;
                }
                default:
                    fragment = new ListFragment();
            }
            setFragment(fragment);
            return true;
        });

        onSaveInstanceState(bundle);
    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_main_fragmentcontainer, fragment)
                .commit();
    }

}
