package com.test.demo.myapplication.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.demo.myapplication.R;
import com.test.demo.myapplication.model.entity.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harveyprince on 16/6/23.
 */
public class GItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    public List<Repo> list;

    public GItemAdapter(Context context, List<Repo> list) {
        this.context = context;
        this.list = list;
    }

    public class GItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview)
        public CardView cardView;
        @BindView(R.id.textview)
        public TextView textview;

        public GItemViewHolder(CardView view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);

        return new GItemViewHolder((CardView) v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GItemViewHolder vh = (GItemViewHolder) holder;
        Repo po = list.get(position);
        vh.textview.setText(po.getFull_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
