package com.test.demo.myapplication.presenter.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.demo.myapplication.R;
import com.test.demo.myapplication.databinding.ActivityMainBinding;
import com.test.demo.myapplication.model.dao.cache.PicassoBigCache;
import com.test.demo.myapplication.model.entity.Account;
import com.test.demo.myapplication.model.entity.Person;
import com.test.demo.myapplication.model.entity.Repo;
import com.test.demo.myapplication.model.entity.SignInResult;
import com.test.demo.myapplication.model.service.GithubService;
import com.test.demo.myapplication.model.service.TableService;
import com.test.demo.myapplication.presenter.dagger.demo.CoffeeApp;
import com.test.demo.myapplication.presenter.dagger.demo.DripCoffeeModule;
import com.test.demo.myapplication.presenter.helper.RetrofitHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.ObjectGraph;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.v2)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Person person = new Person();
        person.setName("harveyprince");
        person.setAge(20);
        binding.setUser(person);

        PicassoBigCache.INSTANCE.getPicassoBigCache(getApplicationContext())
                .load("http://i.imgur.com/DvpvklR.png")
                .into(imageView);


        Retrofit retrofit = RetrofitHelper.build(this);

//        GithubService service = retrofit.create(GithubService.class);

        TableService tservice = retrofit.create(TableService.class);

        Account account = new Account();
        account.setEmail("948841233@qq.com");
        account.setPassword("123");

        tservice.signIn(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        System.out.println("error occured");
                    }
                })
                .subscribe(new Action1<SignInResult>() {
                       @Override
                       public void call(SignInResult signInResult) {
                           System.out.println("user_id:"+signInResult.getUser_id());
                       }
                   }
                );


        /*service.listRepos("harveyprince")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
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
                });*/


    }

    @OnClick(R.id.v2)
    public void textClick(){
        ListActivity.startActivity(getApplicationContext());
    }

    @OnClick(R.id.imageView)
    public void imageClick() {
        /*System.out.println("image clicked");
        ObjectGraph objectGraph = ObjectGraph.create(new DripCoffeeModule());
        CoffeeApp coffeeApp = objectGraph.get(CoffeeApp.class);
        coffeeApp.run();*/
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        imageView.startAnimation(anim);
        System.out.println("fade_in anim");
    }

}
