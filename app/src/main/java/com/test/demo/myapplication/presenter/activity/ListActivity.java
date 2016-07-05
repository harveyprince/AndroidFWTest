package com.test.demo.myapplication.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.test.demo.myapplication.R;
import com.test.demo.myapplication.databinding.ActivityListBinding;
import com.test.demo.myapplication.databinding.ActivityMainBinding;
import com.test.demo.myapplication.model.entity.Repo;
import com.test.demo.myapplication.model.entity.ViewModel;
import com.test.demo.myapplication.model.service.GithubService;
import com.test.demo.myapplication.presenter.adapter.GItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity implements CanRefreshLayout.OnRefreshListener, CanRefreshLayout.OnLoadMoreListener {

    @BindView(R.id.can_content_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    CanRefreshLayout refresh;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

//        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        viewModel = new ViewModel();
        binding.setViewModel(viewModel);

        refresh.setOnLoadMoreListener(this);
        refresh.setOnRefreshListener(this);

        refresh.setStyle(0, 0);


        viewModel.items.addAll(Repo.getList());

//        adapter = new GItemAdapter(getApplicationContext(), list);
//        recyclerView.setAdapter(adapter);

//        adapter.notifyDataSetChanged();

    }

    @Override
    public void onLoadMore() {
        refresh.postDelayed(new Runnable() {
            @Override
            public void run() {
//                list.addAll(Repo.getList());
                viewModel.items.addAll(Repo.getList());
//                adapter.notifyDataSetChanged();

                refresh.loadMoreComplete();
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        refresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                /*list.clear();
                list.addAll(Repo.getList());*/
                viewModel.items.clear();
                viewModel.items.addAll(Repo.getList());
//                adapter.notifyDataSetChanged();
                refresh.refreshComplete();
            }
        }, 1000);

    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
