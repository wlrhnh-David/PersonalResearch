package com.baidu.weiwei22.personalresearch.recycler_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.weiwei22.personalresearch.R;

/**
 * Created by weiwei22 on 17/6/27.
 */

public class RecycleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_recycle);

        getSupportFragmentManager().beginTransaction().add(R.id.container, RecycleFragment.getInstance()).commitAllowingStateLoss();
    }
}
