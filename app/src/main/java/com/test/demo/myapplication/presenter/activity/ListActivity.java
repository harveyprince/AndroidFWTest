package com.test.demo.myapplication.presenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.test.demo.myapplication.R;

import butterknife.BindView;

public class ListActivity extends AppCompatActivity implements CanRefreshLayout.OnRefreshListener, CanRefreshLayout.OnLoadMoreListener {

    @BindView(R.id.can_content_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    CanRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
