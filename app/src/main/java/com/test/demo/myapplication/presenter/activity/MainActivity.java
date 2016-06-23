package com.test.demo.myapplication.presenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.okhttp.ResponseBody;
import com.test.demo.myapplication.R;
import com.test.demo.myapplication.model.dao.cache.PicassoBigCache;
import com.test.demo.myapplication.model.entity.Repo;
import com.test.demo.myapplication.model.service.GithubService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        PicassoBigCache.INSTANCE.getPicassoBigCache(getApplicationContext())
                .load("http://i.imgur.com/DvpvklR.png")
                .into(imageView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        GithubService service = retrofit.create(GithubService.class);

        service.listRepos("harveyprince")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("error occured");
                    }
                })
                .subscribe(new Subscriber<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        textView.setText("error occured");
                    }

                    @Override
                    public void onNext(List<Repo> jsonObject) {
                        textView.setText(jsonObject.toString());
                    }
                });

    }

    @OnClick(R.id.textView)
    public void textClick(){
        ListActivity.startActivity(getApplicationContext());
    }

}
