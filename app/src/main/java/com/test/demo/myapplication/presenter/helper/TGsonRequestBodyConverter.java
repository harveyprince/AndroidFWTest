package com.test.demo.myapplication.presenter.helper;

/**
 * Created by harveyprince on 16/6/29.
 */
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.Buffer;
import retrofit2.Converter;

final class TGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final MediaType TMEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    TGsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);

        adapter.write(jsonWriter, value);
        jsonWriter.close();
        System.out.println("request body convert");

        String content = buffer.readString(Util.UTF_8);
        System.out.println(content);
        System.out.println("request body convert end");
        String t_content = "";
        try {
            JSONObject obj = new JSONObject(content);
            Iterator<String> keys = obj.keys();
            while(keys.hasNext()){
                if (t_content != "") {
                    t_content += "&";
                }
                String key = keys.next();
                t_content += key + "=" + URLEncoder.encode(obj.getString(key), "UTF-8");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (t_content != "") {
            content = t_content;
//            content = URLEncoder.encode(t_content, "UTF-8");
        }
        return RequestBody.create(TMEDIA_TYPE, content);
    }
}
