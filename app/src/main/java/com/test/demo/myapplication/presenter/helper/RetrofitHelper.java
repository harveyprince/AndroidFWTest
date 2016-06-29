package com.test.demo.myapplication.presenter.helper;

import android.content.Context;
import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by harveyprince on 16/6/28.
 */
public class RetrofitHelper {
    static Retrofit retrofit = null;

    public static Retrofit build(Context context){
        if (retrofit == null) {

            ClearableCookieJar cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

            OkHttpClient client = new OkHttpClient().newBuilder().cookieJar(cookieJar).addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    long t1 = System.nanoTime();
                    String requestLog = String.format("Sending request %s on %s%n%s",
                            request.url(), chain.connection(), request.headers());


                    if(request.method().compareToIgnoreCase("post")==0){
                        requestLog ="\n"+requestLog+"\n"+bodyToString(request);
                    }
                    Log.d("TAG","request"+"\n"+requestLog);

                    Response response = chain.proceed(request);
                    long t2 = System.nanoTime();

                    String responseLog = String.format("Received response for %s in %.1fms%n%s",
                            response.request().url(), (t2 - t1) / 1e6d, response.headers());

                    String bodyString = response.body().string();

                    Log.d("TAG","response"+"\n"+responseLog+"\n"+bodyString);

                    return response.newBuilder()
                            .body(ResponseBody.create(response.body().contentType(), bodyString))
                            .build();
                }
            }).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://115.28.78.53/")
                    .addConverterFactory(TGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
