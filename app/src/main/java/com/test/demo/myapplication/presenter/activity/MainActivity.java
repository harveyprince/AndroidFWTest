package com.test.demo.myapplication.presenter.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
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
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.v2)
    TextView textView;

    int windowwidth;
    int windowheight;

    private LayoutParams layoutParams ;

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

        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        windowheight = getWindowManager().getDefaultDisplay().getHeight();

        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x1=0,y1=0;
                int x2,y2;
                int x3 = 0,y3 = 0;
                LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("down");
                        x1 = (int) event.getX();
                        y1 = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("move");
                        x2 = (int)event.getX();
                        y2 = (int)event.getY();
                        System.out.println("x2:"+x1+"|y2:"+y1);
                        /*int x_cord = (int)event.getRawX();
                        int y_cord = (int)event.getRawY();*/

                        /*if(x_cord>windowwidth){x_cord=windowwidth;}
                        if(y_cord>windowheight){y_cord=windowheight;}*/


                        /*layoutParams.leftMargin = x_cord - (int)event.getX();
                        layoutParams.topMargin = y_cord - (int)event.getY();*/

                        layoutParams.leftMargin = layoutParams.leftMargin + x2 - x1;
                        layoutParams.topMargin = layoutParams.topMargin + y2 - y1;

                        System.out.println("x:"+layoutParams.leftMargin+"|y:"+layoutParams.topMargin);
                        imageView.setLayoutParams(layoutParams);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

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
