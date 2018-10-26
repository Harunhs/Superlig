package com.example.harunsofuoglu.superlig.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by harunsofuoglu on 26.10.2018.
 */

public class ApiClient {


    private  static final String BASE_URL="http://zmdp.cloud/";

    private static Retrofit retrofit=null;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Gson gson = new GsonBuilder().create();
    private static GsonConverterFactory converterFactory = GsonConverterFactory.create(gson);




    private static Retrofit.Builder builder=
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(converterFactory);


    public static ApiService createService(final Context context) {

        httpClient.interceptors().clear();


            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authentication","Barear" )
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    okhttp3.Response response = chain.proceed(request);


                    return response;
                }


            });


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=httpClient.writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES)
                .connectTimeout(1,TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit =builder
                .client(client)
                .build();


        return retrofit.create(ApiService.class);

    }
}
