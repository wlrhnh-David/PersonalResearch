package com.frankzhu.recyclerviewdemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.frankzhu.recyclerviewdemo.R;
import com.frankzhu.recyclerviewdemo.fragment.AnimFragment;
import com.frankzhu.recyclerviewdemo.fragment.FullyExpandedFragment;
import com.frankzhu.recyclerviewdemo.fragment.MultipleFragment;
import com.frankzhu.recyclerviewdemo.fragment.MultipleHeaderBottomFragment;
import com.frankzhu.recyclerviewdemo.fragment.NormalFragment;

import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {
    private Fragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        int index = getIntent().getIntExtra("position", 0);
        Log.d("NormalTextViewHolder", "onClick--> index = " + index);
        String title = getIntent().getStringExtra("title");
        setTitle(title);
        updateFragment(index);
    }

    private void updateFragment(int index) {
        if (index == 0) {
            updateNormalFragment(NormalFragment.TYPE_LINEAR_LAYOUT);
        } else if (index == 1) {
            updateNormalFragment(NormalFragment.TYPE_GRID_LAYOUT);
        } else if (index == 2) {
            updateNormalFragment(NormalFragment.TYPE_STAGGERED_GRID_LAYOUT);
        } else if (index == 3) {
            updateMultipleFragment(MultipleFragment.TYPE_LINEAR_LAYOUT);
        } else if (index == 4) {
            updateMultipleFragment(MultipleFragment.TYPE_GRID_LAYOUT);
        } else if (index == 5) {
            updateMultipleHeaderFragment(MultipleFragment.TYPE_LINEAR_LAYOUT);
        } else if (index == 6) {
            updateMultipleHeaderFragment(MultipleFragment.TYPE_GRID_LAYOUT);
        } else if (index == 7) {
            updateAnimFragment(MultipleFragment.TYPE_LINEAR_LAYOUT);
        } else if (index == 8) {
            updateAnimFragment(MultipleFragment.TYPE_GRID_LAYOUT);
        } else if (index == 9) {
            updateFullyExpandedFragment(MultipleFragment.TYPE_LINEAR_LAYOUT);
        } else if (index == 10) {
            updateFullyExpandedFragment(MultipleFragment.TYPE_GRID_LAYOUT);
        }
    }

    public void updateNormalFragment(int type) {
        mFragment = NormalFragment.newInstance(type);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mFragment)
                .commit();
    }

    public void updateMultipleFragment(int type) {
        mFragment = MultipleFragment.newInstance(type);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mFragment)
                .commit();
    }

    public void updateMultipleHeaderFragment(int type) {
        mFragment = MultipleHeaderBottomFragment.newInstance(type);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mFragment)
                .commit();
    }

    public void updateAnimFragment(int type) {
        mFragment = AnimFragment.newInstance(type);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mFragment)
                .commit();
    }

    public void updateFullyExpandedFragment(int type) {
        mFragment = FullyExpandedFragment.newInstance(type);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_normal_list:
                ((NormalFragment)mFragment).changeStyle(3);
                break;
            case R.id.action_normal_grid:
                ((NormalFragment)mFragment).changeStyle(1);
                break;
            case R.id.action_normal_staggered:
                ((NormalFragment)mFragment).changeStyle(2);
                break;
        }
        return true;
    }
}
